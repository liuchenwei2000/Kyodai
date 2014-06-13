/**
 * 
 */
package geometry;

/**
 * 整数值域类
 * 
 * @author 刘晨伟
 * 
 * 创建时间：2007-10-17
 */
public class ValueField {

	private int start;// 左边界
	private int end;// 右边界

	public ValueField() {
		start = 0;
		end = 0;
	}

	public ValueField(int start, int end) {
		if (start > end)
			throw new IllegalArgumentException("左边界不可大于右边界");
		this.start = start;
		this.end = end;
	}

	/**
	 * 判断valueField是否是当前值域的子集
	 * 
	 * @param valueField
	 *            目标值域
	 * @return boolean
	 */
	public boolean contains(ValueField valueField) {
		if ((this.getStart() <= valueField.getStart())
				&& (this.getEnd() >= valueField.getEnd()))
			return true;
		return false;
	}
	
	/**
	 * 判断两个值域是否有交集
	 * 
	 * @param valueField
	 *            目标值域
	 * @return boolean
	 */
	public boolean hasIntersectionWith(ValueField valueField) {
		// 若this的右边界小于valueField的左边界则不想交
		if (this.getEnd() < valueField.getStart())
			return false;
		// 若valueField的右边界小于this的左边界则不想交
		if (valueField.getEnd() < this.getStart())
			return false;
		return true;
	}
	
	/**
	 * 返回两个值域的交集 
	 * 若没有交集返回null
	 * 
	 * @param valueField
	 *            目标值域
	 * @return ValueField
	 */
	public ValueField getIntersectionWith(ValueField valueField) {
		if (!this.hasIntersectionWith(valueField))
			return null;
		// 确定交集的左边界(两值域左边界较大值)
		int start = Math.max(this.getStart(), valueField.getStart());
		// 确定交集的右边界(两值域右边界较小值)
		int end = Math.min(this.getEnd(), valueField.getEnd());
		return new ValueField(start, end);
	}
	
	/**
	 * 返回形式：[2，7]
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + getStart() + "," + getEnd() + "]";
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}
}