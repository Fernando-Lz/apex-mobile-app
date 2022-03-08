package com.example.apexapp.ui.legends;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LegendsViewModel extends ViewModel{

    private final MutableLiveData<String> mText;

    public LegendsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is legends fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
