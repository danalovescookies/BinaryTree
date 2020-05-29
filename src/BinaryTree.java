import java.util.LinkedList;
import java.util.function.Consumer;

public class BinaryTree<T> {
    private Node<T> root;

    public BinaryTree(T rootValue) {
        this.root = new Node<>(rootValue);
    }

    /**
     * Sets given node as root.
     * @param root node to become root
     */
    private void setRoot(Node<T> root) {
        this.root = root;
    }

    /**
     * Returns root node.
     * @return root node
     */
    public Node<T> getRoot() {
        return root;
    }

    /**
     * Applies given function to nodes of the tree using DFS.
     * @param func lambda function to be applied to nodes
     */
    public void dfs(Consumer<T> func) {
        dfs(root, func);
    }

    /**
     * Applies given function to nodes of subtree using DFS.
     * @param node root of subtree
     * @param func lambda function to be applied to nodes
     */
    private void dfs(Node<T> node, Consumer<T> func) {
        if (node.left != null) {
            dfs(node.left, func);
        }
        if (node.right != null) {
            dfs(node.right, func);
        }
        func.accept(node.value);
    }

    /**
     * Applies given function to nodes of the tree using BFS.
     * @param func lambda function to be applied to nodes
     */
    public void bfs(Consumer<T> func){
        LinkedList<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> n = queue.removeFirst();

            func.accept(n.value);
            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
        }
    }

    public static class Node<T> {
        private T value;
        private Node<T> parent;
        private Node<T> left;
        private Node<T> right;

        Node(T value) {
            this.value = value;
            parent = null;
            left = null;
            right = null;
        }

        /**
         * Returns value of node.
         * @return value of node
         */
        public T getValue() {
            return value;
        }

        /**
         * Sets value to node.
         * @param value value to be stored in node
         */
        public void setValue(T value) {
            this.value = value;
        }

        /**
         * Sets left child to given node.
         * @param left node to be left child of current node
         * @throws IllegalArgumentException if passed node already has a parent
         */
        public void setLeft(Node<T> left) {
            if (left.getParent() == null){
                this.left = left;
                left.setParent(this);
            }
            else{
                throw new IllegalArgumentException("Passed node already has a parent!");
            }
        }

        /**
         * Sets right child to given node.
         * @param right node to be right child of current node
         * @throws IllegalArgumentException if passed node already has a parent
         */
        public void setRight(Node<T> right) {
            if (right.getParent() == null){
                this.right = right;
                right.setParent(this);
            }
            else{
                throw new IllegalArgumentException("Passed node already has a parent!");
            }
        }

        /**
         * Sets parent of current node.
         * @param parent node to be parent of current node
         */
        private void setParent(Node<T> parent) {
            this.parent = parent;
        }

        /**
         * Returns parent of current node.
         * @return parent of current node
         */
        public Node<T> getParent() {
            return parent;
        }

        /**
         * Returns left child of node
         * @return left child of node
         */
        public Node<T> getLeft() {
            return left;
        }

        /**
         * Returns right child of node
         * @return right child of node
         */
        public Node<T> getRight() {
            return right;
        }
    }
}
