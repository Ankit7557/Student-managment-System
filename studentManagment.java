//Write a program to create a student database following conditions need to be taken care of:
//1. You will consider two user one teacher and other is the student 
//   The work of teacher is to add and update the student database. 
//   The work of a student is simply is to access his own database only. 
//   Database includes student ID student name, age, gender, branch and marks.
//   Student Id must be unique. 
//2.If student try to access any other student database a message will be displayed "you are not authorised to accessed your database". 
//3.To access his or her database student have to enter the Id and a password that includes first 4 letter of the first name and the DOB in the following format "ritu2110".
// Author: Ankit Prajapati


import java.util.Scanner;
import java.util.TreeMap;

public class studentManagment {
	private static TreeMap<String, Student> db=new TreeMap<String,Student>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int isExit=0;
		
		System.out.println("Hey, there! Welcome\n");
		System.out.println("Are you a teacher? (Y/N)");
		boolean isTeacher=sc.nextLine().toUpperCase().charAt(0)=='Y';
		if(isTeacher) {
			teacher(sc);
			System.out.println("Do You want to Continue?(Press 0 to exit,1 to continue)");
			isExit=sc.nextInt();
			do {
			switch(isExit) {
			case 0:
				System.out.println("Thanks for choosing our Database Management System");
				break;
			case 1: 
				teacher(sc);
				}
			}while(isExit!=0);
			
		}
		else {
			student(sc);
			System.out.println("Do You want to Continue?(Press 0 to exit");
		
			
			do {
					
				 isExit=sc.nextInt();
				
			switch(isExit) {
			case 0:
				System.out.println("Thanks for choosing our Database Management System");
				return;
			
				}
			}while(isExit!=0);
			
		
			sc.close();
		}
	}
	
	private static void teacher(Scanner sc) {
		do {
			System.out.println("Admin Menu\n1. Add a Student\n2. Update a Student\n3. Remove a Student\n4. View a Student\n0. Exit");
			int choice=sc.nextInt();
			switch(choice) {
			case 0:
				System.out.println("Thanks for choosing our Database Management System");
				return;
			case 1://add a student
				addStudentToDB(sc);
				break;
			case 2://update a student
				updateStudentToDB(sc);
				break;
			case 3://remove a student
				removeStudentToDB(sc);
			case 4://view a student details
				System.out.println("Enter student id to view details: ");
				sc.nextLine();
				String id=sc.nextLine();
				if(db.containsKey(id)) {
					System.out.println("Details of the Student:\n"+db.get(id).toString());
				}else {
					System.out.println("No such Student exists!");
				}
				break;
			default:
				break;
			
			}
		}while(true);
		
	}
	private static void addStudentToDB(Scanner sc) {
		System.out.println("Enter Student Details to add");
		System.out.println("Id:");
		sc.nextLine();
		String id=sc.nextLine();
		if(db.containsKey(id)) {
			System.out.println("Student id already exists!");
			return;
		}else {
		System.out.println("Name");
		String name=sc.nextLine();
		
		System.out.println("Date of Birth(ddmmyyyy)");
		String dob=sc.nextLine();
		
		System.out.println("Age:");
		int age=sc.nextInt();
		
		System.out.println("Gender(M/F):");
		char g=sc.next().toUpperCase().charAt(0);
		
		System.out.println("Branch:");
		String branch=sc.next();
		
		System.out.println("Marks:");
		double marks=sc.nextDouble();
		
		Student s=new Student(id,name,dob,age,g,branch,marks);
		db.put(id,s);
	
		}	
	}
	
	private static void updateStudentToDB(Scanner sc) {
		System.out.println("Enter the Student ID to update details:");
		sc.nextLine();
		String id=sc.nextLine();
		if(db.containsKey(id)) {
			System.out.println("What do u want to update?\n1. Namw\n2. Age\n3. Date of Birth\n4. Branch\n5. Marks");
			switch(sc.nextInt()) {
			case 1://name
				System.out.println("Enter new Name");
				String name=sc.nextLine();
				db.get(id).updateName(name);
				break;
			
			case 2://age
				System.out.println("Enter new Age");
				int age=sc.nextInt();
				db.get(id).updateAge(age);
				break;
				
			case 3: //date of birth
				System.out.println("Enter new Date of Birth(ddmmyyyy)");
				String dob=sc.nextLine();
				db.get(id).updateDob(dob);
				break;
				
			case 4: //branch
				System.out.println("Enter new Branch");
				String b=sc.nextLine();
				db.get(id).updateBranch(b);
				break;
				
			case 5: //marks
				System.out.println("Enter new marks");
				Double m=sc.nextDouble();
				db.get(id).updateMarks(m);
				break;
				
			default:
				break;
				
			}
		}else {
			System.out.println("No such Student exists!");
		}
	}
	
	private static void removeStudentToDB(Scanner sc) {
		System.out.println("Enter the Student Id to remove from DB");
		sc.nextLine();
		String id=sc.nextLine();
		if(db.containsKey(id)) {
			db.remove(id);
		}else {
			System.out.println("no such Student exists!");
		}
	}
	
	private static void student(Scanner sc) {
		System.out.println("Enter your id:");
		String id=sc.nextLine();
		if(!db.containsKey(id)) {
			System.out.println("no such Student exist!");
			return;
		}
		System.out.println("Enter your password(NameDob)");
		String password=sc.nextLine();
		String dbpassword=db.get(id).getFirstName().toLowerCase().length()> 4 
					? db.get(id).getFirstName().toLowerCase().substring(0,4): db.get(id).getFirstName().toLowerCase()+""+db.get(id).getDOB().substring(0,4);
		
		if(dbpassword.compareTo(password)==0) {
			System.out.println("Valid Login!");
			System.out.println("\nDetails of Student:\n"+db.get(id).toString());	
		}else {
			System.err.println("Invaild Password");
		}
	
	}
	
	
}
class Student{
	private String id;
	private String name;
	private String dob;
	private int age;
	private char gender;
	private String branch;
	private double marks;
	Student(String id,String name,String dob,int age,char gender,String branch,double marks){
		this.id=id;
		this.name=name;
		this.dob=dob;
		this.age=age;
		this.gender=gender;
		this.branch=branch;
		this.marks=marks;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDOB() {
		return  dob;
	}
	
	public String getFirstName() {
		if(name.contains(" ")) {
			return name.split(" ")[0];
		}
		else {
			return name;
		}
	}
	
		public void updateName(String n) {
			this.name=n;
		}
		
		public void updateDob(String dob)
		{
			this.dob=dob;
		}
		
		public void updateAge(int a) {
			this.age=a;
		}
		
		public void updateBranch(String b)
		{
			this.branch=b;
		}
		
		public void updateMarks(double m) {
			this.marks=m;
		}
		@Override
		public String toString() {
			String s = "Id:\t" + id + "\nName:\t" + name + "\nAge:\t" + age + "\nGender:\t" + gender + "\nBranch:\t"
	                + branch + "\nMarks:\t" + marks;
	        return s;
		}
			
}