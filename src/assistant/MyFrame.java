/**
 * 
 */
package assistant;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * �Զ���Frame��
 * 
 * @author ����ΰ
 *
 * ����ʱ�䣺2007-10-19
 */
public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyFrame(){
		// Ĭ�Ϲرղ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * component������ʾ����
	 */
	protected void setCenter() {
		int windowWidth = getWidth(); // ��ô��ڿ�
		int windowHeight = getHeight(); // ��ô��ڸ�
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2
				- windowHeight / 2);// ���ô��ھ�����ʾ
	}
}