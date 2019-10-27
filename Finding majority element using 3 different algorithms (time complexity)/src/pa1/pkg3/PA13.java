
package pa1.pkg3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

public class PA13 {
    public static int Method3(int[] numbers, int N) {
        int[] count = new int[10];
        int maxCount = 0;
        int maxElement = 0;
        
        for(int i = 0; i < N; i++) {
            int currentCount = ++count[numbers[i]];
            if (currentCount > maxCount) {
                maxCount = currentCount;
                maxElement = numbers[i];
            }           
        }
        if(maxCount > N/2)
            return maxElement;
        return -1;
    }
    
    public static int Method2(int[] numbers, int N) {
        if (N == 1) return numbers[0];
        int k = N/2;
        int elemlsub = Method2(Arrays.copyOfRange(numbers, 0, k), k);
        int elemrsub = Method2(Arrays.copyOfRange(numbers, k, N), N - k);
        if (elemlsub == elemrsub) return elemlsub;
        int lcount = getFrequency(numbers, elemlsub);
        int rcount = getFrequency(numbers, elemrsub);
        if(lcount > k) return elemlsub;
        else if(rcount > k) return elemrsub;
        else return -1;
    }
    private static int getFrequency(int[] numbers, int element) {
        if(element == -1) {
            return 0;
        }
        int count = 0;
        for(int i = 0; i < numbers.length; i++) {
            if (numbers[i] == element) {
                count++;
            }
        }
        return count;
    }
    public static int Method1(int[] numbers, int N) {
        String track = "";
        int[] count = new int[10];      

        for(int i = 0; i < N; i++) {    
            int element = numbers[i];
            if(!track.contains(Integer.toString(element))) {
                track = track + Integer.toString(element);
                for(int j = 0; j < N; j++) {
                    if(element == numbers[j]) {                       
                        count[element]++;                 
                    }
                }
                if(count[element] > N/2)
                    return element;
            }
        }      
        return -1;
    }
    public static void main(String[] args) {
        
        System.out.println("Please enter the filename: ");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.next();
        
        String line = null;
        int[] Majex = new int[1000];
        int length = 0;
        try {
            scanner = new Scanner(new File(fileName));

            int i = 0;
        
            while(scanner.hasNextInt()) {
                Majex[i] = scanner.nextInt();
                i++;
            }
            length = i;

            System.out.println("Please enter a method: ");
        
            scanner = new Scanner(System.in);
            String methodName = scanner.nextLine();
                      
            int result = -1;
        
            switch (methodName) {
                case "method 1":
                    result = Method1(Majex, length);
                    break;
                case "method 2":
                    result = Method2(Majex, length);
                    break;
                case "method 3":
                    result = Method1(Majex, length);
                    break;
                default:
                    System.out.println("Invalid Selection");
            }
 
            if(result != -1) {
                System.out.println(result);
            }
            else {
                System.out.println("The majority element doesn't exist");
            }
            scanner.close();
        }
        catch(Exception e) {
            System.out.println("I have an error '" + fileName + "'");
        }
    }
}
