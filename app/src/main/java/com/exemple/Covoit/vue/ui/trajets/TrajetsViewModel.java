package com.exemple.Covoit.vue.ui.trajets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrajetsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TrajetsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}