/**
 * 
 */
package ui;

import assistant.Score;
import util.other.DialogManager;

/**
 * 倒计时器类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-10-25
 */
public class Calculagraph implements Runnable {

	private KyodaiMainUI m_mainUI;
	private boolean isCounting;// 是否正在倒计时(状态标志)

	Calculagraph(KyodaiMainUI kyodaiMainUI) {
		m_mainUI = kyodaiMainUI;
		isCounting = true;
	}
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			startCountingDown();
		} catch (Exception e) {
			// 被中断了就直接结束线程
			return;
		}
		// 倒计时结束则提示失败信息
		DialogManager.showFailureDialog();
		processScore();
		showNewGame();
	}

	/**
	 * 返回倒计时器是否正在倒计时
	 */
	private boolean isCounting() {
		return isCounting;
	}

	/**
	 * 设置倒计时器的状态
	 */
	private void setCounting(boolean isCounting) {
		this.isCounting = isCounting;
	}
	
	/**
	 * 暂停倒计时
	 */
	protected void pauseCountingDown() {
		this.setCounting(false);
	}
	
	/**
	 * 继续倒计时
	 */
	protected void goOnCountingDown() {
		this.setCounting(true);
	}
	
	/**
	 * 开始倒计时
	 * 
	 * 如果状态标志被置为false则认为是暂停操作
	 * 
	 * @throws Exception
	 */
	private void startCountingDown() throws Exception {
		for (int i = m_mainUI.getMaxTime() - 1; i > -1; i--) {
			try {
				Thread.sleep(1000);// 1秒
			} catch (InterruptedException e) {
				// 当本线程被中断时抛出异常通知线程结束
				throw new Exception(e);
			}
			// 暂停时的处理过程
			if (!isCounting()) {
				i++;// 为了保存暂停前的时间
				continue;
			}
			m_mainUI.setTimeTextWith("" + i);
		}
	}

	/**
	 * 处理分数问题
	 */
	private void processScore() {
		// 判断当前分数是否是最高分
		if (m_mainUI.getCurrentScore() > m_mainUI.getMaxScoreFromFile()) {
			m_mainUI.rewriteMaxScoreToFile(new Score(m_mainUI.getCurrentScore()));
			DialogManager.showCongratulationDialog(String.valueOf(m_mainUI
					.getCurrentScore()));
		}
	}
	
	/**
	 * 重开一局
	 */
	private void showNewGame() {
		m_mainUI.showNewGame();
	}
}