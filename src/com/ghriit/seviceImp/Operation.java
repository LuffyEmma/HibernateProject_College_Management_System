package com.ghriit.seviceImp;

import java.util.List;
import java.util.Scanner;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ghriit.config.HibernateUtil;
import com.ghriit.model.Batch;
import com.ghriit.model.Course;
import com.ghriit.model.Faculty;
import com.ghriit.model.Student;
import com.ghriit.service.Service;

public class Operation implements Service{
	Scanner sc =new Scanner(System.in);


	@SuppressWarnings("unchecked")
	@Override
	public void addCourse() {
		// TODO Auto-generated method stub
		//	call session and session factory
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Course> query = null;
		boolean flag =true;
		while(flag) {
			System.out.println("Press 1 For Insert Course Details :"+"\n"
					+"Press 2 For Retrieve Course Details :"+"\n"
					+"Press 3 For Update Course Details :"+"\n"
					+"Press 4 For Delete Course Details :"+"\n"
					+"Press 5 For GoTo Main Menu");
			System.out.println("Enter Your CHOICE :");
			int ch=sc.nextInt();
			switch (ch) {
			case 1:
				//	create course object and insert data
				Course c =new Course();
				System.out.println("Enter Course ID: ");
				c.setCid(sc.nextInt());
				System.out.println("Enter Course Name:");
				c.setCname(sc.next());
				//  Save session
				session.save(c);
				session.beginTransaction().commit();

				break;
			case 2:
				//Retrieve all  Course
				viewCourse();

				break;
			case 3:
				//Update course name using ID
				System.out.println("Enter Course ID You Want To UPDATE:");
				int id=sc.nextInt();
				System.out.println("Enter Course Name:");
				String nm=sc.next();
				Transaction tx=session.beginTransaction();
				query = session.getNamedQuery("update_course");
				query.setParameter("id", id);
				query.setParameter("nm", nm);
				query.executeUpdate();
				tx.commit();
				break;
			case 4:
				//	Delete course using ID
				System.out.println("Enter Course ID You Want To DELETE:");
				int id1=sc.nextInt();
				Transaction tx1=session.beginTransaction();
				query = session.createNamedQuery("delete_course");
				query.setParameter("id", id1);
				query.executeUpdate();
				tx1.commit();
				break;
			case 5:
				//terminate while loop
				flag =false;
				session.close();
				break;
			default:
				System.out.println("InVaLID INpuT...");
				break;
			}
		}
	}

