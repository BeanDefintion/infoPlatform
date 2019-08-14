package com.infoplatform.friend.server.service.impl;

import com.infoplatform.friend.server.entity.PmFriend;
import com.infoplatform.friend.server.mapper.PmFriendMapper;
import com.infoplatform.friend.server.service.IPmFriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jack
 * @since 2019-08-13
 */
@Service
public class PmFriendServiceImpl extends ServiceImpl<PmFriendMapper, PmFriend> implements IPmFriendService {

    @Override
    public PmFriend selectByUserIdAndFriendId(Long userId, Long pmFriendId) {
        return null;
    }
}
