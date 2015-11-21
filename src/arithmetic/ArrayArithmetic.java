/**
 * 
 */
package arithmetic;

import util.other.ParameterChecker;
import geometry.Location;

/**
 * 数组常用算法类
 * 
 * @author 刘晨伟
 *
 * 创建时间：2007-10-19
 */
public class ArrayArithmetic {

	private ArrayArithmetic() {
		// do nothing and no instance
	}

	/**
	 * 返回target在array中首次出现的索引
	 * (若没找到返回 -1)
	 * 
	 * @param array
	 *            目标数组
	 * @param target
	 *            目标数
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
	 * 返回target在array中首次出现的索引
	 * (若没找到返回 -1)
	 * 
	 * @param array
	 *            目标数组
	 * @param target
	 *            目标数
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
	 * 返回target在the2DArray中首次(行优先)出现的位置
	 * (若没找到返回 null)
	 * 
	 * @param the2DArray
	 *            目标数组
	 * @param target
	 *            目标数
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
	 * 返回target在the2DArray中首次(行优先)出现的索引数组 
	 * (其中[0]存储行号,[1]存储列号,若没找到返回 {-1,-1} )
	 * 
	 * @param the2DArray
	 *            目标二维数组
	 * @param target
	 *            目标数
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
	 * 返回target在the2DArray中首次(行优先)出现的位置 
	 * (若没找到返回 null)
	 * 
	 * @param the2DArray
	 *            目标二维数组
	 * @param target
	 *            目标对象
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
	 * 返回target在the2DArray中第一次出现的索引数组 
	 * (其中[0]存储行号,[1]存储列号,若没找到返回 {-1,-1} )
	 * 
	 * @param the2DArray
	 *            目标数组
	 * @param target
	 *            目标数
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
	 * 判断两个位置是否相邻
	 * 
	 * @param a
	 *            第一个位置
	 * @param b
	 *            第二个位置
	 * @return boolean
	 */
	public static boolean isAdjoin(Location a, Location b) {
		return isAdjoin(a.getRow(), a.getColumn(), b.getRow(), b.getColumn());
	}
	
	/**
	 * 判断两个位置是否相邻
	 * 
	 * @param x1
	 *            第一个位置的行号
	 * @param y1
	 *            第一个位置的列号
	 * @param x2
	 *            第二个位置的行号
	 * @param y2
	 *            第二个位置的列号
	 * @return boolean
	 */
	public static boolean isAdjoin(int x1, int y1, int x2, int y2) {
		boolean left = (x1 - 1 == x2 && y1 == y2);// 2是1左邻居
		boolean right = (x1 + 1 == x2 && y1 == y2);// 2是1右邻居
		boolean up = (x1 == x2 && y1 - 1 == y2);// 2是1上邻居
		boolean down = (x1 == x2 && y1 + 1 == y2);// 2是1下邻居
		return (left || right || up || down);
	}
}
