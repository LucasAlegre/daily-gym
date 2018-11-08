package com.grupo06.dailygym.smartwatch.facade;

import com.grupo06.dailygym.smartwatch.*;

public class SmartWatchFacade {
	
	private ISmartWatch smartWatch;
	
	public SmartWatchFacade(){
		smartWatch = new SmartWatch();
	}
}
