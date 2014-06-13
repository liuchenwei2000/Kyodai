/**
 * 
 */
package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;

import util.resource.MusicPlayer;

import geometry.Location;
import arithmetic.ArrayArithmetic;
import arithmetic.RemovableChecker;
import assistant.KyodaiConstant;
import assistant.Score;

/**
 * ��������ť����¼�������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-10-4
 */
public class KyodaiPanelEHD implements ActionListener {

	private KyodaiPanel m_kyodaiPanel;// ������
	private ButtonQueue<JButton> m_buttonQueue;// �������ť����
	private RemovableChecker m_removableChecker;// �����Լ�����
	
	private int m_counter;// ��ȥ��ť������(�����ж��Ƿ��Ѿ��������а�ť)
	private Score m_score;// ����

	public KyodaiPanelEHD(KyodaiPanel kyodaiPanel) {
		m_kyodaiPanel = kyodaiPanel;
		m_buttonQueue = new ButtonQueue<JButton>();
		m_removableChecker = new RemovableChecker(kyodaiPanel);
		m_counter = 0;
		m_score = new Score();
	}

	/**
	 * ��Ӧ�¼��Ĵ�����
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		JButton currentButton = (JButton) e.getSource();// ��ǰ�������ť
		MusicPlayer.playSelect();
		m_buttonQueue.offer(currentButton);
		// �����ǰ��������������ť���ж������Ƿ��ɾ��
		if (m_buttonQueue.isFull()) {
			JButton button1 = m_buttonQueue.getFirst();
			JButton button2 = m_buttonQueue.getLast();
			// �ж��Ƿ���ͬһ����ť
			if (button1 != button2) {
				// �ж��Ƿ�����ͬͼƬ�İ�ť
				if (hasTheSameIcon(button1, button2)) {
					if (isRemovable(button1, button2)) {
						MusicPlayer.playRemove();
						doRemove(button1, button2);
						if (isAllRemoved())
							doAfterAllRemoved();
						showScore();
					}
				}
			}
		}
	}

	/**
	 * �ж�������ť��ͼ���Ƿ�һ��
	 * 
	 * @param button1
	 *            ��ť1
	 * @param button2
	 *            ��ť2
	 * @return boolean
	 */
	private boolean hasTheSameIcon(JButton button1, JButton button2) {
		return hasTheSameActionCommand(button1, button2);
	}

	/**
	 * �ж�������ť��ActionCommand�Ƿ�һ��
	 * 
	 * @param button1
	 *            ��ť1
	 * @param button2
	 *            ��ť2
	 * @return boolean
	 */
	private boolean hasTheSameActionCommand(JButton button1, JButton button2) {
		return button1.getActionCommand().equals(button2.getActionCommand());
	}

	/**
	 * �ж�������ť�Ƿ��ɾ��
	 * 
	 * @param button1
	 *            ��ť1
	 * @param button2
	 *            ��ť2
	 * @return boolean
	 */
	private boolean isRemovable(JButton button1, JButton button2) {
		// ��ȡ��������ť��ģ�Ͷ�ά�����е�λ��
		Location location1 = getLocationInModel(button1);
		Location location2 = getLocationInModel(button2);
		return m_removableChecker.isRemovable(m_kyodaiPanel.getModel()
				.get2DArray(), location1, location2);
	}

	/**
	 * ��ȥ������ť�ľ��崦�����
	 * 
	 * @param button1
	 *            ��ť1
	 * @param button2
	 *            ��ť2
	 */
	private void doRemove(JButton button1, JButton button2) {
		// �ж��Ƿ�����ȥ�����а�ť
		if(hasRefreshIcon(button1))
		{
			int newNumber = m_kyodaiPanel.getRefreshNumberLeft();
			m_kyodaiPanel.setRefreshNumberLeft(newNumber + 1);
		}
		removeButtons(button1, button2);
		// ��հ�ť����
		m_buttonQueue.clear();
		// ��ȥ��ť������ + 2
		m_counter += 2;
		// ���� + INCREMENT_OF_SCORE
		m_score.addScore(KyodaiConstant.INCREMENT_OF_SCORE);
	}

	/**
	 * ��������ťִ��"ɾ��"���� 
	 * 1����Ϊ���ɼ� 2��ģ�Ͷ�ά����Ķ�ӦԪ����Ϊ0
	 * 
	 * @param button1
	 *            ��ť1
	 * @param button2
	 *            ��ť2
	 */
	private void removeButtons(JButton button1, JButton button2) {
		// �����ڰ�ť�ڽ����ϵĶ���Ϊ���ɼ���
		button1.setVisible(false);
		button2.setVisible(false);
		// ģ�͵Ķ�ά�����ж�ӦԪ�ر�Ϊ0
		setModelWithButtonState(button1);
		setModelWithButtonState(button2);
	}

	/**
	 * ����ģ�͵Ķ�ά�����а�ť��ӦԪ��Ϊ0
	 * 
	 * @param button
	 *            ��ť
	 */
	private void setModelWithButtonState(JButton button) {
		// ��ȡ��ť��ģ�Ͷ�ά�����е�λ������
		Location location = getLocationInModel(button);
		// ����ģ�������и�button��Ӧ����ֵΪ0
		m_kyodaiPanel.getModel().get2DArray()[location.getRow()][location
				.getColumn()] = 0;
	}

