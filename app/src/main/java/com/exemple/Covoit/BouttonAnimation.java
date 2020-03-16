package com.exemple.Covoit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class BouttonAnimation {

    public static boolean rotateFab(View v, boolean rotate){
        v.animate().setDuration(1000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        }).rotation(rotate?135f:0f);

        return rotate;
    }

    public static void show(View v){ //Rend visible
        v.setVisibility(View.VISIBLE);
        v.setAlpha(0f); //Cache au debut de l'animation
        v.setTranslationY(v.getHeight()); //Tout en bas dans le bouton avant
        v.animate().setDuration(1000).translationY(0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        }).alpha(1f).start(); //permet le fondu
    }

    public  static void hide(View v){ //Cache
        v.setVisibility(View.VISIBLE);
        v.setAlpha(1f);
        v.setTranslationY(0);
        v.animate().setDuration(1000).translationY(v.getHeight()).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        }).alpha(0f).start(); //permet le fondu
    }

    public static void init(View v){
        v.setVisibility(View.GONE);
        v.setTranslationY(v.getHeight());
        v.setAlpha(0f);
    }
}
