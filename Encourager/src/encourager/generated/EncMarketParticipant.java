/**
EncEndDeviceConfig.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager.generated;

import encourager.generated.marketparticipant.*;

public class EncMarketParticipant extends BaseMessageFormat {

	public static EncMarketParticipant CreateEncMarketParticipant() {
		EncMarketParticipant emr = new EncMarketParticipant();
		if (!emr.Init(MarketParticipant.class)) return null;
		return emr;
	}
	public MarketParticipant Unmarshal(String xmldata) throws javax.xml.bind.JAXBException {return (MarketParticipant)super.Unmarshal(xmldata);}
}
