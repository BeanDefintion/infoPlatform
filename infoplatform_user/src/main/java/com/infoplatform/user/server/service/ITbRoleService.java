package com.infoplatform.user.server.service;

import com.infoplatform.user.server.entity.TbRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jack
 * @since 2019-08-15
 */
public interface ITbRoleService extends IService<TbRole> {

    /**
     * 根据userId获得该玩家拥有的权限
     *
     * @param userId 用户Id
     * @return List<TbRole>
     **/
    List<TbRole> selectByUserId(Long userId);
}
