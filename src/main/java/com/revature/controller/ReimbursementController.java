package com.revature.controller;

import java.util.Optional;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import io.javalin.http.Handler;

public class ReimbursementController {
	ReimbursementService rs = new ReimbursementService();
	public Handler getReimbByIdController = (ctx) -> {
		int id = 0;
		id = Integer.parseInt(ctx.pathParam("user_id"));
		if(ctx.req.getSession() != null) {
			Optional<Reimbursement> reimbresult = rs.getById(id);
			Gson gson = new Gson();
			String JSONUser = gson.toJson(reimbresult);
			
			ctx.result(JSONUser);
			ctx.status(200);
		}else {
			ctx.result("failed");
			ctx.status(404);
		}
		
	};
}
