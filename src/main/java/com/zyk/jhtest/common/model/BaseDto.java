package com.zyk.jhtest.common.model;

import com.zyk.jhtest.common.exception.BaseAppException;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class BaseDto implements Serializable{
	private static final long serialVersionUID = 1L;

    //成功
    public static final int CODE_SUCCESS = 0;

    //鉴权失败
    public static final int CODE_AUTHORITY = 100001;

    //必选参数为空或没有缺少
    public static final int CODE_PARAM = 100002;

    //参数格式错误
    public static final int CODE_PARAM_FORMAT = 100003;

    //系统错误
    public static final int CODE_ERROR = 100004;

    private Integer status = CODE_SUCCESS;

    /**
     * 返回数据
     * **/
    private Object result;
//    private String message;

    public BaseDto(){

    }
    /**
     * 异常信息BaseDTO构造器
     * @param be
     */
    public BaseDto(BaseAppException be){
        if(be.getErrorCode()==null){
            be.setErrorCode(BaseDto.CODE_SUCCESS );
        }
        ArrayList<Object> reportMessageList = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("reportMessageList",reportMessageList);
        this.setStatus(be.getErrorCode());
        this.setResult(data);
    }

    public BaseDto(Integer status){
        ArrayList<Object> reportMessageList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        result.put("reportMessageList",reportMessageList);
        this.setStatus(status);
        this.setResult(result);
    }

    public BaseDto(Integer status, Object result){
        this.setStatus(status);
        this.setResult(result);
    }

    public BaseDto(int status, String message, Map<String, Object> data) {
        this.setStatus(status);
        this.setResult(data);
    }

//    public BaseDto(Integer status,  List reportMessageList){
//        Map<String, Object> result = new HashMap<>();
//        result.put("reportMessageList",reportMessageList);
//        this.setStatus(status);
//        this.setResult(result);
//    }

//    public static BaseDto createBaseDTO(){
//        ArrayList<Object> reportMessageList = new ArrayList<>();
//        Map<String, Object> result = new HashMap<>();
//        result.put("reportMessageList",reportMessageList);
//        BaseDto baseDto = new BaseDto(CODE_SUCCESS, result);
//        return baseDto;
//    }

    public static BaseDto createBaseDTO(Object result){
        BaseDto baseDto = new BaseDto(CODE_SUCCESS,result);
        return baseDto;
    }

//    public static BaseDto createBaseDTO(List reportMessageList){
//        Map<String, Object> result = new HashMap<>();
//        result.put("reportMessageList",reportMessageList);
//        BaseDto baseDto = new BaseDto(CODE_SUCCESS, result);
//        return baseDto;
//    }

//    public BaseDto(Integer status, Map<String, Object> map){
//		this.setStatus(status);
//		this.setResult(map);
//	}


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
