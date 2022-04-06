package cn.liuliu;

import javax.print.attribute.IntegerSyntax;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

    private Map<Integer, TreeNode> nodeMap;

    public static void main(String[] args) {
//        Main main = new Main();
//        TreeNode root = main.buildTree();
//        for (int i = 0; i < 6; i++) {
//            TreeNode nextNode = main.findNext(main.nodeMap.get(i + 1));
//            System.out.println(nextNode);
//        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 1);
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            iterator.remove();
        }
        System.out.println(map);
    }

    public TreeNode buildTree() {
        nodeMap = new HashMap<>();
        for (int i = 1; i <= 6; i++) {
            nodeMap.put(i, new TreeNode(i));
        }
        TreeNode root = nodeMap.get(1);
        root.left = nodeMap.get(2);
        root.right = nodeMap.get(3);
        nodeMap.get(2).parent = root;
        nodeMap.get(3).parent = root;
        nodeMap.get(3).left = nodeMap.get(4);
        nodeMap.get(3).right = nodeMap.get(5);
        nodeMap.get(4).parent = nodeMap.get(3);
        nodeMap.get(5).parent = nodeMap.get(3);
        nodeMap.get(4).right = nodeMap.get(6);
        nodeMap.get(6).parent = nodeMap.get(4);
        return root;
    }

    public TreeNode findNext(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
//            TreeNode parent = node.parent;
//            if (parent == null) {
//                return null;
//            }
//            if (parent.right == node) {
//
//            }
            TreeNode parent = findParentWithRightNode(node);
            if (parent == null) {
                return null;
            }
            return parent;
        }
        return findLeftNode(node.right);
    }

    private TreeNode findParentWithRightNode(TreeNode node) {
        while (node != null && node.parent != null) {
            TreeNode parent = node.parent;
            if (parent == null) {
                return null;
            }
            if (parent.right != node) {
                return parent;
            }
            node = parent;
        }
        return null;
    }

    private TreeNode findLeftNode(TreeNode root) {
        if (root == null || (root.left == null && root.right == null) || root.left == null) {
            return root;
        }
        return findLeftNode(root.left);
    }

    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode(int value, TreeNode left, TreeNode right, TreeNode parent) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "value=" + value +
                    '}';
        }
    }
}
