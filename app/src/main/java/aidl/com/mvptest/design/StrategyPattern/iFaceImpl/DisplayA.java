package aidl.com.mvptest.design.StrategyPattern.iFaceImpl;

import android.util.Log;

import aidl.com.mvptest.design.StrategyPattern.iFace.IDisplayBehavior;

/**
 * Created by Administrator on 2016/3/23.
 */
public class DisplayA implements IDisplayBehavior{
    @Override
    public void display() {
        Log.i("display","炫耀A");
    }
}
