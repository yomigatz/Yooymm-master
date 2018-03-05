package yooymm.com;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

import yooymm.com.util.ScreenUtil;

/**
 * Created by yaomi on 2018/2/12.
 * Time ：17:11
 */

public class GridPicListAdapter extends BaseAdapter {

    private List<Bitmap> mList;
    private Context mContext;

    public GridPicListAdapter(List<Bitmap> list, Context context){
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv_pic_item = null;
        int density = (int) ScreenUtil.getDeviceDensity(mContext);
        if (convertView == null){
            iv_pic_item = new ImageView(mContext);
            //设置布局图片
            iv_pic_item.setLayoutParams(new GridView.LayoutParams(80*density,100*density));
            //设置显示比例类型
            iv_pic_item.setScaleType(ImageView.ScaleType.FIT_XY);
        }else {
            iv_pic_item = (ImageView) convertView;
        }
        iv_pic_item.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
        iv_pic_item.setImageBitmap(mList.get(position));
        return iv_pic_item;
    }
}
