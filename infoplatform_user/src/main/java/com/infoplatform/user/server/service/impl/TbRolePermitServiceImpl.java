package com.infoplatform.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infoplatform.user.server.entity.TbRolePermit;
import com.infoplatform.user.server.mapper.TbRolePermitMapper;
import com.infoplatform.user.server.service.ITbRolePermitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jack
 * @since 2019-08-15
 */
@Service
public class TbRolePermitServiceImpl extends ServiceImpl<TbRolePermitMapper, TbRolePermit> implements ITbRolePermitService {

    @Resource
    private TbRolePermitMapper rolePermitMapper;

    @Override
    public List<TbRolePermit> selectByRoleId(Integer roleId) {
        LambdaQueryWrapper<TbRolePermit> wrapper = new QueryWrapper<TbRolePermit>().lambda();
        wrapper.eq(TbRolePermit::getRoleId, roleId);
        return rolePermitMapper.selectList(wrapper);
    }
}
