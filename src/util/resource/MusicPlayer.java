/**
 * 
 */
package util.resource;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import assistant.KyodaiConstant;

/**
 * ���ֲ�������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-10-23
 */
public class MusicPlayer {
	
	private static AudioClip audioClip;// ʵ�ʲ���������
	private static boolean isOn = true;// ��Ч״̬

	private MusicPlayer() {
		// do nothing and no instance
	}

	/**
	 * ���� ��ȥ ����
	 */
	public static void playRemove() {
		if (isOn) {
			audioClip = Applet.newAudioClip(getURL(KyodaiConstant.REMOVE));
			audioClip.play();
		}
	}

	/**
	 * ���� ���� ����
	 */
	public static void playRefresh() {
		if (isOn) {
			audioClip = Applet.newAudioClip(getURL(KyodaiConstant.REFRESH));
			audioClip.play();
		}
	}

	/**
	 * ���� ѡ�� ����
	 */
	public static void playSelect() {
		if (isOn) {
			audioClip = Applet.newAudioClip(getURL(KyodaiConstant.SELECT));
			audioClip.play();
		}
	}

	/**
	 * ֹͣ����
	 */
	public static void stop() {
		isOn = false;
	}

	/**
	 * ��ʼ����
	 */
	public static void start() {
		isOn = true;
	}
	
	/**
	 * ����String·����Ӧ��URL
	 * 
	 * @param path
	 *            ·��
	 * @return URL
	 */
	private static URL getURL(String path) {
//		URL url = ResourceManager.getURL(path);// ��JAR��ʹ��
		URL url = null;
		try {
			java.io.File file = new java.io.File(path);
			url = file.toURL();
		} catch (java.net.MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}
}