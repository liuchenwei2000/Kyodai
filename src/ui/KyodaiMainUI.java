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
 * ��������������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-10-11
 */
public class KyodaiMainUI extends MyFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** �˵��� */
	private JMenuBar menuBar;

	private JMenu manageMenu;// �����˵�
	private JMenuItem startItem;// ��ʼ�˵���
	private JMenuItem restartItem;// ���¿�ʼ�˵���

	private JMenu difficultyMenu;// �Ѷ�ѡ��˵�
	private JMenu settingMenu;// ���ò˵�
	private JMenu helpMenu;// �����˵�

	/** ����������� */
	private KyodaiPanel kyodaiPanel;

	/** ������� */
	private JPanel functionPanel;

	private JLabel scoreLabel;// �Ʒֱ�ǩ
	private JTextField scoreTextField;// �Ʒ���

	private JButton buttonPause;// ��ʼ��ť
	private JButton buttonRefresh;// ���а�ť

	private JLabel timeLabel;// ����ʱ��ǩ
	private JTextField timeTextField;// ����ʱ��

	private KyodaiMainUIEventHandler ehd;// �¼��������

	private int m_dimension;// ������ť����ά��

	private boolean isStarted = false;// �Ƿ��Ѿ���ʼ��ʶ��

	private Thread countDown;// ����ʱ�����е��߳�
	private Calculagraph calculagraph;// ����ʱ������

	public KyodaiMainUI(int dimension) {
		super();
		ParameterChecker.checkDimension(dimension);
		setDimension(dimension);
		setLayout(new BorderLayout());

		// �������
		addMenuBar();
		addKyodaiPanel();
		addFunctionPanel();

		// ���ý����С
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
	 * ��Ӳ˵���
	 */
	private void addMenuBar() {
		addMenus();
		setJMenuBar(menuBar);
	}

	/**
	 * Ϊ�˵�����Ӳ˵�
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
	 * �����˵���
	 */
	private JMenu getManageMenu() {
		if (manageMenu == null) {
			manageMenu = new JMenu("����");
			manageMenu.setFont(new Font("����", Font.PLAIN, 12));

			manageMenu.add(getStartItem());
			manageMenu.add(getRestartItem());
			manageMenu.addSeparator();// �ָ���
			manageMenu.add(createMenuItemWithListener("�˳�"));
		}
		return manageMenu;
	}

	/**
	 * ��ʼ�˵���
	 */
	private JMenuItem getStartItem() {
		if (startItem == null) {
			startItem = createMenuItemWithListener("��ʼ");
		}
		return startItem;
	}

	/**
	 * ���¿�ʼ�˵���
	 */
	private JMenuItem getRestartItem() {
		if (restartItem == null) {
			restartItem = createMenuItemWithListener("���¿�ʼ");
		}
		return restartItem;
	}

	/**
	 * �Ѷ�ѡ��˵���
	 */
	private JMenu getDifficultyMenu() {
		if (difficultyMenu == null) {
			difficultyMenu = new JMenu("�Ѷ�ѡ��");
			difficultyMenu.setFont(new Font("����", Font.PLAIN, 12));

			difficultyMenu.add(createMenuItemWithListener("����"));
			difficultyMenu.add(createMenuItemWithListener("�м�"));
			difficultyMenu.add(createMenuItemWithListener("�߼�"));
			difficultyMenu.add(createMenuItemWithListener("�Զ���"));
		}
		return difficultyMenu;
	}

	/**
	 * ���ò˵���
	 */
	private JMenu getSettingMenu() {
		if (settingMenu == null) {
			settingMenu = new JMenu("����");
			settingMenu.setFont(new Font("����", Font.PLAIN, 12));
			settingMenu.add(createMenuItemWithListener("����Ч"));
			settingMenu.add(createMenuItemWithListener("�ر���Ч"));
		}
		return settingMenu;
	}

	/**
	 * �����˵���
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu("����");
			helpMenu.setFont(new Font("����", Font.PLAIN, 12));

			helpMenu.add(createMenuItemWithListener("��߷�"));
			helpMenu.addSeparator();// �ָ���
			helpMenu.add(createMenuItemWithListener("����������"));
		}
		return helpMenu;
	}

	/**
	 * ��ָ�����������м������˵���ť
	 * 
	 * @param name
	 *            �˵���ť����ʾ����
	 * @return JMenuItem
	 */
	private JMenuItem createMenuItemWithListener(String name) {
		JMenuItem item = new JMenuItem(name);
		item.setFont(new Font("����", Font.PLAIN, 12));
		item.addActionListener(getEhd());
		return item;
	}

	/**
	 * ��������������
	 */
	private void addKyodaiPanel() {
		// ��ʼ״̬���ɼ�
		getKyodaiPanel().setVisible(isStarted());
		add(getKyodaiPanel(), BorderLayout.CENTER);
	}

	/**
	 * ���������������
	 */
	public KyodaiPanel getKyodaiPanel() {
		if (kyodaiPanel == null) {
			kyodaiPanel = new KyodaiPanel(getDimension());
			// Ϊ�˺���������ͨ�������÷���
			kyodaiPanel.setMainUIEhd(getEhd());
			// ����ģ�͵�ά����������С
			kyodaiPanel.setSize(getDimension()
					* KyodaiConstant.LENGTH_OF_BUTTON
					+ KyodaiConstant.EXTENSION_LENGTH, getDimension()
					* KyodaiConstant.WIDTH_OF_BUTTON
					+ KyodaiConstant.EXTENSION_WIDTH);
		}
		return kyodaiPanel;
	}

	/**
	 * ��ӹ������
	 */
	private void addFunctionPanel() {
		setButtonsAndItemsState();
		add(getFunctionPanel(), BorderLayout.EAST);
	}

	/**
	 * ���ð�ť�Ͳ˵���״̬
	 */
	protected void setButtonsAndItemsState() {
		// ��ʼ֮��"��ʼ�˵���"���ɼ�
		getStartItem().setEnabled(!isStarted());
		getRestartItem().setEnabled(isStarted());
		setButtonsState(isStarted());
	}

	/**
	 * ���ð�ť�Ŀ�����
	 */
	private void setButtonsState(boolean state) {
		getButtonPause().setEnabled(state);
		getButtonRefresh().setEnabled(state);
	}

	/**
	 * ���ع������
	 */
	private JPanel getFunctionPanel() {
		if (functionPanel == null) {
			FlowLayout flow = new FlowLayout();
			// �����м��
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
	 * ���ط�����ǩ
	 */
	private JLabel getScoreLabel() {
		if (scoreLabel == null) {
			scoreLabel = new JLabel("�� ����");
			scoreLabel.setForeground(Color.BLUE);
			scoreLabel.setPreferredSize(new Dimension(80, 12));
			scoreLabel.setFont(new Font("����", Font.PLAIN, 12));
		}
		return scoreLabel;
	}

	/**
	 * ���ط�����
	 */
	private JTextField getScoreTextField() {
		if (scoreTextField == null) {
			scoreTextField = new JTextField("0");
			scoreTextField.setForeground(Color.DARK_GRAY);
			scoreTextField.setPreferredSize(new Dimension(80, 20));
			// ����������ַ���(���ҵ���)
			scoreTextField
					.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			scoreTextField.setBorder(new LineBorder(Color.MAGENTA));
			scoreTextField.setFont(new Font("����", Font.BOLD, 16));
			scoreTextField.setEditable(false);
		}
		return scoreTextField;
	}

	/**
	 * ���ؿ�ʼ��ť
	 */
	protected JButton getButtonPause() {
		if (buttonPause == null) {
			buttonPause = new JButton("  ��ͣ  ");
			buttonPause.setActionCommand("��ͣ");
			buttonPause.setFont(new Font("����", Font.PLAIN, 12));
			buttonPause.setForeground(Color.RED);
		}
		return buttonPause;
	}

	/**
	 * �������а�ť
	 */
	protected JButton getButtonRefresh() {
		if (buttonRefresh == null) {
			buttonRefresh = new JButton("���� (" + getMaxRefresfNumber() + ")");
			buttonRefresh.setActionCommand("����");
			buttonRefresh.setFont(new Font("����", Font.PLAIN, 12));
			buttonRefresh.setForeground(Color.BLUE);
		}
		return buttonRefresh;
	}

	/**
	 * Ϊ��ť��Ӽ�����
	 */
	private void addButtonsListeners() {
		getButtonPause().addActionListener(getEhd());
		getButtonRefresh().addActionListener(getEhd());
	}

	/**
	 * ���ص���ʱ��ǩ
	 */
	private JLabel getTimeLabel() {
		if (timeLabel == null) {
			timeLabel = new JLabel("����ʱ��");
			timeLabel.setForeground(Color.BLUE);
			timeLabel.setPreferredSize(new Dimension(80, 20));
			// ��ǩ���ݿ�����ʾ
			timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
			timeLabel.setFont(new Font("����", Font.PLAIN, 12));
		}
		return timeLabel;
	}

	/**
	 * ���ص���ʱ��
	 */
	private JTextField getTimeTextField() {
		if (timeTextField == null) {
			timeTextField = new JTextField("" + getMaxTime());
			timeTextField.setForeground(Color.RED);
			timeTextField.setPreferredSize(new Dimension(80, 22));
			// ����������ַ���(���ҵ���)
			timeTextField
					.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			timeTextField.setFont(new Font("����", Font.BOLD, 18));
			timeTextField.setEditable(false);
		}
		return timeTextField;
	}

	/**
	 * �����������¼��������
	 */
	protected KyodaiMainUIEventHandler getEhd() {
		if (ehd == null)
			ehd = new KyodaiMainUIEventHandler(this);
		return ehd;
	}

	/**
	 * �����Զ������ָ����״
	 */
	private void setMyCursor() {
		// �Զ������ָ����״ͼƬ·��
		String iconPath = "images/cursor.gif";
		Image image = ImageCreator.getImage(iconPath);
		// �������ָ����״
		setCursor(getToolkit().createCustomCursor(image, new Point(0, 0), ""));
	}

	/**
	 * ����JFrame��С��ʱ��ͼ��
	 */
	private void setMinimumIcon() {
		// ͼ��·��
		String iconPath = "images/kyodai.gif";
		Image image = ImageCreator.getImage(iconPath);
		setIconImage(image);
	}

	/**
	 * ����ģ��ά��
	 */
	public int getDimension() {
		return m_dimension;
	}

	/**
	 * ����ģ��ά��
	 */
	private void setDimension(int m_dimension) {
		this.m_dimension = m_dimension;
	}

	/**
	 * ������Ϸ�Ƿ�ʼ
	 */
	protected boolean isStarted() {
		return isStarted;
	}

	/**
	 * ������Ϸ��ʼ״̬
	 */
	protected void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	/**
	 * ���ص���ʱ���߳�
	 */
	private Thread getCountDown() {
		if (countDown == null)
			countDown = new Thread(getCalculagraph());
		return countDown;
	}

	/**
	 * ���ص���ʱ������
	 */
	private Calculagraph getCalculagraph() {
		if (calculagraph == null)
			calculagraph = new Calculagraph(this);
		return calculagraph;
	}

	/**
	 * ���õ���ʱ���߳� (һ�������¿�ʼ����ʱʱ��Ϊnull)
	 */
	private void setCountDown(Thread countDown) {
		this.countDown = countDown;
	}

	/**
	 * ��ʼ����ʱ
	 */
	protected void startCountingDown() {
		if (isStarted()) {
			countDown = getCountDown();
			// ����ʱ���߳�Ϊ�ػ��߳�
			countDown.setDaemon(true);
			countDown.start();
		}
	}

	/**
	 * ���¿�ʼ����ʱ
	 */
	public void restartCountingDown() {
		stopCountingDown();
		setCountDown(null);
		startCountingDown();
	}

	/**
	 * ֹͣ����ʱ
	 */
	protected void stopCountingDown() {
		if (isStarted())
			countDown.interrupt();
	}

	/**
	 * ��ͣ����ʱ
	 */
	protected void pauseCountingDown() {
		getCalculagraph().pauseCountingDown();
	}

	/**
	 * ��������ʱ
	 */
	protected void goOnCountingDown() {
		getCalculagraph().goOnCountingDown();
	}

	/**
	 * ���ر�ѡ�а�ť�Ķ���
	 */
	protected LinkedList<JButton> getButtonQueue() {
		return getKyodaiPanel().getButtonQueue();
	}

	/**
	 * ���÷���������
	 */
	public void setScoreTextWith(String s) {
		getScoreTextField().setText(s);
	}

	/**
	 * ���÷���������ΪĬ��ֵ(0)
	 */
	public void setScoreTextWithDefault() {
		setScoreTextWith("0");
	}

	/**
	 * �������а�ť����ΪĬ��ֵ
	 */
	public void setButtonRefreshWithDefault() {
		getButtonRefresh().setEnabled(true);
		getButtonRefresh().setText("���� (" + getMaxRefresfNumber() + ")");
	}

	/**
	 * ���õ���ʱ���޺͵���ʱ��ΪĬ��ֵ�����¿�ʼ����ʱ
	 */
	public void setTimeWithDefaultAndRestart() {
		setTimeTextWithDefault();
		restartCountingDown();
	}

	/**
	 * ���õ���ʱ������
	 */
	public void setTimeTextWith(String s) {
		getTimeTextField().setText(s);
	}

	/**
	 * ���õ���ʱ������ΪĬ��ֵ
	 */
	private void setTimeTextWithDefault() {
		getTimeTextField().setText("" + getMaxTime());
	}

	/**
	 * ���ص�ǰ����������ķ���
	 */
	protected int getCurrentScore() {
		return Integer.parseInt(getScoreTextField().getText());
	}

	/**
	 * ���ط����ļ��е���߷�
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
	 * ������ļ���д���µ���߷�
	 * 
	 * @param newScore
	 *            �µ���߷�
	 */
	protected void rewriteMaxScoreToFile(Score newScore) {
		try {
			ScoreFileManager.writeScoreToFile(newScore);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���ص���ʱ����(ʱ������Ϊ ά�� * ά�� * 2 s)
	 */
	protected int getMaxTime() {
		int maxTime = (getDimension() * getDimension() * 2 / 10) * 10;
		// ����10��
		if (maxTime < 10)
			return 10;
		return maxTime;
	}

	/**
	 * �������д�������(����ά��)
	 */
	protected int getMaxRefresfNumber() {
		return getDimension();
	}

	/**
	 * ������Ϸ���Ŀɼ���
	 */
	protected void setGamePanelVisible(boolean state) {
		getKyodaiPanel().setVisible(state);
	}

	/**
	 * ���ò˵����Ŀ�����
	 */
	protected void setMenusState(boolean state) {
		getManageMenu().setEnabled(state);
		getDifficultyMenu().setEnabled(state);
		getSettingMenu().setEnabled(state);
		getHelpMenu().setEnabled(state);
	}

	/**
	 * ����������ͣ��ť
	 * 
	 * @param text
	 *            �µ���ʾ���ݺͶ�������
	 */
	protected void resetButtonPause(String text) {
		getButtonPause().setText(text);
		getButtonPause().setActionCommand(text.trim());
	}

	/**
	 * �ؿ�һ����Ϸ
	 */
	protected void showNewGame() {
		new KyodaiMainUI(this.getDimension());
		this.dispose();
	}

	/**
	 * �����µ�����ʣ��������ڽ�����ʾ
	 */
	public void setRefreshNumberLeft(int newNumber) {
		getEhd().setRefreshNumberLeft(newNumber);
		getEhd().showRefreshNumber();
	}
	
	/**
	 * ���ص�ǰ����ʣ�����
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