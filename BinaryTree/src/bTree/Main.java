package bTree;

import Excepciones.IsEmptyException;

public class Main {

    public static void main(String[] args) throws IsEmptyException {
        TreeB<Integer> btree = new TreeB<>(5);
        btree.add(5);
        btree.add(5);
        btree.add(1);
        btree.add(2);
        btree.add(2);
        btree.add(2);
        btree.add(3);
        btree.add(3);
        btree.add(4);
        btree.add(8);
        btree.add(8);
        btree.add(8);
        btree.add(7);
        btree.add(7);
        btree.add(7);
        btree.add(7);
        btree.add(9);

        btree.remove(8);
        btree.preOrder();
        btree.inOrder();
        btree.posOrder();
        
        btree.fill(btree, 5, 0, 10);
        System.out.println(btree);
        btree.printLevel();
        System.out.println(btree.beetwen(1,4));
        System.out.println(btree.beetwen(4, 10));
        System.out.println("Height"+btree.height());
        btree.breadthFirstTraversal(); //Impresión por nivel

        System.exit(0);
    }
}
