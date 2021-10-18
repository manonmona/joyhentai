import mona.joyhentai.http.util.MatchesName;
import mona.joyhentai.http.util.StringRange;
import mona.joyhentai.http.util.StringRangeImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author manonmona
 * @@Date 2021/10/13 18:09
 */
public class RemoveRangeTest {
    public static void main(String[] args) {

//        int myLength = 20;
//
//        double result = 17/(myLength/1.0);
//
//        System.out.println(result);
//        System.out.println(17/myLength);

        String str1 = "[奥森ボウイ] 俺得修学旅行～男は女装した俺だけ!! 第9話 [中国翻訳] [DL版]";
        String str2 = "[奥@森@ボ@ウ@イ] 俺得@修@学@旅@行～男@は女@装した俺だ@け!! 第7@話 [中国翻訳] [DL版]";
        MatchesName matchesName = new MatchesName();
        double probability = matchesName.probability(str1, str2);
        System.out.println(probability);

        System.out.println(Arrays.toString(matchesName.equalIndex(str1,str2)));
        Integer[] ind = matchesName.equalIndexAsTerms(str1,str2);
        System.out.println(Arrays.toString(ind));
    }

    public static void removeRangeTest(){
        StringRange stringRange = new StringRangeImpl();
        String str = "(C88) [出席番号26 (にろ)] 水着愛宕とぱんぱかぱーん (艦隊これくしょん -艦これ-) [中国翻訳]";
        System.out.println(str);
        System.out.println(stringRange.removeBeginRange(str , StringRange.ROUND));
        System.out.println(stringRange.removeEndRange(str , StringRange.SQUARE));
    }
}
