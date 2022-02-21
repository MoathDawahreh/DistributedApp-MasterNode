package InvertedIndex;

import java.util.*;
import java.io.*;

public class InvertedIndex1 {
    public static void main(String[] args) {

        TreeMap<String, Integer> frequencyData = new TreeMap<String, Integer>();

        readWordFile(frequencyData);
        printAllCounts(frequencyData);
    }


    public static int getCount(String word, TreeMap<String, Integer> frequencyData) {
        if (frequencyData.containsKey(word)) {
            return frequencyData.get(word);
        } else {
            return 0;
        }
    }


    public static void printAllCounts(TreeMap<String, Integer> frequencyData) {
        System.out.println("-----------------------------------------------");
        System.out.println("ID    Frequency    Term\n-----------------------------------------------");

        for (String word : frequencyData.keySet()) {
            System.out.printf("%15d    %s\n", frequencyData.get(word), word);
        }

        System.out.println("-----------------------------------------------");
    }


    public static void readWordFile(TreeMap<String, Integer> frequencyData) {
        Scanner wordFile;
        String word;
        Integer count;


        for (int x = 0; x < Docs1.length; x++) {

            try {
                wordFile = new Scanner(new FileReader(Docs1[x]));
            } catch (FileNotFoundException e) {
                System.err.println(e);
                return;
            }

            while (wordFile.hasNext()) {

                word = wordFile.next();
               // wordFile.useDelimiter( "[^a-zA-Z]+" );
                word = word.toLowerCase();
                count = getCount(word, frequencyData) + 1;
                frequencyData.put(word, count);
            }

        }
    }

    static String Docs1[] = {"txt.txt"};
    //"C:\\Users\\moath\\Desktop\\Database\\Companies.json"


}