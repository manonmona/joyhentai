package mona.joyhentai.http.util;

/**
 * @Author manonmona
 * @@Date 2021/10/13 17:18
 */
public class StringRangeImpl implements StringRange{

    /**
     * 移除区间内容（移除括号内容）
     * @return
     */
    public String removeRange(String strName , String rangeType){

        boolean result = condition(rangeType);
        if (!result){
            new Exception("不符合和去间区内容类型").printStackTrace();
            return strName;
        }

        char begin = rangeType.charAt(0);
        char end = rangeType.charAt(1);

        /**
         * 移除闭合区间内容
         */
        while (true){
            int i = strName.indexOf(begin);
            // 如果区间内不再包含，则退出
            if(i == -1)break;

            int j = strName.indexOf(end);
            strName = removeDomain(strName, i , j);

        }
        // 去前后空格
        return strName.trim();
    }

    /**
     * 判断是否符合条件
     * @param rangeType
     * @return
     */
    private boolean condition(String rangeType){
        String [] rangeTypes = {"[]","【】","()","（）"};
        boolean result = false;
        // 判断是否有符合去除区间内容的类型
        for(String str : rangeTypes){
            if(str.equals(rangeType)){
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * 移除字符串指定范围
     * @param str
     * @param beginIndex
     * @param endIndex
     * @return
     */
    private String removeDomain(String str , int beginIndex , int endIndex){
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0 , beginIndex));
        sb.append(str.substring(endIndex+1));

        return sb.toString();
    }

    /**
     * 移除开头区间
     * @param strName
     * @param rangeType
     * @return
     */
    public String removeBeginRange(String strName , String rangeType){

        boolean result = condition(rangeType);
        if (!result){
            new Exception("不符合和去间区内容类型").printStackTrace();
            return strName;
        }

        char begin = rangeType.charAt(0);
        char end = rangeType.charAt(1);

        // 查看开头字符是否是区间，如果不是，则不包含开头区间，直接退出
        if(strName.trim().charAt(0)!=begin){
            return strName;
        }

        /**
         * 移除闭合区间内容
         */
        int endIndex = strName.indexOf(end);
        strName = strName.substring(endIndex+1);

        // 去前后空格
        return strName.trim();
    }

    /**
     * 移除末尾区间
     * @param strName
     * @param rangeType
     * @return
     */
    public String removeEndRange(String strName , String rangeType){

        boolean result = condition(rangeType);
        if (!result){
            new Exception("不符合和去间区内容类型").printStackTrace();
            return strName;
        }

        char begin = rangeType.charAt(0);
        char end = rangeType.charAt(1);

        int lastIndex = strName.trim().length();
        // 查看开头字符是否是区间，如果不是，则不包含开头区间，直接退出
        if(strName.trim().charAt(lastIndex-1)!=end){
            return strName;
        }

        /**
         * 移除闭合区间内容
         */
        int beginIndex = strName.lastIndexOf(begin);
        strName = strName.substring(0 , beginIndex-1);

        // 去前后空格
        return strName.trim();
    }

}
