package com.fileloader;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.Callable;

public class SortFile implements Callable<SortedSet<Data>> {
    SortedSet<Data> sortedSet = new TreeSet<Data>(new PriceComparator());

    private String fileName;

    public SortFile(String fileName, SortedSet<Data> sortedSet) {
        this.fileName = fileName;
        this.sortedSet = sortedSet;
    }

    public SortedSet<Data> call() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line;
            BigDecimal value;
            while ( (line = in.readLine()) != null) {
            	value = new BigDecimal(line);
            	Data data = new Data();
            	data.setPrice(value);
                sortedSet.add(data);
            }
            if (in != null) {
                in.close();
            }
        } catch (FileNotFoundException fne) {
            System.out.println("Sorry could not find file");
        } catch (IOException ioe) {
            System.out.println("ERROR: "+ioe.getMessage());
        }
        return sortedSet;
    }
}
