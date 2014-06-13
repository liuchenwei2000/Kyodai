/**
 * 
 */
package util.other;

import java.util.ArrayList;

/**
 * ����ת����
 * 
 * (��������һά����Ͷ�ά����֮�以��ת���洢)
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-10-19
 */
public class ArrayConversion {

	private ArrayConversion() {
		// do nothing and no instance
	}
	
	/**
	 * ��ģ�Ͷ�ά������������ֵ��ŵ�һά������
	 * 
	 * @param the2DArray
	 *            Ŀ���ά����
	 * @return Integer[]
	 */
	public static Integer[] toArray(int[][] the2DArray) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		// ֻ�����ά������������Ԫ��(������������һȦ0Ԫ��)
		for (int i = 1; i < the2DArray.length - 1; i++) {
			for (int j = 1; j < the2DArray[i].length - 1; j++) {
				al.add(the2DArray[i][j]);
			}
		}
		return al.toArray(new Integer[0]);
	}

	/**
	 * ��array�е�Ԫ�����θ�ֵ��ģ�Ͷ�ά����
	 * 
	 * @param the2DArray
	 *            Ŀ��2ά����
	 * @param array
	 *            Դ����
	 */
	public static void to2DArray(int[][] the2DArray, Integer[] array) {
		int index = 0;// array������
		for (int i = 1; i < the2DArray.length - 1; i++) {
			for (int j = 1; j < the2DArray[i].length - 1; j++) {
				the2DArray[i][j] = array[index].intValue();
				index++;
			}
		}
	}
}