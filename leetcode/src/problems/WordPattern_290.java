package problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 * 
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 * 
 * @author zhaoye
 */
public class WordPattern_290 {
	public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        char[] chars = pattern.toCharArray();
        StringBuffer patternSb = new StringBuffer();
        Map<String, String> tmpVal = new HashMap<String, String>();
        Set<String> valSet = new HashSet<String>();
        for(int i=0;i<chars.length;i++){
        	if(strs.length-1 < i){
        		return false;
        	}
        	
        	String c = String.valueOf(chars[i]);
        	if(tmpVal.get(c) == null){
        		tmpVal.put(c, strs[i]);
        		valSet.add(strs[i]);
        	}
        	if(i == 0){
        		patternSb.append(tmpVal.get(c));
        	}else{
        		patternSb.append(" ").append(tmpVal.get(c));
        	}
        }
        
        return (tmpVal.size() == valSet.size()) ? patternSb.toString().equals(str) : false;
    }
	
	public static void main(String[] args) {
		WordPattern_290 exam = new WordPattern_290();
		System.out.println(exam.wordPattern("abba", "dog cat cat dog"));
		System.out.println(exam.wordPattern("abba", "dog cat cat fish"));
		System.out.println(exam.wordPattern("aaaa", "dog cat cat dog"));
		System.out.println(exam.wordPattern("abba", "dog dog dog dog"));
		System.out.println(exam.wordPattern("abba", "d"));
	}
}
