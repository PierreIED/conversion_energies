package com.example.convenergies.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.drm.DrmManagerClient;
import android.media.MediaDrm;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.convenergies.R;
import com.example.convenergies.model.User;


public class MainActivity extends AppCompatActivity {
    private Button mBoutonJeu;
    private Button mBoutonScore;
    private TextView mTextePresentation;
    private EditText mChoixNomJoueur;
    private User mUser;
    private int mNbCarte=5;
    private int mBestScore;
    public static final int GAME_ACTIVITY_REQUEST_CODE=42;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBoutonJeu=(Button) findViewById(R.id.bouton_jeu);
        mBoutonJeu.setEnabled(false);
        mBoutonScore=(Button)  findViewById(R.id.bouton_meilleurs_scores);
        mBoutonScore.setVisibility(View.INVISIBLE);
        mTextePresentation=(TextView)findViewById(R.id.welcome_text);
        mChoixNomJoueur= (EditText) findViewById(R.id.choix_nom);
        mUser= new User();

        mChoixNomJoueur.addTextChangedListener(new TextWatcher() {
                                                   @Override
                                                   public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                   }

                                                   @Override
                                                   public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                       mBoutonJeu.setEnabled(true);

                                                   }

                                                   @Override
                                                   public void afterTextChanged(Editable s) {

                                                   }
                                               });



        mBoutonJeu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUser.setFirstname(mChoixNomJoueur.getText().toString());

                // User clicked the button
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);


            }
        });








    }

}
