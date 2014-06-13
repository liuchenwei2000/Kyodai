/**
 * 
 */
package arithmetic;

import util.other.ArrayConversion;
import util.other.ParameterChecker;

/**
 * �����㷨��
 * 
 * @author ����ΰ
 *
 * ����ʱ�䣺2007-10-19
 */
public class ResetArithmetic {

	private ResetArithmetic() {
		// do nothing and no instance
	}

	/**
	 * �������Ŀ���ά�����е�Ԫ��
	 * 
	 * @param the2DArray
	 *            Ŀ���ά����
	 */
	public static void reset2DArray(int[][] the2DArray) {
		ParameterChecker.check2DArray(the2DArray);
		// ����ά�����Ԫ�ش洢��һά������
		Integer[] oldArray = ArrayConversion.toArray(the2DArray);
		// ��ȡһά����������˳�����ź������
		Integer[] newArray = RandomArithmetic
				.getArrayWithRandomSequence(oldArray);
		// ��������ź�����鸳ֵ����ά����
		ArrayConversion.to2DArray(the2DArray, newArray);
	}
}