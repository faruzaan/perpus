package com.example.perpus.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.perpus.R;

import java.util.ArrayList;

public class AdapterSlide extends PagerAdapter {
    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public AdapterSlide(Context context,ArrayList<Integer> images)
    {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
    @Override
    public void destroyItem(ViewGroup container,int position,Object object)
    {
        container.removeView((View) object);
    }
    @Override
    public Object instantiateItem(ViewGroup view,int postion)
    {
        View myImageLayout = inflater.inflate(R.layout.slide_image, view, false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);
        myImage.setImageResource(images.get(postion));
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }
}
