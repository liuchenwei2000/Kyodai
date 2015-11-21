/**
 * 
 */
package assistant;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 分数类
 * 
 * 用来记录连连看分数信息
 * 包括分数值和创建该分数的日期
 * 
 * @author 刘晨伟
 *
 * 创建时间：2007-11-7
 */
public class Score implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2469803386818828658L;
	
	private int score;// 分数值
	private Date date;// 分数创建日期

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
	 * 加分
	 * 
	 * @param newScore
	 *            新分数
	 */
	public void addScore(int newScore) {
		this.score += newScore;
	}
	
	/**
	 * 返回形式：50 (2007年11月7日 星期三)
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
