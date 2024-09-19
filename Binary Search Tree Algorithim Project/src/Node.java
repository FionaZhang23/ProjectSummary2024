public class Node<T extends Comparable<T>> {
    private T element;
    private Node<T> left;
    private Node<T> right;

// Implement the constructor

    public Node(T element) {
        this.element = element;
        this.left = null;
        this.right = null;
    }

// Implement the setElement method

    public void setElement(T element) {
        this.element = element;
    }

// Implement the setLeft method

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    // Implement the setRight method
    public void setRight(Node<T> right) {
        this.right = right;
    }

    // Implement the getLeft method
    public Node<T> getLeft() {
        return left;
    }

// Implement the getRight method
    public Node<T> getRight() {
        return right;
    }

// Implement the getElement method
    public T getElement() {
        return element;
    }

// Implement the isLeaf method
    public boolean isLeaf() {
        return left == null && right == null;
    }
}