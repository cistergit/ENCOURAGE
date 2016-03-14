/**
EncCIM_AssignedIdentity.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager.generated;

import encourager.generated.cim_identity.*;

public class EncCIM_Identity extends BaseMessageFormat {

	public static EncCIM_Identity CreateEncCIM_Identity() {
		EncCIM_Identity emr = new EncCIM_Identity();
		if (!emr.Init(CIM_Identity.class)) return null;
		return emr;
	}
	public CIM_Identity Unmarshal(String xmldata) throws javax.xml.bind.JAXBException {return (CIM_Identity)super.Unmarshal(xmldata);}
}
