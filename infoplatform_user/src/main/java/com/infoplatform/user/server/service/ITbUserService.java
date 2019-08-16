package com.infoplatform.user.server.service;

import com.infoplatform.user.server.entity.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author jack
 * @since 2019-08-14
 */
public interface ITbUserService extends IService<TbUser> {

    /**
     * 根据用户名查找用户
     *
     * @param userName 用户名
     * @return TbUser
     **/
    TbUser findByLoginName(String userName);

    /**
     * 增加关注数
     *
     * @param userId 用户id
     * @param num    增加的数目
     * @return void
     **/
    void incFollowCount(Long userId, int num);

    /**
     * 增加粉丝数
     *
     * @param userId 用户id
     * @param num    增加的数目
     * @return void
     **/
    void incFansCount(Long userId, int num);
}
