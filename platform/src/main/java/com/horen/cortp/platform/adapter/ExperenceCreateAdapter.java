package com.horen.cortp.platform.adapter;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.activity.ExperienceCreatePictureActivity;
import com.horen.cortp.platform.activity.ExperienceCreateTextActivity;
import com.horen.cortp.platform.api.CommonCode;
import com.horen.cortp.platform.bean.ExperienceCreate;
import com.jaydenxiao.common.commonutils.GlideUtils;
import java.util.ArrayList;
/**
 * Created by HOREN on 2017/12/27.
 */
public class ExperenceCreateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_ITEM_TXT = 1001;
    private final int TYPE_ITEM_PIC = 1002;
    private Activity context;
    private boolean fromExperience;
    private ArrayList<ExperienceCreate> datas = new ArrayList<>();
    public ExperenceCreateAdapter(Activity context, boolean fromExperience){
        this.context = context;
        this.fromExperience = fromExperience;
    }
    public void setData(ArrayList<ExperienceCreate> datas){
        this.datas = datas;
    }
    public void addData(ExperienceCreate data){
        datas.add(data);
    }
    public void update(int index,ExperienceCreate data){
        datas.set(index,data);
    }
    public void remove(int index){
        datas.remove(index);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_TXT){
            View view = LayoutInflater.from(context).inflate(R.layout.activity_experience_create_itemtxt,parent,false);
            return new HolderTxt(view);
        } else if (viewType == TYPE_ITEM_PIC){
            View view = LayoutInflater.from(context).inflate(R.layout.activity_experience_create_itempic,parent,false);
            return new HolderPic(view);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ExperienceCreate create = datas.get(position);
        if (holder instanceof HolderTxt) {
            HolderTxt holderTxt = (HolderTxt) holder;
            holderTxt.textView.setText(create.experience_text);
            holderTxt.layoutTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ExperienceCreateTextActivity.class);
                    intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_TXTDATA,create.experience_text);
                    intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE,false);
                    intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_INDEX,position);
                    intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_TYPE,fromExperience);
                    context.startActivityForResult(intent,CommonCode.RESULT_CODE_CREATE_TXT_SUCCESS);
                }
            });
        } else if (holder instanceof HolderPic) {
            HolderPic holderPic = (HolderPic) holder;
            GlideUtils.load(context,create.experience_image,holderPic.imageView);
            holderPic.layoutPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ExperienceCreatePictureActivity.class);
                    ArrayList<String> photos = new ArrayList<>();
                    photos.add(create.experience_image);
                    intent.putStringArrayListExtra(CommonCode.INTENT_EXPERIENCE_CREATE_PICDATA,photos);
                    intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE,false);
                    intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_INDEX,position);
                    intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_TYPE,fromExperience);
                    context.startActivityForResult(intent,CommonCode.RESULT_CODE_CREATE_PIC_SUCCESS);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(datas.get(position).experience_text)) {
            return TYPE_ITEM_PIC;
        } else {
            return TYPE_ITEM_TXT;
        }
    }
    public ArrayList<ExperienceCreate> getData() {
        return datas;
    }
    class HolderTxt extends RecyclerView.ViewHolder{
        LinearLayout layoutTxt;
        TextView textView;
        public HolderTxt(View itemView) {
            super(itemView);
            layoutTxt = itemView.findViewById(R.id.ll_experience_create_txt);
            textView = itemView.findViewById(R.id.tv_experience_create_txt);
        }
    }
    class HolderPic extends RecyclerView.ViewHolder{
        LinearLayout layoutPic;
        ImageView imageView;
        public HolderPic(View itemView) {
            super(itemView);
            layoutPic = itemView.findViewById(R.id.ll_experience_create_pic);
            imageView = itemView.findViewById(R.id.iv_experience_create_pic);
        }
    }
}