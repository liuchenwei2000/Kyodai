/**
 * 
 */
package arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import util.other.ParameterChecker;

/**
 * ���������������㷨��
 * 
 * @author ����ΰ
 *
 * ����ʱ�䣺2007-10-19
 */
public class RandomArithmetic {
	
	/** ������� */
	private static final Random RANDOM = new Random();
	
	private RandomArithmetic() {
		// do nothing and no instance
	}

	/**
	 * ���ذ����˳������oldAryԪ�غ�������� 
	 * 
	 * �㷨������ 
	 * 1������һ��oldArray.length֮�ڵ������
	 * 2����oldArray�ĸ��������������Ԫ��(����Ϊ��)���뵽list֮������Ϊnull
	 * 3���ظ�����ֱ��oldArray���е�Ԫ�ض������뵽��list��
	 *  
	 * <strong> 
	 * Warning���÷���ִ�к�oldArray��Ԫ�ض�����Ϊnull
	 * </strong>
	 * 
	 * @param oldArray
	 *            Դ����
	 * @return Integer[]
	 */
	public static Integer[] getArrayWithRandomSequence(Integer[] oldArray) {
		ParameterChecker.checkArray(oldArray);
		// ��������ŷź��������
		ArrayList<Integer> newArray = new ArrayList<Integer>();
		do {
			int n = RANDOM.nextInt(oldArray.length);
			// ���������������Ԫ�ز�Ϊnull�����newArray��
			if (oldArray[n] != null) {
				newArray.add(oldArray[n]);
				// ��ԭ����ĸ�Ԫ����Ϊnull
				oldArray[n] = null;
			}
		} while (newArray.size() != oldArray.length);
		return newArray.toArray(new Integer[0]);
	}
	
	/**
	 * ���سɶԳ��ֵ���������� 
	 * (����������������0)
	 * 
	 * @param total
	 *            ��Ҫ������������ĸ���(��Ϊż��)
	 * @param maxNumber
	 *            ���������������ֵ����
	 * @return Integer[]
	 */
	public static Integer[] getArrayWithRandomNumberInPairs(int total,int maxNumber) {
		ParameterChecker.checkArgument(total, maxNumber);
		ArrayList<Integer> al = new ArrayList<Integer>();
		// ���ɸ���Ϊtotalһ������������
		Integer[] half = getArrayWithRandomNumberWithoutZero(total / 2,
				maxNumber);
		// ��half���뵽al������ȷ����������ǳɶԳ��ֵ�
		al.addAll(Arrays.asList(half));
		al.addAll(Arrays.asList(half));
		return al.toArray(new Integer[0]);
	}

	/**
	 * �������������(Ԫ�ط�Χ(0,maxNumber])
	 * 
	 * @param total
	 *            ��Ҫ������������ĸ���
	 * @param maxNumber
	 *            ���������������ֵ����
	 * @return Integer[]
	 */
	public static Integer[] getArrayWithRandomNumberWithoutZero(int total,int maxNumber) {
		ParameterChecker.checkArgument(total, maxNumber);
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < total ; i++) {
			int n = getRandomInteger(maxNumber) + 1;
			al.add(n);
		}
		return al.toArray(new Integer[0]);
	}
	
	/**
	 * �������������(Ԫ�ط�Χ[0,maxNumber))
	 * 
	 * @param total
	 *            ��Ҫ������������ĸ���
	 * @param maxNumber
	 *            ���������������ֵ����
	 * @return Integer[]
	 */
	public static Integer[] getArrayWithRandomNumberWithoutMax(int total,int maxNumber) {
		ParameterChecker.checkArgument(total, maxNumber);
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < total ; i++) {
			int n = getRandomInteger(maxNumber);
			al.add(n);
		}
		return al.toArray(new Integer[0]);
	}
	
	/**
	 * �������������(Ԫ�ط�Χ[0,maxNumber])
	 * 
	 * @param total
	 *            ��Ҫ������������ĸ���
	 * @param maxNumber
	 *            ���������������ֵ����
	 * @return Integer[]
	 */
	public static Integer[] getArrayWithRandomNumber(int total,int maxNumber) {
		return getArrayWithRandomNumberWithoutMax(total, maxNumber + 1);
	}

	/**
	 * ����maxNumber֮�ڵ��������
	 * 
	 * @param maxNumber
	 *            ���������ֵ����
	 * @return int
	 */
	public static int getRandomInteger(int maxNumber) {
		ParameterChecker.checkMaxNumber(maxNumber);
		return RANDOM.nextInt(maxNumber);
	}
}