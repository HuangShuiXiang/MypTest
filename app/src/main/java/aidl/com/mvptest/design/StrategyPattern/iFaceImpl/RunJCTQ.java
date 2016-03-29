package aidl.com.mvptest.design.StrategyPattern.iFaceImpl;

import android.util.Log;

import aidl.com.mvptest.design.StrategyPattern.iFace.IRunBehavior;

/**
 * Created by Administrator on 2016/3/23.
 */
public class RunJCTQ implements IRunBehavior{
    @Override
    public void run() {
        Log.i("run","金蝉脱壳");
    }
}
