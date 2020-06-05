package plagiarismCatcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayDeque;

// -------------------------------------------------------------------------
/**
 * This is a class with a main method called FileProcDemo. You can see a
 * printing of all 6-word sequences of some files in the folder But not all file
 *
 * @author Yang Yi
 * @version Nov 26, 2015
 */
public class FileProcDemo
{
    // ----------------------------------------------------------
    /**
     * This is the main method of this class
     *
     * @param args
     *            is String array
     * @throws FileNotFoundException
     *             is exception
     * @throws IOException
     *             is exception
     */
    public static void main(String[] args)
        throws FileNotFoundException,
        IOException
    {
        File fileName = new File("src/plagiarismCatcher/small_set");
        for (File text : fileName.listFiles())
        {
            BufferedReader bufferReader =
                new BufferedReader(new FileReader(text));
            String line;
            while ((line = bufferReader.readLine()) != null)
            {
                ArrayDeque<String> container = new ArrayDeque<>();
                @SuppressWarnings("resource")
                Scanner in = new Scanner(line);
                while (in.hasNext())
                {
                    String word = in.next();
                    container.add(word);

                    if (container.size() == 6)
                    {
                        System.out.println(container.toString());
                        container.remove();
                    }
                }
            }
            bufferReader.close();
        }
    }
}
