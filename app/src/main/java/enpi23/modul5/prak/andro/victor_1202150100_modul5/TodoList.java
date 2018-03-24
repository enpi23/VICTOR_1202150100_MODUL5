package enpi23.modul5.prak.andro.victor_1202150100_modul5;

/**
 * Created by hakimrizki on 22/03/18.
 */

public class TodoList {
    long id;
    String todo;
    String description;
    String priority;

    public TodoList() {
    }

    public TodoList(String todo, String description, String priority) {
        this.todo = todo;
        this.description = description;
        this.priority = priority;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
