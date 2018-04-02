import com.echisan.util.UploadFileUtil;

import java.util.Calendar;

public class MyTest {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH);
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        String a =  year + "/" + month + "/" + day + "/";
        System.out.println(a);
    }
}
