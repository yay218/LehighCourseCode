import student.TestCase;

// -------------------------------------------------------------------------
/**
 *
 *  This is a unit test class for BinaryTree
 *
 *  @author Yang Yi
 *  @version Nov 12, 2015
 */
public class BinaryTreeTest extends TestCase {
    /**
     * Create a binaryTree called tree
     */
    private BinaryTree<String> tree;

    /**
     * Create a binaryTree called nullTree
     */
    private BinaryTree<String> nullTree;

    /**
     * Create a binaryTree called rightTest
     */
    BinaryTree<String> rightTest;

    /**
     *Create a binaryTree called leftTest
     */
    BinaryTree<String> leftTest;

    /**
     * Create a binaryTree called nullTree
     */
    BinaryTree<String> tree2;


    /**
     * setUp for BinaryTreeTest
     */
    public void setUp() {
        tree = new BinaryTree<String>("test");
        nullTree = new BinaryTree<String>(null);
        tree2 = new BinaryTree<String>("test", leftTest, rightTest);
    }

    // ----------------------------------------------------------
    /**
     * test clone method
     */
    public void testClone()
    {
        tree.getElement();
        tree.setElement("test");
        tree.getLeft();
        tree.getRight();
        assertEquals(nullTree.size(), 0);
        BinaryTree<String> left = new BinaryTree<String>("left");
        BinaryTree<String> right = new BinaryTree<String>("right");
        tree.setLeft(left);
        tree.setRight(right);
        assertEquals(tree.size(), 3);
        BinaryTree<String> rL = new BinaryTree<String>("1");
        BinaryTree<String> rR = new BinaryTree<String>("2");
        right.setLeft(rL);
        right.setRight(rR);
        assertEquals(tree.size(), 5);
        BinaryTree<String> clone;
        clone = tree.clone();
        assertEquals(clone.height(), tree.height());
        assertEquals(clone.size(), tree.size());
    }

    // ----------------------------------------------------------
    /**
     * test ToPreOrderString method
     */
    public void testToPreOrderString() {
        BinaryTree<String> left = new BinaryTree<String>("left");
        BinaryTree<String> right = new BinaryTree<String>("right");
        tree.setLeft(left);
        tree.setRight(right);
        assertEquals(tree.toPreOrderString(), "(test(left) (right) )");
    }
    // ----------------------------------------------------------
    /**
     * test ToInOrderString method
     */
    public void testToInOrderString()
    {
        BinaryTree<String> left = new BinaryTree<String>("left");
        BinaryTree<String> right = new BinaryTree<String>("right");
        tree.setLeft(left);
        tree.setRight(right);
        assertEquals(tree.toInOrderString(), "((left) test(right) )");
    }

    // ----------------------------------------------------------
    /**
     * test ToPostOrderString method
     */
    public void testToPostOrderString()
    {
        BinaryTree<String> left = new BinaryTree<String>("left");
        BinaryTree<String> right = new BinaryTree<String>("right");
        tree.setLeft(left);
        tree.setRight(right);
        assertEquals(tree.toPostOrderString(), "((left) (right) test)");
    }

}