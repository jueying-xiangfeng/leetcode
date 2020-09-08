package 栈;

import java.util.Iterator;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 * 
 *
 */

public class _150_逆波兰表达式求值 {
	
    public int evalRPN(String[] tokens) {
    	
    	Stack<Integer> stack = new Stack<Integer>();
    	
    	Integer p1 = 0;
    	Integer p2 = 0;
    	
    	for (String string : tokens) {
			
    		switch (string) {
			case "+":
				p1 = stack.pop();
				p2 = stack.pop();
				stack.push(p2 + p1);
				break;
			case "-":
				p1 = stack.pop();
				p2 = stack.pop();
				stack.push(p2 - p1);
				break;
			case "*":
				p1 = stack.pop();
				p2 = stack.pop();
				stack.push(p2 * p1);
				break;
			case "/":
				p1 = stack.pop();
				p2 = stack.pop();
				stack.push(p2 / p1);
				break;

			default:
				stack.push(Integer.valueOf(string));
				break;
			}
		}
    	return stack.pop();
    }
}
