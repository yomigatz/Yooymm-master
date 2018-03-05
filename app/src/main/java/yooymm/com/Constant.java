package yooymm.com;

import android.os.Environment;

/**
 * Created by yaomi on 2018/2/13.
 * Time ：17:11
 */

public class Constant {


    //返回码：本地图库
    public static final int RESULT_IMAGE = 100;
    //IMAGE TYPE
    public static final String IMAGE_TYPE = "image/*";

    //Temp图片路径
    public static String TEMP_IMAGE_PATH = Environment.getExternalStorageDirectory().getPath()+"/temp.png";
    //返回码：相机
    public static final int RESULT_CAMERA = 200;

    public static final String TYPE = "type";
    public static final String SELECT_PHOTO_ID = "selectPhotoId";
    public static final String PHOTO_PATH = "photoPath";


}
