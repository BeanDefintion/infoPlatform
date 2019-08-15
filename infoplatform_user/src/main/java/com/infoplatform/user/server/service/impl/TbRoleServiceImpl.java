package com.infoplatform.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infoplatform.user.server.entity.TbRole;
import com.infoplatform.user.server.entity.TbUserRole;
import com.infoplatform.user.server.mapper.TbRoleMapper;
import com.infoplatform.user.server.mapper.TbUserRoleMapper;
import com.infoplatform.user.server.service.ITbRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class TbRoleServiceImpl extends ServiceImpl<TbRoleMapper, TbRole> implements ITbRoleService {

    @Resource
    private TbRoleMapper roleMapper;

    @Resource
    private TbUserRoleMapper userRoleMapper;

    @Override
    public List<TbRole> selectByUserId(Long userId) {
        LambdaQueryWrapper<TbUserRole> wrapper = new QueryWrapper<TbUserRole>().lambda();
        wrapper.eq(TbUserRole::getUserId, userId);
        List<TbUserRole> userRoles = userRoleMapper.selectList(wrapper);
        List<TbRole> roles = new ArrayList<>();
        for (TbUserRole userRole : userRoles) {
            roles.add(roleMapper.selectById(userRole.getRoleId()));
        }
        return roles;
    }
}
