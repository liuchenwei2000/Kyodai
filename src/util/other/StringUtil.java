/**
 * 
 */
package util.other;

/**
 * �ַ���������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-11-1
 */
public class StringUtil {

	private StringUtil() {
		// do nothing and no instance
	}

	/**
	 * �ж��ַ�����������Ƿ���������
	 */
	public static boolean isPositiveNumber(String s) {
		return (isInteger(s) && Integer.parseInt(s) > 0);
	}

	/**
	 * �ж��ַ����Ƿ�������
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
	 * �ж��ַ�����������Ƿ���ż��
	 */
	public static boolean isEvenNumber(String s) {
		return (isInteger(s) && Integer.parseInt(s) % 2 == 0);
	}
}