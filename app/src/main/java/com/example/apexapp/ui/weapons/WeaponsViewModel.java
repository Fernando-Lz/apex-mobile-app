package com.example.apexapp.ui.weapons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WeaponsViewModel extends ViewModel{

    private final MutableLiveData<String> mText;

    public WeaponsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is weapons fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
