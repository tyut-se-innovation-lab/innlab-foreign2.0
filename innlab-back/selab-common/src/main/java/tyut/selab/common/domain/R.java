package tyut.selab.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Result
 * @Description: 统一返回类
 * @Author: gmslymhn
 * @CreateTime: 2024-02-08 00:13
 * @Version: 1.0
 **/
@Data
public class R implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private Object data;

    public static R success() {
        return success("ok");
    }

    public static R success(String message) {
        return success(message, null);
    }

    public static R success(Object data) {
        return success("ok", data);
    }

    public static R success(String message, Object data) {
        R r = new R();
        r.setCode(ResponseCode.OK);
        r.setMessage(message);
        r.setData(data);
        return r;
    }

    public static R error(String message) {
        return error(ResponseCode.ERROR, message);
    }

    public static R error(Integer responseCode, Throwable e) {
        return error(responseCode, e.getMessage() != null ? e.getMessage() : "系统异常，请联系管理员！");
    }

    public static R error(Integer responseCode, String message) {
        R r = new R();
        r.setCode(responseCode);
        r.setMessage(message);
        return r;
    }

}