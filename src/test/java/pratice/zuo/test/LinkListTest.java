package pratice.zuo.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import pratice.zuo.test.LinkList.LinkNode;

/**
 * @author by catface
 * @date 2021/3/22 5:26 下午
 */
@Slf4j
public class LinkListTest {

    @Test
    public void testHaveRing() {
        LinkNode<Integer> head = new LinkNode<>();
        head.setValue(1);
        LinkList<Integer> linkedList = new LinkList<>();
        linkedList.setHead(head);

        LinkNode<Integer> node2 = new LinkNode<>();
        node2.setValue(2);
        LinkNode<Integer> node3 = new LinkNode<>();
        node3.setValue(3);
        LinkNode<Integer> node4 = new LinkNode<>();
        node4.setValue(4);
        LinkNode<Integer> node5 = new LinkNode<>();
        node5.setValue(5);
        LinkNode<Integer> node6 = new LinkNode<>();
        node6.setValue(6);

        head.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        //node5.setNext(node6);

        LinkNode<Integer> midUpper = linkedList.midOrUpperNode();
        log.info("中点or上边界:{}", midUpper.getValue());

        LinkNode<Integer> midDown = linkedList.midOrDown();
        log.info("中点or下边界:{}", midDown.getValue());

        boolean haveRing = linkedList.haveRing();
        log.info("是否有环:{}", haveRing);

    }

    @Test
    public void testFindFirstEnterRing() {
        LinkNode<Integer> head = new LinkNode<>();
        head.setValue(1);
        LinkList<Integer> linkedList = new LinkList<>();
        linkedList.setHead(head);

        LinkNode<Integer> node2 = new LinkNode<>();
        node2.setValue(2);
        LinkNode<Integer> node3 = new LinkNode<>();
        node3.setValue(3);
        LinkNode<Integer> node4 = new LinkNode<>();
        node4.setValue(4);
        LinkNode<Integer> node5 = new LinkNode<>();
        node5.setValue(5);
        LinkNode<Integer> node6 = new LinkNode<>();
        node6.setValue(6);

        head.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node3);

        LinkNode<Integer> firstEnterNode = linkedList.findFirstEnterRingNode();
        log.info("首个入环节点:{}", firstEnterNode.getValue());
    }

    @Test
    public void testFindJoinNodeJoinBeforeRing() {
        LinkNode<Integer> head = new LinkNode<>();
        head.setValue(1);
        LinkList<Integer> list1 = new LinkList<>();
        list1.setHead(head);

        LinkNode<Integer> node2 = new LinkNode<>();
        node2.setValue(2);
        LinkNode<Integer> node3 = new LinkNode<>();
        node3.setValue(3);
        LinkNode<Integer> node4 = new LinkNode<>();
        node4.setValue(4);
        LinkNode<Integer> node5 = new LinkNode<>();
        node5.setValue(5);
        LinkNode<Integer> node6 = new LinkNode<>();
        node6.setValue(6);

        head.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node3);

        LinkNode<Integer> head2 = new LinkNode<>();
        head2.setValue(10);

        LinkList<Integer> list2 = new LinkList<>();
        list2.setHead(head2);

        LinkNode<Integer> node11 = new LinkNode<>();
        node11.setValue(11);
        LinkNode<Integer> node12 = new LinkNode<>();
        node12.setValue(12);

        head2.setNext(node11);
        node11.setNext(node12);
        node12.setNext(node2);

        LinkNode<Integer> nodeInSet = LinkList.findJoinNodeWithHashSet(list1, list2);
        if (nodeInSet != null) {
            log.info("交叉节点:{}", nodeInSet.getValue());
        }

        LinkNode<Integer> nodeResult = LinkList.findJoinNode(list1, list2);
        if (nodeResult != null) {
            log.info("相交节点:{}", nodeResult.getValue());
        }
    }

    @Test
    public void testFindJoinNodeJoinOnRing() {
        LinkNode<Integer> head = new LinkNode<>();
        head.setValue(1);
        LinkList<Integer> list1 = new LinkList<>();
        list1.setHead(head);

        LinkNode<Integer> node2 = new LinkNode<>();
        node2.setValue(2);
        LinkNode<Integer> node3 = new LinkNode<>();
        node3.setValue(3);
        LinkNode<Integer> node4 = new LinkNode<>();
        node4.setValue(4);
        LinkNode<Integer> node5 = new LinkNode<>();
        node5.setValue(5);
        LinkNode<Integer> node6 = new LinkNode<>();
        node6.setValue(6);

        head.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node3);

        LinkNode<Integer> head2 = new LinkNode<>();
        head2.setValue(10);

        LinkList<Integer> list2 = new LinkList<>();
        list2.setHead(head2);

        LinkNode<Integer> node11 = new LinkNode<>();
        node11.setValue(11);
        LinkNode<Integer> node12 = new LinkNode<>();
        node12.setValue(12);

        head2.setNext(node11);
        node11.setNext(node12);
        node12.setNext(node5);

        LinkNode<Integer> nodeInSet = LinkList.findJoinNodeWithHashSet(list1, list2);
        if (nodeInSet != null) {
            log.info("交叉节点:{}", nodeInSet.getValue());
        }

        LinkNode<Integer> nodeResult = LinkList.findJoinNode(list1, list2);
        if (nodeResult != null) {
            log.info("相交节点:{}", nodeResult.getValue());
        }
    }

    @Test
    public void testFindJoinNodeJoinWithoutRing() {
        LinkNode<Integer> head = new LinkNode<>();
        head.setValue(1);
        LinkList<Integer> list1 = new LinkList<>();
        list1.setHead(head);

        LinkNode<Integer> node2 = new LinkNode<>();
        node2.setValue(2);
        LinkNode<Integer> node3 = new LinkNode<>();
        node3.setValue(3);
        LinkNode<Integer> node4 = new LinkNode<>();
        node4.setValue(4);
        LinkNode<Integer> node5 = new LinkNode<>();
        node5.setValue(5);
        LinkNode<Integer> node6 = new LinkNode<>();
        node6.setValue(6);

        head.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);

        LinkNode<Integer> head2 = new LinkNode<>();
        head2.setValue(10);

        LinkList<Integer> list2 = new LinkList<>();
        list2.setHead(head2);

        LinkNode<Integer> node11 = new LinkNode<>();
        node11.setValue(11);
        LinkNode<Integer> node12 = new LinkNode<>();
        node12.setValue(12);

        head2.setNext(node11);
        node11.setNext(node12);
        node12.setNext(node2);

        LinkNode<Integer> nodeInSet = LinkList.findJoinNodeWithHashSet(list1, list2);
        if (nodeInSet != null) {
            log.info("交叉节点:{}", nodeInSet.getValue());
        }

        LinkNode<Integer> nodeResult = LinkList.findJoinNode(list1, list2);
        if (nodeResult != null) {
            log.info("相交节点:{}", nodeResult.getValue());
        }
    }

}
