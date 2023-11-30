package cc.insist.manage.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: cc
 * @Date: 2023/11/29 2:07 PM
 * @Description:
 */
@Data
@Schema
public class LoginParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    @Schema(name = "username", description = "用户名", defaultValue = "cc", example = "cc")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @Schema(name = "password", description = "密码", defaultValue = "123456", example = "123456")
    @NotBlank(message = "密码不能为空")
    private String password;
}