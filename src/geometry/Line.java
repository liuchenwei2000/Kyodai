/**
 * 
 */
package geometry;

import java.util.ArrayList;

/**
 * 线段类
 * (只包含有限个点且行列号都是整数)
 * 
 * @author 刘晨伟
 * 
 * 创建时间：2007-10-16
 */
public abstract class Line {

	private ArrayList<Location> locations;// 用来存储构成线段的位置
	private Location start;// 线段起始位置
	private Location end;// 线段结束位置

	/**
	 * 构造方法
	 * 
	 * @param location1
	 *            线段端点位置1
	 * @param location2
	 *            线段端点位置2
	 */
	public Line(Location location1, Location location2) {
		createLine(location1, location2);
	}

	/**
	 * 实际创建线段的方法,需完成对locations的构建 
	 * (子类需要实现这个方法)
	 * 
	 * @param location1
	 *            线段端点位置1
	 * @param location2
	 *            线段端点位置2
	 */
	protected abstract void createLine(Location location1, Location location2);

	/**
	 * 设置起始位置和结束位置
	 * 
	 * @param start
	 *            起始位置
	 * @param end
	 *            结束位置
	 */
	protected void setStartAndEndLocation(Location start, Location end) {
		setStart(start);
		setEnd(end);
	}

	/**
	 * 判断两条线段是否平行
	 * 
	 * @param line
	 *            目标线段
	 * @return boolean
	 */
	public abstract boolean isParallelWith(Line line);

	/**
	 * 判断两条线段是否垂直
	 * 
	 * @param line
	 *            目标线段
	 * @return boolean
	 */
	public abstract boolean isPlumbUp(Line line);

	/**
	 * 判断两条线段是否相交
	 * 
	 * @param line
	 *            目标线段
	 * @return boolean
	 */
	public boolean isIntersectantWith(Line line) {
		// 根据是否有交点进行判断
		return getPointOfIntersection(line) != null;
	}

	/**
	 * 返回两条线段的交点
	 * 
	 * @param line
	 *            目标线段
	 * @return Location
	 */
	public Location getPointOfIntersection(Line line) {
		if (line == null)
			return null;
		// 对两个线段的位置列表求交集(若交集中有元素则是交点)
		this.getLocations().retainAll(line.getLocations());
		// 没有交点
		if (this.getLocations().size() == 0)
			return null;
		else
			return this.getLocations().get(0);
	}
	
	/**
	 * 返回线段的行号值域
	 * 
	 * @return ValueField
	 */
	public final ValueField getRowField() {
		return new ValueField(this.getStart().getRow(), this.getEnd().getRow());
	}

	/**
	 * 返回线段的列号值域
	 * 
	 * @return ValueField
	 */
	public final ValueField getColumnField() {
		return new ValueField(this.getStart().getColumn(), this.getEnd()
				.getColumn());
	}

	/**
	 * 判断线段是否是水平的
	 * 
	 * @param line
	 *            目标线段
	 * @return boolean
	 */
	public static final boolean isHorizontal(Line line) {
		if (line == null)
			throw new IllegalArgumentException("线段不可为空");
		// 线段两端位置的行号相等则为水平的
		if (line.getStart().getRow() == line.getEnd().getRow())
			return true;
		return false;
	}

	/**
	 * 判断线段是否是垂直的
	 * 
	 * @param line
	 *            目标线段
	 * @return boolean
	 */
	public static final boolean isVertical(Line line) {
		if (line == null)
			throw new IllegalArgumentException("线段不可为空");
		// 线段两端位置的列号相等则为垂直的
		if (line.getStart().getColumn() == line.getEnd().getColumn())
			return true;
		return false;
	}
	
	/**
	 * 返回形式：[2行3列,3行4列,4行5列]
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getLocations().toString();
	}

	/**
	 * 线段的起始位置和结束位置相同则说明线段重合
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Line other = (Line) obj;
		return ((this.getStart().equals(other.getStart())) && (this.getEnd()
				.equals(other.getEnd())));
	}

	/**
	 * @return the locations
	 */
	public ArrayList<Location> getLocations() {
		if (locations == null)
			locations = new ArrayList<Location>();
		return locations;
	}

	/**
	 * @return the start
	 */
	public Location getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(Location start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Location getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(Location end) {
		this.end = end;
	}
}