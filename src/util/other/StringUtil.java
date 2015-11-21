/**
 * 
 */
package util.other;

/**
 * 字符串工具类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-11-1
 */
public class StringUtil {

	private StringUtil() {
		// do nothing and no instance
	}

	/**
	 * 判断字符串代表的数是否是正整数
	 */
	public static boolean isPositiveNumber(String s) {
		return (isInteger(s) && Integer.parseInt(s) > 0);
	}

	/**
	 * 判断字符串是否是整数
	 */
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断字符串代表的数是否是偶数
	 */
	public static boolean isEvenNumber(String s) {
		return (isInteger(s) && Integer.parseInt(s) % 2 == 0);
	}
}
