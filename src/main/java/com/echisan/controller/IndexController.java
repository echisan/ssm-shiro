package com.echisan.controller;

import com.echisan.model.po.*;
import com.echisan.model.vo.IndexGoodsVO;
import com.echisan.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author E73AN
 */
@Controller
public class IndexController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET, name = "renderIndexRM")
    public String renderIndex(HttpSession session, Model model) {

        List<Category> categoryList = categoryService.listCategories();
        model.addAttribute("categoryList",categoryList);


        PageHelper.startPage(1,8);
        List<Goods> goodsList = goodsService.listNewGoods();
        PageInfo<Goods> goodsPageInfo = new PageInfo<Goods>(goodsList);

        List<Goods> newGoods = goodsPageInfo.getList();
        List<IndexGoodsVO> indexGoodsVOList = new ArrayList<IndexGoodsVO>();
        for (Goods goods:newGoods){
            IndexGoodsVO indexGoodsVO = new IndexGoodsVO();
            indexGoodsVO.setGoods(goods);
            Image image = imageService.getGoodsImage(goods.getId());
            indexGoodsVO.setImage(image);
            Long commentNum = commentService.countCommentByGoodsId(goods.getId());
            indexGoodsVO.setCommentNum(commentNum);
            indexGoodsVOList.add(indexGoodsVO);
        }
        model.addAttribute("newGoodsList",indexGoodsVOList);


        PageHelper.startPage(1,8);
        List<Goods> hotGoodsList = goodsService.listHotGoods();
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(hotGoodsList);
        List<Goods> hotGoods = pageInfo.getList();
        List<IndexGoodsVO> indexGoodsVOList1 = new ArrayList<IndexGoodsVO>();
        for (Goods goods:hotGoods){
            IndexGoodsVO indexGoodsVO = new IndexGoodsVO();
            indexGoodsVO.setGoods(goods);
            Image image = imageService.getGoodsImage(goods.getId());
            indexGoodsVO.setImage(image);
            Long commentNum = commentService.countCommentByGoodsId(goods.getId());
            indexGoodsVO.setCommentNum(commentNum);
            indexGoodsVOList1.add(indexGoodsVO);
        }

        model.addAttribute("hotGoodsList", indexGoodsVOList1);

        return "index";
    }


}
