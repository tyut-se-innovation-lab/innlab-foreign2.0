package tyut.selab.common.utils.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequests;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.InputStreamEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.apache.hc.core5.util.Timeout;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: HttpClientUtils
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-22 04:07
 * @Version: 1.0
 **/
@Slf4j
public class HttpClientUtils {


    public static CloseableHttpClient httpClient = HttpClients.createDefault();
    static {
        log.info("设置httpclient5超时时间,tls,连接池等属性");
        // 设置超时时间
        RequestConfig config = RequestConfig.custom().setConnectTimeout(Timeout.ofMilliseconds(60000L))
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(60000L))
                .setResponseTimeout(Timeout.ofMilliseconds(60000L)).build();
        // 未设置支持ssl
//		httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
        // httpclient5经测试不设置支持ssl，也能调用https接口
        try {
            httpClient = HttpClients.custom().setDefaultRequestConfig(config)
                    .setConnectionManager(getHttpClientConnectionManager()).build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            httpClient = HttpClients.createDefault();
            e.printStackTrace();
        }

    }

    private static HttpClientConnectionManager getHttpClientConnectionManager()
            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        // 设置连接池最大连接数1000,最大并发数200，及支持ssl,tls
        return PoolingHttpClientConnectionManagerBuilder.create().setMaxConnTotal(1000).setMaxConnPerRoute(200)
                .setSSLSocketFactory(getSslConnectionSocketFactory()).build();
    }

    /**
     * 支持SSL
     *
     * @return SSLConnectionSocketFactory
     */
    private static SSLConnectionSocketFactory getSslConnectionSocketFactory()
            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (x509Certificates, s) -> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        return new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
        //链接tls版本协议，不指定也可以
