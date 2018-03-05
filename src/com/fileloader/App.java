package com.fileloader;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String args[]) {
        boolean bExit = false;
        while (!bExit) {
            Scanner sc1 = new Scanner(System.in);
            System.out.println("\n\n\n\n"+"Select a program");
            System.out.println("................");
            System.out.println("1. Problem 1");
            System.out.println("2. Problem 2");
            System.out.println("3. Exit system");
            //System.out.println("4. Create Data for program 2");
            System.out.print("Your Option[1-3]: ");
            int prompt = sc1.nextInt();
            switch (prompt) {
                case 1:
                    try {
                        Program1.findClosingPrice();
                    } catch (Exception e) {
                        System.out.println("Error : "+e.getMessage());
                    };
                    break;
                case 2:
                    try {
                        Program2.processFile();
                    } catch (Exception e) {
                        System.out.println("Error : "+e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Thank you...");
                    bExit = true;
                case 4:
					try {
						CreateData.createData_For_Prog2();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                break;
            }
        }
    }
}
