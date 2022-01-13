package com.revature;
import com.revature.controller.AuthController;
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
    	AuthController ac = new AuthController();
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
    	//get user by id
    	app.get("/user/{user_id}", uc.getUserByIdController);
    	//create new user
    	app.post("/user", uc.updateNewUser);
    	//get user by username
    	app.get("/username/{username}", uc.getuserbyusername);
    	//get reimb by id
    	app.get("/reimb/{reimb_id}", rc.getReimbByIdController);
    	//get all reimb belongs to user 
    	app.get("/reimbforuser/{user_id}", uc.getAllReimbUser);
    	//add new reimb
    	app.post("/reimbcreate", rc.addNewReimb);
    	//get all reimb
    	app.get("/reimball", rc.getAllReimbController);
    	//filter reimb by status
    	//1 approved, 2 pending, 3 denied
    	app.get("/reimbfilter/{reimb_status_id}", rc.getByStatsController);
    	app.post("/login",ac.loginHandler);
    	//update
    	app.post("/reimbupdate", rc.updatereimbController);
    	
    }
}
