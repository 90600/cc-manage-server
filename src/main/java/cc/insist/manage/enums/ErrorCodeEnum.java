package cc.insist.manage.enums;

import lombok.Getter;

/**
 * @Author: cc
 * @Date: 2023/8/7 5:41 PM
 * @Description:
 */
@Getter
public enum ErrorCodeEnum {
    /**
     * spring的Validation参数校验错误提示
     */
    VALIDATION_EXCEPTION("参数校验错误， %s"),

    /**
     * 参数错误
     */
    PARAM_DATA_WRONG_ERROR("参数中的入参错误，%s"),
    ;

    private String message;

    ErrorCodeEnum(String message) {
        this.message = message;
    }

}
