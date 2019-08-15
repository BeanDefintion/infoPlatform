package com.infoplatform.user.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infoplatform.user.server.entity.TbUserSalt;
import com.infoplatform.user.server.mapper.TbUserSaltMapper;
import com.infoplatform.user.server.service.ITbUserSaltService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jack
 * @since 2019-08-14
 */
@Service
public class TbUserSaltServiceImpl extends ServiceImpl<TbUserSaltMapper, TbUserSalt> implements ITbUserSaltService {

    @Resource
    private TbUserSaltMapper userSaltMapper;

    @Override
    public TbUserSalt selectByUserId(Long userId) {
        LambdaQueryWrapper<TbUserSalt> wrapper = new QueryWrapper<TbUserSalt>().lambda();
        wrapper.eq(TbUserSalt::getUserId, userId);
        return userSaltMapper.selectOne(wrapper);
    }
}
