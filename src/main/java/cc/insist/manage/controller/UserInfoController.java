package cc.insist.manage.controller;

import cc.insist.manage.model.param.LoginParam;
import cc.insist.manage.model.param.RegisterParam;
import cc.insist.manage.model.vo.UserInfoVO;
import cc.insist.manage.service.UserInfoService;
import cc.insist.manage.utils.JsonData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cc
 * @Date: 2023/11/29 10:54 AM
 * @Description:
 */
@Tag(name = "用户")
@RestController
@RequestMapping("/api/user/v1")
@Slf4j
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Operation(summary = "注册")
    @PostMapping("/register")
    public JsonData register(@RequestBody @Validated RegisterParam param) {
        Boolean register = userInfoService.register(param);
        if (register) {
            return JsonData.buildSuccess();
        }
        return JsonData.buildError("注册失败");
    }

    @Operation(summary = "登录")
    @PostMapping("/login")
    public JsonData<String> login(@RequestBody @Validated LoginParam param) {
        String token = userInfoService.login(param);
        return JsonData.buildSuccess(token);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/userInfo")
    public JsonData<UserInfoVO> userInfo() {
        UserInfoVO userInfo = userInfoService.userInfo();
        return JsonData.buildSuccess(userInfo);
    }
}
