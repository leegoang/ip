public class Todo extends Task {
    public Todo(String decription) {
        super(decription);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
