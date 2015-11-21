/**
 * 
 */
package util.other;

/**
 * 参数合法性检查类
 * 
 * (若不合法则抛出相应的异常信息)
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-10-19
 */
public class ParameterChecker {

	/**
	 * 单例模式
	 */
	private ParameterChecker() {
		// do nothing and no instance
	}

	/**
	 * 检查传入参数的合法性
	 * 
	 * @param dimension
	 *            模型数组的维数
	 */
	public static void checkDimension(int dimension) {
		if (dimension < 1)
			throw new IllegalArgumentException("参数必须为正整数");
		if (dimension % 2 != 0)
			throw new IllegalArgumentException("参数必须为偶数");
	}
	
	/**
	 * 检查传入参数的合法性
	 * 
	 * @param total
	 *            随机数个数
	 * @param maxNumber
	 *            随机数数值上限
	 */
	public static void checkArgument(int total, int maxNumber) {
		checkTotal(total);
		checkMaxNumber(maxNumber);
	}

	/**
	 * 检查随机数个数的合法性
	 * 
	 * @param total
	 *            随机数个数
	 */
	public static void checkTotal(int total) {
		if (total % 2 != 0)
			throw new IllegalArgumentException("成对随机数的个数必须为偶数!");
	}

	/**
	 * 检查随机数数值上限的合法性
	 * 
	 * @param maxNumber
	 *            随机数数值上限
	 */
	public static void checkMaxNumber(int maxNumber) {
		if (maxNumber < 0)
			throw new IllegalArgumentException("随机数的数值上限不可小于0!");
	}

	/**
	 * 检查数组参数的合法性
	 * 
	 * @param array
	 */
	public static <T> void checkArray(T[] array) {
		if (array == null || array.length == 0)
			throw new NullPointerException("传入的数组为空或者长度为零!");
	}

	/**
	 * 检查数组参数的合法性
	 * 
	 * @param array
	 */
	public static void checkArray(int[] array) {
		if (array == null || array.length == 0)
			throw new NullPointerException("传入的数组为空或者长度为零!");
	}

	/**
	 * 检查二维数组参数的合法性
	 * 
	 * @param the2DArray
	 *            二维数组
	 */
	public static <T> void check2DArray(T[][] the2DArray) {
		if (the2DArray == null || the2DArray.length == 0
				|| the2DArray[0].length == 0)
			throw new NullPointerException("传入的二维数组为空或者长度为零!");
	}

	/**
	 * 检查二维数组参数的合法性
	 * 
	 * @param the2DArray
	 *            二维数组
	 */
	public static void check2DArray(int[][] the2DArray) {
		if (the2DArray == null || the2DArray.length == 0
				|| the2DArray[0].length == 0)
			throw new NullPointerException("传入的二维数组为空或者长度为零!");
	}
}
