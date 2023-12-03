import java.io.BufferedInputStream;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import java.nio.file.Path;
import java.nio.file.Files;

import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        Scanner inFile;
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        int numWords = 0;
        int numLines = 0;
        int numChars = 0;
        String rec = "";
        String line = "";

        ArrayList<String> lines = new ArrayList<>();

        try
        {

            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                inFile = new Scanner(file);
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                System.out.println("File: " + file.getFileName());
                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    numLines++;
                    numChars += line.length();
                    numWords += new StringTokenizer(line, " ,").countTokens();
                }
                inFile.close();
                System.out.printf("Lines: %d\nWords: %d\nCharacters: %d\n", numLines, numWords, numChars);
            }
            else
            {
                System.out.println("Failed to choose a file to process!");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}