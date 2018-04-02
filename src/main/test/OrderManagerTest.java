import com.echisan.model.po.Goods;
import com.echisan.model.po.Order;
import com.echisan.service.GoodsService;
import com.echisan.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml",
        "classpath:spring/applicationContext-service.xml","classpath:spring/applicationContext.xml"})
public class OrderManagerTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @Test
    public void test(){
        List<Order> orderList = orderService.listUnFinishOrderByUserId(22L);
        List<Long> goodsIds = new ArrayList<Long>();
        for (Order order : orderList){
            goodsIds.add(order.getGoodsId());
        }

        List<Goods> goodsList = goodsService.listGoodsByIds(goodsIds);

        System.out.println(orderList.toString());
        System.out.println(goodsList.toString());

        System.out.println(orderList.get(0).getOrderStatus().equals((byte)0));
    }

}
