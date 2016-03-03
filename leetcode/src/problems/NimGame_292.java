package problems;

/**
 * 一次可以减掉最大max个，最后减完的人赢，问什么总数下可能会赢
 * @author zhaoye
 *
 */
public class NimGame_292 {
	public boolean canWinNim(int n) {
        return canWinNim(n, 3);
    }
	
	public boolean canWinNim(int n, int max)
	{
	    return n % (max+1) != 0;
	}
	
	public static void main(String[] args) {
		int num = 4;
		NimGame_292 exam = new NimGame_292();
		boolean flag = exam.canWinNim(num);
		System.out.println(flag);
	}
}
