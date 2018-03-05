package com.fileloader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program2  {
    private static SortedSet<Data> sortedSet = Collections.synchronizedSortedSet(new TreeSet<Data>(new PriceComparator()));
    
    /* Method to accept the 2 input file name.
     * Create 2 threads to reads the input files
     * which would create a TreeSet, which would
     * be the input to create a sorted and merged
     * file.
     */
    @SuppressWarnings("unchecked")
	public static void processFile() throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.print("....Enter Input file 1: ");
        String inputPath1 = sc.nextLine();
        System.out.print("\n....Enter Input file 2: ");
        String inputPath2 = sc.nextLine();
        System.out.print("\n....Enter Output File Name: ");
        String outputPath = sc.nextLine();

        SortFile sortFile1 = new SortFile(inputPath1, sortedSet);
        SortFile sortFile2 = new SortFile(inputPath2, sortedSet);
        
        System.out.println("Process ... Inprogress");
        ExecutorService threadExeutor = Executors.newFixedThreadPool(1);

        try {
			sortedSet = threadExeutor.submit(sortFile1).get();
	        sortedSet = threadExeutor.submit(sortFile2).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} 
        
        threadExeutor.shutdown();

        createOutputFile(outputPath);
        System.out.println("Process ... Completed");
    }

    //Method to create the output file.
    public static void createOutputFile(String fileName) throws IOException {
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(fileName);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream));

            //System.out.println("Size:"+sortedSet.size());
            Iterator<Data> iterator;
            iterator = sortedSet.iterator();
            while (iterator.hasNext()) {
            	Data data = new Data();
            	data = iterator.next();
                out.write(data.getPrice().toString());
                out.newLine();
            }
            out.flush();
            if (out != null) {
                out.close();
            }

        } catch (FileNotFoundException fne) {
            System.out.println("Sorry could not find file directory");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
