/**
 * 
 */
package assistant;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * ������
 * 
 * ������¼������������Ϣ
 * ��������ֵ�ʹ����÷���������
 * 
 * @author ����ΰ
 *
 * ����ʱ�䣺2007-11-7
 */
public class Score implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2469803386818828658L;
	
	private int score;// ����ֵ
	private Date date;// ������������

	public Score() {
		score = 0;
		date = new Date();
	}

	public Score(int score) {
		this.score = score;
		this.date = new Date();
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * �ӷ�
	 * 
	 * @param newScore
	 *            �·���
	 */
	public void addScore(int newScore) {
		this.score += newScore;
	}
	
	/**
	 * ������ʽ��50 (2007��11��7�� ������)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM,
				Locale.CHINA);
		StringBuilder s = new StringBuilder("  ");
		s.append(score);
		s.append("    ");
		s.append("(");
		s.append(format.format(date));
		s.append(")");
		return s.toString();
	}
}