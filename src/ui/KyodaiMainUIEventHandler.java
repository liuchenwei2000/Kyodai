/**
 * 
 */
package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panel.KyodaiPanel;

import util.other.DialogManager;
import util.other.StringUtil;
import util.resource.MusicPlayer;

import arithmetic.ResetArithmetic;
import assistant.KyodaiConstant;

/**
 * �������������¼�������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-10-11
 */
public class KyodaiMainUIEventHandler implements ActionListener {

	private KyodaiMainUI m_kyodaiUI;
	private int refreshNumberLeft;// ʣ�����д���

	public KyodaiMainUIEventHandler(KyodaiMainUI kyodaiUI) {
		m_kyodaiUI = kyodaiUI;
		refreshNumberLeft = kyodaiUI.getMaxRefresfNumber();
	}

	/**
	 * �������������
	 */
	public KyodaiMainUI getKyodaiMainUI() {
		return m_kyodaiUI;
	}

	/**
	 * ����ʣ�����д���
	 */
	protected void setRefreshNumberLeft(int newNumber) {
		this.refreshNumberLeft = newNumber;
	}
	
	/**
	 * ���ص�ǰʣ�����д���
	 */
	protected int getRefreshNumberLeft() {
		return refreshNumberLeft;
	}

	/**
	 * ��Ӧ�¼��Ĵ�����
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("��ͣ")) {
			pause();
		} else if (e.getActionCommand().equals("����")) {
			goOn();
		} else if (e.getActionCommand().equals("����")) {
			refresh();
		} else {
			doSelectedMenuItem(e.getActionCommand());
		}
	}

	/**
	 * ��ͣ����
	 */
	private void pause() {
		// ��ͣ����ʱ
		pauseCountingDown();
		// �������״̬(��Ϸ���治�ɼ���������ͣ��ť������ť�Ͳ˵��������)
		setComponentsState(false);
		// ����������ͣ��ť����Ϣ
		resetButtonPause("  ����  ");
	}

	/**
	 * ��ͣ����ʱ
	 */
	private void pauseCountingDown() {
		m_kyodaiUI.pauseCountingDown();
	}

	/**
	 * ���ø����״̬
	 */
	private void setComponentsState(boolean state) {
		setGamePanelVisible(state);
		setButtonRefreshState(state);
		setMenusState(state);
	}

	/**
	 * ������Ϸ���ɼ���
	 */
	private void setGamePanelVisible(boolean state) {
		m_kyodaiUI.setGamePanelVisible(state);
	}

	/**
	 * �������а�ť������
	 */
	private void setButtonRefreshState(boolean state) {
		m_kyodaiUI.getButtonRefresh().setEnabled(state);
	}

	/**
	 * ���ò˵����Ŀ�����
	 */
	private void setMenusState(boolean state) {
		m_kyodaiUI.setMenusState(state);
	}

	/**
	 * ����������ͣ��ť
	 */
	private void resetButtonPause(String text) {
		m_kyodaiUI.resetButtonPause(text);
	}

	/**
	 * ������Ϸ
	 */
	private void goOn() {
		goOnCountingDown();
		setComponentsState(true);
		resetButtonPause("  ��ͣ  ");
	}

	/**
	 * ��������ʱ
	 */
	private void goOnCountingDown() {
		m_kyodaiUI.goOnCountingDown();
	}

	/**
	 * ִ�����в���
	 */
	private void refresh() {
		MusicPlayer.playRefresh();
		refreshNumberLeft--;
		showRefreshNumber();
		refreshKyodaiPanel();
	}

	/**
	 * �ڰ�ť����ʾ���д���
	 */
	protected void showRefreshNumber() {
		if (refreshNumberLeft == 0)
			setButtonRefreshState(false);
		else
			setButtonRefreshState(true);
		m_kyodaiUI.getButtonRefresh().setText("���� (" + refreshNumberLeft + ")");
	}

	/**
	 * ������������尴ť
	 */
	private void refreshKyodaiPanel() {
		// ��ȡ��ǰģ�͵Ķ�ά����
		int[][] the2DArray = getKyodaiPanel().getModel().get2DArray();
		// ����ģ���ж�ά�����е�Ԫ��
		ResetArithmetic.reset2DArray(the2DArray);
		// ���ݵ�ǰģ�����»��ƽ���
		getKyodaiPanel().repaintUsingCurrentModel();
		clearButtonQueue();
	}

	/**
	 * ���������������
	 */
	private KyodaiPanel getKyodaiPanel() {
		return m_kyodaiUI.getKyodaiPanel();
	}

	/**
	 * ��հ�ť����
	 */
	private void clearButtonQueue() {
		m_kyodaiUI.getButtonQueue().clear();
	}

