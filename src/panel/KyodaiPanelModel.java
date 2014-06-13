/**
 * 
 */
package panel;

import util.other.ArrayConversion;
import util.other.ParameterChecker;
import arithmetic.RandomArithmetic;

/**
 * ��������ť���ģ����
 * 
 * 1��ģ��ʵ����Ϊ��ά���飬ά��Ϊż�� 
 * 2��ģ�͵�ʵ��ά��Ϊ����ά�� + 2 
 * 3��ģ�������������һȦԪ�ض���Ϊ0
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-10-8
 */
public class KyodaiPanelModel {

	private int[][] m_2DArray = null;// ��ά����(��Ԫ�ص�ֵ����������Ӧ��ť)
	private int dimension;// ��ά����ά��
	private int maxNumber;// ��ά����Ԫ�ص�����(������������������Ұ����������)

	/**
	 * ���췽��
	 * 
	 * @param maxNumber
	 *            ���������ֵ����
	 */
	public KyodaiPanelModel(int maxNumber) {
		// Ĭ��ά��Ϊ10
		init(maxNumber, 10);
	}

	/**
	 * ���췽��
	 * 
	 * @param maxNumber
	 *            ���������ֵ����
	 * @param dimension
	 *            ����ά��
	 */
	public KyodaiPanelModel(int maxNumber, int dimension) {
		init(maxNumber, dimension);
	}

	/**
	 * ��ʼ��ģ��
	 * 
	 * @param maxNumber
	 *            ģ���ж�ά����Ԫ�ص�����(��)
	 * @param dimension
	 *            ģ�͵�ά��(����Ϊ����0��ż��)
	 */
	private void init(int maxNumber, int dimension) {
		ParameterChecker.checkMaxNumber(maxNumber);
		ParameterChecker.checkDimension(dimension);
		setMaxNumber(maxNumber);
		setDimension(dimension);
	}

	/**
	 * ����(dimension+2)*(dimension+2)�Ķ�ά��������
	 * 
	 * @return int[][]
	 */
	public int[][] get2DArray() {
		if (m_2DArray == null) {
			// m_2DArray��ʵ��ά��dimension + 2
			m_2DArray = new int[getDimension() + 2][getDimension() + 2];
			set2DAryWithRanSqnceNmbInPairs(m_2DArray);
		}
		return m_2DArray;
	}

	/**
	 * ��the2DArray���и�ֵ 
	 * ��Ԫ�������������maxNumber(��)֮�ڵ����� 
	 * ����ÿһ����������ǳɶԳ��ֵ�
	 * 
	 * @param the2DArray
	 *            Ŀ���ά����
	 */
	private void set2DAryWithRanSqnceNmbInPairs(int[][] the2DArray) {
		// ��ȡԪ�سɶԳ��ֵ����������
		Integer[] inpairs = RandomArithmetic.getArrayWithRandomNumberInPairs(
				getDimension() * getDimension(), getMaxNumber());
		// ��ȡ�����˳���������������
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