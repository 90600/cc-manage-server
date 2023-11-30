package cc.insist.manage.handler;

import cc.insist.manage.constants.CommonConstant;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: cc
 * @Date: 2023/7/26 5:23 PM
 * @Description:
 */
@Slf4j
@Component
public class MyBatisExtensionHandler implements MetaObjectHandler {

    /**
     * 新增时，自动写入createTime、modifyTime、isDel
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, CommonConstant.ATTR_IS_DEL, () -> 0, Integer.class);
        this.strictInsertFill(metaObject, CommonConstant.ATTR_CREATE_TIME, LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, CommonConstant.ATTR_MODIFY_TIME, LocalDateTime::now, LocalDateTime.class);
    }

    /**
     * 修改时，自动写入modifyTime
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, CommonConstant.ATTR_MODIFY_TIME, LocalDateTime::now, LocalDateTime.class);
    }
}
