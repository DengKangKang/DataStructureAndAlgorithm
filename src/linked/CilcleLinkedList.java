package linked;

@SuppressWarnings("all")
public class CilcleLinkedList {

    public static void main(String[] args) {
        CilcleLinkedList ls = new CilcleLinkedList();
        ls.add(1);
        ls.add(2);
        ls.add(3);
    }

    private Node head = new Node(-1, null);
    private Node end = null;


    private void add(int v) {
        if(end == null ) {
            end = new Node(v,head);
            head.next =end;
        }else{
            head.next =new Node(v,head.next);
        }
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

    class Node {
        int v;
        Node next;

        Node(int v, Node next) {
            this.v = v;
            this.next = next;

        }


    }


}
