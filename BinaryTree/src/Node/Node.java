package Node;

public class Node<T extends Comparable<T>> {

    private T value;
    private Node<T> next;
    private Node<T> back;
    private long cont;
    private long level;
    

    public Node<T> getBack() {
        return back;
    }

    public void setBack(Node<T> back) {
        this.back = back;
    }

    public Node(T value, Node<T> next, Node<T> back) {
        this.value = value;
        this.next = next;
        this.back = back;
    }

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public Node() {
        this.value = null;
        this.next = null;
        this.back = null;
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

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
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
    

}