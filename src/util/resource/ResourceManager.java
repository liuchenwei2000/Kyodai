/**
 * 
 */
package util.resource;

import java.io.InputStream;
import java.net.URL;

/**
 * ��Դ������
 * 
 * ��Ҫ���ڶ�λ��ȡϵͳ��Դ
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-10-23
 */
public class ResourceManager {

	private ResourceManager() {
		// do nothing and no instance
	}

	/**
	 * ����String·����Ӧ��URL 
	 * <strong> Warning��ֻ���ڴ��ʱ���ñ����� </strong>
	 * 
	 * @param path
	 *            �ļ������·��
	 * @return URL
	 */
	public static URL getURL(String path) {
		URL url = ClassLoader.getSystemResource(path);
		return url;
	}

	/**
	 * ����String·����Ӧ��InputStream 
	 * <strong> Warning��ֻ���ڴ��ʱ���ñ����� </strong>
	 * 
	 * @param path
	 *            �ļ������·��
	 * @return InputStream
	 */
	public static InputStream getInputStream(String path) {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
		return inputStream;
	}
}