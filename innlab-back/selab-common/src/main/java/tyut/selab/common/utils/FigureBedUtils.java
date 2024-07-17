package tyut.selab.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import tyut.selab.common.domain.Lz;
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
        Pattern skdkldsPattern = Pattern.compile("var skdklds = '([^']+)'");
        Matcher skdkldsMatcher = skdkldsPattern.matcher(jsCode);
        if(skdkldsMatcher.find()){
            return skdkldsMatcher.group(1);
        }
        return "";
    }

    public static String getLz(Lz lz){
        String url = lz.getIsNewd()+"/"+lz.getFId();
        String jsCode =HttpsUtils.sendGet(url);
        String url1 = "https://wwd.lanzoue.com/ajaxm.php?file="+extractUrl(jsCode);
        String skdklds = extractSkdklds(jsCode);
        String xwwwfrom = "action=downprocess&sign="+skdklds+"&p="+lz.getPwd();
        String json = HttpsUtils.sendSSLPost(url1,url,null,xwwwfrom);
        JSONObject data = JSON.parseObject(json);
        String geturl = data.getString("url");
        if (geturl.equals("0")){
            return null;
        }
        String url2 = data.getString("dom")+"/file/"+geturl;
        System.out.println(url2);
        return url2;
    }
    public static void main(String[] args) throws IOException {
        String jsCode =HttpsUtils.sendGet("https://www.lanzouh.com/iriea24ezvcj");
        String url1 = "https://wwd.lanzoue.com/ajaxm.php?file="+extractUrl(jsCode);
        String skdklds = extractSkdklds(jsCode);
        String xwwwfrom = "action=downprocess&sign="+skdklds+"&p="+"6mkr";
        String json = HttpsUtils.sendSSLPost(url1,"https://www.lanzouh.com/iriea24ezvcj",null,xwwwfrom);
        JSONObject data = JSON.parseObject(json);
        String geturl = data.getString("url");
        String url2 = data.getString("dom")+"/file/"+geturl;
        System.out.println(url2);
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
