package app.qimi.cn.module_userinfo.presenter;

import app.qimi.cn.module_userinfo.view.IUserLoginView;
import dagger.Module;
import dagger.Provides;

/**
 * @author GaoXP
 * @time 2018/3/2.
 */

@Module
public class LoginModule {

    private final IUserLoginView mView;

    public LoginModule(IUserLoginView view) {
        mView = view;
    }

    @Provides
    IUserLoginView provideMainView() {
        return mView;
    }

}
