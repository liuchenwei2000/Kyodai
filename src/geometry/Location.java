/**
 * 
 */
package geometry;

/**
 * λ����
 * 
 * ��Ҫ�����洢Ԫ���ڶ�ά�����е�������Ϣ
 * 
 * @author ����ΰ
 * 
 * ����ʱ�䣺2007-10-16
 */
public class Location {

	private int row;// �к�
	private int column;// �к�

	public Location() {
		row = 0;
		column = 0;
	}

	public Location(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * �ж���λ���Ƿ���ͬһ��
	 * 
	 * @param loaction
	 *            Ŀ��λ��
	 * @return boolean
	 */
	public boolean isInTheSameRowWith(Location loaction) {
		return this.getRow() == loaction.getRow();
	}

	/**
	 * �ж���λ���Ƿ���ͬһ��
	 * 
	 * @param loaction
	 *            Ŀ��λ��
	 * @return boolean
	 */
	public boolean isInTheSameColumnWith(Location loaction) {
		return this.getColumn() == loaction.getColumn();
	}

	/**
	 * �ж�����λ���Ƿ�λ��ͬ��ͬһ�߶���
	 * 
	 * @param loaction
	 *            Ŀ��λ��
	 * @return boolean
	 */
	public boolean isInTheSameLineWith(Location loaction) {
		return (isInTheSameRowWith(loaction) || isInTheSameColumnWith(loaction));
	}

	/**
	 * ������λ�õ�������ͬ���ʾ������������ͬ��
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
	 * ��ʽΪ��3��5��
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getRow() + "��" + getColumn() + "��";
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