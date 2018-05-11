package com.alibaba.dingtalk.openapi.demo;

import com.alibaba.dingtalk.openapi.demo.user.UserHelper;
import com.alibaba.dingtalk.openapi.demo.utils.ConvertUtil;
import com.dingtalk.open.client.api.model.corp.CorpUserDetail;
import com.dingtalk.open.client.api.model.corp.CorpUserList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.dingtalk.openapi.demo.auth.AuthHelper.getAccessToken;

/**
 * @author admin
 * @date 2018/5/11 10:23
 */
public class EmployeeApi {
    private static String accessToken;

    static {
        try {
            accessToken = getAccessToken();
        } catch (OApiException e) {
            e.printStackTrace();
        }
    }

    private static void log(Object... msgs) {
        StringBuilder sb = new StringBuilder();
        for (Object o : msgs) {
            if (o != null) {
                sb.append(o.toString());
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * 钉钉添加员工
     */
    @Test
    public void addEmployee() throws Exception {
        log("成功获取access token: ", accessToken);
        //TODO  员工的主要参数
        /*String tel; 手机号
        String workPlace;//工作区域
        String remark;//备注
        String mobile;//电话号码
        String email;.//邮件号码
        String orderInDepts;//部门权限
        Boolean isAdmin;//
        Boolean isBoss;//
        Boolean isSenior;//
        String dingId;//
        String isLeaderInDepts;//在部门中是否是管理者
        Boolean isHide;//高管模式
        List<Long> department;//员工所属部门
        String position;//职位
        String avatar;
        String jobnumber;//工号
        Map<String, String> extattr;*/
        CorpUserDetail userDetail = new CorpUserDetail();
        userDetail.setMobile("13193799274");
        userDetail.setEmail("13193799272@139.com");
        userDetail.setIsBoss(false);
        userDetail.setIsHide(false);
        userDetail.setIsBoss(false);
        userDetail.setActive(false);
        userDetail.setName("袁晓阳");
        List<Long> department = new ArrayList<>();
        department.add(65443232L);
        department.add(65452183L);
        userDetail.setDepartment(department);//设置员工的部门信息
        String user_id = UserHelper.createUser(accessToken, userDetail);//员工唯一user_id
        log("员工唯一user_id", user_id);

    }

    @Test
    public void updateEmployee() throws Exception {
        log("成功获取access token: ", accessToken);
        CorpUserDetail userDetail = new CorpUserDetail();
        userDetail.setUserid("190617365434432641");
        userDetail.setEmail("13193799272@139.com");
        userDetail.setIsBoss(false);
        userDetail.setIsHide(false);
        userDetail.setIsBoss(false);
        userDetail.setActive(true);
        userDetail.setName("hahah");
        List<Long> department = new ArrayList<>();
        department.add(65443232L);
        userDetail.setDepartment(department);//设置员工的部门信息

        log("更新员工");
        UserHelper.updateUser(accessToken, userDetail);
    }

    @Test
    public void getAllUserId() throws Exception {
        CorpUserList userList = UserHelper.getDepartmentUser(accessToken, 65443232L, null, null, null);
        if (null != userList) {
            log("部门下的员工列表", ConvertUtil.convertBeans2List(userList.getUserlist()));
        }
    }
}
