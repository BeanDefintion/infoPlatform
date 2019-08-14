package com.infoplatform.friend.server.service;

import com.infoplatform.friend.server.entity.PmFriend;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jack
 * @since 2019-08-13
 */
public interface IPmFriendService extends IService<PmFriend> {

    PmFriend selectByUserIdAndFriendId(Long userId, Long pmFriendId);
}
