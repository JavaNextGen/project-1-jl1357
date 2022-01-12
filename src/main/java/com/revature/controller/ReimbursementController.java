package com.revature.controller;

import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import com.revature.models.Status;
import io.javalin.http.Handler;

public class ReimbursementController {
	ReimbursementService rs = new ReimbursementService();
	public Handler getReimbByIdController = (ctx) -> {
		int id = 0;
		id = Integer.parseInt(ctx.pathParam("reimb_id"));
		if(ctx.req.getSession() != null) {
			Optional<Reimbursement> reimbresult = rs.getById(id);
			Gson gson = new Gson();
			String JSONreimb = gson.toJson(reimbresult);
			
			ctx.result(JSONreimb);
			ctx.status(200);
		}else {
			ctx.result("failed");
			ctx.status(404);
		}
		
	};
public Handler addNewReimb = (ctx) -> {
		
		if(ctx.req.getSession() != null) {
			
			String body = ctx.body();
			Gson gson = new Gson();
			Reimbursement reimb = gson.fromJson(body, Reimbursement.class);
			rs.createReimb(reimb);
			ctx.result("created reimb");
			ctx.status(200);
		}else {
			ctx.result("failed");
			ctx.status(404);
		}
		
	};
	public Handler getAllReimbController = (ctx) -> {
		if(ctx.req.getSession() != null) {
			List<Reimbursement> reimbresult = rs.getAllReimb();
			Gson gson = new Gson();
			String JSONreimb = gson.toJson(reimbresult);
			
			ctx.result(JSONreimb);
			ctx.status(200);
		}else {
			ctx.result("failed");
			ctx.status(404);
		}
		
	};
	public Handler getByStatsController = (ctx) -> {
		int id = 0;
		id = Integer.parseInt(ctx.pathParam("reimb_status_id"));
		Status reimbstatus=Status.PENDING;
		
		if(id==1) {
			reimbstatus=Status.APPROVED;
		}
		else if(id==3) {
			reimbstatus=Status.DENIED;
		}
		if(ctx.req.getSession() != null) {
			List<Reimbursement> reimbresult = rs.getReimbursementsByStatus(reimbstatus);
			Gson gson = new Gson();
			String JSONreimb = gson.toJson(reimbresult);
			
			ctx.result(JSONreimb);
			ctx.status(200);
		}else {
			ctx.result("failed");
			ctx.status(404);
		}
		
	};
}
