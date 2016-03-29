package aidl.com.mvptest.presenter;

/**
 * Created by Administrator on 2016/3/18.
 */
public interface ILoginPresenter {
    void doLogin(String name,String pwd);
    void clear();
}
