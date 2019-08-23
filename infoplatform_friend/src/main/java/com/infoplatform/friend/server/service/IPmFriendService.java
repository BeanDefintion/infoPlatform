package com.infoplatform.friend.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.infoplatform.friend.server.entity.PmFriend;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jack
 * @since 2019-08-13
 */
public interface IPmFriendService extends IService<PmFriend> {

    /**
     * 根据用户Id和FriendId查找用户
     *
     * @param userId     用户Id
     * @param pmFriendId 被执行操作的用户Id
     **/
    PmFriend selectByUserIdAndFriendId(Long userId, Long pmFriendId);

    /**
     * 根据用户Id和FriendId删除用户
     *
     * @param userId     用户Id
     * @param pmFriendId 被执行操作的用户Id
     **/
    Integer deleteByUserIdAndFriendId(Long userId, Long pmFriendId);


    /**
     * 查找用户的关注列表
     * @param userId 用户id
     * @param friendPage 分页对象
     **/
    IPage<PmFriend> selectFollowListByUserId(Page<PmFriend> friendPage, Long userId);

    /**
     * 查找用户的粉丝列表
     * @param userId 用户id
     * @param objectPage 分页对象
     **/
    IPage<PmFriend> selectFansByUserId(Page<PmFriend> objectPage, Long userId);
}
