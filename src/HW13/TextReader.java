package HW13;

import java.io.*;
import java.util.Arrays;

public class TextReader {
    public static void main(String[] args) {
        File text = new File("test.txt");
        String[] split = new String[1];
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(text))) {
            bufferedOutputStream.write("200, 300, 100".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(text));
            String line = bufferedReader.readLine();
            while(line != null) {
                split = line.split(", ");
                System.out.println("unsorted");
                System.out.println(Arrays.toString(split));
                Arrays.sort(split);
                System.out.println("sorted");
                System.out.println(Arrays.toString(split));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("blaSorted.txt"))) {
            bufferedOutputStream.write(Arrays.toString(split).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
