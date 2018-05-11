package com.alibaba.dingtalk.openapi.demo;

import com.alibaba.dingtalk.openapi.demo.auth.AuthHelper;
import com.alibaba.dingtalk.openapi.demo.department.DepartmentHelper;
import com.alibaba.dingtalk.openapi.demo.utils.ConvertUtil;
import com.dingtalk.open.client.api.model.corp.Department;
import org.junit.Test;

import java.util.List;

/**
 * @author admin
 * @date 2018/5/11 10:22
 */
public class DepartmentApi {
    private static void log(Object... msgs) {
        StringBuilder sb = new StringBuilder();
        for (Object o : msgs) {
            if (o != null) {
                sb.append(o.toString());
            }
        }
        System.out.println(sb.toString());
    }

    @Test
    public void addDepartment() throws Exception {
        // 获取access token
        String accessToken = AuthHelper.getAccessToken();
        log("成功获取access token: ", accessToken);

        // 获取jsapi ticket
        String ticket = AuthHelper.getJsapiTicket(accessToken);
        log("成功获取jsapi ticket: ", ticket);

        // 获取签名
        String nonceStr = "nonceStr";
        long timeStamp = System.currentTimeMillis();
        String url = "http://www.dingtalk.com";
        String signature = AuthHelper.sign(ticket, nonceStr, timeStamp, url);
        log("成功签名: ", signature);

        // 创建部门
        String name = "上海研发部";
        String parentId = "1";
        String order = "1";
        boolean createDeptGroup = true;
        long departmentId = Long.parseLong(DepartmentHelper.createDepartment(accessToken, name, parentId, order, createDeptGroup));
        log("成功创建部门", name, " 部门id=", departmentId);


        // 获取部门列表
        List<Department> list = DepartmentHelper.listDepartments(accessToken, parentId);
        log("成功获取部门列表", ConvertUtil.convertBeans2List(list));

       /* // 更新部门
        name = "hahahaha";
        boolean autoAddUser = true;
        String deptManagerUseridList = null;
        boolean deptHiding = false;
        String deptPerimits = "aa|qq";
        DepartmentHelper.updateDepartment(accessToken, departmentId, name, parentId, order, createDeptGroup,
                autoAddUser, deptManagerUseridList, deptHiding, deptPerimits, null,
                null, null, null, null);


        log("成功更新部门", " 部门id=", departmentId);*/
    }
}
