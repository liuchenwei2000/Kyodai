/**
 * 
 */
package util.other;

import javax.swing.JOptionPane;

/**
 * 对话框管理器类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-11-1
 */
public class DialogManager {

	private DialogManager() {
		// do nothing and no instance
	}

	/**
	 * 显示错误信息对话框
	 * 
	 * @param message
	 *            信息
	 */
	public static void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * 显示普通信息对话框
	 * 
	 * @param message
	 *            信息
	 * @param title
	 *            标题
	 */
	public static void showMessageDialog(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title,
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * 显示输入对话框
	 * 
	 * @param message
	 *            信息
	 * @param defaultValue
	 *            输入框默认值
	 * @return String
	 */
	public static String showInputDialog(String message, String defaultValue) {
		return JOptionPane.showInputDialog(null, message, defaultValue);
	}

	/**
	 * 显示失败信息框
	 */
	public static void showFailureDialog() {
		showMessageDialog("...你失败了...", "Failed");
	}

	/**
	 * 显示创造最高分信息框
	 * 
	 * @param maxScore
	 *            最高分
	 */
	public static void showCongratulationDialog(String maxScore) {
		showMessageDialog("你已经创造了新的最高分：" + maxScore, "Congratulations");
	}
}
