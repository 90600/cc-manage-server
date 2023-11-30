package cc.insist.manage.service;

import cc.insist.manage.model.param.LoginParam;
import cc.insist.manage.model.param.RegisterParam;
import cc.insist.manage.model.vo.UserInfoVO;

/**
 * @Author: cc
 * @Date: 2023/11/29 11:31 AM
 * @Description:
 */
public interface UserInfoService {

    /**
     * 注册
     * @param param
     * @return
     */
    Boolean register(RegisterParam param);

    /**
     * 登录
     * @param param
     * @return
     */
    String login(LoginParam param);

    /**
     * 获取用户信息
     * @return
     */
    UserInfoVO userInfo();
}
