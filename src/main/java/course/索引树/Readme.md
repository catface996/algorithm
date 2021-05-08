# Index Tree
## 解决的问题
解决累加和问题

## 其他方案的比较
* 前缀和数组

  原始数组arr[], 前缀和数组prefix[],查询i位置到j位置的累加和,只需要prefix[j]-prefix[i]即可;但是当arr[]中的x位置的数值发生变化是,需要大量更新prefix[]中的数值,复杂度是O(N)

* 线段树

  原始数组arr[],SegmentTree中有orgin[] 对应arr[],有sum[] 记录累加和,有lazy记录懒添加,有chang[]记录懒更新,有update[]记录懒更新是否生效

  支持范围add,支持范围update,支持范围query

## Index Tree 原理

在tree[]数组中:

0位置负责记录0位置的累加和,

1位置负责记录0~1位置的累加和

2位置负责记录2位置的累加和

3位置负责记录0~3位置的累加和

...以此类推

![image-20210508184533711](/Users/catface/Library/Application%20Support/typora-user-images/image-20210508184533711.png)

## 实现方案

通过index的二进制来实现

tree[] 数组来记录累加和,长度为原始数组长度加1,弃用0位置.

* add 方法的实现

  ```java
  // 需要计算index位置被tree中的那些位置累加,首先一定被tree[index]中累加,假如index位置为1,那么1位置的值将被,2,4,8,16,...2^x<=n位置的记录累加和
  // 如果index=5,则被5,6,8,...,
  // 抽象成 对应的二进制为0000 0101, 0000 0110, 0000 1000,...
  // 通项公式为 index; index+ = index&-index, 即 index+index只保留最右侧的1
  ```

* querySum方法的实现

  ```java
  // 假设index=8,8位置负责累计了1~8位置的累加和,直接返回
  // 假设index=7,7位置只累计了7位置的值,6位置累计了5~6位置的累加和,4位置累计了1~4位置的累加和,所以只需要返回tree[7]+tree[6]+tree[4]
  // 8转成二进制 0000 1000,
  // 7,6,4 分别转成二进制 0000 0111, 0000 0110, 0000 0100
  // 通项公式为 index,index-=index&-index, 即 index - index只保留最右侧i
  ```



