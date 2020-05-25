package com.alabamaor.todolisttest.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alabamaor.todolisttest.model.ToDoListModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    public MutableLiveData<List<ToDoListModel>> mList = new MutableLiveData<>();
    public MutableLiveData<Integer> mItemsRemain = new MutableLiveData<>();

    public void addItemToList(ToDoListModel item) {
        List<ToDoListModel> list = mList.getValue();

        if (list == null) {
            list = new ArrayList<>();
        }
        if (mItemsRemain.getValue() != null) {
            mItemsRemain.setValue(mItemsRemain.getValue() + 1);
        } else {
            mItemsRemain.setValue(1);
        }
        list.add(item);
        mList.setValue(list);
    }

}

