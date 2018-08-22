package httpapplication.nicechina.com.httpapplication.global;

import okhttp3.MediaType;

/**
 * Created by diaoyuhang on 2017-06-01.
 */

public class AppConfig {

    public static String SERVER_API = "http://192.168.0.101:8081/rest/";

    public static String SERVER_RESOURCE_ADDRESS = "http://192.168.0.101:8081";

    public static String SERVER_ADDRESS = "http://192.168.0.101:8081";

    public static final MediaType MEDIA_URLENCODED = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");//mdiatype 这个需要和服务端保持一致

    public static final MediaType MEDIA_JSON  = MediaType.parse("application/json; charset=utf-8");

    public static final String GONG_ZUO_JIE_DIAN = "工作节点";

    public static final String WU_LIU_CHENG_JIE_DIAN = "工作节点";

    public static String IP = "118.89.101.126";

    public static String PORT = "8080";

    public static String LOCAL_WORK_FILES_SAVE_PATH="/Android/data/jinfulaikeji.com.powerapp/files/Download/";//在线保存工作的文件绝对路径

}
