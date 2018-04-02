package com.echisan.service;

import com.echisan.model.po.Comment;

import java.util.List;

/**
 * @author E73AN
 */
public interface CommentService {

    /**
     * 添加评论
     * @param comment 评论的实体
     */
    void saveComment(Comment comment);

    /**
     * 通过评论的id删除评论
     * @param commentId 评论id
     */
    void deleteCommentById(Long commentId);

    /**
     * 更新评论
     * @param comment 需要更新的评论
     */
    void updateComment(Comment comment);

    /**
     * 获取某个物品下的所有评论
     * @param goodsId 该物品的id
     * @return 评论列表
     */
    List<Comment> listCommentsByGoodsId(Long goodsId);

    /**
     * 获取某个用户下的所有评论
     * @param userId 某个用户的id
     * @return 评论列表
     */
    List<Comment> listCommentsByUserId(Long userId);

    Long countCommentByGoodsId(Long goodsId);
}
