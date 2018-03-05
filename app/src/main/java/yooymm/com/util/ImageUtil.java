package yooymm.com.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.util.ArrayList;
import java.util.List;

import yooymm.com.PuzzleActivity;
import yooymm.com.R;
import yooymm.com.bean.GridItem;

/**
 * 图像工具类：实现图像的分割与自适应
 *
 */
public class ImageUtil {

    /**
     * 切图、初始状态（正常顺序）
     *
     * @param type        游戏种类
     * @param bitmap 选择的图片
     * @param context     context
     */
    public static void createInitBitmaps(int type, Bitmap bitmap,
                                  Context context) {
        List<Bitmap> bitmapList = new ArrayList<Bitmap>();
        // 每个Item的宽高
        int itemWidth = bitmap.getWidth() / type;
        int itemHeight = bitmap.getHeight() / type;
        for (int i = 1; i <= type; i++) {
            for (int j = 1; j <= type; j++) {
                Bitmap childBitmap = Bitmap.createBitmap(
                        bitmap,
                        (j - 1) * itemWidth,
                        (i - 1) * itemHeight,
                        itemWidth,
                        itemHeight);
                bitmapList.add(childBitmap);
                GridItem gridItem = new GridItem(
                        (i - 1) * type + j,
                        (i - 1) * type + j,
                        childBitmap);
                GameUtil.gridItemList.add(gridItem);
            }
        }
        // 保存最后一个图片在拼图完成时填充
        final int lastItemIndex = type * type - 1;
        PuzzleActivity.lastBitmap = bitmapList.get(lastItemIndex);
        // 设置最后一个为空Item
        bitmapList.remove(lastItemIndex);
        GameUtil.gridItemList.remove(lastItemIndex);
        Bitmap blankBitmap = BitmapFactory.decodeResource(
                context.getResources(), R.drawable.blank);
        blankBitmap = Bitmap.createBitmap(
                blankBitmap, 0, 0, itemWidth, itemHeight);

        bitmapList.add(blankBitmap);
        GameUtil.gridItemList.add(new GridItem(type * type, 0, blankBitmap));
        GameUtil.blankGridItem = GameUtil.gridItemList.get(lastItemIndex);
    }

    /**
     * 处理图片 放大、缩小到合适位置
     *
     * @param newWidth  缩放后Width
     * @param newHeight 缩放后Height
     * @param bitmap    bitmap
     * @return bitmap
     */
    public static Bitmap resizeBitmap(float newWidth, float newHeight, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(
                newWidth / bitmap.getWidth(),
                newHeight / bitmap.getHeight());
        Bitmap newBitmap = Bitmap.createBitmap(
                bitmap, 0, 0,
                bitmap.getWidth(),
                bitmap.getHeight(),
                matrix, true);
        return newBitmap;
    }
}