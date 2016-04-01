package problems;

import entity.SquareEntity;

/**
 * 幻方构造方法
 * @author zhaoye
 * @since 2016-4-1
 */
public class HuanfangSolution {
	/**
	 * 奇幻方
	 */
	private final int TYPE_ODD = 0;
	
	/**
	 * 单偶幻方
	 */
	private final int TYPE_SINGLE_EVEN = 1;
	
	/**
	 * 双偶幻方
	 */
	private final int TYPE_DOUBLE_EVEN = 2;
	
	/**
	 * 构造幻方
	 * @param rank	阶数
	 * @return
	 */
	public SquareEntity buildSquare(int rank){
		SquareEntity entity = new SquareEntity(rank);
		
		//判断幻方类型
		int type = jugeRank(rank);
		
		//构造幻方
		switch (type) {
		case TYPE_ODD:
			buildOddSquare(entity);
			break;
			
		case TYPE_SINGLE_EVEN:
			buildSingleEvenSquare(entity);
			break;
			
		case TYPE_DOUBLE_EVEN:
			buildDoubleEvenSquare(entity);
			break;
		}
		
		return entity;
	}
	
	/**
	 * 判断幻方类型
	 * @param rank	阶数
	 * @return	类型
	 */
	private int jugeRank(int rank){
		int type = -1;
		
		if(rank % 2 != 0){
			type = TYPE_ODD;
		}else if(rank % 4 == 0){
			type = TYPE_DOUBLE_EVEN;
		}else{
			type = TYPE_SINGLE_EVEN;
		}
		
		return type;
	}
	
	/**
	 * 构造奇幻方
	 * @param entity
	 */
	private int buildOddSquare(SquareEntity entity){
		return buildOddSquare(entity, 1, 0, 0, entity.getRank() - 1, entity.getRank() - 1);
	}
	
	/**
	 * 构造奇幻方
	 * @param entity
	 * @param minValue
	 * @param beginRow
	 * @param beginCol
	 * @param endRow
	 * @param endCol
	 */
	private int buildOddSquare(SquareEntity entity, int minValue, int beginRow, int beginCol, int endRow, int endCol){
		// 罗伯特构建法：
		// 把1（或最小的数）放在第一行正中； 按以下规律排列剩下的(n×n－1)个数： 
		// 1、每一个数放在前一个数的右上一格； 
		// 2、如果这个数所要放的格已经超出了顶行那么就把它放在底行，仍然要放在右一列； 
		// 3、如果这个数所要放的格已经超出了最右列那么就把它放在最左列，仍然要放在上一行； 
		// 4、如果这个数所要放的格已经超出了顶行且超出了最右列，那么就把它放在前一个数的下一行同一列的格内； 
		// 5、如果这个数所要放的格已经有数填入，那么就把它放在前一个数的下一行同一列的格内。
		
		//获取阶数
		int rank = endCol - beginCol + 1;
		
		//从第一行中间开始填，第一个填最小值
		int rowIndex = beginRow;
		int columnIndex = beginCol + rank/2;
		int value = minValue;
		entity.setValue(rowIndex, columnIndex, value);
		
		//依次向下填
		while(value < minValue + rank * rank - 1){
			//值递增
			value++;
			
			//行变化
			int oldRowIndex = rowIndex;
			if(rowIndex != beginRow){
				rowIndex--;
			}else if(columnIndex == endCol){
				rowIndex++;
			}else{
				rowIndex = endRow;
			}
			
			//列变化
			if(columnIndex != endCol){
				columnIndex++;
			}else if(oldRowIndex != beginRow){
				columnIndex = beginCol;
			}
			
			//如果有值
			if(entity.getValue(rowIndex, columnIndex) != entity.INIT_VALUE){
				rowIndex = rowIndex + 2;
				columnIndex--;
			}
			
			//填值
			entity.setValue(rowIndex, columnIndex, value);
		}
		return value;
	}
	
	/**
	 * 构造双偶幻方
	 * @param entity
	 */
	private void buildDoubleEvenSquare(SquareEntity entity){
		// 对称交换法
		// 1.把数字按顺序填写
		// 2.按4×4把幻方划分成小方阵
		// 3.把每个小方阵的对角线数字换成互补(两个数的和等于幻方中最大的数与 1 的和)的数字
		
		//获取阶数
		int rank = entity.getRank();
		
		//遍历幻方
		int i,j;
		for(i=0;i<rank;i++){
			for(j=0;j<rank;j++){
				//顺序填写的值
				int value = i*rank + (j+1);
				
				//判断是否为小方阵对角线的位置
				//定位在小方阵中的坐标
				int row = i%4;
				int col = j%4;
				if(row == col || row + col == 3){//是否位于两条对角线上
					//替换为互补值
					value = rank*rank + 1 - value;
				}
				
				//输入值
				entity.setValue(i, j, value);
			}
		}
		
	}
	
	/**
	 * 构造单偶幻方
	 * @param entity
	 */
	private void buildSingleEvenSquare(SquareEntity entity){
		// 象限对称交换法
		// 计算k值，满足 4 * k + 2 = rank
		// 1.把方阵分为A，B，C，D四个象限，按A-D-B-C顺序使用罗伯法填数；
		// 2.在A象限的中间行、中间格开始，按自左向右的方向，标出k格。A象限的其它行则标出最左边的k格。将这些格，和C象限相对位置上的数，互换位置；
		// 3.在B象限任一行的中间格，自右向左，标出k-1列。 将B象限标出的这些数，和D象限相对位置上的数进行交换，就形成幻方。
		
		//获取阶数
		int rank = entity.getRank();
		
		//计算k值
		int k = (rank-2)/4;
		
		//计算象限分界点
		int splitIndex = rank / 2 - 1;
		
		//罗伯特法填数
		//A象限
		int minValue = buildOddSquare(entity, 1, 0, 0, splitIndex, splitIndex);
		//D象限
		minValue = buildOddSquare(entity, minValue + 1, splitIndex + 1, splitIndex + 1, rank - 1, rank - 1);
		//B象限
		minValue = buildOddSquare(entity, minValue + 1, 0, splitIndex + 1, splitIndex, rank - 1);
		//C象限
		minValue = buildOddSquare(entity, minValue + 1, splitIndex + 1, 0, rank - 1, splitIndex);
		
		//AC象限交换
		int i,j;
		for(i=0;i<rank;i++){
			for(j=0;j<rank/2;j++){
				if(i < rank/2){//遍历A象限
					if(j < rank/2/2 && (i != rank/2/2) 
							|| i == rank/2/2 && j >= rank/2/2 && j <= rank/2/2 + k){
						//交换AC象限值
						int val = entity.getValue(i, j);
						entity.setValue(i, j, entity.getValue(i + rank/2, j));
						entity.setValue(i + rank/2, j, val);
					}
				}
			}
		}
		
		//BD象限交换
		for(i=0;i<rank;i++){
			for(j=rank/2 + rank/2/2 + 1 - (k-1);j<rank/2 + rank/2/2 + 1;j++){
				if(i < rank/2){//遍历B象限
					//交换BD象限值
					int val = entity.getValue(i, j);
					entity.setValue(i, j, entity.getValue(i + rank/2, j));
					entity.setValue(i + rank/2, j, val);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int rank = 18;
		HuanfangSolution solution = new HuanfangSolution();
		System.out.println(solution.buildSquare(rank));
	}
}
