package cc.insist.manage.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: cc
 * @Date: 2023/11/29 10:39 AM
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_a_user")
public class UserInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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

    /**
     * 盐，用于个人敏感信息处理
     */
    private String secret;
}
