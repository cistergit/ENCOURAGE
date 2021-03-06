/**
EncMeterConfig.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager.generated;

import encourager.generated.meterconfig.*;

public class EncMeterConfig extends BaseMessageFormat {

	public static EncMeterConfig CreateEncMeterConfig() {
		EncMeterConfig emr = new EncMeterConfig();
		if (!emr.Init(MeterConfig.class)) return null;
		return emr;
	}
	public MeterConfig Unmarshal(String xmldata) throws javax.xml.bind.JAXBException {return (MeterConfig)super.Unmarshal(xmldata);}
}
