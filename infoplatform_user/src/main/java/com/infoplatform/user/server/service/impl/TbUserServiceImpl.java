package com.infoplatform.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infoplatform.user.server.entity.TbUser;
import com.infoplatform.user.server.mapper.TbUserMapper;
import com.infoplatform.user.server.service.ITbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author jack
 * @since 2019-08-14
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

    @Resource
    private TbUserMapper userMapper;

    @Override
    public TbUser findByLoginName(String userName) {
        LambdaQueryWrapper<TbUser> wrapper = new QueryWrapper<TbUser>().lambda();
        wrapper.eq(TbUser::getLoginName, userName);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public void incFollowCount(Long userId, int num) {
        TbUser user = userMapper.selectById(userId);
        user.setFollowCount(user.getFollowCount() + num);
        userMapper.updateById(user);
    }

    @Override
    public void incFansCount(Long userId, int num) {
        TbUser user = userMapper.selectById(userId);
        user.setFansCount(user.getFansCount() + num);
        userMapper.updateById(user);
    }
}
