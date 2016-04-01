package entity;

import java.util.ArrayList;
import java.util.List;

import utils.StringUtils;

/**
 * 幻方实体
 * @author zhaoye
 * @since 2016-4-1
 */
public class SquareEntity {
	public final int INIT_VALUE = 0;
	private List<List<Integer>> data;
	private int rank;
	
	/**
	 * 幻方实体构建
	 * @param rank	阶数
	 */
	public SquareEntity(int rank) {
		super();
		this.rank = rank;
		init();
	}

	/**
	 * n阶幻方初始化
	 */
	public void init(){
		data = new ArrayList<List<Integer>>();
		int i,j;
		for(i=0;i<rank;i++){
			List<Integer> row = new ArrayList<>();
			for(j=0;j<rank;j++){
				row.add(INIT_VALUE);
			}
			data.add(row);
		}
	}
	
	/**
	 * 获取行
	 * @param index	下标从0开始
	 * @return
	 */
	public List<Integer> getRow(int index){
		return index < rank ? data.get(index) : null;
	}
	
	/**
	 * 获取值
	 * @param rowIndex	行下标，从0开始
	 * @param columnIndex	列下标，从0开始
	 * @return
	 */
	public int getValue(int rowIndex, int columnIndex){
		return (rowIndex < rank && columnIndex < rank) ? data.get(rowIndex).get(columnIndex) : null;
	} 
	
	/**
	 * 设置值
	 * @param rowIndex	行下标，从0开始
	 * @param columnIndex	列下标，从0开始
	 * @param value	值
	 */
	public void setValue(int rowIndex, int columnIndex, int value){
		if(rowIndex < rank && columnIndex < rank){
			data.get(rowIndex).set(columnIndex, value);
		}
	}
	
	/**
	 * 是否还有空缺
	 * @return
	 */
	public boolean hasBlank(){
		int i,j;
		for(i=0;i<data.size();i++){
			List<Integer> row = data.get(i);
			for(j=0;j<row.size();j++){
				if(row.get(j) == INIT_VALUE){//存在取默认值的数
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		if(rank > 0){
			int i,j;
			for(i=0;i<data.size();i++){
				List<Integer> row = data.get(i);
				for(j=0;j<row.size();j++){
					sb.append(
							StringUtils.formatCodeStr(
									String.valueOf(row.get(j)), " ",
									String.valueOf(rank * rank).length(), true))
							.append(" ");
				}
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}

	public int getRank() {
		return rank;
	}

}
