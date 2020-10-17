import java.util.Scanner;
import java.util.Stack;

/**
 * LeetCode 844. Backspace String Compare
 * https://leetcode.com/problems/backspace-string-compare/
 */
public class Solution {

    /**
     * Process backspace(s) in a string.
     * Using strings.
     * Execution O(n)
     */
    static String processStr(String str) {

        // **** initialization ****
        StringBuilder sb = new StringBuilder();

        // **** traverse the input string ****
        for (int i = 0; i < str.length(); i++) {

            // **** get character ****
            char ch = str.charAt(i);

            // **** process ch ****
            if (ch == '#') {
                if (sb.length() > 0)
                    sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(ch);
            }     
        }

        // **** return final string ****
        return sb.toString();
    }


    /**
     * Process using strings.
     * Execution O(2 * n)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 37.2 MB, less than 9.86% of Java online submissions.
     */
    static boolean backspaceCompare(String S, String T) {
        
        // **** sanity check(s) ****
        if (S.equals(T))
            return true;

        // **** compare strings ****
        return processStr(S).equals(processStr(T));
    }


    /**
     * Process using stacks.
     * Execution O(2 * n)
     * Runtime: 2 ms, faster than 42.83% of Java online submissions.
     * Memory Usage: 37.4 MB, less than 9.73% of Java online submissions.
     */
    static boolean backspaceCompare0(String S, String T) {

        // **** initiatization ****
        Stack<Character> sStack = new Stack<Character>();
        Stack<Character> tStack = new Stack<Character>();

        // **** process S using a stack ****
        for (char ch : S.toCharArray()) {
            if (ch != '#')
                sStack.push(ch);
            else if (!sStack.isEmpty())
                sStack.pop();
        }

        // **** process T using a stack ****
        for (char ch : T.toCharArray()) {
            if (ch != '#') 
                tStack.push(ch);
            else if (!tStack.isEmpty())
                tStack.pop();
        }

        // **** compare contents of stacks ****
        return sStack.equals(tStack);
    }


    /**
     * Execution O(n)
     * Time Limit Exceeded!!!
     */
    static boolean backspaceCompare1(String S, String T) {

        // **** perform sanity checks ****
        if (S == null && T == null)
            return true;
        else if (S == null || T == null)
            return false;
        else if (S.length() == 0 && T.length() == 0)
            return true;
        else if (S.length() == 0 || T.length() == 0)
            return false;

        // **** initialization ****
        int s       = S.length() - 1;
        int t       = T.length() - 1;
        int delete  = 0;

        // **** traverse the strings backwards ****
        for ( ; s >= 0 || t >= 0; ) {

            // **** count the '#' characters (if any) ****
            for (delete = 0; s >= 0 && S.charAt(s) == '#'; delete++, s--);

            // **** delete associated characters (if needed) ****
            for ( ; delete > 0 && s >= 0; s--, delete--) {
                if (S.charAt(s) == '#') {
                    delete++; s--;
                }
            }

            // **** count the '#' characters (if any) ****
            for (delete = 0; t >= 0 && T.charAt(t) == '#'; delete++, t--);

            // **** delete associated characters (if needed) ****
            for ( ; delete > 0 && t >= 0; t--, delete--) {
                if (T.charAt(t) == '#') {
                    delete++; t--;
                }
            }

            // **** delete matching characters ****
            while (s >= 0 && t >= 0 && S.charAt(s) == T.charAt(t) && S.charAt(s) != '#') {
                s--; t--;
            }

            // **** check if done ****
            if (s >= 0 && t >= 0 && 
                S.charAt(s) != T.charAt(t) && 
                S.charAt(s) != '#' && T.charAt(t) != '#')
                return false;
        }

        // **** ****
        return (s == t);
    }


    /**
     * Test scaffolding
     */
    public static void main(String[] args) {

        // **** open scanner ****
        Scanner sc = new Scanner(System.in);

        // **** read S ****
        String S = sc.nextLine().trim();

        // **** read T ****
        String T = sc.nextLine().trim();

        // *** close scanner ****
        sc.close();

        // ???? ????
        System.out.print("main <<<      ");
        for (int i = 0; i < S.length(); i++)
            System.out.print("" + i);
        System.out.println();

        System.out.println("main <<< S ==>" + S + "<==");
        System.out.println("main <<< T ==>" + T + "<==");

        System.out.print("main <<<      ");
        for (int i = 0; i < T.length(); i++)
            System.out.print("" + i);
        System.out.println();

        // **** process and display result ****
        System.out.println("main <<< backspaceCompare0: " + backspaceCompare0(S, T));

        // **** process and display result ****
        System.out.println("main <<< backspaceCompare1: " + backspaceCompare1(S, T));

        // **** process and display result ****
        System.out.println("main <<<  backspaceCompare: " + backspaceCompare(S, T));
    }
}