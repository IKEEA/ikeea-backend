package mif.vu.ikeea.FileHandling;

import mif.vu.ikeea.Exceptions.WrongFileException;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;

public class Writer {

    public static void writeUsingOutputStream(String filename, String data) {

        try(FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(data);

        } catch (IOException e) {
            throw new WrongFileException("Something went wrong while trying to log actions to file");
        }

    }

}
