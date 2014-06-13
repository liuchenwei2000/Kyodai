/**
 * 
 */
package arithmetic;

import util.other.ParameterChecker;
import geometry.Location;

/**
 * ���鳣���㷨��
 * 
 * @author ����ΰ
 *
 * ����ʱ�䣺2007-10-19
 */
public class ArrayArithmetic {

	private ArrayArithmetic() {
		// do nothing and no instance
	}

	/**
	 * ����target��array���״γ��ֵ�����
	 * (��û�ҵ����� -1)
	 * 
	 * @param array
	 *            Ŀ������
	 * @param target
	 *            Ŀ����
	 * @return int
	 */
	public static int getIndex(int[] array, int target) {
		ParameterChecker.checkArray(array);
		for (int i = 0; i < array.length; i++) {
			if (array[i] == target)
				return i;
		}
		return -1;
	}
	
	/**
	 * ����target��array���״γ��ֵ�����
	 * (��û�ҵ����� -1)
	 * 
	 * @param array
	 *            Ŀ������
	 * @param target
	 *            Ŀ����
	 * @return int
	 */
	public static int getIndex(Object[] array, Object target) {
		ParameterChecker.checkArray(array);
		for (int i = 0; i < array.length; i++) {
			if (array[i] == target)
				return i;
		}
		return -1;
	}
	
	/**
	 * ����target��the2DArray���״�(������)���ֵ�λ��
	 * (��û�ҵ����� null)
	 * 
	 * @param the2DArray
	 *            Ŀ������
	 * @param target
	 *            Ŀ����
	 * @return Location
	 */
	public static Location getLocation(int[][] the2DArray, int target) {
		int row = getIndices(the2DArray, target)[0];
		int column = getIndices(the2DArray, target)[1];
		if (row == -1)
			return null;
		return new Location(row, column);
	}
	
	/**
	 * ����target��the2DArray���״�(������)���ֵ��������� 
	 * (����[0]�洢�к�,[1]�洢�к�,��û�ҵ����� {-1,-1} )
	 * 
	 * @param the2DArray
	 *            Ŀ���ά����
	 * @param target
	 *            Ŀ����
	 * @return int[]
	 */
	public static int[] getIndices(int[][] the2DArray, int target) {
		ParameterChecker.check2DArray(the2DArray);
		for (int i = 0; i < the2DArray.length; i++) {
			for (int j = 0; j < the2DArray[i].length; j++) {
				if (the2DArray[i][j] == target)
					return new int[] { i, j };
			}
		}
		return new int[] { -1, -1 };
	}
	
	/**
	 * ����target��the2DArray���״�(������)���ֵ�λ�� 
	 * (��û�ҵ����� null)
	 * 
	 * @param the2DArray
	 *            Ŀ���ά����
	 * @param target
	 *            Ŀ�����
	 * @return
	 */
	public static Location getLocation(Object[][] the2DArray, Object target) {
		int row = getIndexes(the2DArray, target)[0];
		int column = getIndexes(the2DArray, target)[1];
		if (row == -1)
			return null;
		return new Location(row, column);
	}
	
	/**
	 * ����target��the2DArray�е�һ�γ��ֵ��������� 
	 * (����[0]�洢�к�,[1]�洢�к�,��û�ҵ����� {-1,-1} )
	 * 
	 * @param the2DArray
	 *            Ŀ������
	 * @param target
	 *            Ŀ����
	 * @return int[]
	 */
	public static int[] getIndexes(Object[][] the2DArray, Object target) {
		ParameterChecker.check2DArray(the2DArray);
		for (int i = 0; i < the2DArray.length; i++) {
			for (int j = 0; j < the2DArray[i].length; j++) {
				if (the2DArray[i][j] == target)
					return new int[] { i, j };
			}
		}
		return new int[] { -1, -1 };
	}

	/**
	 * �ж�����λ���Ƿ�����
	 * 
	 * @param a
	 *            ��һ��λ��
	 * @param b
	 *            �ڶ���λ��
	 * @return boolean
	 */
	public static boolean isAdjoin(Location a, Location b) {
		return isAdjoin(a.getRow(), a.getColumn(), b.getRow(), b.getColumn());
	}
	
	/**
	 * �ж�����λ���Ƿ�����
	 * 
	 * @param x1
	 *            ��һ��λ�õ��к�
	 * @param y1
	 *            ��һ��λ�õ��к�
	 * @param x2
	 *            �ڶ���λ�õ��к�
	 * @param y2
	 *            �ڶ���λ�õ��к�
	 * @return boolean
	 */
	public static boolean isAdjoin(int x1, int y1, int x2, int y2) {
		boolean left = (x1 - 1 == x2 && y1 == y2);// 2��1���ھ�
		boolean right = (x1 + 1 == x2 && y1 == y2);// 2��1���ھ�
		boolean up = (x1 == x2 && y1 - 1 == y2);// 2��1���ھ�
		boolean down = (x1 == x2 && y1 + 1 == y2);// 2��1���ھ�
		return (left || right || up || down);
	}
}