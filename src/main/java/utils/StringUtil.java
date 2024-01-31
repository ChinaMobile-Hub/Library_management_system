package utils;

/**
 * @ClassName StringUtil
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/10 ����5:39
 * @Version 1.0
 **/
public class StringUtil {
    /**
     * �ж��ַ����Ƿ�Ϊ��
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str.length()==0) return true;
        return false;
    }

    /**
     * �жϴ���Ĳ���������һ��������0��
     * @param str
     * @return
     */
    public static boolean hasEmpty(String... str) {
        for (String s : str) {
            if (isEmpty(s)) return true;
        }
        return false;
    }

    /**
     * �жϸ��ַ����а������ǲ���int������
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * �ж��ǲ��Ǹ�����
     * @param str
     * @return
     */
    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
