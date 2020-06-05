package plagiarismCatcher;

import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * This class is a woldSpliter
 *
 * @author Yang Yi
 * @version Dec 5, 2015
 */
public class WordSpliter
{
    /**
     * Variable sentence
     */
    String sentence;


    // ----------------------------------------------------------
    /**
     * Create a new WordSpliter object.
     *
     * @param sentence
     *            is String
     */
    public WordSpliter(String sentence)
    {
        this.sentence = sentence;
    }


    // ----------------------------------------------------------
    /**
     * Create a method return the size
     *
     * @return size is an integer
     */
    public int size()
    {
        return splitToWords().size();
    }


    // ----------------------------------------------------------
    /**
     * Create a method called splitToWords
     *
     * @return result is ArrayList
     */
    public ArrayList<String> splitToWords()
    {
        ArrayList<String> result = new ArrayList<String>();
        sentence = deleteUselessHead(sentence);
        sentence = deleteUselessTail(sentence);
        int realLength = sentence.length();
        int tempIndex = 0;
        for (int i = 0; i < realLength; i++)
        {
            if (sentence.charAt(i) == ' ' || sentence.charAt(i) == '\n')
            {
                String temp = sentence.substring(tempIndex, i);
                temp = deleteUselessHead(temp);
                temp = deleteUselessTail(temp);
                result.add(temp);
                tempIndex = i + 1;
            }
        }
        result.add(sentence.substring(tempIndex, sentence.length()));
        return result;
    }


    private String deleteUselessHead(String input)
    {
        if (input.equals(""))
        {
            return input;
        }
        if ((input.charAt(0) <= 90 && input.charAt(0) >= 65)
            || (input.charAt(0) <= 122 && input.charAt(0) >= 97))
        {
            return input;
        }
        else if (input.charAt(0) <= 57 && input.charAt(0) >= 48)
        {
            return input;
        }
        else
        {
            return deleteUselessHead(input.substring(1));
        }
    }


    private String deleteUselessTail(String input)
    {
        if (input.equals(""))
        {
            return input;
        }
        if ((input.charAt(input.length() - 1) <= 90
            && input.charAt(input.length() - 1) >= 65)
            || (input.charAt(input.length() - 1) <= 122
                && input.charAt(input.length() - 1) >= 97))
        {
            return input;
        }
        else if (input.charAt(input.length() - 1) <= 57
            && input.charAt(input.length() - 1) >= 48)
        {
            return input;
        }
        else
        {
            return deleteUselessHead(input.substring(0, input.length() - 1));
        }
    }


    // ----------------------------------------------------------
    /**
     * Create a method called getSequences
     *
     * @param sequenceLength
     *            is an integer
     * @return result is ArrayList
     */
    public ArrayList<String> getSequences(int sequenceLength)
    {
        ArrayList<String> list = splitToWords();
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < list.size() - sequenceLength + 1; i++)
        {
            String temp = "";
            for (int j = 0; j < sequenceLength; j++)
            {
                temp += list.get(i + j) + " ";
            }
            result.add(temp);
        }
        return result;
    }

}
