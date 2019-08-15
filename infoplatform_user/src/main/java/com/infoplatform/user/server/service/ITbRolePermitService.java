package com.infoplatform.user.server.service;

import com.infoplatform.user.server.entity.TbRolePermit;
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
public interface ITbRolePermitService extends IService<TbRolePermit> {

    /**
     * 根据角色id查找权限
     *
     * @param roleId 角色id
     * @return List
     **/
    List<TbRolePermit> selectByRoleId(Integer roleId);
}
