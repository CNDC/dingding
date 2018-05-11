package com.alibaba.dingtalk.openapi.demo;

import com.alibaba.dingtalk.openapi.demo.utils.HttpHelper;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.SmartworkAttendsGetsimplegroupsRequest;
import com.dingtalk.api.request.SmartworkAttendsListscheduleRequest;
import com.dingtalk.api.response.SmartworkAttendsGetsimplegroupsResponse;
import com.dingtalk.api.response.SmartworkAttendsListscheduleResponse;
import com.taobao.api.ApiException;
import com.taobao.api.internal.util.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.alibaba.dingtalk.openapi.demo.auth.AuthHelper.getAccessToken;

/**
 * @author admin
 * @date 2018/5/11 14:42
 */
public class ParticipationApi {

    private static String accessToken;

    static {
        try {
            accessToken = getAccessToken();
        } catch (OApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllEmployeeInfo() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
        SmartworkAttendsListscheduleRequest req = new SmartworkAttendsListscheduleRequest();
        req.setWorkDate(StringUtils.parseDateTime("2018-05-11 11:11:11"));
        req.setOffset(0L);
        req.setSize(200L);
        SmartworkAttendsListscheduleResponse rsp = client.execute(req, accessToken);
        System.out.println(rsp.getBody());
    }

    //获取考勤组列表详情
    @Test
    public void getPaticpetionSetInfo() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
        SmartworkAttendsGetsimplegroupsRequest req = new SmartworkAttendsGetsimplegroupsRequest();
        req.setOffset(0L);
        req.setSize(10L);
        SmartworkAttendsGetsimplegroupsResponse rsp = client.execute(req, accessToken);
        System.out.println(rsp.getBody());
    }

    /* @Test
     public void getAllEmployee() throws ApiException {
         DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
         SmartworkAttendsGetsimplegroupsRequest req = new SmartworkAttendsGetsimplegroupsRequest();
         req.setOffset(0L);
         req.setSize(10L);
         SmartworkAttendsGetsimplegroupsResponse rsp = client.execute(req, accessToken);
         System.out.println(rsp.getBody());
     }*/
    @Test
    public void getParticpetionGroupsInfo() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
        SmartworkAttendsGetsimplegroupsRequest req = new SmartworkAttendsGetsimplegroupsRequest();
        req.setOffset(0L);
        req.setSize(10L);
        SmartworkAttendsGetsimplegroupsResponse rsp = client.execute(req, accessToken);
        System.out.println(rsp.getBody());
    }

    //用于返回企业内员工的实际打卡记录。比如，企业给一个员工设定的排班是上午9点和下午6点各打一次卡，但是员工在这期间打了多次，该接口会把设定的的起始的打卡记录返回。
    @Test
    public void getParticpetion() throws Exception {
        HashMap<String, Object> param = new HashMap<>();
        param.put("workDateFrom", "2018-05-11 06:08:43");
        param.put("workDateTo", "2018-05-11 16:02:43");
        List<Object> list = new ArrayList<>();
        list.add(14402300641111210L);
        param.put("userIdList", list);
        param.put("offset", 0);
        param.put("limit", 30);
        /*{
            "workDateFrom": "yyyy-MM-dd hh:mm:ss",
            "workDateTo": "yyyy-MM-dd hh:mm:ss",
            "userIdList":["员工UserId列表"],// 必填，与offset和limit配合使用，不传表示分页获取全员的数据
            "offset":0, // 必填，第一次传0，如果还有多余数据，下次传之前的offset加上limit的值
            "limit":1,  // 必填，表示数据条数，最大不能超过50条
        }*/
        JSONObject result = HttpHelper.httpPost("https://oapi.dingtalk.com/attendance/list?access_token=" + accessToken, param);
        System.out.println(result);
    }

    //用于返回企业内员工的实际打卡记录。比如，企业给一个员工设定的排班是上午9点和下午6点各打一次卡，但是员工在这期间打了多次，该接口会把所有的打卡记录都返回。
    @Test
    public void getParticpetionDetil() throws Exception {
        HashMap<String, Object> param = new HashMap<>();
        param.put("checkDateFrom", "2018-05-11 00:08:43");
        param.put("checkDateTo", "2018-05-11 23:00:00");
        List<Object> list = new ArrayList<>();
        list.add("14402300641111210");
        param.put("userIds", list.toString());
        /*{
            "userIds": ["001","002"],
            "checkDateFrom": "yyyy-MM-dd hh:mm:ss",
            "checkDateTo": "yyyy-MM-dd hh:mm:ss"
          }*/
        JSONObject result = HttpHelper.httpPost("https://oapi.dingtalk.com/attendance/listRecord?access_token=" + accessToken, param);
        System.out.println(result);
    }
}
