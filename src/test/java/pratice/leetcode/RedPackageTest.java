package pratice.leetcode;

import java.util.List;

import org.junit.Test;
import pratice.zuo.other.RedPackage;

/**
 * @author by catface
 * @date 2021/2/3 10:29 上午
 */
public class RedPackageTest {

	@Test
	public void test() {
		RedPackage redPackage = new RedPackage();
		List<Integer> packages = redPackage.firstRound(1000, 20, 80, 40);
		int totalAmount = 0;
		for (Integer aPackage : packages) {
			System.out.println(aPackage);
			totalAmount = totalAmount + aPackage;
		}
		System.out.println(totalAmount);
	}
}
