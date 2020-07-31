package hashtab;

import java.util.Arrays;

public class HashTable {

    public static void main(String[] args) {
        String s = "  hello world!  ";

        System.out.println(reverseWords(s));
    }

    public static String reverseWords(String s) {
        String[] ss = s.split("\\s");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = ss.length - 1; i >= 0; i--) {
            if (!ss[i].isEmpty()) {
                stringBuilder.append(ss[i]).append(" ");
            }
        }

        return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();
    }

    private int capacity = 4;
    private int size = 0;
    private Node[] table;

    private HashTable() {
        table = new Node[capacity];
    }

    private void push(Object k, Object v) {
        if (k == null) return;
        int hash = k.hashCode();
        int i = (capacity - 1) & hash;
        if (table[i] == null) {
            table[i] = new Node(k, v, hash, null);
            return;
        }
        Node node = table[i];
        while (node != null) {
            if (node.k == k) return;
            node = node.next;
        }
        table[i] = new Node(k, v, hash, table[i]);
    }

    private Object find(Object k) {
        if (k == null) return null;
        int hash = k.hashCode();
        int i = (capacity - 1) & hash;
        Node node = table[i];
        while (node != null) {
            if (node.hash == hash && node.k == k) return node.v;
            node = node.next;
        }
        return null;
    }


    class Node {
        Object k;
        Object v;
        int hash;
        Node next;

        Node(Object k, Object v, int hash, Node next) {
            this.k = k;
            this.v = v;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public String toString() {
            return "k: " + k + " v: " + v;
        }
    }
}
