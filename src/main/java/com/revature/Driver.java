package com.revature;
import com.revature.controller.ReimbursementController;
import com.revature.controller.UserController;
import com.revature.models.Menu;
import java.sql.Connection;
import java.sql.SQLException;
import io.javalin.Javalin;

import com.revature.util.ConnectionFactory;
public class Driver {

    public static void main(String[] args) {
    	UserController uc = new UserController();
    	ReimbursementController rc = new ReimbursementController();
    	try(Connection conn = ConnectionFactory.getConnection()){
			System.out.println("Connection Successful :)");
		} catch(SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
    	/*
    	Menu menu = new Menu();
    	menu.displayMenu();
		*/
    	Javalin app = Javalin.create(
				config -> {
					config.enableCorsForAllOrigins(); // allows the server to process JS requests from anywhere
				}
			).start(3001);
    	
    	app.get("/user/{user_id}", uc.getUserByIdController);
    	app.post("/user", uc.updateNewUser);
    	app.get("/reimb/{user_id}", rc.getReimbByIdController);
    }
}
