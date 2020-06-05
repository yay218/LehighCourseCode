package plagiarismCatcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * This is a class which transform files to String
 *
 * @author Yang Yi
 * @version Dec 5, 2015
 */
public class Transform
{
    private static final int DEFAULT_BUFFER_SIZE = 1;
    /**
     * Variable setPath
     */
    String                   setPath;
    /**
     * Variable fileSet
     */
    ArrayList<File>          fileSet;


    // ----------------------------------------------------------
    /**
     * Create a new Transform object.
     *
     * @param setPath
     *            is String
     * @throws Exception
     *             is exception
     */
    public Transform(String setPath)
        throws Exception
    {
        this.setPath = setPath;
        this.fileSet = readAllFile(setPath);
    }


    // ----------------------------------------------------------
    /**
     * Create a method called getOriginalSet
     *
     * @return fileSet
     */
    public ArrayList<File> getOriginalSet()
    {
        return this.fileSet;
    }


    // ----------------------------------------------------------
    /**
     * Create a method called fileToString
     *
     * @param file
     *            is File
     * @param encoding
     *            is String
     * @return writer is String
     */
    private String fileToString(File file, String encoding)
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


    // ----------------------------------------------------------
    /**
     * Create a method called toStringSet
     *
     * @return fileToString result
     */
    public ArrayList<String> toStringSet()
    {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < fileSet.size(); i++)
        {
            result.add(fileToString(fileSet.get(i), "txt"));
        }
        return result;
    }


    // ----------------------------------------------------------
    /**
     * Create a method called readAllFile
     *
     * @param filePath
     *            is String
     * @throws Exception
     *             is exception
     * @return a list
     */
    private ArrayList<File> readAllFile(String filePath)
        throws Exception
    {
        File f = null;
        f = new File(filePath);
        File[] files = f.listFiles();
        ArrayList<File> list = new ArrayList<File>();
        for (File file : files)
        {
            if (file.isDirectory())
            {
                readAllFile(file.getAbsolutePath());
            }
            else if (file.isFile() && file.getName().endsWith(".txt"))
            {
                list.add(file);
            }
        }
        return list;
    }

}
