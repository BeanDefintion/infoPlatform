package com.infoplatform.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infoplatform.user.server.entity.*;
import com.infoplatform.user.server.mapper.TbPermitMapper;
import com.infoplatform.user.server.mapper.TbUserPermitMapper;
import com.infoplatform.user.server.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashSet;
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
public class TbPermitServiceImpl extends ServiceImpl<TbPermitMapper, TbPermit> implements ITbPermitService {

    @Resource
    private TbUserPermitMapper userPermitMapper;

    @Resource
    private TbPermitMapper permitMapper;

    @Autowired
    private ITbRoleService roleService;

    @Autowired
    private ITbRolePermitService rolePermitService;

    @Override
    public List<TbPermit> selectByUserId(Long userId) {
        LambdaQueryWrapper<TbUserPermit> wrapper = new QueryWrapper<TbUserPermit>().lambda();
        wrapper.eq(TbUserPermit::getUserId, userId);
        List<TbUserPermit> userPermits = userPermitMapper.selectList(wrapper);
        LinkedHashSet<TbPermit> permits = new LinkedHashSet<>();
        for (TbUserPermit userPermit : userPermits) {
            permits.add(permitMapper.selectById(userPermit.getPermitId()));
        }

        List<TbRole> roles = roleService.selectByUserId(userId);
        for (TbRole role : roles) {
            List<TbRolePermit> rolePermits = rolePermitService.selectByRoleId(role.getRoleId());
            for (TbRolePermit rolePermit : rolePermits) {
                permits.add(permitMapper.selectById(rolePermit.getPermitId()));
            }
        }

        return new ArrayList<>(permits);
    }
}
