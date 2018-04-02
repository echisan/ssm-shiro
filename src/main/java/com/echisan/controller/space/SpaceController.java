package com.echisan.controller.space;

import com.echisan.model.po.User;
import com.echisan.model.po.UserInfo;
import com.echisan.model.po.Wallet;
import com.echisan.model.po.WalletRecord;
import com.echisan.service.UserService;
import com.echisan.service.WalletService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author E73AN
 */
@Controller
@RequestMapping(value = "/space")
public class SpaceController {

    @Autowired
    private UserService userService;

    @Autowired
    private WalletService walletService;

    @GetMapping()
    public String renderSpaceIndex(){
        return "space/home";
    }

    @RequestMapping(value = "/wallet")
    public String renderWallet(Model model, HttpSession session,
                               @RequestParam(value = "page",required = false, defaultValue = "1")Integer page){
        User user = (User) session.getAttribute("activeUser");
        Wallet wallet = walletService.getWalletByUserId(user.getId());
        model.addAttribute("wallet",wallet);

        PageHelper.startPage(page,20);
        List<WalletRecord> walletRecordList = walletService.listWalletRecordByUserId(user.getId());
        PageInfo<WalletRecord> walletRecordPageInfo = new PageInfo<WalletRecord>(walletRecordList);
        model.addAttribute("walletRecordPageInfo",walletRecordPageInfo);
        return "space/wallet";
    }

    @RequestMapping(value = "/wallet", method = RequestMethod.POST)
    @ResponseBody
    public String addBalance(@RequestParam(value = "add_balance")BigDecimal addBalance,
                             HttpSession session){
        User user = (User) session.getAttribute("activeUser");
        Wallet wallet = walletService.getWalletByUserId(user.getId());

        BigDecimal finalBalance = wallet.getBalance().add(addBalance);

        // 更新一下钱包的记录
        WalletRecord walletRecord = new WalletRecord();
        walletRecord.setUserId(user.getId());
        walletRecord.setMoney(addBalance);
        walletRecord.setBehaviourType(WalletService.INCOME);
        walletRecord.setRemarks("充值");
        walletService.saveWalletRecord(walletRecord);


        try {
            walletService.updateWalletBalanceByUserId(user.getId(),finalBalance);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed msg: "+e.getMessage();
        }

    }

    @GetMapping(value = "/pwd")
    public String renderWalletPage(Model model){
        return "space/pwd";
    }

    @GetMapping(value = "/info/{user_id}")
    public String renderInfo(@PathVariable(value = "user_id")Long userId, Model model){
        UserInfo userInfo = userService.getUserInfoByUserId(userId);
        if (userInfo!=null){
            model.addAttribute("userInfo",userInfo);
        }
        return "space/info";
    }

    @PostMapping(value = "/info/{user_id}")
    @ResponseBody
    public String insertAndUpdateInfo(@PathVariable(value = "user_id")Long userId,
                                      @RequestParam(value = "real_name")String realName,
                                      @RequestParam(value = "phone")String phone,
                                      @RequestParam(value = "address")String address){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setRealName(realName);
        userInfo.setAddress(address);
        userInfo.setPhone(phone);

        try {
            userService.saveUserInfo(userInfo);
            return "success";
        } catch (Exception e) {
            return "failed message : "+ e.getMessage();
        }
    }

    @PostMapping(value = "/pwd")
    @ResponseBody
    public String updateUserPassword(@RequestParam(value = "new_password")String newPassword,
                                     HttpSession session){

        User activeUser = (User) session.getAttribute("activeUser");

        try {
            userService.updateUserPassword(activeUser.getId(),newPassword);
            return "success";
        } catch (Exception e) {
            return "failed message:"+e.getMessage();
        }
    }

    @GetMapping(value = "/comment")
    public String renderCommentPage(){
        return "space/comment";
    }
}
