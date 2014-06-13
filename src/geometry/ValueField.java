/**
 * 
 */
package geometry;

/**
 * ����ֵ����
 * 
 * @author ����ΰ
 * 
 * ����ʱ�䣺2007-10-17
 */
public class ValueField {

	private int start;// ��߽�
	private int end;// �ұ߽�

	public ValueField() {
		start = 0;
		end = 0;
	}

	public ValueField(int start, int end) {
		if (start > end)
			throw new IllegalArgumentException("��߽粻�ɴ����ұ߽�");
		this.start = start;
		this.end = end;
	}

	/**
	 * �ж�valueField�Ƿ��ǵ�ǰֵ����Ӽ�
	 * 
	 * @param valueField
	 *            Ŀ��ֵ��
	 * @return boolean
	 */
	public boolean contains(ValueField valueField) {
		if ((this.getStart() <= valueField.getStart())
				&& (this.getEnd() >= valueField.getEnd()))
			return true;
		return false;
	}
	
	/**
	 * �ж�����ֵ���Ƿ��н���
	 * 
	 * @param valueField
	 *            Ŀ��ֵ��
	 * @return boolean
	 */
	public boolean hasIntersectionWith(ValueField valueField) {
		// ��this���ұ߽�С��valueField����߽����뽻
		if (this.getEnd() < valueField.getStart())
			return false;
		// ��valueField���ұ߽�С��this����߽����뽻
		if (valueField.getEnd() < this.getStart())
			return false;
		return true;
	}
	
	/**
	 * ��������ֵ��Ľ��� 
	 * ��û�н�������null
	 * 
	 * @param valueField
	 *            Ŀ��ֵ��
	 * @return ValueField
	 */
	public ValueField getIntersectionWith(ValueField valueField) {
		if (!this.hasIntersectionWith(valueField))
			return null;
		// ȷ����������߽�(��ֵ����߽�ϴ�ֵ)
		int start = Math.max(this.getStart(), valueField.getStart());
		// ȷ���������ұ߽�(��ֵ���ұ߽��Сֵ)
		int end = Math.min(this.getEnd(), valueField.getEnd());
		return new ValueField(start, end);
	}
	
	/**
	 * ������ʽ��[2��7]
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