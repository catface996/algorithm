# KMP总结

## 概念
### 字符串中i位置前的字符串的最长且相等的前缀和后缀
 前缀和后缀的长度均小于原始字符串的长度

字符串abcabck,i=6,即在字符串中k位置的信息是3.
abcabc
前缀长度为1,后缀长度为1时 a<>c
前缀长度为2,后缀长度为2时, ab<>bc
前缀长度为3,后缀长度为3时, abc==abc
前缀长度为4,后缀长度为4时, abca <> cabc
前缀长度为5,后缀长度为5时, abcab <> bcabc

## next数组
求字符串中每一个位置的字符最长且相等的前缀和后缀的长度最大值

1) 默认next[0] = -1
2) 求每个字符的最长相等前缀和后缀

abcabck的next数组为: [-1, 0, 0, 0, 1, 2, 3]

## 快速求解next数组

1) cn位置和i位置的字符相等,next数组中i位置的值为[cn+1]

2) cn位置和i位置的字符不相等且cn>0

* 那么相等且最长的前后缀的长度必然小于cn,与加上'K'后的后缀相等的前缀,必然出现在'K'位置之前的字符串中的最长前缀中,或者不存在.如下图,next[7]=3,当i=16时,i-1位置的K与cn位置的M不相等,cn=next[cn] ,cn变为3,判断3位置的'x'是否与i-1位置的'K'相等,如果相等,则next[i] =cn+1,即next[16]=4,不相等,继续cn = next[cn];

  换钟理解方法:如果i-1位置的'K'与cn位置的'M'不相等,则i-1位置的'K'字符之前必定存在若干字符与cn位置的'M'字符前的若干字符相同,在如下图的样例中为'abcxabc',此时需要判断加上'K'之后的后缀,与前缀中的某个前缀要相同,既要判断'M'字符前的子串中,相同且最长的前缀和后缀,判断'K'是否与前缀中的最长前缀是否相同.

3) cn位置和i位置字符不相等且cn<=0

​	cn已经来到字符串头部,next[i] =0;

![image-20210506195749243](https://tva1.sinaimg.cn/large/008i3skNly1gq8ygcqnjcj31ma0jkq8s.jpg)

<center><font size=5>图1</font></center>

![image-20210506201954349](https://tva1.sinaimg.cn/large/008i3skNly1gq8z3bpeqsj32400l8q9g.jpg)

<center><font size=5>图2</font></center>


## str1中匹配str2
str1 = "abcabcabckf"
str2 = "abcabck"
str2的next数组: [-1, 0, 0, 0, 1, 2, 3]
i位置代表目前在str1中匹配到了哪个位置
j位置代表目前在str2中匹配到了哪个位置

步骤如下: 初始状态: i=0;j=0;
1) i=0;j=0; str1[i]==str2[j] (a==a) i++;j++
2) i=1;j=1; str1[i]==str2[j] (b==b) i++;j++;
3) ...
4) i=5;j=5; str1[i]==str2[j] (c==c) i++;j++;
5) i=6;j=6; str1[i]<>str2[j] (a<>k) j=next[j]
6) i=6;j=3; str1[i]==str2[j] (a==a) i++;j++;
7) ... 
8) i=9;j=6 str1[i]==str2[j] (k==k) 且str2遍历结束 返回 i-j 即str1中首次出现str2的开始位置



