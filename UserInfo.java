package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;


public class UserInfo {
	
		private static final String selectQuery = "SELECT * from employee";
		private static final String specificQuery = "SELECT * from employee where emp_id = ?";
		private static final String insertQuery = "INSERT into employee (emp_id,emp_name,dept,salary) values(?,?,?,?)";
		private static final String deleteQuery = "DELETE from employee where emp_id = ?";
		private static final String updateQuery = "UPDATE employee set salary = ? where emp_id = ?";
		private static ResultSet res = null;
		static Connection con = null;
		private static Statement stmt = null;
		private static PreparedStatement pstmt = null;
		static Scanner scan = new Scanner(System.in);
	
	
	public static void selectAllEmp() {
		try {
			con=MyConnect.connect();
			stmt = con.createStatement();
			res = stmt.executeQuery(selectQuery);
			
			while(res.next() == true) {
				System.out.printf("%-3d %-8s %-12s %d\n"
						,res.getInt(1)
						,res.getString(2)
						,res.getString(3)
						,res.getInt(4));
			}	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void specificEmp() {
		try {
			con = MyConnect.connect();
			pstmt  = con.prepareStatement(specificQuery);
			
			System.out.print("Enter the emp id : ");
			int emp_id = scan.nextInt();
			pstmt.setInt(1, emp_id);
			
			res = pstmt.executeQuery();
			while(res.next() == true) {
				System.out.printf("%-3d %-8s %-5s %d\n"
						,res.getInt(1)
						,res.getString(2)
						,res.getString(3)
						,res.getInt(4));
			}	
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void registerEmp() {
		
		try {
			con = MyConnect.connect();
			pstmt =con.prepareStatement(insertQuery);		
			
			System.out.print("Enter emp id to add : ");
			int emp_id = scan.nextInt();
			pstmt.setInt(1, emp_id);
			
			scan.nextLine();
			
			System.out.print("Enter emp name to add : ");
			String emp_name = scan.nextLine();
			pstmt.setString(2, emp_name);
			
			System.out.print("Enter emp Dept to add : ");
			String dept = scan.nextLine();
			pstmt.setString(3, dept);
			
			System.out.print("Enter emp salary to add : ");
			int salary = scan.nextInt();
			pstmt.setInt(4, salary);
			
			int x = pstmt.executeUpdate();
			System.out.println(x + " Employee added");
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void deleteEmp(){
		
		try {
			con = MyConnect.connect();
			pstmt = con.prepareStatement(deleteQuery);
			System.out.print("Enter emp id to delete : ");
			int emp_id = scan.nextInt();
			pstmt.setInt(1, emp_id);
			int x = pstmt.executeUpdate();
			System.out.println(x + " Employee deleted");
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void modifyEmp() {
		try {
			con = MyConnect.connect();			
			pstmt = con.prepareStatement(updateQuery);
			
			System.out.print("Enter emp id to update salary : ");
			int emp_id = scan.nextInt();
			pstmt.setInt(2, emp_id);
			
			System.out.print("Enter new salary : ");
			int salary = scan.nextInt();
			pstmt.setInt(1, salary);
		
			int x = pstmt.executeUpdate();
			System.out.println(x +" Employee updated");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	
	
	public static void main(String[] args){
		int choice = 0;
		System.out.println("Employees Information : \n");
		for(;;) {
			System.out.println("Show Employee Menu : yes/no");
			String menu = scan.nextLine();
			System.out.println();
			if(menu.equalsIgnoreCase("no")) {
				System.out.println("Thank you");
				break;
			}
			
			if(menu.equalsIgnoreCase("yes")) {
				
				System.out.println("1.Show Data of all Employees"
						+ "\n2.Show specific data"
						+ "\n3.Register User"
						+ "\n4.Delete User"
						+ "\n5.Modify User"
						+ "\n6.Exit\n");
				
				try {
					System.out.print("Enter the option : ");
					choice = scan.nextInt();
					scan.nextLine();
				}
				catch(InputMismatchException e) {
					System.out.println("Enter options number");
				}
			
				if(choice == 6) {
					System.out.println("Employees Information is closed");
				}
				else if(choice == 1) {
					selectAllEmp();
				}
				else if(choice == 2) {
					specificEmp();
				}
				else if(choice == 3) {
					registerEmp();
				}
				else if(choice == 4) {
					deleteEmp();
				}
				else if(choice == 5) {
					modifyEmp();
				}
				else if(choice == 6) {
					System.out.println("Employees Information is closed");
					break;
				}
				else {
					System.out.println("Invalid Input");						
				}
			}
			
			else {
				System.out.println("Invalid Input");
			}		
		}
	}
}

	