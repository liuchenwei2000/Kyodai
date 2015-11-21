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
 * 连连看按钮面板事件处理类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-10-4
 */
public class KyodaiPanelEHD implements ActionListener {

	private KyodaiPanel m_kyodaiPanel;// 面板对象
	private ButtonQueue<JButton> m_buttonQueue;// 被点击按钮队列
	private RemovableChecker m_removableChecker;// 可消性检查对象
	
	private int m_counter;// 消去按钮计数器(用于判断是否已经消完所有按钮)
	private Score m_score;// 分数

	public KyodaiPanelEHD(KyodaiPanel kyodaiPanel) {
		m_kyodaiPanel = kyodaiPanel;
		m_buttonQueue = new ButtonQueue<JButton>();
		m_removableChecker = new RemovableChecker(kyodaiPanel);
		m_counter = 0;
		m_score = new Score();
	}

	/**
	 * 响应事件的处理方法
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		JButton currentButton = (JButton) e.getSource();// 当前被点击按钮
		MusicPlayer.playSelect();
		m_buttonQueue.offer(currentButton);
		// 如果当前队列内有两个按钮则判断它们是否可删除
		if (m_buttonQueue.isFull()) {
			JButton button1 = m_buttonQueue.getFirst();
			JButton button2 = m_buttonQueue.getLast();
			// 判断是否是同一个按钮
			if (button1 != button2) {
				// 判断是否是相同图片的按钮
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
	 * 判断两个按钮的图标是否一样
	 * 
	 * @param button1
	 *            按钮1
	 * @param button2
	 *            按钮2
	 * @return boolean
	 */
	private boolean hasTheSameIcon(JButton button1, JButton button2) {
		return hasTheSameActionCommand(button1, button2);
	}

	/**
	 * 判断两个按钮的ActionCommand是否一样
	 * 
	 * @param button1
	 *            按钮1
	 * @param button2
	 *            按钮2
	 * @return boolean
	 */
	private boolean hasTheSameActionCommand(JButton button1, JButton button2) {
		return button1.getActionCommand().equals(button2.getActionCommand());
	}

	/**
	 * 判断两个按钮是否可删除
	 * 
	 * @param button1
	 *            按钮1
	 * @param button2
	 *            按钮2
	 * @return boolean
	 */
	private boolean isRemovable(JButton button1, JButton button2) {
		// 获取这两个按钮在模型二维数组中的位置
		Location location1 = getLocationInModel(button1);
		Location location2 = getLocationInModel(button2);
		return m_removableChecker.isRemovable(m_kyodaiPanel.getModel()
				.get2DArray(), location1, location2);
	}

	/**
	 * 消去两个按钮的具体处理过程
	 * 
	 * @param button1
	 *            按钮1
	 * @param button2
	 *            按钮2
	 */
	private void doRemove(JButton button1, JButton button2) {
		// 判断是否是消去了重列按钮
		if(hasRefreshIcon(button1))
		{
			int newNumber = m_kyodaiPanel.getRefreshNumberLeft();
			m_kyodaiPanel.setRefreshNumberLeft(newNumber + 1);
		}
		removeButtons(button1, button2);
		// 清空按钮队列
		m_buttonQueue.clear();
		// 消去按钮计数器 + 2
		m_counter += 2;
		// 分数 + INCREMENT_OF_SCORE
		m_score.addScore(KyodaiConstant.INCREMENT_OF_SCORE);
	}

	/**
	 * 对两个按钮执行"删除"操作 
	 * 1，设为不可见 2，模型二维数组的对应元素设为0
	 * 
	 * @param button1
	 *            按钮1
	 * @param button2
	 *            按钮2
	 */
	private void removeButtons(JButton button1, JButton button2) {
		// 队列内按钮在界面上的都变为不可见的
		button1.setVisible(false);
		button2.setVisible(false);
		// 模型的二维数组中对应元素变为0
		setModelWithButtonState(button1);
		setModelWithButtonState(button2);
	}

	/**
	 * 设置模型的二维数组中按钮对应元素为0
	 * 
	 * @param button
	 *            按钮
	 */
	private void setModelWithButtonState(JButton button) {
		// 获取按钮在模型二维数组中的位置数组
		Location location = getLocationInModel(button);
		// 设置模型数组中该button对应的数值为0
		m_kyodaiPanel.getModel().get2DArray()[location.getRow()][location
				.getColumn()] = 0;
	}

