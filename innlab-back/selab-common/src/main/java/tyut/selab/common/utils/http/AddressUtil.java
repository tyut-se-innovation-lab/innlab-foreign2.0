package tyut.selab.common.utils.http;

import com.alibaba.fastjson2.JSONObject;
import tyut.selab.common.utils.StringUtils;

/**
 * @ClassName: AddressUtil
 * @Description:获取地址工具类
 * @Author: gmslymhn
 * @CreateTime: 2024-05-23 01:34
 * @Version: 1.0
 **/
public class AddressUtil {
    /**
     * 根据IP地址获取地理位置
     * @param ip ip地址
     * @return 地址
     */
//    public static String getAddressByIP(String ip) {
//        if (StringUtils.isBlank(ip)) {
//            return "";
//        }
//        if ("127.0.0.1".equals(ip)) {
//            return "局域网，无法获取位置";
//        }
//        String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_id=6006&format=json&query=" + ip;
//        JSONObject resJson = JSONObject.parseObject(HttpClientUtils.get(url));
////        System.out.println(resJson.getJSONArray("data").get(0));
//        JSONObject data = (JSONObject) resJson.getJSONArray("data").get(0);
//        String location = data.getString("location");
//        return location;
//    }
    public static String getAddressByIP(String ip) {
        if (StringUtils.isBlank(ip)) {
            return "";
        }
        if ("127.0.0.1".equals(ip)) {
            return "局域网，无法获取位置";
        }
//        String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_id=6006&format=json&query=" + ip;
        String url = "https://qifu-api.baidubce.com/ip/geo/v1/district?ip=" + ip;
        JSONObject resJson = JSONObject.parseObject(HttpClientUtils.get(url));
//        System.out.println(resJson.getJSONArray("data").get(0));
        JSONObject data = (JSONObject) resJson.get("data");
        String location = data.getString("prov")+data.getString("city")+data.getString("district");
        return location;
    }

    public static void main(String[] args) {
        System.out.println(getAddressByIP("38.207.136.53"));
    }
}
