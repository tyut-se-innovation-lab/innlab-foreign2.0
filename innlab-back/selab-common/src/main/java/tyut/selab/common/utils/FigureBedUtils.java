package tyut.selab.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import tyut.selab.common.domain.Lz;
import tyut.selab.common.utils.http.HttpClientUtils;
import tyut.selab.common.utils.http.HttpsUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @ClassName: FigureBedUtils
 * @Description: 图床工具类
 * @Author: gmslymhn
 * @CreateTime: 2024-05-23 13:19
 * @Version: 1.0
 **/
@Slf4j
public class FigureBedUtils {

    public static Lz addLz(File file,String cookie,String folder_id_bb_n) throws IOException {
        String newPayUrl="https://pc.woozooo.com/html5up.php";
        Map<String,String> map = new HashMap<>();
        map.put("folder_id_bb_n",folder_id_bb_n);
        map.put("task","1");
        map.put("ve","2");
        map.put("vie","2");
        JSONObject resJson = JSONObject.parseObject(HttpsUtils.doPostFormDataFile(newPayUrl,file,map,cookie));
        JSONObject data = (JSONObject) resJson.getJSONArray("text").get(0);
        String id = data.getString("id");
        JSONObject json1 = JSONObject.parseObject(HttpsUtils.sendSSLPost("https://pc.woozooo.com/doupload.php",null,cookie,"task=22&file_id="+id));
        JSONObject json2 = json1.getJSONObject("info");
        Lz lz = new Lz();
        lz.setPwd(json2.getString("pwd"));
        lz.setFId(json2.getString("f_id"));
        lz.setIsNewd(json2.getString("is_newd"));
        return lz;
    }

    private static String extractUrl(String jsCode) {
        Pattern filePattern = Pattern.compile("'/ajaxm.php\\?file=(\\d+)'");
        Matcher fileMatcher = filePattern.matcher(jsCode);
        String file = "";
        if(fileMatcher.find()){
            return fileMatcher.group(1);
        }
        return "" ;
    }
    /**
     * 正则表达
     * @param jsCode
     * @return
     */
    private static String extractSkdklds(String jsCode) {
        if (jsCode == null) {
            return "";
        }
        Pattern signPattern = Pattern.compile("'sign':'([^']+)'");
        Matcher signMatcher = signPattern.matcher(jsCode);
        log.info(String.valueOf(signMatcher));
//        while (signMatcher.find()) {
//            String sign = signMatcher.group(1);
//            log.info(sign);
//        }
        String sign1 = "";
        String sign2 = "";
//        if (signMatcher.find()) {
        if (signMatcher.find()) {
            sign1 =signMatcher.group(1);
        }
        if (signMatcher.find()) {
            sign2 =signMatcher.group(1);
        }
        if (sign2.isEmpty()){
            return sign1;
        }else {
            return sign2;
        }
//        }
    }

    public static String getLz(Lz lz) {
        String url = lz.getIsNewd() + "/" + lz.getFId();

        // 第一次请求，获取页面
        String jsCode = HttpsUtils.sendGetWithHttpClient(url);

        System.out.println(jsCode);
        // 检查是否是反爬挑战页面
        if (jsCode.contains("acw_sc__v2") && jsCode.contains("arg1")) {
            log.info("检测到反爬挑战，开始处理...");

            try {
                // 处理挑战
                jsCode = LanzouAntiCrawler.handleChallenge(jsCode, url, "");

                // 再次检查是否还有挑战
                if (jsCode.contains("acw_sc__v2") && jsCode.contains("arg1")) {
                    log.error("挑战处理失败，仍然存在反爬页面");
                    return null;
                }

            } catch (Exception e) {
                log.error("反爬挑战处理失败: " + e.getMessage());
                return null;
            }
        }

        // 提取 file 参数
        String fileParam = extractUrl(jsCode);
        if (StringUtils.isEmpty(fileParam)) {
            log.error("无法提取 file 参数");
            return null;
        }

        String url1 = lz.getIsNewd() + "/ajaxm.php?file=" + fileParam;
        log.info("提取的 file 参数: " + fileParam);

        // 提取 sign 参数
        String skdklds = extractSkdklds(jsCode);
        if (StringUtils.isEmpty(skdklds)) {
            log.error("无法提取 sign 参数");
            return null;
        }

        log.info("提取的 sign: " + skdklds);

        String xwwwfrom = "action=downprocess&sign=" + skdklds + "&p=" + lz.getPwd() + "&kd=1";
        log.info("POST数据: " + xwwwfrom);

        // 发送 POST 请求
        String json = HttpsUtils.sendSSLPost(url1, url, null, xwwwfrom);

        if (StringUtils.isEmpty(json)) {
            log.error("图片：" + lz.getFId() + " 获取失败！");
            return null;
        }

        JSONObject data = JSON.parseObject(json);
        String geturl = data.getString("url");

        if ("0".equals(geturl)) {
            log.error("服务器返回错误: " + data.getString("inf"));
            return null;
        }

        // 获取最终 URL
        String url2 = data.getString("dom") + "/file/" + geturl;
        log.info("中间 URL: " + url2);

        // 获取重定向 URL
        Map<String, Object> headers = new HashMap<>();
        headers.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        headers.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        headers.put("referer", url);
        headers.put("cookie", "down_ip=1");

        String url3 = HttpClientUtils.get2(url2, headers);

        if (StringUtils.isEmpty(url3)) {
            log.error("获取最终 URL 失败");
            return null;
        }

        log.info("最终 URL: " + url3);
        return url3;
    }




