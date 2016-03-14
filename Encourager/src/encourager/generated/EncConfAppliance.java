/**
EncCIM_AssignedIdentity.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager.generated;

import encourager.generated.confappliance.*;

public class EncConfAppliance extends BaseMessageFormat {

	public static EncConfAppliance CreateEncConfAppliance() {
		EncConfAppliance emr = new EncConfAppliance();
		if (!emr.Init(ConfAppliance.class)) return null;
		return emr;
	}
	public ConfAppliance Unmarshal(String xmldata) throws javax.xml.bind.JAXBException {return (ConfAppliance)super.Unmarshal(xmldata);}
}
