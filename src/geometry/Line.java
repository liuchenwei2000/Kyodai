/**
 * 
 */
package geometry;

import java.util.ArrayList;

/**
 * �߶���
 * (ֻ�������޸��������кŶ�������)
 * 
 * @author ����ΰ
 * 
 * ����ʱ�䣺2007-10-16
 */
public abstract class Line {

	private ArrayList<Location> locations;// �����洢�����߶ε�λ��
	private Location start;// �߶���ʼλ��
	private Location end;// �߶ν���λ��

	/**
	 * ���췽��
	 * 
	 * @param location1
	 *            �߶ζ˵�λ��1
	 * @param location2
	 *            �߶ζ˵�λ��2
	 */
	public Line(Location location1, Location location2) {
		createLine(location1, location2);
	}

	/**
	 * ʵ�ʴ����߶εķ���,����ɶ�locations�Ĺ��� 
	 * (������Ҫʵ���������)
	 * 
	 * @param location1
	 *            �߶ζ˵�λ��1
	 * @param location2
	 *            �߶ζ˵�λ��2
	 */
	protected abstract void createLine(Location location1, Location location2);

	/**
	 * ������ʼλ�úͽ���λ��
	 * 
	 * @param start
	 *            ��ʼλ��
	 * @param end
	 *            ����λ��
	 */
	protected void setStartAndEndLocation(Location start, Location end) {
		setStart(start);
		setEnd(end);
	}

	/**
	 * �ж������߶��Ƿ�ƽ��
	 * 
	 * @param line
	 *            Ŀ���߶�
	 * @return boolean
	 */
	public abstract boolean isParallelWith(Line line);

	/**
	 * �ж������߶��Ƿ�ֱ
	 * 
	 * @param line
	 *            Ŀ���߶�
	 * @return boolean
	 */
	public abstract boolean isPlumbUp(Line line);

	/**
	 * �ж������߶��Ƿ��ཻ
	 * 
	 * @param line
	 *            Ŀ���߶�
	 * @return boolean
	 */
	public boolean isIntersectantWith(Line line) {
		// �����Ƿ��н�������ж�
		return getPointOfIntersection(line) != null;
	}

	/**
	 * ���������߶εĽ���
	 * 
	 * @param line
	 *            Ŀ���߶�
	 * @return Location
	 */
	public Location getPointOfIntersection(Line line) {
		if (line == null)
			return null;
		// �������߶ε�λ���б��󽻼�(����������Ԫ�����ǽ���)
		this.getLocations().retainAll(line.getLocations());
		// û�н���
		if (this.getLocations().size() == 0)
			return null;
		else
			return this.getLocations().get(0);
	}
	
	/**
	 * �����߶ε��к�ֵ��
	 * 
	 * @return ValueField
	 */
	public final ValueField getRowField() {
		return new ValueField(this.getStart().getRow(), this.getEnd().getRow());
	}

	/**
	 * �����߶ε��к�ֵ��
	 * 
	 * @return ValueField
	 */
	public final ValueField getColumnField() {
		return new ValueField(this.getStart().getColumn(), this.getEnd()
				.getColumn());
	}

	/**
	 * �ж��߶��Ƿ���ˮƽ��
	 * 
	 * @param line
	 *            Ŀ���߶�
	 * @return boolean
	 */
	public static final boolean isHorizontal(Line line) {
		if (line == null)
			throw new IllegalArgumentException("�߶β���Ϊ��");
		// �߶�����λ�õ��к������Ϊˮƽ��
		if (line.getStart().getRow() == line.getEnd().getRow())
			return true;
		return false;
	}

	/**
	 * �ж��߶��Ƿ��Ǵ�ֱ��
	 * 
	 * @param line
	 *            Ŀ���߶�
	 * @return boolean
	 */
	public static final boolean isVertical(Line line) {
		if (line == null)
			throw new IllegalArgumentException("�߶β���Ϊ��");
		// �߶�����λ�õ��к������Ϊ��ֱ��
		if (line.getStart().getColumn() == line.getEnd().getColumn())
			return true;
		return false;
	}
	
	/**
	 * ������ʽ��[2��3��,3��4��,4��5��]
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getLocations().toString();
	}

	/**
	 * �߶ε���ʼλ�úͽ���λ����ͬ��˵���߶��غ�
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