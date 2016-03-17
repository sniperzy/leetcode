package problems;

import java.util.Arrays;

/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * 
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 * @author zhaoye
 * @since 2016-3-17
 */
public class ValidAnagram_242 {
    public boolean isAnagram(String s, String t) {
        boolean flag = false;
        
        if(s== null && t == null){
        	flag = true;
        }else if(s != null && t != null && s.length() == t.length()){
        	char[] sArr = s.toCharArray();  
            char[] tArr = t.toCharArray();  
              
            Arrays.sort(sArr);  
            Arrays.sort(tArr);  
              
            return String.valueOf(sArr).equals(String.valueOf(tArr));
        }
        
        return flag;
    }
    
    public static void main(String[] args) {
    	ValidAnagram_242 exam = new ValidAnagram_242();
		System.out.println(exam.isAnagram("anagram", "nagaram"));
		System.out.println(exam.isAnagram("rat", "car"));
	}
}