	@Override
	public void viewCourse() {
		// TODO Auto-generated method stub
		//	Retrieve all  Course
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Course> query = session.createNamedQuery("select_course",Course.class);
		List<Course> list =query.getResultList();
		for(Course cou:list) {
			System.out.println("--------------------------------------------------");
			System.out.println("Course ID: "+cou.getCid()+"\t"+"Course Name: "+cou.getCname());
			System.out.println("--------------------------------------------------");
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public void addFaculty() {
		// TODO Auto-generated method stub
		//	call session and session factory
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Faculty> query =null;
		boolean flag =true;
		while(flag) {
			System.out.println("Press 1 For Insert Faculty Details :"+"\n"
					+"Press 2 For Retrieve Faculty Details :"+"\n"
					+"Press 3 For Update Faculty Details :"+"\n"
					+"Press 4 For Delete Faculty Details :"+"\n"
					+"Press 5 For GoTo Main Menu");
			System.out.println("Enter Your CHOICE :");
			int ch=sc.nextInt();
			switch (ch) {
			case 1:
				//create faculty object and insert data
				Faculty f =new Faculty();
				System.out.println("Enter Faculty ID: ");
				f.setFid(sc.nextInt());
				System.out.println("Enter Faculty Name:");
				f.setFname(sc.next());
				System.out.println("************************************");
				System.out.println("Select one of the course shown below: ");
				viewCourse();
				System.out.println("************************************");
				System.err.println("Enter Course ID :");
				int id = sc.nextInt();
				//insert course ID to faculty				
				Course c=session.get(Course.class, id);
				if(c != null) {

					f.setCourse(c);
					// Save session
					session.save(f);
					session.beginTransaction().commit();
				}
				else {
					System.out.println("Course ID NOT Matched...");
				}
				break;

			case 2:
				//	Retrieve all Faculty
				viewFaculty();
				break;
			case 3:
				//Update faculty name using ID
				System.out.println("Enter Faculty ID You Want To UPDATE:");
				int id1=sc.nextInt();
				System.out.println("Enter Faculty Name:");
				String nm=sc.next();
				Transaction tx=session.beginTransaction();
				query = session.getNamedQuery("update_faculty");
				query.setParameter("id", id1);
				query.setParameter("nm", nm);
				query.executeUpdate();
				tx.commit();
				break;
			case 4:
				//delete faculty name using ID
				System.out.println("Enter Faculty ID You Want To DELETE:");
				int id2=sc.nextInt();
				Transaction tx1=session.beginTransaction();
				query = session.getNamedQuery("delete_faculty");
				query.setParameter("id", id2);
				query.executeUpdate();
				tx1.commit();
				break;
			case 5:
				//terminate while loop
				flag =false;
				session.close();
				break;
			default:
				System.out.println("InVaLID INpuT...");
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void viewFaculty() {
		// TODO Auto-generated method stub
		//	call session and session factory
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Faculty> query =session.getNamedQuery("select_faculty"); 
		List<Faculty> list =query.getResultList();
		for(Faculty fc:list) {
			System.out.println("--------------------------------------------------");
			System.out.println("Faculty ID: "+fc.getFid()+"\t"+"Faculty Name: "+fc.getFname()
			+"\t"+"Course ID: "+fc.getCourse().getCid()+"\t"+"Course Name: "+fc.getCourse().getCname());
			System.out.println("--------------------------------------------------");

		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void addBatch() {
		// TODO Auto-generated method stub
		//	call session and session factory
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Batch> query =null;

		boolean flag =true;
		while(flag) {
			System.out.println("Press 1 For Insert Batch Details :"+"\n"
					+"Press 2 For Retrieve Batch Details :"+"\n"
					+"Press 3 For Update Batch Details :"+"\n"
					+"Press 4 For Delete Batch Details :"+"\n"
					+"Press 5 For GoTo Main Menu");
			System.out.println("Enter Your CHOICE :");
			int ch=sc.nextInt();
			switch (ch) {
			case 1:
				//	create batch object and insert data
				Batch b =new Batch();
				System.out.println("Enter Batch ID: ");
				b.setBid(sc.nextInt());
				System.out.println("Enter Batch Name:");
				b.setBname(sc.next());
				// Save session
				System.out.println("************************************");
				System.out.println("Select one of the Faculty shown below: ");
				viewFaculty();
				System.out.println("************************************");
				System.err.println("Enter Faculty ID :");
				int id = sc.nextInt();
				//insert Faculty ID to Batch	
				Faculty f=session.get(Faculty.class, id);
				if(f != null) {

					b.setFaculty(f);
					// Save session
					session.save(b);
					session.beginTransaction().commit();
				}
				else {
					System.out.println("Faculty ID NOT Matched...");
				}

				break;
			case 2:
				//	Retrieve all  batch
				viewBatch();

				break;
			case 3:
				//Update batch name using ID
				System.out.println("Enter Batch ID You Want To UPDATE:");
				int id1=sc.nextInt();
				System.out.println("Enter Batch Name:");
				String nm=sc.next();
				Transaction tx=session.beginTransaction();
				query = session.getNamedQuery("update_batch");
				query.setParameter("id", id1);
				query.setParameter("nm", nm);
				query.executeUpdate();
				tx.commit();
				break;
			case 4:
				//Delete batch name using ID
				System.out.println("Enter Batch ID You Want To DELETE:");
				int id2=sc.nextInt();
				Transaction tx1=session.beginTransaction();
				query = session.getNamedQuery("delete_batch");
				query.setParameter("id", id2);
				query.executeUpdate();
				tx1.commit();
				break;
			case 5:
				//terminate while loop
				flag =false;
				session.close();
				break;
			default:
				System.out.println("InVaLID INpuT...");
				break;
			}
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void viewBatch() {
		//	Retrieve all  batch
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Batch> query =session.getNamedQuery("select_batch"); 
		List<Batch> list =query.getResultList();
		for(Batch bt:list) {
			System.out.println("--------------------------------------------------");
			System.out.println("Batch ID: "+bt.getBid()+" |"+"Batch Name: "+bt.getBname()
			+" |"+"Faculty ID: "+bt.getFaculty().getFid()+" |"+"Faculty Name: "+bt.getFaculty().getFname()
			+" |"+"Course ID: "+bt.getFaculty().getCourse().getCid()+" |"+"Course Name: "+bt.getFaculty().getCourse().getCname());
			System.out.println("--------------------------------------------------");

		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void addStudent() {
		// TODO Auto-generated method stub
		//call session and session factory
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Student> query =null;

		boolean flag =true;
		while(flag) {
			System.out.println("Press 1 For Insert Student Details :"+"\n"
					+"Press 2 For Retrieve Student Details :"+"\n"
					+"Press 3 For Update Student Details :"+"\n"
					+"Press 4 For Delete Student Details :"+"\n"
					+"Press 5 For GoTo Main Menu");
			System.out.println("Enter Your CHOICE :");
			int ch=sc.nextInt();
			switch (ch) {
			case 1:
				//create student object and insert data
				Student s =new Student();
				System.out.println("Enter Student ID: ");
				s.setSid(sc.nextInt());
				System.out.println("Enter Student Name:");
				s.setSname(sc.next());				
				System.out.println("************************************");
				System.out.println("Select one of the Batch shown below: ");
				viewBatch();
				System.out.println("************************************");
				System.err.println("Enter Batch ID:");
				int id = sc.nextInt();
				//insert Batch ID to Student	
				Batch b=session.get(Batch.class, id);
				if(b != null) {
					s.setBatch(b);
					// Save session
					session.save(s);
					session.beginTransaction().commit();
				}
				else {
					System.out.println("Batch ID NOT Matched...");
				}

				break;
			case 2:
				//	Retrieve all  Student
				viewStudent();
				break;
			case 3:
				//Update student name using ID
				System.out.println("Enter Student ID You Want To UPDATE:");
				int id1=sc.nextInt();
				System.out.println("Enter Student Name:");
				String nm=sc.next();
				Transaction tx=session.beginTransaction();
				query = session.getNamedQuery("update_student");
				query.setParameter("id", id1);
				query.setParameter("nm", nm);
				query.executeUpdate();
				tx.commit();
				break;
			case 4:
				//delete student name using ID
				System.out.println("Enter Student ID You Want To DELETE:");
				int id2=sc.nextInt();
				Transaction tx1=session.beginTransaction();
				query = session.getNamedQuery("delete_student");
				query.setParameter("id", id2);
				query.executeUpdate();
				tx1.commit();
				break;
			case 5:
				//terminate while loop
				flag =false;
				session.close();
				break;
			default:
				System.out.println("InVaLID INpuT...");
				break;
			}
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void viewStudent() {
		// Retrieve all  student
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Student> query =session.getNamedQuery("select_student"); 
		List<Student> list =query.getResultList();
		for(Student st:list) {
			System.out.println("--------------------------------------------------");
			System.out.println("Student ID: "+st.getSid()+" |"+"Student Name: "+st.getSname()
			+" | "+"Batch ID: "+st.getBatch().getBid()+" |"+"Batch Name: "+st.getBatch().getBname()
			+" |"+"Faculty ID: "+st.getBatch().getFaculty().getFid()+" |"+"Faculty Name: "+st.getBatch().getFaculty().getFname()
			+" |"+"Course ID: "+st.getBatch().getFaculty().getCourse().getCid()+"| "+"Course Name: "+st.getBatch().getFaculty().getCourse().getCname());
			System.out.println("--------------------------------------------------");

		}
	}
}
