/**
 * 
 */
package geometry;

/**
 * 位置类
 * 
 * 主要用来存储元素在二维数组中的索引信息
 * 
 * @author 刘晨伟
 * 
 * 创建时间：2007-10-16
 */
public class Location {

	private int row;// 行号
	private int column;// 列号

	public Location() {
		row = 0;
		column = 0;
	}

	public Location(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * 判断两位置是否在同一行
	 * 
	 * @param loaction
	 *            目标位置
	 * @return boolean
	 */
	public boolean isInTheSameRowWith(Location loaction) {
		return this.getRow() == loaction.getRow();
	}

	/**
	 * 判断两位置是否在同一列
	 * 
	 * @param loaction
	 *            目标位置
	 * @return boolean
	 */
	public boolean isInTheSameColumnWith(Location loaction) {
		return this.getColumn() == loaction.getColumn();
	}

	/**
	 * 判断两个位置是否位于同于同一线段上
	 * 
	 * @param loaction
	 *            目标位置
	 * @return boolean
	 */
	public boolean isInTheSameLineWith(Location loaction) {
		return (isInTheSameRowWith(loaction) || isInTheSameColumnWith(loaction));
	}

	/**
	 * 若两个位置的行列相同则表示这两个点是相同的
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Location other = (Location) obj;
		return (this.getRow() == other.getRow() && this.getColumn() == other
				.getColumn());
	}

	/**
	 * 形式为：3行5列
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getRow() + "行" + getColumn() + "列";
	}
	
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}
}