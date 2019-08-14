package com.lili.thread2;

import java.util.ArrayList;
import java.util.List;

public class HappyCinema2 {
	 public static void main(String[] args) {
		 //先準備可用位置
		 List<Integer> available = new ArrayList<Integer>();
		 for(int i=1; i<=10; i++) {
			 available.add(i);
		 }
		 
		 //準備顧客12要的位置 
		 List<Integer> Custom1 = new ArrayList<Integer>();
		 //他選了1,2號位
		 Custom1.add(1);
		 Custom1.add(2);
		 
		//準備顧客2要的位置 
		 List<Integer> Custom2 = new ArrayList<Integer>();
		 //他選了4,5,6號位
		 Custom2.add(7);
		 Custom2.add(5);
		 Custom2.add(6);
		 
		  //New Cinema
		 SxtCinema c = new SxtCinema(available, "SxtCinema");
		 new Thread(new HpCustomer(c,Custom1),"Ella").start();
		 new Thread(new HpCustomer(c,Custom2),"LiLiTam").start();
		 
	}
}

class HpCustomer implements Runnable{
	SxtCinema cinema;
	List<Integer> seats;
	
	public HpCustomer(SxtCinema cinema, List<Integer> seats) {
		this.cinema = cinema;
		this.seats = seats;
	}

	@Override
	public void run() {
		synchronized(cinema) {

			boolean flag = cinema.bookTickets(seats);
			if(flag) {
				System.out.println("出票成功 - " + Thread.currentThread().getName() + " == 位置為: " + seats);
			}
			else {
				System.out.println("出票失敗 - " + Thread.currentThread().getName() + " == 位置不足");
			}
		}
		
		System.out.println("淨餘位置:" + cinema.getAvailable());
		System.out.println();
	}
}

class SxtCinema{
	List<Integer> available;  //可用位置
	String name;              //名稱
	
	public List<Integer> getAvailable() {
		return available;
	}

	public SxtCinema(List<Integer> available, String name) {
		this.available = available;
		this.name = name;
	}
	
	//Buy function
	public boolean bookTickets(List<Integer> seat) {
		//先把available Copy 一份作比較
		List<Integer> copy = new ArrayList<Integer>();
		copy.addAll(available);   //List的copy
		
		//如可用位置包含
		boolean flag = available.containsAll(seat);
		if(flag) {
			//把copy中的seat拿出
			copy.removeAll(seat);
			//把淨餘位置指向copy
			available = copy;
			return true;
		}
		else {
			return false;
		}
	}
}