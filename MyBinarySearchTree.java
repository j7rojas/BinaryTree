import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class MyBinarySearchTree {
    @FunctionalInterface
    public interface TraversalStrategy {
        // Consume<Integer> the type that represents a function in Java
        void traverse(Node root, Consumer<Integer> action);
    }

    public interface BinarySearchTreeInterface {
        public void insert(int data);
        void delete(int data);
        boolean search(int data);
        void traverseTree(TraversalStrategy strategy);
        void print();
    }

    // Every node can be considered a root node, basically just means look at it's
    // left/right child
    static class BinarySearchTree implements BinarySearchTreeInterface {
        private Node root;

        @Override
        public void insert(int data) {
            Node newNode = new Node(data);
            root = insertHelper(root, newNode);
        }

        // Main recursive code for handling insert
        private Node insertHelper(Node root, Node node) {
            int data = node.data;

            // If root is empty then our tree is empty, first data is our root
            if (root == null) {
                root = node;
                return root;
            } // Check for duplicate, if duplicate found change nothing
            else if (data == root.data) {
                return root;
            }
            // Check if current data is less than the left child if so, start all over
            // recursively
            else if (data < root.data) {
                // The new root is really just the left child of the current root node
                root.left = insertHelper(root.left, node);
            } // The current data then must be greater than the current node
            else {
                root.right = insertHelper(root.right, node);
            }

            return root;
        }

        @Override
        public void traverseTree(TraversalStrategy strategy) {
            strategy.traverse(root, data -> System.out.print(data + " "));
            System.out.println();
        }

        // In-order Traversal
        public TraversalStrategy inOrder() {
            return this::inOrderHelper;
        }        

        /*
        *   In-order Traversal:
            Visit the left subtree, then the current node, then the right subtree.
            Result: This traversal visits nodes in ascending order (for binary search trees).
            Order: Left → Root → Right
         */
        private void inOrderHelper(Node root, Consumer<Integer> action) {
            if (root != null) {
                inOrderHelper(root.left, action);
                action.accept(root.data);
                inOrderHelper(root.right, action);
            }
        }

        // Pre-order Traversal
        public TraversalStrategy preOrder() {
            return this::preOrderHelper;
        }
        
        /*
         * Pre-order Traversal:
            Visit the current node first, then the left subtree, and then the right subtree.
            Result: This is useful when you want to process the node before its children, such as cloning a tree.
            Order: Root → Left → Right
         */
        private void preOrderHelper(Node root, Consumer<Integer> action) {
            if (root != null) {
                action.accept(root.data);
                preOrderHelper(root.left, action);
                preOrderHelper(root.right, action);
            }
        }

        // Post-order Traversal
        public TraversalStrategy postOrder() {
            return this::postOrderHelper;
        }
        
        /*
         * Post-order Traversal:
            Visit the left subtree first, then the right subtree, and finally the current node.
            Result: This is useful for operations that need to be done after processing the children, like deleting nodes from a tree.
            Order: Left → Right → Root
         */
        private void postOrderHelper(Node root, Consumer<Integer> action) {
            if (root != null) {
                postOrderHelper(root.left, action);
                postOrderHelper(root.right, action);
                action.accept(root.data);
            }
        }
        
      // Print method (Level-order traversal with indentation)
      @Override
      public void print() {
          if (root == null) {
              System.out.println("Tree is empty.");
              return;
          }

          Queue<Node> queue = new LinkedList<>();
          queue.add(root);
          int level = 0;
          
          while (!queue.isEmpty()) {
              int nodeCount = queue.size();
              System.out.print("Level " + level + ": ");
              
              while (nodeCount > 0) {
                  Node current = queue.poll();
                  System.out.print(current.data + " ");

                  if (current.left != null) {
                      queue.add(current.left);
                  }
                  if (current.right != null) {
                      queue.add(current.right);
                  }
                  nodeCount--;
              }
              System.out.println();
              level++;
          }
      }

        
        @Override
        public boolean search(int data) {
            return searchHelper(root, data);
        }

        private boolean searchHelper(Node root, int data) {
            if(root == null){
                return false;
            } else if (root.data == data){
                return true;
            } else if(data < root.data){
                return searchHelper(root.left, data);
            }
            else{
                return searchHelper(root.right, data);
            }
        }

        @Override
        public void delete(int data) {
            if(search(data)){
                deleteHelper(root, data);
            } else {
                System.out.println(data + " not found in the tree");
            }
        }

        private Node deleteHelper(Node root, int data) {
            // Base case to end deletion
            if(root == null){
                return root;
            } else if(data < root.data){
                root.left = deleteHelper(root.left, data);
            } else if (data > root.data){
                root.right = deleteHelper(root.right, data);
            } else {// we have found the target node
                // if the node is a leaf node aka it has no children
                if(root.left == null && root.right == null) {
                    // perform deletion
                    root = null;
                } else if(root.right != null) { // The node we are trying to delete has a right child
                    root.data = successor(root);
                    root.right = deleteHelper(root.right, root.data);
                } else { // The node we are trying to delete has a left child
                    root.data = predecessor(root);
                    root.left = deleteHelper(root.left, root.data);
                }
            }

            return root;
        }

        /*
         * Find the least value below the right-child of this root node
         */
        private int successor(Node root) {
            // Went to the right once
            root = root.right;
            // Find the smallest left child
            while(root.left != null){
                root = root.left;
            }
            return root.data;
        }

                /*
         * Find the greatest value below the left-child of this root node
         */
        private int predecessor(Node root) {
            root = root.left;
            while(root.right != null){
                root = root.right;
            }
            return root.data;
        }
    }
}
