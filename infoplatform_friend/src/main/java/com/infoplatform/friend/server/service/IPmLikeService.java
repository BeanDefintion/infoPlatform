package com.infoplatform.friend.server.service;

import com.infoplatform.friend.server.entity.PmLike;
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
public interface IPmLikeService extends IService<PmLike> {

    /**
     * 根据用户Id和类型与类型Id查找点赞
     *
     * @param userId         用户Id
     * @param likeType       关联类型
     * @param likeRelationId 关联Id
     **/
    PmLike selectByUserIdAndLikeId(Long userId, Integer likeType, Long likeRelationId);

    /**
     * 根据类型与id查找点赞
     *
     * @param likeType       类型
     * @param likeRelationId 关联id
     * @return List<PmLike>
     **/
    List<PmLike> selectByTypeAndId(int likeType, Long likeRelationId);
}
