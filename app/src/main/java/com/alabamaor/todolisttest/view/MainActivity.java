package com.alabamaor.todolisttest.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alabamaor.todolisttest.R;
import com.alabamaor.todolisttest.model.ToDoListModel;
import com.alabamaor.todolisttest.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ToDoListAdapter.ToDoListItem {

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
        toDoListAdapter.setListItemListener(this::onItemSelected);
        recyclerView.setAdapter(toDoListAdapter);


        addButton.setOnClickListener(v -> {

            if (!(itemEditText.getText().toString().isEmpty() || itemEditText.getText() == null)) {
                mainViewModel.addItemToList(new ToDoListModel(itemEditText.getText().toString()));
                itemEditText.setText("");
            }

        });

        observeViewModel();
    }


    private void observeViewModel() {

        mainViewModel.mList.observe(this, list -> {
            if (list != null) {
                toDoListAdapter.update(list);
            }
        });
        mainViewModel.mItemsRemain.observe(this, count -> {
            if (count != null) {
                if (count == 0) {
                    itemCountTextView.setText(getString(R.string.no_items_in_the_list));
                } else if (count == 1) {
                    itemCountTextView.setText(getString(R.string.one_item_in_the_list));
                } else {
                    itemCountTextView.setText(count + " " + getString(R.string.items_in_the_list));
                }
            }
        });
    }

    @Override
    public void onItemSelected(View v, int position) {

        int c = mainViewModel.mItemsRemain.getValue();
        List<ToDoListModel> list = mainViewModel.mList.getValue();
        list.get(position).setComplete(!list.get(position).isComplete());
        if (list.get(position).isComplete())
            c--;
        else c++;
        mainViewModel.mItemsRemain.setValue(c);
        mainViewModel.mList.setValue(list);
    }


    @Override
    protected void onStop() {
        super.onStop();
        toDoListAdapter.setListItemListener(null);
    }
}
