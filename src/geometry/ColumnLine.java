/**
 * 
 */
package geometry;

/**
 * ��ֱ�߶�(��)��
 * 
 * @author ����ΰ
 * 
 * ����ʱ�䣺2007-10-16
 */
public class ColumnLine extends Line {

	/**
	 * ���췽��
	 * 
	 * @param location1
	 *            �߶ζ˵�λ��1
	 * @param location2
	 *            �߶ζ˵�λ��2
	 */
	public ColumnLine(Location location1, Location location2) {
		super(location1, location2);
	}

	/**
	 * �����߶�(���߶ΰ�����λ�ö����뵽locations��)
	 *
	 * @see geometry.Line#createLine(geometry.Location, geometry.Location)
	 */
	@Override
	protected void createLine(Location location1, Location location2) {
		// �к�С����Ϊ��ʼλ��
		if (location1.getRow() < location2.getRow()) {
			setStartAndEndLocation(location1, location2);
		} else {
			setStartAndEndLocation(location2, location1);
		}
		// ��������ʼλ�õ�����λ�õ����������е�λ��
		for (int i = getStart().getRow(); i <= getEnd().getRow(); i++) {
			// ����Locations(����λ�õĺ�������ͬ)
			getLocations().add(new Location(i, getStart().getColumn()));
		}
	}

	/**
	 * �ж�line�Ƿ������ƽ��
	 * 
	 * @see geometry.Line#isParallelWith(geometry.Line)
	 */
	@Override
	public boolean isParallelWith(Line line) {
		return isVertical(line);
	}

	/**
	 * �ж�line�Ƿ�����ߴ�ֱ
	 * 
	 * @see geometry.Line#isPlumbUp(geometry.Line)
	 */
	@Override
	public boolean isPlumbUp(Line line) {
		return isHorizontal(line);
	}
}