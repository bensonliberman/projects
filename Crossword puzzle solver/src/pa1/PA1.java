/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pa1;

import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.String;

public class PA1 {
  
    static char[][] puzzle = new char[50][50];
    static char[][] solution = new char[50][50];
    static char[][] words = new char[50][50];
    static int currentWord = 0;
    
    public static void main(String[] args) { 
    
        long startTime = System.nanoTime();
        
        String fileName = "C:\\Users\\benso\\Documents\\NetBeansProjects\\PA1\\puzzleinput.txt";
        String fileWords = "C:\\Users\\benso\\Documents\\NetBeansProjects\\PA1\\wordlist.txt"; 
 
        String line = null;
                       
        try {
            FileReader fileReader = new FileReader(fileName);
            FileReader fileReader2 = new FileReader(fileWords);
       
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
            
            int i = 0;
            int j = 0;
            
            while((line = bufferedReader.readLine()) != null) {
                puzzle[i++] = line.toCharArray();
            }   
            
            while((line = bufferedReader2.readLine()) != null) {
                words[j++] = line.toUpperCase().toCharArray();
            }
            
            bufferedReader.close();
            bufferedReader2.close();
        }
        catch(Exception ex) {
            System.out.println("I have an error '" + fileName + "'");                
        }

        for(int i = 0; i < words.length; i++) {
            currentWord = i;
            checkAllDirections(words[currentWord]);
        }
  
        for (char[] row : solution){
            System.out.println(Arrays.toString(row).replaceAll(",", "").replaceAll("\\[|\\]", ""));            
        }
        
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println();
        System.out.println("Calculated Run Time: " + totalTime + " nanoseconds");
    }
    
    public static void checkAllDirections(char[] word){
        for(int i = 0; i < puzzle.length; i++) {
            for(int j = 0; j < puzzle[i].length; j++) {
                if(word[0] == puzzle[i][j]) {
                    //call all functions
                    readRight(i,j);
                    readLeft(i,j);
                    readUp(i,j);
                    readDown(i,j);
                    readNE(i,j);
                    readSE(i,j);
                    readNW(i,j);
                    readSW(i,j);
                }
            }
        }
    }
    
    public static boolean readRight(int row, int column) {
        for(int i = 0; i < words[currentWord].length; i++) {
            if(column+i >= 50) {
                return false;
            }
            if(words[currentWord][i] != puzzle[row][column+i]) {
                return false;
            }
        }
        for(int i = 0; i < words[currentWord].length; i++) { 
            solution[row][column+i] = puzzle[row][column+i];
        }
        return true;
    }
    public static boolean readLeft(int row, int column) {
        for(int i = 0; i < words[currentWord].length; i++) {
            if(column-i < 0) {
                return false;
            }
            if(words[currentWord][i] != puzzle[row][column-i]) {
                return false;
            }
        }
        for(int i = 0; i < words[currentWord].length; i++) { 
            solution[row][column-i] = puzzle[row][column-i];
        }
        return true;
    }
    public static boolean readUp(int row, int column) {
        for(int i = 0; i < words[currentWord].length; i++) {
            if(row-i < 0) {
                return false;
            }
            if(words[currentWord][i] != puzzle[row-i][column]) {
                return false;
            }
        }
        for(int i = 0; i < words[currentWord].length; i++) { 
            solution[row-i][column] = puzzle[row-i][column];
        }
        return true;
    }
    public static boolean readDown(int row, int column) {
        for(int i = 0; i < words[currentWord].length; i++) {
            if(row+i >= 50) {
                return false;
            }
            if(words[currentWord][i] != puzzle[row+i][column]) {
                return false;
            }
        }
        for(int i = 0; i < words[currentWord].length; i++) { 
            solution[row+i][column] = puzzle[row+i][column];
        }
        return true;
    }
    public static boolean readNE(int row, int column) {
        for(int i = 0; i < words[currentWord].length; i++) {
            if(column+i >= 50 || row-i < 0) {
                return false;
            }
            if(words[currentWord][i] != puzzle[row-i][column+i]) {
                return false;
            }
        }
        for(int i = 0; i < words[currentWord].length; i++) { 
            solution[row-i][column+i] = puzzle[row-i][column+i];
        }
        return true;
    }
    public static boolean readSE(int row, int column) {
        for(int i = 0; i < words[currentWord].length; i++) {
            if(column+i >= 50 || row+i >= 50) {
                return false;
            }
            if(words[currentWord][i] != puzzle[row+i][column+i]) {
                return false;
            }
        }
        for(int i = 0; i < words[currentWord].length; i++) { 
            solution[row+i][column+i] = puzzle[row+i][column+i];
        }
        return true;
    }
    public static boolean readNW(int row, int column) {
        for(int i = 0; i < words[currentWord].length; i++) {
            if(column-i < 0 || row-i < 0) {
                return false;
            }
            if(words[currentWord][i] != puzzle[row-i][column-i]) {
                return false;
            }
        }
        for(int i = 0; i < words[currentWord].length; i++) { 
            solution[row-i][column-i] = puzzle[row-i][column-i];
        }
        return true;
    }
    public static boolean readSW(int row, int column) {
        for(int i = 0; i < words[currentWord].length; i++) {
            if(column-i < 0 || row+i >= 50) {
                return false;
            }
            if(words[currentWord][i] != puzzle[row+i][column-i]) {
                return false;
            }
        }
        for(int i = 0; i < words[currentWord].length; i++) { 
            solution[row+i][column-i] = puzzle[row+i][column-i];
        }
        return true;
    }
}
