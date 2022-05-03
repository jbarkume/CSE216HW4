public class TreeNode<T extends Comparable<T>> {

    private T data;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * Adds a child to this node. If the node being added's data is greater than the current node
     * its added to the right, left otherwise
     * @param child
     *  The <code>TreeNode</code> being added
     * @return
     *  returns true if the node was added, false if this node already has a child in that place
     */
    public boolean addChild(TreeNode<T> child) {
        if (data.compareTo(child.getData()) > 0) {
            if (left == null) {
                left = child;
                return true;
            }
            return false;
        }
        if (right == null) {
            right = child;
            return true;
        }
        return false;
    }

    public String inOrder() {
        String s = "" + this.data;
        if (left != null) {
            s += " L:(" + left.inOrder() + ")";
        }
        if (right != null)
            s += " R:(" + right.inOrder() + ")";
        return s;
    }
}
