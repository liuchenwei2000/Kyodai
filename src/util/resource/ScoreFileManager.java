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
 * �����ļ�������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-11-1
 */
public class ScoreFileManager {

	private static ObjectInputStream reader;// ��ȡ������
	private static ObjectOutputStream writer;// д�������

	private ScoreFileManager() {
		// do nothing and no instance
	}

	/**
	 * ����������д���ļ�
	 * 
	 * @param score
	 *            ��������
	 * @throws IOException
	 */
	public static void writeScoreToFile(Score score) throws IOException {
		try {
			writer = new ObjectOutputStream(new FileOutputStream(
					KyodaiConstant.SCORE));
			writer.writeObject(score);
			writer.close();
		} catch (FileNotFoundException e) {
			// �ļ����������ȴ����µķ����ļ���д��
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
	 * �ӷ����ļ��ж�ȡ��������
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
			// �����ļ����������ȴ���Ȼ��д��Ĭ�Ϸ���������Ĭ�Ϸ�������
			createNewScoreFile();
			return getDefaultScoreAndResetFile();
		} catch (IOException e) {
			// �����ļ������ݱ��޸�
			DialogManager.showErrorDialog("�����ļ��ѱ��޸ģ����ݽ���գ�");
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
	 * ����Ĭ�Ϸ���������������ļ�
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
	 * �½�һ�������ļ�
	 */
	private static void createNewScoreFile() {
		try {
			new File(KyodaiConstant.SCORE).createNewFile();
		} catch (IOException ex) {
			DialogManager.showErrorDialog(ex.getMessage());
		}
	}
}