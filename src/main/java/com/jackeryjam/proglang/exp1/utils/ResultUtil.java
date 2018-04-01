package com.jackeryjam.proglang.exp1.utils;

public class ResultUtil {
    public static Msg success(String _msg,Object object){
        Msg msg=new Msg();
        msg.setCode(200);
        msg.setMsg(_msg);
        msg.setData(object);
        return msg;
    }
    public static Msg success(Object object){
        return success("request success", object);
    }
    public static Msg success(){
        return success("request success", null);
    }

    public static Msg notFount(){
        Msg msg=new Msg();
        msg.setCode(404);
        msg.setMsg("not found");
        msg.setData(null);
        return msg;
    }

    public static Msg error(Integer code,String resultmsg){
        Msg msg=new Msg();
        msg.setCode(code);
        msg.setMsg(resultmsg);
        return msg;
    }
}
