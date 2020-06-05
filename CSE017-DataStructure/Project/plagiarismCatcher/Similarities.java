package plagiarismCatcher;

import java.io.File;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * This is a class to compare and find similarities of files
 *
 * @author Yang Yi
 * @version Dec 5, 2015
 */
public class Similarities
{
    // ~ Create variables
    private int                                  highSimilarity;
    private ArrayList<String>                    set;
    private ArrayList<File>                      originalSet;
    private int                                  length;
    private BinarySearchTree<Integer>            searchBST;
    private ArrayList<BinarySearchTree<Integer>> treeList;


    // ----------------------------------------------------------
    /**
     * Create a new Similarities object.
     *
     * @param path
     *            is String
     * @param length
     *            is integer
     * @param highSimilarity
     *            is integer
     * @throws Exception
     *             is exception
     */
    public Similarities(String path, int length, int highSimilarity)
        throws Exception
    {
        this.length = length;
        Transform transform = new Transform(path);
        originalSet = transform.getOriginalSet();
        set = transform.toStringSet();
        treeList = new ArrayList<BinarySearchTree<Integer>>();
        this.highSimilarity = highSimilarity;
        setUp();
    }


    // ----------------------------------------------------------
    /**
     * Create a new Similarities object.
     */
    private void setUp()
    {
        for (int i = 0; i < set.size(); i++)
        {
            searchBST = new BinarySearchTree<Integer>();
            StringSpliter spliter = new StringSpliter(set.get(i));
            ArrayList<ArrayList<String>> list2 =
                new ArrayList<ArrayList<String>>();
            ArrayList<String> sentence = spliter.splitToSentences();
            for (int j = 0; j < sentence.size(); j++)
            {
                WordSpliter wordSpliter = new WordSpliter(sentence.get(j));
                list2.add(wordSpliter.getSequences(length));
            }
            StringToInt toInt = new StringToInt(list2);
            searchBST = toInt.toIntBST();
            treeList.add(searchBST);
        }
    }


    // ----------------------------------------------------------
    /**
     * Create a method called compare
     */
    public void compare()
    {
        ArrayList<String> catchList = new ArrayList<String>();
        for (int i = 0; i < set.size(); i++)
        {
            BinarySearchTree<Integer> tree = treeList.get(i);
            for (int j = i + 1; j < set.size(); j++)
            {
                BinarySearchTree<Integer> tree2 = treeList.get(j);
                FindSimilarity compareTwo = new FindSimilarity(tree, tree2);
                System.out.println(
                    originalSet.get(i).getName() + " "
                        + originalSet.get(j).getName() + " "
                        + compareTwo.similarity());
                if (compareTwo.similarity() > highSimilarity)
                {
                    catchList.add(
                        originalSet.get(i).getName() + " and "
                            + originalSet.get(j).getName()
                            + " share a high number of similar. They have "
                            + compareTwo.similarity()
                            + " sequences in common.");
                }
            }
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i < catchList.size(); i++)
        {
            System.out.println(catchList.get(i));
        }

    }

}
