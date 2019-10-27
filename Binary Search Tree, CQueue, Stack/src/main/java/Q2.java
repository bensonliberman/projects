import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many soldiers? ");
        int numSoldiers = scanner.nextInt();

        System.out.println("Type " + numSoldiers + " names: ");
        for(int i = 0; i < numSoldiers; i++) {
            String name = scanner.next();
            cQueue.enqueue(name);
        }

        System.out.print("Enter the positions: ");
        int positions = scanner.nextInt();

        System.out.print("The survivor is: " + cQueue.playGame(positions));

        scanner.close();
    }
}

class CQueue {
    private Link first;
    private Link last;
    private int size = 0;

    public CQueue() {

    }

    public void enqueue(String name) {
        Link person = new Link(name);
        if (first == null) {
            first = person;
            last = person;
            first.next = last;
            last.next = first;
        }
        else {
            last.next = person;
            person.next = first;
            last = person;
        }
        size++;
    }

    public Link playGame(int n){
        Link cur = first;
        while(size != 1){
            for(int i = 0; i < n-2; i++){
                cur = cur.next;
            }
            if(cur.next == first){
                first = cur.next.next;
            }
            cur.next = cur.next.next;
            cur = cur.next;
            size--;
        }
        return cur;

    }

    public int getSize() {
        return size;
    }
}

class Link {
    private String name;
    public Link next;

    public Link(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}