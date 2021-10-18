package mona.joyhentai.http.util;

/**
 * @Author manonmona
 * @@Date 2021/10/15 16:12
 */
public interface StringRange {

    /**
     * 方括号
     */
    public static final String SQUARE = "[]";
    public static final String CN_SQUARE = "【】";
    /**
     * 圆括号
     */
    public static final String ROUND = "()";
    public static final String CN_ROUND = "（）";


    /**
     * 移除区间内容（移除括号内容）
     * @return
     */
    public String removeRange(String strName , String rangeType);

    /**
     * 移除开头区间
     * @param strName
     * @param rangeType
     * @return
     */
    public String removeBeginRange(String strName , String rangeType);

    /**
     * 移除末尾区间
     * @param strName
     * @param rangeType
     * @return
     */
    public String removeEndRange(String strName , String rangeType);
}
