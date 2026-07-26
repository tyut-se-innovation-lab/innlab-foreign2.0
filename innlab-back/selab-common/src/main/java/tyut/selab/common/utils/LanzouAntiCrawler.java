package tyut.selab.common.utils;

import lombok.extern.slf4j.Slf4j;
import tyut.selab.common.utils.http.HttpsUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LanzouAntiCrawler {

    private static final int[] POS_LIST = {15, 35, 29, 24, 33, 16, 1, 38, 10, 9, 19, 31, 40, 27, 22, 23, 25, 13, 6, 11, 39, 18, 20, 8, 14, 21, 32, 26, 2, 30, 7, 4, 17, 5, 3, 28, 34, 37, 12, 36};
    private static final String MASK = "3000176000856006061501533003690027800375";

    /**
     * 提取 arg1 参数
     */
    public static String extractArg1(String html) {
        // 尝试多种正则表达式模式
        String[] patterns = {
                "var arg1\\s*=\\s*'([^']+)'",
                "arg1\\s*=\\s*['\"]([A-F0-9]+)['\"]",
                "['\"]([A-F0-9]{40})['\"]"
        };

        for (String patternStr : patterns) {
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(html);
            if (matcher.find()) {
                log.info("提取到 arg1: " + matcher.group(1));
                return matcher.group(1);
            }
        }

        log.error("无法提取 arg1 参数");
        return null;
    }

    /**
     * 执行反爬挑战计算
     */
    public static String executeChallenge(String arg1) {
        try {
            // 重新排列字符
            char[] output = new char[POS_LIST.length];

            for (int i = 0; i < arg1.length(); i++) {
                char ch = arg1.charAt(i);
                for (int j = 0; j < POS_LIST.length; j++) {
                    if (POS_LIST[j] == i + 1) {
                        output[j] = ch;
                        break;
                    }
                }
            }

            String arg2 = new String(output);

            // 异或运算
            StringBuilder arg3 = new StringBuilder();
            int length = Math.min(arg2.length(), MASK.length());

            for (int i = 0; i < length; i += 2) {
                String strHex = arg2.substring(i, Math.min(i + 2, arg2.length()));
                String maskHex = MASK.substring(i, Math.min(i + 2, MASK.length()));

                int strChar = Integer.parseInt(strHex, 16);
                int maskChar = Integer.parseInt(maskHex, 16);
                int xorChar = strChar ^ maskChar;

                String hex = Integer.toHexString(xorChar);
                if (hex.length() == 1) {
                    hex = "0" + hex;
                }
                arg3.append(hex);
            }

            return "acw_sc__v2=" + arg3.toString();

        } catch (Exception e) {
            log.error("挑战计算失败: " + e.getMessage());
            return null;
        }
    }

    /**
     * 处理反爬挑战
     */
    public static String handleChallenge(String html, String url, String cookie) {
        if (html.contains("acw_sc__v2") && html.contains("arg1")) {
            log.info("检测到反爬挑战，正在处理...");

            String arg1 = extractArg1(html);
            if (arg1 == null) {
                throw new RuntimeException("无法提取挑战参数");
            }

            String challengeCookie = executeChallenge(arg1);
            if (challengeCookie == null) {
                throw new RuntimeException("挑战计算失败");
            }

            log.info("生成的 cookie: " + challengeCookie);

            // 添加 cookie 重新请求
            Map<String, String> headers = new HashMap<>();
            headers.put("Cookie", cookie + "; " + challengeCookie);

            // 重新请求
            String newHtml = HttpsUtils.sendGetWithHeaders(url, headers);

            if (newHtml.contains("acw_sc__v2") && newHtml.contains("arg1")) {
                throw new RuntimeException("挑战处理失败");
            }

            log.info("挑战处理成功");
            return newHtml;
        }

        return html;
    }
}
