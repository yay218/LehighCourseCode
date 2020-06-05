package plagiarismCatcher;

// -------------------------------------------------------------------------
/**
 * This is a class to find the similarity between two files using trees
 *
 * @author Yang Yi
 * @version Dec 5, 2015
 */
public class FindSimilarity
{
    /**
     * Create a BinarySearchTree
     */
    BinarySearchTree<Integer> tree;
    /**
     * Create another BinarySearchTree
     */
    BinarySearchTree<Integer> tree2;


    // ----------------------------------------------------------
    /**
     * Create a new FindSimilarity object.
     *
     * @param tree
     *            is BinarySearchTree
     * @param tree2
     *            is BinarySearchTree
     */
    public FindSimilarity(
        BinarySearchTree<Integer> tree,
        BinarySearchTree<Integer> tree2)
    {
        this.tree = tree;
        this.tree2 = tree2;
    }


    // ----------------------------------------------------------
    /**
     * Create a method called similarity
     *
     * @return node is BinaryNode
     */
    private int similarity(BinaryNode<Integer> node)
    {
        if (node == null)
        {
            return 0;
        }
        else
        {
            int b = node.getElement();
            if (tree2.find(b) != null)
            {
                return 1 + similarity(node.getLeft())
                    + similarity(node.getRight());
            }
            else
            {
                return similarity(node.getLeft()) + similarity(node.getRight());
            }

        }
    }


    // ----------------------------------------------------------
    /**
     * create a public method called similarity
     *
     * @return an integer
     */
    public int similarity()
    {
        return similarity(tree.root());
    }

}
