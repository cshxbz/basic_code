package com.hxb.basic_framework.baselib.http.parser;


import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * 数据格式：
 *  {"status":1|0,"message":"xxxx","data":{}}
 *  status==1:成功
 *  status==0:失败
 *  data ：实际的数据体
 */
public class CommonRespJsonRawParser extends RespRawParser {

    @Override
    public void performParse() throws JSONException{
        JSONObject jo = new JSONObject(rootData);
        int status = jo.getInt("status");
        if(status==1){
            success=true;
            this.data=jo.getString("data");
        }else{
            success=false;
            this.failMsg = jo.getString("message");
        }
    }
}
