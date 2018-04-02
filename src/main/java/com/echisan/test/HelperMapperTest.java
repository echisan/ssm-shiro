package com.echisan.test;


import com.echisan.dao.helper.GoodsCategoryHelperMapper;
import com.echisan.model.po.GoodsCategory;
import com.echisan.model.po.Image;
import com.echisan.service.ImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author E73AN
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml",
        "classpath:spring/applicationContext-dao.xml",
        "classpath:spring/applicationContext-service.xml"
})
public class HelperMapperTest {

    @Autowired
    private GoodsCategoryHelperMapper goodsCategoryHelperMapper;

    @Autowired
    private ImageService imageService;

    @Test
    public void gcTest() {
        List<GoodsCategory> list = new ArrayList<GoodsCategory>();
        for (Short i = 0; i < 3; i++) {
            GoodsCategory goodsCategory = new GoodsCategory();
            goodsCategory.setGoodsId(1L);
            goodsCategory.setCategoryId(i);
            list.add(goodsCategory);
        }
        goodsCategoryHelperMapper.insertByBatch(list);

    }


    @Test
    public void imageTest(){

        System.out.println(imageService.listImagesByGoodsId(18L));
    }

}
