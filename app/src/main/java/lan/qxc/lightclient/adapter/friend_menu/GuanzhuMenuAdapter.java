package lan.qxc.lightclient.adapter.friend_menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import lan.qxc.lightclient.R;
import lan.qxc.lightclient.entity.FriendVO;
import lan.qxc.lightclient.retrofit_util.api.APIUtil;
import lan.qxc.lightclient.ui.fragment.friend_menu.GuanzhuMenuContactFragment;
import lan.qxc.lightclient.util.ImageUtil;

public class GuanzhuMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<FriendVO> friendVOS;
    private Context mContext;
    private LayoutInflater layoutInflater;

    private GuanzhuMenuContactFragment.ClickGzMenuListener clickGzMenuListener;
    private GuanzhuMenuContactFragment.ClickUserLayoutListener clickUserLayoutListener;



    public GuanzhuMenuAdapter(Context context, List<FriendVO> friendVOS){
        this.mContext = context;
        this.friendVOS=friendVOS;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setClickGzMenuListener(GuanzhuMenuContactFragment.ClickGzMenuListener clickGzMenuListener){
        this.clickGzMenuListener = clickGzMenuListener;
    }

    public void setClickUserLayoutListener(GuanzhuMenuContactFragment.ClickUserLayoutListener clickUserLayoutListener){
        this.clickUserLayoutListener = clickUserLayoutListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new GuanzhuMenuAdapter.GuanzhuViewHolder(layoutInflater.inflate(R.layout.item_guanzhu_contact_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        FriendVO friendVO = friendVOS.get(position);

        String headIcPath = APIUtil.getUrl(friendVO.getIcon());
        ImageUtil.getInstance().setNetImageToView(mContext,headIcPath,((GuanzhuViewHolder)holder).iv_headic_guanzhu_list_item);
        ((GuanzhuViewHolder)holder).tv_nickname_guanzhu_list_item.setText(friendVO.getUsername());
        ((GuanzhuViewHolder)holder).tv_intro_guanzhu_list_item.setText(friendVO.getIntroduce());


        int gzType = friendVO.getGuanzhu_type();
        if(gzType==1){        //我已经关注了对方
            ((GuanzhuViewHolder)holder).layout_gz_guanzhu_contact_list_item.setBackground(mContext.getResources().getDrawable(R.drawable.redis_has_guanzhu_layout));
            ((GuanzhuViewHolder)holder).iv_gz_addlable_guanzhu_contact_item.setVisibility(View.GONE);
            ((GuanzhuViewHolder)holder).tv_gz_text_guanzhu_contact_list_item.setTextColor(mContext.getResources().getColor(R.color.my_font_grey));
            ((GuanzhuViewHolder)holder).tv_gz_text_guanzhu_contact_list_item.setText("已关注");

        }else if(gzType==0){   //双方都关注了彼此  那就是好友

            ((GuanzhuViewHolder)holder).layout_gz_guanzhu_contact_list_item.setBackground(mContext.getResources().getDrawable(R.drawable.redis_has_guanzhu_layout));
            ((GuanzhuViewHolder)holder).iv_gz_addlable_guanzhu_contact_item.setVisibility(View.GONE);
            ((GuanzhuViewHolder)holder).tv_gz_text_guanzhu_contact_list_item.setTextColor(mContext.getResources().getColor(R.color.my_font_grey));
            ((GuanzhuViewHolder)holder).tv_gz_text_guanzhu_contact_list_item.setText("好友");

        }else{                           //我未关注对方
            ((GuanzhuViewHolder)holder).layout_gz_guanzhu_contact_list_item.setBackground(mContext.getResources().getDrawable(R.drawable.redis_guanzhu_layout));
            ((GuanzhuViewHolder)holder).iv_gz_addlable_guanzhu_contact_item.setVisibility(View.VISIBLE);
            ((GuanzhuViewHolder)holder).tv_gz_text_guanzhu_contact_list_item.setTextColor(mContext.getResources().getColor(R.color.my_orange_light));
            ((GuanzhuViewHolder)holder).tv_gz_text_guanzhu_contact_list_item.setText("关注");
        }


        ((GuanzhuViewHolder)holder).layout_title_guanzhu_list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUserLayoutListener.getPosition(position);
            }
        });

        ((GuanzhuViewHolder)holder).layout_gz_guanzhu_contact_list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGzMenuListener.getPosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendVOS.size();
    }


    class GuanzhuViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout layout_title_guanzhu_list_item;
        CircleImageView iv_headic_guanzhu_list_item;
        TextView tv_nickname_guanzhu_list_item;
        TextView tv_intro_guanzhu_list_item;

        LinearLayout layout_gz_guanzhu_contact_list_item;
        ImageView iv_gz_addlable_guanzhu_contact_item;
        TextView tv_gz_text_guanzhu_contact_list_item;

        public GuanzhuViewHolder(View view) {
            super(view);
            layout_title_guanzhu_list_item = view.findViewById(R.id.layout_title_guanzhu_list_item);
            iv_headic_guanzhu_list_item = view.findViewById(R.id.iv_headic_guanzhu_list_item);
            tv_nickname_guanzhu_list_item = view.findViewById(R.id.tv_nickname_guanzhu_list_item);
            tv_intro_guanzhu_list_item = view.findViewById(R.id.tv_intro_guanzhu_list_item);


            layout_gz_guanzhu_contact_list_item = view.findViewById(R.id.layout_gz_guanzhu_contact_list_item);
            iv_gz_addlable_guanzhu_contact_item = view.findViewById(R.id.iv_gz_addlable_guanzhu_contact_item);
            tv_gz_text_guanzhu_contact_list_item = view.findViewById(R.id.tv_gz_text_guanzhu_contact_list_item);

        }
    }

}
