package com.revature.controller;

import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.AuthService;

import io.javalin.http.Handler;

import com.google.gson.Gson;

public class AuthController {
	AuthService as = new AuthService();
	
	public Handler loginHandler = (ctx) ->{
		String body = ctx.body();
		
		Gson gson = new Gson();
		LoginDTO LDTO = gson.fromJson(body,LoginDTO.class);
		User loginuser = as.login(LDTO.getUsername(), LDTO.getPassword());
		if(loginuser != null) {
		//if((loginuser.getUsername() !=null) &(loginuser.getPassword() != null) ) {
			ctx.req.getSession();
			ctx.res.setHeader("Set-Cookie", "key=value; HttpOnly; SameSite=None; Secure");
			ctx.status(202);
			ctx.result("login success");
		}
		else {
			ctx.status(401);
			ctx.result("login failed");
		}
		
	};
}
