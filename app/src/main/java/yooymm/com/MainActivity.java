package yooymm.com;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yooymm.com.util.ScreenUtil;

public class MainActivity extends BaseActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @BindView(R.id.tv_puzzle_main_type_selected)
    TextView mTvPuzzleMainTypeSelected;
    @BindView(R.id.ll_puzzle_main_spinner)
    LinearLayout mLlPuzzleMainSpinner;
    @BindView(R.id.gv_xpuzzle_main_pic_list)
    GridView mGVPicList;
    private int[] mResPicId;
    private List<Bitmap> mBitmapList;

    // 游戏类型N*N
    private int type = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        mResPicId = new int[]{
                R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
                R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
                R.drawable.pic7, R.drawable.pic8, R.drawable.pic9,
                R.drawable.pic10, R.drawable.pic11, R.drawable.pic12,
                R.drawable.pic13, R.drawable.pic14,
                R.drawable.pic15, R.mipmap.ic_launcher
        };
        mBitmapList = new ArrayList<>();
        Bitmap[] mBitmaps = new Bitmap[mResPicId.length];
        for (int i = 0;i<mBitmaps.length;i++){
            mBitmaps[i] = BitmapFactory.decodeResource(getResources(),mResPicId[i]);
            mBitmapList.add(mBitmaps[i]);
        }
        mGVPicList.setAdapter(new GridPicListAdapter(mBitmapList,this));
    }

    private PopupWindow mPopupWindow;

    /**
     * 显示popup window
     *
     * @param view
     */
    private void popupShow(View view) {
        int density = (int) ScreenUtil.getDeviceDensity(this);
        //显示popup window
        mPopupWindow = new PopupWindow(view, 200 * density, 50 * density);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        //透明背景
        Drawable transpent = new ColorDrawable(Color.TRANSPARENT);
        mPopupWindow.setBackgroundDrawable(transpent);
        //获取位置
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        mPopupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] - 40 * density, location[1] + 30 * density);

    }

    /**
     * 调用图库相机回调方法
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Constant.RESULT_IMAGE && data != null) {
                // 相册
                Cursor cursor = this.getContentResolver().query(
                        data.getData(), null, null, null, null);
                cursor.moveToFirst();
                String imagePath = cursor.getString(
                        cursor.getColumnIndex("_data"));
                Intent intent = new Intent(
                        MainActivity.this,
                        PuzzleActivity.class);
                intent.putExtra(Constant.PHOTO_PATH, imagePath);
                intent.putExtra(Constant.TYPE, type);
                cursor.close();
                startActivity(intent);
            } else if (requestCode == Constant.RESULT_CAMERA) {
                // 相机
                Intent intent = new Intent(
                        MainActivity.this,
                        PuzzleActivity.class);
                intent.putExtra("photoPath", Constant.TEMP_IMAGE_PATH);
                intent.putExtra(Constant.TYPE, type);
                startActivity(intent);
            }
        }
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

}