    public static void main(String[] args) throws IOException {
//        String jsCode =HttpsUtils.sendGet("https://www.lanzouh.com/iriea24ezvcj");
//        String url1 = "https://wwd.lanzoue.com/ajaxm.php?file="+extractUrl(jsCode);
//        String skdklds = extractSkdklds(jsCode);
//        String xwwwfrom = "action=downprocess&sign="+skdklds+"&p="+"6mkr";
//        String json = HttpsUtils.sendSSLPost(url1,"https://www.lanzouh.com/iriea24ezvcj",null,xwwwfrom);
//        JSONObject data = JSON.parseObject(json);
//        String geturl = data.getString("url");
//        String url2 = data.getString("dom")+"/file/"+geturl;
//        System.out.println(url2);
        Lz lz = new Lz("5ia8","iF23l2z8ziwb","https://innlab.lanzn.com");
        System.out.println(getLz(lz));
//        String newPayUrl="https://pc.woozooo.com/html5up.php";
//        File file = new File("C:/Users/gmsly/Pictures/Default.jpg.it");
//        Map<String,String> map = new HashMap<>();
////        map.put("upload_file",file);
//        map.put("folder_id_bb_n","9922980");
//        map.put("task","1");
//        map.put("ve","2");
//        map.put("vie","2");
//
//        String cookie = "phpdisk_info=UGcHMVc1Um4PPQNgWjFVBgZkATFeDgJkBjBVMw86AzRZal9pVzcHOw49UDAIWwFpVTQEMw1sUWIHYlU0BWZWbFA3BzxXYFJmD2kDZlplVT0GNQFlXmMCYQZjVWcPawM2WTxfZVczB28OPVA5CGABUlU0BDMNYlE1BzFVMgU3VmZQZAc0VzU%3D; uag=0cb02a08b733cb8746c46ab5aa1e121f; tfstk=fLsEi9mArkEeHAbk_Cty_ropbmKpVnFbUgOWETXkdBAHVWQZZ1ftFBtBANRPs1R3pWFda0fNnUA5V3MdWs6mAMapV0-p23VbGoZjp9KJqwh_xSGLjLpzV2vHZKWJb5VbGoZ_-AxzTS_5p8F9bLdMKLxkqRuMeCvnK_AHSfv2H3AlZ32wjKJJEDYn-ADM3bIFKTmwheVKG9sTA59J8IXak0olwmTpiORZqSjwL7AcKCooqBmTbbXNdWoACHIfgK1QjD5VEtQk7glggQINIMblBXPkBafBjBt0-A-Cv9bDriV-rs-FaEjH7YmejhRkbdj0Fqd1bB1lYF2j0UtGcExh5PFBPHJVZMCEU0fcCtspWgPqsQQBHhvP2-0yaUjPbbpGvFSR8b0y-dpwGRyM0y12wW7z_A0-yFxvQIwBd43J-dpwGRyZy4LMHdRbdp1..; PHPSESSID=e3ofq2m8esth277rj1enf0tojlh0ebvl; __tins__21412745=%7B%22sid%22%3A%201716441642047%2C%20%22vd%22%3A%201%2C%20%22expires%22%3A%201716443442047%7D;";
//
//        JSONObject resJson = JSONObject.parseObject(HttpsUtils.doPostFormDataFile("https://pc.woozooo.com/html5up.php",file,map,cookie));
//        JSONObject data = (JSONObject) resJson.getJSONArray("text").get(0);
//        String id = data.getString("id");
//        JSONObject json1 = JSONObject.parseObject(HttpsUtils.sendSSLPost("https://pc.woozooo.com/doupload.php",null,cookie,"task=22&file_id="+id));
//        JSONObject json2 = json1.getJSONObject("info");
//        Lz lz = new Lz();
//        lz.setPwd(json2.getString("pwd"));
//        lz.setFId(json2.getString("f_id"));
//        lz.setIsNewd(json2.getString("is_newd"));
//        System.out.println(lz);
        //        JSONObject date = new JSONObject(map);
//        String response= HttpClientUtils.postForm(newPayUrl,null,map,cookie);
//        System.out.println(response);
    }
}
