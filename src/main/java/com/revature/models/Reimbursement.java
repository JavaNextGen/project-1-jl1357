package com.revature.models;

//import java.sql.Timestamp;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {
//public class Reimbursement {
	//private int reimb_id;
	//private int reimb_amount;
//	private Timestamp submitted;
//	private Timestamp abc;
//	private Timestamp resolved;
	private String reimb_description;
	private int ers_users_fk_auth;
	private int ers_users_fk_reslver;
	private int ers_reimb_status_fk;
	private int ers_reimb_type_fk;
	
   

	public Reimbursement(int id, Status status, User author, User resolver, double amount,/*Timestamp submitted, Timestamp resolved,*/ String reimb_description, int ers_users_fk_auth, int ers_users_fk_reslver,int ers_reimb_status_fk,int ers_reimb_type_fk) {
		super(id,status,author,resolver,amount);
//		this.submitted = submitted;
//		this.resolved = resolved;
		this.reimb_description = reimb_description;
		this.ers_users_fk_auth = ers_users_fk_auth;
		this.ers_users_fk_reslver = ers_users_fk_reslver;
		this.ers_reimb_status_fk = ers_reimb_status_fk;
		this.ers_reimb_type_fk = ers_reimb_type_fk;
	}

	/*public Reimbursement(int reimb_id, int reimb_amount, String submitted, String resolved, String reimb_description,
			int ers_users_fk_auth, int ers_users_fk_reslver, int ers_reimb_status_fk, int ers_reimb_type_fk) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.reimb_description = reimb_description;
		this.ers_users_fk_auth = ers_users_fk_auth;
		this.ers_users_fk_reslver = ers_users_fk_reslver;
		this.ers_reimb_status_fk = ers_reimb_status_fk;
		this.ers_reimb_type_fk = ers_reimb_type_fk;
	}*/


	public Reimbursement() {
        super();
    }

    @Override
	public String toString() {
		return "Reimbursement [reimb_description=" + reimb_description + ", ers_users_fk_auth=" + ers_users_fk_auth
				+ ", ers_users_fk_reslver=" + ers_users_fk_reslver + ", ers_reimb_status_fk=" + ers_reimb_status_fk
				+ ", ers_reimb_type_fk=" + ers_reimb_type_fk + "]";
	}

	public String getReimb_description() {
		return reimb_description;
	}

	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}

	public int getErs_users_fk_auth() {
		return ers_users_fk_auth;
	}

	public void setErs_users_fk_auth(int ers_users_fk_auth) {
		this.ers_users_fk_auth = ers_users_fk_auth;
	}

	public int getErs_users_fk_reslver() {
		return ers_users_fk_reslver;
	}

	public void setErs_users_fk_reslver(int ers_users_fk_reslver) {
		this.ers_users_fk_reslver = ers_users_fk_reslver;
	}

	public int getErs_reimb_status_fk() {
		return ers_reimb_status_fk;
	}

	public void setErs_reimb_status_fk(int ers_reimb_status_fk) {
		this.ers_reimb_status_fk = ers_reimb_status_fk;
	}

	public int getErs_reimb_type_fk() {
		return ers_reimb_type_fk;
	}

	public void setErs_reimb_type_fk(int ers_reimb_type_fk) {
		this.ers_reimb_type_fk = ers_reimb_type_fk;
	}

	/**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    public Reimbursement(int id, Status status, User author, User resolver, double amount) {
        super(id, status, author, resolver, amount);
    }
    
	/*public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public int getReimb_amount() {
		return reimb_amount;
	}

	public void setReimb_amount(int reimb_amount) {
		this.reimb_amount = reimb_amount;
	}

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public String getReimb_description() {
		return reimb_description;
	}

	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}

	public int getErs_users_fk_auth() {
		return ers_users_fk_auth;
	}

	public void setErs_users_fk_auth(int ers_users_fk_auth) {
		this.ers_users_fk_auth = ers_users_fk_auth;
	}

	public int getErs_users_fk_reslver() {
		return ers_users_fk_reslver;
	}

	public void setErs_users_fk_reslver(int ers_users_fk_reslver) {
		this.ers_users_fk_reslver = ers_users_fk_reslver;
	}

	public int getErs_reimb_status_fk() {
		return ers_reimb_status_fk;
	}

	public void setErs_reimb_status_fk(int ers_reimb_status_fk) {
		this.ers_reimb_status_fk = ers_reimb_status_fk;
	}

	public int getErs_reimb_type_fk() {
		return ers_reimb_type_fk;
	}

	public void setErs_reimb_type_fk(int ers_reimb_type_fk) {
		this.ers_reimb_type_fk = ers_reimb_type_fk;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", reimb_amount=" + reimb_amount + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", reimb_description=" + reimb_description + ", ers_users_fk_auth="
				+ ers_users_fk_auth + ", ers_users_fk_reslver=" + ers_users_fk_reslver + ", ers_reimb_status_fk="
				+ ers_reimb_status_fk + ", ers_reimb_type_fk=" + ers_reimb_type_fk + "]";
	}*/
    
}
