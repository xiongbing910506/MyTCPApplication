package httpapplication.nicechina.com.httpapplication.model;

import java.util.List;

/**
 * Created by diaoyuhang on 2017-06-05.
 */

public class ResultListJson<T> {
    /**
     * 信息
     */
    private String message;

    /**
     * 状态码
     */
    private int statusCode;

    /**
     * 是否成功
     */
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<T> getData() {
        return data;
    }

    public void setData( List<T> data) {
        this.data = data;
    }

    public  List<T> data;
}
