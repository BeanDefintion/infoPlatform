package com.infoplatform.friend.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infoplatform.friend.server.entity.PmFriend;
import com.infoplatform.friend.server.entity.PmLike;
import com.infoplatform.friend.server.mapper.PmLikeMapper;
import com.infoplatform.friend.server.service.IPmLikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jack
 * @since 2019-08-21
 */
@Service
public class PmLikeServiceImpl extends ServiceImpl<PmLikeMapper, PmLike> implements IPmLikeService {

    @Resource
    private PmLikeMapper pmLikeMapper;

    @Override
    public PmLike selectByUserIdAndLikeId(Long userId, Integer likeType, Long likeRelationId) {
        LambdaQueryWrapper<PmLike> wrapper = new QueryWrapper<PmLike>().lambda();
        wrapper.eq(PmLike::getLikeType, likeType).eq(PmLike::getLikeRelationId, likeRelationId).eq(PmLike::getLikeUserId, userId);
        return pmLikeMapper.selectOne(wrapper);
    }

    @Override
    public List<PmLike> selectByTypeAndId(int likeType, Long likeRelationId) {
        LambdaQueryWrapper<PmLike> wrapper = new QueryWrapper<PmLike>().lambda();
        wrapper.eq(PmLike::getLikeType, likeType).eq(PmLike::getLikeRelationId, likeRelationId);
        wrapper.orderByDesc(PmLike::getCrtTime);
        return pmLikeMapper.selectList(wrapper);
    }
}
