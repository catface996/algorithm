package pratice.zuo;

import org.junit.Test;
import pratice.zuo.other.BinaryTree;
import pratice.zuo.other.BinaryTree.Node;

/**
 * @author by catface
 * @date 2021/3/22 4:56 下午
 */
public class BinaryTreeTest {

    @Test
    public void testPreOrder() {

        Node<Integer> root = new Node<>();
        root.setValue(0);
        BinaryTree<Integer> bTree = new BinaryTree<>();
        bTree.setRoot(root);

        Node<Integer> l1 = new Node<>();
        l1.setValue(1);

        Node<Integer> r1 = new Node<>();
        r1.setValue(2);

        Node<Integer> l11 = new Node<>();
        l11.setValue(3);

        Node<Integer> l12 = new Node<>();
        l12.setValue(4);

        Node<Integer> r11 = new Node<>();
        r11.setValue(5);

        Node<Integer> r12 = new Node<>();
        r12.setValue(6);

        root.setLeftChild(l1);
        root.setRightChild(r1);

        l1.setLeftChild(l11);
        l1.setRightChild(l12);

        r1.setLeftChild(r11);
        r1.setRightChild(r12);

        System.out.println("前序遍历:");
        bTree.preOrder(bTree.getRoot());
        System.out.println("");

        System.out.println("中序遍历:");
        bTree.inOrder(bTree.getRoot());
        System.out.println("");

        System.out.println("后续遍历:");
        bTree.postOrder(bTree.getRoot());
        System.out.println("");

        System.out.println("广度优先遍历:");
        bTree.broadTravel(bTree.getRoot());
        System.out.println("");

    }
}
