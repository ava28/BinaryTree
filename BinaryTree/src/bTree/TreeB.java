package bTree;

import Excepciones.IsEmptyException;

import Node.Node;
import java.util.stream.Stream;
import tree.Tree;

public class TreeB<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;
    int nodecount=0;
    public TreeB(T value) {
        this.root = new Node<>(value);
        this.root.setCont(0L);
        this.root.setLevel(0L);
    }

    public TreeB(Node<T> node) {
        this.root = node;
        this.root.setCont(0L);
        this.root.setLevel(0L);
    }

    
    @Override
    public boolean add(T value) {
        if (value == null) {
            return false;
        } else {
            if (root.getValue() == null) {
                root.setValue(value);
                return true;
            } else {
                if (add(root, value, root.getLevel()) != null) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private Node<T> add(Node<T> root, T value, long lvl) {
        if (root == null) {
            root = new Node<>(value);
            root.setLevel(lvl);
            root.setCont(0);
            return root;
        } else {
            switch (root.getValue().compareTo(value)) {
                case 0:
                    root.setCont(root.getCont() + 1);
                    break;
                case 1:
                    root.setBack(add(root.getBack(), value, ++lvl));
                    break;
                default:
                    root.setNext(add(root.getNext(), value, ++lvl));
                    break;
            }
        }
        return root;
    }
    private long count;

    @Override
    public long beetwen(T start, T end) throws IsEmptyException {
        count = -1l;
        beetwen(root, start, end);
        return count + 1;
    }

    private void beetwen(Node node, T x, T y) {
        if (node == null) {
            return;
        }
        if (node.getValue().compareTo(x) == 1) {
            beetwen(node.getBack(), x, y);
        }
        if ((node.getValue().compareTo(x) == 0 || node.getValue().compareTo(x) == 1) && (node.getValue().compareTo(y) == 0 || node.getValue().compareTo(y) == -1)) {
            if (node.getCont() > 0) {
                for (int i = 0; i < node.getCont() + 1; i++) {
                    count += 1l;
                }
            } else {
                count += 1l;
            }
        }
        if (node.getValue().compareTo(y) == -1) {
            beetwen(node.getNext(), x, y);
        }
    }

    /**
     *
     * @return Retorna el valor mas grande.
     * @throws IsEmptyException
     */
    @Override
    public T bigger() throws IsEmptyException {
        return bigger(root);
    }

    /**
     *
     * @param node Recive el arbol.
     * @return
     */
    private T bigger(Node<T> node) {
        return node.getNext() == null ? node.getValue() : bigger(node.getNext());
    }

    private int altura = 0;

    /**
     *
     * @return Retorna la altura del arbol.
     * @throws IsEmptyException
     */
    @Override
    public int height() throws IsEmptyException {
        heigth(root, 1);
        return altura;
    }

    /**
     * @param reco Arbol.
     * @param nivel Empieza en 1.
     */
    private void heigth(Node reco, int nivel) {
        if (reco != null) {
            heigth(reco.getBack(), nivel + 1);
            if (nivel > altura) {
                altura = nivel;
            }
            heigth(reco.getNext(), nivel + 1);
        }
    }

    /**
     * @throws IsEmptyException
     */
    @Override
    public void inOrder() throws IsEmptyException {
        System.out.println("\nIn-Order:");
        inOrder(root);
    }

    private void inOrder(Node<T> root) {
        if (root != null) {
            inOrder(root.getBack());
            System.out.print(root.getValue() + "{" + root.getLevel() + "," + root.getCont() + "}, ");
            inOrder(root.getNext());
        }
    }

    /**
     * @throws IsEmptyException
     */
    @Override
    public void isEmpty() throws IsEmptyException {
        if (root == null) {
            throw new IsEmptyException("Esmpty tree.");
        }
    }

    /**
     * @throws IsEmptyException
     */
    @Override
    public void posOrder() throws IsEmptyException {
        System.out.println("\nPos-Order:");
        posOrder(root);
    }

    private void posOrder(Node<T> root) {
        if (root != null) {
            posOrder(root.getBack());
            posOrder(root.getNext());
            System.out.print(root.getValue() + "{" + root.getLevel() + "," + root.getCont() + "}, ");
        }
    }

    /**
     * @throws IsEmptyException
     */
    @Override
    public void preOrder() throws IsEmptyException {
        System.out.println("\nPre-Order:");
        preOrder(root);
    }

    private void preOrder(Node<T> root) {
        if (root != null) {
            nodecount++;
            System.out.print(root.getValue() + "{" + root.getLevel() + "," + root.getCont() + "}, ");
            preOrder(root.getBack());
            preOrder(root.getNext());
        }
    }
    
    @Override
    public boolean remove(T value) throws IsEmptyException {
        Node<T> tmp;
        boolean opc;
        try {
            if ((tmp = search(value)) != null) {
                if ((opc = remove(tmp)) == true) {
                    return opc;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    private boolean remove(Node<T> node) throws IsEmptyException {
        if (node.getCont() > 0) {
            node.setCont(node.getCont() - 1);
            lvlUpdate(root, 1);
            return true;
        }
        Node<T> father = hasFather(node, root, null);
        try {
            if (father == null) {
                if (root.getNext() != null) {
                    Node<T> minor = minor(root.getNext());
                    minor.setBack(root.getBack());
                    root = root.getNext();
                } else {
                    root = root.getBack();
                }
                System.gc();
                return true;
            }
            if (node.getBack() == null && node.getNext() == null) { 
                if (node.getValue().compareTo(father.getValue()) > 0) {
                    father.setNext(null);
                } else {
                    father.setBack(null);
                }
                return true;
            }
            if (node.getNext() != null && node.getBack() == null) {
                if (node.getValue().compareTo(father.getValue()) > 0) {
                    father.setNext(node.getNext());
                } else {
                    father.setBack(node.getNext());
                }
                return true;
            }
            if (node.getNext() == null && node.getBack() != null) {
                if (node.getValue().compareTo(father.getValue()) > 0) {
                    father.setNext(node.getBack());
                } else {
                    father.setBack(node.getBack());
                }
                return true;
            } else {
                Node<T> minor = minor(node.getNext());
                minor.setBack(node.getBack());
                father.setBack(minor);
                father.setNext(null);
                System.gc();
                return true;
            }
        } catch (IsEmptyException ex) {
            System.err.println(ex.getMessage());
        } finally {
            lvlUpdate(root, 1);
        }
        return false;
    }

    private Node<T> hasFather(Node<T> value, Node<T> root, Node<T> father) {
        if (value.getValue().equals(root.getValue())) {
            return father;
        }
        if (value.getValue().compareTo(root.getValue()) <= -1) {
            return hasFather(value, root.getBack(), root);
        } else {
            return hasFather(value, root.getNext(), root);
        }
    }

    @Override
    public Node<T> search(T value) throws IsEmptyException {
        return search(value, root);
    }

    private Node<T> search(T value, Node<T> root) {
        if (root == null) {
            return null;
        } else {
            if (root.getValue().equals(value)) {
                return root;
            } else {
                return value.compareTo(root.getValue()) < 0 ? search(value, root.getBack()) : search(value, root.getNext());
            }
        }
    }

    @Override
    public void lvlUpdate(Node<T> root, int nivel) {
        if (root != null) {
            root.setLevel(nivel);
            heigth(root.getBack(), nivel + 1);
            root.setLevel(nivel);
            heigth(root.getNext(), nivel + 1);

        }
    }


    @Override
    public T minor() throws IsEmptyException {
        return minor(root).getValue();
    }

    
    @Override
    public Node<T> minor(Node<T> node) throws IsEmptyException {
        if (node.getBack() == null) {
            
            return node;
        } else {
            return minor(node.getBack());
        }
    }

    public void printLevel() throws IsEmptyException {
        long h = height();
        for (int i = 1; i <= h; i++) {
            
            System.out.print("Lvl " + (i-1) + " : ");
            printLevel(root, i);
            System.out.println();
        }
    }

    
    private void printLevel(Node<T> node, int level) {
        if (node == null) {
            
            return;
        }
        if (level == 1) {
            System.out.print(node.getValue() + " ");
        } else if (level > 1) {
            printLevel(node.getBack(), level - 1);
            printLevel(node.getNext(), level - 1);
        }
    }
    
    public int width() {
        return this.nodecount;
    }
    public void fill(TreeB b, int x, int n, int m) {
        for (int i = 0; i < x; i++) {
            b.add((int) Math.abs(Math.floor(Math.random() * (n - m + 1) + m)));
        }
    }

    @Override
    public String toString() {
        return TreePrinter.getTreeDisplay(root);
    }
}