package com.lili.thread2;

public class HappyCinema {
	 public static void main(String[] args) {
		 //新增戲院
		Cinema c = new Cinema(2, "HappyCinena");
		
		//Customer
		new Thread(new Customer(c, 2),"Ella").start();
		new Thread(new Customer(c, 3),"Poge").start();
	}
}

 class Customer implements Runnable{
	 Cinema cinema;
	 int ticketNum; 
	 
	 //Constructor
	 public Customer(Cinema cinema, int ticketNum) {
		super();
		this.cinema = cinema;
		this.ticketNum = ticketNum;
	}

	@Override
	public void run() {
		synchronized(cinema) {
			boolean flag = cinema.bookTickets(ticketNum);
			if(flag) {
				System.out.println("出票成功" + Thread.currentThread().getName() + "--> 位置為: " + ticketNum);
			}
			else {
				System.out.println("出票失敗" + Thread.currentThread().getName() + "--> 位置不夠 ");
			}
		}
	}
	 
 }


class Cinema{
	int seat; //可以坐的位置
	String name;   //名稱
	
	//Constructor
	public Cinema(int seat, String name) {
		this.seat = seat;
		this.name = name;
	}
	
	//購票是否成功
	public boolean bookTickets(int ticketNum) {
		System.out.println("可用位置為: " + seat);
		
		//如果購票數量 > 淨餘位置
		if(ticketNum > seat) {
			return false;
		}
		else {
			seat = seat - ticketNum;
			return true;
		}
		
		
	}
}