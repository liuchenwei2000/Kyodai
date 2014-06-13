/**
 * 
 */
package arithmetic;

import panel.KyodaiPanel;
import util.other.ParameterChecker;
import util.resource.LineDrawer;
import geometry.ColumnLine;
import geometry.Line;
import geometry.Location;
import geometry.RowLine;
import geometry.ValueField;

/**
 * 连连看按钮可消性检查类 
 * (若可消还将绘制可消路线)
 * 
 * @author 刘晨伟
 *
 * 创建时间：2007-10-17
 */
public class RemovableChecker {

	private LineDrawer drawer;// 线段绘制器对象

	public RemovableChecker(KyodaiPanel kyodaiPanel) {
		drawer = new LineDrawer(kyodaiPanel);
	}

	/**
	 * 判断两个位置在二维数组中是否是可消的 若可消同时在界面上绘制消去路线
	 * 
	 * @param ary
	 *            目标二维数组
	 * @param a
	 *            位置a
	 * @param b
	 *            位置b
	 * @return boolean
	 */
	public boolean isRemovable(int[][] ary, Location a, Location b) {
		/** 两位置在同一直线上可连通 */
		if (isConnectedInTheSameLine(ary, a, b)) {
			drawer.drawRemovablePathAndUpdateUI(a, b);
			return true;
		}
		/** 两位置所在的行列线段相交 */
		// 位置a所在的行线段
		Line rowLineA = getRowLineContainsLocation(ary, a);
		// 位置b所在的行线段
		Line rowLineB = getRowLineContainsLocation(ary, b);
		// 位置a所在的列线段
		Line columnLineA = getColumnLineContainsLocation(ary, a);
		// 位置b所在的列线段
		Line columnLineB = getColumnLineContainsLocation(ary, b);

		Location intersection = null;// 线段交点
		// a位置所在的行线段和b位置所在的列线段相交
		if (rowLineA.isIntersectantWith(columnLineB)) {
			intersection = rowLineA.getPointOfIntersection(columnLineB);
			drawer.drawRemovablePathAndUpdateUI(a, intersection, b);
			return true;
		}
		// a位置所在的列线段和b位置所在的行线段相交
		if (columnLineA.isIntersectantWith(rowLineB)) {
			intersection = columnLineA.getPointOfIntersection(rowLineB);
			drawer.drawRemovablePathAndUpdateUI(a, intersection, b);
			return true;
		}
		/** 存在一条线段垂直相交于两位置所在的行列平行线 */
		Location[] intersections = null;// 线段交点数组
		// 行线段交点数组
		intersections = getIntersectionsForRowLines(ary, rowLineA, rowLineB);
		// 存在一条线段垂直相交于a,b所在的的行线段
		if (intersections != null) {
			drawer.drawRemovablePathAndUpdateUI(a, intersections[0],
					intersections[1], b);
			return true;
		}
		// 列线段交点数组
		intersections = getIntersectionsForColumnLines(ary, columnLineA,
				columnLineB);
		// 存在一条线段垂直相交于a,b所在的的列线段
		if (intersections != null) {
			drawer.drawRemovablePathAndUpdateUI(a, intersections[0],
					intersections[1], b);
			return true;
		}
		return false;
	}

	/**
	 * 判断两个位置在数组中是否在同一直线上可连通
	 * 
	 * @param ary
	 *            目标二维数组
	 * @param a
	 *            位置a
	 * @param b
	 *            位置b
	 * @return boolean
	 */
	private boolean isConnectedInTheSameLine(int[][] ary, Location a, Location b) {
		ParameterChecker.check2DArray(ary);
		if (a.isInTheSameLineWith(b))
			return (isConnectedInRow(ary, a, b) || isConnectedInColumn(ary, a,
					b));
		return false;
	}

