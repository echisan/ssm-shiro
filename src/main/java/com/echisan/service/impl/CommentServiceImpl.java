package com.echisan.service.impl;

import com.echisan.dao.CommentMapper;
import com.echisan.model.po.Comment;
import com.echisan.model.po.CommentExample;
import com.echisan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author E73AN
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void saveComment(Comment comment) {
        commentMapper.insertSelective(comment);
    }

    @Override
    public void deleteCommentById(Long commentId) {
        commentMapper.deleteByPrimaryKey(commentId);
    }

    @Override
    public void updateComment(Comment comment) {
        commentMapper.updateByPrimaryKey(comment);
    }

    @Override
    public List<Comment> listCommentsByGoodsId(Long goodsId) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        commentExample.setOrderByClause("id desc");
        return commentMapper.selectByExample(commentExample);
    }

    @Override
    public List<Comment> listCommentsByUserId(Long userId) {

        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andUserIdEqualTo(userId);

        return commentMapper.selectByExample(commentExample);
    }

    @Override
    public Long countCommentByGoodsId(Long goodsId) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andGoodsIdEqualTo(goodsId);
        return commentMapper.countByExample(commentExample);

    }
}