//		return new SSLConnectionSocketFactory(sslContext,  new String[] {"TLSv1.1","TLSv1.2","TLSv1.3"},
//				  null, new NoopHostnameVerifier());

    }

    /**
     * get方式请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        return get(url, null, null);
    }

    /**
     *
     * @param url
     * @param params
     * @return
     */
    public static String get(String url, Map<String, Object> headers, Map<String, Object> params) {
        log.info("httpclient5 get start url="+url+"headers="+headers+",params="+params);

        String resultContent = null;
        HttpGet httpGet = new HttpGet(url);

        // 设置header
        if (headers != null) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                httpGet.addHeader(entry.getKey(), entry.getValue());
            }
        }
        if (params != null && params.size() > 0) {
            // 表单参数
            List<NameValuePair> nvps = new ArrayList<>();
            // GET 请求参数，如果中文出现乱码需要加上URLEncoder.encode
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            // 增加到请求 URL 中
            try {
                URI uri = new URIBuilder(new URI(url)).addParameters(nvps).build();
                httpGet.setUri(uri);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

        }

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            // 获取状态码
            // 检查响应状态码
            int statusCode = response.getCode();
            System.out.println(response.getVersion()); // HTTP/1.1
            System.out.println(response.getCode()); // 200
            System.out.println(response.getReasonPhrase()); // OK
            HttpEntity entity = response.getEntity();
            if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
                // 获取重定向网址
                String redirectUrl = response.getFirstHeader("Location").getValue();
                log.info("Redirected to URL: " + redirectUrl);
                return redirectUrl; // 返回新的网址
            }
            // 获取响应信息
            resultContent = EntityUtils.toString(entity,"UTF-8");
            log.info("httpclient5 get end url="+url+"headers="+headers+",params="+params+",result="+resultContent);
            // 确保流被完全消费
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Exception httpclient5 get url="+url+"headers="+headers+",params="+params+",exception="+e.getStackTrace());

        }

        return resultContent;
    }
    public static String get2(String url, Map<String, Object> headers) {
        log.info("httpclient5 get start url="+url+" headers="+headers);

        String resultContent = null;
        HttpGet httpGet = new HttpGet(url);

        // 设置 header
        if (headers != null) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                httpGet.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }


        // Create HttpClient with redirection disabled
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .disableRedirectHandling() // 禁用重定向处理
                .build();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {

            // 检查响应状态码
            int statusCode = response.getCode();
            System.out.println(response.getVersion()); // HTTP/1.1
            System.out.println(statusCode); // 状态码
            System.out.println(response.getReasonPhrase()); // 状态描述

            if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
                // 获取重定向网址
                String redirectUrl = response.getFirstHeader("Location").getValue();
                log.info("Redirected to URL: " + redirectUrl);
                return redirectUrl; // 返回新的网址
            }

            HttpEntity entity = response.getEntity();
            resultContent = EntityUtils.toString(entity, "UTF-8");
            log.info("httpclient5 get end url=" + url + " headers=" + headers + ", result=" + resultContent);
            EntityUtils.consume(entity);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            log.info("Exception httpclient5 get url=" + url + " headers=" + headers +  ", exception=" + e.getMessage());
        }

        return resultContent;
    }

    /**
     * post form请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String postForm(String url, Map<String, String> headers, Map<String, Object> params) {
        log.info("httpclient5 postForm start url="+url+"headers="+headers+",params="+params);

        String result = null;
        HttpPost httpPost = new HttpPost(url);
        // 设置header
        if (headers != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                httpPost.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        httpPost.addHeader("Content-Type","application/form-data;charset=UTF-8");
        // 表单参数
        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }
//注意编码为UTF-8,否则中文会出现乱码
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("UTF-8")));

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            System.out.println(response.getVersion()); // HTTP/1.1
            System.out.println(response.getCode()); // 200
            System.out.println(response.getReasonPhrase()); // OK

            HttpEntity entity = response.getEntity();
            // 获取响应信息
            result = EntityUtils.toString(entity,"UTF-8");
            log.info("httpclient5 postForm end url="+url+"headers="+headers+",params="+params+",result="+result);

            // 确保流被完全消费
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Exception httpclient5 postForm url="+url+"headers="+headers+",params="+params+",exception="+e.getStackTrace());

        }

        return result;
    }

    /**
     * post form请求,返回Response，有些请求需要拿到header
     *
     * @param url
     * @param params
     * @return
     */
    public static CloseableHttpResponse postFormReturnResponse(String url, Map<String, Object> headers,
                                                               Map<String, Object> params) {
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");

        // 设置header
        if (headers != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        // 表单参数
        List<NameValuePair> nvps = new ArrayList<>();
        // POST 请求参数
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps,Charset.forName("UTF-8")));

        try {
            response = httpClient.execute(httpPost);
//			Header[] header=response.getHeaders();
            System.out.println(response.getVersion()); // HTTP/1.1
            System.out.println(response.getCode()); // 200
            System.out.println(response.getReasonPhrase()); // OK
//
//			HttpEntity entity = response.getEntity();
//			// 获取响应信息
//			result = EntityUtils.toString(entity);
//			// 确保流被完全消费
//			EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * post json请求
     *
     * @param url
     * @param jsonBody
     * @return
     */
    public static String postJson(String url, String jsonBody) {
        log.info("httpclient5 postJson start url="+url+",jsonBody="+jsonBody);

        String result = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type","application/json;charset=UTF-8");
        httpPost.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            // 获取响应信息
            result = EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            log.info("Exception postJson postForm url="+url+",jsonBody="+jsonBody+",exception="+e.getStackTrace());

        }
        log.info("httpclient5 postJson end url="+url+",result="+result);

        return result;
    }

    /**
     * post stream请求(file,json,xml转stream)
     *
     * @param url
//     * @param jsonBody
     * @return
     */
    public static String postStream(String url, String params, ContentType contentType) {
        String result = null;
        final HttpPost httppost = new HttpPost(url);
        InputStream inputStream = new ByteArrayInputStream(new String(params).getBytes());
        final InputStreamEntity reqEntity = new InputStreamEntity(inputStream, -1, contentType);
        // 也可以使用 FileEntity 的形式
        // FileEntity reqEntity = new FileEntity(new File(params),
        // ContentType.APPLICATION_JSON);

        httppost.setEntity(reqEntity);
        try (final CloseableHttpResponse response = httpClient.execute(httppost)) {
            System.out.println("----------------------------------------");
            System.out.println(response.getCode() + " " + response.getReasonPhrase());
            result = EntityUtils.toString(response.getEntity(),"UTF-8");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * getAsync异步请求，回调获取结果
     *
     * @param url
     * @return
     */
    public static String getAsync(String url) {
        try (CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault()) {
            // 开始 http clinet
            httpclient.start();
            // 根据请求响应情况进行回调操作
//            CountDownLatch latch = new CountDownLatch(1);
            SimpleHttpRequest request = SimpleHttpRequests.get(url);
            httpclient.execute(request, new FutureCallback<SimpleHttpResponse>() {
                @Override
                public void completed(SimpleHttpResponse response2) {
//                    latch.countDown();
                    System.out.println("getAsync:" + request.getRequestUri() + "->" + response2.getCode());
                }

                @Override
                public void failed(Exception ex) {
//                    latch.countDown();
                    System.out.println("getAsync:" + request.getRequestUri() + "->" + ex);
                }

                @Override
                public void cancelled() {
//                    latch.countDown();
                    System.out.println("getAsync:" + request.getRequestUri() + " cancelled");
                }

            });
//            latch.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void main(String[] args) {
//        System.out.println(get("http://tool.xwm.lol/api.php?msg=321"));
//        File file = new File("C:/Users/gmsly/Pictures/图片1.png");
//        Map<String,Object> map = new HashMap<>();
//        map.put("image",file);
//        Map<String,String> map1 = new HashMap<>();
//        map1.put("token","e883d14b6f64303e99e1e8d97a836302");
//        String response = postForm(newPayUrl,map1,map);
//        map.put("email","gmslymhn@163.com");
//        map.put("password","q482686291");
//        map.put("refresh",1);
//        JSONObject date = new JSONObject(map);
//        String response=postJson(newPayUrl,date.toJSONString());
//        System.out.println(response);
//
//        JSONObject date1 = JSONObject.parseObject(response);
//        JSONObject date2 = date1.getJSONObject("data");
//        String token = date2.getString("token");
//        System.out.println(token);
        String url = "https://down-load.lanrar.com/file/?AmRUagEwUmMACQoyUWRTPwM8BT1eM1ZkVWBWZFwtATAGP1QuD2NVMAAVBRxXSAVGW24PJFZ5Uz8AYAAoBzIEcQIkVGUBNVJqADMKA1FsUzYDZAUxXjhWbVVnVmVcMgE5BjNUMQ90VWcAJQVuVzoFNFszDz1WMVNoACEAdgcrBDgCMFQzAW5SMgB5CmxRPFN9A2gFMV4uVjFVN1Y2XDABZQY1VGUPNFUzAGYFZFc1BWZbZw9vVjtTZQA0ADAHbgQyAmFUYAFpUmIAbwo6UTVTagNsBTVeZ1Z6VTtWJFwmAXUGdVRmDyBVaAAyBW5XMAUwWzEPOlY5U2IANgAgBy8EbAJvVGYBOlI7AGcKa1E3U2QDaAUyXjdWYVViVmFcLgEuBiBUZQ8+VXYAawViVzQFNls3DztWO1NlAD4AMAdsBCMCd1RzAStSOwBnCmtRN1NkA2kFNV4wVmRVZVZgXCYBdQZvVHMPb1U1AG8FfVcxBTdbMw8kVjtTYAAwACgHaQQxAjpULQF6UmIAOQorUWtTDQM6BWtePFZk";
        Map<String, Object> headers = new HashMap<>();
            headers.put("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        headers.put("accept-language","zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        headers.put("sec-ch-ua","\"Chromium\";v=\"122\", \"Not(A:Brand\";v=\"24\", \"Microsoft Edge\";v=\"122\"");
        headers.put("sec-ch-ua-mobile","?0");
        headers.put("sec-ch-ua-platform","\"Windows\"");
        headers.put("sec-fetch-dest", "document");
        headers.put("sec-fetch-mode","navigate");
        headers.put("sec-fetch-site","none");
        headers.put("sec-fetch-user","?1");
        headers.put("upgrade-insecure-requests","1");
        headers.put("cookie","down_ip=1");

        get2(url,headers);
    }
}
