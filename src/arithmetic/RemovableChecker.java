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
 * ��������ť�����Լ���� 
 * (�������������ƿ���·��)
 * 
 * @author ����ΰ
 *
 * ����ʱ�䣺2007-10-17
 */
public class RemovableChecker {

	private LineDrawer drawer;// �߶λ���������

	public RemovableChecker(KyodaiPanel kyodaiPanel) {
		drawer = new LineDrawer(kyodaiPanel);
	}

	/**
	 * �ж�����λ���ڶ�ά�������Ƿ��ǿ����� ������ͬʱ�ڽ����ϻ�����ȥ·��
	 * 
	 * @param ary
	 *            Ŀ���ά����
	 * @param a
	 *            λ��a
	 * @param b
	 *            λ��b
	 * @return boolean
	 */
	public boolean isRemovable(int[][] ary, Location a, Location b) {
		/** ��λ����ͬһֱ���Ͽ���ͨ */
		if (isConnectedInTheSameLine(ary, a, b)) {
			drawer.drawRemovablePathAndUpdateUI(a, b);
			return true;
		}
		/** ��λ�����ڵ������߶��ཻ */
		// λ��a���ڵ����߶�
		Line rowLineA = getRowLineContainsLocation(ary, a);
		// λ��b���ڵ����߶�
		Line rowLineB = getRowLineContainsLocation(ary, b);
		// λ��a���ڵ����߶�
		Line columnLineA = getColumnLineContainsLocation(ary, a);
		// λ��b���ڵ����߶�
		Line columnLineB = getColumnLineContainsLocation(ary, b);

		Location intersection = null;// �߶ν���
		// aλ�����ڵ����߶κ�bλ�����ڵ����߶��ཻ
		if (rowLineA.isIntersectantWith(columnLineB)) {
			intersection = rowLineA.getPointOfIntersection(columnLineB);
			drawer.drawRemovablePathAndUpdateUI(a, intersection, b);
			return true;
		}
		// aλ�����ڵ����߶κ�bλ�����ڵ����߶��ཻ
		if (columnLineA.isIntersectantWith(rowLineB)) {
			intersection = columnLineA.getPointOfIntersection(rowLineB);
			drawer.drawRemovablePathAndUpdateUI(a, intersection, b);
			return true;
		}
		/** ����һ���߶δ�ֱ�ཻ����λ�����ڵ�����ƽ���� */
		Location[] intersections = null;// �߶ν�������
		// ���߶ν�������
		intersections = getIntersectionsForRowLines(ary, rowLineA, rowLineB);
		// ����һ���߶δ�ֱ�ཻ��a,b���ڵĵ����߶�
		if (intersections != null) {
			drawer.drawRemovablePathAndUpdateUI(a, intersections[0],
					intersections[1], b);
			return true;
		}
		// ���߶ν�������
		intersections = getIntersectionsForColumnLines(ary, columnLineA,
				columnLineB);
		// ����һ���߶δ�ֱ�ཻ��a,b���ڵĵ����߶�
		if (intersections != null) {
			drawer.drawRemovablePathAndUpdateUI(a, intersections[0],
					intersections[1], b);
			return true;
		}
		return false;
	}

	/**
	 * �ж�����λ�����������Ƿ���ͬһֱ���Ͽ���ͨ
	 * 
	 * @param ary
	 *            Ŀ���ά����
	 * @param a
	 *            λ��a
	 * @param b
	 *            λ��b
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
	 * �ж���λ����ͬһ�����Ƿ��ǿ���ͨ��
	 * 
	 * @param ary
	 *            Ŀ���ά����
	 * @param a
	 *            λ��a
	 * @param b
	 *            λ��b
	 * @return boolean
	 */
	private boolean isConnectedInRow(int[][] ary, Location a, Location b) {
		ParameterChecker.check2DArray(ary);
		if (!a.isInTheSameRowWith(b))
			return false;
		Line line = new RowLine(a, b);
		int startColumn = line.getStart().getColumn();// ��ʼλ�õ��к�
		int endColumn = line.getEnd().getColumn();// ����λ�õ��к�
		// ��������ʼλ�õ�����λ�õ����������е�λ��(�����������˵�)
		for (int i = startColumn + 1; i < endColumn; i++) {
			// �������ϴ���ĳλ�ò�Ϊ0����λ���������ϲ���ͨ
			if (ary[a.getRow()][i] != 0)
				return false;
		}
		return true;
	}

	/**
	 * �ж���λ����ͬһ�����Ƿ��ǿ���ͨ��
	 * 
	 * @param ary
	 *            Ŀ���ά����
	 * @param a
	 *            λ��a
	 * @param b
	 *            λ��b
	 * @return boolean
	 */
	private boolean isConnectedInColumn(int[][] ary, Location a, Location b) {
		ParameterChecker.check2DArray(ary);
		if (!a.isInTheSameColumnWith(b))
			return false;
		Line line = new ColumnLine(a, b);
		int startRow = line.getStart().getRow();// ��ʼλ�õ��к�
		int endRow = line.getEnd().getRow();// ����λ�õ��к�
		// ��������ʼλ�õ�����λ�õ����������е�λ��(�����������˵�)
		for (int i = startRow + 1; i < endRow; i++) {
			// �������ϴ���ĳλ�ò�Ϊ0����λ���������ϲ���ͨ
			if (ary[i][a.getColumn()] != 0)
				return false;
		}
		return true;
	}

