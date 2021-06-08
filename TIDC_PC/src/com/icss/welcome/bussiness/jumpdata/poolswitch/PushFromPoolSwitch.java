package com.icss.welcome.bussiness.jumpdata.poolswitch;

import java.util.concurrent.atomic.AtomicBoolean;

import com.icss.welcome.bussiness.jumpdata.JunpDataFromPoolJob;


public class PushFromPoolSwitch {
	private static AtomicBoolean fromPoolSwitch = new AtomicBoolean();
	public static boolean getState(){
		return fromPoolSwitch.get();
	}
	public static void changeState(){
		boolean b = fromPoolSwitch.get();
		fromPoolSwitch.getAndSet(b ? false : true);
		Thread t = new Thread(new JunpDataFromPoolJob());
		t.start();
	}
}
