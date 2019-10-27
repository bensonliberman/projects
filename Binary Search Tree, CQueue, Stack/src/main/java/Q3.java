import java.util.Random;

class Node {
    int data;
    int depth = 0;
    Node left;
    Node right;
}

class BST {

    int totalDepth = 0;
    double avgDepth = 0;

    public Node createNode(int n, int depth) {
        Node a = new Node();
        a.depth = depth;
        a.data = n;
        a.left = null;
        a.right = null;
        totalDepth += a.depth;
        return a;
    }

    public Node add(Node node, int value, int depth) {
        if(node == null) {
            return createNode(value, depth);
        }
        if(value < node.data){
            node.left = add(node.left, value, depth + 1);
        }
        else if(value > node.data) {
            node.right = add(node.right, value, depth + 1);
        }
        return node;
    }
}

public class Q3 {
    static int treeSize = 8;
    static int[] numbers = new int[treeSize];

    public static void main(String[] args) {
        Random rand = new Random();

        BST tree = new BST();
        Node root = null;

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(50001);
            root = tree.add(root, numbers[i],0);
        }

        tree.avgDepth = tree.totalDepth / (double)treeSize;
        System.out.println("Average Node Depth = " + tree.avgDepth);

    }
}
