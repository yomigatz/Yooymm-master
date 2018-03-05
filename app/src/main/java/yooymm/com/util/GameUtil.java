package yooymm.com.util;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import yooymm.com.PuzzleActivity;
import yooymm.com.bean.GridItem;

/**
 * 拼图工具类：实现拼图的交换与生成算法
 *
 */
public class GameUtil {

    // 游戏信息单元格Bean
    public static List<GridItem> gridItemList = new ArrayList<>();
    // 空格单元格
    public static GridItem blankGridItem = new GridItem();

    /**
     * 判断点击的Item是否可移动
     *
     * @param position position
     * @return 能否移动
     */
    public static boolean isMoveable(int position) {
        int type = PuzzleActivity.TYPE;
        // 获取空格Item
        int blankId = GameUtil.blankGridItem.getItemId() - 1;
        // 不同行 相差为type
        if (Math.abs(blankId - position) == type) {
            return true;
        }
        // 相同行 相差为1
        return (blankId / type == position / type) &&
                Math.abs(blankId - position) == 1;
    }

    /**
     * 交换空格与点击Item的位置
     *
     * @param from  交换图
     * @param blank 空白图
     */
    public static void swapItems(GridItem from, GridItem blank) {
        GridItem tempGridItem = new GridItem();
        // 交换BitmapId
        tempGridItem.setBitmapId(from.getBitmapId());
        from.setBitmapId(blank.getBitmapId());
        blank.setBitmapId(tempGridItem.getBitmapId());
        // 交换Bitmap
        tempGridItem.setBitmap(from.getBitmap());
        from.setBitmap(blank.getBitmap());
        blank.setBitmap(tempGridItem.getBitmap());
        // 设置新的Blank
        GameUtil.blankGridItem = from;
    }

    /**
     * 生成随机的Item
     */
    public static void getPuzzleGenerator() {
        int index = 0;
        // 随机打乱顺序
        for (int i = 0; i < gridItemList.size(); i++) {
            index = (int) (Math.random() *
                    PuzzleActivity.TYPE * PuzzleActivity.TYPE);
            swapItems(gridItemList.get(index), GameUtil.blankGridItem);
        }
        List<Integer> data = new ArrayList<Integer>();
        for (int i = 0; i < gridItemList.size(); i++) {
            data.add(gridItemList.get(i).getBitmapId());
        }
        // 判断生成是否有解
        if (canSolve(data)) {
            return;
        } else {
            Log.i("GameUtil","No answer,next generator...");
            getPuzzleGenerator();
        }
    }

    /**
     * 是否拼图成功
     *
     * @return 是否拼图成功
     */
    public static boolean isSuccess() {
        for (GridItem gridItem : GameUtil.gridItemList) {
            if (gridItem.getBitmapId() != 0 &&
                    (gridItem.getItemId()) == gridItem.getBitmapId()) {
                continue;
            } else if (gridItem.getBitmapId() == 0 &&
                    gridItem.getItemId() == PuzzleActivity.TYPE * PuzzleActivity.TYPE) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 该数据是否有解
     *
     * @param dataList 拼图数组数据
     * @return 该数据是否有解
     */
    public static boolean canSolve(List<Integer> dataList) {
        // 获取空格Id
        int blankId = GameUtil.blankGridItem.getItemId();
        // 可行性原则
        if (dataList.size() % 2 == 1) {
            return getInversions(dataList) % 2 == 0;
        } else {
            // 从底往上数,空格位于奇数行
            if (((blankId - 1) / PuzzleActivity.TYPE) % 2 == 1) {
                return getInversions(dataList) % 2 == 0;
            } else {
                // 从底往上数,空位位于偶数行
                return getInversions(dataList) % 2 == 1;
            }
        }
    }

    /**
     * 计算倒置和算法
     *
     * @param dataList 拼图数组数据
     * @return 该序列的倒置和
     */
    public static int getInversions(List<Integer> dataList) {
        int inversions = 0;
        int inversionCount = 0;
        for (int i = 0; i < dataList.size(); i++) {
            for (int j = i + 1; j < dataList.size(); j++) {
                int index = dataList.get(i);
                if (dataList.get(j) != 0 && dataList.get(j) < index) {
                    inversionCount++;
                }
            }
            inversions += inversionCount;
            inversionCount = 0;
        }
        return inversions;
    }
}
