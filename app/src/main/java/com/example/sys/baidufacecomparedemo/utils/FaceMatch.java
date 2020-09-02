package com.example.sys.baidufacecomparedemo.utils;

/**
 * Created by SYS on 2020/8/26.
 */

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 人脸对比
 */
public class FaceMatch {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    //这个你传进来了吗

    public static String match(byte[] mImg1,byte[] mImg2,String accessToken) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";

        try {

//            String imgStr = Base64.encodeToString(mImg1, 0);
            String imgStr =Base64Util.encode(mImg1);
            String imgParam =URLEncoder.encode(imgStr, "UTF-8");
            String imgStr2 = Base64Util.encode(mImg2);
            String imgParam2 = URLEncoder.encode(imgStr2, "UTF-8");


            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。

            //

            Map<String,Object> map1 = new HashMap<>();

            map1.put("image",imgParam);
            map1.put("image_type","BASE64");
            map1.put("face_type","LIVE");
            map1.put("quality_control","LOW");
            map1.put("liveness_control","HIGH");


            Map<String,Object> map2 = new HashMap<>();
            map2.put("image",imgParam2);
            map2.put("image_type","BASE64");
            map2.put("face_type","IDCARD");
            map2.put("quality_control","LOW");
            map2.put("liveness_control","HIGH");
            List<Object> list = new ArrayList<>();
            list.add(map1);
            list.add(map2);
            //再把list转json字符
            String jsonStr = GsonUtils.toJson(list);
            System.out.println("请求参数："+jsonStr+"acctoken"+accessToken);
            String result = HttpUtil.post(url, accessToken,"application/json", jsonStr);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}