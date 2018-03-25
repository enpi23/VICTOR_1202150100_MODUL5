package enpi23.modul5.prak.andro.victor_1202150100_modul5;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import enpi23.modul5.prak.andro.victor_1202150100_modul5.Helper.DataBaseHelper;
import enpi23.modul5.prak.andro.victor_1202150100_modul5.Helper.SwipeHelper;
import enpi23.modul5.prak.andro.victor_1202150100_modul5.Todo.TodoListAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    DataBaseHelper dbHelper;
    TodoListAdapter adapter;
    String filter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        populaterecyclerView(filter);

        SwipeHelper swipeHelper = new SwipeHelper(MainActivity.this, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        swipeHelper.setLeftBackgroundColor(R.color.colorAccent);
        swipeHelper.setRightBackgroundColor(R.color.colorPrimary);
        swipeHelper.setLeftImg(R.drawable.ic_launcher_background);
        swipeHelper.setRightImg(R.drawable.ic_launcher_background);
        swipeHelper.setSwipetoDismissCallBack(getCallback(adapter));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeHelper);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Menambahkan opsi setting pada action bar
        getMenuInflater().inflate(R.menu.setting_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.activity_setting_color);
            dialog.setTitle("Change");
            dialog.setCancelable(true);
            //Setting inilialisasi RadioButton

            //Setting kondisi dari RadioButton
            final RadioButton rdRed = dialog.findViewById(R.id.RadioRed);
            final RadioButton rdPurple = dialog.findViewById(R.id.RadioPurple);
            final RadioButton rdBlue = dialog.findViewById(R.id.RadioBlue);
            Button btnChange = dialog.findViewById(R.id.ChangeBtn);
            btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rdRed.isChecked()) {
                        recyclerView.setBackgroundResource(R.color.RedColor);
                        Toast.makeText(view.getContext(), "Change to Red", Toast.LENGTH_SHORT).show();
                    }
                    if (rdPurple.isChecked()) {
                        recyclerView.setBackgroundResource(R.color.PurpleColor);
                        Toast.makeText(view.getContext(), "Change to Purple", Toast.LENGTH_SHORT).show();
                    }
                    if (rdBlue.isChecked()) {
                        recyclerView.setBackgroundResource(R.color.BlueColor);
                        Toast.makeText(view.getContext(), "Change to Blue", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(view.getContext(), "Color Has Been Changed", Toast.LENGTH_SHORT).show();
                }
            });

            //Menampilkan dialog dengan isi radiobutton
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void populaterecyclerView(String filter) {
        dbHelper = new DataBaseHelper(this);
        adapter = new TodoListAdapter(dbHelper.todoLists(filter), this, recyclerView);
        recyclerView.setAdapter(adapter);
    }//method yang berisikan inilialisasi recyclerview yang berisikan adpater dengan database


    private SwipeHelper.SwipetoDismissCallBack getCallback(final TodoListAdapter adapter) {
        return new SwipeHelper.SwipetoDismissCallBack() {
            @Override
            public void onSwipedLeft(RecyclerView.ViewHolder viewHolder) {
                adapter.remove(viewHolder.getAdapterPosition());
            }

            //Membuat method yang berisikan ketika ditarik dari kiri dan kanan akan terhapus
            @Override
            public void onSwipedRight(RecyclerView.ViewHolder viewHolder) {
                adapter.remove(viewHolder.getAdapterPosition());
            }
        };
    }


}
