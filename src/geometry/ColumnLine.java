/**
 * 
 */
package geometry;

/**
 * 垂直线段(列)类
 * 
 * @author 刘晨伟
 * 
 * 创建时间：2007-10-16
 */
public class ColumnLine extends Line {

	/**
	 * 构造方法
	 * 
	 * @param location1
	 *            线段端点位置1
	 * @param location2
	 *            线段端点位置2
	 */
	public ColumnLine(Location location1, Location location2) {
		super(location1, location2);
	}

	/**
	 * 构建线段(把线段包含的位置都加入到locations中)
	 *
	 * @see geometry.Line#createLine(geometry.Location, geometry.Location)
	 */
	@Override
	protected void createLine(Location location1, Location location2) {
		// 行号小的作为起始位置
		if (location1.getRow() < location2.getRow()) {
			setStartAndEndLocation(location1, location2);
		} else {
			setStartAndEndLocation(location2, location1);
		}
		// 遍历从起始位置到结束位置的列线上所有的位置
		for (int i = getStart().getRow(); i <= getEnd().getRow(); i++) {
			// 构建Locations(所有位置的横坐标相同)
			getLocations().add(new Location(i, getStart().getColumn()));
		}
	}

	/**
	 * 判断line是否和列线平行
	 * 
	 * @see geometry.Line#isParallelWith(geometry.Line)
	 */
	@Override
	public boolean isParallelWith(Line line) {
		return isVertical(line);
	}

	/**
	 * 判断line是否和列线垂直
	 * 
	 * @see geometry.Line#isPlumbUp(geometry.Line)
	 */
	@Override
	public boolean isPlumbUp(Line line) {
		return isHorizontal(line);
	}
}