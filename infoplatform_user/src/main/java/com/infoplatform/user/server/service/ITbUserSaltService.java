package com.infoplatform.user.server.service;

import com.infoplatform.user.server.entity.TbUserSalt;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jack
 * @since 2019-08-14
 */
public interface ITbUserSaltService extends IService<TbUserSalt> {

    TbUserSalt selectByUserId(Long userId);
}
