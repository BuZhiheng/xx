package com.horen.cortp.platform.api;
import android.support.annotation.Nullable;
import com.jaydenxiao.common.baseapp.AppConfig;
import com.jaydenxiao.common.commonutils.LanguageUtil;
import com.jaydenxiao.common.commonutils.SpKey;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
/**
 * Created by HOREN on 2017/11/15.
 *
 * to >>>>>> RequestBody build()
 *
 * app_token locale
 *
 */
public class MyRequestBody extends RequestBody {
    private static final MediaType CONTENT_TYPE =
            MediaType.parse("application/json; charset=utf-8");
    private final List<String> encodedNames;
    private final List<String> encodedValues;

    MyRequestBody(List<String> encodedNames, List<String> encodedValues) {
        this.encodedNames = Util.immutableList(encodedNames);
        this.encodedValues = Util.immutableList(encodedValues);
    }
    /** The number of key-value pairs in this form-encoded body. */
    public int size() {
        return encodedNames.size();
    }
    @Override public MediaType contentType() {
        return CONTENT_TYPE;
    }
    @Override public long contentLength() {
        return writeOrCountBytes(null, true);
    }
    @Override public void writeTo(BufferedSink sink) throws IOException {
        writeOrCountBytes(sink, false);
    }
    /**
     * Either writes this request to {@code sink} or measures its content length. We have one method
     * do double-duty to make sure the counting and content are consistent, particularly when it comes
     * to awkward operations like measuring the encoded length of header strings, or the
     * length-in-digits of an encoded integer.
     */
    private long writeOrCountBytes(@Nullable BufferedSink sink, boolean countBytes) {
        long byteCount = 0L;
        Buffer buffer;
        if (countBytes) {
            buffer = new Buffer();
        } else {
            buffer = sink.buffer();
        }
        for (int i = 0, size = encodedNames.size(); i < size; i++) {
            if (i > 0) buffer.writeByte('&');
            buffer.writeUtf8(encodedNames.get(i));
            buffer.writeByte('=');
            buffer.writeUtf8(encodedValues.get(i));
        }
        if (countBytes) {
            byteCount = buffer.size();
            buffer.clear();
        }
        return byteCount;
    }
    public static final class Builder {
        JSONObject jsonObject = new JSONObject();
        public MyRequestBody.Builder add(String name, String value) {
            try {
                jsonObject.put(name, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return this;
        }
        public RequestBody build() {
            try {
                jsonObject.put("app_token", AppConfig.getInstance().getString(SpKey.LOGIN_TOKEN, ""));
                jsonObject.put("locale", LanguageUtil.getLanguageParam());
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return create(CONTENT_TYPE,jsonObject.toString());
        }
    }
}