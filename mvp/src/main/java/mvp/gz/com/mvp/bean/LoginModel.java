package mvp.gz.com.mvp.bean;


/**
 * Created by camming on 2018\12\28 0028.
 * code is data  data is code
 *
 * 登录结果
 */

public class LoginModel extends HttpModel {


    private User data;

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }


    public User getUser() {
        return data;
    }

    public void setUser(User data) {
        this.data = data;
    }
}
