package com.horen.cortp.platform.api;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
/**
 * Created by HOREN on 2017/11/15.
 *
 * to >>>>>> RequestBody build()
 *
 * app_token locale
 *
 */
public class MyMultipartBody extends RequestBody {
    public static final MediaType MIXED = MediaType.parse("multipart/mixed");
    public static final MediaType ALTERNATIVE = MediaType.parse("multipart/alternative");
    public static final MediaType DIGEST = MediaType.parse("multipart/digest");
    public static final MediaType PARALLEL = MediaType.parse("multipart/parallel");
    public static final MediaType FORM = MediaType.parse("multipart/form-data");
    public static final MediaType FORM_DATA = MediaType.parse("form-data");
    private static final byte[] COLONSPACE = {':', ' '};
    private static final byte[] CRLF = {'\r', '\n'};
    private static final byte[] DASHDASH = {'-', '-'};
    private final ByteString boundary;
    private final MediaType originalType;
    private final MediaType contentType;
    private final List<MyMultipartBody.Part> parts;
    private long contentLength = -1L;
    MyMultipartBody(ByteString boundary, MediaType type, List<MyMultipartBody.Part> parts) {
        this.boundary = boundary;
        this.originalType = type;
        this.contentType = MediaType.parse(type + ";boundary=" + boundary.utf8());
        this.parts = Util.immutableList(parts);
    }

    public MediaType type() {
        return originalType;
    }

    public String boundary() {
        return boundary.utf8();
    }

    /** The number of parts in this multipart body. */
    public int size() {
        return parts.size();
    }

    public List<MyMultipartBody.Part> parts() {
        return parts;
    }

    public MyMultipartBody.Part part(int index) {
        return parts.get(index);
    }

    /** A combination of {@link #type()} and {@link #boundary()}. */
    @Override public MediaType contentType() {
        return contentType;
    }

    @Override public long contentLength() throws IOException {
        long result = contentLength;
        if (result != -1L) return result;
        return contentLength = writeOrCountBytes(null, true);
    }

    @Override public void writeTo(BufferedSink sink) throws IOException {
        writeOrCountBytes(sink, false);
    }
    private long writeOrCountBytes(
            @Nullable BufferedSink sink, boolean countBytes) throws IOException {
        long byteCount = 0L;

        Buffer byteCountBuffer = null;
        if (countBytes) {
            sink = byteCountBuffer = new Buffer();
        }

        for (int p = 0, partCount = parts.size(); p < partCount; p++) {
            MyMultipartBody.Part part = parts.get(p);
            Headers headers = part.headers;
            RequestBody body = part.body;

            sink.write(DASHDASH);
            sink.write(boundary);
            sink.write(CRLF);

            if (headers != null) {
                for (int h = 0, headerCount = headers.size(); h < headerCount; h++) {
                    sink.writeUtf8(headers.name(h))
                            .write(COLONSPACE)
                            .writeUtf8(headers.value(h))
                            .write(CRLF);
                }
            }

            MediaType contentType = body.contentType();
            if (contentType != null) {
                sink.writeUtf8("Content-Type:")
                        .writeUtf8(contentType.toString())
                        .write(CRLF);
            }

            long contentLength = body.contentLength();
            if (contentLength != -1) {
                sink.writeUtf8("Content-Length:")
                        .writeDecimalLong(contentLength)
                        .write(CRLF);
            } else if (countBytes) {
                // We can't measure the body's size without the sizes of its components.
                byteCountBuffer.clear();
                return -1L;
            }

            sink.write(CRLF);

            if (countBytes) {
                byteCount += contentLength;
            } else {
                body.writeTo(sink);
            }

            sink.write(CRLF);
        }

        sink.write(DASHDASH);
        sink.write(boundary);
        sink.write(DASHDASH);
        sink.write(CRLF);

        if (countBytes) {
            byteCount += byteCountBuffer.size();
            byteCountBuffer.clear();
        }

        return byteCount;
    }
    static StringBuilder appendQuotedString(StringBuilder target, String key) {
        target.append('"');
        for (int i = 0, len = key.length(); i < len; i++) {
            char ch = key.charAt(i);
            switch (ch) {
                case '\n':
                    target.append("%0A");
                    break;
                case '\r':
                    target.append("%0D");
                    break;
                case '"':
                    target.append("%22");
                    break;
                default:
                    target.append(ch);
                    break;
            }
        }
        target.append('"');
        return target;
    }
    public static RequestBody create(File file) {
        return create(FORM,file);
    }
    public static RequestBody create(String value) {
        return create(FORM_DATA,value);
    }
    public static final class Part {
        public static MyMultipartBody.Part create(RequestBody body) {
            return create(null, body);
        }

