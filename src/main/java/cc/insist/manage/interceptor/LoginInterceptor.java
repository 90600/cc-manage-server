package cc.insist.manage.interceptor;

import cc.insist.manage.enums.BizCodeEnum;
import cc.insist.manage.model.dto.UserInfoDTO;
import cc.insist.manage.utils.CommonUtil;
import cc.insist.manage.utils.JWTUtil;
import cc.insist.manage.utils.JsonData;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: cc
 * @Date: 2023/11/29 2:51 PM
 * @Description:
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    public static ThreadLocal<UserInfoDTO> threadLocal = new ThreadLocal<>();

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("token");
        if (accessToken == null) {
            accessToken = request.getParameter("token");
        }
        if (StringUtils.isNotBlank(accessToken)) {
            //不为空
            Claims claims = JWTUtil.checkJWT(accessToken);
            if (claims == null) {
                //未登录
                CommonUtil.sendJsonMessage(response, JsonData.buildResult(BizCodeEnum.ACCOUNT_UNLOGIN));
                return false;
            }

            long userId = Long.parseLong(claims.get("id").toString());
            String username = (String) claims.get("username");
            UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                    .username(username)
                    .id(userId)
                    .build();

            //通过attribute传递用户信息
            //request.setAttribute("loginUser",loginUser);

            //通过threadLocal传递用户登录信息
            threadLocal.set(userInfoDTO);
            return true;
        }
        CommonUtil.sendJsonMessage(response, JsonData.buildResult(BizCodeEnum.ACCOUNT_UNLOGIN));
        return false;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        threadLocal.remove();
    }
}

