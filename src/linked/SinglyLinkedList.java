package linked;

@SuppressWarnings("all")
public class SinglyLinkedList {

    public static void main(String[] args) {
        SinglyLinkedList ls = new SinglyLinkedList();
        ls.add(1);
        ls.add(2);
        System.out.println(ls);
    }

    private Node head = new Node(-1, null);


    private void add(int v) {
        head.next = new Node(v, head.next);
    }

    public void delete(int v) {
        if (head.next == null) return;
        Node linked = head.next;
        Node prev = head;
        while (linked != null) {
            if (linked.v == v) {
                prev.next = linked.next;
                return;
            } else {
                prev = linked;
                linked = linked.next;
            }

        }
    }


    @Override
    public String toString() {
        if (head.next == null) return "";
        StringBuilder str = new StringBuilder();
        Node linked = head.next;
        while (linked != null) {
            str.append(linked.v);
            linked = linked.next;
        }
        return str.toString();
    }

    class Node {
        int v;
        Node next;

        Node(int v, Node next) {
            this.v = v;
            this.next = next;

        }


    }


}
