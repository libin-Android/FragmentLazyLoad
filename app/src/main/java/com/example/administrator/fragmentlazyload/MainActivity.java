package com.example.administrator.fragmentlazyload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * ViewPager + Fragment 的配合使用
 * fragment的懒加载
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
