package com.echisan.controller.user;

import com.echisan.model.FormErrorMessages;
import com.echisan.model.bo.LoginFormBO;
import com.echisan.model.po.Role;
import com.echisan.model.po.User;
import com.echisan.service.UserRolePermissionService;
import com.echisan.service.UserService;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author E73AN
 */
@Controller
public class LoginController {

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRolePermissionService userRolePermissionService;

    @GetMapping(value = "/login")
    public String renderLoginPage(){
        return "login";
    }

    @PostMapping(value = "/login")
    public String doLogin(@ModelAttribute(value = "loginForm")@Validated LoginFormBO loginFormBO, BindingResult result, HttpSession session, Model model){

        Map<String, String> errors = new HashMap<String, String>();
        List<FieldError> fieldErrorList = result.getFieldErrors();
        if (result.hasErrors()){
            for (FieldError fieldError:fieldErrorList) {
                if ("username".equals(fieldError.getField())) {
                    errors.put(FormErrorMessages.USERNAME_IS_NULL, fieldError.getDefaultMessage());
                } else if ("password".equals(fieldError.getField())) {
                    errors.put(FormErrorMessages.PASSWORD_IS_NULL, fieldError.getDefaultMessage());
                } else if ("verifyCode".equals(fieldError.getField())) {
                    errors.put(FormErrorMessages.VERIFY_CODE_ERROR, fieldError.getDefaultMessage());
                }
            }
            model.addAttribute("errors",errors);
            return "login";
        }

        if (!loginFormBO.getVerifyCode().equalsIgnoreCase((String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY))){
            errors.put(FormErrorMessages.VERIFY_CODE_ERROR,"验证码错误！");
            model.addAttribute("errors",errors);
            return "login";
        }


        UsernamePasswordToken token = new UsernamePasswordToken(loginFormBO.getUsername(), loginFormBO.getPassword());
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();


        try {
            subject.login(token);
            model.addAttribute(FormErrorMessages.LOGIN_STATUS_SUCCEED,"登陆成功！");
            User activeUser =  userService.getUserByName(loginFormBO.getUsername());
            session.setAttribute("activeUser",activeUser);
            Role role = userRolePermissionService.getUserRoleByUserId(activeUser.getId());
            session.setAttribute("role",role);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            model.addAttribute(FormErrorMessages.LOGIN_STATUS_FAILED,"账号或密码不正确！");
            model.addAttribute("username",loginFormBO.getUsername());
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
