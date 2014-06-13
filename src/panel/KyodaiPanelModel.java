/**
 * 
 */
package panel;

import util.other.ArrayConversion;
import util.other.ParameterChecker;
import arithmetic.RandomArithmetic;

/**
 * 连连看按钮面板模型类
 * 
 * 1，模型实际上为二维数组，维数为偶数 
 * 2，模型的实际维数为参数维数 + 2 
 * 3，模型数组的最外面一圈元素都置为0
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-10-8
 */
public class KyodaiPanelModel {

	private int[][] m_2DArray = null;// 二维数组(其元素的值用于生成相应按钮)
	private int dimension;// 二维数组维数
	private int maxNumber;// 二维数组元素的上限(用于随机数产生，并且包括这个上限)

	/**
	 * 构造方法
	 * 
	 * @param maxNumber
	 *            随机数的数值上限
	 */
	public KyodaiPanelModel(int maxNumber) {
		// 默认维数为10
		init(maxNumber, 10);
	}

	/**
	 * 构造方法
	 * 
	 * @param maxNumber
	 *            随机数的数值上限
	 * @param dimension
	 *            参数维数
	 */
	public KyodaiPanelModel(int maxNumber, int dimension) {
		init(maxNumber, dimension);
	}

	/**
	 * 初始化模型
	 * 
	 * @param maxNumber
	 *            模型中二维数组元素的上限(含)
	 * @param dimension
	 *            模型的维数(必须为大于0的偶数)
	 */
	private void init(int maxNumber, int dimension) {
		ParameterChecker.checkMaxNumber(maxNumber);
		ParameterChecker.checkDimension(dimension);
		setMaxNumber(maxNumber);
		setDimension(dimension);
	}

	/**
	 * 返回(dimension+2)*(dimension+2)的二维整数数组
	 * 
	 * @return int[][]
	 */
	public int[][] get2DArray() {
		if (m_2DArray == null) {
			// m_2DArray的实际维数dimension + 2
			m_2DArray = new int[getDimension() + 2][getDimension() + 2];
			set2DAryWithRanSqnceNmbInPairs(m_2DArray);
		}
		return m_2DArray;
	}

	/**
	 * 对the2DArray进行赋值 
	 * 其元素是随机产生的maxNumber(含)之内的整数 
	 * 并且每一个随机数都是成对出现的
	 * 
	 * @param the2DArray
	 *            目标二维数组
	 */
	private void set2DAryWithRanSqnceNmbInPairs(int[][] the2DArray) {
		// 获取元素成对出现的随机数数组
		Integer[] inpairs = RandomArithmetic.getArrayWithRandomNumberInPairs(
				getDimension() * getDimension(), getMaxNumber());
		// 获取按随机顺序二次排序后的数组
		Integer[] randomSequence = RandomArithmetic
				.getArrayWithRandomSequence(inpairs);
		ArrayConversion.to2DArray(the2DArray, randomSequence);
	}
	
	/**
	 * @return the dimension
	 */
	public int getDimension() {
		return dimension;
	}

	/**
	 * @param dimension
	 *            the dimension to set
	 */
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	/**
	 * @return the maxNumber
	 */
	protected int getMaxNumber() {
		return maxNumber;
	}

	/**
	 * @param maxNumber the maxNumber to set
	 */
	protected void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
}