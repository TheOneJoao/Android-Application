package com.outspin.app.ui.radar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RadarViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RadarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Radar fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}