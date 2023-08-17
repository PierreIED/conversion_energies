package com.example.convenergies.model;




import java.util.List;

public class Convertisseur {





    private String mName;
    private String mLegende;


    private int mRefImage;
    private List<Integer> mEnergies;

    public Convertisseur (String name, String legende, int refImage, List<Integer> energies){
        this.setName(name);
        this.setLegende(legende);
        this.setRefImage(refImage);
        this.setEnergies(energies);
        }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getLegende() {
        return mLegende;
    }

    public void setLegende(String mLegende) {
        this.mLegende = mLegende;
    }





    public List<Integer> getEnergies() {
        return mEnergies;
    }

    public void setEnergies(List<Integer> mEnergies) {
        this.mEnergies = mEnergies;
    }


    public int getRefImage() {
        return mRefImage;
    }

    public void setRefImage(int mRefImage) {
        this.mRefImage = mRefImage;
    }


}
