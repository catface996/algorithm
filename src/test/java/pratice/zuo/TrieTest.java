package pratice.zuo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import pratice.zuo.other.Trie;
import pratice.zuo.other.Trie.Tree;

/**
 * @author by catface
 * @date 2021/3/22 3:43 下午
 */
@Slf4j
public class TrieTest {

    @Test
    public void test() {
        Tree tree = new Trie.Tree();
        for (int i = 0; i < 10; i++) {
            tree.insert("akdfkldf");
        }
        for (int i = 0; i < 10; i++) {
            tree.insert("akdjjkl");
        }
        int times = tree.search("akdfkldf");
        log.info("存在次数:{}", times);
        tree.delete("akdfkldf");
        times = tree.search("akdfkldf");
        log.info("删除一次后,存在次数:{}", times);
        log.info("tree:{}", tree);
        int prefixNumber = tree.prefixNumber("akd");
        log.info("前缀数量:{}", prefixNumber);
    }

}
