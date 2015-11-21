/**
 * 
 */
package util.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.other.DialogManager;

import assistant.KyodaiConstant;
import assistant.Score;

/**
 * 分数文件管理器
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-11-1
 */
public class ScoreFileManager {

	private static ObjectInputStream reader;// 读取对象流
	private static ObjectOutputStream writer;// 写入对象流

	private ScoreFileManager() {
		// do nothing and no instance
	}

	/**
	 * 将分数对象写入文件
	 * 
	 * @param score
	 *            分数对象
	 * @throws IOException
	 */
	public static void writeScoreToFile(Score score) throws IOException {
		try {
			writer = new ObjectOutputStream(new FileOutputStream(
					KyodaiConstant.SCORE));
			writer.writeObject(score);
			writer.close();
		} catch (FileNotFoundException e) {
			// 文件不存在则先创建新的分数文件再写入
			createNewScoreFile();
			writeScoreToFile(score);
		} catch (IOException e) {
			DialogManager.showErrorDialog(e.getMessage());
		} finally {
			if (writer != null)
				writer.close();
		}
	}

	/**
	 * 从分数文件中读取分数对象
	 * 
	 * @throws IOException
	 */
	public static Score readScoreFromFile() throws IOException {
		Score score = null;
		try {
			reader = new ObjectInputStream(new FileInputStream(
					KyodaiConstant.SCORE));
			score = (Score) reader.readObject();
			return score;
		} catch (FileNotFoundException e) {
			// 分数文件不存在则先创建然后写入默认分数并返回默认分数对象
			createNewScoreFile();
			return getDefaultScoreAndResetFile();
		} catch (IOException e) {
			// 分数文件的数据被修改
			DialogManager.showErrorDialog("分数文件已被修改，数据将清空！");
			return getDefaultScoreAndResetFile();
		} catch (ClassNotFoundException e) {
			DialogManager.showErrorDialog(e.getMessage());
			return score;
		} finally {
			if (reader != null)
				reader.close();

		}
	}

	/**
	 * 返回默认分数对象并重设分数文件
	 */
	private static Score getDefaultScoreAndResetFile() {
		Score defaultScore = new Score();
		try {
			writeScoreToFile(defaultScore);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return defaultScore;
	}
	
	/**
	 * 新建一个分数文件
	 */
	private static void createNewScoreFile() {
		try {
			new File(KyodaiConstant.SCORE).createNewFile();
		} catch (IOException ex) {
			DialogManager.showErrorDialog(ex.getMessage());
		}
	}
}
