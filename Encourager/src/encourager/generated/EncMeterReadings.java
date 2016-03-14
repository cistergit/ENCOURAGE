/**
EncMeterReadings.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager.generated;

import encourager.generated.meterreadings.*;

public class EncMeterReadings extends BaseMessageFormat {

	public static EncMeterReadings CreateEncMeterReadings() {
		EncMeterReadings emr = new EncMeterReadings();
		if (!emr.Init(MeterReadings.class)) return null;
		return emr;
	}
	public MeterReadings Unmarshal(String xmldata) throws javax.xml.bind.JAXBException {return (MeterReadings)super.Unmarshal(xmldata);}
}
