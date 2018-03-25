package enpi23.modul5.prak.andro.victor_1202150100_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import enpi23.modul5.prak.andro.victor_1202150100_modul5.Helper.DataBaseHelper;
import enpi23.modul5.prak.andro.victor_1202150100_modul5.Todo.TodoList;

public class CreateActivity extends AppCompatActivity {

    EditText EdTodo, EdDesc, EdPriority;
    Button AddBtn;
    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        EdTodo = (EditText) findViewById(R.id.EdTodo);
        EdDesc = (EditText) findViewById(R.id.EdDesc);
        EdPriority = (EditText) findViewById(R.id.EdPriority);
        AddBtn = (Button) findViewById(R.id.AddBtn);

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTodo();
            }
        });
    }

    private void saveTodo() {
        String todo = EdTodo.getText().toString().trim();
        String description = EdDesc.getText().toString().trim();
        String priority = EdPriority.getText().toString().trim();
        dbHelper = new DataBaseHelper(this);

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
        //Setting ketika salah satu EditText tidak terisi

    }

    private void goBackHome() {
        startActivity(new Intent(CreateActivity.this, MainActivity.class));
    }
}
