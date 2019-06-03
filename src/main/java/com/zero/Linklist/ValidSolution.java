package com.zero.Linklist;

import java.util.HashMap;
import java.util.Stack;

public class ValidSolution {

    private HashMap<Character, Character> mappings;

    public ValidSolution() {
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put('(', ')');
        this.mappings.put('}', '{');
        this.mappings.put('[', ']');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the current character is a closing bracket.
            if (this.mappings.containsKey(c)) {

                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != this.mappings.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        } // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }
}
