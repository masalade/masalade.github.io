import java.util.*;

class Node {
    int val;
    Node left;
    Node right;

    Node() {
    }

    Node(int val) {
        this.val = val;
    }

    Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public void recoverTree(Node root) {
        Stack<Node> stack = new Stack<>();
        Node current = root;
        Node lastProcessed = null;
        Node[] swapped = new Node[2];
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            if (lastProcessed != null && lastProcessed.val > current.val) {
                if (swapped[0] == null) {
                    swapped[0] = lastProcessed;
                    swapped[1] = current;
                } else {
                    swapped[1] = current;
                    break;
                }
            }
            lastProcessed = current;
            current = current.right;
        }
        int temp = swapped[0].val;
        swapped[0].val = swapped[1].val;
        swapped[1].val = temp;
    }

    static void printInorder(Node TreeNode) {
        if (TreeNode == null)
            return;
        printInorder(TreeNode.left);
        System.out.print(" " + TreeNode.val);
        printInorder(TreeNode.right);
    }

    static Node build(String s[]) {
        if (s[0] == "N" || s.length == 0)
            return null;
        Node root = new Node(Integer.parseInt(s[0]));
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        int i = 1;

        while (!q.isEmpty() && i < s.length) {
            Node curr = q.poll();
            String cval = s[i];

            if (!cval.equals("N")) {
                int h = Integer.parseInt(cval);
                curr.left = new Node(h);
                q.add(curr.left);
            }
            i++;
            if (i >= s.length)
                break;
            cval = s[i];
            if (!cval.equals("N")) {
                int h = Integer.parseInt(cval);
                curr.right = new Node(h);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }
}
