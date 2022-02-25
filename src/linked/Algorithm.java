package linked;

public class Algorithm {

    public static void main(String[] args) {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.add(1);
        singlyLinkedList.add(2);
        singlyLinkedList.add(3);
        singlyLinkedList.add(4);
        singlyLinkedList.add(5);
        singlyLinkedList.head.next = reverse(singlyLinkedList.head.next);
        System.out.println(singlyLinkedList);
    }

    public static SinglyLinkedList.Node reverse(SinglyLinkedList.Node node) {
        if (node == null || node.next == null) return node;
        SinglyLinkedList.Node rv = node;
        while (node.next != null) {
            SinglyLinkedList.Node tmp = rv;
            rv = node.next;
            node.next = node.next.next;
            rv.next = tmp;
        }
        return rv;
    }
}
