package Node;

public class Node<T extends Comparable<T>> {

    private T value;
    private Node<T> right;
    private Node<T> left;
    private long cont;
    private long level;
    private int height;
    private int bf;
    private int count = 0;


    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node(T value, Node<T> right, Node<T> left) {
        this.value = value;
        this.right = right;
        this.left = left;
    }

    public Node(T value, Node<T> right) {
        this.value = value;
        this.right = right;
    }

    public Node() {
        this.value = null;
        this.right = null;
        this.left = null;
    }

    public Node(T value) {
        this.value = value;

    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> next) {
        this.right = next;
    }

    public long getCont() {
        return cont;
    }

    public void setCont(long cont) {
        this.cont = cont;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }
    
    public String getText(){
        return this.value.toString()+" {lvl: "+this.level+", repet: "+this.cont+"}";
    }
    public int getBf() {
        return bf;
    }

    public void setBf(int bf) {
        this.bf = bf;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
