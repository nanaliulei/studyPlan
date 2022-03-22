package cn.liuliu;

import java.util.Random;

public class SearchTree {

    TreeNode root;

    public static void main(String[] args) {
        SearchTree searchTree = new SearchTree();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            searchTree.insert(random.nextInt(100));
        }
//        TreeNode node = searchTree.root;
//        while (node != null) {
//            TreeNode successor = searchTree.successor(node);
//            System.out.println(new StringBuilder().append("current node:").append(node).append(". Successor is: ").append(successor));
//            node = successor;
//        }
//        System.out.println("--------------------------------------");
//        node = searchTree.root;
//        while (node != null) {
//            TreeNode predecessor = searchTree.predecessor(node);
//            System.out.println(new StringBuilder().append("current node:").append(node).append(". Predecessor is: ").append(predecessor));
//            node = predecessor;
//        }
        searchTree.inorder(searchTree.root);
        for (int i = 0; i < 100; i++) {
            TreeNode node = searchTree.delete(i);
            if (node != null) {
                System.out.println("-----------------------------------");
                System.out.println(node);
                searchTree.inorder(searchTree.root);
            }
        }
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }

    public TreeNode delete(int val) {
        TreeNode node = root;
        while (node != null) {
            if (node.val > val) {
                node = node.left;
            } else if (node.val < val) {
                node = node.right;
            } else {
                break;
            }
        }
        if (node == null) {
            return node;
        }
        delete(node);
        return node;
    }

    private void delete(TreeNode node) {
        TreeNode parent = node.parent, successor = successor(node);
        if (node.left == null) {
            transplant(node, node.right);
        } else if (node.right == null) {
            transplant(node, node.left);
        } else {
            if (successor != node.right) {
                transplant(successor, successor.right);
                successor.right = node.right;
                successor.right.parent = successor;
            }
            transplant(node, successor);
            successor.left = node.left;
            successor.left.parent = successor;
        }
    }

    public void transplant(TreeNode node1, TreeNode node2) {
        TreeNode parent = node1.parent;
        if (parent == null) {
            root = node2;
        } else if (parent.left == node1) {
            parent.left = node2;
        } else if (parent.right == node1) {
            parent.right = node2;
        }
        if (node2 != null) {
            node2.parent = parent;
        }
    }

    public TreeNode predecessor(TreeNode node) {
        if (node.left != null) {
            return getMax(node.left);
        }
        TreeNode parent = node.parent;
        while (parent != null && parent.left == node) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    public TreeNode successor(TreeNode node) {
        if (node.right != null) {
            return getMin(node.right);
        }
        TreeNode parent = node.parent;
        while (parent != null && parent.right == node) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    public TreeNode getMin() {
        return getMin(root);
    }

    private TreeNode getMin(TreeNode node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public TreeNode getMax() {
        return getMax(root);
    }

    private TreeNode getMax(TreeNode node) {
        if (node == null) {
            return node;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public void insert(int val) {
        TreeNode cur = root, parent = null, newNode = new TreeNode(val);
        while (cur != null) {
            parent = cur;
            if (val > cur.val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        if (parent == null) {
            root = newNode;
            return;
        }
        newNode.parent = parent;
        if (val > parent.val) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode(int val) {
            this.val = val;
        }

        public String toString() {
            return new StringBuilder().append("value is ").append(val).toString();
        }
    }

}
