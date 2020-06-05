import java.util.HashMap;

// -------------------------------------------------------------------------
/**
 * An implementation of the Levenshtein distance algorithm.
 *
 * @author Yang Yi
 * @version 2015.09.22
 */
public class Levenshtein
{
    // ~ Fields ..........................................................

    // Add fields here.
    private String           string1;
    private String           string2;
    /**
     * Create a HashMap to store
     */
    HashMap<String, Integer> map;

    // ~ Constructors ....................................................


    // Add constructors here.
    // ----------------------------------------------------------
    /**
     * Create a new Levenshtein object.
     *
     * @param string1
     *            is a string
     * @param string2
     *            is a string
     */
    public Levenshtein(String string1, String string2)
    {
        this.string1 = string1;
        this.string2 = string2;
        map = new HashMap<String, Integer>();
    }

    // ~ Methods .........................................................


    // Add methods here.
    /**
     * Create a new distance method.
     *
     * @param c1
     *            is an int
     * @param length1
     *            is an int
     * @param c2
     *            is an int
     * @param length2
     *            is an int
     */
    private int distance(int c1, int length1, int c2, int length2)
    {
        String key = c1 + "," + length1 + "," + c2 + "," + length2;
        if (map.containsKey(key))
        {
            return map.get(key);
        }
        else if (length1 == 0)
        {
            map.put(key, length2);
            return length2;
        }
        else if (length2 == 0)
        {
            map.put(key, length1);
            return length1;
        }
        else
        {
            int distance1 = distance(c1 + 1, length1 - 1, c2, length2) + 1;
            int distance2 = distance(c1, length1, c2 + 1, length2 - 1) + 1;
            int distance3 =
                distance(c1 + 1, length1 - 1, c2 + 1, length2 - 1) + 1;
            if (string1.charAt(c1) == string2.charAt(c2))
            {
                distance3--;
            }
            map.put(key, Math.min(distance3, Math.min(distance1, distance2)));
            return Math.min(distance3, Math.min(distance1, distance2));
        }

    }


    // ----------------------------------------------------------
    /**
     * Create a method called distance()
     *
     * @return a integer
     */
    public int distance()
    {
        return distance(0, string1.length(), 0, string2.length());
    }
}
