/**
 * 
 */
package util.other;

import javax.swing.JOptionPane;

/**
 * �Ի����������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-11-1
 */
public class DialogManager {

	private DialogManager() {
		// do nothing and no instance
	}

	/**
	 * ��ʾ������Ϣ�Ի���
	 * 
	 * @param message
	 *            ��Ϣ
	 */
	public static void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * ��ʾ��ͨ��Ϣ�Ի���
	 * 
	 * @param message
	 *            ��Ϣ
	 * @param title
	 *            ����
	 */
	public static void showMessageDialog(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * ��ʾ����Ի���
	 * 
	 * @param message
	 *            ��Ϣ
	 * @param defaultValue
	 *            �����Ĭ��ֵ
	 * @return String
	 */
	public static String showInputDialog(String message, String defaultValue) {
		return JOptionPane.showInputDialog(null, message, defaultValue);
	}

	/**
	 * ��ʾʧ����Ϣ��
	 */
	public static void showFailureDialog() {
		showMessageDialog("...��ʧ����...", "Failed");
	}

	/**
	 * ��ʾ������߷���Ϣ��
	 * 
	 * @param maxScore
	 *            ��߷�
	 */
	public static void showCongratulationDialog(String maxScore) {
		showMessageDialog("���Ѿ��������µ���߷֣�" + maxScore, "Congratulations");
	}
}