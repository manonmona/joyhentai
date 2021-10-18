package mona.joyhentai.http.util;

import mona.joyhentai.model.Books;

import java.util.*;

/**
 * @Author manonmona
 * @@Date 2021/10/15 16:29
 */
public class MatchesName {

    /**
     * 匹配相同概率
     * @param myself
     * @param herself
     * @return
     */
    public double probability(String myself, String herself){
        int myLength = myself.length();
        int herLength = herself.length();

        String sortStr , longStr;
        if (myLength > herLength) {
            sortStr = herself;
            longStr = myself;
        }else{
            sortStr = myself;
            longStr = herself;
        }
        //记录相同字符个数
        int equalCount = 0;
        for (int i = 0; i < sortStr.length(); i++) {
            char a = sortStr.charAt(i);
            if(longStr.contains(a+"")){
                equalCount++;
            }
        }

        return equalCount/(sortStr.length()/1.0);
    }

    /**
     * 返回匹配名称的索引
     * @param myself
     * @param herself
     * @return
     */
    public Integer[] equalIndex(String myself, String herself){
        Set<Integer> indexArr = new TreeSet<>();//保证元素的自然顺序

        int myLength = myself.length();
        int herLength = herself.length();

        boolean isMe = true;
        String sortStr , longStr;
        if (myLength > herLength) {
            sortStr = herself;
            longStr = myself;
        }else{
            sortStr = myself;
            longStr = herself;
            isMe = false;
        }

        for (int i = 0; i < sortStr.length(); i++) {
            char a = sortStr.charAt(i);
            for (int j = 0; j < longStr.length(); j++) {
                if(longStr.charAt(j)==a){
                    if(isMe){
                        indexArr.add(i);
                    }else{
                        indexArr.add(j);
                    }
                }
            }
        }
        return indexArr.toArray(new Integer[]{});
    }

    /**S
     * 返回匹配名称词组的索引
     * @param myself
     * @param herself
     * @return
     */
    public Integer[] equalIndexAsTerms(String myself, String herself){
        Integer[] equalIndex = this.equalIndex(myself, herself);
        Set<Integer> indexArr = new TreeSet<>();//保证元素的自然顺序
        for (int i = 1; i <equalIndex.length ; i++) {
            // 判断，如果上一个索引和当前索引是紧连着的，则符合词组
            int begin = equalIndex[i-1];
            int end = equalIndex[i];
            if( begin == end-1){
                indexArr.add(begin);
                indexArr.add(end);
            }
        }
        return indexArr.toArray(new Integer[]{});
    }
}
