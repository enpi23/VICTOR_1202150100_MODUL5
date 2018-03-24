package enpi23.modul5.prak.andro.victor_1202150100_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    EditText mTodo, mDescription, mPriority;
    Button btnTambah;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mTodo = (EditText) findViewById(R.id.ed_todo);
        mDescription = (EditText) findViewById(R.id.ed_description);
        mPriority = (EditText) findViewById(R.id.ed_priority);
        btnTambah = (Button) findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTodo();
            }
        });
    }

    private void saveTodo() {
        String todo = mTodo.getText().toString().trim();
        String description = mDescription.getText().toString().trim();
        String priority = mPriority.getText().toString().trim();
        dbHelper = new DataHelper(this);

        if (todo.isEmpty()) {
            Toast.makeText(this, "Masukkan To Do terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
        if (description.isEmpty()) {
            Toast.makeText(this, "Masukkan deskripsi terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
        if (priority.isEmpty()) {
            Toast.makeText(this, "Masukkan prioritas terlebih dahulu", Toast.LENGTH_SHORT).show();
        }

        TodoList todoList = new TodoList(todo, description, priority);
        dbHelper.saveNewTodo(todoList);
        goBackHome();

    }

    private void goBackHome() {
        startActivity(new Intent(CreateActivity.this, MainActivity.class));
    }
}
