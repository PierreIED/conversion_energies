package com.example.convenergies.model;

import java.util.Collections;
import java.util.List;

public class ConvertisseurBanque {
    private List<Convertisseur> mConvList;
    private int mNextConvIndex;

    public ConvertisseurBanque(List<Convertisseur> convList) {
        mConvList = convList;

        // Shuffle the question list
        Collections.shuffle(mConvList);

        mNextConvIndex = 0;
    }

    public Convertisseur getConv() {

        if (mNextConvIndex == mConvList.size()) {
            mNextConvIndex = 0;
        }

        // Please note the post-incrementation
        return mConvList.get(mNextConvIndex++);
    }

}
