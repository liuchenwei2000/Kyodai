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
 * 连连看面板上的线段绘制类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-10-23
 */
public class LineDrawer {

	private KyodaiPanel m_kyodaiPanel;// 画线的目标面板
	private Graphics2D g;// 绘制对象

	public LineDrawer(KyodaiPanel kyodaiPanel) {
		m_kyodaiPanel = kyodaiPanel;
	}

	/**
	 * 返回自定义设置后的画图对象
	 * 
	 * @return Graphics2D
	 */
	private Graphics2D getGraphic2D() {
		if (g == null) {
			g = (Graphics2D) m_kyodaiPanel.getGraphics();
			// 线的颜色
			g.setColor(Color.BLUE);
			// 线的粗细
			g.setStroke(new BasicStroke(3.0f));
		}
		return g;
	}

	/**
	 * 画两点间的直线
	 * 
	 * @param p1
	 *            点1
	 * @param p2
	 *            点2
	 */
	private void drawLine(Point p1, Point p2) {
		Graphics2D drawer = getGraphic2D();
		drawer.drawLine(p1.x, p1.y, p2.x, p2.y);
	}

	/**
	 * 在界面上画模型中两位置的连线
	 * 
	 * @param location1
	 *            位置1
	 * @param location2
	 *            位置2
	 */
	private void drawRemovablePath(Location location1, Location location2) {
		Point p1 = getPointWithLocationInModel(location1);
		Point p2 = getPointWithLocationInModel(location2);
		drawLine(p1, p2);
	}

	/**
	 * 在界面上画模型中两位置的可消路径
	 * 
	 * @param start
	 *            起始位置
	 * @param end
	 *            结束位置
	 */
	public void drawRemovablePathAndUpdateUI(Location start, Location end) {
		drawRemovablePath(start, end);
		updateMainUI();
	}

	/**
	 * 在界面上画模型中三位置的可消路径
	 * 
	 * @param start
	 *            起始位置
	 * @param middle
	 *            中间位置
	 * @param end
	 *            结束位置
	 */
	public void drawRemovablePathAndUpdateUI(Location start, Location middle,
			Location end) {
		drawRemovablePath(start, middle);
		drawRemovablePath(middle, end);
		updateMainUI();
	}

	/**
	 * 在界面上画模型中四位置的可消路径
	 * 
	 * @param start
	 *            起始位置
	 * @param middle1
	 *            中间位置1
	 * @param middle2
	 *            中间位置2
	 * @param end
	 *            结束位置
	 */
	public void drawRemovablePathAndUpdateUI(Location start, Location middle1,
			Location middle2, Location end) {
		drawRemovablePath(start, middle1);
		drawRemovablePath(middle1, middle2);
		drawRemovablePath(middle2, end);
		updateMainUI();
	}

	/**
	 * 刷新主界面(实际是为了消去已画的线)
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
	 * 根据模型中的位置信息返回在UI界面中对应的点(按钮中心点)
	 * 
	 * @param locationInModel
	 *            模型中的位置
	 * @return Point
	 */
	private Point getPointWithLocationInModel(Location locationInModel) {
		int x = getXWithLocationInModel(locationInModel);
		int y = getYWithLocationInModel(locationInModel);
		return new Point(x, y);
	}

	/**
	 * 根据模型中的位置信息返回在UI界面中对应的点(按钮中心点)的横坐标
	 * 
	 * @param locationInModel
	 *            模型中的位置
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
	 * 根据模型中的位置信息返回在UI界面中对应的点(按钮中心点)的纵坐标
	 * 
	 * @param locationInModel
	 *            模型中的位置
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
