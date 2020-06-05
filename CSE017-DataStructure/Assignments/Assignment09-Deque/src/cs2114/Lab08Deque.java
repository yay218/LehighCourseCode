package cs2114;

// -------------------------------------------------------------------------
/**
 *  This is Lab08Deque class
 *  @param <E> Lab08Deque
 *
 *  @author Yang Yi
 *  @version 2015.10.26
 */
public class Lab08Deque<E> extends DoubleLinkDeque<E>
{

    // ----------------------------------------------------------
    /**
     * Create a new Lab08Deque object.
     */
    public Lab08Deque() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        size = 0;
        head.setPrevious(new Node<E>(null));
        tail.setNext(new Node<E>(null));
        head.setNext(tail);
        tail.setPrevious(head);
    }

    @Override
    public void enqueueAtFront(E value)
    {
        Node<E> newNode = new Node<E>(value);
        newNode.setNext(head.getNext());
        head.getNext().setPrevious(newNode);
        head.setNext(newNode);
        newNode.setPrevious(head);
        size++;
    }

    @Override
    public E dequeueAtFront()
    {
        Node<E> temp = head.getNext();
        head.setNext(head.getNext().getNext());
        head.getNext().setPrevious(head);
        size--;
        return temp.getData();
    }

    @Override
    public void enqueueAtRear(E value)
    {
        Node<E> newNode = new Node<E>(value);
        newNode.setPrevious(tail.getPrevious());
        tail.getPrevious().setNext(newNode);
        newNode.setNext(tail);
        tail.setPrevious(newNode);
        size++;
    }

    @Override
    public E dequeueAtRear()
    {
        Node<E> temp = tail.getPrevious();
        tail.getPrevious().getPrevious().setNext(tail);
        tail.setPrevious(tail.getPrevious().getPrevious());
        size--;
        return temp.getData();
    }

    @Override
    public E frontItem()
    {
        return head.getNext().getData();
    }

    @Override
    public E rearItem()
    {
        return tail.getPrevious().getData();
    }

    @Override
    public void clear()
    {
        head.setNext(tail);
        tail.setPrevious(head);
        size = 0;
    }

    @Override
    public String toString() {
        String result = "[";
        int count = size;
        for (int i = 0; i < count; i++) {
            E temp = this.dequeueAtFront();
            this.enqueueAtRear(temp);
            if (i == count - 1) {
                result += temp;
            }
            else {
                result += temp + ", ";
            }
        }
        return result + "]";
    }
}
