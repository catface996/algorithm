package question.class2_能力超过难度赚相应的钱.code;

/**
 * @author by catface
 * @date 2021/5/24 5:26 下午
 */
public class Solution3 {

    // 使用有序表

    /**
     * 使用avl树实现有序表
     */
    public static class TreeMap<K extends Comparable<K>, V> {

        private Node<K, V> root;

        /**
         * 右旋,头节点的左孩子作为新的头结点
         * <p>
         * 原头节点作为新头节点的右孩子
         * <p>
         * 新头节点原有的右孩子作为原头节点的左孩子
         *
         * @param head 待旋转的头结点
         * @return 旋转后的新头节点
         */
        private Node<K, V> rightRotate(Node<K, V> head) {
            // 头节点变成左孩子的右节点
            // 左孩子原有的右节点变成原头节点的左孩子
            Node<K, V> newHead = head.left;
            Node<K, V> leftRight = newHead.right;
            newHead.right = head;
            head.left = leftRight;
            // 更新发生变动节点的高度
            head.height = Math.max(head.right != null ? head.right.height : 0,
                head.left != null ? head.left.height : 0);
            newHead.height = Math.max(newHead.left != null ? newHead.left.height : 0, newHead.right.height);
            return newHead;
        }

        /**
         * 左旋
         * <p>
         * 原头节点的右孩子提升为新的头结点
         * <p>
         * 原头节点下沉为新头节点的左孩子
         * <p>
         * 新头节点原有的左孩子变为原头节点的右孩子
         *
         * @param head 待旋转的头结点
         * @return 旋转后的新头节点
         */
        private Node<K, V> leftRotate(Node<K, V> head) {
            Node<K, V> newHead = head.right;
            Node<K, V> rightLeft = newHead.left;
            newHead.left = head;
            head.right = rightLeft;
            head.height = Math.max(head.left != null ? head.left.height : 0,
                head.right != null ? head.right.height : 0);
            newHead.height = Math.max(newHead.left.height, newHead.right != null ? newHead.right.height : 0);
            return newHead;
        }

        /**
         * 检查当前节点是否违规,如果违规,做调整
         * <p>
         * avl 树有四种违规,分别是 LL,LR,RL,RR
         * <p>
         * 可能只出现LL,只出现LR,只出现RL,只出现RR,
         * <p>
         * 也可能同时出现LL,LR, 以及 RL和RR ,不会同时出现LL和RR,不会同时出现LR和RL
         * <p>
         * LL和LR同时出现,当LL处理
         * <p>
         * RL和RR同时出现,当RR处理
         *
         * @param cur 当前要维护的节点
         * @return 维护后返回的新头节点
         */
        private Node<K, V> maintain(Node<K, V> cur) {
            return null;
        }
    }

    public static class Node<K extends Comparable<K>, V> {
        private final K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
