package httpapplication.nicechina.com.httpapplication.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class UserInfo extends SugarRecord {
    private int userId;
    @Ignore
    private String userName;
    public int getUserId()
    {
        return userId;
    }
    public void setUserId(int id)
    {
        userId = id;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String name)
    {
        userName = name;
    }
}
