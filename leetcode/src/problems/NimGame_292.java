package problems;

/**
 * һ�ο��Լ������max�������������Ӯ����ʲô�����¿��ܻ�Ӯ
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
