package org.sumaciudadana.affidavit.util;

public enum EnumWealth {
	
	RANGO0(0,0),
	RANGO1(0, 50000), 
	RANGO2(50001, 250000), 
	RANGO3(250001, 500000), 
	RANGO4(500001, 1000000), 
	RANGO5(1000001, 5000000), 
	RANGO6(5000001, 0);

	private long minWealth;
	private long maxWealth;

	public long getMinWealth() {
		return minWealth;
	}

	public long getMaxWealth() {
		return maxWealth;
	}

	EnumWealth(long min, long max) {
		this.minWealth = min;
		this.maxWealth = max;
	}
	
	
	

}
