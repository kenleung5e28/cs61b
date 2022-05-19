package byog.Core;

public class BinaryTree<T> {
    private final TreeNode<T> root;

    public BinaryTree(T rootValue) {
        root = new TreeNode<>(rootValue);
    }

    public TreeNode<T> getRoot() {
        return root;
    }
}
