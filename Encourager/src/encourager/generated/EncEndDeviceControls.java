/**
EncEndDeviceControls.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager.generated;

import encourager.generated.enddevicecontrols.*;

public class EncEndDeviceControls extends BaseMessageFormat {

	public static EncEndDeviceControls CreateEncEndDeviceControls() {
		EncEndDeviceControls emr = new EncEndDeviceControls();
		if (!emr.Init(EndDeviceControls.class)) return null;
		return emr;
	}
	public EndDeviceControls Unmarshal(String xmldata) throws javax.xml.bind.JAXBException {return (EndDeviceControls)super.Unmarshal(xmldata);}
}
