package cc.insist.manage.handler;

import cc.insist.manage.enums.BizCodeEnum;
import lombok.Data;

/**
 * @Author: cc
 * @Date: 2022/7/21 7:46 PM
 * @Description:
 */
@Data
public class BizException extends RuntimeException {

    private int code;

    private String msg;

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public BizException(String message) {
        super(message);
        this.code = 500;
        this.msg = message;
    }


    public BizException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();
    }





}
