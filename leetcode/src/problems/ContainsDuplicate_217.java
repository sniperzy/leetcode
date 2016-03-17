package problems;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, find if the array contains any duplicates. 
 * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 * @author zhaoye
 * @since 2016-3-17
 */
public class ContainsDuplicate_217 {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> intSet = new HashSet<Integer>();
        for(int i : nums){
        	intSet.add(i);
        }
        
        return nums.length != intSet.size();
    }
    
    public static void main(String[] args) {
    	ContainsDuplicate_217 exam = new ContainsDuplicate_217();
    	System.out.println(exam.containsDuplicate(new int[]{1,2,3,4,4}));
    	System.out.println(exam.containsDuplicate(new int[]{1,2,3,4}));
	}
}
