package app.qimi.cn.module_userinfo.view;

/**
 * @author GaoXP
 * @time 2018/3/1.
 */

public interface IUserLoginView {


    //这个就是 activity 页面索要做的事情
    String getUserName();

    String getPassWord();

    void clearUserName();

    void clearPassWord();

    void toMainActivity();

    void showFailedError();


}
