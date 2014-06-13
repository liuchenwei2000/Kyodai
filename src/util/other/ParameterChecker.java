/**
 * 
 */
package util.other;

/**
 * �����Ϸ��Լ����
 * 
 * (�����Ϸ����׳���Ӧ���쳣��Ϣ)
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-10-19
 */
public class ParameterChecker {

	/**
	 * ����ģʽ
	 */
	private ParameterChecker() {
		// do nothing and no instance
	}

	/**
	 * ��鴫������ĺϷ���
	 * 
	 * @param dimension
	 *            ģ�������ά��
	 */
	public static void checkDimension(int dimension) {
		if (dimension < 1)
			throw new IllegalArgumentException("��������Ϊ������");
		if (dimension % 2 != 0)
			throw new IllegalArgumentException("��������Ϊż��");
	}
	
	/**
	 * ��鴫������ĺϷ���
	 * 
	 * @param total
	 *            ���������
	 * @param maxNumber
	 *            �������ֵ����
	 */
	public static void checkArgument(int total, int maxNumber) {
		checkTotal(total);
		checkMaxNumber(maxNumber);
	}

	/**
	 * �������������ĺϷ���
	 * 
	 * @param total
	 *            ���������
	 */
	public static void checkTotal(int total) {
		if (total % 2 != 0)
			throw new IllegalArgumentException("�ɶ�������ĸ�������Ϊż��!");
	}

	/**
	 * ����������ֵ���޵ĺϷ���
	 * 
	 * @param maxNumber
	 *            �������ֵ����
	 */
	public static void checkMaxNumber(int maxNumber) {
		if (maxNumber < 0)
			throw new IllegalArgumentException("���������ֵ���޲���С��0!");
	}

	/**
	 * �����������ĺϷ���
	 * 
	 * @param array
	 */
	public static <T> void checkArray(T[] array) {
		if (array == null || array.length == 0)
			throw new NullPointerException("���������Ϊ�ջ��߳���Ϊ��!");
	}

	/**
	 * �����������ĺϷ���
	 * 
	 * @param array
	 */
	public static void checkArray(int[] array) {
		if (array == null || array.length == 0)
			throw new NullPointerException("���������Ϊ�ջ��߳���Ϊ��!");
	}

	/**
	 * ����ά��������ĺϷ���
	 * 
	 * @param the2DArray
	 *            ��ά����
	 */
	public static <T> void check2DArray(T[][] the2DArray) {
		if (the2DArray == null || the2DArray.length == 0
				|| the2DArray[0].length == 0)
			throw new NullPointerException("����Ķ�ά����Ϊ�ջ��߳���Ϊ��!");
	}

	/**
	 * ����ά��������ĺϷ���
	 * 
	 * @param the2DArray
	 *            ��ά����
	 */
	public static void check2DArray(int[][] the2DArray) {
		if (the2DArray == null || the2DArray.length == 0
				|| the2DArray[0].length == 0)
			throw new NullPointerException("����Ķ�ά����Ϊ�ջ��߳���Ϊ��!");
	}
}