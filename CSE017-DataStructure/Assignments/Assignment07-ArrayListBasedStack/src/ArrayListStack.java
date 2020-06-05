import java.util.ArrayList;
import java.util.EmptyStackException;

// -------------------------------------------------------------------------
/**
 * An implementation of the stack data type that adapts an ArrayList to store
 * its contents. This is a PARTIAL IMPLEMENTATION that needs completion.
 *
 * @param <T>
 *            the type of elements stored in the stack
 * @author Tony Allevato (authored class skeleton)
 * @author Yang Yi
 * @version 2015.10.14
 */
public class ArrayListStack<T>
    implements SimpleStack<T>
{
    // ~ Instance/static variables ............................................

    /**
     * Declare a field called list
     */
    ArrayList<T> list;


    // ----------------------------------------------------------
    /**
     * Create a new ArrayListStack object.
     */
    // ~ Constructors .........................................................

    // ----------------------------------------------------------
    public ArrayListStack()
    {
        list = new ArrayList<T>();
    }

    // ~ Methods ..............................................................


    // ----------------------------------------------------------
    /**
     * Create a method called push
     *
     * @param item
     *            is T type
     */
    public void push(T item)
    {
        list.add(item);
    }


    // ----------------------------------------------------------
    /**
     * Create a method called pop
     */
    public void pop()
    {
        if (list.isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            list.remove(size() - 1);
        }
    }


    // ----------------------------------------------------------
    /**
     * Create a method called top
     *
     * @return the top element
     */
    public T top()
    {
        if (list.isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            return list.get(size() - 1);
        }
    }


    // ----------------------------------------------------------
    /**
     * Create a method called size
     *
     * @return the size of the list
     */
    public int size()
    {
        return list.size();
    }


    // ----------------------------------------------------------
    /**
     * Create a method called isEmpty
     *
     * @return whether the list is empty
     */
    public boolean isEmpty()
    {
        return list.size() == 0;
    }
}
