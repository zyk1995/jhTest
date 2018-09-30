package com.zyk.jhtest.common.exception;


import com.alibaba.fastjson.JSON;

/**
 * checked Exception的子类，系统中所有checked类都要继承该类。
 *
 * @author liuzhaofeng
 * @version 2017/4/6
 */
public class BaseAppException extends RuntimeException {

    private static final long serialVersionUID = 8343048459443313229L;
    /**
     * 异常错误码
     */
    private Integer errorCode;

    /**
     * 获取异常错误码。
     *
     * @return 异常错误码
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * 设置异常错误码。
     *
     * @param errorCode
     * 			异常错误码
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    ///////////////////////////////////////////////
    //   构造方法
    ///////////////////////////////////////////////
    public BaseAppException() {
        super();
    }

    /**
     * @param message
     * 			发生异常错误的信息
     * @param cause
     * 			发生异常的原因
     */
    public BaseAppException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param errorCode
     * 			异常错误码
     * @param message
     * 			发生异常错误的信息
     * @param cause
     * 			发生异常的原因
     */
    public BaseAppException(Integer errorCode, String message, Throwable cause) {
        this(message, cause);
        setErrorCode(errorCode);
    }

    /**
     *
     * @param errorCode 错误状态码
     * @param message 错误信息
     * @return
     */
    public BaseAppException(Integer errorCode, String message){
        super(message);
        setErrorCode(errorCode);

    }
    public static BaseAppException createBaseAppException(Integer errorCode, String message){
    	BaseAppException baseAppException = new BaseAppException(errorCode, message);
        return baseAppException;
    }
    /**
     * @param cause
     * 			发生异常的原因
     */
    public BaseAppException(Throwable cause) {
        super(cause);
    }

    @Override
    /**
     * 返回程序异常的简短描述。
     *
     * @return 描述程序异常的字符串
     */
    public String toString() {
        Integer errorCode = getErrorCode();
        String s = (errorCode != null) ? errorCode + "--" + getClass().getName() : getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (s + ": " + message) : s;
    }

    /**
     * @author zb
     * @Time 2018-06-14
     * 处理异常信息方法
     * @param e
     * @return
     */
    public static   String getMessage(Exception e){
        String msg = JSON.parseObject(e.toString()).getJSONObject("error_response").get("msg").toString();
        return msg;
    }
}
