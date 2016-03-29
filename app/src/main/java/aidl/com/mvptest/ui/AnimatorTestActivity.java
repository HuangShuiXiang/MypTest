package aidl.com.mvptest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

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

public class AnimatorTestActivity extends AppCompatActivity implements ILoginView {
    private EditText ed_user;
    private EditText ed_pwd;
    private Button btn_login;
    private Button btn_clear;
    private ILoginPresenter loginPresenter;
    private MoveTestView tv_num;
    AnimatorSet animatorSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_test);
        ed_user = (EditText) findViewById(R.id.ed_user);
        ed_pwd = (EditText) findViewById(R.id.ed_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        tv_num = (MoveTestView) findViewById(R.id.tv_num);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.doLogin(ed_user.getText().toString(),ed_pwd.getText().toString());
                //透明度
                ObjectAnimator animator = ObjectAnimator.ofFloat(btn_login,"alpha",1f,0,1f);
                animator.setDuration(2000);
                animator.start();

//                ValueAnimator animator1 = ValueAnimator.ofInt(0,100);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(btn_login,"rotationX",0,180);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(btn_login,"rotationY",0,180);
//                ObjectAnimator animator2 = ObjectAnimator.ofFloat(btn_login,"scaleX",1f,3f,1f);
//                ObjectAnimator animator3 = ObjectAnimator.ofFloat(btn_login,"scaleY",1f,3f,1f);
//                Animator animator2 = AnimatorInflater.loadAnimator(x.this,R.animator.value_animator);
//                animator2.setTarget(tv_num);
//                animator2.start();
                animatorSet = new AnimatorSet();
                animatorSet.setDuration(10000);
                animatorSet.play(animator).before(animator2).after(animator3);
                animatorSet.start();
               /* animator1.setDuration(1000);
                animator1.start();
                animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                       tv_num.setText(animation.getAnimatedValue() +"");
                    }
                });*/
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.clear();
                //旋转
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(btn_clear,"rotation",0f,360f);
                animator1.setRepeatMode(ObjectAnimator.REVERSE);
                animator1.setInterpolator(new BounceInterpolator());
                animator1.setRepeatCount(-1);
//                animator1.setDuration(2000);
//                animator1.start();
                //平移
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(btn_clear,"translationX",btn_clear.getTranslationX(),-500,btn_clear.getTranslationX());
                animator2.setRepeatMode(ObjectAnimator.REVERSE);
                animator2.setInterpolator(new BounceInterpolator());
                animator2.setRepeatCount(-1);
//                animator2.setDuration(2000);
//                animator2.start();
                animatorSet = new AnimatorSet();
                animatorSet.play(animator1).with(animator2);
                animatorSet.setDuration(2000);
                animatorSet.start();
//                btn_clear.animate().alpha(0f).x(-btn_clear.getTranslationX()).alpha(1).x(btn_clear.getTranslationX()).rotation(360).setDuration(5000);
            }
        });
        loginPresenter = new LoginPresenterImpl(this);

        //装饰者模式
        IEquip iEquip = new BlueGemDecorator(new RedGemDecorator(new BlueGemDecorator(new ArmEquip())));
        ToastUitl.showShort(this,"攻击力" + iEquip.calculateAttack());
        ToastUitl.showShort(this,"描述" + iEquip.description());

        //策略模式
        Role role = new RoleA("张无忌");
        role.setAttackBehavior(new AttackJYSG())
        .setDefendBehavior(new DefendJZZ())
        .setDisplayBehavior(new DisplayA())
        .setRunBehavior(new RunJCTQ());
        role.attack();
        role.defend();
        role.display();
        role.run();

        //工厂模式

        //观察者模式

        //mvc模式

        //mvp模式

        //mvvm模式
    }

    @Override
    public void loginResult(String code) {
        if(code .equals("0")){
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void clearText() {
        ed_pwd.setText("");
        ed_user.setText("");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(animatorSet != null){
            animatorSet.cancel();
        }
    }
}
