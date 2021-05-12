# AC自动机

## 概念

* fail指针

  fail指针是指,在前缀树上某一个节点匹配失败后,吓一跳是什么位置.

  举例: abcdef;cdey;ez;

  当abcd匹配失败时,以d结尾的后缀,能匹配到的最长前缀.

  极端例子:

  abcde;bcde;cde;de;e;

* 避免过早的淘汰匹配成功的可能性.

## 算法

### 基础结构

~~~java
public class Node{
  /**
  	是否是某个模式字符串的结尾
  **/
 	private int end;
  /**
  	当前字符的后继字符节点key代表经过的字符,Node是下一个节点
  **/
  private Map<Character,Node> nexts;
}
~~~



### 构建前缀树算法

~~~java
public class AcAutomation {
    public void insert(String word);
}
~~~

### 建立fail指针算法

~~~java
public class AcAutomation {
    public void buildFailPoint();
}
~~~

### 大文本匹配字符串算法

~~~java
public class AcAutomation {
    public int containWordNum();
    public int containWordKind();
}
~~~
1.在前缀树上没匹配到一个节点,都跟随该节点的fail指针遍历一遍,途径的所有fail节点中,是有效模式串终点的,收集答案,
注意:当前节点保持不变
2.如果匹配到某一节点无法继续向下匹配,当前节点跳转到当前节点的fail节点






  