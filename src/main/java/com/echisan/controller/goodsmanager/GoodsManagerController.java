package com.echisan.controller.goodsmanager;

import com.echisan.model.po.Category;
import com.echisan.model.po.Goods;
import com.echisan.model.po.Image;
import com.echisan.model.po.User;
import com.echisan.model.vo.GoodsVO;
import com.echisan.model.vo.PublishedGoodsVo;
import com.echisan.model.vo.UpdateGoodsCategoryVO;
import com.echisan.service.CategoryService;
import com.echisan.service.GoodsService;
import com.echisan.service.ImageService;
import com.echisan.util.Constants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author E73AN
 */
@Controller
public class GoodsManagerController {

    private static final String TEMPLATE_PAGE_KEY = "TEMPLATE_PAGE_KEY";
    private static final String ADD_GOODS_PAGE = "add_goods_page";
    private static final String PUBLISHED_GOODS = "published_goods";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/gm", method = RequestMethod.GET)
    public String renderGoodsMangerPage(Model model) {
        model.addAttribute(TEMPLATE_PAGE_KEY, ADD_GOODS_PAGE);
        List<Category> categories = categoryService.listCategories();
        model.addAttribute("categories", categories);
        return "manager/home";
    }

    @RequestMapping(value = "gm/published", method = RequestMethod.GET)
    public String renderPublishedPage(Model model, HttpSession session,
                                      @RequestParam(value = "page", defaultValue = "1",required = false)Integer page) {
        // 转到相应页面
        model.addAttribute(TEMPLATE_PAGE_KEY, PUBLISHED_GOODS);
        // 获取已登陆的用户
        User activeUser = (User) session.getAttribute("activeUser");

        // 获取该用户的物品信息
        PageHelper.startPage(page,10);
        List<Goods> goodsList = goodsService.listGoodsByUserId(activeUser.getId());
        PageInfo<Goods> goodsPageInfo = new PageInfo<Goods>(goodsList);

        System.out.println(goodsPageInfo);

        if (goodsList != null && goodsList.size() != 0) {

            List<PublishedGoodsVo> publishedGoodsVoList = new ArrayList<PublishedGoodsVo>();
            for (Goods goods : goodsList) {
                PublishedGoodsVo pgv = new PublishedGoodsVo();
                pgv.setGoods(goods);
                pgv.setImage(imageService.getGoodsImage(goods.getId()));
                publishedGoodsVoList.add(pgv);
            }

            model.addAttribute("publishedGoodsVoList", publishedGoodsVoList);
            model.addAttribute("pageInfo",goodsPageInfo);
        }

        return "manager/home";
    }


    @RequestMapping(value = "gm/goods", method = RequestMethod.POST)
    @ResponseBody
    public String addGoods(String name, BigDecimal price, String description,
                           @RequestParam(value = "categoryIds") Short[] categories,
                           HttpSession session) {

        Goods goods = new Goods();
        goods.setName(name);
        goods.setPrice(price);
        goods.setDescription(description);

        User user = (User) session.getAttribute("activeUser");

        goods.setUserId(user.getId());
        goodsService.saveGoods(goods, categories);

        List<String> imageUrls = (List<String>) session.getAttribute(Constants.IMAGE_URLS_SESSION_KEY);
        if (imageUrls != null && imageUrls.size() != 0) {
            imageService.saveImage(imageUrls, goods.getId());
        }
        session.removeAttribute(Constants.IMAGE_URLS_SESSION_KEY);
        return "success";
    }

    @RequestMapping(value = "gm/goods/{goods_id}", method = RequestMethod.GET)
    public String renderUpdateGoodsPage(@PathVariable(value = "goods_id") Long goodsId, Model model) {
        Goods goods = goodsService.getGoodsById(goodsId);
        GoodsVO goodsVO = new GoodsVO();
        goodsVO.setGoods(goods);
        List<Image> imageList = imageService.listImagesByGoodsId(goods.getId());
        if (imageList!=null){
            goodsVO.setImages(imageList);
        }

        List<Category> allCategoryList = categoryService.listCategories();

        List<Category> goodsCategoryList = categoryService.listCategoryByGoodsId(goodsId);

        List<Short> goodsCategoryIds = new ArrayList<Short>();

        for (Category c :
                goodsCategoryList) {
            goodsCategoryIds.add(c.getId());
        }

        List<UpdateGoodsCategoryVO> updateGoodsCategoryVOList = new ArrayList<UpdateGoodsCategoryVO>();
        for (Category category : allCategoryList){
            UpdateGoodsCategoryVO vo = new UpdateGoodsCategoryVO();
            vo.setCategory(category);
            if (goodsCategoryIds.contains(category.getId())){
                vo.setSelect(true);
            }else {
                vo.setSelect(false);
            }
            updateGoodsCategoryVOList.add(vo);
        }

        goodsVO.setUpdateGoodsCategoryVOList(updateGoodsCategoryVOList);
        model.addAttribute("goodsVO",goodsVO);
        return "manager/update_goods";

    }

    @RequestMapping(value = "gm/goods/{goods_id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateGoodsInfo(@PathVariable(value = "goods_id")Long goodsId,
                                  String name, BigDecimal price, Short[] categoryIds,String description,
                                  @RequestParam(value = "user_id") Long userId){

        Goods goods = new Goods();
        goods.setId(goodsId);
        goods.setName(name);
        goods.setPrice(price);
        goods.setDescription(description);
        goods.setUserId(userId);
        goods.setLastChangeTime(new Date());


        try {
            goodsService.updateGoods(goods);
            goodsService.updateGoodsCategory(goodsId,categoryIds);
            return "success";
        } catch (Exception e) {
            return "failed message: "+e.getMessage();
        }

    }

    @RequestMapping(value = "gm/goods/image/{image_id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteImageFromGoods(@PathVariable(value = "image_id")Long imageId){
        try {
            goodsService.deleteGoodsOneImageByImageId(imageId);
            return "success";
        } catch (Exception e) {
            return "failed message: "+e.getMessage();
        }
    }

    @RequestMapping(value = "gm/goods/{goods_id}/image", method = RequestMethod.POST)
    @ResponseBody
    public String updateGoodsImage(@PathVariable(value = "goods_id")Long goodsId, HttpSession session){
        List<String> imageUrl = (List<String>) session.getAttribute(Constants.IMAGE_URLS_SESSION_KEY);
        try {
            imageService.saveImage(imageUrl,goodsId);
            session.removeAttribute(Constants.IMAGE_URLS_SESSION_KEY);
            goodsService.updateGoodsLastChangeTime(goodsId);
            return "success";
        } catch (Exception e) {
            return "failed message: "+e.getMessage();
        }
    }

}
