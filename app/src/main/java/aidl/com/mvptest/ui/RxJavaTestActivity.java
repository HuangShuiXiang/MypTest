package aidl.com.mvptest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nineoldandroids.animation.AnimatorSet;

import java.util.ArrayList;
import java.util.List;

import aidl.com.mvptest.R;
import aidl.com.mvptest.design.StrategyPattern.iFace.Role;
import aidl.com.mvptest.design.StrategyPattern.iFaceImpl.AttackJYSG;
import aidl.com.mvptest.design.StrategyPattern.iFaceImpl.DefendJZZ;
import aidl.com.mvptest.design.StrategyPattern.iFaceImpl.DisplayA;
import aidl.com.mvptest.design.StrategyPattern.iFaceImpl.RoleA;
import aidl.com.mvptest.design.StrategyPattern.iFaceImpl.RunJCTQ;
import aidl.com.mvptest.design.decorPattern.ArmEquip;
import aidl.com.mvptest.design.decorPattern.BlueGemDecorator;
import aidl.com.mvptest.design.decorPattern.IEquip;
import aidl.com.mvptest.design.decorPattern.RedGemDecorator;
import aidl.com.mvptest.presenter.ILoginPresenter;
import aidl.com.mvptest.presenter.LoginPresenterImpl;
import aidl.com.mvptest.util.ToastUitl;
import aidl.com.mvptest.view.ILoginView;
import aidl.com.mvptest.view.MoveTestView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaTestActivity extends AppCompatActivity{
    private static final String TAG = "RxJavaTestActivity";
    private EditText ed_user;
    private EditText ed_pwd;
    private Button btn_login;
    private Button btn_clear;
    private MoveTestView tv_num;
    private List<String> list = new ArrayList<>();
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_test);
        ed_user = (EditText) findViewById(R.id.ed_user);
        ed_pwd = (EditText) findViewById(R.id.ed_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        tv_num = (MoveTestView) findViewById(R.id.tv_num);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        //模仿冲数据库拿数据
                        list = getResultFromDb();
                    }
                }).*/
                //写法一
                Observable.from(getResultFromDb())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        s = list.get(x);
                        x++;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return s;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG,s);
                        tv_num.setText(s);
                    }
                });


            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //写法二
                Observable.create(new Observable.OnSubscribe<List<String>>() {
                    @Override
                    public void call(Subscriber<? super List<String>> subscriber) {
                        list = getResultFromDb();
                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<String>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(List<String> strings) {

                            }
                        });
            }
        });
    }

    private List<String> getResultFromDb() {
        list.clear();
        for (int i = 0; i < 10; i++) {
            list.add("item"+i);
        }
        return list;
    }

}