	/**
	 * 判断按钮是否是重列图标
	 */
	private boolean hasRefreshIcon(JButton button) {
		return getValueInModel2DArray(button) == KyodaiConstant.REFRESH_ICON;
	}
	
	/**
	 * 返回按钮在模型二维数组中的值
	 * 
	 * @param button
	 *            按钮
	 * @return int
	 */
	private int getValueInModel2DArray(JButton button) {
		// 获取按钮在模型二维数组中的位置数组
		Location location = getLocationInModel(button);
		// 设置模型数组中该button对应的数值为0
		int value = m_kyodaiPanel.getModel().get2DArray()[location.getRow()][location
				.getColumn()];
		return value;
	}
	
	/**
	 * 返回按钮在模型二维数组中的位置
	 * 
	 * @param button
	 *            按钮
	 * @return Location
	 */
	private Location getLocationInModel(JButton button) {
		Location locationInButtons = getLocationInButtons(button);
		return getLocationInModel(locationInButtons);
	}

	/**
	 * 寻找目标按钮在按钮二维数组中的位置
	 * 
	 * @param button
	 *            目标按钮
	 * @return Location
	 */
	private Location getLocationInButtons(JButton button) {
		JButton[][] buttons = m_kyodaiPanel.getButtons();
		return ArrayArithmetic.getLocation(buttons, button);
	}

	/**
	 * 按钮二维数组的位置转换为对应在模型二维数组中的位置
	 * 
	 * @param locationInButtons
	 *            按钮在按钮数组中的位置
	 * @return Location
	 */
	private Location getLocationInModel(Location locationInButtons) {
		return new Location(locationInButtons.getRow() + 1, locationInButtons
				.getColumn() + 1);
	}

	/**
	 * 判断按钮是否已经全部被消去
	 * 
	 * @return boolean
	 */
	private boolean isAllRemoved() {
		int dimension = m_kyodaiPanel.getModel().getDimension();
		// 根据消去按钮的个数和按钮总个数进行比较
		return (m_counter == dimension * dimension);
	}

	/**
	 * 显示新一局的游戏按钮界面
	 */
	private void showNewGamePanel() {
		m_kyodaiPanel.setNewModel();
		m_kyodaiPanel.repaintUsingCurrentModel();
	}

	/**
	 * 一局结束之后的具体处理过程
	 */
	private void doAfterAllRemoved() {
		m_counter = 0;
		// 过关奖励分
		m_score.addScore(getPrizeScore());
		showScore();
		showNewGamePanel();
		m_kyodaiPanel.restartCountingDown();
	}
	
	/**
	 * 返回过关奖励分(维数 * 20)
	 */
	private int getPrizeScore() {
		return m_kyodaiPanel.getModel().getDimension() * 20;
	}
	
	/**
	 * 在计分栏显示当前分数
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
	 * 按钮队列(内部类) 
	 * <strong> 
	 * Warning：按钮队列的元素数(size)最多为2 
	 * 当向一个size为2(即只有队首和队尾)的队列再添加元素时
	 * 会把队首元素踢出队列，然后把新元素加入队尾 
	 * </strong>
	 * 
	 * 创建时间：2007-10-20
	 * 
	 * @author 刘晨伟
	 */
	@SuppressWarnings("hiding")
	private class ButtonQueue<JButton> extends LinkedList<JButton> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/** 所容纳的固定元素数 */
		private static final int SIZE = 2;

		public ButtonQueue() {
			super();
		}

		/**
		 * 如果队列内元素数为2再调用此方法则会把队首元素踢出然后添加新元素到队尾
		 * 
		 * @see java.util.LinkedList#offer(java.lang.Object)
		 */
		@Override
		public boolean offer(JButton o) {
			if (this.size() == SIZE) {
				// 踢出队首元素
				this.poll();
			}
			return super.offer(o);
		}

		/**
		 * 判断队列是否为空
		 * 
		 * @return boolean
		 */
		public boolean isEmpty() {
			return this.size() == 0;
		}

		/**
		 * 判断队列是否为满(即元素数为2)
		 * 
		 * @return boolean
		 */
		public boolean isFull() {
			return this.size() == SIZE;
		}
	}
}
