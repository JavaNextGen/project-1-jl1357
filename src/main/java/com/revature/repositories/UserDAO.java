package com.revature.repositories;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;
import com.revature.models.Role;
//import jdk.javadoc.internal.doclets.formats.html.markup.HtmlAttr.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
    		ResultSet rs= null;
    		String sql = "SELECT * FROM ers_users inner join ers_roles on ers_roles.role_id = ers_users.role_id_fk where username = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, username);
    		rs=ps.executeQuery();
    
    		while(rs.next()) {
    			Role urole= Role.EMPLOYEE;
	    		if(rs.getString("user_role").equals("FINANCE_MANAGER")) {
	    			
	    			//System.out.println("in loop");
	   				 urole = Role.FINANCE_MANAGER;
	   			}
    			User nu = new User(
    						rs.getInt("user_id"),
    						rs.getString("username"),
    						rs.getString("password"),
    						urole,
    						rs.getString("user_lname"),
    						rs.getString("user_fname"),
    						rs.getString("user_email"),
    						rs.getInt("role_id_fk")
    					);
    			
    			//System.out.println(rs.getInt("user_id"));
    			//System.out.println(rs.getString("password"));
    			Optional<User> useroptional = Optional.ofNullable(nu);
    			//System.out.println(urole);
    			return useroptional;
    		}
    		//System.out.println(rs);
    				
    	} catch (SQLException e) {
    		System.out.println("cant select byname");
    		e.printStackTrace();
    		
    	}
        return null;
    }
    public User getbyUserid(int idinput) {
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
    		ResultSet rs= null;
    		//String sql = "SELECT * FROM ers_users where user_id = ? ";
    		String sql = "SELECT * FROM ers_users inner join ers_roles on ers_roles.role_id = ers_users.role_id_fk where user_id = ? ";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, idinput);
    		rs=ps.executeQuery();
    		User resultuser = new User();
    		
    		while(rs.next()) {
    			Role urole= Role.EMPLOYEE;
    			if(rs.getString("user_role").equals("FINANCE_MANAGER")) {
    				 urole = Role.FINANCE_MANAGER;
    			}
    			User nu = new User(
    						rs.getInt("user_id"),
    						rs.getString("username"),
    						rs.getString("password"),
    						urole,
    						rs.getString("user_lname"),
    						rs.getString("user_fname"),
    						rs.getString("user_email"),
    						rs.getInt("role_id_fk")
    					);
    			//System.out.println(rs.getInt("user_id"));
    			//System.out.println(rs.getString("password"));
    			resultuser=nu;
    			//System.out.println(nu);
    			
    		}
    		return resultuser;
    		//System.out.println(rs);
    				
    	} catch (SQLException e) {
    		System.out.println("cant select by user id");
    		e.printStackTrace();
    		
    	}
        return null;
    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     *
     * Note: The userToBeRegistered will have an id=0, and username and password will not be null.
     * Additional fields may be null.
     */
    public User create(User userToBeRegistered) {
    	try(Connection conn = ConnectionFactory.getConnection()){
    		//System.out.println("ur here");
    		
    		String sql = "INSERT INTO ers_users( username,PASSWORD,user_lname,user_fname,user_email,role_id_fk) VALUES (?,?,?,?,?,?)";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		//ps.setInt(1, userToBeRegistered.getId());
    		//System.out.println(userToBeRegistered.getId());
    		ps.setString(1, userToBeRegistered.getUsername());
    		ps.setString(2, userToBeRegistered.getPassword());
    		ps.setString(3, userToBeRegistered.getUser_lname());
    		ps.setString(4, userToBeRegistered.getUser_fname());
    		ps.setString(5, userToBeRegistered.getUser_email());
    		ps.setInt(6, userToBeRegistered.getRole_id_fk());
    		ps.executeUpdate();
    		ResultSet rs2=null;
    		String sql2="SELECT * FROM ers_users inner join ers_roles on ers_roles.role_id = ers_users.role_id_fk WHERE user_id = ?";
    		PreparedStatement ps2 = conn.prepareStatement(sql2);
    		ps2.setInt(1,userToBeRegistered.getId());
    		rs2=ps2.executeQuery();
    		while(rs2.next()) {
    			Role urole=Role.EMPLOYEE;
    			
    			if(rs2.getString("user_role").equals("FINANCE_MANAGER")) {
    				 urole = Role.FINANCE_MANAGER;
    			}
    			userToBeRegistered=new User(
    						rs2.getInt("user_id"),
    						rs2.getString("username"),
    						rs2.getString("password"),
    						urole,
    						rs2.getString("user_lname"),
    						rs2.getString("user_fname"),
    						rs2.getString("user_email"),
    						rs2.getInt("role_id_fk")
    					);
    			//System.out.println(rs2.getInt("user_id"));
    			//System.out.println(rs.getString("password"));
    			//System.out.println(userToBeRegistered);
    			
    			return userToBeRegistered;
    		}
    		//System.out.println(rs);
    				
    	} catch (SQLException e) {
    		System.out.println("cant create");
    		e.printStackTrace();
    		
    	}
        return null;
    }
    public boolean usernamechecker(String username) {
    	try(Connection conn = ConnectionFactory.getConnection()){
    		ResultSet rs= null;
    		String sql = "SELECT * FROM ers_users where username = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, username);
    		rs=ps.executeQuery();
    		if(rs.next()) {
    			return false;
    		}
    		else {
    			return true;
    		}
    		//System.out.println(rs);
    				
    	} catch (SQLException e) {
    		System.out.println("username incorrect");
    		e.printStackTrace();
    		
    	}
    	return true;
        
    }
    public boolean emailchecker(String useremail) {
    	try(Connection conn = ConnectionFactory.getConnection()){
    		ResultSet rs= null;
    		String sql = "SELECT * FROM ers_users where user_email = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, useremail);
    		rs=ps.executeQuery();
    		if(rs.next()) {
    			return false;
    		}
    		else {
    			return true;
    		}
    		//System.out.println(rs);
    				
    	} catch (SQLException e) {
    		System.out.println("email incorrect");
    		e.printStackTrace();
    		
    	}
    	return true;
        
    }
    public User loginDAO(String username, String password) {
    	try(Connection conn = ConnectionFactory.getConnection()){
    		ResultSet rs= null;
    		String sql = "SELECT * FROM ers_users WHERE (ers_users.username = ? and ers_users.\"password\" = ?);";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, username);
    		ps.setString(2, password);
    		rs=ps.executeQuery();
    		Role urole=Role.EMPLOYEE;
    		User r = new User();
    		while(rs.next()) {
    			if(rs.getInt("role_id_fk")==2) {
					urole=Role.FINANCE_MANAGER;
				}
    				User nu = new User(
    						rs.getInt("user_id"),
    						rs.getString("username"),
    						rs.getString("password"),
    						urole,
    						rs.getString("user_lname"),
    						rs.getString("user_fname"),
    						rs.getString("user_email"),
    						rs.getInt("role_id_fk")
    					);
    			r = nu;
    		}
    		
    		return r;
    				
    	} catch (SQLException e) {
    		System.out.println("password incorrect");
    		e.printStackTrace();
    		
    	}
    	return null;
        
    }
