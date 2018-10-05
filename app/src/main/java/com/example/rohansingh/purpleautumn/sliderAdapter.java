package com.example.rohansingh.purpleautumn;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class sliderAdapter extends PagerAdapter {

    Context context ;
    LayoutInflater layoutInflater;

    public sliderAdapter(Context context){
        this.context=context;
    }

    int[] images={
            R.drawable.slide,
            R.drawable.slideseven,
            R.drawable.slideten
    };

    String[] Quotes={"REGISTER AND HELP","FIND THE NEAREST DONOR","SAVE LIVES"};
    @Override
    public int getCount() {
        return images.length ;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

     layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
     View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        CircleImageView image = (CircleImageView)view.findViewById(R.id.imageicon);
        TextView text= (TextView)view.findViewById(R.id.textIcon);

        image.setImageResource(images[position]);
        text.setText(Quotes[position]);
        container.addView(view);
        return view;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(ConstraintLayout)object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
