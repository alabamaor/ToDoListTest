package com.alabamaor.todolisttest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alabamaor.todolisttest.R;
import com.alabamaor.todolisttest.model.ToDoListModel;
import com.alabamaor.todolisttest.viewmodel.MainViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.itemCountTextView)
    TextView itemCountTextView;

    @BindView(R.id.addButton)
    Button addButton;

    @BindView(R.id.itemEditText)
    EditText itemEditText;

    MainViewModel mainViewModel;
    ToDoListAdapter toDoListAdapter = new ToDoListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(toDoListAdapter);

        addButton.setOnClickListener(v -> {

            if (!(  itemEditText.getText().toString().isEmpty() || itemEditText.getText() == null )){
                mainViewModel.addItemToList(new ToDoListModel(itemEditText.getText().toString(), false));
                itemEditText.setText("");
            }

        });

        observeViewModel();
    }


    private void observeViewModel() {

        mainViewModel.mList.observe(this, list -> {
            if (list != null) {
               toDoListAdapter.update(list);

               if (list.size() == 0){
                   itemCountTextView.setText(getString(R.string.no_items_in_the_list));
               }
               else if (list.size() == 1){
                   itemCountTextView.setText(getString(R.string.one_item_in_the_list));
               }
               else{
                   itemCountTextView.setText(list.size() + " " + getString(R.string.items_in_the_list));
               }

            }
        });
    }
}
