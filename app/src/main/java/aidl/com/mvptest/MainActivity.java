package aidl.com.mvptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.materlistview.CrashHandler.ServerLogActivity;
import com.materlistview.cardModel.Card;
import com.materlistview.cardModel.OnButtonPressListener;
import com.materlistview.materListView.MaterialListView;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;

import aidl.com.mvptest.card.cardModel.ItemCard;
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
import aidl.com.mvptest.ui.AnimatorTestActivity;
import aidl.com.mvptest.ui.CanvasTestActivity;
import aidl.com.mvptest.ui.DesignTestActivity;
import aidl.com.mvptest.ui.RxJavaTestActivity;
import aidl.com.mvptest.util.ToastUitl;
import aidl.com.mvptest.view.ILoginView;
import aidl.com.mvptest.view.MoveTestView;

public class MainActivity extends AppCompatActivity {
    private MaterialListView material_ListView;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        material_ListView = (MaterialListView) findViewById(R.id.material_ListView);
        fillArrayList();
    }

    /**
     * 填充卡片
     */
    private void fillArrayList() {
        initList();
        for (int i = 0; i < list.size(); i++) {
            ItemCard card = new ItemCard(this);
            card.setName(list.get(i));
            card.setPosition(i);
            card.setOnAddFreshUIPressedListener(new OnButtonPressListener() {
                @Override
                public void onButtonPressedListener(View view, Card card) {
                    ItemCard card1 = (ItemCard)card;
                    switch (card1.getPosition()){
                        case 0:
                            startActivity(new Intent(MainActivity.this, DesignTestActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(MainActivity.this, AnimatorTestActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(MainActivity.this, RxJavaTestActivity.class));
                            break;
                        case 3:
                            startActivity(new Intent(MainActivity.this, CanvasTestActivity.class));
                            break;
                    }
                }
            });
            material_ListView.add(card);
        }
    }

    private void initList() {
        list.add("设计模式test");
        list.add("属性动画test");
        list.add("RxJava test");
        list.add("Canvas类 test");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "请求/响应日志");
        menu.add(1, 1, 1, "crash闪退日志");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent inient = new Intent(this,
                ServerLogActivity.class);
        inient.putExtra(ServerLogActivity.APPNAME, "mvp_test");
        switch (item.getItemId()) {
            case 0:
                startActivity(inient);
                break;
            case 1:
                inient.putExtra("crash", "crash");
                startActivity(inient);
                break;
        }

        return true;
    }
}
