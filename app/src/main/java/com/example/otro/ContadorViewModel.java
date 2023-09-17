package com.example.otro;

import android.view.View;
import androidx.lifecycle.LiveData;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContadorViewModel extends ViewModel {

    private final MutableLiveData<Integer> contador = new MutableLiveData<>();

    public MutableLiveData<Integer> getContador() {
        return contador;
    }

    public void resetContador() {
        contador.postValue(0);
    }

}


