package com.byl.recyclerview.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.byl.recyclerview.R;
import com.byl.recyclerview.adapter.PhotoPagerAdapter;
import java.util.ArrayList;


public class PhotoPreviewActivity extends Activity implements PhotoPagerAdapter.PhotoViewClickListener{

    public static final String EXTRA_PHOTOS = "extra_photos";
    public static final String EXTRA_CURRENT_ITEM = "extra_current_item";

    /** 选择结果，返回为 ArrayList&lt;String&gt; 图片路径集合  */
    public static final String EXTRA_RESULT = "preview_result";

    /** 预览请求状态码 */
    public static final int REQUEST_PREVIEW = 99;

    private ArrayList<String> paths;
    private ViewPager mViewPager;
    private PhotoPagerAdapter mPagerAdapter;
    private int currentItem = 0;
    
    private TextView index,info;
    private ArrayList<String> pathArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_preview);
        
        mViewPager = (ViewPager) findViewById(R.id.vp_photos);
        index= (TextView) findViewById(R.id.index);
        info= (TextView) findViewById(R.id.info);

        paths = new ArrayList<String>();
        pathArr =  getIntent().getStringArrayListExtra(EXTRA_RESULT);

        if(pathArr != null){
        	paths.addAll(pathArr);
        }

        currentItem = getIntent().getIntExtra(EXTRA_CURRENT_ITEM, 0);

        mPagerAdapter = new PhotoPagerAdapter(this, paths);
        mPagerAdapter.setPhotoViewClickListener(this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(currentItem);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                updateTitle();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        updateTitle();
    }
    

    @Override
    public void OnPhotoTapListener(View view, float v, float v1) {
        onBackPressed();
    }
    
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_RESULT, paths);
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }

    public void updateTitle() {
    	currentItem = mViewPager.getCurrentItem();
    	index.setText( (currentItem + 1) + "/" +  paths.size());
    }


}
