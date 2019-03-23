package tree;

import Excepciones.IsEmptyException;
import Node.Node;

public interface Tree<T extends Comparable<T>> {

    boolean add(T value);

    long beetwen(T start, T end) throws IsEmptyException;

    T bigger() throws IsEmptyException;

    int height() throws IsEmptyException;

    void inOrder() throws IsEmptyException;

    void isEmpty() throws IsEmptyException;

    void lvlUpdate(Node<T> root, int nivel);

    T minor() throws IsEmptyException;

    Node<T> minor(Node<T> node) throws IsEmptyException;

    void posOrder() throws IsEmptyException;

    void preOrder() throws IsEmptyException;

    boolean remove(T value) throws IsEmptyException;

    Node<T> search(T value) throws IsEmptyException;
}
