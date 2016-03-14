/**
UsersManager.java by César Teixeira and Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.

Based on the "Simple Identity Management Profile", part of CIM by DMTF.

CIM_Account represents accounts that are defined locally on the system.
CIM_Identity represents a security principal.
The CIM_AssignedIdentity association is used to associate the security principal with the entity whose privileges are being managed. Local accounts can have one or more associated security principals. These security principles create a relationship between the authenticated individual and the authorization granted to the individual.

*/

package encourager;

import encourager.generated.cim_assignedidentity.*;
import encourager.generated.cim_identity.*;
import encourager.generated.cim_account.*;
import encourager.generated.*;

import java.util.List;
import java.util.ArrayList;

public class UsersManager {
	private List<CIM_AssignedIdentity> m_assigned_identity;
	private List<CIM_Identity> m_identity;
	private List<CIM_Account> m_account;
	private String m_semantics;

	public static UsersManager Unmarshal(String xml, UsersManager existing_users_manager) throws Exception {
		UsersManager ret = null;
		if (existing_users_manager == null)
			ret = new UsersManager("");
		else
			ret = existing_users_manager;

		String temp = ""+xml;
		EncCIM_AssignedIdentity encass = encourager.generated.EncCIM_AssignedIdentity.CreateEncCIM_AssignedIdentity();
		EncCIM_Identity encide = encourager.generated.EncCIM_Identity.CreateEncCIM_Identity();
		EncCIM_Account encacc = encourager.generated.EncCIM_Account.CreateEncCIM_Account();

		int next=1;
		while (next>0) {
			next=temp.indexOf("<?", 3);
			String work = null;
			if (next > 0) {
				work = temp.substring(0,next);
				temp = temp.substring(next);
			} else {
				work = temp;
			}
			String semantics = UsersManager.InferSemantics(work);
			if (0 == "CIM_AssignedIdentity".compareTo(semantics)) {
				ret.m_assigned_identity.add(encass.Unmarshal(work));
			}
			if (0 == "CIM_Identity".compareTo(semantics)) {
				ret.m_identity.add(encide.Unmarshal(work));
			}
			if (0 == "CIM_Account".compareTo(semantics)) {
				ret.m_account.add(encacc.Unmarshal(work));
			}
//			System.out.println(semantics+"\n\n");
		}

		return ret;
	}

	public UsersManager() {
		ResetUsersManager();
	}

	public UsersManager(String sem) {
		m_semantics = sem;
		ResetUsersManager();
	}

	public void ResetUsersManager() {
		m_assigned_identity = new ArrayList<CIM_AssignedIdentity>();
		m_identity = new ArrayList<CIM_Identity>();
		m_account = new ArrayList<CIM_Account>();
		m_semantics = null;
	}


	public boolean AddAssignedIdentity(String access_id, String privileges_id) throws Exception {
		CIM_AssignedIdentity ai = new CIM_AssignedIdentity();

		ai.setManagedElement(access_id);
		ai.setIdentityInfo(privileges_id);
		m_assigned_identity.add(ai);

		return true;
	}

	public boolean AddIdentity(String privileges_id, String desc) throws Exception {
		CIM_Identity ai = new CIM_Identity();

		ai.setDescription(desc);
		ai.setInstanceID(privileges_id);
		m_identity.add(ai);

		return true;
	}

	public boolean AddAccount(String user_id, String password, String access_id) throws Exception {
		CIM_Account ai = new CIM_Account();
		ai.setName(user_id);
		ai.setUserID(user_id);
		ai.setInstanceID(access_id);
		ai.setUserPassword(password);
		m_account.add(ai);
		return true;
	}





public String ToSql() throws Exception {
	StringBuilder sb = new StringBuilder();
	for (java.util.Iterator<CIM_AssignedIdentity> e = m_assigned_identity.iterator(); e.hasNext();) {
		CIM_AssignedIdentity ec = e.next();
		sb.append("INSERT INTO MIDDLEWARE.access (access_id, privileges_id) VALUES ('"+ec.getManagedElement()+"', '"+ec.getIdentityInfo()+"');\n");
	}
	for (java.util.Iterator<CIM_Identity> e = m_identity.iterator(); e.hasNext();) {
		CIM_Identity ec = e.next();
		sb.append("INSERT INTO MIDDLEWARE.privileges (privileges_id, privileges_desc) VALUES ('"+ec.getInstanceID()+"', '"+ec.getDescription()+"');\n");
	}
	for (java.util.Iterator<CIM_Account> e = m_account.iterator(); e.hasNext();) {
		CIM_Account ec = e.next();
		sb.append("INSERT INTO MIDDLEWARE.user_table (user_id, password, access_id) VALUES ('"+ec.getName()+"', '"+ec.getUserPassword()+"', '"+ec.getInstanceID()+"');\n");
	}
	return sb.toString();
}


	public String Marshal() {
		StringBuilder sb = new StringBuilder();
		EncCIM_AssignedIdentity emr = EncCIM_AssignedIdentity.CreateEncCIM_AssignedIdentity();
		for (java.util.Iterator<CIM_AssignedIdentity> e = m_assigned_identity.iterator(); e.hasNext();) {
			CIM_AssignedIdentity ec = e.next();
			sb.append(emr.Marshal(ec));
		}
		EncCIM_Identity emid = EncCIM_Identity.CreateEncCIM_Identity();
		for (java.util.Iterator<CIM_Identity> e = m_identity.iterator(); e.hasNext();) {
			CIM_Identity ec = e.next();
			sb.append(emid.Marshal(ec));
		}
		EncCIM_Account encacc = EncCIM_Account.CreateEncCIM_Account();
		for (java.util.Iterator<CIM_Account> e = m_account.iterator(); e.hasNext();) {
			CIM_Account ec = e.next();
			sb.append(encacc.Marshal(ec));
		}
		return sb.toString();
	}

	public static String InferSemantics(String xmldata) throws Exception {
		String semantics = null;
		String pattern = "<(\\w+)";
		java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
		java.util.regex.Matcher m = r.matcher(xmldata);
		if (m.find()) {
			semantics = m.group(0).substring(1);
		}
		return semantics;
	}

	public static void main(String[] args) throws Exception {
		UsersManager mr = new UsersManager("");
		mr.AddAssignedIdentity("cister", "superuser");
		mr.AddIdentity("superuser", "ultra powers");
		mr.AddAccount("michele", "segreto", "cister");
		mr.AddAccount("cesar", "segredo", "cister");
		String xmldata = mr.Marshal();
		System.out.println("XML data:\n"+xmldata);
		mr.ResetUsersManager();
		UsersManager mr2 = UsersManager.Unmarshal(xmldata, mr);
		System.out.println("XML data2:\n"+mr2.Marshal());

		String sql1 = mr.ToSql();
		System.out.println(sql1);

				
	}
}

