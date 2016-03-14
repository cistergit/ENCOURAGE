/**
MeterConfigManager.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager;

import encourager.generated.meterconfig.*;
import encourager.generated.*;

import java.util.List;
import java.util.ArrayList;

public class MeterConfigManager {

	List<Meter> m_meters;
	public void add(Meter a) {m_meters.add(a);}

	private String m_semantics;

	public MeterConfigManager(String semantics) throws Exception {
		if (
			null != semantics &&
			!semantics.equals("CELLFLEXIBILITY") &&
			!"".equals(semantics)
		) {throw new Exception("bad semantics");}
		m_semantics = semantics;
		Reset();
	}

	public MeterConfigManager() {
		m_semantics = "";
		Reset();
	}

	public String getSemantics() {
		return m_semantics;
	}

	public void Reset() {
		m_meters = new ArrayList<Meter>();
	}

	/**
		If timestamp is not specified, it defaults to "now".
	*/
	public Meter AddCellFlexibility(
		String cell_id,
		javax.xml.datatype.XMLGregorianCalendar cellflex_datefrom,
		javax.xml.datatype.XMLGregorianCalendar cellflex_dateto, // TODO this way, it is a WEIRD HACK!!!
//		how many extra KW you can store/release (in the heating system?) from the house, without getting out of the comfort boundaries
		double cellflex_powercharge,
		double cellflex_powerrelease,
		double cellflex_storecharge,
		double cellflex_storerelease
	) throws Exception {
		if (!"CELLFLEXIBILITY".equals(getSemantics())) throw new Exception("wrong semantics for a cell flexibility");

		Meter m = new Meter();
		m.setType("FLEXIBILITY");
		m.setIsVirtual(true);
		m.setMRID(cell_id);

		// time can be in : Meter.ActivityRecord, Meter.ConfigurationEvent, Meter.Status
		ActivityRecord charged = new ActivityRecord();
		if (null == cellflex_datefrom) cellflex_datefrom = RabbitUtils.getXMLGregorianCalendarNow();
		charged.setCreatedDateTime(cellflex_datefrom);
		charged.setType("CAN_GIVE");
		charged.setReason(cellflex_storerelease+" KWH");
		charged.setSeverity(""+cellflex_powerrelease+" KW");
		m.getActivityRecords().add(charged);

		ActivityRecord can_still_recharge = new ActivityRecord();
		if (null != cellflex_dateto) can_still_recharge.setCreatedDateTime(cellflex_dateto);
		can_still_recharge.setType("CAN_RECEIVE");
		can_still_recharge.setReason(cellflex_storecharge+" KWH");
		can_still_recharge.setSeverity(cellflex_powercharge+" KW");
		m.getActivityRecords().add(can_still_recharge);

		add(m);
		return m;
	}

public String ToSqlCellFlexibility() throws Exception {
	StringBuffer sqlCommands = new StringBuffer();
	for (java.util.Iterator<Meter> e = m_meters.iterator(); e.hasNext();) {
		Meter m = e.next();
		String cell_id = m.getMRID();

		ActivityRecord can_still_recharge = null;
		ActivityRecord charged = m.getActivityRecords().get(0);
		if ("CAN_GIVE".equals(charged.getType())) {
			can_still_recharge = m.getActivityRecords().get(1);
		} else {
			can_still_recharge = charged;
			charged = m.getActivityRecords().get(1);
		}
		javax.xml.datatype.XMLGregorianCalendar cellflex_datefrom = charged.getCreatedDateTime();
		javax.xml.datatype.XMLGregorianCalendar cellflex_dateto = charged.getCreatedDateTime(); // it could be null, no problem.

		double cellflex_powercharge = Double.parseDouble(can_still_recharge.getSeverity().split(" ")[0]);
		double cellflex_powerrelease = Double.parseDouble(charged.getSeverity().split(" ")[0]);
		double cellflex_storecharge = Double.parseDouble(can_still_recharge.getReason().split(" ")[0]);
		double cellflex_storerelease = Double.parseDouble(charged.getReason().split(" ")[0]);

		sqlCommands.append("INSERT INTO cellflexibility (cell_id, cellflex_datefrom, cellflex_dateto, cellflex_powercharge, cellflex_powerrelease, cellflex_storecharge, cellflex_storerelease) VALUES ('");
		sqlCommands.append(cell_id);
		sqlCommands.append("', ");
		sqlCommands.append(cellflex_datefrom);
		sqlCommands.append(", '");
		sqlCommands.append(cellflex_dateto);
		sqlCommands.append("', '");
		sqlCommands.append(cellflex_powercharge);
		sqlCommands.append("', '");
		sqlCommands.append(cellflex_powerrelease);
		sqlCommands.append("', '");
		sqlCommands.append(cellflex_storecharge);
		sqlCommands.append("', '");
		sqlCommands.append(cellflex_storerelease);
		sqlCommands.append("');\n");
	}
	return sqlCommands.toString();
}


	public String ToSql() throws Exception {
		if ("CELLFLEXIBILITY".equals(m_semantics)) return ToSqlCellFlexibility();

		throw new Exception("specify the semantics, I see a "+m_semantics);
	}

	public String Marshal() {
		EncMeterConfig encoder = EncMeterConfig.CreateEncMeterConfig();
		MeterConfig edc = new MeterConfig();

		for (java.util.Iterator<Meter> e = m_meters.iterator(); e.hasNext();) {
			Meter ec = e.next();
			edc.getMeter().add(ec);
		}
		return encoder.Marshal(edc);
	}

	public String InferSemantics()
	{
		String semantics = null;
		for (java.util.Iterator<Meter> e = m_meters.iterator(); e.hasNext();) {
			Meter ec = e.next();
			if (ec.isIsVirtual() && ec.getType().equals("FLEXIBILITY")) semantics = "CELLFLEXIBILITY";
		}
		m_semantics = semantics;
		return semantics;
	}

	private static EncMeterConfig edcm = encourager.generated.EncMeterConfig.CreateEncMeterConfig(); // ALBANO remove this
	public static MeterConfigManager Unmarshal(String xml) throws Exception {
// ALBANO: decomment this		EncMeterConfig edcm = encourager.generated.EncMeterConfig.CreateEncMeterConfig();
		MeterConfig edc = edcm.Unmarshal(xml);

		MeterConfigManager ret = new MeterConfigManager();
		for (java.util.Iterator<Meter> e = edc.getMeter().iterator(); e.hasNext();) {
			Meter ec = e.next();
			ret.add(ec);
		}
		return ret;
	}

}

