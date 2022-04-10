# 字符串相关算法

## 字符串匹配算法

简单的字符串匹配问题：存在字符串s和pattern，求pattern在s中出现的第一个位置。即s.indexOf(pattern)

最朴素的想法为两重循环，从前到后计算s以当前位置i开始的字符串是否与pattern匹配，复杂度为O(N<sup>2</sup>)

### KMP

朴素算法存在重复计算，考虑利用pattern字符串本身的特点，当一次匹配失败后，不是返回至起点重新匹配，而是从中间状态开始匹配。

KMP对pattern字符串计算next数组，next[i]的值为子字符串是s[0, i]的后缀子串最长可以匹配到前缀子串的位置，如果没有与前缀子串匹配的后缀子串，则值为-1；

**前缀子串**：所有以0开始，长度小于len(s)的子串为s的前缀子串；

**后缀子串**：所有以len(s) - 1结束，长度小于len(s)的子串为s的后缀子串；

**next[i]**: 以0开始，以i结束的字符串si的最长匹配前后缀子串中的前缀子串的结束位置。如aabaa最长的匹配的前后缀子串为aa，其中前缀子串结束位置为1。

当进行字符串匹配时，如果字符串s的i位当前匹配到pattern的j位，当匹配i+1和j+1位时，发现不匹配，可以通过next数组向前回退，将i+1位于next[j] + 1位开始进行匹配。原理为当s的i位与pattern的j位匹配时，s的i位同样与pattern的next[j]位匹配。

例如s为"aabaabaac", pattern为"aabaac"，s的4的位置可以匹配到pattern的4的位置，但是s的5位置的b与pattern的5位置的c不匹配，此时pattern的next[4] = 1（aabaa最长匹配前后缀子串中，前缀结束位置为1），s中4的位置，同样能匹配到pattern中1的位置，因此可以从s中5的位置与pattern中2的位置开始继续进行匹配。

KMP算法主要分为两部分，首先计算next数组，之后通过next数组进行匹配。

#### 示例力扣 [28. 实现 strStr()](https://leetcode-cn.com/problems/implement-strstr/)

```
    public int strStr(String haystack, String needle) {
        char[] chars1 = haystack.toCharArray(), chars2 = needle.toCharArray();
        int len1 = chars1.length, len2 = chars2.length, pos = -1;
        int[] next = getNext(chars2);
        for (int i = 0; i < len1; i++) {
            while (pos > -1 && chars1[i] != chars2[pos + 1]) {
                pos = next[pos];
            }
            if (chars1[i] == chars2[pos + 1] && ++pos == len2 - 1) {
                return i - pos;
            }
        }
        return -1;
    }

    private int[] getNext(char[] chars) {
        int len = chars.length, pos = -1;
        int[] next = new int[len];
        next[0] = -1;
        for (int i = 1; i < len; i++) {
            while (pos > -1 && chars[i] != chars[pos + 1]) {
                pos = next[pos];
            }
            if (chars[i] == chars[pos + 1]) {
                pos++;
            }
            next[i] = pos;
        }
        return next;
    }
```
