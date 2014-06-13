/**
 * 
 */
package arithmetic;

import util.other.ArrayConversion;
import util.other.ParameterChecker;

/**
 * 重列算法类
 * 
 * @author 刘晨伟
 *
 * 创建时间：2007-10-19
 */
public class ResetArithmetic {

	private ResetArithmetic() {
		// do nothing and no instance
	}

	/**
	 * 随机重排目标二维数组中的元素
	 * 
	 * @param the2DArray
	 *            目标二维数组
	 */
	public static void reset2DArray(int[][] the2DArray) {
		ParameterChecker.check2DArray(the2DArray);
		// 将二维数组的元素存储到一维数组中
		Integer[] oldArray = ArrayConversion.toArray(the2DArray);
		// 获取一维数组进行随机顺序重排后的数组
		Integer[] newArray = RandomArithmetic
				.getArrayWithRandomSequence(oldArray);
		// 将随机重排后的数组赋值给二维数组
		ArrayConversion.to2DArray(the2DArray, newArray);
	}
}