	/**
	 * 判断两位置在同一行上是否是可连通的
	 * 
	 * @param ary
	 *            目标二维数组
	 * @param a
	 *            位置a
	 * @param b
	 *            位置b
	 * @return boolean
	 */
	private boolean isConnectedInRow(int[][] ary, Location a, Location b) {
		ParameterChecker.check2DArray(ary);
		if (!a.isInTheSameRowWith(b))
			return false;
		Line line = new RowLine(a, b);
		int startColumn = line.getStart().getColumn();// 起始位置的列号
		int endColumn = line.getEnd().getColumn();// 结束位置的列号
		// 遍历从起始位置到结束位置的行线上所有的位置(不包括两个端点)
		for (int i = startColumn + 1; i < endColumn; i++) {
			// 若行线上存在某位置不为0则两位置在行线上不连通
			if (ary[a.getRow()][i] != 0)
				return false;
		}
		return true;
	}

	/**
	 * 判断两位置在同一列上是否是可连通的
	 * 
	 * @param ary
	 *            目标二维数组
	 * @param a
	 *            位置a
	 * @param b
	 *            位置b
	 * @return boolean
	 */
	private boolean isConnectedInColumn(int[][] ary, Location a, Location b) {
		ParameterChecker.check2DArray(ary);
		if (!a.isInTheSameColumnWith(b))
			return false;
		Line line = new ColumnLine(a, b);
		int startRow = line.getStart().getRow();// 起始位置的行号
		int endRow = line.getEnd().getRow();// 结束位置的行号
		// 遍历从起始位置到结束位置的列线上所有的位置(不包括两个端点)
		for (int i = startRow + 1; i < endRow; i++) {
			// 若列线上存在某位置不为0则两位置在列线上不连通
			if (ary[i][a.getColumn()] != 0)
				return false;
		}
		return true;
	}

	/**
	 * 返回包含位置location的水平线段(形式如：00000location000) 
	 * 也就是由location的水平邻接位置(值为0的)组成的线段
	 * 若location左右都是非0位置则返回只包括自身的单点行线段
	 * 
	 * @param the2DArray
	 *            目标二维数组
	 * @param location
	 *            目标位置
	 * @return Line
	 */
	private Line getRowLineContainsLocation(int[][] the2DArray,
			Location location) {
		// 寻找行线段的左端位置
		Location left = getLeftPort(the2DArray, location);
		// 寻找行线段的右端位置
		Location right = getRightPort(the2DArray, location);
		return new RowLine(left, right);
	}

	/**
	 * 返回包含位置location的垂直线段 
	 * 也就是由location的垂直邻接位置(值为0的)组成的线段
	 * 若location上下都是非0位置则返回只包括自身的单点列线段
	 * 
	 * @param the2DArray
	 *            目标二维数组
	 * @param location
	 *            目标位置
	 * @return Line
	 */
	private Line getColumnLineContainsLocation(int[][] the2DArray,
			Location location) {
		// 寻找列线段的上端位置
		Location up = getUpPort(the2DArray, location);
		// 寻找列线段的下端位置
		Location down = getDownPort(the2DArray, location);
		return new ColumnLine(up, down);
	}

	/**
	 * 返回location所在的行线段的左端位置 
	 * 若没有左端位置则返回location
	 * 
	 * @param the2DArray
	 *            目标二维数组
	 * @param location
	 *            目标位置
	 * @return Location
	 */
	private Location getLeftPort(int[][] the2DArray, Location location) {
		Location temp = new Location(location.getRow(), location.getColumn());
		for (int i = location.getColumn() - 1; i >= 0; i--) {
			if (the2DArray[location.getRow()][i] == 0)
				temp.setColumn(i);
			else
				break;
		}
		return temp;
	}

	/**
	 * 返回location所在的行线段的右端位置 
	 * 若没有右端位置则返回location
	 * 
	 * @param the2DArray
	 *            目标二维数组
	 * @param location
	 *            目标位置
	 * @return Location
	 */
	private Location getRightPort(int[][] the2DArray, Location location) {
		Location temp = new Location(location.getRow(), location.getColumn());
		for (int i = location.getColumn() + 1; i < the2DArray.length; i++) {
			if (the2DArray[location.getRow()][i] == 0)
				temp.setColumn(i);
			else
				break;
		}
		return temp;
	}

