package com.fish.shop.untils;

import com.fish.shop.vo.ResultVo;

/**
 * Created by mingkunyu on 2019-05-19
 */
public class ResultVOUtils {
    public static ResultVo success(Object object){
        ResultVo resultVo=new ResultVo();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return resultVo;
    }
}
