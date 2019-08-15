package com.infoplatform.user.server.service;

import com.infoplatform.user.server.entity.TbPermit;
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
public interface ITbPermitService extends IService<TbPermit> {

    /**
     * 根据用户id查找用户权限
     *
     * @param userId 用户Id
     * @return List
     **/
    List<TbPermit> selectByUserId(Long userId);
}
