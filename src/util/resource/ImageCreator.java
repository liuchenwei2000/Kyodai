/**
 * 
 */
package util.resource;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * ͼƬ��������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-10-21
 */
public class ImageCreator {

	private ImageCreator() {
		// do nothing and no instance
	}

	/**
	 * ����iconPath��ʾ��·�����ؿ���������ʹ�õ�Image
	 * 
	 * @param iconPath
	 *            ͼƬ���·���ַ���
	 * @return Image
	 */
	public static Image getImage(String iconPath) {
		Image image = new ImageIcon(iconPath).getImage();
		return image;
	}

	/**
	 * ����iconPath��ʾ��·�����ؿ���������ʹ�õ�ImageIcon
	 * 
	 * @param iconPath
	 *            ͼƬ���·���ַ���
	 * @return Image
	 */
	public static ImageIcon getImageIcon(String iconPath) {
		ImageIcon icon = new ImageIcon(iconPath);
		return icon;
	}

//	/**
//	 * ����iconPath��ʾ��·������JAR��ʹ�õ�Image
//	 * 
//	 * @param iconPath
//	 *            ͼƬ���·���ַ���
//	 * @return Image
//	 */
//	public static Image getImage(String iconPath) {
//		Image image = new ImageIcon(ResourceManager.getURL(iconPath)).getImage();
//		return image;
//	}
//	
//	/**
//	 * ����iconPath��ʾ��·������JAR��ʹ�õ�ImageIcon
//	 * 
//	 * @param iconPath
//	 *            ͼƬ���·���ַ���
//	 * @return Image
//	 */
//	public static ImageIcon getImageIcon(String iconPath) {
//		ImageIcon icon = new ImageIcon(ResourceManager.getURL(iconPath));
//		return icon;
//	}
}