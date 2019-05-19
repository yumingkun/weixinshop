package com.fish.shop.vo;

import lombok.Data;

/**
 * Created by mingkunyu on 2019-05-19
 */
//http请求返回的最外层对象
@Data
public class ResultVo<T>{

    /***
     *错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;


    /**
     * 具体内容
     */
    private T data;
}
