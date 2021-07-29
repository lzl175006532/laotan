package com.laotan.net.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.laotan.net.common.JsonResult;
import com.laotan.net.common.ResultStatusCode;
import com.laotan.net.service.SystemParamService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Copyright 通泰信诚
 * @Author lizilong
 * @Description
 * @Date 13:57$ 2021/7/29$
 **/
@Component
public class SendMsgUtil {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    SystemParamService systemParamService;
    /**
     * @Copyright: 通泰信诚
     * @Author: lizilong
     * @Since: 2021/7/29 14:55
     * @Params: [cellPhone, msgContent]
     * @Return: com.laotan.net.common.JsonResult
     * @Description: 发送手机验证码
     */
    public JsonResult sendVerificationCode(String cellPhone) {
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
            String secretId = systemParamService.selectByParamKey("SECRET_ID","");
            String secretKey = systemParamService.selectByParamKey("SECRET_KEY","");

            Credential cred = new Credential(secretId,secretKey);
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            SmsClient client = new SmsClient(cred, "ap-beijing", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            SendSmsRequest req = new SendSmsRequest();
            //发送手机号
            String[] phoneNumberSet1 = {"+86"+cellPhone};
            req.setPhoneNumberSet(phoneNumberSet1);
            String smsSdkAppId = systemParamService.selectByParamKey("SMS_SDK_APP_ID","");
            req.setSmsSdkAppId(smsSdkAppId);
            String signName = systemParamService.selectByParamKey("SIGN_NAME","");
            req.setSignName(signName);
            String templateId = systemParamService.selectByParamKey("TEMPLATE_ID","");
            req.setTemplateId(templateId);
            //短信内容
            String msgContent = (int)((Math.random()*9+1)*100000) +"";
            String[] templateParamSet1 = {msgContent};
            req.setTemplateParamSet(templateParamSet1);
            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
            SendSmsResponse resp = client.SendSms(req);
            // 输出json格式的字符串回包
            /***
             * {
             *   "Response": {
             *     "SendStatusSet": [
             *       {
             *         "SerialNo": "2645:107939710816275387404341786",
             *         "PhoneNumber": "+8615201517869",
             *         "Fee": 1,
             *         "SessionContext": "",
             *         "Code": "Ok",
             *         "Message": "send success",
             *         "IsoCode": "CN"
             *       }
             *     ],
             *     "RequestId": "b4f97131-c9f3-4922-8ff6-89345e4092af"
             *   }
             * }
             */
            System.out.println(SendSmsResponse.toJsonString(resp));
            return this.getResult(SendSmsResponse.toJsonString(resp),cellPhone,msgContent);
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            return new JsonResult(ResultStatusCode.FAIL_OPERATION.getCode(),"发送短信异常："+e.getMessage());
        }
    }

    public JsonResult getResult(String result,String cellPhone,String msgContent) {
//        String result = "{\n" +
//                "  \"Response\": {\n" +
//                "    \"SendStatusSet\": [\n" +
//                "      {\n" +
//                "        \"SerialNo\": \"2645:107939710816275387404341786\",\n" +
//                "        \"PhoneNumber\": \"+8615201517869\",\n" +
//                "        \"Fee\": 1,\n" +
//                "        \"SessionContext\": \"\",\n" +
//                "        \"Code\": \"Ok\",\n" +
//                "        \"Message\": \"send success\",\n" +
//                "        \"IsoCode\": \"CN\"\n" +
//                "      }\n" +
//                "    ],\n" +
//                "    \"RequestId\": \"b4f97131-c9f3-4922-8ff6-89345e4092af\"\n" +
//                "  }\n" +
//                "}";
        if(StringUtils.isEmpty(result)){
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"发送短信返回为空");
        }
        JSONObject object = JSONObject.parseObject(result);
        if(object != null){
            JSONArray sendStatusSet = object.getJSONArray("SendStatusSet");
            if(sendStatusSet != null){
                JSONObject jsonObject = sendStatusSet.getJSONObject(0);
                if(jsonObject != null){
                    String code = jsonObject.getString("Code");
                    if("Ok".equals(code)){
                        //加入到redis缓存，设置失效时间2分钟
                        redisUtils.setCacheObject(cellPhone,msgContent,120L);
                        return new JsonResult(ResultStatusCode.SUCCESS.getCode(),"发送短信成功");
                    }else{
                        return new JsonResult(ResultStatusCode.FAIL_OPERATION.getCode(),"发送短信失败："+jsonObject.getString("Message"));
                    }
                }else{
                    return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"发送短信返回结果SendStatusSet数组第一个对象为空");
                }
            }else{
                return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"发送短信返回结果SendStatusSet参数为空");
            }
        }else{
            return new JsonResult(ResultStatusCode.NOT_NULL.getCode(),"发送短信返回结果Response参数为空");
        }

    }
}
