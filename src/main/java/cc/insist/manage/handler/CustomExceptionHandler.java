package cc.insist.manage.handler;

import cc.insist.manage.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: cc
 * @Date: 2022/7/21 7:47 PM
 * @Description:
 * @ControllerAdvice是@Controller注解的一个增强，这个注解是Spring里面的东西，可以处理全局异常
 * @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 */
@ControllerAdvice
//@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    /**
     * 接口body参数验证异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public JsonData exceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return JsonData.buildError(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handler(Exception e) {

        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            log.error("[业务异常]{}", e);
            return JsonData.buildCodeAndMsg(bizException.getCode(), bizException.getMsg());
        } else {
            log.error("[系统异常]{}", e);
            return JsonData.buildError(String.format("[系统异常] msg= %s", e.getMessage()));
        }
    }

}
