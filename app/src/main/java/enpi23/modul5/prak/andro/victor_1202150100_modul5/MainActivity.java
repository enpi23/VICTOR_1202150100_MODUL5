package enpi23.modul5.prak.andro.victor_1202150100_modul5;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    DataHelper dbHelper;
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

        SwipeToDelete swipeToDelete = new SwipeToDelete(MainActivity.this, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        swipeToDelete.setLeftBackgroundColor(R.color.colorAccent);
        swipeToDelete.setRightBackgroundColor(R.color.colorPrimary);
        swipeToDelete.setLeftImg(R.drawable.ic_launcher_background);
        swipeToDelete.setRightImg(R.drawable.ic_launcher_background);
        swipeToDelete.setSwipetoDismissCallBack(getCallback(adapter));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDelete);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populaterecyclerView(String filter) {
        dbHelper = new DataHelper(this);
        adapter = new TodoListAdapter(dbHelper.todoLists(filter), this, recyclerView);
        recyclerView.setAdapter(adapter);

    }

    private SwipeToDelete.SwipetoDismissCallBack getCallback(final TodoListAdapter adapter) {
        return new SwipeToDelete.SwipetoDismissCallBack() {
            @Override
            public void onSwipedLeft(RecyclerView.ViewHolder viewHolder) {
                adapter.remove(viewHolder.getAdapterPosition());
            }

            @Override
            public void onSwipedRight(RecyclerView.ViewHolder viewHolder) {
                Toast.makeText(MainActivity.this, "Another or same action", Toast.LENGTH_SHORT).show();
            }
        };
    }

}
