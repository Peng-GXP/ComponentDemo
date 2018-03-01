package app.qimi.cn.module_userinfo.biz;

/**
 * @author GaoXP
 * @time 2018/3/1.
 */

public class UserBiz implements IUserBiz {

    @Override
    public void login(String username, String password,OnLoginListener listener) {

        //请求成功,进行校验
        if ("gxp".equals(username) && "123".equals(password)) {
//            User user = new User();
//
//            user.username = username;
//            user.password = password;

            listener.loginSuccess();

        } else {

            listener.loginFailed();
        }
    }
}
