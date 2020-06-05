package plagiarismCatcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * This class is a String Spliter
 *
 * @author Yang Yi
 * @version Dec 5, 2015
 */
public class StringSpliter
{
    private static final int DEFAULT_BUFFER_SIZE = 1;
    /**
     * Create a variable
     */
    String                   contents;


    // ----------------------------------------------------------
    /**
     * Create a new StringSpliter object.
     *
     * @param contents
     *            is String
     */
    public StringSpliter(String contents)
    {
        this.contents = contents;
    }


    // ----------------------------------------------------------
    /**
     * Create a method called size
     *
     * @return size is an integer
     */
    public int size()
    {
        return splitToSentences().size();
    }


    // ----------------------------------------------------------
    /**
     * Create a method called splitToSentences
     *
     * @return result is ArrayList
     */
    public ArrayList<String> splitToSentences()
    {
        ArrayList<String> result = new ArrayList<String>();
        while (contents.endsWith(" "))
        {
            contents = contents.substring(0, contents.length() - 1);
        }
        int realLength = contents.length();
        int tempIndex = 0;
        for (int i = 0; i < realLength; i++)
        {
            if (contents.charAt(i) == '.' || contents.charAt(i) == '?'
                || contents.charAt(i) == '!')
            {
                String temp = contents.substring(tempIndex, i);
                temp = deleteUselessHead(temp);
                temp = deleteUselessTail(temp);
                result.add(temp);
                tempIndex = i + 1;
            }
        }
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
     * Create a method called fileToString
     *
     * @param file
     * @param encoding
     * @return writer is String
     */
    public static String fileToString(File file, String encoding)
    {
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try
        {
            if (encoding == null || "".equals(encoding.trim()))
            {
                reader =
                    new InputStreamReader(new FileInputStream(file), encoding);
            }
            else
            {
                reader = new InputStreamReader(new FileInputStream(file));
            }
            char[] buffer = new char[DEFAULT_BUFFER_SIZE];
            int n = 0;
            while (-1 != (n = reader.read(buffer)))
            {
                writer.write(buffer, 0, n);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (reader != null)
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
        }
        return writer.toString();
    }

}
