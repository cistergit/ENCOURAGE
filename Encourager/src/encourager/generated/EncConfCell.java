/**
EncCIM_AssignedIdentity.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager.generated;

import encourager.generated.confcell.*;

public class EncConfCell extends BaseMessageFormat {

	public static EncConfCell CreateEncConfCell() {
		EncConfCell emr = new EncConfCell();
		if (!emr.Init(ConfCell.class)) return null;
		return emr;
	}
	public ConfCell Unmarshal(String xmldata) throws javax.xml.bind.JAXBException {return (ConfCell)super.Unmarshal(xmldata);}
}
