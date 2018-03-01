package app.qimi.cn.module_userinfo.presenter;

import android.widget.Toast;

import app.qimi.cn.lib_common.myapplication.BaseApplication;
import app.qimi.cn.module_userinfo.biz.IUserBiz;
import app.qimi.cn.module_userinfo.biz.OnLoginListener;
import app.qimi.cn.module_userinfo.biz.UserBiz;
import app.qimi.cn.module_userinfo.view.IUserLoginView;

/**
 * @author GaoXP
 * @time 2018/3/1.
 */

public class UserLoginPresenter {

    private IUserBiz userBiz;
    private IUserLoginView userLoginView;

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login() {

        userBiz.login(userLoginView.getUserName(), userLoginView.getPassWord(), new OnLoginListener() {
            @Override
            public void loginSuccess() {
                Toast.makeText(BaseApplication.getInstance(), "login scuess", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void loginFailed() {
                Toast.makeText(BaseApplication.getInstance(), "login failed", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void clear() {
            userLoginView.clearUserName();
            userLoginView.clearPassWord();

    }


}