	/**
	 * ѡ��˵��¼�����
	 */
	private void doSelectedMenuItem(String actionCommand) {
		if (actionCommand.equals("��ʼ")) {
			start();
		} else if (actionCommand.equals("���¿�ʼ")) {
			restart();
		} else if (actionCommand.equals("�˳�")) {
			exit();
		} else if (actionCommand.equals("�ر���Ч")) {
			turnOffMusic();
		} else if (actionCommand.equals("����Ч")) {
			turnOnMusic();
		} else if (actionCommand.equals("��߷�")) {
			showMaxScoreDialog();
		} else if (actionCommand.equals("����������")) {
			showAboutDialog();
		} else {
			doSelectedDifficulty(actionCommand);
		}
	}

	/**
	 * ִ�п�ʼ����
	 */
	private void start() {
		m_kyodaiUI.setStarted(true);
		m_kyodaiUI.setButtonsAndItemsState();
		m_kyodaiUI.getKyodaiPanel().setVisible(true);
		m_kyodaiUI.startCountingDown();
	}

	/**
	 * ���¿�ʼ
	 */
	private void restart() {
		setScoreWithDefault();
		setRefreshWithDefault();
		restartCountingDown();
		updateKyodaiPanel();
	}

	/**
	 * ���÷����ͷ�����ΪĬ��ֵ
	 */
	private void setScoreWithDefault() {
		getKyodaiPanel().resetScore();
		m_kyodaiUI.setScoreTextWithDefault();
	}

	/**
	 * �������д��������а�ťΪĬ��ֵ
	 */
	private void setRefreshWithDefault() {
		setRefreshNumberLeft(m_kyodaiUI.getMaxRefresfNumber());
		m_kyodaiUI.setButtonRefreshWithDefault();
	}

	/**
	 * ���¿�ʼ��ʱ
	 */
	private void restartCountingDown() {
		m_kyodaiUI.setTimeWithDefaultAndRestart();
	}

	/**
	 * ��������ť���ĸ���
	 */
	private void updateKyodaiPanel() {
		getKyodaiPanel().setNewModel();
		getKyodaiPanel().repaintUsingCurrentModel();
		getKyodaiPanel().resetButtonCounter();
		clearButtonQueue();
	}

	/**
	 * �˳�����
	 */
	private void exit() {
		System.exit(0);
	}

	/**
	 * �ر���Ч
	 */
	private void turnOffMusic() {
		MusicPlayer.stop();
	}

	/**
	 * ����Ч
	 */
	private void turnOnMusic() {
		MusicPlayer.start();
	}

	/**
	 * ��ʾ"����"��Ϣ
	 */
	private void showAboutDialog() {
		StringBuilder message = new StringBuilder("Author : liuchenwei2000\n");
		message.append("Version : " + KyodaiConstant.VERSION + "\n");
		message.append("E-mail : liuchenwei2000@163.com\n");
		message.append("����bug����ϵ�ң�лл��");
		DialogManager.showMessageDialog(message.toString(), "About");
	}

	/**
	 * ��ʾ"��߷�"��Ϣ
	 */
	private void showMaxScoreDialog() {
		String maxScore = "��߷֣�" + m_kyodaiUI.getMaxScoreFromFile();
		DialogManager.showMessageDialog(maxScore, "Max Score");
	}

	/**
	 * ѡ�� �Ѷ�ѡ������ť�� �¼�����
	 */
	private void doSelectedDifficulty(String actionCommand) {
		if (actionCommand.equals("����")) {
			new KyodaiMainUI(KyodaiConstant.LOW);
		} else if (actionCommand.equals("�м�")) {
			new KyodaiMainUI(KyodaiConstant.MIDDLE);
		} else if (actionCommand.equals("�߼�")) {
			new KyodaiMainUI(KyodaiConstant.HIGH);
		} else if (actionCommand.equals("�Զ���")) {
			try {
				startCustomGame();
			} catch (Exception e) {
				return;
			}
		}
		// ֹͣԭ�еĵ���ʱ�߳�
		m_kyodaiUI.stopCountingDown();
		m_kyodaiUI.dispose();
	}

	/**
	 * ��ʼ�Զ�����Ϸ(ģ��ά��20(��)֮��)
	 * 
	 * @throws Exception
	 */
	private void startCustomGame() throws Exception {
		String input = DialogManager.showInputDialog("�������Զ����Ѷ�(20֮����ż��)��",
				String.valueOf(KyodaiConstant.LOW));
		// ���ȡ���¼�����(ֻ����Ϊflag)
		if (input == null)
			throw new Exception("ȡ������");
		// �ж�����Ϸ���
		if (StringUtil.isPositiveNumber(input)
				&& StringUtil.isEvenNumber(input)
				&& Integer.parseInt(input) < 21) {
			new KyodaiMainUI(Integer.parseInt(input));
		} else {
			DialogManager.showErrorDialog("�������벻��ȷ���������룡");
			startCustomGame();
		}
	}
}