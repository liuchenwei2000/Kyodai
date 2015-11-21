/**
 * 
 */
package util.resource;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import assistant.KyodaiConstant;

/**
 * 音乐播放器类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-10-23
 */
public class MusicPlayer {
	
	private static AudioClip audioClip;// 实际播放器对象
	private static boolean isOn = true;// 音效状态

	private MusicPlayer() {
		// do nothing and no instance
	}

	/**
	 * 播放 消去 音乐
	 */
	public static void playRemove() {
		if (isOn) {
			audioClip = Applet.newAudioClip(getURL(KyodaiConstant.REMOVE));
			audioClip.play();
		}
	}

	/**
	 * 播放 重列 音乐
	 */
	public static void playRefresh() {
		if (isOn) {
			audioClip = Applet.newAudioClip(getURL(KyodaiConstant.REFRESH));
			audioClip.play();
		}
	}

	/**
	 * 播放 选择 音乐
	 */
	public static void playSelect() {
		if (isOn) {
			audioClip = Applet.newAudioClip(getURL(KyodaiConstant.SELECT));
			audioClip.play();
		}
	}

	/**
	 * 停止播放
	 */
	public static void stop() {
		isOn = false;
	}

	/**
	 * 开始播放
	 */
	public static void start() {
		isOn = true;
	}
	
	/**
	 * 返回String路径对应的URL
	 * 
	 * @param path
	 *            路径
	 * @return URL
	 */
	private static URL getURL(String path) {
//		URL url = ResourceManager.getURL(path);// 打JAR包使用
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
