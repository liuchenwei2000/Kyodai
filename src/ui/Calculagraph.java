/**
 * 
 */
package ui;

import assistant.Score;
import util.other.DialogManager;

/**
 * ����ʱ����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-10-25
 */
public class Calculagraph implements Runnable {

	private KyodaiMainUI m_mainUI;
	private boolean isCounting;// �Ƿ����ڵ���ʱ(״̬��־)

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
			// ���ж��˾�ֱ�ӽ����߳�
			return;
		}
		// ����ʱ��������ʾʧ����Ϣ
		DialogManager.showFailureDialog();
		processScore();
		showNewGame();
	}

	/**
	 * ���ص���ʱ���Ƿ����ڵ���ʱ
	 */
	private boolean isCounting() {
		return isCounting;
	}

	/**
	 * ���õ���ʱ����״̬
	 */
	private void setCounting(boolean isCounting) {
		this.isCounting = isCounting;
	}
	
	/**
	 * ��ͣ����ʱ
	 */
	protected void pauseCountingDown() {
		this.setCounting(false);
	}
	
	/**
	 * ��������ʱ
	 */
	protected void goOnCountingDown() {
		this.setCounting(true);
	}
	
	/**
	 * ��ʼ����ʱ
	 * 
	 * ���״̬��־����Ϊfalse����Ϊ����ͣ����
	 * 
	 * @throws Exception
	 */
	private void startCountingDown() throws Exception {
		for (int i = m_mainUI.getMaxTime() - 1; i > -1; i--) {
			try {
				Thread.sleep(1000);// 1��
			} catch (InterruptedException e) {
				// �����̱߳��ж�ʱ�׳��쳣֪ͨ�߳̽���
				throw new Exception(e);
			}
			// ��ͣʱ�Ĵ������
			if (!isCounting()) {
				i++;// Ϊ�˱�����ͣǰ��ʱ��
				continue;
			}
			m_mainUI.setTimeTextWith("" + i);
		}
	}

	/**
	 * �����������
	 */
	private void processScore() {
		// �жϵ�ǰ�����Ƿ�����߷�
		if (m_mainUI.getCurrentScore() > m_mainUI.getMaxScoreFromFile()) {
			m_mainUI.rewriteMaxScoreToFile(new Score(m_mainUI.getCurrentScore()));
			DialogManager.showCongratulationDialog(String.valueOf(m_mainUI
					.getCurrentScore()));
		}
	}
	
	/**
	 * �ؿ�һ��
	 */
	private void showNewGame() {
		m_mainUI.showNewGame();
	}
}