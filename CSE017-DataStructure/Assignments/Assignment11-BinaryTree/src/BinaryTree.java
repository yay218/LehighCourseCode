//-------------------------------------------------------------------------
/**
 * Represents a binary tree of arbitrary structure.  This class is different
 * from those in the textbook.  It does not use a separate node class.
 *
 * @param <T> The type of data elements contained in the tree.
 *
 * @author John Lewis (lewis63) and Stephen Edwards (stedwar2)
 * @author  Yang Yi
 * @version 2015.11.12
 */
public class BinaryTree<T>
{
    //~ Instance/static variables .............................................

    private T element;
    private BinaryTree<T> left;
    private BinaryTree<T> right;


    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a single binary tree node containing the
     * given element and no children.
     * @param value The data element to store in the new tree node.
     */
    public BinaryTree(T value)
    {
        this.element = value;
        left = null;
        right = null;
    }


    // ----------------------------------------------------------
    /**
     * Creates a single binary tree node containing the given
     * element and child subtrees.
     * @param value The data value to store on the new node.
     * @param leftChild  A reference to the left child for the new node.
     * @param rightChild A reference to the right child for the new node.
     */
    public BinaryTree(
        T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild)
    {
        element = value;
        right = rightChild;
        left = leftChild;
    }


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Get the data element in this node (i.e., stored at the root of
     * this tree).
     * @return This node's data element
     */
    public T getElement()
    {
        return element;
    }


    // ----------------------------------------------------------
    /**
     * Sets the data element in this node (i.e., store it at the root of
     * this tree).
     * @param value The new data value to store in this node
     */
    public void setElement(T value)
    {
        element = value;
    }


    // ----------------------------------------------------------
    /**
     * Get the left child of this node.
     * @return This node's left child, or null if none.
     */
    public BinaryTree<T> getLeft()
    {
        return left;
    }


    // ----------------------------------------------------------
    /**
     * Sets the left child of this node.
     * @param subtree A reference to the new left child for this node.
     */
    public void setLeft(BinaryTree<T> subtree)
    {
        left = subtree;
    }


    // ----------------------------------------------------------
    /**
     * Get the right child of this node.
     * @return This node's right child, or null if none.
     */
    public BinaryTree<T> getRight()
    {
        return right;
    }


    // ----------------------------------------------------------
    /**
     * Sets the right child of this node.
     * @param subtree A reference to the new right child for this node.
     */
    public void setRight(BinaryTree<T> subtree)
    {
        right = subtree;
    }


    // ----------------------------------------------------------
    /**
     * Calculate the size of this binary tree.
     * @return The size of this tree.
     */
    public int size()
    {
        if (element == null) {
            return 0;
        }

        int size = 1;
        if (left != null) {
            size += left.size();
        }
        if (right != null) {
            size += right.size();
        }
        return size;
    }


    // ----------------------------------------------------------
    /**
     * Calculate the height of this binary tree.
     * @return The height of this tree.
     */
    public int height()
    {
        int height = 1;  // Default height if there are no children

        if (left != null)
        {
            // If there is a left child, check the height on the left side
            height = Math.max(height, 1 + left.height());
        }

        if (right != null)
        {
            // If there is a right child, check the height on the right side
            height = Math.max(height, 1 + right.height());
        }

        return height;
    }


    // ----------------------------------------------------------
    /**
     * Produce a copy of this entire tree.  The copy will contain
     * duplicates of all of the nodes in this tree, but will share
     * references to the same data values (i.e., this tree's node structure
     * is duplicated, but the data elements stored in the nodes are not).
     * @return A reference to a brand new duplicate of this tree.
     */
    public BinaryTree<T> clone()
    {
        BinaryTree<T> copy = new BinaryTree<T>(element);

        if (left != null)
        {
            // If there's a left subtree, duplicate it and attach to the copy
            copy.left = left.clone();
        }
        if (right != null)
        {
            // If there's a right subtree, duplicate it and attach to the copy
            copy.right = right.clone();
        }
        return copy;    // Return resulting tree
    }


    // ----------------------------------------------------------
    /**
     * Generate a string containing the "printed version" of this binary
     * tree using a pre-order traversal.  The tree's contents are printed
     * as a parenthesized list.  See {@link #toPostOrderString()} for an
     * example.
     * @return a printable representation of this tree's contents, using
     *         a pre-order traversal.
     */
    public String toPreOrderString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(element);
        if (left != null)
        {
            builder.append(left.toPreOrderString());
            // Add a space before the next element
            builder.append(" ");
        }
        if (right != null)
        {
            builder.append(right.toPreOrderString());
            // Add a space before the next element
            builder.append(" ");
        }

        builder.append(")");

        return builder.toString();
    }


    // ----------------------------------------------------------
    /**
     * Generate a string containing the "printed version" of this binary
     * tree using a in-order traversal.  The tree's contents are printed as
     * a parenthesized list.  See {@link #toPostOrderString()} for an example.
     * @return a printable representation of this tree's contents, using
     *         an in-order traversal.
     */
    public String toInOrderString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("(");

        if (left != null)
        {
            builder.append(left.toInOrderString());
            // Add a space before the next element
            builder.append(" ");
        }
        builder.append(element);
        if (right != null)
        {
            builder.append(right.toInOrderString());
            // Add a space before the next element
            builder.append(" ");
        }

        builder.append(")");

        return builder.toString();
    }


    // ----------------------------------------------------------
    /**
     * Generate a string containing the "printed version" of this binary
     * tree using a post-order traversal.  The tree's contents are printed
     * as a parenthesized list.  For example, if a tree containing the data
     * value A has a left child containing B and a right child containing C,
     * it would be printed as ((B) (C) A).  The parentheses directly reflect
     * the structure of each node in the tree.
     * @return a printable representation of this tree's contents, using
     *         a post-order traversal.
     */
    public String toPostOrderString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        if (left != null)
        {
            builder.append(left.toPostOrderString());
            // Add a space before the next element
            builder.append(" ");
        }
        if (right != null)
        {
            builder.append(right.toPostOrderString());
            // Add a space before the next element
            builder.append(" ");
        }
        builder.append(element);
        builder.append(")");

        return builder.toString();
    }
}
