package app.qimi.cn.module_userinfo.biz;

/**
 * @author GaoXP
 * @time 2018/3/1.
 */

public interface IUserBiz {

    void login(String username, String password, OnLoginListener listener);
}
