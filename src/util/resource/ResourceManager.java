/**
 * 
 */
package util.resource;

import java.io.InputStream;
import java.net.URL;

/**
 * 资源管理器
 * 
 * 主要用于定位获取系统资源
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-10-23
 */
public class ResourceManager {

	private ResourceManager() {
		// do nothing and no instance
	}

	/**
	 * 返回String路径对应的URL 
	 * <strong> Warning：只有在打包时调用本方法 </strong>
	 * 
	 * @param path
	 *            文件的相对路径
	 * @return URL
	 */
	public static URL getURL(String path) {
		URL url = ClassLoader.getSystemResource(path);
		return url;
	}

	/**
	 * 返回String路径对应的InputStream 
	 * <strong> Warning：只有在打包时调用本方法 </strong>
	 * 
	 * @param path
	 *            文件的相对路径
	 * @return InputStream
	 */
	public static InputStream getInputStream(String path) {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
		return inputStream;
	}
}