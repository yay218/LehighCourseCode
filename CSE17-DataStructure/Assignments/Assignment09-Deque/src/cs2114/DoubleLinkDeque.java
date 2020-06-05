package cs2114;

// -------------------------------------------------------------------------
/**
 * An abstract class for a deque implemented using a double-linked list.
 *
 * @param <E>
 *            The type of elements contained in the deque.
 * @author S. Edwards, T. Allevato, M. Perez-Quinones, M. Ellis
 * @version 2014.10.19
 */
public abstract class DoubleLinkDeque<E>
    implements Deque<E>
{
    // ~ Instance/static variables .............................................

    /**
     * A reference to the beginning of the deque.
     */
    protected Node<E> head;

    /**
     * A reference to the end of the deque.
     */
    protected Node<E> tail;

    /**
     * The number of elements in the deque.
     */
    protected int     size;


    // -------------------------------------------------------------------------
    /**
     * Internal class to for the node of the linked list Follow it with
     * additional details about its purpose, what abstraction it represents, and
     * how to use it.
     *
     * @param <E>
     * @author mapq
     * @version Oct 8, 2014
     */
    protected static final class Node<E>
    {
        // ~ Fields
// .............................................................

        // The data element stored in the node.
        private E       data;

        // The next node in the sequence.
        private Node<E> next;

        // The previous node in the sequence.
        private Node<E> previous;


        // ~ Constructor
// ........................................................

        /**
         * Creates a new Node with the specified data.
         *
         * @param data
         *            the data for the node
         */
        public Node(E data)
        {
            this.data = data;
        }


        // ----------------------------------------------------------
        /**
         * Gets the data in the node.
         *
         * @return the data in the node
         */
        public E getData()
        {
            return data;
        }


        // ----------------------------------------------------------
        /**
         * Gets the node that follows this one in the sequence.
         *
         * @return the node that follows this one in the sequence
         */
        public Node<E> getNext()
        {
            return next;
        }


        // ----------------------------------------------------------
        /**
         * Gets the node that precedes this one in the sequence.
         *
         * @return the node that precedes this one in the sequence
         */
        public Node<E> getPrevious()
        {
            return previous;
        }


        // ----------------------------------------------------------
        /**
         * Sets the data in the node.
         *
         * @param data
         *            the new data to put in the node
         */
        public void setData(E data)
        {
            this.data = data;
        }


        // ----------------------------------------------------------
        /**
         * Sets the next link in the node.
         *
         * @param next
         *            the new next link to put in the node
         */
        public void setNext(Node<E> next)
        {
            this.next = next;
        }


        // ----------------------------------------------------------
        /**
         * Sets the previous link in the node.
         *
         * @param previous
         *            the new previous link to put in the node
         */
        public void setPrevious(Node<E> previous)
        {
            this.previous = previous;
        }
    }


    // ----------------------------------------------------------
    /**
     * Create a new DoubleLinkDeque object.
     */
    public DoubleLinkDeque()
    {
        size = 0;
        head = null;
        tail = null;
    }


    // ----------------------------------------------------------
    /**
     * Get the number of items in this deque. Does not alter the deque.
     *
     * @return The number of items this deque contains.
     */
    public int size()
    {
        return size;
    }

}
