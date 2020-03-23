package lan.qxc.lightclient.util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import lan.qxc.lightclient.R;
import lan.qxc.lightclient.retrofit_util.api.BaseAPI;

public class ImageUtil {


    public static ImageUtil getInstance(){
        return ImageUtilHolder.imageUtil;
    }

    private static class ImageUtilHolder{
        static ImageUtil imageUtil = new ImageUtil();
    }


    //显示网络图片到imageview
    public void setNetImageToView (Context context , String path, ImageView view){
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.default_icon);
        options.error(R.drawable.default_icon);

        Glide.with(context)
                .load(path)
                .apply(options)
                .into(view);
    }



}
