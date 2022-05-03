
import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {

    private String name;
    TreeNode<T> root;

    public BinarySearchTree(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Iterator<T> iterator() {

        if (root == null)
            return null;

        return new Iterator<T>() {

            Queue<TreeNode<T>> queue = genQueue(root);

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public T next() {
                return queue.remove().getData();
            }
        };
    }

    /**
     * Generates a queue of tree nodes in the BST in the correct iteration order where the least
     * element will be in front.
     * @return
     *  Returns a queue of <code>TreeNode</code>
     */
    private Queue<TreeNode<T>> genQueue(TreeNode<T> node) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        if (node.left != null) {
            queue.addAll(genQueue(node.left));
        }
        queue.add(node);
        if (node.right != null) {
            queue.addAll(genQueue(node.right));
        }
        return queue;

    }

    /**
     * Adds a single TreeNode to the BST according to BST rules.
     * @param element
     *  The data of the <code>TreeNode</code> being added
     */
    public void add(T element) {
        TreeNode<T> node = new TreeNode<>(element);
        if (root == null)
           root = node;
        else {
            TreeNode<T> pointer = root;
            while (pointer != null) {
                if (pointer.addChild(node))
                    break;
                else if (element.compareTo(pointer.getData()) <= 0)
                    pointer = pointer.left;
                else
                    pointer = pointer.right;
            }
        }

    }

    /**
     * Adds a list of TreeNodes where the elements are the data of each tree node
     * @param elements
     *  elements of type T of the Treenodes being added
     */
    public void addAll(List<T> elements) {
        for (T e : elements) {
            this.add(e);
        }
    }

    @Override
    public String toString() {
        return "[" + this.name + "] " + root.inOrder();
    }

    public static void main(String[] args) {
        // each tree has a name, provided to its constructor
        BinarySearchTree<Integer> t1 = new BinarySearchTree<>("Oak");
        // adds the elements to t1 in the order 5, 3, 0, and then 9
        t1.addAll(Arrays.asList(5, 3, 0, 9));
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>("Maple");
        // adds the elements to t2 in the order 9, 5, and then 10
        t2.addAll(Arrays.asList(9, 5, 10));
        System.out.println(t1); // see the expected output for exact format
        t1.forEach(System.out::println); // iteration in increasing order
        System.out.println(t2); // see the expected output for exact format
        t2.forEach(System.out::println); // iteration in increasing order
        BinarySearchTree<String> t3 = new BinarySearchTree<>("Cornucopia");
        t3.addAll(Arrays.asList("coconut", "apple", "banana", "plum", "durian",
                "no durians on this tree!", "tamarind"));
        System.out.println(t3); // see the expected output for exact format
        t3.forEach(System.out::println); // iteration in increasing order
    }
}
