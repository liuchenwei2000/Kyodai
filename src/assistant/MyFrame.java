/**
 * 
 */
package assistant;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 自定义Frame类
 * 
 * @author 刘晨伟
 *
 * 创建时间：2007-10-19
 */
public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyFrame(){
		// 默认关闭操作
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * component居中显示设置
	 */
	protected void setCenter() {
		int windowWidth = getWidth(); // 获得窗口宽
		int windowHeight = getHeight(); // 获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2
				- windowHeight / 2);// 设置窗口居中显示
	}
}
