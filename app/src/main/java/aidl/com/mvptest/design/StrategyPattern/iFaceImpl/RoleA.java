package aidl.com.mvptest.design.StrategyPattern.iFaceImpl;

import aidl.com.mvptest.design.StrategyPattern.iFace.Role;

/**
 * Created by Administrator on 2016/3/23.
 */
public class RoleA extends Role{
    public RoleA(String name){
        this.name = name;
    }
}
