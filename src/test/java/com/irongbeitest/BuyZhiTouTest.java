package com.irongbeitest;

import com.irongbei.BuyZhiTou;

public class BuyZhiTouTest {
public static void main(String[] args) throws InterruptedException {
	BuyZhiTou bzt=new BuyZhiTou();
	for(int i=0;i<900;i++) {
	bzt.buyZhiTou();
	Thread.sleep(1000);
}
}
}