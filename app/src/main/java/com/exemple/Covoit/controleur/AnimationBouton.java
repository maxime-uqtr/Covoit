package com.exemple.Covoit.controleur;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public interface AnimationBouton {

    static boolean rotateFab(View v, boolean rotate){
        v.animate().setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        }).rotation(rotate?135f:0f);

        return rotate;
    }

    static void show(View v){ //Rend visible
        v.setVisibility(View.VISIBLE);
        v.setAlpha(0f); //Cache au debut de l'animation
        v.setTranslationY(v.getHeight()); //Tout en bas dans le bouton avant
        v.animate().setDuration(250).translationY(0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        }).alpha(1f).start(); //permet le fondu
        v.setClickable(true);
    }

    static void hide(View v){ //Cache
        v.setVisibility(View.VISIBLE);
        v.setAlpha(1f);
        v.setTranslationY(0);
        v.animate().setDuration(250).translationY(v.getHeight()).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        }).alpha(0f).start(); //permet le fondu
        v.setClickable(false);
    }

    static void init(View v){
        v.setVisibility(View.GONE);
        v.setTranslationY(v.getHeight());
        v.setAlpha(0f);
    }
}
