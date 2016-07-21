package com.byl.recyclerview.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.byl.recyclerview.R;
import com.byl.recyclerview.view.TouchImageView;

import net.tsz.afinal.FinalBitmap;

import java.util.ArrayList;
import java.util.List;



public class PhotoPagerAdapter extends PagerAdapter {

  public interface PhotoViewClickListener{
    void OnPhotoTapListener(View view, float v, float v1);
  }

  public PhotoViewClickListener listener;

  private List<String> paths = new ArrayList<String>();
  private Context mContext;
  private LayoutInflater mLayoutInflater;
  FinalBitmap fb = null;


  public PhotoPagerAdapter(Context mContext, List<String> paths) {
    this.mContext = mContext;
    this.paths = paths;
    mLayoutInflater = LayoutInflater.from(mContext);
    fb = FinalBitmap.create(mContext);
  }

  public void setPhotoViewClickListener(PhotoViewClickListener listener){
    this.listener = listener;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {

    View itemView = mLayoutInflater.inflate(R.layout.item_preview, container, false);

    TouchImageView imageView = (TouchImageView) itemView.findViewById(R.id.iv_pager);

    final String path = paths.get(position);
    
    fb.display(imageView, path);

    container.addView(itemView);

    return itemView;
  }


  @Override public int getCount() {
    return paths.size();
  }


  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }


  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }

  @Override
  public int getItemPosition (Object object) { return POSITION_NONE; }

}
