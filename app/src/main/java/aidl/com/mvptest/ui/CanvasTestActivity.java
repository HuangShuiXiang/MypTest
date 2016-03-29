package aidl.com.mvptest.ui;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import aidl.com.mvptest.R;
import aidl.com.mvptest.view.MoveTestView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class CanvasTestActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "CanvasTestActivity";
    private aidl.com.mvptest.view.CanvasView view;
    private Button start_add;
    private Button start_subtract;
    private Button sweep_add;
    private Button sweep_subtract;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_test);
        initView();
    }

    private void initView() {
        view = (aidl.com.mvptest.view.CanvasView) findViewById(R.id.view);
        start_add = (Button) findViewById(R.id.start_add);
        start_subtract = (Button) findViewById(R.id.start_subtract);
        sweep_add = (Button) findViewById(R.id.sweep_add);
        sweep_subtract = (Button) findViewById(R.id.sweep_subtract);
        start_add.setOnClickListener(this);
        start_subtract.setOnClickListener(this);
        sweep_add.setOnClickListener(this);
        sweep_subtract.setOnClickListener(this);
    }
    int start = 0;
    int sweep = 180;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_add:
                start+=10;
                view.setStartAngle(start);
            break;
            case R.id.start_subtract:
                start-=10;
                view.setStartAngle(start);
            break;
            case R.id.sweep_add:
                sweep+=10;
                view.setStartAngle(sweep);
            break;
            case R.id.sweep_subtract:
                sweep-=10;
                view.setStartAngle(sweep);
            break;
        }
        view.invalidate();
    }
}
