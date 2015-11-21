/**
 * 
 */
package util.other;

import java.util.ArrayList;

/**
 * 数组转换类
 * 
 * (将数据在一维数组和二维数组之间互相转换存储)
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-10-19
 */
public class ArrayConversion {

	private ArrayConversion() {
		// do nothing and no instance
	}
	
	/**
	 * 将模型二维数组的有意义的值存放到一维数组中
	 * 
	 * @param the2DArray
	 *            目标二维数组
	 * @return Integer[]
	 */
	public static Integer[] toArray(int[][] the2DArray) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		// 只处理二维数组中真正的元素(即不含最外面一圈0元素)
		for (int i = 1; i < the2DArray.length - 1; i++) {
			for (int j = 1; j < the2DArray[i].length - 1; j++) {
				al.add(the2DArray[i][j]);
			}
		}
		return al.toArray(new Integer[0]);
	}

	/**
	 * 将array中的元素依次赋值给模型二维数组
	 * 
	 * @param the2DArray
	 *            目标2维数组
	 * @param array
	 *            源数组
	 */
	public static void to2DArray(int[][] the2DArray, Integer[] array) {
		int index = 0;// array的索引
		for (int i = 1; i < the2DArray.length - 1; i++) {
			for (int j = 1; j < the2DArray[i].length - 1; j++) {
				the2DArray[i][j] = array[index].intValue();
				index++;
			}
		}
	}
}
