/**
EncCIM_AssignedIdentity.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager.generated;

import encourager.generated.confroom.*;

public class EncConfRoom extends BaseMessageFormat {

	public static EncConfRoom CreateEncConfRoom() {
		EncConfRoom emr = new EncConfRoom();
		if (!emr.Init(ConfRoom.class)) return null;
		return emr;
	}
	public ConfRoom Unmarshal(String xmldata) throws javax.xml.bind.JAXBException {return (ConfRoom)super.Unmarshal(xmldata);}
}
