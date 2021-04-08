package pratice.zuo.other;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/3/22 4:46 下午
 */
public class BinaryTree<T> {

    private Node<T> root;

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public void preOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        // 首先打头结点
        System.out.print(node.value);
        System.out.print(";");

        // 递归打印左孩子
        preOrder(node.leftChild);

        // 递归打印右孩子
        preOrder(node.rightChild);
    }

    public void inOrder(Node<T> node) {
        if (node == null) {
            return;
        }
        // 首先打印左孩子
        inOrder(node.leftChild);

        // 打印自身节点
        System.out.print(node.value);
        System.out.print(";");

        // 最后打印右孩子
        inOrder(node.rightChild);
    }

    public void postOrder(Node<T> node) {
        if (node == null) {
            return;
        }

        // 优先打印左孩子
        postOrder(node.leftChild);

        // 再打印右孩子
        postOrder(node.rightChild);

        // 最后打印节点本身
        System.out.print(node.value);
        System.out.print(";");
    }

    public void broadTravel(Node<T> node) {
        if (node == null) {
            return;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node<T> tempNode = queue.poll();
            System.out.print(tempNode.getValue());
            System.out.print(";");
            if (tempNode.leftChild != null) {
                queue.add(tempNode.leftChild);
            }
            if (tempNode.rightChild != null) {
                queue.add(tempNode.rightChild);
            }
        }
    }

    public static class Node<T> {
        private Node<T> leftChild;
        private Node<T> rightChild;
        private T value;

        public Node<T> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node<T> leftChild) {
            this.leftChild = leftChild;
        }

        public Node<T> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node<T> rightChild) {
            this.rightChild = rightChild;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

}
