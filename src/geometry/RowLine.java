/**
 * 
 */
package geometry;

/**
 * ˮƽ�߶�(��)��
 * 
 * @author ����ΰ
 * 
 * ����ʱ�䣺2007-10-16
 */
public class RowLine extends Line {

	/**
	 * ���췽��
	 * 
	 * @param location1
	 *            �߶ζ˵�λ��1
	 * @param location2
	 *            �߶ζ˵�λ��2
	 */
	public RowLine(Location location1, Location location2) {
		super(location1, location2);
	}

	/**
	 * �����߶�(���߶ΰ�����λ�����μ��뵽locations��)
	 *
	 * @see geometry.Line#createLine(geometry.Location, geometry.Location)
	 */
	@Override
	protected void createLine(Location location1, Location location2) {
		// �к�С����Ϊ��ʼλ��
		if (location1.getColumn() < location2.getColumn()) {
			setStartAndEndLocation(location1, location2);
		} else {
			setStartAndEndLocation(location2, location1);
		}
		// ��������ʼλ�õ�����λ�õ����������е�λ��
		for (int i = getStart().getColumn(); i <= getEnd().getColumn(); i++) {
			// ����locations(����λ�õ��к���ͬ)
			getLocations().add(new Location(getStart().getRow(), i));
		}
	}

	/**
	 * �ж�line�Ƿ������ƽ��
	 *
	 * @see geometry.Line#isParallelWith(geometry.Line)
	 */
	@Override
	public boolean isParallelWith(Line line) {
		 return isHorizontal(line);
	}

	/**
	 * �ж�line�Ƿ�����ߴ�ֱ
	 *
	 * @see geometry.Line#isPlumbUp(geometry.Line)
	 */
	@Override
	public boolean isPlumbUp(Line line) {
		return isVertical(line);
	}
}