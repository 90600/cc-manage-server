package cc.insist.manage.utils;

import cc.insist.manage.enums.BizCodeEnum;
import cc.insist.manage.enums.ErrorCodeEnum;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: cc
 * @Date: 2023/8/7 5:40 PM
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonData<E> {

    /**
     * 状态码 0 表示成功
     */
    private Integer code;

    /**
     * 数据
     */
    private E data;

    /**
     * 描述
     */
    private String msg;

    /**
     * 获取远程调用数据
     * 注意事项：
     * 支持多单词下划线专驼峰（序列化和反序列化）
     *
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T> T getData(TypeReference<T> typeReference) {
        return JSON.parseObject(JSON.toJSONString(data), typeReference);
    }

    /**
     * 成功，不传入数据
     *
     * @return
     */
    public static JsonData buildSuccess() {
        return new JsonData(200, null, null);
    }

    /**
     * 成功，传入数据
     *
     * @param data
     * @return
     */
    public static <E> JsonData<E> buildSuccess(E data) {
        return new JsonData<E>(200, data, null);
    }

    /**
     * 失败，传入描述信息
     *
     * @param msg
     * @return
     */
    public static JsonData buildError(String msg) {
        return new JsonData(-1, null, msg);
    }

    /**
     * 失败，传入描述消息
     *
     * @param errorCodeEnum
     * @param args
     * @return
     */
    public static JsonData buildEnumError(ErrorCodeEnum errorCodeEnum, Object... args) {
        String error = errorCodeEnum.getMessage();
        String message;
        if (args == null || args.length == 0) {
            message = error;
        } else {
            message = String.format(error, args);
        }
        return buildError(message);
    }

    /**
     * 失败，传入描述信息
     *
     * @param message
     * @param args
     * @return
     */
    public static JsonData buildMsgError(String message, Object... args) {
        if (null == args || args.length == 0) {
            return buildError(message);
        } else {
            return buildError(String.format(message, args));
        }
    }


    /**
     * 自定义状态码和错误信息
     *
     * @param code
     * @param msg
     * @return
     */
    public static JsonData buildCodeAndMsg(int code, String msg) {
        return new JsonData(code, null, msg);
    }

    /**
     * 传入枚举，返回信息
     *
     * @param codeEnum
     * @return
     */
    public static JsonData buildResult(BizCodeEnum codeEnum) {
        return JsonData.buildCodeAndMsg(codeEnum.getCode(), codeEnum.getMessage());
    }
}
