package myfragmentlazyload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Administrator on 2016/11/1.
 * 自定义的ViewPagerFragment，防止在setUserVisibleHint()对控件进行操作，报null异常
 * 实现fragment的懒加载
 */
public abstract class ViewPagerFragment extends Fragment{
    /**
     * 是否懒加载
     */
    private LazyLoadListener lazyLoadListener;
    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发，判断view是否初始化完成
     */
    private boolean hasCreateView;
    /**
     * 判读Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发，即防止预加载
     */
    private boolean isFragmentVisible;
    /**
     * onCreateView()里返回的view，修饰为protected,所以子类继承该类时，在onCreateView里必须对该变量进行初始化
     */
    protected View rootView;

    /**
     * 当切换到当前的fragment时isVisibleToUser为true，
     * 当切走到其他的fragment时isVisibleToUser为false。
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (rootView==null){
            return;
        }
        hasCreateView=true;//标志view记在完成
        if (isVisibleToUser){
            onFragmentVisibleChange(true);
            isFragmentVisible=true;
            return;
        }
        if (isFragmentVisible){
            onFragmentVisibleChange(false);
            isFragmentVisible=false;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    /**
     * onCreateView()后调用，
     * @param view 为onCreateView()的返回值view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView=view;
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
    }

    /**
     * 让hasCreateView和isFragmentVisible设置为默认值false
     */
    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }
    /**************************************************************
     *  自定义的回调方法，子类可根据需求重写
     *************************************************************/

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
       if (isVisible){
        lazyLoadListener.canLoad();
       }else {
        lazyLoadListener.canNotLoad();
       }
    }
}
