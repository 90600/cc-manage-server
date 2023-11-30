package cc.insist.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: cc
 * @Date: 2023/11/30 10:15 AM
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户
     */
    private String username;

}
