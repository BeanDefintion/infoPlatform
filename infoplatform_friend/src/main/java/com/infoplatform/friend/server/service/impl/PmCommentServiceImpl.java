package com.infoplatform.friend.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.infoplatform.friend.server.entity.PmComment;
import com.infoplatform.friend.server.mapper.PmCommentMapper;
import com.infoplatform.friend.server.service.IPmCommentService;
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
 * @since 2019-08-21
 */
@Service
public class PmCommentServiceImpl extends ServiceImpl<PmCommentMapper, PmComment> implements IPmCommentService {

    @Resource
    private PmCommentMapper commentMapper;

    @Override
    public List<PmComment> selectByTypeAndId(int type, Long relationId) {
        LambdaQueryWrapper<PmComment> wrapper = new QueryWrapper<PmComment>().lambda();
        wrapper.eq(PmComment::getCommentType, type).eq(PmComment::getCommentRelationId, relationId);
        wrapper.orderByDesc(PmComment::getCrtTime);
        return commentMapper.selectList(wrapper);
    }
}