        public static MyMultipartBody.Part create(@Nullable Headers headers, RequestBody body) {
            if (body == null) {
                throw new NullPointerException("body == null");
            }
            if (headers != null && headers.get("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (headers != null && headers.get("Content-Length") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Length");
            }
            return new MyMultipartBody.Part(headers, body);
        }

        public static MyMultipartBody.Part createFormData(String name, String value) {
            return createFormData(name, null, RequestBody.create(FORM_DATA, value));
        }

        public static MyMultipartBody.Part createFormData(String name, @Nullable String filename, RequestBody body) {
            if (name == null) {
                throw new NullPointerException("name == null");
            }
            StringBuilder disposition = new StringBuilder("form-data;name=");
            appendQuotedString(disposition, name);

            if (filename != null) {
                disposition.append(";filename=");
                appendQuotedString(disposition, filename);
            }

            return create(Headers.of("Content-Disposition", disposition.toString()), body);
        }

        final @Nullable Headers headers;
        final RequestBody body;

        private Part(@Nullable Headers headers, RequestBody body) {
            this.headers = headers;
            this.body = body;
        }

        public @Nullable Headers headers() {
            return headers;
        }

        public RequestBody body() {
            return body;
        }
    }

    public static final class Builder {
        private final ByteString boundary;
        private MediaType type = MIXED;
        private final List<MyMultipartBody.Part> parts = new ArrayList<>();

        public Builder() {
            this(UUID.randomUUID().toString());
            setType(FORM);
        }

        public Builder(String boundary) {
            this.boundary = ByteString.encodeUtf8(boundary);
        }
        public MyMultipartBody.Builder setType(MediaType type) {
            if (type == null) {
                throw new NullPointerException("type == null");
            }
            if (!type.type().equals("multipart")) {
                throw new IllegalArgumentException("multipart != " + type);
            }
            this.type = type;
            return this;
        }
        /** Add a part to the body. */
        public MyMultipartBody.Builder addPart(RequestBody body) {
            return addPart(MyMultipartBody.Part.create(body));
        }

        /** Add a part to the body. */
        public MyMultipartBody.Builder addPart(@Nullable Headers headers, RequestBody body) {
            return addPart(MyMultipartBody.Part.create(headers, body));
        }

        /** Add a form data part to the body. */
        public MyMultipartBody.Builder addFormDataPart(String name, String value) {
            return addPart(MyMultipartBody.Part.createFormData(name, value));
        }

        /** Add a form data part to the body. */
        public MyMultipartBody.Builder addFormDataPart(String name, @Nullable String filename, RequestBody body) {
            return addPart(MyMultipartBody.Part.createFormData(name, filename, body));
        }

        /** Add a part to the body. */
        public MyMultipartBody.Builder addPart(MyMultipartBody.Part part) {
            if (part == null) throw new NullPointerException("part == null");
            parts.add(part);
            return this;
        }
        /** Assemble the specified parts into a request body. */
        public MyMultipartBody build() {
            if (parts.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new MyMultipartBody(boundary, type, parts);
        }
    }
}