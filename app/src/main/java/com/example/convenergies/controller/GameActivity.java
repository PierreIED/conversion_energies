package com.example.convenergies.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.convenergies.R;
import com.example.convenergies.model.Convertisseur;
import com.example.convenergies.model.ConvertisseurBanque;
import com.example.convenergies.model.ImageEnergie;
import com.example.convenergies.model.ListeImageEnergie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class GameActivity extends AppCompatActivity {

    private Button mBoutonVerif;
    private Button mBoutonAnnul;
    private Button mBoutonRAZ;
    private EditText mDemandeEnergie;
    private int mNombreEnergie;
    private ImageView mImageViewEnergieEntrante;
    private ImageView mImageViewConvertisseur;
    private ImageView mImageViewEnergieSortante1;
    private ImageView mImageViewEnergieSortante2;
    private ImageEnergie mImageViewEnergie1;
    private ImageEnergie mImageViewEnergie2;
    private ImageEnergie mImageViewEnergie3;
    private ImageEnergie mImageViewEnergie4;
    private ImageEnergie mImageViewEnergie5;
    private List<ImageEnergie> mListeImagesEnergies;
    private TextView testEdit;
    private List<String>  mListeNomEnergies;
    private Convertisseur mCurrentConv;
    private ConvertisseurBanque mConvBank;
    private ListeImageEnergie mListeImageEnergieClass;


    private boolean mEnableTouchEvents;
    private boolean testTour;
    private List<Convertisseur> mConvLIst;
    private int mTourDeJeu;


    public static final int DIALOG_FIN_JEU=10;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initApp();







    }
    //fonctions utilitaires-mécanique du jeu
    private void nextTourDeJeu(ConvertisseurBanque convBank){
        int mNombreEnergiesRestantes=0;

        for (int i=0;i<mNombreEnergie;i++){
            ImageEnergie iterableImageEnergie=mListeImageEnergieClass.getEn(i);
            iterableImageEnergie.setSelected(false);
            if(!iterableImageEnergie.isFound())
            {iterableImageEnergie.getImgView().setVisibility(View.VISIBLE);
                mListeImageEnergieClass.setEnabledEnergieListener(iterableImageEnergie,true);
                mNombreEnergiesRestantes++;
            }

        }
        if(!(mNombreEnergiesRestantes==0))
            {


            mCurrentConv=convBank.getConv();
            displayConvertisseur(mCurrentConv);
            mTourDeJeu++;
            mImageViewEnergieEntrante.setBackgroundResource(R.drawable.border);
            mImageViewEnergieEntrante.setImageResource(R.drawable.fleche_energie);
            mImageViewEnergieEntrante.setOnTouchListener(new mToucheEnergie());
            mImageViewEnergieEntrante.setTag(-1);
            mImageViewEnergieSortante1.setImageResource(R.drawable.fleche_energie);
            mImageViewEnergieSortante1.setBackgroundResource(R.drawable.border);
            mImageViewEnergieSortante1.setOnTouchListener(new mToucheEnergie());
            mImageViewEnergieSortante1.setTag(-1);
            mImageViewEnergieSortante2.setImageResource(R.drawable.fleche_energie);
            mImageViewEnergieSortante2.setBackgroundResource(R.drawable.border);
            mImageViewEnergieSortante2.setOnTouchListener(new mToucheEnergie());
            mImageViewEnergieSortante2.setTag(-1);
            mBoutonAnnul.setEnabled(false);}
        else{finDuJeu(mTourDeJeu);}

        }





    private boolean testTourEntrante(Convertisseur mConv){
        int tag= Integer.parseInt(mImageViewEnergieEntrante.getTag().toString());
        if(tag==-1)
        {return true;}
        else {
            ImageEnergie imgEntr = mListeImageEnergieClass.getEn(tag);
            boolean test = imgEntr.getTypeEnergie() == mConv.getEnergies().get(0);
            if (test) {
                mImageViewEnergieEntrante.setBackgroundResource(R.drawable.border_true);
            } else {
                mImageViewEnergieEntrante.setBackgroundResource(R.drawable.border_false);
            }
            return test;
        }

    }
    private boolean testTourSortante(Convertisseur mConv){
        int tag1=(Integer.parseInt(mImageViewEnergieSortante1.getTag().toString()));
        int tag2=Integer.parseInt(mImageViewEnergieSortante2.getTag().toString());
        boolean testE1;
        boolean testE2;

        if(tag1==-1){testE1=true;}
        else {
            ImageEnergie imgEnSor1 = mListeImageEnergieClass.getEn(tag1);
            testE1 = (imgEnSor1.getTypeEnergie() == mConv.getEnergies().get(1) || imgEnSor1.getTypeEnergie() == mConv.getEnergies().get(2));
            if (testE1) {
                mImageViewEnergieSortante1.setBackgroundResource(R.drawable.border_true);
            } else {
                mImageViewEnergieSortante1.setBackgroundResource(R.drawable.border_false);

            }
        }
        if(tag2==-1){testE2=true;}
        else {
            ImageEnergie imgEnSor2 = mListeImageEnergieClass.getEn(tag2);
            if(tag1!=-1){
            testE2 = (imgEnSor2.getTypeEnergie() == mConv.getEnergies().get(1) || imgEnSor2.getTypeEnergie() == mConv.getEnergies().get(2)) && imgEnSor2.getTypeEnergie() != mListeImageEnergieClass.getEn(tag1).getTypeEnergie();}
            else{testE2=(imgEnSor2.getTypeEnergie() == mConv.getEnergies().get(1) || imgEnSor2.getTypeEnergie() == mConv.getEnergies().get(2));}

            if (testE2) {
                mImageViewEnergieSortante2.setBackgroundResource(R.drawable.border_true);

            } else {
                mImageViewEnergieSortante2.setBackgroundResource(R.drawable.border_false);
            }
        }
        return testE1 && testE2;
    }

    //seulement pour la version tablette : le dragevent n'est pas compatible avec la liste déroulante horizontale sur les petits écrans.
    class MyDragListener implements View.OnDragListener {


        @Override
        public boolean onDrag(View v, DragEvent event) {



            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    break;
                case DragEvent.ACTION_DROP:

                    if(isValidDragEvent(event.getClipData())){
                        int action = event.getAction();
                        ClipData data=event.getClipData();
                        ClipData.Item item=data.getItemAt(0);
                        int tag=Integer.parseInt((String) item.getText());
                        ImageEnergie mImageDeDepart=mListeImageEnergieClass.getEn(tag-1);

                        setImageEnergie((ImageView) v,mListeImageEnergieClass.getEn(tag-1).getTypeEnergie());
                        v.setTag(tag);
                        v.setOnDragListener(null);
                        mImageDeDepart.getImgView().setVisibility(View.INVISIBLE);
                        return true;
                    }
                    // Dropped, reassign View to ViewGroup


                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    ;

            }

        return true;}
    }

    private boolean isValidDragEvent(ClipData data) {
        return  !(data == null || data.getItemCount() == 0);


    }

    //fonctions d'initialisations et d'affichage
    private void initApp(){
        mBoutonVerif=(Button) findViewById(R.id.boutonVerif);
        mBoutonAnnul=(Button) findViewById(R.id.boutonAnnul);
        mBoutonAnnul.setEnabled(false);
        mBoutonRAZ=(Button) findViewById(R.id.boutonRAZ);
        mDemandeEnergie=(EditText)  findViewById(R.id.nbEnDem);
        mDemandeEnergie.setVisibility(View.INVISIBLE);
        testEdit=(TextView) findViewById(R.id.nbEn);
        testEdit.setVisibility(View.INVISIBLE);
        mImageViewEnergieEntrante=(ImageView) findViewById(R.id.eEntrante);
        mImageViewConvertisseur=(ImageView) findViewById(R.id.conv);
        mImageViewEnergieSortante1=(ImageView) findViewById(R.id.eSortante1);
        mImageViewEnergieSortante2=(ImageView) findViewById(R.id.eSortante2);
        mImageViewEnergie1=new ImageEnergie((ImageView) findViewById(R.id.e1),0);
        mImageViewEnergie2=new ImageEnergie((ImageView) findViewById(R.id.e2),1);
        mImageViewEnergie3=new ImageEnergie((ImageView) findViewById(R.id.e3),2);
        mImageViewEnergie4=new ImageEnergie((ImageView) findViewById(R.id.e4),3);
        mImageViewEnergie5=new ImageEnergie((ImageView) findViewById(R.id.e5),4);
        //version tablette
        //mImageViewEnergieEntrante.setOnDragListener(new MyDragListener());
        //version téléphone
        mImageViewEnergieEntrante.setOnTouchListener(new mToucheEnergie());
        mImageViewEnergieSortante1.setOnTouchListener(new mToucheEnergie());
        mImageViewEnergieSortante2.setOnTouchListener(new mToucheEnergie());
        mImageViewEnergieSortante2.setBackgroundResource(R.drawable.border);
        mImageViewEnergieSortante1.setBackgroundResource(R.drawable.border);
        mImageViewEnergieEntrante.setBackgroundResource(R.drawable.border);
        mImageViewEnergieEntrante.setImageResource(R.drawable.fleche_energie);
        mImageViewEnergieSortante1.setImageResource(R.drawable.fleche_energie);
        mImageViewEnergieSortante2.setImageResource(R.drawable.fleche_energie);

        mImageViewEnergieEntrante.setTag(-1);
        mImageViewEnergieSortante1.setTag(-1);
        mImageViewEnergieSortante2.setTag(-1);
        testEdit=(TextView)findViewById(R.id.consigne);
        mListeImagesEnergies=Arrays.asList(mImageViewEnergie1,mImageViewEnergie2,mImageViewEnergie3,mImageViewEnergie4,mImageViewEnergie5);


        mTourDeJeu=1;
        mNombreEnergie=5;
        mListeNomEnergies= Arrays.asList("","mécanique","électrique","chimique","lumineuse","thermique");


        for(int i=0;i<mNombreEnergie;i++) {
            Random rand = new Random();
            int j = rand.nextInt(5) + 1;
            mListeImagesEnergies.get(i).setFound(false);
            mListeImagesEnergies.get(i).getImgView().setVisibility(View.VISIBLE);
            setImageEnergie(mListeImagesEnergies.get(i).getImgView(),j);
            mListeImagesEnergies.get(i).setTypeEnergie(j);


        }

        mListeImageEnergieClass= new ListeImageEnergie(mListeImagesEnergies);


        mConvBank= new ConvertisseurBanque(setConvList());


        mCurrentConv=mConvBank.getConv() ;




        displayConvertisseur(mCurrentConv);
        mBoutonVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEnableTouchEvents = false;
                boolean testTour1=testTourEntrante(mCurrentConv);
                boolean testTour2=testTourSortante(mCurrentConv);
                testTour=(testTour1 && testTour2);


                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mEnableTouchEvents = true;
                        ;

                        if(testTour)
                        {removeEnergie(mImageViewEnergieSortante2);
                            removeEnergie(mImageViewEnergieSortante1);
                            removeEnergie(mImageViewEnergieEntrante);

                        }


                        nextTourDeJeu(mConvBank);
                    }
                }, 2000); // LENGTH_SHORT is usually 2 second long





                }



        });
        mBoutonRAZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initApp();
            }
        });

        mBoutonAnnul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=0;i<mNombreEnergie;i++){
                    ImageEnergie iterableImageEnergie=mListeImageEnergieClass.getEn(i);
                    iterableImageEnergie.setSelected(false);
                    iterableImageEnergie.getImgView().setBackgroundResource(R.drawable.border_none);
                    if(!iterableImageEnergie.isFound())
                    {iterableImageEnergie.getImgView().setVisibility(View.VISIBLE);
                    mListeImageEnergieClass.setEnabledEnergieListener(iterableImageEnergie,true);
                    }
                }
                mImageViewEnergieEntrante.setBackgroundResource(R.drawable.border);
                mImageViewEnergieEntrante.setImageResource(R.drawable.fleche_energie);
                mImageViewEnergieEntrante.setOnTouchListener(new mToucheEnergie());
                mImageViewEnergieSortante1.setImageResource(R.drawable.fleche_energie);
                mImageViewEnergieSortante1.setBackgroundResource(R.drawable.border);
                mImageViewEnergieSortante1.setOnTouchListener(new mToucheEnergie());
                mImageViewEnergieSortante2.setImageResource(R.drawable.fleche_energie);
                mImageViewEnergieSortante2.setBackgroundResource(R.drawable.border);
                mImageViewEnergieSortante2.setOnTouchListener(new mToucheEnergie());
                mBoutonAnnul.setEnabled(false);
                mImageViewEnergieEntrante.setTag(-1);
                mImageViewEnergieSortante1.setTag(-1);
                mImageViewEnergieSortante2.setTag(-1);

            }
        });
    }

    private void removeEnergie(ImageView imgView){
        if (Integer.parseInt(imgView.getTag().toString())!=-1){

            mListeImageEnergieClass.getEn(Integer.parseInt(imgView.getTag().toString())).setFound(true);

        }
    }

    public final class mToucheEnergie implements View.OnTouchListener {


        public boolean onTouch(View v, MotionEvent event){
            ImageEnergie imageSelec;


            if(mListeImageEnergieClass.isEnergieSelected()==-1)
            {return false;}

            else {
                int idxEn=mListeImageEnergieClass.isEnergieSelected();
                mBoutonAnnul.setEnabled(true);
                mListeImageEnergieClass.getEn(idxEn);
                setImageEnergie((ImageView) v,mListeImageEnergieClass.getEn(idxEn).getTypeEnergie());
                v.setTag( idxEn);
                mListeImageEnergieClass.getEn(idxEn).setSelected(false);
                v.setOnTouchListener(null);
                mListeImageEnergieClass.getEn(idxEn).getImgView().setVisibility(View.INVISIBLE);
                return true;
            }



        }



    }



    private void displayConvertisseur(Convertisseur conv){
        mImageViewConvertisseur.setImageResource(conv.getRefImage());

    }
    private void setImageEnergie(ImageView imgView, int typeEnergie){
        imgView.setBackgroundResource(R.drawable.border_none);
        switch (typeEnergie) {

            case 0:
                imgView.setVisibility(View.GONE);
                break;


            case 1:
                imgView.setImageResource(R.drawable.mecanique);
                break;
            case 2:
                imgView.setImageResource(R.drawable.electrique);
                break;
            case 3:
                imgView.setImageResource(R.drawable.chimique);
                break;
            case 4:
                imgView.setImageResource(R.drawable.lumineuse);
                break;
            case 5:
                imgView.setImageResource(R.drawable.thermique);
                break;

        }
    }

    private void finDuJeu(int tourJeu){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setTitle("Bravo")
                .setMessage("Votre score est de : "+ tourJeu+" Voulez-vous refaire une partie ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        initApp();

                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();




    }

    //données
    private List<Convertisseur> setConvList(){
        List<Convertisseur> theList;

        theList=Arrays.asList(
                new Convertisseur("ampoule","Ampoule",R.drawable.ampoule,Arrays.asList(2,4,5)),
                new Convertisseur("barrage","Barrage",R.drawable.barrage,Arrays.asList(1,2,5)),
                new Convertisseur("bateauAMoteur","Bateau à moteur",R.drawable.bateau_a_moteur,Arrays.asList(3,1,5)),
                new Convertisseur("batterie","Batterie",R.drawable.batterie,Arrays.asList(3,2,5)),
                new Convertisseur("braceletLumineux","Bracelet lumineux",R.drawable.bracelet_lumineux,Arrays.asList(3,4,0)),
                new Convertisseur("centraleThermique","Centrale thermique",R.drawable.centrale_thermique,Arrays.asList(3,2,5)),
                new Convertisseur("cheminee","Cheminée",R.drawable.cheminee,Arrays.asList(3,4,5)),
                new Convertisseur("coureur","Coureur",R.drawable.coureur,Arrays.asList(3,1,5)),
                new Convertisseur("eolienne","Eolienne",R.drawable.eolienne,Arrays.asList(1,2,5)),
                new Convertisseur("led","LED",R.drawable.lediod,Arrays.asList(2,4,0)),
                new Convertisseur("machine_a_laver","Machine à laver",R.drawable.machine_a_laver,Arrays.asList(2,1,5)),
                new Convertisseur("oiseau","Oiseau",R.drawable.oiseau,Arrays.asList(3,1,5)),
                new Convertisseur("ordinateur","Ordinateur",R.drawable.ordinateur,Arrays.asList(2,4,5)),
                new Convertisseur("panneau solaire","Panneau Solaire Photovoltaïque",R.drawable.panneau_solaire,Arrays.asList(4,2,0)),
                new Convertisseur("pile","Pile",R.drawable.pile,Arrays.asList(3,2,5)),
                new Convertisseur("plante","Plante",R.drawable.plante,Arrays.asList(4,3,0)),
                new Convertisseur("seche_cheveux","Sèche cheveux",R.drawable.seche_cheveux,Arrays.asList(2,1,5)),
                new Convertisseur("telephone","Téléphone",R.drawable.telephone,Arrays.asList(2,4,5)),
                new Convertisseur("tracteur","Tracteur",R.drawable.tracteur,Arrays.asList(3,1,5)),
                new Convertisseur("ver_luisant","Ver luisant",R.drawable.ver_luisant,Arrays.asList(3,4,0)),
                new Convertisseur("voilier","Pile",R.drawable.voilier,Arrays.asList(1,1,0)),
                new Convertisseur("voiture","Voiture",R.drawable.voiture,Arrays.asList(3,1,5))

        );
        return(theList);


    }



    }











