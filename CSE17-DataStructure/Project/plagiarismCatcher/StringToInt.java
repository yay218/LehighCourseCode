package plagiarismCatcher;

import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * This is a class to transform String to Integer
 *
 * @author Yang Yi
 * @version Dec 5, 2015
 */
public class StringToInt
{
    /**
     * Create ArrayList list
     */
    ArrayList<ArrayList<String>> list;


    // ----------------------------------------------------------
    /**
     * Create a new StringToInt object.
     *
     * @param list
     *            is ArrayList
     */
    public StringToInt(ArrayList<ArrayList<String>> list)
    {
        this.list = list;
    }


    // ----------------------------------------------------------
    /**
     * Create a method called toIntBST
     *
     * @return result is BinarySearchTree
     */
    public BinarySearchTree<Integer> toIntBST()
    {
        BinarySearchTree<Integer> result = new BinarySearchTree<Integer>();
        for (int i = 0; i < list.size(); i++)
        {
            for (int j = 0; j < list.get(i).size(); j++)
            {
                if (result
                    .find(list.get(i).get(j).toLowerCase().hashCode()) == null)
                {
                    result.insert(list.get(i).get(j).toLowerCase().hashCode());
                }
            }
        }
        return result;
    }

}
