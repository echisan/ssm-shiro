package com.echisan.controller.admin;

import com.echisan.model.po.Category;
import com.echisan.model.po.Goods;
import com.echisan.model.po.User;
import com.echisan.model.vo.AdminGoodsVO;
import com.echisan.service.CategoryService;
import com.echisan.service.GoodsService;
import com.echisan.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author E73AN
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String renderAdminPage(){
        return "admin/home";
    }

    @GetMapping(value = "/category")
    public String renderCategoryPage(Model model){

        List<Category> categories = categoryService.listCategories();
        model.addAttribute("categories",categories);


        return "admin/category";
    }

    @GetMapping(value = "/user")
    public String renderUserPage(Model model){

        List<User> normalUser = userService.listAuthUser();
        return "admin/user";
    }

    @PostMapping(value = "/category")
    @ResponseBody
    public String addCategory(@RequestParam(value = "category_name") String categoryName){


        Category category = new Category();
        category.setName(categoryName);
        category.setDescription(categoryName);

        try {
            categoryService.saveCategory(category);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed msg: "+ e.getMessage();
        }
    }

    @PostMapping(value = "/category/delete")
    @ResponseBody
    public String deleteCategory(@RequestParam(value = "categoryIds") Short[] categoryIds){

        try {
            if (categoryIds.length!=0){
                if (categoryIds.length==1){
                    categoryService.deleteCategoryById(categoryIds[0]);
                }else {
                    List<Short> categoryIdsList = new ArrayList<Short>();
                    Collections.addAll(categoryIdsList, categoryIds);
                    categoryService.deleteCategoryByIds(categoryIdsList);
                }
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failed msg:"+e.getMessage();
        }
        return "error";
    }

    @GetMapping(value = "/goods")
    public String renderGoodsPage(@RequestParam(value = "page",defaultValue = "1",required = false)Integer page,
                                  Model model){

        PageHelper.startPage(page,20);
        List<Goods> goodsList = goodsService.listGoods();
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(goodsList);
        List<AdminGoodsVO> adminGoodsVOList = new ArrayList<AdminGoodsVO>();

        for (Goods goods :
                goodsList) {
            AdminGoodsVO agvo = new AdminGoodsVO();
            agvo.setGoods(goods);
            agvo.setUser(userService.getUserById(goods.getUserId()));
            adminGoodsVOList.add(agvo);
        }

        model.addAttribute("adminGoodsVOList",adminGoodsVOList);
        model.addAttribute("pageInfo",pageInfo);

        return "admin/goods";
    }

    @GetMapping(value = "/goods/{username}")
    public String getUserGoods(@PathVariable(value = "username")String username, Model model,
                               @RequestParam(value = "page",defaultValue = "1",required = false)Integer page){
        PageHelper.startPage(page,20);
        List<Goods> userGoodsList = goodsService.listGoodsByUsername(username);
        PageInfo<Goods> goodsPageInfo = new PageInfo<Goods>(userGoodsList);
        List<AdminGoodsVO> adminGoodsVOList = goodsListToAdminGoodsVo(userGoodsList);
        model.addAttribute("adminGoodsVOList",adminGoodsVOList);
        model.addAttribute("pageInfo",goodsPageInfo);
        return "admin/goods";

    }

    private List<AdminGoodsVO> goodsListToAdminGoodsVo(List<Goods> goodsList){
        List<AdminGoodsVO> adminGoodsVOList = new ArrayList<AdminGoodsVO>();
        for (Goods goods :
                goodsList) {
            AdminGoodsVO agvo = new AdminGoodsVO();
            agvo.setGoods(goods);
            agvo.setUser(userService.getUserById(goods.getUserId()));
            adminGoodsVOList.add(agvo);
        }
        return adminGoodsVOList;
    }

    @DeleteMapping(value = "/goods/{goods_id}")
    @ResponseBody
    public String deleteGoods(@PathVariable(value = "goods_id")Long goodsId){

        try {
            goodsService.deleteGoodsById(goodsId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

}
