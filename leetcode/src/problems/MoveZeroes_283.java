package problems;

/**
 * given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0]
 * You must do this in-place without making a copy of the array.
 * 
 * @author zhaoye
 * @since 2016-3-4
 */
public class MoveZeroes_283 {
	public void moveZeroes(int[] nums) {
		int firstIndex = -1;
        for(int i=0;i<nums.length;i++){
        	int n = nums[i];
        	if(n == 0 && firstIndex == -1){
        		firstIndex = i;
        	}else if(firstIndex > -1){
        		nums[firstIndex] = n;
        		nums[i] = 0;
        		for(int j=0;i<nums.length;j++){
        			if(nums[j] == 0){
        				firstIndex = j;
        				break;
        			}
        		}
        	}
        }
    }
	
	public static void main(String[] args) {
		MoveZeroes_283 exam = new MoveZeroes_283();
		int[] ints = new int[]{0, 1, 0, 3, 12};
		exam.moveZeroes(ints);
		
		for(int i : ints){
			System.out.print(i + ",");
		}
	}
}
