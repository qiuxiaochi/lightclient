package lan.qxc.lightclient.ui.widget.nice9layout;

import android.content.Context;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lan.qxc.lightclient.R;


public class ImageMulitVAdapter extends VirtualLayoutAdapter<ImageMulitVAdapter.ImageViewHolder> implements MyItemTouchCallback.ItemTouchAdapter {
    private List<String> pictures = new ArrayList<>();
    private Context context;
    private boolean canDrag;
    private int itemMargin;
    private ImageNice9Layout.ItemDelegate mItemDelegate;
    private ImageNice9Layout.ItemClickDelete itemClickDelete;

//    public ImageMulitVAdapter(@NonNull VirtualLayoutManager layoutManager, List<String> pictures, Context context, boolean canDrag, int itemMagrin) {
//        super(layoutManager);
//        this.pictures = pictures;
//        this.context = context;
//        this.canDrag = canDrag;
//        this.itemMargin = itemMagrin;
//    }


    public ImageMulitVAdapter(@NonNull VirtualLayoutManager layoutManager, Context context, boolean canDrag, int itemMargin) {
        super(layoutManager);
        this.context = context;
        this.canDrag = canDrag;
        this.itemMargin = itemMargin;
    }

    public void bindData(List<String> pictures){
        this.pictures = pictures;
        notifyDataSetChanged();
    }

    public void setItemDelegate(ImageNice9Layout.ItemDelegate itemDelegate) {
        mItemDelegate = itemDelegate;
    }

    public void setItemClickDelete(ImageNice9Layout.ItemClickDelete itemClickDelete){
        this.itemClickDelete = itemClickDelete;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_nine_mulit_image, null));
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, final int position) {
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = 0, height = 0;
        int imageCount = pictures.size();
        int displayW = DisplayUtils.getDisplayWidth(context);
        if (imageCount == 1) {
            width = displayW;
            height = width;
        } else if (imageCount == 2) {
            width = displayW / 2;
            height = width;
        } else if (imageCount == 3) {
            if (position == 0) {
                width = (int) (displayW * 0.66);
                height = width;
                layoutParams.rightMargin = itemMargin;
                layoutParams.bottomMargin = itemMargin;
            } else {
                if (position == 1 || position == 2) {
                    if (position == 1) {
                        layoutParams.bottomMargin = itemMargin / 2;
                    } else {
                        layoutParams.bottomMargin = itemMargin;
                    }
                }
                width = (int) ((displayW * 0.33));
                height = width;
            }
        } else if (imageCount == 4) {
            if (position == 0) {
                width = displayW;
                height = (int) (width * 0.5);
            } else {
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else if (imageCount == 5) {
            if (position == 0 || position == 1) {
                width = (int) (displayW * 0.5);
                height = width;
            } else {
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else if (imageCount == 6) {
            if (position == 0) {
                width = (int) (displayW * 0.66);
                height = width;
                layoutParams.rightMargin = 10;
                layoutParams.bottomMargin = 10;
            } else {
                if (position == 1 || position == 2) {
                    if (position == 1) {
                        layoutParams.bottomMargin = itemMargin / 2;
                    } else {
                        layoutParams.bottomMargin = itemMargin;
                    }

                }
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else if (imageCount == 7) {
            if (position == 0) {
                width = displayW;
                height = (int) (width * 0.5);
            } else {
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else if (imageCount == 8) {
            if (position == 0 || position == 1) {
                width = (int) (displayW * 0.5);
                height = width;
            } else {
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else {
            width = (int) (displayW * 0.33);
            height = width;
        }




//                    final int urlListSize = pictures != null ? pictures.size() : 0;
//            int column = 3;
//            if (pictures.size() == 1) {
//                column = 1;
//            } else if (pictures.size() == 4) {
//                column = 2;
//            }
//            int row = 0;
//            if (pictures.size() > 6) {
//                row = 3;
//            } else if (pictures.size() > 3) {
//                row = 2;
//            } else if (pictures.size() > 0) {
//                row = 1;
//            }
//            DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
//            int  mSingleMaxSize = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 216, mDisplayMetrics) + 0.5f);
//            int  mSpace = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, mDisplayMetrics) + 0.5f);
//            final int imageSize = urlListSize == 1 ? mSingleMaxSize :
//                    (int) ((displayW * 1f - mSpace * (column - 1)) / column);
//            layoutParams.width=imageSize;
//            layoutParams.height=layoutParams.width;






        layoutParams.width = width;
        layoutParams.height = height;
        holder.itemView.setLayoutParams(layoutParams);

        final String imageUrl = pictures.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (mItemDelegate != null){
                        mItemDelegate.onItemClick(position);
                    }
            }
        });
        Glide.with(context)
                .load(imageUrl)
                .into(holder.mImageView);

        holder.iv_nine_image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickDelete != null){
                    itemClickDelete.onItemDelete(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(pictures, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(pictures, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public List<String> getPictures() {
        return pictures;
    }

    @Override
    public void onSwiped(int position) {
        notifyItemRemoved(position);
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public ImageView iv_nine_image_delete;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_mulit_image);
            iv_nine_image_delete = (ImageView) itemView.findViewById(R.id.iv_nine_image_delete);
        }
    }

}
