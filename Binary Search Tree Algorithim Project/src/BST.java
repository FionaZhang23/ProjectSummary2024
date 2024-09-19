import java.util.*;

public class BST<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> root;
    private int size;

// Implement the constructor
    public BST() {
        this.root = null;
        this.size = 0;
    }

// Implement the clear method
    public void clear() {
        root = null;
        size = 0;
    }

// Implement the size method
    public int size() {
        return size;
    }

// Implement the insert method
    public void insert(T element) {
        root = insertRecursive(root, element);
        size++;
    }

    public Node<T> insertRecursive(Node<T> root, T element) {
        if (root == null) {
            return new Node<>(element);
        }
        if (element.compareTo(root.getElement()) < 0) {
            root.setLeft(insertRecursive(root.getLeft(), element));
        } else if (element.compareTo(root.getElement()) > 0) {
            root.setRight(insertRecursive(root.getRight(), element));
        }
        return root;
    }

// Implement the remove method
    public void remove(T element) {
        root = removeRecursive(root, element);
        size--;
    }

    public Node<T> removeRecursive(Node<T> root, T element) {
        if (root == null) {
            return null;
        }
        if (element.compareTo(root.getElement()) < 0) {
            root.setLeft(removeRecursive(root.getLeft(), element));
        } else if (element.compareTo(root.getElement()) > 0) {
            root.setRight(removeRecursive(root.getRight(), element));
        } else {
            // Node to be deleted found
            if (root.isLeaf()) {
                return null; // No child case
            } else if (root.getLeft() == null) {
                return root.getRight(); // One child case
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            T minValue = findMin(root.getRight()).getElement();
            root.setElement(minValue);
            root.setRight(removeRecursive(root.getRight(), minValue));
        }
        return root;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

// Implement the search method

    public boolean search(T element) {
        return searchRecursive(root, element);
    }

    private boolean searchRecursive(Node<T> node, T element) {
        if (node == null) {
            return false;
        }
        if (element.compareTo(node.getElement()) == 0) {
            return true;
        } else if (element.compareTo(node.getElement()) < 0) {
            return searchRecursive(node.getLeft(), element);
        } else {
            return searchRecursive(node.getRight(), element);
        }
    }

// Implement the iterator method
    @Override
    public Iterator<T> iterator() {
        return new BSTIterator<>(root);
    }

// Implement the BSTIterator class
    private class BSTIterator<E extends Comparable<E>> implements Iterator<E> {
        private Stack<Node<E>> stack = new Stack<>();

        public BSTIterator(Node<E> root) {
            pushAllLeft(root);
        }

        private void pushAllLeft(Node<E> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            Node<E> node = stack.pop();
            pushAllLeft(node.getRight());
            return node.getElement();
        }
    }

}