package cc.insist.manage.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: cc
 * @Date: 2023/11/30 10:38 AM
 * @Description:
 */
@Data
public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像地址
     */
    private String userPic;
}
