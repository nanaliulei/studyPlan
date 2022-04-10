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

### Z Algorithm

解决字符串你最长公共前缀问题，计算z数组，其中z[i]为以i开始的字符串与原字符串s的最长公共前缀。

**朴素算法**：双重循环，在s的每个位置i，遍历最长公共前缀。复杂度O(N<sup>2</sup>)

根据在z[i]数组的性质，可以不必每次从头进行匹配。

**算法介绍**

1. 记录当前已匹配的最靠右的公共区间[l, r]，初始l=0,r=0;

2. 从i=1开始计算z[i]值；

3. 如果i<=r，i在[l, r]区间内，由于[0, r - l]==[l, r]，因此从i开始，已知可匹配到min(i + z[i - l] - 1, r)，即z[i] = min(z[i - l], r - i + 1)，之后只需从下一位(i + z[i])开始匹配；

4. 如果i>r，则之前没有匹配经验可以使用，直接执行朴素算法，把当前位置和起始位置进行公共前缀查找；

5. 如果当前公共前缀的最右侧位置i+z[i]-1>r，则更新l=i,r=i+z[i]-1;

6. 重复执行2-5，直到i=len(s)

**示例 [2223. 构造字符串的总得分和](https://leetcode-cn.com/problems/sum-of-scores-of-built-strings/)**

```
你需要从空字符串开始 构造 一个长度为 n 的字符串 s ，构造的过程为每次给当前字符串 前面 添加 一个 字符。构造过程中得到的所有字符串编号为 1 到 n ，其中长度为 i 的字符串编号为 si 。

比方说，s = "abaca" ，s1 == "a" ，s2 == "ca" ，s3 == "aca" 依次类推。
si 的 得分 为 si 和 sn 的 最长公共前缀 的长度（注意 s == sn ）。

给你最终的字符串 s ，请你返回每一个 si 的 得分之和 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sum-of-scores-of-built-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

java代码

```
    public long sumScores(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length, l = 0, r = 0;
        int[] z = new int[len];
        long result = len;
        for (int i = 1; i < len; i++) {
            if (i <= r) {
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while (i + z[i] < len && chars[i + z[i]] == chars[z[i]]) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
            result += z[i];
        }
        return result;
    }
```

简化代码

```
    public long sumScores(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length, l = 0, r = 0;
        int[] z = new int[len];
        long result = len;
        for (int i = 1; i < len; i++) {
            z[i] = Math.max(0, Math.min(z[i - l], r - i + 1));
            while (i + z[i] < len && chars[i + z[i]] == chars[z[i]]) {
                l = i;
                r = i + z[i];
                z[i]++;
            }
            result += z[i];
        }
        return result;
    }
```

**时间复杂度分析**

内层while循环更新r最多len次，时间复杂度为O(N)。
