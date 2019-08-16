package com.infoplatform.friend.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infoplatform.friend.server.entity.PmFriend;
import com.infoplatform.friend.server.mapper.PmFriendMapper;
import com.infoplatform.friend.server.service.IPmFriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jack
 * @since 2019-08-13
 */
@Service
public class PmFriendServiceImpl extends ServiceImpl<PmFriendMapper, PmFriend> implements IPmFriendService {

    @Resource
    private PmFriendMapper friendMapper;

    @Override
    public PmFriend selectByUserIdAndFriendId(Long userId, Long pmFriendId) {
        LambdaQueryWrapper<PmFriend> wrapper = new QueryWrapper<PmFriend>().lambda();
        wrapper.eq(PmFriend::getPmFriendId, pmFriendId).eq(PmFriend::getPmUserId, userId);
        return friendMapper.selectOne(wrapper);
    }
}
