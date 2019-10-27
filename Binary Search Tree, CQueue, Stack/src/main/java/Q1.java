import javafx.util.Pair;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

public class Q1 {
    static char[][] maze = new char[20][20];

    public static void main(String[] args) {
        String fileName = "maze.txt";

        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int i = 0;

            while((line = bufferedReader.readLine()) != null) {
                maze[i++] = line.toCharArray();
            }

            for(int j = 0; j < 20; j++) {
                for(int k = 0; k < 20; k++) {
                    System.out.print(maze[j][k]);
                }
                System.out.println();
            }

            bufferedReader.close();
        }
        catch(Exception ex) {
            System.out.println("I have an error '" + fileName + "'");
        }

        System.out.println("Enter starting x coordinate: ");
        Scanner scanner = new Scanner(System.in);
        int coorX = scanner.nextInt();
        System.out.println("Enter starting y coordinate: ");
        scanner = new Scanner(System.in);
        int coorY = scanner.nextInt();

        boolean found = findPath(coorX,coorY);

        if(found) {
            System.out.println("I am free!");
            for(int j = 0; j < 20; j++) {
                for(int k = 0; k < 20; k++) {
                    System.out.print(maze[j][k]);
                }
                System.out.println();
            }
        }
        else {
            System.out.println("Help, I am trapped");
            for(int j = 0; j < 20; j++) {
                for(int k = 0; k < 20; k++) {
                    System.out.print(maze[j][k]);
                }
                System.out.println();
            }
        }
    }

    public static boolean findPath(int x, int y) {
        Stack<Pair<Integer, Integer>> path = new Stack<Pair<Integer, Integer>>();
        Pair<Integer, Integer> curPos = new Pair<>(x,y);

        maze[x][y] = 'S';

        if (checkLeft(curPos.getKey(),curPos.getValue())) path.push(new Pair<>(curPos.getKey(),curPos.getValue()-1));
        if (checkRight(curPos.getKey(),curPos.getValue())) path.push(new Pair<>(curPos.getKey(),curPos.getValue()+1));
        if (checkUp(curPos.getKey(),curPos.getValue())) path.push(new Pair<>(curPos.getKey()-1,curPos.getValue()));
        if (checkDown(curPos.getKey(),curPos.getValue())) path.push(new Pair<>(curPos.getKey()+1,curPos.getValue()));

        while (!path.isEmpty()) {
            curPos = path.pop();

            if (maze[curPos.getKey()][curPos.getValue()] == 'E') return true;
            maze[curPos.getKey()][curPos.getValue()] = '+';

            if (checkLeft(curPos.getKey(),curPos.getValue())) path.push(new Pair<>(curPos.getKey(),curPos.getValue()-1));
            if (checkRight(curPos.getKey(),curPos.getValue())) path.push(new Pair<>(curPos.getKey(),curPos.getValue()+1));
            if (checkUp(curPos.getKey(),curPos.getValue())) path.push(new Pair<>(curPos.getKey()-1,curPos.getValue()));
            if (checkDown(curPos.getKey(),curPos.getValue())) path.push(new Pair<>(curPos.getKey()+1,curPos.getValue()));
        }

        return false;
    }

    public static boolean checkLeft(int x, int y) {
        if (y == 0) return false;
        if (maze[x][y-1] == '0' || maze[x][y-1] == 'E') return true;
        return false;
    }
    public static boolean checkRight(int x, int y) {
        if (y == 19) return false;
        if (maze[x][y+1] == '0' || maze[x][y+1] == 'E') return true;
        return false;
    }
    public static boolean checkUp(int x, int y) {
        if (x == 0) return false;
        if (maze[x-1][y] == '0' || maze[x-1][y] == 'E') return true;
        return false;
    }
    public static boolean checkDown(int x, int y) {
        if (x == 19) return false;
        if (maze[x + 1][y] == '0' || maze[x + 1][y] == 'E') return true;
        return false;
    }
}
