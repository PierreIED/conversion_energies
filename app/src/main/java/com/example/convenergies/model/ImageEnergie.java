package com.example.convenergies.model;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.convenergies.R;

import java.util.List;

public class ImageEnergie  {


    private ImageView mImgView;
    private int mTag;
    private boolean isSelected;
    private boolean isFound;




    private int mTypeEnergie;


    public ImageEnergie(ImageView imgView, int tag){
       this.setImgView(imgView);
       this.setmTag(tag);
       this.isFound=false;
       this.setTypeEnergie(-1);
        //version tablette
       //this.getImgView().setOnTouchListener(new MyTouchListener());

        //version tel
        this.isSelected= false;

            }


    public final class selectEnergieListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            isSelected=true;
            v.setBackgroundResource(R.drawable.border_selected);
            v.setOnTouchListener(null);
            return true;
        }
    }
    public ImageView getImgView() {
        return mImgView;
    }

    public void setImgView(ImageView mImgView) {
        this.mImgView = mImgView;
    }

    public int getmTag() {
        return mTag;
    }

    public void setmTag(int mTag) {
        this.mTag = mTag;
    }

    public boolean isFound() {
        return isFound;
    }

    public void setFound(boolean found) {
        isFound = found;
    }
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getTypeEnergie() {
        return mTypeEnergie;
    }

    public void setTypeEnergie(int mTypeEnergie) {
        this.mTypeEnergie = mTypeEnergie;
    }







    }


