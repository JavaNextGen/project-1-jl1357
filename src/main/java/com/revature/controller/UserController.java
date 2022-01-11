package com.revature.controller;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class UserController {
	UserService us = new UserService();
	ReimbursementService rs = new ReimbursementService();
	public Handler getUserByIdController = (ctx) -> {
		int id = 0;
		id = Integer.parseInt(ctx.pathParam("user_id"));
		if(ctx.req.getSession() != null) {
			User userresult = us.getbyUserid(id);
			//System.out.println(userresult);
			Gson gson = new Gson();
			String JSONUser = gson.toJson(userresult);
			System.out.println(userresult);
			ctx.result(JSONUser);
			ctx.status(200);
		}else {
			ctx.result("failed");
			ctx.status(404);
		}
		
	};
	
	public Handler updateNewUser = (ctx) -> {
		
		if(ctx.req.getSession() != null) {
			
			String body = ctx.body();
			Gson gson = new Gson();
			User user = gson.fromJson(body, User.class);
			us.create(user);
			ctx.result("created user");
			ctx.status(200);
		}else {
			ctx.result("failed");
			ctx.status(404);
		}
		
	};
	public Handler getAllReimbUser = (ctx) -> {
		int id = 0;
		id = Integer.parseInt(ctx.pathParam("user_id"));
		if(ctx.req.getSession() != null) {
			List<Reimbursement> reimblist = rs.getAllReimbUser(id);
			//System.out.println(userresult);
			Gson gson = new Gson();
			String JSONUser = gson.toJson(reimblist);
			
			ctx.result(JSONUser);
			ctx.status(200);
		}else {
			ctx.result("failed");
			ctx.status(404);
		}
		
	};
	
}
