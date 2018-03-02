package app.qimi.cn.module_userinfo.presenter;

import app.qimi.cn.module_userinfo.LoginActivity;
import dagger.Component;

/**
 * @author GaoXP
 * @time 2018/3/2.
 */

@Component(modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

}