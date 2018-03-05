package yooymm.com.bean;

import android.graphics.Bitmap;

/**
 * 拼图Item逻辑实体类：封装逻辑相关属性
 *
 */
public class GridItem {

    // Item的Id
    private int id;
    // bitmap的Id
    private int bitmapId;
    // bitmap
    private Bitmap bitmap;

    public GridItem() {
    }

    public GridItem(int itemId, int bitmapId, Bitmap bitmap) {
        this.id = itemId;
        this.bitmapId = bitmapId;
        this.bitmap = bitmap;
    }

    public int getItemId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBitmapId() {
        return bitmapId;
    }

    public void setBitmapId(int bitmapId) {
        this.bitmapId = bitmapId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public String toString() {
        return "GridItem{" +
                "id=" + id +
                ", bitmapId=" + bitmapId +
                ", bitmap=" + bitmap +
                '}';
    }
}
