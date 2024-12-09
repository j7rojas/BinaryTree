public class Main {
    public static void main(String[] args){
        MyBinarySearchTree.BinarySearchTree tree = new MyBinarySearchTree.BinarySearchTree();
        
        tree.insert(5);
        tree.insert(3);
        tree.insert(2);
        tree.insert(4);
        tree.insert(7);
        tree.insert(6);
        tree.insert(6);

        tree.insert(8);
        tree.insert(9);

        System.out.println();
        tree.print();

        System.out.println("\nDoes 7 exist? " + tree.search(7)); 
        System.out.println("Does 6 exist? " + tree.search(6)); 
        System.out.println("Does 0 exist? " + tree.search(0)); 

        System.out.println("\nInOrder:");
        tree.traverseTree(tree.inOrder());

        System.out.println("\nPostOrder:");
        tree.traverseTree(tree.postOrder());

        System.out.println("\nPreOrder:");
        tree.traverseTree(tree.preOrder());

        System.out.println("******************************");
        tree.delete(3);
        tree.print();

        System.out.println("\nDoes 7 exist? " + tree.search(7)); 
        System.out.println("Does 6 exist? " + tree.search(6)); 
        System.out.println("Does 0 exist? " + tree.search(0)); 
        
        System.out.println("\nInOrder:");
        tree.traverseTree(tree.inOrder());

        System.out.println("\nPostOrder:");
        tree.traverseTree(tree.postOrder());

        System.out.println("\nPreOrder:");
        tree.traverseTree(tree.preOrder());
    }
}
