package com.alabamaor.todolisttest.model;

public class ToDoListModel {

    String item;
    Boolean isComplete;

    public ToDoListModel(String item) {
        this.item = item;
        this.isComplete = false;
    }

    public String getItem() {
        return item;
    }

    public ToDoListModel setItem(String item) {
        this.item = item;
        return this;
    }

    public Boolean isComplete() {
        return isComplete;
    }

    public ToDoListModel setComplete(Boolean complete) {
        isComplete = complete;
        return this;
    }
}
