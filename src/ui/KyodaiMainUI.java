/**
 * 
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import assistant.KyodaiConstant;
import assistant.MyFrame;
import assistant.Score;

import panel.KyodaiPanel;
import util.other.ParameterChecker;
import util.resource.ImageCreator;
import util.resource.ScoreFileManager;

/**
 * 连连看主界面类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-10-11
 */
public class KyodaiMainUI extends MyFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 菜单栏 */
	private JMenuBar menuBar;

	private JMenu manageMenu;// 操作菜单
	private JMenuItem startItem;// 开始菜单项
	private JMenuItem restartItem;// 重新开始菜单项

	private JMenu difficultyMenu;// 难度选择菜单
	private JMenu settingMenu;// 设置菜单
	private JMenu helpMenu;// 帮助菜单

	/** 连连看主面板 */
	private KyodaiPanel kyodaiPanel;

	/** 功能面板 */
	private JPanel functionPanel;

	private JLabel scoreLabel;// 计分标签
	private JTextField scoreTextField;// 计分栏

	private JButton buttonPause;// 开始按钮
	private JButton buttonRefresh;// 重列按钮

	private JLabel timeLabel;// 倒计时标签
	private JTextField timeTextField;// 倒计时栏

	private KyodaiMainUIEventHandler ehd;// 事件处理对象

	private int m_dimension;// 构建按钮面板的维数

	private boolean isStarted = false;// 是否已经开始标识符

	private Thread countDown;// 倒计时器运行的线程
	private Calculagraph calculagraph;// 倒计时器对象

	public KyodaiMainUI(int dimension) {
		super();
		ParameterChecker.checkDimension(dimension);
		setDimension(dimension);
		setLayout(new BorderLayout());

		// 增加组件
		addMenuBar();
		addKyodaiPanel();
		addFunctionPanel();

		// 设置界面大小
		setSize(getDimension() * KyodaiConstant.LENGTH_OF_BUTTON + 170,
				getDimension() * KyodaiConstant.WIDTH_OF_BUTTON + 110);

		setTitle(KyodaiConstant.VERSION);
		setMyCursor();
		setMinimumIcon();
		setResizable(false);
		setCenter();
		setVisible(true);

		startCountingDown();
	}

	/**
	 * 添加菜单栏
	 */
	private void addMenuBar() {
		addMenus();
		setJMenuBar(menuBar);
	}

	/**
	 * 为菜单栏添加菜单
	 */
	private void addMenus() {
		if (menuBar == null)
			menuBar = new JMenuBar();
		menuBar.add(getManageMenu());
		menuBar.add(getDifficultyMenu());
		menuBar.add(getSettingMenu());
		menuBar.add(getHelpMenu());
	}

	/**
	 * 操作菜单栏
	 */
	private JMenu getManageMenu() {
		if (manageMenu == null) {
			manageMenu = new JMenu("操作");
			manageMenu.setFont(new Font("宋体", Font.PLAIN, 12));

			manageMenu.add(getStartItem());
			manageMenu.add(getRestartItem());
			manageMenu.addSeparator();// 分隔符
			manageMenu.add(createMenuItemWithListener("退出"));
		}
		return manageMenu;
	}

	/**
	 * 开始菜单项
	 */
	private JMenuItem getStartItem() {
		if (startItem == null) {
			startItem = createMenuItemWithListener("开始");
		}
		return startItem;
	}

	/**
	 * 重新开始菜单项
	 */
	private JMenuItem getRestartItem() {
		if (restartItem == null) {
			restartItem = createMenuItemWithListener("重新开始");
		}
		return restartItem;
	}

	/**
	 * 难度选择菜单栏
	 */
	private JMenu getDifficultyMenu() {
		if (difficultyMenu == null) {
			difficultyMenu = new JMenu("难度选择");
			difficultyMenu.setFont(new Font("宋体", Font.PLAIN, 12));

			difficultyMenu.add(createMenuItemWithListener("初级"));
			difficultyMenu.add(createMenuItemWithListener("中级"));
			difficultyMenu.add(createMenuItemWithListener("高级"));
			difficultyMenu.add(createMenuItemWithListener("自定义"));
		}
		return difficultyMenu;
	}

	/**
	 * 设置菜单栏
	 */
	private JMenu getSettingMenu() {
		if (settingMenu == null) {
			settingMenu = new JMenu("设置");
			settingMenu.setFont(new Font("宋体", Font.PLAIN, 12));
			settingMenu.add(createMenuItemWithListener("打开音效"));
			settingMenu.add(createMenuItemWithListener("关闭音效"));
		}
		return settingMenu;
	}

	/**
	 * 帮助菜单栏
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu("帮助");
			helpMenu.setFont(new Font("宋体", Font.PLAIN, 12));
			helpMenu.add(createMenuItemWithListener("最高分"));
			helpMenu.addSeparator();// 分隔符
			helpMenu.add(createMenuItemWithListener("关于连连看"));
		}
		return helpMenu;
	}

	/**
	 * 用指定名创建带有监听器菜单按钮
	 * 
	 * @param name
	 *            菜单按钮的显示名称
	 * @return JMenuItem
	 */
	private JMenuItem createMenuItemWithListener(String name) {
		JMenuItem item = new JMenuItem(name);
		item.setFont(new Font("宋体", Font.PLAIN, 12));
		item.addActionListener(getEhd());
		return item;
	}

	/**
	 * 添加连连看主面板
	 */
	private void addKyodaiPanel() {
		// 开始状态不可见
		getKyodaiPanel().setVisible(isStarted());
		add(getKyodaiPanel(), BorderLayout.CENTER);
	}

	/**
	 * 返回连连看主面板
	 */
	public KyodaiPanel getKyodaiPanel() {
		if (kyodaiPanel == null) {
			kyodaiPanel = new KyodaiPanel(getDimension());
			// 为了和主面板进行通信以设置分数
			kyodaiPanel.setMainUIEhd(getEhd());
			// 根据模型的维数设置面板大小
			kyodaiPanel.setSize(getDimension()
					* KyodaiConstant.LENGTH_OF_BUTTON
					+ KyodaiConstant.EXTENSION_LENGTH, getDimension()
					* KyodaiConstant.WIDTH_OF_BUTTON
					+ KyodaiConstant.EXTENSION_WIDTH);
		}
		return kyodaiPanel;
	}

	/**
	 * 添加功能面板
	 */
	private void addFunctionPanel() {
		setButtonsAndItemsState();
		add(getFunctionPanel(), BorderLayout.EAST);
	}

	/**
	 * 设置按钮和菜单项状态
	 */
	protected void setButtonsAndItemsState() {
		// 开始之后"开始菜单项"不可见
		getStartItem().setEnabled(!isStarted());
		getRestartItem().setEnabled(isStarted());
		setButtonsState(isStarted());
	}

	/**
	 * 设置按钮的可用性
	 */
	private void setButtonsState(boolean state) {
		getButtonPause().setEnabled(state);
		getButtonRefresh().setEnabled(state);
	}

	/**
	 * 返回功能面板
	 */
	private JPanel getFunctionPanel() {
		if (functionPanel == null) {
			FlowLayout flow = new FlowLayout();
			// 设置列间距
			flow.setVgap(15);
			functionPanel = new JPanel(flow);
			functionPanel.setPreferredSize(new Dimension(100, 40));
			functionPanel.add(getScoreLabel());
			functionPanel.add(getScoreTextField());
			functionPanel.add(getButtonPause());
			functionPanel.add(getButtonRefresh());
			functionPanel.add(getTimeLabel());
			functionPanel.add(getTimeTextField());
			addButtonsListeners();
		}
		return functionPanel;
	}

	/**
	 * 返回分数标签
	 */
	private JLabel getScoreLabel() {
		if (scoreLabel == null) {
			scoreLabel = new JLabel("分 数：");
			scoreLabel.setForeground(Color.BLUE);
			scoreLabel.setPreferredSize(new Dimension(80, 12));
			scoreLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		}
		return scoreLabel;
	}

	/**
	 * 返回分数栏
	 */
	private JTextField getScoreTextField() {
		if (scoreTextField == null) {
			scoreTextField = new JTextField("0");
			scoreTextField.setForeground(Color.DARK_GRAY);
			scoreTextField.setPreferredSize(new Dimension(80, 20));
			// 设置组件布局方向(从右到左)
			scoreTextField
					.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			scoreTextField.setBorder(new LineBorder(Color.MAGENTA));
			scoreTextField.setFont(new Font("宋体", Font.BOLD, 16));
			scoreTextField.setEditable(false);
		}
		return scoreTextField;
	}

	/**
	 * 返回开始按钮
	 */
	protected JButton getButtonPause() {
		if (buttonPause == null) {
			buttonPause = new JButton("  暂停  ");
			buttonPause.setActionCommand("暂停");
			buttonPause.setFont(new Font("宋体", Font.PLAIN, 12));
			buttonPause.setForeground(Color.RED);
		}
		return buttonPause;
	}

	/**
	 * 返回重列按钮
	 */
	protected JButton getButtonRefresh() {
		if (buttonRefresh == null) {
			buttonRefresh = new JButton("重列 (" + getMaxRefresfNumber() + ")");
			buttonRefresh.setActionCommand("重列");
			buttonRefresh.setFont(new Font("宋体", Font.PLAIN, 12));
			buttonRefresh.setForeground(Color.BLUE);
		}
		return buttonRefresh;
	}

	/**
	 * 为按钮添加监听器
	 */
	private void addButtonsListeners() {
		getButtonPause().addActionListener(getEhd());
		getButtonRefresh().addActionListener(getEhd());
	}

	/**
	 * 返回倒计时标签
	 */
	private JLabel getTimeLabel() {
		if (timeLabel == null) {
			timeLabel = new JLabel("倒计时：");
			timeLabel.setForeground(Color.BLUE);
			timeLabel.setPreferredSize(new Dimension(80, 20));
			// 标签内容靠左显示
			timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
			timeLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		}
		return timeLabel;
	}

	/**
	 * 返回倒计时栏
	 */
	private JTextField getTimeTextField() {
		if (timeTextField == null) {
			timeTextField = new JTextField("" + getMaxTime());
			timeTextField.setForeground(Color.RED);
			timeTextField.setPreferredSize(new Dimension(80, 22));
			// 设置组件布局方向(从右到左)
			timeTextField
					.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			timeTextField.setFont(new Font("宋体", Font.BOLD, 18));
			timeTextField.setEditable(false);
		}
		return timeTextField;
	}

	/**
	 * 返回主界面事件处理对象
	 */
	protected KyodaiMainUIEventHandler getEhd() {
		if (ehd == null)
			ehd = new KyodaiMainUIEventHandler(this);
		return ehd;
	}

	/**
	 * 设置自定义鼠标指针形状
	 */
	private void setMyCursor() {
		// 自定义鼠标指针形状图片路径
		String iconPath = "images/cursor.gif";
		Image image = ImageCreator.getImage(iconPath);
		// 设置鼠标指针形状
		setCursor(getToolkit().createCustomCursor(image, new Point(0, 0), ""));
	}

	/**
	 * 设置JFrame最小化时的图标
	 */
	private void setMinimumIcon() {
		// 图标路径
		String iconPath = "images/kyodai.gif";
		Image image = ImageCreator.getImage(iconPath);
		setIconImage(image);
	}

	/**
	 * 返回模型维数
	 */
	public int getDimension() {
		return m_dimension;
	}

	/**
	 * 设置模型维数
	 */
	private void setDimension(int m_dimension) {
		this.m_dimension = m_dimension;
	}

	/**
	 * 返回游戏是否开始
	 */
	protected boolean isStarted() {
		return isStarted;
	}

	/**
	 * 设置游戏开始状态
	 */
	protected void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	/**
	 * 返回倒计时器线程
	 */
	private Thread getCountDown() {
		if (countDown == null)
			countDown = new Thread(getCalculagraph());
		return countDown;
	}

	/**
	 * 返回倒计时器对象
	 */
	private Calculagraph getCalculagraph() {
		if (calculagraph == null)
			calculagraph = new Calculagraph(this);
		return calculagraph;
	}

	/**
	 * 设置倒计时器线程 (一般在重新开始倒计时时设为null)
	 */
	private void setCountDown(Thread countDown) {
		this.countDown = countDown;
	}

	/**
	 * 开始倒计时
	 */
	protected void startCountingDown() {
		if (isStarted()) {
			countDown = getCountDown();
			// 倒计时器线程为守护线程
			countDown.setDaemon(true);
			countDown.start();
		}
	}

	/**
	 * 重新开始倒计时
	 */
	public void restartCountingDown() {
		stopCountingDown();
		setCountDown(null);
		startCountingDown();
	}

	/**
	 * 停止倒计时
	 */
	protected void stopCountingDown() {
		if (isStarted())
			countDown.interrupt();
	}

	/**
	 * 暂停倒计时
	 */
	protected void pauseCountingDown() {
		getCalculagraph().pauseCountingDown();
	}

	/**
	 * 继续倒计时
	 */
	protected void goOnCountingDown() {
		getCalculagraph().goOnCountingDown();
	}

	/**
	 * 返回被选中按钮的队列
	 */
	protected LinkedList<JButton> getButtonQueue() {
		return getKyodaiPanel().getButtonQueue();
	}

	/**
	 * 设置分数栏内容
	 */
	public void setScoreTextWith(String s) {
		getScoreTextField().setText(s);
	}

	/**
	 * 设置分数栏内容为默认值(0)
	 */
	public void setScoreTextWithDefault() {
		setScoreTextWith("0");
	}

	/**
	 * 设置重列按钮内容为默认值
	 */
	public void setButtonRefreshWithDefault() {
		getButtonRefresh().setEnabled(true);
		getButtonRefresh().setText("重列 (" + getMaxRefresfNumber() + ")");
	}

	/**
	 * 设置倒计时上限和倒计时栏为默认值并重新开始倒计时
	 */
	public void setTimeWithDefaultAndRestart() {
		setTimeTextWithDefault();
		restartCountingDown();
	}

	/**
	 * 设置倒计时栏内容
	 */
	public void setTimeTextWith(String s) {
		getTimeTextField().setText(s);
	}

	/**
	 * 设置倒计时栏内容为默认值
	 */
	private void setTimeTextWithDefault() {
		getTimeTextField().setText("" + getMaxTime());
	}

	/**
	 * 返回当前界面分数栏的分数
	 */
	protected int getCurrentScore() {
		return Integer.parseInt(getScoreTextField().getText());
	}

	/**
	 * 返回分数文件中的最高分
	 */
	protected int getMaxScoreFromFile() {
		Score score = null;
		try {
			score = ScoreFileManager.readScoreFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return score == null ? 0 : score.getScore();
	}

	/**
	 * 向分数文件中写入新的最高分
	 * 
	 * @param newScore
	 *            新的最高分
	 */
	protected void rewriteMaxScoreToFile(Score newScore) {
		try {
			ScoreFileManager.writeScoreToFile(newScore);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回倒计时上限(时间上限为 维数 * 维数 * 2 s)
	 */
	protected int getMaxTime() {
		int maxTime = (getDimension() * getDimension() * 2 / 10) * 10;
		// 最少10秒
		if (maxTime < 10)
			return 10;
		return maxTime;
	}

	/**
	 * 返回重列次数上限(等于维数)
	 */
	protected int getMaxRefresfNumber() {
		return getDimension();
	}

	/**
	 * 设置游戏面板的可见性
	 */
	protected void setGamePanelVisible(boolean state) {
		getKyodaiPanel().setVisible(state);
	}

	/**
	 * 设置菜单栏的可用性
	 */
	protected void setMenusState(boolean state) {
		getManageMenu().setEnabled(state);
		getDifficultyMenu().setEnabled(state);
		getSettingMenu().setEnabled(state);
		getHelpMenu().setEnabled(state);
	}

	/**
	 * 重新设置暂停按钮
	 * 
	 * @param text
	 *            新的显示内容和动作命令
	 */
	protected void resetButtonPause(String text) {
		getButtonPause().setText(text);
		getButtonPause().setActionCommand(text.trim());
	}

	/**
	 * 重开一局游戏
	 */
	protected void showNewGame() {
		new KyodaiMainUI(this.getDimension());
		this.dispose();
	}

	/**
	 * 设置新的重列剩余次数并在界面显示
	 */
	public void setRefreshNumberLeft(int newNumber) {
		getEhd().setRefreshNumberLeft(newNumber);
		getEhd().showRefreshNumber();
	}
	
	/**
	 * 返回当前重列剩余次数
	 */
	public int getRefreshNumberLeft(){
		return getEhd().getRefreshNumberLeft();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new KyodaiMainUI(KyodaiConstant.LOW);
	}
}