	/**
	 * ���ذ���λ��location��ˮƽ�߶�(��ʽ�磺00000location000) 
	 * Ҳ������location��ˮƽ�ڽ�λ��(ֵΪ0��)��ɵ��߶�
	 * ��location���Ҷ��Ƿ�0λ���򷵻�ֻ��������ĵ������߶�
	 * 
	 * @param the2DArray
	 *            Ŀ���ά����
	 * @param location
	 *            Ŀ��λ��
	 * @return Line
	 */
	private Line getRowLineContainsLocation(int[][] the2DArray,
			Location location) {
		// Ѱ�����߶ε����λ��
		Location left = getLeftPort(the2DArray, location);
		// Ѱ�����߶ε��Ҷ�λ��
		Location right = getRightPort(the2DArray, location);
		return new RowLine(left, right);
	}

	/**
	 * ���ذ���λ��location�Ĵ�ֱ�߶� 
	 * Ҳ������location�Ĵ�ֱ�ڽ�λ��(ֵΪ0��)��ɵ��߶�
	 * ��location���¶��Ƿ�0λ���򷵻�ֻ��������ĵ������߶�
	 * 
	 * @param the2DArray
	 *            Ŀ���ά����
	 * @param location
	 *            Ŀ��λ��
	 * @return Line
	 */
	private Line getColumnLineContainsLocation(int[][] the2DArray,
			Location location) {
		// Ѱ�����߶ε��϶�λ��
		Location up = getUpPort(the2DArray, location);
		// Ѱ�����߶ε��¶�λ��
		Location down = getDownPort(the2DArray, location);
		return new ColumnLine(up, down);
	}

	/**
	 * ����location���ڵ����߶ε����λ�� 
	 * ��û�����λ���򷵻�location
	 * 
	 * @param the2DArray
	 *            Ŀ���ά����
	 * @param location
	 *            Ŀ��λ��
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
	 * ����location���ڵ����߶ε��Ҷ�λ�� 
	 * ��û���Ҷ�λ���򷵻�location
	 * 
	 * @param the2DArray
	 *            Ŀ���ά����
	 * @param location
	 *            Ŀ��λ��
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
	 * ����location���ڵ����߶ε��϶�λ�� 
	 * ��û���϶�λ���򷵻�location
	 * 
	 * @param the2DArray
	 *            Ŀ���ά����
	 * @param location
	 *            Ŀ��λ��
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
	 * ����location���ڵ����߶ε��¶�λ�� 
	 * ��û���¶�λ���򷵻�location
	 * 
	 * @param the2DArray
	 *            Ŀ���ά����
	 * @param location
	 *            Ŀ��λ��
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
	 * ������һ��ȫ0ֵ�߶κ����������߶δ�ֱ���ཻ�򷵻ؽ���λ������ 
	 * ���򷵻�null
	 * 
	 * @param the2DArray
	 *            Ŀ���ά����
	 * @param line1
	 *            ���߶�1
	 * @param line2
	 *            ���߶�2
	 * @return Location[]
	 */
	private Location[] getIntersectionsForRowLines(int[][] the2DArray, Line line1,
			Line line2) {
		ValueField columnField1 = line1.getColumnField();
		ValueField columnField2 = line2.getColumnField();
		// ���������߶ε��к�����û�н����򲻿���ͨ����һ���߶�ʹ֮��ֱ���ཻ
		if (!columnField1.hasIntersectionWith(columnField2))
			return null;
		// ��ȡ�������߶��к�ֵ��Ľ���
		ValueField intersection = columnField1
				.getIntersectionWith(columnField2);
		// �������������ȡ����Ӧ��λ�ý����ж��Ƿ������Ͽ���ͨ��
		for (int i = intersection.getStart(); i <= intersection.getEnd(); i++) {
			// line1�ϵĶ�Ӧλ��
			Location a = new Location(line1.getStart().getRow(), i);
			// line2�ϵĶ�Ӧλ��
			Location b = new Location(line2.getStart().getRow(), i);
			if (isConnectedInColumn(the2DArray, a, b))
				return new Location[] { a, b };
		}
		return null;
	}

	/**
	 * ������һ��0ֵ�߶κ����������߶δ�ֱ���ཻ�򷵻ؽ���λ������ 
	 * ���򷵻�null
	 * 
	 * @param the2DArray
	 *            Ŀ���ά����
	 * @param line1
	 *            ���߶�1
	 * @param line2
	 *            ���߶�2
	 * @return Location[]
	 */
	private Location[] getIntersectionsForColumnLines(int[][] the2DArray,
			Line line1, Line line2) {
		ValueField rowField1 = line1.getRowField();
		ValueField rowField2 = line2.getRowField();
		// ���������߶ε��к�����û�н����򲻿���ͨ����һ���߶�ʹ֮��ֱ���ཻ
		if (!rowField1.hasIntersectionWith(rowField2))
			return null;
		// ��ȡ�������߶��к�ֵ��Ľ���
		ValueField intersection = rowField1.getIntersectionWith(rowField2);
		// �������������ȡ����Ӧ��λ�ý����ж��Ƿ������Ͽ���ͨ��
		for (int i = intersection.getStart(); i <= intersection.getEnd(); i++) {
			// line1�ϵĶ�Ӧλ��
			Location a = new Location(i, line1.getStart().getColumn());
			// line2�ϵĶ�Ӧλ��
			Location b = new Location(i, line2.getStart().getColumn());
			if (isConnectedInRow(the2DArray, a, b))
				return new Location[] { a, b };
		}
		return null;
	}
}