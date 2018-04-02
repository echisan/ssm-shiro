package com.echisan.controller;

import com.echisan.model.po.Comment;
import com.echisan.model.po.User;
import com.echisan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author E73AN
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public String addComment(@RequestParam(value = "content")String content,
                             @RequestParam(value = "goodsId")Long goodsId,
                             HttpSession session,
                             RedirectAttributes attributes){

        User activeUser = (User) session.getAttribute("activeUser");
        Comment comment = new Comment();
        comment.setGoodsId(goodsId);
        comment.setUserId(activeUser.getId());
        comment.setContent(content);

        commentService.saveComment(comment);

        attributes.addAttribute(goodsId);

        return "success";

    }

    @RequestMapping(value = "/comment/{comment_id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteComment(@PathVariable(value = "comment_id")Long commentId){
        commentService.deleteCommentById(commentId);
        return "success";
    }
}
