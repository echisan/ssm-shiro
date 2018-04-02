package com.echisan.controller;

import com.echisan.util.Constants;
import com.echisan.util.UploadFileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author E73AN
 */
@Controller
public class ImageController {


    @PostMapping(value = "/upload/image")
    @ResponseBody
    public List<String> saveImagePath(@RequestParam(value = "uploadImage") MultipartFile[] multipartFiles, HttpSession session){

        if (multipartFiles[0].getSize()==0){
            return null;
        }

        List<String> imageUrls = UploadFileUtil.uploadFileList(multipartFiles);
        if (imageUrls!=null){
            System.out.println(imageUrls);
            List<String> sessionImageUrls = (List<String>) session.getAttribute(Constants.IMAGE_URLS_SESSION_KEY);
            if (sessionImageUrls!=null && sessionImageUrls.size()!=0){
                sessionImageUrls.addAll(imageUrls);
                session.setAttribute(Constants.IMAGE_URLS_SESSION_KEY,sessionImageUrls);
                return sessionImageUrls;
            }
            session.setAttribute(Constants.IMAGE_URLS_SESSION_KEY,imageUrls);
            return imageUrls;
        }else {
            return null;
        }
    }
}
