public class Stack <T> extends List <T> {
    public Stack() {
        super(); // Call the constructor of the List class
    }
    public void push(T value) {
        Node newNode = new Node(value, null, head);
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
    }

    // Pop an element from the stack
    public T pop() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T value = head.value;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        return value;
    }

    // Peek the top element of the stack
    public T peek() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return head.value;
    }
}
