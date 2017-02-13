package me.shihao.library;

/**
 * Created by shihao <1406504841@qq.com> on 2017/2/13.16:06
 */

public class RecyclerItemCallback<T, V> {
    /**
     * 单击事件
     *
     * @param position 位置
     * @param model    实体
     * @param holder   控件
     */
    public void onItemClick(int position, T model, V holder) {
    }

    /**
     * 长按事件
     *
     * @param position 位置
     * @param model    实体
     * @param holder   控件
     */
    public void onItemLongClick(int position, T model, V holder) {
    }

    public void onItemViewClick(int viewType, int position, T model, V holder) {

    }

    public void onItemViewLongClick(int viewType, int position, T model, V holder) {

    }
}
