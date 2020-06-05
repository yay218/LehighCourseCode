
// -------------------------------------------------------------------------
/**
 *  Create a class called Expressions that contains a static main method
 *
 *  @author Yang Yi
 *  @version Nov 12, 2015
 */
public class Expressions
{
    // ----------------------------------------------------------
    /**
     * Create a class called Expressions that contains a static main method.
     * @param args is String
     */
    public static void main(String[] args) {
        BinaryTree<String> parent = new BinaryTree<String>("*");
        BinaryTree<String> minus = new BinaryTree<String>("-");
        BinaryTree<String> a = new BinaryTree<String>("a");
        BinaryTree<String> b = new BinaryTree<String>("b");
        BinaryTree<String> c = new BinaryTree<String>("c");
        BinaryTree<String> d = new BinaryTree<String>("d");
        BinaryTree<String> plus = new BinaryTree<String>("+");
        BinaryTree<String> divide = new BinaryTree<String>("/");
        BinaryTree<String> e = new BinaryTree<String>("e");

        parent.setLeft(minus);
        parent.setRight(divide);

        divide.setRight(e);
        divide.setLeft(plus);

        minus.setLeft(a);
        minus.setRight(b);

        plus.setLeft(c);
        plus.setRight(d);
        System.out.println(parent.toInOrderString());
    }
}
