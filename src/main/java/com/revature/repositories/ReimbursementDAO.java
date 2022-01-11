package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
	UserService us = new UserService();
	public List<Reimbursement> getAllReimbUser(int idinput){
		try(Connection conn = ConnectionFactory.getConnection()){
    		ResultSet rs= null;
    		ResultSet rs2= null;
    		ResultSet rs3= null;
    		String sql = "SELECT * FROM ers_reimb INNER JOIN ers_users ON ers_reimb.ers_users_fk_auth  = ers_users.user_id WHERE ers_reimb.ers_users_fk_auth =? ORDER BY reimb_id";
    		String sql2 = "SELECT * FROM ers_reimb LEFT JOIN ers_users ON ers_reimb.ers_users_fk_reslvr = ers_users.role_id_fk WHERE ers_reimb.ers_users_fk_auth =? ORDER BY reimb_id";
			String sql3 = "SELECT * FROM ers_reimb left JOIN ers_reimb_status ON ers_reimb.ers_reimb_status_fk = ers_reimb_status.reimb_status_id WHERE  ers_reimb.ers_users_fk_auth =? ORDER BY reimb_id;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			ps.setInt(1, idinput);
			ps2.setInt(1, idinput);
    		
			ps3.setInt(1, idinput);
			rs=ps.executeQuery();
			rs2=ps2.executeQuery();
			rs3=ps3.executeQuery();
			Status reimbStatus = Status.PENDING;
    		List<Reimbursement> reimblist = new ArrayList<>();
    		
    		
    		while(rs.next()&rs2.next()&rs3.next()) {
    			if(rs3.getString("reimb_status")=="Approved") {
    				
   					reimbStatus = Status.APPROVED;
    			}
    			
    			else if(rs3.getString("reimb_status")=="Denied") {
    				reimbStatus = Status.DENIED;
    			}
    			
    			User auth = us.getbyUserid(idinput);
    			User resl = us.getbyUserid(rs2.getInt("user_id"));
    			Reimbursement r = new Reimbursement(
    						rs.getInt("reimb_id"),
    						reimbStatus,
    						auth,
    						resl,
    						rs.getDouble("reimb_amount"),
    						//rs.getTimestamp("reimb_submitted"),
							//rs.getTimestamp("reimb_resolved"),
							rs.getString("reimb_description"),
							rs.getInt("ers_users_fk_auth"),
							rs.getInt("ers_users_fk_reslvr"),
							rs.getInt("ers_reimb_status_fk"),
							rs.getInt("ers_reimb_type_fk")
    					);
    				reimblist.add(r);
    				
    			}
    			//System.out.println(reimblist);
    		
    		return reimblist;
    		
    				
    	} catch (SQLException e) {
    		System.out.println("getbystatus failed");
    		e.printStackTrace();
    		
    	}
        return Collections.emptyList();
	}
    public Optional<Reimbursement> getById(int id) {
    	try(Connection conn = ConnectionFactory.getConnection()){
    		ResultSet rs= null;
    		ResultSet rs2= null;
    		ResultSet rs3= null;
    		String sqlauth = "SELECT * FROM ers_reimb LEFT JOIN ers_users ON ers_reimb.ers_users_fk_auth  = ers_users.user_id WHERE ers_reimb.reimb_id =?";
    		String sqlresl = "SELECT * FROM ers_reimb LEFT JOIN ers_users ON ers_reimb.ers_users_fk_reslvr = ers_users.role_id_fk WHERE ers_reimb.reimb_id =?";
    		String sqlstat = "SELECT * FROM ers_reimb LEFT JOIN ers_reimb_status ON ers_reimb.ers_reimb_status_fk = ers_reimb_status.reimb_status_id WHERE ers_reimb.reimb_id =?";
    		PreparedStatement ps = conn.prepareStatement(sqlauth);
    		PreparedStatement ps2 = conn.prepareStatement(sqlresl);
    		PreparedStatement ps3 = conn.prepareStatement(sqlstat);
    		ps.setInt(1, id);
    		ps2.setInt(1, id);
    		ps3.setInt(1, id);
    		rs=ps.executeQuery();
    		rs2=ps2.executeQuery();
    		rs3=ps3.executeQuery();
    		//System.out.println(rs.next());
    		while(rs.next()&rs2.next()&rs3.next()) {
    			
    			Status reimbStatus = Status.PENDING;
    			if(rs3.getString("reimb_status")=="Approved") {
    				
   					reimbStatus = Status.APPROVED;
    			}
    			
    			else if(rs3.getString("reimb_status")=="Denied") {
    				reimbStatus = Status.DENIED;
    			}
    			//System.out.println("ur here");
    			//System.out.println(rs3.getString("reimb_status"));
    			User auth = us.getbyUserid(rs.getInt("user_id"));
    			User resl = us.getbyUserid(rs2.getInt("user_id"));
    			Reimbursement nr = new Reimbursement(
	    					rs.getInt("reimb_id"),
	    					reimbStatus,
	    					//rs.getString("user_fname"),
	    					auth,
	    					//rs2.getString("user_fname"),
	    					resl,
							rs.getDouble("reimb_amount"),
							//rs.getTimestamp("reimb_submitted"),
							//rs.getTimestamp("reimb_resolved"),
							rs.getString("reimb_description"),
							rs.getInt("ers_users_fk_auth"),
							rs.getInt("ers_users_fk_reslvr"),
							rs.getInt("ers_reimb_status_fk"),
							rs.getInt("ers_reimb_type_fk")
    					);
    			Optional<Reimbursement> Reimbursementoptional = Optional.ofNullable(nr);
    			System.out.println(Reimbursementoptional);
    		return Reimbursementoptional;}
    		
    		//System.out.println(rs);
    				
    	} catch (SQLException e) {
    		System.out.println("cant select reimb byid");
    		e.printStackTrace();
    		
    	}
        return Optional.empty();
    }
    //return reimb not optional
    public Reimbursement getById2(int id) {
    	try(Connection conn = ConnectionFactory.getConnection()){
    		ResultSet rs= null;
    		ResultSet rs2= null;
    		ResultSet rs3= null;
    		String sqlauth = "SELECT * FROM ers_reimb INNER JOIN ers_users ON ers_reimb.ers_users_fk_auth  = ers_users.user_id WHERE ers_reimb.reimb_id =?";
    		String sqlresl = "SELECT * FROM ers_reimb INNER JOIN ers_users ON ers_reimb.ers_users_fk_reslvr = ers_users.role_id_fk WHERE ers_reimb.reimb_id =?";
    		String sqlstat = "SELECT * FROM ers_reimb INNER JOIN ers_reimb_status ON ers_reimb.ers_reimb_status_fk = ers_reimb_status.reimb_status_id WHERE ers_reimb.reimb_id =?";
    		PreparedStatement ps = conn.prepareStatement(sqlauth);
    		PreparedStatement ps2 = conn.prepareStatement(sqlresl);
    		PreparedStatement ps3 = conn.prepareStatement(sqlstat);
    		ps.setInt(1, id);
    		ps2.setInt(1, id);
    		ps3.setInt(1, id);
    		rs=ps.executeQuery();
    		rs2=ps2.executeQuery();
    		rs3=ps3.executeQuery();
    		
    		while(rs.next()&rs2.next()&rs3.next()) {
    			
    			Status reimbStatus = Status.PENDING;
    			if(rs3.getString("reimb_status")=="Approved") {
    				
   					reimbStatus = Status.APPROVED;
    			}
    			
    			else if(rs3.getString("reimb_status")=="Denied") {
    				reimbStatus = Status.DENIED;
    			}
    			User auth = us.getbyUserid(rs.getInt("user_id"));
    			User resl = us.getbyUserid(rs2.getInt("user_id"));
    			Reimbursement nr = new Reimbursement(
	    					rs.getInt("reimb_id"),
	    					reimbStatus,
	    					auth,
	    					resl,
							rs.getDouble("reimb_amount"),
							//rs.getTimestamp("reimb_submitted"),
							//rs.getTimestamp("reimb_resolved"),
							rs.getString("reimb_description"),
							rs.getInt("ers_users_fk_auth"),
							rs.getInt("ers_users_fk_reslvr"),
							rs.getInt("ers_reimb_status_fk"),
							rs.getInt("ers_reimb_type_fk")
    					);
    		/*ResultSet rs= null;
    		String sql = "SELECT * FROM ers_reimb where reimb_id = ?";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, id);
    		rs=ps.executeQuery();
    		while(rs.next()) {
    			Reimbursement nr = new Reimbursement(
	    					rs.getInt("reimb_id"),
							rs.getInt("reimb_amount"),
							rs.getString("reimb_submitted"),
							rs.getString("reimb_resolved"),
							rs.getString("reimb_description"),
							rs.getInt("ers_users_fk_auth"),
							rs.getInt("ers_users_fk_reslvr"),
							rs.getInt("ers_reimb_status_fk"),
							rs.getInt("ers_reimb_type_fk")
    					);
    			
    			System.out.println(nr);*/
    			System.out.println(nr);
    			return nr;
    		}
    		//System.out.println(rs);
    				
    	} catch (SQLException e) {
    		System.out.println("cant select reimb byid");
    		e.printStackTrace();
    		
    	}
        return null;
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
    	try(Connection conn = ConnectionFactory.getConnection()){
    		ResultSet rs= null;
    		
    		String sql = "SELECT * FROM ers_reimb LEFT JOIN ers_reimb_status ON ers_reimb.ers_reimb_status_fk = ers_reimb_status.reimb_status_id WHERE ers_reimb_status.reimb_status = ?;";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setString(1, status.toString());
    		rs=ps.executeQuery();
    		List<Reimbursement> reimblist = new ArrayList<>();
    		
    		
    		while(rs.next()) {
    			
    			ResultSet rs2= null;
    			String sqlauth = "SELECT * FROM ers_reimb LEFT JOIN ers_users ON ers_reimb.ers_users_fk_auth  = ers_users.user_id WHERE ers_reimb.reimb_id =?";
    			PreparedStatement ps2 = conn.prepareStatement(sqlauth);
    			ps2.setInt(1, rs.getInt("reimb_id"));
    			rs2=ps2.executeQuery();
    			ResultSet rs3= null;
    			String sqlresl = "SELECT * FROM ers_reimb LEFT JOIN ers_users ON ers_reimb.ers_users_fk_reslvr = ers_users.role_id_fk WHERE ers_reimb.reimb_id =?";
    			PreparedStatement ps3 = conn.prepareStatement(sqlresl);
    			ps3.setInt(1, rs.getInt("reimb_id"));
    			rs3=ps3.executeQuery();
    			
    			while(rs2.next()&rs3.next()) {
    			User auth = us.getbyUserid(rs2.getInt("user_id"));
    			User resl = us.getbyUserid(rs3.getInt("user_id"));
    			Reimbursement r = new Reimbursement(
    					rs.getInt("reimb_id"),
    					status,
    					auth,
    					resl,
						rs.getDouble("reimb_amount"),
						//rs.getTimestamp("reimb_submitted"),
						//rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getInt("ers_users_fk_auth"),
						rs.getInt("ers_users_fk_reslvr"),
						rs.getInt("ers_reimb_status_fk"),
						rs.getInt("ers_reimb_type_fk")
    					);
    			//System.out.println("in list");
    			reimblist.add(r);
    			}
    			
    		}
    			//System.out.println(reimblist);
    		
    		return reimblist;
    		
    				
    	} catch (SQLException e) {
    		System.out.println("getbystatus failed");
    		e.printStackTrace();
    		
    	}
        return Collections.emptyList();
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) {
    		int id = unprocessedReimbursement.getId();
    		int reslvr = unprocessedReimbursement.getErs_users_fk_reslver();
    		int stat=1;
    		if (finalStatus.toString()=="Pending") {
    			stat=1;
    		}
    		else if (finalStatus.toString()=="Approved") {
    			stat=2;
    		}
    		else if (finalStatus.toString()=="Denied") {
    			stat=3;
    		}
    		
    	try(Connection conn = ConnectionFactory.getConnection()){
    		
    		ResultSet rs= null;
    		String sql = "UPDATE ers_reimb SET ers_reimb_status_fk = ?, ers_users_fk_reslvr = ? WHERE reimb_id =? ";
    		PreparedStatement ps = conn.prepareStatement(sql);
    		ps.setInt(1, stat);
    		ps.setInt(2, reslvr);
    		ps.setInt(3, id);
    		ps.executeUpdate();
    		String sqlauth = "SELECT * FROM ers_reimb INNER JOIN ers_users ON ers_reimb.ers_users_fk_auth  = ers_users.user_id WHERE ers_reimb.reimb_id =?";
    		//String sqlresl = "SELECT * FROM ers_reimb INNER JOIN ers_users ON ers_reimb.ers_users_fk_reslvr = ers_users.role_id_fk WHERE ers_reimb.reimb_id =?";
    		//String sqlstat = "SELECT * FROM ers_reimb INNER JOIN ers_reimb_status ON ers_reimb.ers_reimb_status_fk = ers_reimb_status.reimb_status_id WHERE ers_reimb.reimb_id =?";
    		PreparedStatement ps2 = conn.prepareStatement(sqlauth);
    		//PreparedStatement ps3 = conn.prepareStatement(sqlresl);
    		//PreparedStatement ps4 = conn.prepareStatement(sqlstat);
    		ps2.setInt(1, id);
    		//ps3.setInt(1, id);
    		//ps4.setInt(1, id);
    		ResultSet rs2= null;
    		//ResultSet rs3= null;
    		//ResultSet rs4= null;
    		rs2=ps2.executeQuery();
    		//rs3=ps3.executeQuery();
    		//rs4=ps4.executeQuery();
    		while(rs2.next()) {
    			User auth = us.getbyUserid(id);
    			Reimbursement nr = new Reimbursement(
    					id,
    					finalStatus,
    					auth,
    					resolver,
    					unprocessedReimbursement.getAmount(),
    					//unprocessedReimbursement.getSubmitted(),
    					//unprocessedReimbursement.getResolved(),
    					unprocessedReimbursement.getReimb_description(),
    					unprocessedReimbursement.getErs_users_fk_auth(),
    					unprocessedReimbursement.getErs_users_fk_reslver(),
    					unprocessedReimbursement.getErs_reimb_status_fk(),
    					unprocessedReimbursement.getErs_reimb_type_fk()
//						rs.getDouble("reimb_amount"),
//						rs.getString("reimb_submitted"),
//						rs.getString("reimb_resolved"),
//						rs.getString("reimb_description"),
//						rs.getInt("ers_users_fk_auth"),
//						rs.getInt("ers_users_fk_reslvr"),
//						rs.getInt("ers_reimb_status_fk"),
//						rs.getInt("ers_reimb_type_fk")
    					);
    			
    			System.out.println(nr);
    			return nr;
    		}

    		
    		//System.out.println(rs);
    				
    	} catch (SQLException e) {
    		System.out.println("getbystatus failed");
    		e.printStackTrace();
    		
    	}
    	return null;
    }
    public Reimbursement createReimb(Reimbursement unprocessedReimbursement) {
	try(Connection conn = ConnectionFactory.getConnection()){
		
		//ResultSet rs= null;
		String sql = "INSERT INTO ers_reimb(reimb_id, reimb_amount,reimb_description,ers_users_fk_auth,ers_reimb_status_fk,ers_reimb_type_fk) VALUES (?,?,?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, unprocessedReimbursement.getId());
		ps.setDouble(2, unprocessedReimbursement.getAmount());
		//ps.setTimestamp(3, unprocessedReimbursement.getSubmitted());
		//ps.setTimestamp(3, unprocessedReimbursement.getSubmitted());
		ps.setString(3, unprocessedReimbursement.getReimb_description());
		ps.setInt(4, unprocessedReimbursement.getErs_users_fk_auth());
		ps.setInt(5, unprocessedReimbursement.getErs_reimb_status_fk());
		ps.setInt(6, unprocessedReimbursement.getErs_reimb_type_fk());
		ps.executeUpdate();
			
		

		
		//System.out.println(rs);
				
	} catch (SQLException e) {
		System.out.println("add new reimb failed");
		e.printStackTrace();
		
	}
	return null;
}
    public List<Reimbursement> getAllReimb() {
    	try(Connection conn = ConnectionFactory.getConnection()){
    		ResultSet rs= null;
    		ResultSet rs2= null;
    		ResultSet rs3= null;
    		String sqlauth = "SELECT * FROM ers_reimb LEFT JOIN ers_users ON ers_reimb.ers_users_fk_auth  = ers_users.user_id";
    		String sqlresl = "SELECT * FROM ers_reimb LEFT JOIN ers_users ON ers_reimb.ers_users_fk_reslvr = ers_users.role_id_fk";
    		String sqlstat = "SELECT * FROM ers_reimb LEFT JOIN ers_reimb_status ON ers_reimb.ers_reimb_status_fk = ers_reimb_status.reimb_status_id";
    		Statement ps = conn.createStatement();
    		Statement ps2 = conn.createStatement();
    		Statement ps3 = conn.createStatement();
    		rs=ps.executeQuery(sqlauth);
    		rs2=ps2.executeQuery(sqlresl);
    		rs3=ps3.executeQuery(sqlstat);
    		List<Reimbursement> reimblist = new ArrayList<>();
    		while(rs.next()&rs2.next()&rs3.next()) {
    			
    			Status reimbStatus = Status.PENDING;
    			if(rs3.getString("reimb_status")=="Approved") {
    				
   					reimbStatus = Status.APPROVED;
    			}
    			
    			else if(rs3.getString("reimb_status")=="Denied") {
    				reimbStatus = Status.DENIED;
    			}
    			User auth = us.getbyUserid(rs.getInt("user_id"));
    			User resl = us.getbyUserid(rs2.getInt("user_id"));
    			Reimbursement r = new Reimbursement(
    					rs.getInt("reimb_id"),
    					reimbStatus,
    					auth,
    					resl,
						rs.getDouble("reimb_amount"),
						//rs.getTimestamp("reimb_submitted"),
						//rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getInt("ers_users_fk_auth"),
						rs.getInt("ers_users_fk_reslvr"),
						rs.getInt("ers_reimb_status_fk"),
						rs.getInt("ers_reimb_type_fk")
    					);
    			//System.out.println("in list");
    			reimblist.add(r);
    			
    			
    		}
    			System.out.println(reimblist);
    		
    		return reimblist;
    		
    				
    	} catch (SQLException e) {
    		System.out.println("getall reimb failed");
    		e.printStackTrace();
    		
    	}
        return Collections.emptyList();
    }
    
//    public Reimbursement update(Reimbursement unprocessedReimbursement) {
//		int id = unprocessedReimbursement.getId();
//	try(Connection conn = ConnectionFactory.getConnection()){
//		ResultSet rs= null;
//		String sql = "UPDATE ers_reimb SET ers_reimb_status_fk = ? WHERE reimb_id =? ";
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.setInt(1, 2);
//		ps.setInt(2, id);
//		ps.executeUpdate();
//		
//
//			Reimbursement r = new Reimbursement(
//						rs.getInt("reimb_id"),
//						rs.getInt("reimb_amount"),
//						rs.getString("reimb_submitted"),
//						rs.getString("reimb_resolved"),
//						rs.getString("reimb_description"),
//						rs.getInt("ers_users_fk_auth"),
//						rs.getInt("ers_users_fk_reslvr"),
//						rs.getInt("ers_reimb_status_fk"),
//						rs.getInt("ers_reimb_type_fk")
//					);
//			return r;
//
//		
//		//System.out.println(rs);
//				
//	} catch (SQLException e) {
//		System.out.println("getbystatus failed");
//		e.printStackTrace();
//		
//	}
//	return null;
//}
}
