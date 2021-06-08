package com.icss.welcome.bussiness.jumpdata.poolswitch;

import java.util.concurrent.atomic.AtomicBoolean;

import com.icss.welcome.bussiness.jumpdata.JumpDataFromPool;


public class PushToPoolSwitch {
	private static AtomicBoolean toPoolSwitch = new AtomicBoolean();
	public static boolean getState(){
		return toPoolSwitch.get();
	}
	public static void changeState(){
		boolean b = toPoolSwitch.get();
		toPoolSwitch.getAndSet(b ? false : true);
		if(!b && PushFromPoolSwitch.getState()){
			PushFromPoolSwitch.changeState();
		}else if(!b){
			new JumpDataFromPool().delData(null);
		}
	}
}
