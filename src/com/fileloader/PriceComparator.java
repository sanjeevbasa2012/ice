package com.fileloader;

import java.util.Comparator;

public class PriceComparator implements Comparator<Data> {

	@Override
	public int compare(Data data1, Data data2) {
		int val = data1.getPrice().compareTo(data2.getPrice());
		//System.out.println(data1.getPrice()+", "+data2.getPrice()+", "+val);
		return val;
	}
	
	

}
