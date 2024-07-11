package tyut.selab.common.utils;

import java.util.Random;

/**
 * @ClassName: RandomUtils
 * @Description: 随机生成字符串
 * @Author: gmslymhn
 * @CreateTime: 2024-05-18 12:24
 * @Version: 1.0
 **/
public class RandomUtils {
    public static String createCode(int n ){
        // 1、定义可能出现的字符信息
        String datas = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456";

        // 2、循环n次，每次生成一个随机的索引，提取相应的字符连接起来即可
        String code = "";
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            // 随机获得一个索引
            int index = r.nextInt(datas.length());
            char c = datas.charAt(index);
            code+=c;
        }
        // 3、输出生成的字符串
        return code;
    }

    public static String createCode1(int n ){
        // 1、定义可能出现的字符信息
        String datas = "0123456789";

        // 2、循环n次，每次生成一个随机的索引，提取相应的字符连接起来即可
        String code = "";
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            // 随机获得一个索引
            int index = r.nextInt(datas.length());
            char c = datas.charAt(index);
            code+=c;
        }
        // 3、输出生成的字符串
        return code;
    }
}
