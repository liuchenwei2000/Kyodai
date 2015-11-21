/**
 * 
 */
package assistant;

/**
 * 连连看常量接口
 * 
 * @author 刘晨伟
 *
 * 创建时间：2007-10-25
 */
public interface KyodaiConstant {
	/** 按钮gif图片的数目 */
	int NUMBER_OF_ICONS = 39;
	/** 背景jpg图片的数目 */
	int NUMBER_OF_IMAGES = 10;
	/** 重列图标的图片号 */
	int REFRESH_ICON = 40;
	
	/** gif图片的长度像素数 */
	int X_PIX_OF_ICON = 31;
	/** gif图片的宽度像素数 */
	int Y_PIX_OF_ICON = 34;
	
	/** 连连看按钮的长度 */
	int LENGTH_OF_BUTTON = X_PIX_OF_ICON;
	/** 连连看按钮的宽度 */
	int WIDTH_OF_BUTTON = Y_PIX_OF_ICON;
	
	/** 默认消去一对按钮的分数 */
	int INCREMENT_OF_SCORE = 10;
	
	/** 连连看按钮面板显示时的扩展长度 */
	int EXTENSION_LENGTH = 60;
	
	/** 连连看按钮面板显示时的扩展宽度 */
	int EXTENSION_WIDTH = 60;
	
	/** 低级对应的模型维数 */
	int LOW = 6;
	/** 中级对应的模型维数 */
	int MIDDLE = 12;
	/** 高级对应的模型维数 */
	int HIGH = 18;
	
	/** 版本信息 */
	String VERSION = "连连看V2.3";
	
	/** jpg图片后缀名 */
	String SUFFIX_JPG = ".jpg";
	/** gif图片后缀名 */
	String SUFFIX_GIF = ".gif";
	
	/** 图标路径 */
	String PATH_OF_ICONS = "images/";
	/** 图片路径 */
	String PATH_OF_IMAGES = "images/bkgrd/";
	
	/** 最高分存储文件相对路径 */
	String SCORE = "score.info";
	
	/** 消去 音乐文件路径 */
	String REMOVE = "sound/remove.wav";
	/** 重列 音乐文件路径 */
	String REFRESH = "sound/refresh.wav";
	/** 选择 音乐文件路径 */
	String SELECT = "sound/select.wav";
}
