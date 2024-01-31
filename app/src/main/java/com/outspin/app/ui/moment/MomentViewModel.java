package com.outspin.app.ui.moment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MomentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MomentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Story fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}