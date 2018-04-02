package com.echisan.controller.user;

import com.echisan.model.FormErrorMessages;
import com.echisan.model.bo.PasswordSaltUserBO;
import com.echisan.model.bo.RegisterFormBO;
import com.echisan.model.bo.RegisterUserBO;
import com.echisan.model.po.User;
import com.echisan.service.UserRolePermissionService;
import com.echisan.service.UserService;
import com.echisan.util.PasswordHelper;
import com.google.code.kaptcha.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author E73AN
 */
@Controller
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRolePermissionService userRolePermissionService;

    @GetMapping(value = "/register")
    public String renderRegisterPage(){
        return "register";
    }

    @PostMapping(value = "/register")
    public String doRegister(@ModelAttribute(value = "registerForm") RegisterFormBO registerFormBO, HttpSession session,Model model){
        boolean flag = true;
        Map<String,String> errorMap = new HashMap<String, String>(4);
        String verifyCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (registerFormBO.getUsername()==null || "".equals(registerFormBO.getUsername())){
            errorMap.put(FormErrorMessages.USERNAME_IS_NULL,"用户名不能为空！");
            flag = false;
        }else {
            model.addAttribute("usernameTemp",registerFormBO.getUsername());
        }

        if (!registerFormBO.getVerifyCode().equalsIgnoreCase(verifyCode)) {
            errorMap.put(FormErrorMessages.VERIFY_CODE_ERROR,"验证码错误！");
            flag = false;
        }

        if ("".equals(registerFormBO.getPassword()) || "".equals(registerFormBO.getRePassword())){
            errorMap.put(FormErrorMessages.PASSWORD_IS_NULL,"密码不能为空！");
            flag = false;
        }else {
            if (!registerFormBO.getPassword().equals(registerFormBO.getRePassword())){
                errorMap.put(FormErrorMessages.PASSWORD_NOT_SAME,"两次密码匹配不正确！");
                flag = false;
            }
        }

        if (flag){
            if (userService.getUserByName(registerFormBO.getUsername())!=null){
                errorMap.put(FormErrorMessages.USERNAME_IS_EXIST,"该帐号已存在！");
            }else {
                RegisterUserBO registerUserBO = new RegisterUserBO(registerFormBO.getUsername(),registerFormBO.getPassword());
                PasswordHelper.encryptPasswordAndSetSalt(registerUserBO);
                User user = new User();
                user.setUsername(registerUserBO.getUsername());
                user.setPassword(registerUserBO.getPassword());
                user.setSalt(registerUserBO.getSalt());
                userService.saveDefaultUser(user);
                // 这里注册成功 直接登陆再跳转到首页
                User activeUser = userService.getUserByName(registerUserBO.getUsername());
                session.setAttribute("activeUser",activeUser);
                session.setAttribute("role",userRolePermissionService.getUserRoleByUserId(activeUser.getId()));
                return "redirect:/";
            }
        }

        model.addAttribute("errors",errorMap);
        return "register";
    }

}
