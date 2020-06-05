import java.util.EmptyStackException;

// -------------------------------------------------------------------------
/**
 * An implementation of the stack data type that uses a linked chain of
 * {@code Node<E>} objects to store its contents. This is a PARTIAL
 * IMPLEMENTATION that needs completion.
 *
 * @param <E>
 *            the type of elements stored in the stack
 * @author Tony Allevato (authored class skeleton)
 * @author Yang Yi
 * @version 2015.10.19
 */
public class LinkedStack<E>
    implements StackInterface<E>
{
    /**
     * Create node1
     */
    // ~ Fields ...............................................................

    private Node<E> topNode;

    // ~ Constructors .........................................................


    // ----------------------------------------------------------
    // ----------------------------------------------------------
    /**
     * Create a new LinkedStack object.
     */
    public LinkedStack()
    {
        topNode = new Node<E>(null);
    }

    // ~ Methods ..............................................................


    // ----------------------------------------------------------
    /**
     * Create a method called push
     *
     * @param item
     *            is E type
     */
    public void push(E item)
    {
        Node<E> node2 = new Node<E>(item);
        if (item != null)
        {
            topNode.join(node2);
            topNode = node2;
        }
    }


    // ----------------------------------------------------------
    /**
     * Create a method called pop
     */
    public void pop()
    {
        if (topNode.previous() != null)
        {
            topNode = topNode.previous();
            topNode.split();
        }
        else
        {
            throw new EmptyStackException();
        }
    }


    // ----------------------------------------------------------
    /**
     * Create a method called peek
     *
     * @return E type
     */
    public E peek()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            return topNode.data();
        }
    }


    // ----------------------------------------------------------
    /**
     * Create a method called isEmpty
     *
     * @return whether the list is empty
     */
    public boolean isEmpty()
    {
        return topNode.data() == null;
    }
}
