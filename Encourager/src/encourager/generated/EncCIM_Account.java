/**
EncCIM_AssignedIdentity.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager.generated;

import encourager.generated.cim_account.*;

public class EncCIM_Account extends BaseMessageFormat {

	public static EncCIM_Account CreateEncCIM_Account() {
		EncCIM_Account emr = new EncCIM_Account();
		if (!emr.Init(CIM_Account.class)) return null;
		return emr;
	}
	public CIM_Account Unmarshal(String xmldata) throws javax.xml.bind.JAXBException {return (CIM_Account)super.Unmarshal(xmldata);}
}
