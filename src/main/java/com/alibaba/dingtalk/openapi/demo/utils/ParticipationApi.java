package com.alibaba.dingtalk.openapi.demo.utils;

import com.alibaba.dingtalk.openapi.demo.OApiException;
import org.junit.Test;

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
    public void getAllEmployeeInfo() {

    }
}
