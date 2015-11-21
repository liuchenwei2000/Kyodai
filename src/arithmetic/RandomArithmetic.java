/**
 * 
 */
package arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import util.other.ParameterChecker;

/**
 * 产生随机数数组的算法类
 * 
 * @author 刘晨伟
 *
 * 创建时间：2007-10-19
 */
public class RandomArithmetic {
	
	/** 随机对象 */
	private static final Random RANDOM = new Random();
	
	private RandomArithmetic() {
		// do nothing and no instance
	}

	/**
	 * 返回按随机顺序排列oldAry元素后的新数组 
	 * 
	 * 算法描述： 
	 * 1，产生一个oldArray.length之内的随机数
	 * 2，将oldArray的该随机数索引处的元素(若不为空)加入到list之后设置为null
	 * 3，重复进行直到oldArray所有的元素都被加入到了list中
	 *  
	 * <strong> 
	 * Warning：该方法执行后oldArray的元素都将变为null
	 * </strong>
	 * 
	 * @param oldArray
	 *            源数组
	 * @return Integer[]
	 */
	public static Integer[] getArrayWithRandomSequence(Integer[] oldArray) {
		ParameterChecker.checkArray(oldArray);
		// 进行随机排放后的新数组
		ArrayList<Integer> newArray = new ArrayList<Integer>();
		do {
			int n = RANDOM.nextInt(oldArray.length);
			// 若随机数索引处的元素不为null则加入newArray中
			if (oldArray[n] != null) {
				newArray.add(oldArray[n]);
				// 把原数组的该元素置为null
				oldArray[n] = null;
			}
		} while (newArray.size() != oldArray.length);
		return newArray.toArray(new Integer[0]);
	}
	
	/**
	 * 返回成对出现的随机数数组 
	 * (这里的随机数不包括0)
	 * 
	 * @param total
	 *            所要产生的随机数的个数(需为偶数)
	 * @param maxNumber
	 *            所产生随机数的数值上限
	 * @return Integer[]
	 */
	public static Integer[] getArrayWithRandomNumberInPairs(int total,int maxNumber) {
		ParameterChecker.checkArgument(total, maxNumber);
		ArrayList<Integer> al = new ArrayList<Integer>();
		// 生成个数为total一半的随机数数组
		Integer[] half = getArrayWithRandomNumberWithoutZero(total / 2,
				maxNumber);
		// 把half加入到al中两次确保随机数都是成对出现的
		al.addAll(Arrays.asList(half));
		al.addAll(Arrays.asList(half));
		return al.toArray(new Integer[0]);
	}

	/**
	 * 返回随机数数组(元素范围(0,maxNumber])
	 * 
	 * @param total
	 *            所要产生的随机数的个数
	 * @param maxNumber
	 *            所产生随机数的数值上限
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
	 * 返回随机数数组(元素范围[0,maxNumber))
	 * 
	 * @param total
	 *            所要产生的随机数的个数
	 * @param maxNumber
	 *            所产生随机数的数值上限
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
	 * 返回随机数数组(元素范围[0,maxNumber])
	 * 
	 * @param total
	 *            所要产生的随机数的个数
	 * @param maxNumber
	 *            所产生随机数的数值上限
	 * @return Integer[]
	 */
	public static Integer[] getArrayWithRandomNumber(int total,int maxNumber) {
		return getArrayWithRandomNumberWithoutMax(total, maxNumber + 1);
	}

	/**
	 * 返回maxNumber之内的随机整数
	 * 
	 * @param maxNumber
	 *            随机数的数值上限
	 * @return int
	 */
	public static int getRandomInteger(int maxNumber) {
		ParameterChecker.checkMaxNumber(maxNumber);
		return RANDOM.nextInt(maxNumber);
	}
}
