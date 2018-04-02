package com.echisan.controller.goods;


import com.echisan.model.po.*;
import com.echisan.model.vo.CommentVO;
import com.echisan.model.vo.GoodsVO;
import com.echisan.model.vo.IndexGoodsVO;
import com.echisan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author E73AN
 */
@Controller
public class GoodsController {

    /**
     * 浏览次数只有超过20分钟才有效
     */
    private static final int VIEW_TIME_LIMIT = 20;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsCategoryService goodsCategoryService;


    @RequestMapping(value = "/goods/{goods_id}", method = RequestMethod.GET)
    public String renderGoodsPage(@PathVariable(value = "goods_id") Long goodsId,
                                  Model model) {

        if (goodsService.getGoodsById(goodsId)==null){
            return "redirect:/404";
        }


        goodsService.updateGoodsViewsNum(goodsId);

        Goods goods = goodsService.getGoodsById(goodsId);
        List<Image> imageList = imageService.listImagesByGoodsId(goodsId);
        User user = userService.getUserById(goods.getUserId());
        List<Category> categoryList = categoryService.listCategoryByGoodsId(goodsId);

        List<Comment> commentList = commentService.listCommentsByGoodsId(goodsId);
        List<CommentVO> commentVOList = null;

        if (commentList != null && commentList.size() != 0) {
            commentVOList = new ArrayList<CommentVO>();
            for (Comment c :
                    commentList) {
                CommentVO commentVO = new CommentVO();
                commentVO.setComment(c);
                commentVO.setReplyUsername(userService.getUserById(c.getUserId()).getUsername());
                commentVOList.add(commentVO);
            }
        }

        GoodsVO goodsVO = new GoodsVO();
        goodsVO.setGoods(goods);
        goodsVO.setImages(imageList);
        goodsVO.setUser(user);
        goodsVO.setComments(commentVOList);
        goodsVO.setGoodsCategoryList(categoryList);
        model.addAttribute("goodsInfo", goodsVO);
        return "goods";
    }

    @RequestMapping(value = "/goods/{goods_id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteGoods(@PathVariable(value = "goods_id") Long goodsId) {
        try {
            goodsService.deleteGoodsById(goodsId);
            imageService.deleteImagesByGoodsId(goodsId);
            return "success";
        } catch (Exception e) {
            return "failed";
        }
    }

    @RequestMapping(value = "/goods/new")
    public String renderNewGoodsList(Model model) {

        List<Goods> newGoods = goodsService.listNewGoods();
        List<IndexGoodsVO> indexGoodsVOList = new ArrayList<IndexGoodsVO>();

        for (Goods goods : newGoods) {
            IndexGoodsVO indexGoodsVO = new IndexGoodsVO();
            indexGoodsVO.setGoods(goods);
            indexGoodsVO.setImage(imageService.getGoodsImage(goods.getId()));
            indexGoodsVO.setCommentNum(commentService.countCommentByGoodsId(goods.getId()));
            indexGoodsVOList.add(indexGoodsVO);
        }

        model.addAttribute("goodsList", indexGoodsVOList);

        return "goods_list";
    }

    @RequestMapping(value = "/goods/hot")
    public String renderHotGoodsList(Model model) {
        List<Goods> hotGoods = goodsService.listHotGoods();
        List<IndexGoodsVO> indexGoodsVOList = new ArrayList<IndexGoodsVO>();

        for (Goods goods : hotGoods) {
            IndexGoodsVO indexGoodsVO = new IndexGoodsVO();
            indexGoodsVO.setGoods(goods);
            indexGoodsVO.setImage(imageService.getGoodsImage(goods.getId()));
            indexGoodsVO.setCommentNum(commentService.countCommentByGoodsId(goods.getId()));
            indexGoodsVOList.add(indexGoodsVO);
        }
        model.addAttribute("goodsList", indexGoodsVOList);

        return "goods_list";
    }

    @RequestMapping(value = "/goods/search", method = RequestMethod.GET)
    public String renderSearchGoodsList(@RequestParam(value = "keyword") String keyWord,
                                        @RequestParam(value = "categoryId") Short categoryId,
                                        Model model) {

        List<Goods> goodsList = null;

        // 如果关键词与分类都为控
        if ("".equals(keyWord.trim()) && categoryId == null) {
            goodsList = new ArrayList<Goods>();
        }

        // 如果关键词不为空 且 分类为空
        if (!"".equals(keyWord.trim()) && categoryId == null) {
            goodsList = goodsService.listGoodsByKeyWords(keyWord);
        }

        // 如果关键词为空 且 分类不为空
        if ("".equals(keyWord.trim()) && categoryId != null) {
            goodsList = goodsService.listGoodsByCategoryId(categoryId);
        }

        // 如果关键词 且 分类 都不为空
        if (!"".equals(keyWord.trim()) && categoryId != null) {

            List<Goods> keywordGoodsList = goodsService.listGoodsByKeyWords(keyWord);
            List<Goods> categoryGoodsList = goodsService.listGoodsByCategoryId(categoryId);
            goodsList = new ArrayList<Goods>();
            for (Goods keywordGoods : keywordGoodsList){
                for (Goods categoryGoods : categoryGoodsList){
                    if (keywordGoods.getId().equals(categoryGoods.getId())){
                        goodsList.add(keywordGoods);
                    }
                }
            }
        }

        List<IndexGoodsVO> indexGoodsVOList2 = null;
        if (goodsList!=null){
            indexGoodsVOList2 = toIndexGoodsVOList(goodsList);
        }


        List<Category> categoryList = categoryService.listCategories();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("goodsList",indexGoodsVOList2);
        model.addAttribute("keyword", keyWord);
        return "search_goods_list";
    }

    @RequestMapping(value = "goods/category/{id}", method = RequestMethod.GET)
    public String renderCategoryGoodsList(@PathVariable(value = "id") Short categoryId, Model model) {

        List<Goods> goodsList = goodsService.listGoodsByCategoryId(categoryId);
        List<IndexGoodsVO> categoryGoodsList = toIndexGoodsVOList(goodsList);
        model.addAttribute("goodsList", categoryGoodsList);
        return "search_goods_list";

    }


    private List<IndexGoodsVO> toIndexGoodsVOList(List<Goods> list) {
        List<IndexGoodsVO> indexGoodsVOList = new ArrayList<IndexGoodsVO>();
        for (Goods goods : list) {
            IndexGoodsVO indexGoodsVO = new IndexGoodsVO();
            indexGoodsVO.setGoods(goods);
            indexGoodsVO.setImage(imageService.getGoodsImage(goods.getId()));
            indexGoodsVO.setCommentNum(commentService.countCommentByGoodsId(goods.getId()));
            indexGoodsVOList.add(indexGoodsVO);
        }
        return indexGoodsVOList;
    }

}
