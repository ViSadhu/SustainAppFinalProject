package com.example.vihang.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vihang.Guide;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Guide> selectedItem;

    public SharedViewModel() {
        selectedItem = new MutableLiveData<>();
    }

    public MutableLiveData<Guide> getSelectedItem() {
        return selectedItem;
    }

    public void selectedItem(Guide item) {
        selectedItem.setValue(item);
    }
}
