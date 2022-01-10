package com.revature.models;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li>
 *     <li>Address</li>
 * </ul>
 *
 */
public class User extends AbstractUser {
//public class User{
//	private int user_id;
//	private String username;
//	private String password;
	private String user_lname;
	private String user_fname;
	private String user_email;
	private int role_id_fk;
	
    public User() {
        super();
    }
    
//	public User(int user_id, String username, String password, String user_lname, String user_fname, String user_email,
//			int role_id_fk) {
//		super();
//		this.user_id = user_id;
//		this.username = username;
//		this.password = password;
//		this.user_lname = user_lname;
//		this.user_fname = user_fname;
//		this.user_email = user_email;
//		this.role_id_fk = role_id_fk;
//	}

	public User(int id, String username, String password, Role role,String user_lname, String user_fname, String user_email, int role_id_fk) {
		super(id,username,password,role);
		this.user_lname = user_lname;
		this.user_fname = user_fname;
		this.user_email = user_email;
		this.role_id_fk = role_id_fk;
	}

   
	


	/**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */
	public User(int id, String username, String password, Role role) {
        super(id, username, password, role);
    }

//	public int getUser_id() {
//		return user_id;
//	}
//
//	public void setUser_id(int user_id) {
//		this.user_id = user_id;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public String getUser_lname() {
		return user_lname;
	}

	public void setUser_lname(String user_lname) {
		this.user_lname = user_lname;
	}

	public String getUser_fname() {
		return user_fname;
	}

	public void setUser_fname(String user_fname) {
		this.user_fname = user_fname;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getRole_id_fk() {
		return role_id_fk;
	}

	public void setRole_id_fk(int role_id_fk) {
		this.role_id_fk = role_id_fk;
	}

	@Override
	public String toString() {
		return "User [user_lname=" + user_lname + ", user_fname=" + user_fname + ", user_email=" + user_email
				+ ", role_id_fk=" + role_id_fk + "]";
	}

//	@Override
//	public String toString() {
//		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", user_lname="
//				+ user_lname + ", user_fname=" + user_fname + ", user_email=" + user_email + ", role_id_fk="
//				+ role_id_fk + "]";
//	}

	
	
    
}
