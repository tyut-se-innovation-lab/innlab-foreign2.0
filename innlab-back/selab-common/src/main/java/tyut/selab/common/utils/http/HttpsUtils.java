package tyut.selab.common.utils.http;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import tyut.selab.common.constant.Constants;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.common.utils.StringUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * @ClassName: HttpsUtils
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-03-17 19:27
 * @Version: 1.0
 **/
@Slf4j
public class HttpsUtils
{


    public static String sendGetWithHttpClient(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        // 设置请求头
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            // HttpClient 会自动处理 gzip 解压缩
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            log.error("请求失败", e);
        }
        return null;
    }

    public static String sendGetWithHeaders(String url, Map<String, String> headers) {
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // 设置请求方法
            connection.setRequestMethod("GET");

            // 设置默认 headers
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
            connection.setRequestProperty("Accept-Encoding", "gzip");

            // 添加自定义 headers
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            // 处理响应
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String contentEncoding = connection.getHeaderField("Content-Encoding");
                InputStream inputStream;

                if ("gzip".equalsIgnoreCase(contentEncoding)) {
                    inputStream = new GZIPInputStream(connection.getInputStream());
                } else {
                    inputStream = connection.getInputStream();
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                return response.toString();
            }

        } catch (Exception e) {
            log.error("请求失败", e);
        }
        return null;
    }
    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url 发送请求的 URL
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url)
    {
        return sendGet(url, StringUtils.EMPTY);
    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param)
    {
        return sendGet(url, param, Constants.UTF8);
    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param contentType 编码类型
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String contentType)
    {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try
        {
            String urlNameString = StringUtils.isNotBlank(param) ? url + "?" + param : url;
            long t1 = System.currentTimeMillis();
            log.info("sendGet -- 开始接口调用，URL：{}", urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), contentType));
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
//            log.info("recv - {}", result);
            long t2 = System.currentTimeMillis();
            log.info("sendGet -- 调用接口结束，耗时：{}", t2 - t1);
//            log.info("sendGet -- 调用接口结束，耗时：{}，响应结果：{}", t2 - t1, result);

        }
        catch (ConnectException e)
        {
            log.error("调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e)
        {
            log.error("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, e);
        }
        catch (IOException e)
        {
            log.error("调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e)
        {
            log.error("调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, e);
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception ex)
            {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try
        {
            log.info("sendPost - {}", url);
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
            log.info("recv - {}", result);
        }
        catch (ConnectException e)
        {
            log.error("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e)
        {
            log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        }
        catch (IOException e)
        {
            log.error("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e)
        {
            log.error("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }



    /**
     * 蓝奏云一层直链解密
     * @param url
     * @param referer
     * @param param
     * @return
     */

    public static String sendSSLPost(String url,String referer,String cookie,String param) {
        StringBuilder response = new StringBuilder();
//        String url = "https://wwd.lanzoue.com/ajaxm.php?file=169194924"; // 请求的URL
//        String referer = "https://wwd.lanzoue.com/iRT8U1rpelze";
        String contentType = "application/x-www-form-urlencoded";
//        String content = "action=downprocess&sign=AmQBP1tqBTRVXAU6CjoHO1M4ATICZAEyBjgHNVM2VmVUYABxXndXPlUyB2ZXMlBhVTkDPgJtCzoFMVJn&p=2y5e";

        String content = param;
        try {
            long t1 = System.currentTimeMillis();
            log.info("sendSSLPost -- 开始接口调用，URL：{}", url);
            URL urlObj = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) urlObj.openConnection();
            conn.setRequestMethod("POST");
            if (ObjectUtils.isNotNull(referer)){
                conn.setRequestProperty("Referer", referer);
            }
            if (ObjectUtils.isNotNull(cookie)){
                conn.setRequestProperty("Cookie", cookie);
            }
            conn.setRequestProperty("Content-Type", contentType);
            conn.setDoOutput(true);
            conn.setRequestProperty("User-Agent", "Mozilla/4.76");

            // 写入请求体
            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(content);
            osw.flush();
            osw.close();

            // 读取响应
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            long t2 = System.currentTimeMillis();
            log.info("sendSSLPost -- 调用接口结束，耗时：{}，响应结果：{}", t2 - t1, response);


            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    private static class TrustAnyTrustManager implements X509TrustManager
    {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
        {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
        {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier
    {
        @Override
        public boolean verify(String hostname, SSLSession session)
        {
            return true;
        }
    }

    /**
     * 以post方式调用第三方接口,以 form-data 形式  发送 MultipartFile 文件数据
     *
     * @param url              post请求url
     * @param multipartFile    文件
     * @param otherFormDataMap 表单中的其他参数
     * @return 返回 接口返回结果
     */
    public static String doPostFormDataFile(String url, File multipartFile, Map<String, String> otherFormDataMap,String cookie) throws IOException {
        File toFile = null;
        try{
            toFile = multipartFile;
            Map<String, Object> data = new HashMap<>();
            data.put("upload_file", toFile);
            data.putAll(otherFormDataMap);
            int connectTimeout = 50000; // 连接超时时间为5秒
            int readTimeout = 100000; // 读取超时时间为10秒
            long t1 = System.currentTimeMillis();
            log.info("doPostFormDataFile -- 开始接口调用，otherFormDataMap：{}，fileName：{}", JSON.toJSONString(otherFormDataMap));
            String response = HttpRequest.post(url)
                    .header("Cookie", cookie)
                    .form(data)
                    .contentType("multipart/form-data")
                    .setConnectionTimeout(connectTimeout)
                    .setReadTimeout(readTimeout)
                    .execute()
                    .body();
            long t2 = System.currentTimeMillis();
            log.info("doPostFormDataFile -- 调用接口结束，耗时：{}，otherFormDataMap：{}，fileName：{}，响应结果：{}", t2 - t1, JSON.toJSONString(otherFormDataMap), response);
            return response;
        }finally{
            if(toFile != null){
                //.it为临时文件使用后，在这里使用delete()删除
                toFile.delete();
            }
        }
    }


    public static void downloadImageUsingSendGet(String url)throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        InputStream inputStream =null;
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            // 调用sendGet方法获取图片输入流
            inputStream = connection.getInputStream();
        } else {
            throw new IOException("HTTP响应码：" + responseCode);
        }
        try {

            // 创建保存路径
            File dir = new File("D:/GM/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = url.substring(132,151);
            System.out.println(fileName);
            // 创建目标文件
            File targetFile = new File("D:/GM/" + fileName);

            // 写入文件
            try (FileOutputStream outputStream = new FileOutputStream(targetFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                System.out.println("图片保存成功，路径：" + targetFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("保存图片失败：" + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("下载或保存图片失败：" + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        downloadImageUsingSendGet("https://pdf1.webgetstore.com/2025/05/15/e0e2547660ea10c176b879343cf240f0.it?sg=23f965a46d65045837a565f8a3e4d7a3&e=6825c0dc&fileName=2025-05-15ILxXJ.png.it");
//        Map<String, String> map =new HashMap<>();
//        map.put("task","1");
//        map.put("vie","3");
//        map.put("ve","2");
////        map.put("name","3");
//        map.put("folder_id_bb_n","9922980");
//        File file = new File("C:\\Users\\gmsly\\Desktop\\稿定AI_202304241218.png.it");
//        System.out.println(doPostFormDataFile("https://pc.woozooo.com/html5up.php",file,map,null));
    }


}
