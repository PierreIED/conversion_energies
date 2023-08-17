package com.example.convenergies.model;


public class User {
    private String mFirstname;

    public int getmBestScore() {
        return mBestScore;
    }

    public void setmBestScore(int mBestScore) {
        this.mBestScore = mBestScore;
    }

    private int mBestScore;

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }


    @Override
    public String toString() {
        return "User{" +
                "mFirstname='" + mFirstname + '\'' +
                "mBestScore='"+mBestScore+ '\'' +
                '}';
    }
}
