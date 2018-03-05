package com.fileloader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program1 {

    /* Method to process the input file.
     * Accept from the file path and name of
     * input file and the output file. Read
     * each line on encounter of a alphanumeric
     * value treat it a a breakpoint between 2 
     * CSUIP prices, write the latest/closing price
     * in the output file. 
     */
    public static void findClosingPrice() throws IOException {
        Pattern pattern = Pattern.compile("^[a-zA-z0-9]*$");
        FileOutputStream outputStream = null;
        Matcher matcher;

        //Accept Input File Name from user
        System.out.print("....Enter Input file name: ");
        Scanner sc = new Scanner(System.in);
        String inputPath = sc.nextLine();

        //Accept Output File Name from user
        System.out.print("....Enter Output file name: ");
        String output = sc.nextLine();
        outputStream = new FileOutputStream(output);

        System.out.println("Input Filename: " + inputPath);
        System.out.println("Output Filename: " + output);

        try {
            String line;
            String stockName = "";
            String closingPrice = "";
            BufferedReader in = new BufferedReader(new FileReader(inputPath));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream));

            //Start reading the input file line by line
            System.out.println("Process ... Inprogress");
            while ( (line = in.readLine()) != null) {
                //Check if the line is a CUSIP (which would be alphnumeric)
                // use this match a breakpoint between each CUSIP
                matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    if (stockName.length()>0) {
                        out.write(stockName+", "+closingPrice);
                        out.newLine();
                        closingPrice = "";
                    }
                    stockName = line;
                } else {
                    closingPrice = line;
                }
            }
            //Print the last CUSIP and its closing price
            if (stockName.length()>0) {
                out.write(stockName+", "+closingPrice);
                out.newLine();
            }

            out.flush();
            if (in != null)  in.close();
            if (out != null) out.close();
            System.out.println("Process ... Completed");
        } catch (FileNotFoundException fne) {
            System.out.println("Sorry could not find file");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }

    }
}
