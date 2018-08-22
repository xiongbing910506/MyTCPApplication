package httpapplication.nicechina.com.httpapplication.model;

import com.orm.SugarRecord;

/**
 * Created by diaoyuhang on 2017-06-07.
 */

public class FileTag extends SugarRecord{

    private String type;
    private String path;

    public FileTag(String type, String path) {
        this.type = type;
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
