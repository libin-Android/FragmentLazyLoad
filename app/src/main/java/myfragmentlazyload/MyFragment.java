package myfragmentlazyload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *让自定义的fragment集成viewPagerFragment，处理页面可见和不可见的逻辑
 */
public class MyFragment extends ViewPagerFragment implements LazyLoadListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 当页面可见的操作
     */
    @Override
    public void canLoad() {

    }

    /**
     * 当页面不可见的操作
     */
    @Override
    public void canNotLoad() {

    }
}
