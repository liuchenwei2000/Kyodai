/**
 * 
 */
package util.resource;

import geometry.Location;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import assistant.KyodaiConstant;


import panel.KyodaiPanel;

/**
 * ����������ϵ��߶λ�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-10-23
 */
public class LineDrawer {

	private KyodaiPanel m_kyodaiPanel;// ���ߵ�Ŀ�����
	private Graphics2D g;// ���ƶ���

	public LineDrawer(KyodaiPanel kyodaiPanel) {
		m_kyodaiPanel = kyodaiPanel;
	}

	/**
	 * �����Զ������ú�Ļ�ͼ����
	 * 
	 * @return Graphics2D
	 */
	private Graphics2D getGraphic2D() {
		if (g == null) {
			g = (Graphics2D) m_kyodaiPanel.getGraphics();
			// �ߵ���ɫ
			g.setColor(Color.BLUE);
			// �ߵĴ�ϸ
			g.setStroke(new BasicStroke(3.0f));
		}
		return g;
	}

	/**
	 * ��������ֱ��
	 * 
	 * @param p1
	 *            ��1
	 * @param p2
	 *            ��2
	 */
	private void drawLine(Point p1, Point p2) {
		Graphics2D drawer = getGraphic2D();
		drawer.drawLine(p1.x, p1.y, p2.x, p2.y);
	}

	/**
	 * �ڽ����ϻ�ģ������λ�õ�����
	 * 
	 * @param location1
	 *            λ��1
	 * @param location2
	 *            λ��2
	 */
	private void drawRemovablePath(Location location1, Location location2) {
		Point p1 = getPointWithLocationInModel(location1);
		Point p2 = getPointWithLocationInModel(location2);
		drawLine(p1, p2);
	}

	/**
	 * �ڽ����ϻ�ģ������λ�õĿ���·��
	 * 
	 * @param start
	 *            ��ʼλ��
	 * @param end
	 *            ����λ��
	 */
	public void drawRemovablePathAndUpdateUI(Location start, Location end) {
		drawRemovablePath(start, end);
		updateMainUI();
	}

	/**
	 * �ڽ����ϻ�ģ������λ�õĿ���·��
	 * 
	 * @param start
	 *            ��ʼλ��
	 * @param middle
	 *            �м�λ��
	 * @param end
	 *            ����λ��
	 */
	public void drawRemovablePathAndUpdateUI(Location start, Location middle,
			Location end) {
		drawRemovablePath(start, middle);
		drawRemovablePath(middle, end);
		updateMainUI();
	}

	/**
	 * �ڽ����ϻ�ģ������λ�õĿ���·��
	 * 
	 * @param start
	 *            ��ʼλ��
	 * @param middle1
	 *            �м�λ��1
	 * @param middle2
	 *            �м�λ��2
	 * @param end
	 *            ����λ��
	 */
	public void drawRemovablePathAndUpdateUI(Location start, Location middle1,
			Location middle2, Location end) {
		drawRemovablePath(start, middle1);
		drawRemovablePath(middle1, middle2);
		drawRemovablePath(middle2, end);
		updateMainUI();
	}

	/**
	 * ˢ��������(ʵ����Ϊ����ȥ�ѻ�����)
	 */
	private void updateMainUI() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m_kyodaiPanel.updateUI();
	}

	/**
	 * ����ģ���е�λ����Ϣ������UI�����ж�Ӧ�ĵ�(��ť���ĵ�)
	 * 
	 * @param locationInModel
	 *            ģ���е�λ��
	 * @return Point
	 */
	private Point getPointWithLocationInModel(Location locationInModel) {
		int x = getXWithLocationInModel(locationInModel);
		int y = getYWithLocationInModel(locationInModel);
		return new Point(x, y);
	}

	/**
	 * ����ģ���е�λ����Ϣ������UI�����ж�Ӧ�ĵ�(��ť���ĵ�)�ĺ�����
	 * 
	 * @param locationInModel
	 *            ģ���е�λ��
	 * @return int
	 */
	private int getXWithLocationInModel(Location locationInModel) {
		int x = KyodaiConstant.EXTENSION_LENGTH / 2
				+ (locationInModel.getColumn() - 1)
				* KyodaiConstant.LENGTH_OF_BUTTON
				+ KyodaiConstant.LENGTH_OF_BUTTON / 2;
		return x;
	}

	/**
	 * ����ģ���е�λ����Ϣ������UI�����ж�Ӧ�ĵ�(��ť���ĵ�)��������
	 * 
	 * @param locationInModel
	 *            ģ���е�λ��
	 * @return int
	 */
	private int getYWithLocationInModel(Location locationInModel) {
		int y = KyodaiConstant.EXTENSION_WIDTH / 2
				+ (locationInModel.getRow() - 1)
				* KyodaiConstant.WIDTH_OF_BUTTON
				+ KyodaiConstant.WIDTH_OF_BUTTON / 2;
		return y;
	}
}