	/**
	 * 返回location所在的列线段的上端位置 
	 * 若没有上端位置则返回location
	 * 
	 * @param the2DArray
	 *            目标二维数组
	 * @param location
	 *            目标位置
	 * @return Location
	 */
	private Location getUpPort(int[][] the2DArray, Location location) {
		Location temp = new Location(location.getRow(), location.getColumn());
		for (int i = location.getRow() - 1; i >= 0; i--) {
			if (the2DArray[i][location.getColumn()] == 0)
				temp.setRow(i);
			else
				break;
		}
		return temp;
	}

	/**
	 * 返回location所在的列线段的下端位置 
	 * 若没有下端位置则返回location
	 * 
	 * @param the2DArray
	 *            目标二维数组
	 * @param location
	 *            目标位置
	 * @return Location
	 */
	private Location getDownPort(int[][] the2DArray, Location location) {
		Location temp = new Location(location.getRow(), location.getColumn());
		for (int i = location.getRow() + 1; i < the2DArray.length; i++) {
			if (the2DArray[i][location.getColumn()] == 0)
				temp.setRow(i);
			else
				break;
		}
		return temp;
	}

	/**
	 * 若存在一条全0值线段和这两条行线段垂直且相交则返回交点位置数组 
	 * 否则返回null
	 * 
	 * @param the2DArray
	 *            目标二维数组
	 * @param line1
	 *            行线段1
	 * @param line2
	 *            行线段2
	 * @return Location[]
	 */
	private Location[] getIntersectionsForRowLines(int[][] the2DArray, Line line1,
			Line line2) {
		ValueField columnField1 = line1.getColumnField();
		ValueField columnField2 = line2.getColumnField();
		// 若两条行线段的列号区间没有交集则不可能通过另一条线段使之垂直且相交
		if (!columnField1.hasIntersectionWith(columnField2))
			return null;
		// 获取两条行线段列号值域的交集
		ValueField intersection = columnField1
				.getIntersectionWith(columnField2);
		// 遍历这个交集，取出对应的位置进行判断是否在列上可连通的
		for (int i = intersection.getStart(); i <= intersection.getEnd(); i++) {
			// line1上的对应位置
			Location a = new Location(line1.getStart().getRow(), i);
			// line2上的对应位置
			Location b = new Location(line2.getStart().getRow(), i);
			if (isConnectedInColumn(the2DArray, a, b))
				return new Location[] { a, b };
		}
		return null;
	}

	/**
	 * 若存在一条0值线段和这两条列线段垂直且相交则返回交点位置数组 
	 * 否则返回null
	 * 
	 * @param the2DArray
	 *            目标二维数组
	 * @param line1
	 *            列线段1
	 * @param line2
	 *            列线段2
	 * @return Location[]
	 */
	private Location[] getIntersectionsForColumnLines(int[][] the2DArray,
			Line line1, Line line2) {
		ValueField rowField1 = line1.getRowField();
		ValueField rowField2 = line2.getRowField();
		// 若两条列线段的行号区间没有交集则不可能通过另一条线段使之垂直且相交
		if (!rowField1.hasIntersectionWith(rowField2))
			return null;
		// 获取两条列线段行号值域的交集
		ValueField intersection = rowField1.getIntersectionWith(rowField2);
		// 遍历这个交集，取出对应的位置进行判断是否是行上可连通的
		for (int i = intersection.getStart(); i <= intersection.getEnd(); i++) {
			// line1上的对应位置
			Location a = new Location(i, line1.getStart().getColumn());
			// line2上的对应位置
			Location b = new Location(i, line2.getStart().getColumn());
			if (isConnectedInRow(the2DArray, a, b))
				return new Location[] { a, b };
		}
		return null;
	}
}