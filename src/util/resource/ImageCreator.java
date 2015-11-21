/**
 * 
 */
package util.resource;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * 图片生成器类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-10-21
 */
public class ImageCreator {

	private ImageCreator() {
		// do nothing and no instance
	}

	/**
	 * 根据iconPath表示的路径返回开发环境下使用的Image
	 * 
	 * @param iconPath
	 *            图片相对路径字符串
	 * @return Image
	 */
	public static Image getImage(String iconPath) {
		Image image = new ImageIcon(iconPath).getImage();
		return image;
	}

	/**
	 * 根据iconPath表示的路径返回开发环境下使用的ImageIcon
	 * 
	 * @param iconPath
	 *            图片相对路径字符串
	 * @return Image
	 */
	public static ImageIcon getImageIcon(String iconPath) {
		ImageIcon icon = new ImageIcon(iconPath);
		return icon;
	}

//	/**
//	 * 根据iconPath表示的路径返回JAR下使用的Image
//	 * 
//	 * @param iconPath
//	 *            图片相对路径字符串
//	 * @return Image
//	 */
//	public static Image getImage(String iconPath) {
//		Image image = new ImageIcon(ResourceManager.getURL(iconPath)).getImage();
//		return image;
//	}
//	
//	/**
//	 * 根据iconPath表示的路径返回JAR下使用的ImageIcon
//	 * 
//	 * @param iconPath
//	 *            图片相对路径字符串
//	 * @return Image
//	 */
//	public static ImageIcon getImageIcon(String iconPath) {
//		ImageIcon icon = new ImageIcon(ResourceManager.getURL(iconPath));
//		return icon;
//	}
}
