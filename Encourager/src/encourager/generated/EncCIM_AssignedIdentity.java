/**
EncCIM_AssignedIdentity.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager.generated;

import encourager.generated.cim_assignedidentity.*;

public class EncCIM_AssignedIdentity extends BaseMessageFormat {

	public static EncCIM_AssignedIdentity CreateEncCIM_AssignedIdentity() {
		EncCIM_AssignedIdentity emr = new EncCIM_AssignedIdentity();
		if (!emr.Init(CIM_AssignedIdentity.class)) return null;
		return emr;
	}
	public CIM_AssignedIdentity Unmarshal(String xmldata) throws javax.xml.bind.JAXBException {return (CIM_AssignedIdentity)super.Unmarshal(xmldata);}
}
