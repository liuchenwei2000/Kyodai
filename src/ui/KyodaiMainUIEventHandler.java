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
 * 连连看主界面事件处理类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-10-11
 */
public class KyodaiMainUIEventHandler implements ActionListener {

	private KyodaiMainUI m_kyodaiUI;
	private int refreshNumberLeft;// 剩余重列次数

	public KyodaiMainUIEventHandler(KyodaiMainUI kyodaiUI) {
		m_kyodaiUI = kyodaiUI;
		refreshNumberLeft = kyodaiUI.getMaxRefresfNumber();
	}

	/**
	 * 返回主界面对象
	 */
	public KyodaiMainUI getKyodaiMainUI() {
		return m_kyodaiUI;
	}

	/**
	 * 设置剩余重列次数
	 */
	protected void setRefreshNumberLeft(int newNumber) {
		this.refreshNumberLeft = newNumber;
	}
	
	/**
	 * 返回当前剩余重列次数
	 */
	protected int getRefreshNumberLeft() {
		return refreshNumberLeft;
	}

	/**
	 * 响应事件的处理方法
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("暂停")) {
			pause();
		} else if (e.getActionCommand().equals("继续")) {
			goOn();
		} else if (e.getActionCommand().equals("重列")) {
			refresh();
		} else {
			doSelectedMenuItem(e.getActionCommand());
		}
	}

	/**
	 * 暂停游侠
	 */
	private void pause() {
		// 暂停倒计时
		pauseCountingDown();
		// 设置组件状态(游戏画面不可见，除了暂停按钮其他按钮和菜单项都不可用)
		setComponentsState(false);
		// 重新设置暂停按钮的信息
		resetButtonPause("  继续  ");
	}

	/**
	 * 暂停倒计时
	 */
	private void pauseCountingDown() {
		m_kyodaiUI.pauseCountingDown();
	}

	/**
	 * 设置各组件状态
	 */
	private void setComponentsState(boolean state) {
		setGamePanelVisible(state);
		setButtonRefreshState(state);
		setMenusState(state);
	}

	/**
	 * 设置游戏面板可见性
	 */
	private void setGamePanelVisible(boolean state) {
		m_kyodaiUI.setGamePanelVisible(state);
	}

	/**
	 * 设置重列按钮可用性
	 */
	private void setButtonRefreshState(boolean state) {
		m_kyodaiUI.getButtonRefresh().setEnabled(state);
	}

	/**
	 * 设置菜单栏的可用性
	 */
	private void setMenusState(boolean state) {
		m_kyodaiUI.setMenusState(state);
	}

	/**
	 * 重新设置暂停按钮
	 */
	private void resetButtonPause(String text) {
		m_kyodaiUI.resetButtonPause(text);
	}

	/**
	 * 继续游戏
	 */
	private void goOn() {
		goOnCountingDown();
		setComponentsState(true);
		resetButtonPause("  暂停  ");
	}

	/**
	 * 继续倒计时
	 */
	private void goOnCountingDown() {
		m_kyodaiUI.goOnCountingDown();
	}

	/**
	 * 执行重列操作
	 */
	private void refresh() {
		MusicPlayer.playRefresh();
		refreshNumberLeft--;
		showRefreshNumber();
		refreshKyodaiPanel();
	}

	/**
	 * 在按钮上显示重列次数
	 */
	protected void showRefreshNumber() {
		if (refreshNumberLeft == 0)
			setButtonRefreshState(false);
		else
			setButtonRefreshState(true);
		m_kyodaiUI.getButtonRefresh().setText("重列 (" + refreshNumberLeft + ")");
	}

	/**
	 * 重列连连看面板按钮
	 */
	private void refreshKyodaiPanel() {
		// 获取当前模型的二维数组
		int[][] the2DArray = getKyodaiPanel().getModel().get2DArray();
		// 重排模型中二维数组中的元素
		ResetArithmetic.reset2DArray(the2DArray);
		// 根据当前模型重新绘制界面
		getKyodaiPanel().repaintUsingCurrentModel();
		clearButtonQueue();
	}

	/**
	 * 返回连连看主面板
	 */
	private KyodaiPanel getKyodaiPanel() {
		return m_kyodaiUI.getKyodaiPanel();
	}

	/**
	 * 清空按钮队列
	 */
	private void clearButtonQueue() {
		m_kyodaiUI.getButtonQueue().clear();
	}

