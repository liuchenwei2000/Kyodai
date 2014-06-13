/**
 * 
 */
package assistant;

/**
 * �����������ӿ�
 * 
 * @author ����ΰ
 *
 * ����ʱ�䣺2007-10-25
 */
public interface KyodaiConstant {
	/** ��ťgifͼƬ����Ŀ */
	int NUMBER_OF_ICONS = 39;
	/** ����jpgͼƬ����Ŀ */
	int NUMBER_OF_IMAGES = 10;
	/** ����ͼ���ͼƬ�� */
	int REFRESH_ICON = 40;
	
	/** gifͼƬ�ĳ��������� */
	int X_PIX_OF_ICON = 31;
	/** gifͼƬ�Ŀ�������� */
	int Y_PIX_OF_ICON = 34;
	
	/** ��������ť�ĳ��� */
	int LENGTH_OF_BUTTON = X_PIX_OF_ICON;
	/** ��������ť�Ŀ�� */
	int WIDTH_OF_BUTTON = Y_PIX_OF_ICON;
	
	/** Ĭ����ȥһ�԰�ť�ķ��� */
	int INCREMENT_OF_SCORE = 10;
	
	/** ��������ť�����ʾʱ����չ���� */
	int EXTENSION_LENGTH = 60;
	
	/** ��������ť�����ʾʱ����չ��� */
	int EXTENSION_WIDTH = 60;
	
	/** �ͼ���Ӧ��ģ��ά�� */
	int LOW = 6;
	/** �м���Ӧ��ģ��ά�� */
	int MIDDLE = 12;
	/** �߼���Ӧ��ģ��ά�� */
	int HIGH = 18;
	
	/** �汾��Ϣ */
	String VERSION = "������V2.3";
	
	/** jpgͼƬ��׺�� */
	String SUFFIX_JPG = ".jpg";
	/** gifͼƬ��׺�� */
	String SUFFIX_GIF = ".gif";
	
	/** ͼ��·�� */
	String PATH_OF_ICONS = "images/";
	/** ͼƬ·�� */
	String PATH_OF_IMAGES = "images/bkgrd/";
	
	/** ��߷ִ洢�ļ����·�� */
	String SCORE = "score.info";
	
	/** ��ȥ �����ļ�·�� */
	String REMOVE = "sound/remove.wav";
	/** ���� �����ļ�·�� */
	String REFRESH = "sound/refresh.wav";
	/** ѡ�� �����ļ�·�� */
	String SELECT = "sound/select.wav";
}