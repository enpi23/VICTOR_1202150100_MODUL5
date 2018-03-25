package enpi23.modul5.prak.andro.victor_1202150100_modul5.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import enpi23.modul5.prak.andro.victor_1202150100_modul5.Todo.TodoList;

/**
 * Created by enpi23 on 23/03/18.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "todo_list";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TODO = "todo";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRIORITY = "priority";
    public static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TODO + " TEXT NOT NULL, " +
                COLUMN_DESCRIPTION + " TEXT NOT NULL," +
                COLUMN_PRIORITY + " NUMBER NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void saveNewTodo(TodoList todoList) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TODO, todoList.getTodo());
        values.put(COLUMN_DESCRIPTION, todoList.getDescription());
        values.put(COLUMN_PRIORITY, todoList.getPriority());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<TodoList> todoLists(String filter) {
        String query;
        if (filter.equals("")) {
            query = "SELECT  * FROM " + TABLE_NAME;
        } else {
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " + filter;
        }

        List<TodoList> personLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        TodoList todoList;

        if (cursor.moveToFirst()) {
            do {
                todoList = new TodoList();

                todoList.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                todoList.setTodo(cursor.getString(cursor.getColumnIndex(COLUMN_TODO)));
                todoList.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                todoList.setPriority(cursor.getString(cursor.getColumnIndex(COLUMN_PRIORITY)));
                personLinkedList.add(todoList);
            } while (cursor.moveToNext());
        }
        return personLinkedList;
    }

    public void deletePersonRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE _id='" + id + "'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();

    }
}
