package cc.insist.manage.service.impl;

import cc.insist.manage.handler.BizException;
import cc.insist.manage.interceptor.LoginInterceptor;
import cc.insist.manage.mapper.UserInfoMapper;
import cc.insist.manage.model.dto.UserInfoDTO;
import cc.insist.manage.model.entity.UserInfo;
import cc.insist.manage.model.param.LoginParam;
import cc.insist.manage.model.param.RegisterParam;
import cc.insist.manage.model.vo.UserInfoVO;
import cc.insist.manage.service.UserInfoService;
import cc.insist.manage.utils.CommonUtil;
import cc.insist.manage.utils.JWTUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cc
 * @Date: 2023/11/29 11:31 AM
 * @Description:
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Boolean register(RegisterParam param) {
        UserInfo one = new LambdaQueryChainWrapper<>(userInfoMapper)
                .eq(UserInfo::getUsername, param.getUsername())
                .one();
        if (one != null) {
            throw new BizException(String.format("用户已经存在，username=%s", param.getUsername()));
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(param, userInfo);
        userInfo.setSecret("$1$" + CommonUtil.getRandomCode(8));
        String cryptPwd = Md5Crypt.md5Crypt(param.getPassword().getBytes(), userInfo.getSecret());
        userInfo.setPassword(cryptPwd);
        int insert = userInfoMapper.insert(userInfo);
        return insert == 1;
    }

    @Override
    public String login(LoginParam param) {
        UserInfo userInfo = new LambdaQueryChainWrapper<>(userInfoMapper)
                .eq(UserInfo::getUsername, param.getUsername())
                .one();
        if (userInfo == null) {
            throw new BizException(String.format("用户不存在，username=%s", param.getUsername()));
        }
        String md5Crypt = Md5Crypt.md5Crypt(param.getPassword().getBytes(), userInfo.getSecret());
        if (!md5Crypt.equalsIgnoreCase(userInfo.getPassword())) {
            throw new BizException(String.format("密码错误，username=%s", param.getUsername()));
        }
        UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                .username(param.getUsername())
                .id(userInfo.getId())
                .build();
        String accessToken = JWTUtil.geneJsonWebToken(userInfoDTO);
        return accessToken;
    }

    @Override
    public UserInfoVO userInfo() {
        UserInfoDTO userInfoDTO = LoginInterceptor.threadLocal.get();
        UserInfo userInfo = userInfoMapper.selectById(userInfoDTO.getId());
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo,userInfoVO);
        return userInfoVO;
    }
}
