package plagiarismCatcher;

// -------------------------------------------------------------------------
/**
 * Run this class to get the printing
 *
 * @author Yang Yi
 * @version Dec 6, 2015
 */
public class RunMe
{
    // ----------------------------------------------------------
    /**
     * The main method
     *
     * @param args
     *            is String array
     * @throws Exception
     *             is exception
     */
    public static void main(String args[])
        throws Exception
    {
        Similarities A =
            new Similarities("src/plagiarismCatcher/small_set", 6, 200);
        A.compare();
//        Similarities B =
//            new Similarities("src/plagiarismCatcher/medium_set", 6, 200);
//        B.compare();
//        Similarities C =
//            new Similarities("src/plagiarismCatcher/large_set", 6, 200);
//        C.compare();
    }
}
