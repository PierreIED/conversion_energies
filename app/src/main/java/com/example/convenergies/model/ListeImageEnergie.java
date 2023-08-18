package com.example.convenergies.model;
import android.view.MotionEvent;
import android.view.View;
import com.example.convenergies.R;
import java.util.List;


public class ListeImageEnergie {
    private List<ImageEnergie> mListeImageEnergies;

    public ListeImageEnergie(List<ImageEnergie> imgList) {
        mListeImageEnergies = imgList;

        for(int i=0; i<imgList.size(); i++)
        {
            imgList.get(i).getImgView().setOnTouchListener(new ListeImageEnergie.selectEnergieListener());
        }
    }

    public void unselectAllEnergies(){
        for(int i=0; i<mListeImageEnergies.size();i++)
        {mListeImageEnergies.get(i).setSelected(false);
        mListeImageEnergies.get(i).getImgView().setBackgroundResource(R.drawable.border_none);

        if(!mListeImageEnergies.get(i).isFound()){setEnabledEnergieListener(mListeImageEnergies.get(i),true);

        }

        }
    }

    public void setEnabledEnergieListener(ImageEnergie img, boolean clicable){
        if (clicable){
            img.getImgView().setOnTouchListener(new ListeImageEnergie.selectEnergieListener());
        }
        else{
            img.getImgView().setOnTouchListener(null);
        }
    }



    public final class selectEnergieListener implements View.OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {


            unselectAllEnergies();
            mListeImageEnergies.get(Integer.parseInt(v.getTag().toString())).setSelected(true);
            v.setBackgroundResource(R.drawable.border_selected);
            v.setOnTouchListener(null);
            return true;
        }

    }

    public ImageEnergie getEn(int i){
        return (mListeImageEnergies.get(i));
    }

    public int getSize(){
        return mListeImageEnergies.size();
    }

    public int isEnergieSelected(){
        for(int i=0;i<this.getSize();i++){
            if (this.getEn(i).isSelected()) {return this.getEn(i).getMTag(); }
        }

        return -1;
    }



}
