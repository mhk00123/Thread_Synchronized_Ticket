package com.lili.thread2;

public class HappyCinema {
	 public static void main(String[] args) {
		 //�s�W���|
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
				System.out.println("�X�����\" + Thread.currentThread().getName() + "--> ��m��: " + ticketNum);
			}
			else {
				System.out.println("�X������" + Thread.currentThread().getName() + "--> ��m���� ");
			}
		}
	}
	 
 }


class Cinema{
	int seat; //�i�H������m
	String name;   //�W��
	
	//Constructor
	public Cinema(int seat, String name) {
		this.seat = seat;
		this.name = name;
	}
	
	//�ʲ��O�_���\
	public boolean bookTickets(int ticketNum) {
		System.out.println("�i�Φ�m��: " + seat);
		
		//�p�G�ʲ��ƶq > �b�l��m
		if(ticketNum > seat) {
			return false;
		}
		else {
			seat = seat - ticketNum;
			return true;
		}
		
		
	}
}