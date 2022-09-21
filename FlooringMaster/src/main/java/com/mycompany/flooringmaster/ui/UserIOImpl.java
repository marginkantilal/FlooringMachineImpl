/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.ui;

import java.util.Scanner;

/**
 *
 * @author ritheenhep
 */
public class UserIOImpl implements UserIO {
    
    
      Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
  
        System.out.println(message);
    }
    @Override
     public void println(String message) {
  
        System.out.println(message);
    }


    @Override
    public double readDouble(String prompt) {
        print(prompt);
 
        String input = sc.nextLine();
 
        return Double.parseDouble(input);
 
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        do {
            print(prompt);
       
            String input = sc.nextLine();
     
            double db = Double.parseDouble(input);
     
            if (db >= min && db <= max) {
    
               return db;
               
           } else {
   
             //   System.out.println("Enter number within range");
            }

        } while (true);
    }

    @Override
    public float readFloat(String prompt) {
 
        print(prompt);
  
        String input = sc.nextLine();
 
        return Float.parseFloat(input);
  
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
       
        do {
            print(prompt);
        
            Float fl = sc.nextFloat();

            if (fl >= min || fl <= max) {

                return fl;

            } else {
      
            //    System.out.println("Please enter number within range");
            }

        } while (true);

    }

    @Override
    public int readInt(String prompt) {
        do{
        print(prompt);
   
        String input = sc.nextLine();
        return Integer.parseInt(input);
       
        }while(true);
   
    }

    
    @Override
    public int readInt(String prompt, int min, int max) {
     
        do {
            print(prompt);
     
            String input = sc.nextLine();
     
            int in = Integer.parseInt(input);
     
            if (in >= min && in <= max) {
     
                return in;
     
            } else {
      
              //  System.out.println("out of range");
            }

        } while (true);
    }

    @Override
    public long readLong(String prompt) {
      
        print(prompt);
    
        String input = sc.nextLine();
    
        return Long.parseLong(input);
    
        
        
        
    }

    @Override
    public long readLong(String prompt, long min, long max) {
    
        do {
            print(prompt);
           
            String input = sc.nextLine();
           
            long ln = Long.parseLong(input);
            
            if (ln >= min || ln <= max) {

            } else {
              //  System.out.println("Please Enter value within range");
            }

        }while(true);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return sc.nextLine();    
    }
    
    
}
