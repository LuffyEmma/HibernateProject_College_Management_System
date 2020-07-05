package com.ghriit.client;

import java.util.Scanner;

import com.ghriit.seviceImp.Operation;

public class Test {
	public static void main(String[] args) {

		Operation op= new Operation();
		Scanner sc =new Scanner(System.in);
		boolean flag =true;
		while(flag) {
			System.out.println("Enter 1 for Cource Data"					
					+"\n"+"Enter 2 for Faculty Data"					
					+"\n"+"Enter 3 for Batch Data"				
					+"\n"+"Enter 4 for Student Data");
			System.out.println("Enter 5 For Exit");
			System.out.println("**************************");
			System.out.println("Enter Your Choice :");
			int ch =sc.nextInt();
			switch (ch) {
			case 1:
				op.addCourse();
				break;
			case 2:
				op.addFaculty();
				break;
			case 3:
				op.addBatch();
				break;
			case 4:
				op.addStudent();
				break;
			case 5:
				System.out.println("Thank You...!!!");
				System.exit(0);
				sc.close();
				break;
			default:
				System.out.println("InvaLID INpuT...");
				break;
			}
		}
	}

}