	/**
	 * �жϰ�ť�Ƿ�������ͼ��
	 */
	private boolean hasRefreshIcon(JButton button) {
		return getValueInModel2DArray(button) == KyodaiConstant.REFRESH_ICON;
	}
	
	/**
	 * ���ذ�ť��ģ�Ͷ�ά�����е�ֵ
	 * 
	 * @param button
	 *            ��ť
	 * @return int
	 */
	private int getValueInModel2DArray(JButton button) {
		// ��ȡ��ť��ģ�Ͷ�ά�����е�λ������
		Location location = getLocationInModel(button);
		// ����ģ�������и�button��Ӧ����ֵΪ0
		int value = m_kyodaiPanel.getModel().get2DArray()[location.getRow()][location
				.getColumn()];
		return value;
	}
	
	/**
	 * ���ذ�ť��ģ�Ͷ�ά�����е�λ��
	 * 
	 * @param button
	 *            ��ť
	 * @return Location
	 */
	private Location getLocationInModel(JButton button) {
		Location locationInButtons = getLocationInButtons(button);
		return getLocationInModel(locationInButtons);
	}

	/**
	 * Ѱ��Ŀ�갴ť�ڰ�ť��ά�����е�λ��
	 * 
	 * @param button
	 *            Ŀ�갴ť
	 * @return Location
	 */
	private Location getLocationInButtons(JButton button) {
		JButton[][] buttons = m_kyodaiPanel.getButtons();
		return ArrayArithmetic.getLocation(buttons, button);
	}

	/**
	 * ��ť��ά�����λ��ת��Ϊ��Ӧ��ģ�Ͷ�ά�����е�λ��
	 * 
	 * @param locationInButtons
	 *            ��ť�ڰ�ť�����е�λ��
	 * @return Location
	 */
	private Location getLocationInModel(Location locationInButtons) {
		return new Location(locationInButtons.getRow() + 1, locationInButtons
				.getColumn() + 1);
	}

	/**
	 * �жϰ�ť�Ƿ��Ѿ�ȫ������ȥ
	 * 
	 * @return boolean
	 */
	private boolean isAllRemoved() {
		int dimension = m_kyodaiPanel.getModel().getDimension();
		// ������ȥ��ť�ĸ����Ͱ�ť�ܸ������бȽ�
		return (m_counter == dimension * dimension);
	}

	/**
	 * ��ʾ��һ�ֵ���Ϸ��ť����
	 */
	private void showNewGamePanel() {
		m_kyodaiPanel.setNewModel();
		m_kyodaiPanel.repaintUsingCurrentModel();
	}

	/**
	 * һ�ֽ���֮��ľ��崦�����
	 */
	private void doAfterAllRemoved() {
		m_counter = 0;
		// ���ؽ�����
		m_score.addScore(getPrizeScore());
		showScore();
		showNewGamePanel();
		m_kyodaiPanel.restartCountingDown();
	}
	
	/**
	 * ���ع��ؽ�����(ά�� * 20)
	 */
	private int getPrizeScore() {
		return m_kyodaiPanel.getModel().getDimension() * 20;
	}
	
	/**
	 * �ڼƷ�����ʾ��ǰ����
	 */
	private void showScore() {
		m_kyodaiPanel.setScoreText(m_score.getScore() + "");
	}

	/**
	 * @return the m_ButtonQueue
	 */
	protected LinkedList<JButton> getButtonQueue() {
		return m_buttonQueue;
	}

	/**
	 * @param m_counter
	 *            the m_counter to set
	 */
	protected void setCounter(int counter) {
		this.m_counter = counter;
	}

	/**
	 * @return the score
	 */
	protected Score getScore() {
		return m_score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	protected void setScore(Score score) {
		this.m_score = score;
	}

	/**
	 * ��ť����(�ڲ���) 
	 * <strong> 
	 * Warning����ť���е�Ԫ����(size)���Ϊ2 
	 * ����һ��sizeΪ2(��ֻ�ж��׺Ͷ�β)�Ķ��������Ԫ��ʱ
	 * ��Ѷ���Ԫ���߳����У�Ȼ�����Ԫ�ؼ����β 
	 * </strong>
	 * 
	 * ����ʱ�䣺2007-10-20
	 * 
	 * @author ����ΰ
	 */
	@SuppressWarnings("hiding")
	private class ButtonQueue<JButton> extends LinkedList<JButton> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/** �����ɵĹ̶�Ԫ���� */
		private static final int SIZE = 2;

		public ButtonQueue() {
			super();
		}

		/**
		 * ���������Ԫ����Ϊ2�ٵ��ô˷������Ѷ���Ԫ���߳�Ȼ�������Ԫ�ص���β
		 * 
		 * @see java.util.LinkedList#offer(java.lang.Object)
		 */
		@Override
		public boolean offer(JButton o) {
			if (this.size() == SIZE) {
				// �߳�����Ԫ��
				this.poll();
			}
			return super.offer(o);
		}

		/**
		 * �ж϶����Ƿ�Ϊ��
		 * 
		 * @return boolean
		 */
		public boolean isEmpty() {
			return this.size() == 0;
		}

		/**
		 * �ж϶����Ƿ�Ϊ��(��Ԫ����Ϊ2)
		 * 
		 * @return boolean
		 */
		public boolean isFull() {
			return this.size() == SIZE;
		}
	}
}