package com.lili.thread2;

import java.util.ArrayList;
import java.util.List;

public class HappyCinema2 {
	 public static void main(String[] args) {
		 //���ǳƥi�Φ�m
		 List<Integer> available = new ArrayList<Integer>();
		 for(int i=1; i<=10; i++) {
			 available.add(i);
		 }
		 
		 //�ǳ��U��12�n����m 
		 List<Integer> Custom1 = new ArrayList<Integer>();
		 //�L��F1,2����
		 Custom1.add(1);
		 Custom1.add(2);
		 
		//�ǳ��U��2�n����m 
		 List<Integer> Custom2 = new ArrayList<Integer>();
		 //�L��F4,5,6����
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
				System.out.println("�X�����\ - " + Thread.currentThread().getName() + " == ��m��: " + seats);
			}
			else {
				System.out.println("�X������ - " + Thread.currentThread().getName() + " == ��m����");
			}
		}
		
		System.out.println("�b�l��m:" + cinema.getAvailable());
		System.out.println();
	}
}

class SxtCinema{
	List<Integer> available;  //�i�Φ�m
	String name;              //�W��
	
	public List<Integer> getAvailable() {
		return available;
	}

	public SxtCinema(List<Integer> available, String name) {
		this.available = available;
		this.name = name;
	}
	
	//Buy function
	public boolean bookTickets(List<Integer> seat) {
		//����available Copy �@���@���
		List<Integer> copy = new ArrayList<Integer>();
		copy.addAll(available);   //List��copy
		
		//�p�i�Φ�m�]�t
		boolean flag = available.containsAll(seat);
		if(flag) {
			//��copy����seat���X
			copy.removeAll(seat);
			//��b�l��m���Vcopy
			available = copy;
			return true;
		}
		else {
			return false;
		}
	}
}