package pratice.zuo.test;

import org.junit.Test;
import pratice.zuo.test.BinaryTree.Node;

/**
 * @author by catface
 * @date 2021/3/22 4:56 下午
 */
public class BinaryTreeTest {

    @Test
    public void testPreOrder() {
        Node<Integer> root = new Node<>();
        root.setValue(100);
        BinaryTree<Integer> bTree = new BinaryTree<>();

        Node<Integer> leftChild = new Node<>();
        leftChild.setValue(10);

        Node<Integer> rightChild = new Node<>();
        rightChild.setValue(20);

        root.setLeftChild(leftChild);
        root.setRightChild(rightChild);

        bTree.setRoot(root);
        bTree.preOrder(bTree.getRoot());
    }
}
