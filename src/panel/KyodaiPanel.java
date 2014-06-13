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
 * ��������ť���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-10-4
 */
public class KyodaiPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** gifͼƬ���Ƶ����� */
	private static String[] icons;

	private KyodaiPanelModel model;// ��ť���ģ��
	private KyodaiPanelEHD ehd;// ��ť����¼��������
	private KyodaiMainUIEventHandler mainUIEhd;// �������¼��������

	private JLabel imageLabel;// ����ͼƬ��ǩ
	
	private int m_dimension;// ģ��ά��
	private JButton[][] buttons;// ��ģ�͵õ��Ķ�Ӧ�İ�ť��ά����

	/**
	 * ���췽��
	 */
	public KyodaiPanel() {
		init();
	}

	/**
	 * ���췽��
	 * 
	 * @param dimension
	 *            ָ����ά��
	 */
	public KyodaiPanel(int dimension) {
		init(dimension);
	}

	/**
	 * ��ʼ����ť������
	 */
	protected void init() {
		// Ĭ��ά��10
		init(10);
	}

	/**
	 * ��ָ��ά����ʼ����ť������
	 * 
	 * @param dimension
	 *            ָ��ά��
	 */
	protected void init(int dimension) {
		setDimension(dimension);
		setLayout(null);
		// ����PanelΪ���񻯱߿�
		setBorder(new EtchedBorder());
		// ���ñ���ɫ
		setBackground(Color.WHITE);
		// ��ӽ�������Ҫ�����а�ť
		addButtons();
		// ��ӱ���ͼƬ
		addBkgrdImage();
	}

	/**
	 * ������ϵ�������е���������ť
	 */
	private void addButtons() {
		for (int i = 0; i < getButtons().length; i++) {
			for (int j = 0; j < getButtons().length; j++) {
				// ���ն�ά��������ʾ��˳�����ð�ť��SIZE_OF_BUTTON�Ǹ���ͼƬ����Ԥ�õĴ�С
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
	 * ����������а�ť���������
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
					 * ��Ϊģ���еĶ�ά������(dimension + 2) * (dimension + 2)��
					 * ����buttons[i][j]��Ӧ��get2DArray()[i + 1][j + 1]
					 */
					// ���ģ���е�ֵΪ0������Ϊ�ð�ť���ɼ�
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
	 * ����������������ť
	 */
	protected void setButtons(JButton[][] buttons) {
		this.buttons = buttons;
	}

	/**
	 * ����һ���հװ�ť(�ڽ����ϵĲ���ʾ) ��ģ�Ͷ�ά�����е�Ԫ��ֵΪ0ʱ�ŵ��ô˷���������ť
	 * 
	 * @return JButton
	 */
	private JButton getNullButton() {
		JButton nullButton = new JButton();
		// ActionCommandΪ0
		nullButton.setActionCommand("0");
		nullButton.setVisible(false);
		return nullButton;
	}

	/**
	 * ���� ������ ��ť ��nָ������Ӧ��ͼƬ���������е�gifͼƬ
	 * 
	 * @param n
	 *            ͼƬ���
	 * @return JButton
	 */
	private JButton getButton(int n) {
		JButton button = null;
		String iconName = getIcons()[n];// ͼƬ��
		ImageIcon icon = ImageCreator.getImageIcon(iconName);
		button = new JButton(icon);
		// ÿ����ť��ActionCommand��������ͼƬ��(Ҳ��ģ���еĶ�ӦԪ����ͬ)
		button.setActionCommand(iconName.substring(iconName.indexOf('\\') + 1,
				iconName.indexOf('.')));
		return button;
	}

	/**
	 * ���� gif ͼƬ���Ƶ�����
	 * 
	 * @return String[]
	 */
	private static String[] getIcons() {
		if (icons == null) {
			// ����Ϊ NUMBER_OF_ICONS + 1 ��Ϊ��ʹͼƬ�ź�ģ���е�Ԫ����ͬ
			icons = new String[KyodaiConstant.NUMBER_OF_ICONS + 1];
			// ͼƬ����ĵ�һ���ǲ����ڵ�ͼƬ "images/0.gif"
			icons[0] = KyodaiConstant.PATH_OF_ICONS + "0"
					+ KyodaiConstant.SUFFIX_GIF;
			for (int i = 1; i < icons.length; i++) {
				// ƴ��ͼƬ������
				icons[i] = KyodaiConstant.PATH_OF_ICONS + i
						+ KyodaiConstant.SUFFIX_GIF;
			}
		}
		return icons;
	}

	/**
	 * Ϊ�����ӱ���ͼƬ
	 */
	private void addBkgrdImage() {
		add(getImageLabel());
	}

	
	/**
	 * ������������ģ��
	 */
	public KyodaiPanelModel getModel() {
		if (model == null) {
			model = new KyodaiPanelModel(KyodaiConstant.NUMBER_OF_ICONS, m_dimension);
		}
		return model;
	}

	/**
	 * ������������ģ��
	 */
	private void setModel(KyodaiPanelModel model) {
		this.model = model;
	}

	/**
	 * ���ذ�ť����¼��������
	 */
	protected KyodaiPanelEHD getKyodaiPanelEHD() {
		if (ehd == null)
			ehd = new KyodaiPanelEHD(this);
		return ehd;
	}

	/**
	 * �ڹ���������ʱ����Ҫ���ø÷����������� (Ϊ��ʹ�������Ժ���������ͨ��)
	 * 
	 * @param mainUIEhd
	 *            �������¼��������
	 */
	public void setMainUIEhd(KyodaiMainUIEventHandler mainUIEhd) {
		this.mainUIEhd = mainUIEhd;
	}

	/**
	 * ���ر���ͼƬ��ǩ
	 */
	private JLabel getImageLabel() {
		if (imageLabel == null) {
			imageLabel = new JLabel();
		}
		// ͼƬ�����������
		ImageIcon bkgrdImage = ImageCreator.getImageIcon(getRandomImageName());
		imageLabel.setIcon(bkgrdImage);
		imageLabel.setBounds(0, 0, bkgrdImage.getIconWidth(), bkgrdImage
				.getIconHeight());
		return imageLabel;
	}
	
	/**
	 * �������������ͼƬ��
	 */
	private String getRandomImageName() {
		// ͼƬ��
		int code = RandomArithmetic
				.getRandomInteger(KyodaiConstant.NUMBER_OF_IMAGES) + 1;
		// ƴ��ͼƬ��������
		StringBuilder name = new StringBuilder(KyodaiConstant.PATH_OF_IMAGES);
		name.append(code);
		name.append(KyodaiConstant.SUFFIX_JPG);
		return name.toString();
	}

	/**
	 * ����ά��
	 */
	private void setDimension(int dimension) {
		this.m_dimension = dimension;
	}

	/**
	 * Ϊ��ť��������µ�ģ��
	 */
	public void setNewModel() {
		// ��ȡ��ǰģ��ά��
		int dimension = getModel().getDimension();
		// ����ά��������ģ��
		KyodaiPanelModel model = new KyodaiPanelModel(
				KyodaiConstant.NUMBER_OF_ICONS, dimension);
		setModel(model);
	}

	/**
	 * �������(Ĭ��Ϊ0)
	 */
	public void resetScore() {
		getKyodaiPanelEHD().setScore(new Score());
	}

	/**
	 * ʹ�õ�ǰģ�����»��ư�ť���
	 */
	public void repaintUsingCurrentModel() {
		// ����������а�ť
		removeAll();
		// ��buttons����Ϊ��
		setButtons(null);
		// �����°�ť
		addButtons();
		// ���ӱ���ͼƬ
		addBkgrdImage();
		// ˢ�½���
		updateUI();
	}

	/**
	 * ���÷���������
	 * 
	 * @param text
	 */
	protected void setScoreText(String text) {
		mainUIEhd.getKyodaiMainUI().setScoreTextWith(text);
	}

	/**
	 * ���¿�ʼ��ʱ
	 */
	protected void restartCountingDown() {
		mainUIEhd.getKyodaiMainUI().restartCountingDown();
	}

	/**
	 * �����µ�ˢ�´���
	 */
	protected void setRefreshNumberLeft(int newNumber) {
		mainUIEhd.getKyodaiMainUI().setRefreshNumberLeft(newNumber);
	}

	/**
	 * ���ص�ǰʣ�����д���
	 */
	protected int getRefreshNumberLeft() {
		return mainUIEhd.getKyodaiMainUI().getRefreshNumberLeft();
	}
	
	/**
	 * ��������ȥ��ť������(Ĭ��Ϊ0)
	 */
	public void resetButtonCounter() {
		getKyodaiPanelEHD().setCounter(0);
	}
	
	/**
	 * ���ر�ѡ�а�ť�Ķ���
	 */
	public LinkedList<JButton> getButtonQueue() {
		return getKyodaiPanelEHD().getButtonQueue();
	}
}