	/**
	 * 选择菜单事件处理
	 */
	private void doSelectedMenuItem(String actionCommand) {
		if (actionCommand.equals("开始")) {
			start();
		} else if (actionCommand.equals("重新开始")) {
			restart();
		} else if (actionCommand.equals("退出")) {
			exit();
		} else if (actionCommand.equals("关闭音效")) {
			turnOffMusic();
		} else if (actionCommand.equals("打开音效")) {
			turnOnMusic();
		} else if (actionCommand.equals("最高分")) {
			showMaxScoreDialog();
		} else if (actionCommand.equals("关于连连看")) {
			showAboutDialog();
		} else {
			doSelectedDifficulty(actionCommand);
		}
	}

	/**
	 * 执行开始操作
	 */
	private void start() {
		m_kyodaiUI.setStarted(true);
		m_kyodaiUI.setButtonsAndItemsState();
		m_kyodaiUI.getKyodaiPanel().setVisible(true);
		m_kyodaiUI.startCountingDown();
	}

	/**
	 * 重新开始
	 */
	private void restart() {
		setScoreWithDefault();
		setRefreshWithDefault();
		restartCountingDown();
		updateKyodaiPanel();
	}

	/**
	 * 设置分数和分数栏为默认值
	 */
	private void setScoreWithDefault() {
		getKyodaiPanel().resetScore();
		m_kyodaiUI.setScoreTextWithDefault();
	}

	/**
	 * 设置重列次数和重列按钮为默认值
	 */
	private void setRefreshWithDefault() {
		setRefreshNumberLeft(m_kyodaiUI.getMaxRefresfNumber());
		m_kyodaiUI.setButtonRefreshWithDefault();
	}

	/**
	 * 重新开始计时
	 */
	private void restartCountingDown() {
		m_kyodaiUI.setTimeWithDefaultAndRestart();
	}

	/**
	 * 连连看按钮面板的更新
	 */
	private void updateKyodaiPanel() {
		getKyodaiPanel().setNewModel();
		getKyodaiPanel().repaintUsingCurrentModel();
		getKyodaiPanel().resetButtonCounter();
		clearButtonQueue();
	}

	/**
	 * 退出程序
	 */
	private void exit() {
		System.exit(0);
	}

	/**
	 * 关闭音效
	 */
	private void turnOffMusic() {
		MusicPlayer.stop();
	}

	/**
	 * 打开音效
	 */
	private void turnOnMusic() {
		MusicPlayer.start();
	}

	/**
	 * 显示"关于"信息
	 */
	private void showAboutDialog() {
		StringBuilder message = new StringBuilder("Author : liuchenwei2000\n");
		message.append("Version : " + KyodaiConstant.VERSION + "\n");
		message.append("E-mail : liuchenwei2000@163.com\n");
		message.append("如有bug请联系我，谢谢！");
		DialogManager.showMessageDialog(message.toString(), "About");
	}

	/**
	 * 显示"最高分"信息
	 */
	private void showMaxScoreDialog() {
		String maxScore = "最高分：" + m_kyodaiUI.getMaxScoreFromFile();
		DialogManager.showMessageDialog(maxScore, "Max Score");
	}

	/**
	 * 选择 难度选择栏按钮项 事件处理
	 */
	private void doSelectedDifficulty(String actionCommand) {
		if (actionCommand.equals("初级")) {
			new KyodaiMainUI(KyodaiConstant.LOW);
		} else if (actionCommand.equals("中级")) {
			new KyodaiMainUI(KyodaiConstant.MIDDLE);
		} else if (actionCommand.equals("高级")) {
			new KyodaiMainUI(KyodaiConstant.HIGH);
		} else if (actionCommand.equals("自定义")) {
			try {
				startCustomGame();
			} catch (Exception e) {
				return;
			}
		}
		// 停止原有的倒计时线程
		m_kyodaiUI.stopCountingDown();
		m_kyodaiUI.dispose();
	}

	/**
	 * 开始自定义游戏(模型维数20(含)之内)
	 * 
	 * @throws Exception
	 */
	private void startCustomGame() throws Exception {
		String input = DialogManager.showInputDialog("请输入自定义难度(20之内正偶数)：",
				String.valueOf(KyodaiConstant.LOW));
		// 点击取消事件处理(只是作为flag)
		if (input == null)
			throw new Exception("取消输入");
		// 判断输入合法性
		if (StringUtil.isPositiveNumber(input)
				&& StringUtil.isEvenNumber(input)
				&& Integer.parseInt(input) < 21) {
			new KyodaiMainUI(Integer.parseInt(input));
		} else {
			DialogManager.showErrorDialog("您的输入不正确请重新输入！");
			startCustomGame();
		}
	}
}