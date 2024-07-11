package tyut.selab.common.domain;

/**
 * @ClassName: ResponseCode
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-03-27 12:20
 * @Version: 1.0
 **/
public interface ResponseCode {
    /**
     * 请求成功。一般用于GET与POST请求
     */
    Integer OK = 200;
    /**
     * 请求失败。一般用于GET与POST请求
     */
    Integer ERROR = 500;
    /**
     *  已创建。成功请求并创建了新的资源
     */
    Integer Created = 201;
    /**
     * 已接受。已经接受请求，但未处理完成
     */
    Integer Accepted = 202;

    /**
     * 非授权信息。请求成功。但返回的meta信息不在原始的服务器，而是一个副本
     */
    Integer  Non_Authoritative_Information = 203;
    /**
     * 无内容。服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档
     */
    Integer  No_Content = 204;
    /**
     * 重置内容。服务器处理成功，用户终端（例如：浏览器）应重置文档视图。可通过此返回码清除浏览器的表单域
     */
    Integer  Reset_Content = 205;
    /**
     * 部分内容。服务器成功处理了部分GET请求
     */
    Integer  Partial_Content = 206;
    /**
     * 永久移动。请求的资源已被永久的移动到新URI，返回信息会包括新的URI，浏览器会自动定向到新URI。今后任何新的请求都应使用新的URI代替
     */
    Integer  Moved_Permanently = 301;
    /**
     * 临时移动。与301类似。但资源只是临时被移动。客户端应继续使用原有URI
     */
    Integer  Found = 302;
    /**
     * 	客户端请求的语法错误，服务器无法理解
     */
    Integer  Bad_Request = 400;
    /**
     * 请求要求用户的身份认证
     */
    Integer  Unauthorized = 401;
    /**
     *	保留，将来使用
     */
    Integer  Payment_Required = 402;
    /**
     *	服务器理解请求客户端的请求，但是拒绝执行此请求
     */
    Integer Forbidden = 403;
    /**
     * 服务器无法根据客户端的请求找到资源（网页）。通过此代码，网站设计人员可设置"您所请求的资源无法找到"的个性页面
     */
    Integer Not_Found = 404;
    /**
     * 服务器内部错误，无法完成请求
     */
    Integer Internal_Server_Error = 500;
}
