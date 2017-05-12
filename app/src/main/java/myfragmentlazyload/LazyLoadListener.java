package myfragmentlazyload;

/**
 * Created by Administrator on 2016/11/1.
 * 是否懒加载的监听
 */
public interface LazyLoadListener {
    void canLoad();
    void canNotLoad();
}
