package com.example.convenergies.model;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.convenergies.R;

public class ImageEnergie  {


    private ImageView mImgView;
    private int mTag;
    private boolean isSelected;
    private boolean isFound;
    private int mTypeEnergie;


    public ImageEnergie(ImageView imgView, int tag){
        this.setImgView(imgView);
        this.setMTag(tag);
        this.isFound=false;
        this.setTypeEnergie(-1);
        this.isSelected= false;

            }

    // getters and setters :
    public ImageView getImgView() {
        return mImgView;
    }

    public void setImgView(ImageView mImgView) {
        this.mImgView = mImgView;
    }

    public int getMTag() {
        return mTag;
    }

    public void setMTag(int mTag) {
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


