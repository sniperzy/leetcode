package problems;

public class ExcelSheetColumn_168_171 {
	
	/**
	 * For example
	 * 
	 * 1 -> A
	 * 2 -> B
	 * 3 -> C
	 * ...
	 * 26 -> Z
	 * 27 -> AA
	 * 28 -> AB 
	 * 
	 * @param n
	 * @return
	 */
	public String convertToTitle(int n) {
        String result = "";
        
        while(n > 0){
        	result = (char)((n - 1) % 26 + getAsc("A")) + result;
        	n = (n - 1) / 26;
        }
        
        return result;
    }
	
	/**
	 * For example
	 * 
	 * A -> 1
	 * B -> 2
	 * C -> 3
	 * ...
	 * Z -> 26
	 * AA -> 27
	 * AB -> 28 
	 * 
	 * @param n
	 * @return
	 */
    public int titleToNumber(String s) {
    	int result = 0;
    	
    	byte[] gc = s.getBytes();
    	int i;
    	int ai = getAsc("A");
    	for(i=-0;i<gc.length;i++){
    		int c = (int)gc[i] - ai + 1;
    		result = c + 26 * result;
    	}
        
        return result;
    }
    
    /**
     * 字符转ASC
     * @param st
     * @return
     */
    public int getAsc(String st) {
        byte[] gc = st.getBytes();
        int ascNum = (int) gc[0];
        return ascNum;
    }
    
    public static void main(String[] args) {
    	ExcelSheetColumn_168_171 exam = new ExcelSheetColumn_168_171();
		System.out.println(exam.convertToTitle(998));
		System.out.println(exam.titleToNumber("ALJ"));
	}
}
