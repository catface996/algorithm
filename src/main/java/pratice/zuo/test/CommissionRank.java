//package pratice.zuo.test;
//
//import java.util.Comparator;
//import java.util.List;
//
///**
// * @author by catface
// * @date 2021/4/2 11:58 下午
// */
//public class CommissionRank {
//
//    private List<Rank> rankList;
//
//    public CommissionRank(List<Rank> rankList) {
//        this.rankList = rankList;
//        rankList.sort(new Comparator<Rank>() {
//            @Override
//            public int compare(Rank o1, Rank o2) {
//                return Long.compare(o2.end, o1.end);
//            }
//        });
//    }
//
//
//    public static double calculateCommission(double payAmount, double balanceBeforePay) {
//        if (balanceBeforePay)
//    }
//
//    private int getCurrentRank(double balanceBeforePay){
//
//    }
//
//    /**
//     * 佣金区间,左开,右闭(start,end]
//     */
//    public static class Rank {
//        long start;
//        long end;
//        double rate;
//    }
//
//}
