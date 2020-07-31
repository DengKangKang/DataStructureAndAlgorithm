package stack;

class LinkedStack {
    private Node firstNode = null;


    void push(String item) {
        firstNode = new Node(item, firstNode);
    }

    String pop() {
        if (firstNode == null) return null;
        String value = firstNode.value;
        firstNode = firstNode.next;
        return value;
    }


    private class Node {
        String value;
        Node next;

        Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }

    }

}

