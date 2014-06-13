/**
 * 
 */
package geometry;

/**
 * 水平线段(行)类
 * 
 * @author 刘晨伟
 * 
 * 创建时间：2007-10-16
 */
public class RowLine extends Line {

	/**
	 * 构造方法
	 * 
	 * @param location1
	 *            线段端点位置1
	 * @param location2
	 *            线段端点位置2
	 */
	public RowLine(Location location1, Location location2) {
		super(location1, location2);
	}

	/**
	 * 构建线段(把线段包含的位置依次加入到locations中)
	 *
	 * @see geometry.Line#createLine(geometry.Location, geometry.Location)
	 */
	@Override
	protected void createLine(Location location1, Location location2) {
		// 列号小的作为起始位置
		if (location1.getColumn() < location2.getColumn()) {
			setStartAndEndLocation(location1, location2);
		} else {
			setStartAndEndLocation(location2, location1);
		}
		// 遍历从起始位置到结束位置的行线上所有的位置
		for (int i = getStart().getColumn(); i <= getEnd().getColumn(); i++) {
			// 构建locations(所有位置的行号相同)
			getLocations().add(new Location(getStart().getRow(), i));
		}
	}

	/**
	 * 判断line是否和行线平行
	 *
	 * @see geometry.Line#isParallelWith(geometry.Line)
	 */
	@Override
	public boolean isParallelWith(Line line) {
		 return isHorizontal(line);
	}

	/**
	 * 判断line是否和行线垂直
	 *
	 * @see geometry.Line#isPlumbUp(geometry.Line)
	 */
	@Override
	public boolean isPlumbUp(Line line) {
		return isVertical(line);
	}
}