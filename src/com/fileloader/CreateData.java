package com.fileloader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class CreateData {
	
	public static void createData_For_Prog2() throws IOException {
        FileOutputStream outputStream = null;
        Random rnd = new Random(2000000);
        
        String fileName = "C:\\Users\\sanje\\OneDrive\\Documents\\icetemp\\Set1.txt";

        try {
            outputStream = new FileOutputStream(fileName);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream));
            
            float min = 3F;
            float max = 30F;
            
            BigDecimal value;

            int i = 0;
            while (i<= 1000000) {
            	float randomFloat = rnd.nextFloat() * (max-min)+min;
            	//value = CreateData.newRandomBigDecimal(rnd, 4);
                out.write(String.valueOf(randomFloat));
                out.newLine();
                i++;
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
	
	private static BigDecimal newRandomBigDecimal(Random r, int precision) {
	    BigInteger n = BigInteger.TEN.pow(precision);
	    return new BigDecimal(newRandomBigInteger(n, r), precision);
	}
	

	private static BigInteger newRandomBigInteger(BigInteger n, Random rnd) {
	    BigInteger r;
	    do {
	        r = new BigInteger(n.bitLength(), rnd);
	    } while (r.compareTo(n) >= 0);
	
	    return r;
	}



}
