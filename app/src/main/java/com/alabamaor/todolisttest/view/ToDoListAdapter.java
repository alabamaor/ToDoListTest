package com.alabamaor.todolisttest.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alabamaor.todolisttest.R;
import com.alabamaor.todolisttest.model.ToDoListModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ListViewHolder> {

    private List<ToDoListModel> toDoList;


    public ToDoListAdapter(List<ToDoListModel> toDoList) {
        this.toDoList = toDoList;
    }

    public void update(List<ToDoListModel> newToDoList) {
        this.toDoList.clear();
        this.toDoList.addAll(newToDoList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from
                (parent.getContext()).inflate(
                R.layout.list_tile, parent,
                false);


        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bind(toDoList.get(position));
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }



    class ListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.listTileCheckBox)
        CheckBox item;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ToDoListModel toDoListModel) {
            item.setText(toDoListModel.getItem());
            item.setChecked(toDoListModel.isComplete());
        }
    }


}

