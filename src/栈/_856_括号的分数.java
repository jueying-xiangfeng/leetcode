package 栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/score-of-parentheses/
 * 
 *给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：

() 得 1 分。
AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
(A) 得 2 * A 分，其中 A 是平衡括号字符串。
 

示例 1：

输入： "()"
输出： 1
示例 2：

输入： "(())"
输出： 2
示例 3：

输入： "()()"
输出： 2
示例 4：

输入： "(()(()))"
输出： 6
 

提示：

S 是平衡括号字符串，且只含有 ( 和 ) 。
2 <= S.length <= 50

 */
public class _856_括号的分数 {
	
    public int scoreOfParentheses(String S) {
    	
    	Stack<Integer> stack = new Stack<Integer>();
    	int len = S.length();
    	for (int i = 0; i < len; i++) {
			char c = S.charAt(i);
			
			if (c == '(') {
				stack.push(0); // 使用 0 模拟 '('
			} else {
				// 此时左右括号匹配 得1分
				if (stack.peek() == 0) {
					stack.pop();
					stack.push(1);
				} else {
					int score = 0;
					while (stack.peek() != 0) {
						score += stack.pop();
					}
					stack.pop();
					stack.push(score*2);
				}
			}
		}
    	int result = 0;
    	while (!stack.isEmpty()) {
			result += stack.pop();
		}
    	return result;
    }
}
