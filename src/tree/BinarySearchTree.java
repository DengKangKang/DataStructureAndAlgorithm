package tree;


import com.sun.jndi.toolkit.url.Uri;
import com.sun.jndi.toolkit.url.UrlUtil;

import java.net.MalformedURLException;
import java.net.URL;

class BinarySearchTree {

    public static void main(String[] args) throws MalformedURLException {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(4);
        tree.insert(3);
        tree.insert(5);
        tree.insert(2);
        tree.insert(7);
        tree.insert(6);
        tree.insert(11);
        tree.insert(9);
        tree.layerOrder(tree.tree);
    }

    private Node tree;

    private Node find(int v) {
        Node p = tree;

        while (p != null) {
            if (p.v > v) {
                p = p.left;
            } else if (p.v < v) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    private void insert(int v) {
        if (tree == null) {
            tree = new Node(v, null, null);
            return;
        }
        Node p = tree;
        while (true) {
            if (v > p.v) {
                if (p.right == null) {
                    p.right = new Node(v, null, null);
                    return;
                }
                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new Node(v, null, null);
                    return;
                }
                p = p.left;
            }
        }
    }

    private void delete(int v) {

        Node p = tree;
        Node pp = null;

        while (p != null && p.v != v) {
            pp = p;
            if (p.v > v) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        if (p == null) return;

        if (p.right != null && p.left != null) {
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.v = minP.v;
            p = minP;
            pp = minPP;
        }

        Node child;
        if (p.left != null) child = p.left;
        else child = p.right;

        if (pp == null) tree = child;
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }


    private void preOrder(Node node) {
        if (node == null) return;
        System.out.println(node.v);
        preOrder(node.left);
        preOrder(node.right);

    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.v);
        inOrder(node.right);

    }

    private void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.v);

    }

    private void layerOrder(Node... nodes) {
        if (nodes == null) return;
        StringBuilder sb = new StringBuilder();
        Node[] nextNodes = new Node[nodes.length * 2];
        int count = 0;
        for (Node node : nodes) {
            if (node == null) continue;
            sb.append(node.v).append(" ");
            nextNodes[count++] = node.left;
            nextNodes[count++] = node.right;
        }
        System.out.println(sb);
        if (count != 0) {
            layerOrder(nextNodes);
        }
    }

    static class Node {
        int v;
        Node left;
        Node right;


        Node(int v, Node left, Node right) {
            this.v = v;
            this.left = left;
            this.right = right;

        }
    }
}

