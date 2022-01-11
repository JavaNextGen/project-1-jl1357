package com.revature.models;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.revature.repositories.UserDAO;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
//import com.revature.services.RoleService;

//This Menu Class will have a displayMenu() method that displays the menu to the user and lets them interact with it
//The menu will make use of the Scanner class to take user inputs in order to travel through the menu options.
public class Menu {

	UserService us = new UserService(); //we need this object to use methods from EmployeeService
	//RoleService rs = new RoleService();
	ReimbursementService rs = new ReimbursementService();
	//All of the menu display options and control flow are contained within this method
	public void displayMenu() {
		
		boolean displayMenu = true; //we're going to use this to toggle whether the menu continues after user input
		Scanner scan = new Scanner(System.in); //Scanner object to parse (take) user input
		
		//give the user a pretty greeting :)
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		System.out.println("Welcome to The Krusty Krab Employee Management System");
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		
		
		//display the menu as long as the displayMenu boolean == true
		//this is going to hold and display all my menu options until displayMenu == false
		while(displayMenu) { 
			
			//menu options
			System.out.println("hi -> get greeted");
			System.out.println("employees -> show all employees");
			System.out.println("employeesById -> get employees with a certain ID");
			System.out.println("employeesByTitle -> get employees of a certain title");
			System.out.println("1 -> get employees with a username");
			System.out.println("2 -> get reimb with a id");
			System.out.println("3 -> create new user");
			System.out.println("4 -> get reimb by status");
			System.out.println("5 -> update reimb");
			System.out.println("add -> add a new employee");
			System.out.println("exit -> exit the application");
			
			
			//parse user input after they choose a menu option, and put it into a String variable
			String input = scan.nextLine();
			
			//switch statement that takes the user input and executes the appropriate code
			//BEN MAY ADD MORE COMMENTS HERE ONCE WE DO DATABASE CONNECTIVITY
			switch(input) {
			
			case "hi": {
				System.out.println("Hello there.");
				break; //we need a break in each case block, or else all the other cases will still run
			}
			
			/*case "employees" :{
				
				//get the List of employees from the repository layer
				List<Employee> employees = es.getEmployees();
				
				//enhanced for loop to print out the Employees one by one
				for (Employee e : employees) {
					System.out.println(e);
				}
				
				break;
			}*/
			
			case "1" :{
				System.out.println("What employee Username would you like to search for?");
				
				String idInput = scan.nextLine(); //get user's input for id
				 //we still need nextLine so that we can move to the next line for more input
				
				//what if the user inputs a String? program crashes
				//up to you to polish your project a bit and add some foolproofing mechanisms
				
				//List<Employee> employees = es.getEmployeeById(idInput);
				Optional<User> user1=us.getByUsername(idInput);
				
	                                                                  			
			
				
				break;
			}
			case "2" :{
				System.out.println("What reimb id would you like to search for?");
				
				int idInput = scan.nextInt(); //get user's input for id
				 //we still need nextLine so that we can move to the next line for more input
				scan.nextLine();
				//what if the user inputs a String? program crashes
				//up to you to polish your project a bit and add some foolproofing mechanisms
				
				//List<Employee> employees = es.getEmployeeById(idInput);
				Optional<Reimbursement> reimb1=rs.getById(idInput);
				//Reimbursement reimb2 =rs.getById2(idInput);
				
			
				
				break;
			}
			case "3" :{
				System.out.println("enter id");
				int idInput = scan.nextInt();
				scan.nextLine();
				System.out.println("enter usernamne");
				String nameInput = scan.nextLine();
				
				System.out.println("enter password");
				String passInput = scan.nextLine();
				System.out.println("enter first name");
				String fnameInput = scan.nextLine();
				System.out.println("enter lasnamne");
				String lnameInput = scan.nextLine();
				System.out.println("enter email");
				String emailInput = scan.nextLine();
				System.out.println("enter role");
				String roleInput = scan.nextLine();
				Role r1 = Role.valueOf(roleInput);
				int roleidInput=1;
				if(roleInput == "FINANCE_MANAGER") {
					roleidInput=2;
				}
			
				
				//User userToBeRegistered = new User(idInput,nameInput,passInput,fnameInput,lnameInput,emailInput,roleidInput);
				User userToBeRegistered = new User(idInput,nameInput,passInput,r1,fnameInput,lnameInput,emailInput,roleidInput);
				us.create(userToBeRegistered);
				
				
			
				
				break;
			}

			case "4" :{
				System.out.println("enter status:pending or approved or denied(ALL CAPTIAL)");
				
				String str = scan.nextLine(); 
				
				Status s1=Status.valueOf(str);
				
				//System.out.println(Status.valueOf(str));
				List<Reimbursement> ReimbursementList = rs.getReimbursementsByStatus(s1);
				for(Reimbursement r : ReimbursementList)
				{
					System.out.println(r); //print them out one by one via the enhanced for loop
				}
	                                                                  			
			
				
				break;
			}
			case "5" :{
				System.out.println("enter ur id");
				int authid = scan.nextInt(); 
				scan.nextLine();
				User checker = new User();
				checker=us.getbyUserid(authid);
				if(checker.getRole_id_fk() != 2) {
					System.out.println("u cant update");
					break;
				}
				System.out.println("enter reimb id that u want update");
				
				int reimbid = scan.nextInt(); 
				scan.nextLine();
				System.out.println("enter reimb status: PENDING, APPROVED, DENIED");
				String str = scan.nextLine();  
				Status s1=Status.valueOf(str);
				Reimbursement r1=rs.getById2(reimbid);
				Reimbursement r2=rs.process(r1,s1,checker);
				//Reimbursement r3=rs.update(r2);
				break;
			}
			case "6" :{
				System.out.println("What user id would you like to search for?");
				
				int idInput = scan.nextInt();
				scan.nextLine();//get user's input for id
				 //we still need nextLine so that we can move to the next line for more input
				
				//what if the user inputs a String? program crashes
				//up to you to polish your project a bit and add some foolproofing mechanisms
				
				//List<Employee> employees = es.getEmployeeById(idInput);
				User user1=us.getbyUserid(idInput);
				
	                                                                  			
			
				
				break;
			}
			case "7" :{
				System.out.println("What user id would you like to search for?");
				
				int idInput = scan.nextInt();
				scan.nextLine();//get user's input for id
				 //we still need nextLine so that we can move to the next line for more input
				
				//what if the user inputs a String? program crashes
				//up to you to polish your project a bit and add some foolproofing mechanisms
				
				//List<Employee> employees = es.getEmployeeById(idInput);
				List<Reimbursement> ReimbursementList = rs.getAllReimbUser(idInput);
				for(Reimbursement r : ReimbursementList)
				{
					System.out.println(r); //print them out one by one via the enhanced for loop
				}
	                                                                  			
			
				
				break;
			}
			/*case "employeebytitle": {
				
			System.out.println("Enter Employee Role to Search: (Case Sensitive! e.g. \"Fry Cook\")");
			String roleInput = scan.nextLine(); //get user's input for Role to search by
			
			List<Employee> employees = es.getEmployeesByRoleTitle(roleInput); //get the List of Employees from the dao
			
			for(Employee e : employees)
			{
				System.out.println(e); //print them out one by one via the enhanced for loop
			}
			break;				
			
		}*/
			
			/*case "add" : {
				
				//we need to prompt the user for the employee's name, and their role_id
				System.out.println("Enter Employee First Name");
				String f_name = scan.nextLine();
				
				System.out.println("Enter Employee Last Name");
				String l_name = scan.nextLine();
				
				System.out.println("Enter Role Id: 1) Manager 2) Fry Cook 3) Cashier 4) Marketing 5) Nepotism");
				int roleId = scan.nextInt(); //we need nextInt because ID is an int datatype in the database
				scan.nextLine(); //without any nextLine(), your enter keystroke will be grabbed as the next input
				//so we need nextLine() in order to actually move to the..... NEXT line!
				
				//Given all this information, we'll create a new Employee object to send to the service layer
				//then the service layer will send it to the repository layer.
				Employee newEmployee = new Employee(f_name, l_name, roleId);
				
				//Put the new Employee into the addEmployee() method in the EmployeeService Class
				es.addEmployee(newEmployee);
				
				break;
			}*/
				
				/*case "updateSalary": {
				
				System.out.println("Enter Role Title to change");
				String titleInput = scan.nextLine();
				
				System.out.println("Enter a new Salary for this Role");
				int salaryInput = scan.nextInt();
				scan.nextLine();
				
				rs.updateSalary(titleInput, salaryInput);
				break;
			}*/
			
			case "exit": {
				displayMenu = false;
				break;
			}
			
			//this default block will catch any user inputs that don't match a valid menu option
			default: {
				System.out.println("Invalid selection... try again :'( ");
				break;
			}
			
			
			} //end of switch
			
		} //end of while loop
		
		
	}
	
}
