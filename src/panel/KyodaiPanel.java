/**
 * 
 */
package panel;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import ui.KyodaiMainUIEventHandler;
import util.resource.ImageCreator;
import arithmetic.RandomArithmetic;
import assistant.KyodaiConstant;
import assistant.Score;

/**
 * 连连看按钮面板
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-10-4
 */
public class KyodaiPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** gif图片名称的数组 */
	private static String[] icons;

	private KyodaiPanelModel model;// 按钮面板模型
	private KyodaiPanelEHD ehd;// 按钮面板事件处理对象
	private KyodaiMainUIEventHandler mainUIEhd;// 主界面事件处理对象

	private JLabel imageLabel;// 背景图片标签
	
	private int m_dimension;// 模型维数
	private JButton[][] buttons;// 由模型得到的对应的按钮二维数组

	/**
	 * 构造方法
	 */
	public KyodaiPanel() {
		init();
	}

	/**
	 * 构造方法
	 * 
	 * @param dimension
	 *            指定的维数
	 */
	public KyodaiPanel(int dimension) {
		init(dimension);
	}

	/**
	 * 初始化按钮面板界面
	 */
	protected void init() {
		// 默认维数10
		init(10);
	}

	/**
	 * 用指定维数初始化按钮面板界面
	 * 
	 * @param dimension
	 *            指定维数
	 */
	protected void init(int dimension) {
		setDimension(dimension);
		setLayout(null);
		// 设置Panel为浮雕化边框
		setBorder(new EtchedBorder());
		// 设置背景色
		setBackground(Color.WHITE);
		// 添加界面所需要的所有按钮
		addButtons();
		// 添加背景图片
		addBkgrdImage();
	}

	/**
	 * 在面板上的添加所有的连连看按钮
	 */
	private void addButtons() {
		for (int i = 0; i < getButtons().length; i++) {
			for (int j = 0; j < getButtons().length; j++) {
				// 按照二维数组所表示的顺序设置按钮，SIZE_OF_BUTTON是根据图片像素预置的大小
				getButtons()[i][j].setBounds(30
						+ KyodaiConstant.LENGTH_OF_BUTTON * j, 30
						+ KyodaiConstant.WIDTH_OF_BUTTON * i,
						KyodaiConstant.LENGTH_OF_BUTTON,
						KyodaiConstant.WIDTH_OF_BUTTON);
				getButtons()[i][j].addActionListener(getKyodaiPanelEHD());
				add(getButtons()[i][j]);
			}
		}
	}

	/**
	 * 返回面板所有按钮对象的数组
	 * 
	 * @return JButton[][]
	 */
	protected JButton[][] getButtons() {
		if (buttons == null) {
			buttons = new JButton[getModel().getDimension()][getModel()
					.getDimension()];
			for (int i = 0; i < getModel().getDimension(); i++) {
				for (int j = 0; j < getModel().getDimension(); j++) {
					/*
					 * 因为模型中的二维数组是(dimension + 2) * (dimension + 2)的
					 * 所以buttons[i][j]对应于get2DArray()[i + 1][j + 1]
					 */
					// 如果模型中的值为0则设置为该按钮不可见
					if (getModel().get2DArray()[i + 1][j + 1] == 0) {
						buttons[i][j] = getNullButton();
					} else {
						buttons[i][j] = getButton(getModel().get2DArray()[i + 1][j + 1]);
					}
				}
			}
		}
		return buttons;
	}

	/**
	 * 设置面板的连连看按钮
	 */
	protected void setButtons(JButton[][] buttons) {
		this.buttons = buttons;
	}

	/**
	 * 返回一个空白按钮(在界面上的不显示) 当模型二维数组中的元素值为0时才调用此方法创建按钮
	 * 
	 * @return JButton
	 */
	private JButton getNullButton() {
		JButton nullButton = new JButton();
		// ActionCommand为0
		nullButton.setActionCommand("0");
		nullButton.setVisible(false);
		return nullButton;
	}

	/**
	 * 返回 连连看 按钮 由n指定所对应在图片名称数组中的gif图片
	 * 
	 * @param n
	 *            图片编号
	 * @return JButton
	 */
	private JButton getButton(int n) {
		JButton button = null;
		String iconName = getIcons()[n];// 图片名
		ImageIcon icon = ImageCreator.getImageIcon(iconName);
		button = new JButton(icon);
		// 每个按钮的ActionCommand都是它的图片号(也和模型中的对应元素相同)
		button.setActionCommand(iconName.substring(iconName.indexOf('\\') + 1,
				iconName.indexOf('.')));
		return button;
	}

	/**
	 * 返回 gif 图片名称的数组
	 * 
	 * @return String[]
	 */
	private static String[] getIcons() {
		if (icons == null) {
			// 长度为 NUMBER_OF_ICONS + 1 是为了使图片号和模型中的元素相同
			icons = new String[KyodaiConstant.NUMBER_OF_ICONS + 1];
			// 图片数组的第一个是不存在的图片 "images/0.gif"
			icons[0] = KyodaiConstant.PATH_OF_ICONS + "0"
					+ KyodaiConstant.SUFFIX_GIF;
			for (int i = 1; i < icons.length; i++) {
				// 拼出图片完整名
				icons[i] = KyodaiConstant.PATH_OF_ICONS + i
						+ KyodaiConstant.SUFFIX_GIF;
			}
		}
		return icons;
	}

	/**
	 * 为面板添加背景图片
	 */
	private void addBkgrdImage() {
		add(getImageLabel());
	}

	
	/**
	 * 返回面板的数据模型
	 */
	public KyodaiPanelModel getModel() {
		if (model == null) {
			model = new KyodaiPanelModel(KyodaiConstant.NUMBER_OF_ICONS, m_dimension);
		}
		return model;
	}

	/**
	 * 设置面板的数据模型
	 */
	private void setModel(KyodaiPanelModel model) {
		this.model = model;
	}

	/**
	 * 返回按钮面板事件处理对象
	 */
	protected KyodaiPanelEHD getKyodaiPanelEHD() {
		if (ehd == null)
			ehd = new KyodaiPanelEHD(this);
		return ehd;
	}

	/**
	 * 在构建本面板的时候需要调用该方法进行设置 (为了使本面板可以和主面板进行通信)
	 * 
	 * @param mainUIEhd
	 *            主界面事件处理对象
	 */
	public void setMainUIEhd(KyodaiMainUIEventHandler mainUIEhd) {
		this.mainUIEhd = mainUIEhd;
	}

	/**
	 * 返回背景图片标签
	 */
	private JLabel getImageLabel() {
		if (imageLabel == null) {
			imageLabel = new JLabel();
		}
		// 图片是随机产生的
		ImageIcon bkgrdImage = ImageCreator.getImageIcon(getRandomImageName());
		imageLabel.setIcon(bkgrdImage);
		imageLabel.setBounds(0, 0, bkgrdImage.getIconWidth(), bkgrdImage
				.getIconHeight());
		return imageLabel;
	}
	
	/**
	 * 返回随机产生的图片名
	 */
	private String getRandomImageName() {
		// 图片号
		int code = RandomArithmetic
				.getRandomInteger(KyodaiConstant.NUMBER_OF_IMAGES) + 1;
		// 拼出图片完整名称
		StringBuilder name = new StringBuilder(KyodaiConstant.PATH_OF_IMAGES);
		name.append(code);
		name.append(KyodaiConstant.SUFFIX_JPG);
		return name.toString();
	}

	/**
	 * 设置维数
	 */
	private void setDimension(int dimension) {
		this.m_dimension = dimension;
	}

	/**
	 * 为按钮面板设置新的模型
	 */
	public void setNewModel() {
		// 获取当前模型维数
		int dimension = getModel().getDimension();
		// 根据维数生成新模型
		KyodaiPanelModel model = new KyodaiPanelModel(
				KyodaiConstant.NUMBER_OF_ICONS, dimension);
		setModel(model);
	}

	/**
	 * 重设分数(默认为0)
	 */
	public void resetScore() {
		getKyodaiPanelEHD().setScore(new Score());
	}

	/**
	 * 使用当前模型重新绘制按钮面板
	 */
	public void repaintUsingCurrentModel() {
		// 清除面板的所有按钮
		removeAll();
		// 把buttons设置为空
		setButtons(null);
		// 增加新按钮
		addButtons();
		// 增加背景图片
		addBkgrdImage();
		// 刷新界面
		updateUI();
	}

	/**
	 * 设置分数栏内容
	 * 
	 * @param text
	 */
	protected void setScoreText(String text) {
		mainUIEhd.getKyodaiMainUI().setScoreTextWith(text);
	}

	/**
	 * 重新开始计时
	 */
	protected void restartCountingDown() {
		mainUIEhd.getKyodaiMainUI().restartCountingDown();
	}

	/**
	 * 设置新的刷新次数
	 */
	protected void setRefreshNumberLeft(int newNumber) {
		mainUIEhd.getKyodaiMainUI().setRefreshNumberLeft(newNumber);
	}

	/**
	 * 返回当前剩余重列次数
	 */
	protected int getRefreshNumberLeft() {
		return mainUIEhd.getKyodaiMainUI().getRefreshNumberLeft();
	}
	
	/**
	 * 重设已消去按钮计数器(默认为0)
	 */
	public void resetButtonCounter() {
		getKyodaiPanelEHD().setCounter(0);
	}
	
	/**
	 * 返回被选中按钮的队列
	 */
	public LinkedList<JButton> getButtonQueue() {
		return getKyodaiPanelEHD().getButtonQueue();
	}
}