public User getbyusername(String username) {
    	
    	try(Connection conn = ConnectionFactory.getConnection()){
    		ResultSet rs= null;
    		//String sql = "SELECT * FROM ers_users where user_id = ? ";
    		String sql = "SELECT * FROM ers_users inner join ers_roles on ers_roles.role_id = ers_users.role_id_fk where username = ? ";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, username);
    		rs=ps.executeQuery();
    		User resultuser = new User();
    		
    		while(rs.next()) {
    			Role urole= Role.EMPLOYEE;
    			if(rs.getString("user_role").equals("FINANCE_MANAGER")) {
    				 urole = Role.FINANCE_MANAGER;
    			}
    			User nu = new User(
    						rs.getInt("user_id"),
    						rs.getString("username"),
    						rs.getString("password"),
    						urole,
    						rs.getString("user_lname"),
    						rs.getString("user_fname"),
    						rs.getString("user_email"),
    						rs.getInt("role_id_fk")
    					);
    			//System.out.println(rs.getInt("user_id"));
    			//System.out.println(rs.getString("password"));
    			resultuser=nu;
    			//System.out.println(nu);
    			
    		}
    		return resultuser;
    		//System.out.println(rs);
    				
    	} catch (SQLException e) {
    		System.out.println("cant select by username");
    		e.printStackTrace();
    		
    	}
        return null;
    }
}
