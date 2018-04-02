package com.echisan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * @author E73AN
 */
public class UploadFileUtil {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileUtil.class);
    private static final String LOCAL_PATH = "F:/学习/大三下/实训/upload/";
    private static final String IMAGE_CONTEXT_PATH = "/image/";

    /**
     * 上传图片
     *
     * @param multipartFiles multipartFiles
     * @return 返回照片的url 例如：/image/2018/3/15/image.png
     */
    public static List<String> uploadFileList(MultipartFile[] multipartFiles) {

        List<String> imageUrls = null;
        String datePath = getDatePath();
        File datePathFile = new File(LOCAL_PATH + datePath);
        if (!datePathFile.exists()) {
            if (datePathFile.mkdirs()) {
                logger.info("创建文件夹 " + datePathFile.getAbsolutePath() + " 成功");
            } else {
                logger.info("创建文件夹" + datePathFile.getAbsolutePath() + "失败");
            }
        }
        String finalFilePath = datePathFile.getAbsolutePath()+"/";

        if (multipartFiles != null && multipartFiles.length > 0) {
            imageUrls = new ArrayList<String>();
            String newFileName = "";
            for (MultipartFile multipartFile : multipartFiles) {
                String originalFileName = multipartFile.getOriginalFilename();
                newFileName = System.currentTimeMillis()+originalFileName.substring(originalFileName.lastIndexOf("."));
                File file = new File(finalFilePath+newFileName);
                try {
                    multipartFile.transferTo(file);
                    String imageUrl = IMAGE_CONTEXT_PATH + datePath + file.getName();
                    imageUrls.add(imageUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.info("写入文件失败！");
                }
            }
            return imageUrls;
        }
        return null;
    }

    private static String getDatePath() {
        Calendar calendar = Calendar.getInstance();
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH)+1;
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "/" + month + "/" + day + "/";
    }
}
