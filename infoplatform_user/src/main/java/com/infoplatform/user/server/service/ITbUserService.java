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

    TbUser findByLoginName(String userName);
}
