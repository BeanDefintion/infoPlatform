package com.infoplatform.friend.server.service;

import com.infoplatform.friend.server.entity.PmComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jack
 * @since 2019-08-21
 */
public interface IPmCommentService extends IService<PmComment> {

    /**
     * 根据类型与关联Id查找评论列表
     *
     * @param type       类型
     * @param relationId 关联Id
     * @return List<PmComment>
     **/
    List<PmComment> selectByTypeAndId(int type, Long relationId);
}
