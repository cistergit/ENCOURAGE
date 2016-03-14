/**
EndDeviceConstrolsManager.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager;

import encourager.generated.enddevicecontrols.*;
import encourager.generated.*;

import java.math.BigDecimal;

import java.util.List;
import java.util.ArrayList;
import encourager.RabbitUtils;

public class EndDeviceControlsManager {
//Price signals are implemented as a subtype of EndDeviceControl, where the price is a message parameter.

	List<EndDeviceControl> m_controls;
	List<EndDeviceControlType> m_types;

	public void add(EndDeviceControl a) {m_controls.add(a);}
	public void add(EndDeviceControlType a) {m_types.add(a);}

// set at creation time. It can be: 
	private String m_semantics;

	public EndDeviceControlsManager(String semantics) throws Exception {
		if (
			null != semantics &&
			!semantics.equals("PRICE_UTIL") &&
			!semantics.equals("PRICE_MKT") &&
			!semantics.equals("PRICE_BROKER") &&
			!semantics.equals("PRICE_INTRADAY") &&
			!semantics.equals("TEXT") &&
			!semantics.equals("USERCMD") &&
			!semantics.equals("CONSUMPTIONORDER") &&
			!semantics.equals("SURPLUSNEEDS") &&
			!semantics.equals("CELLFLEXIBILITY") &&
			!"".equals(semantics)
		) {throw new Exception("bad semantics");}
		m_semantics = semantics;
		ResetEndDeviceControls();
	}

	public EndDeviceControlsManager() {
		m_semantics = "";
		ResetEndDeviceControls();
	}

	public String getSemantics() {
		return m_semantics;
	}

	public void ResetEndDeviceControls() {
		m_controls = new ArrayList<EndDeviceControl>();
		m_types = new ArrayList<EndDeviceControlType>();
	}


private PanDisplay CreatePanMessage(String command_id, String msg, double duration, javax.xml.datatype.XMLGregorianCalendar when) {
	PanDisplay pd = new PanDisplay();
	pd.setCommand(command_id);
	pd.setConfirmationRequired(false);
	if (duration < 0) {
		pd.setDurationIndefinite(true);
	} else {
		pd.setDuration((float)duration);
		pd.setDurationIndefinite(false);
	}
	if (null == when) when = RabbitUtils.getXMLGregorianCalendarNow();
	pd.setStartDateTime(when);
	pd.setTextMessage(msg);
	pd.setTransmissionMode(TransmissionModeKind.fromValue("both"));
	return pd;
}

private PanDemandResponse CreatePanDemandResponse(String control_value, double duration, javax.xml.datatype.XMLGregorianCalendar when) {
	PanDemandResponse pd = new PanDemandResponse();
	pd.setCommand(control_value);
	if (duration < 0) {
		pd.setDurationIndefinite(true);
	} else {
		pd.setDuration((float)duration);
		pd.setDurationIndefinite(false);
	}
	if (null == when) when = RabbitUtils.getXMLGregorianCalendarNow();
	pd.setStartDateTime(when);
	return pd;
}

private PanPricing CreatePanPricing(String currency, double price, double duration, javax.xml.datatype.XMLGregorianCalendar when) {
	PanPricing pd = new PanPricing();
	if (duration < 0) {
		pd.setDurationIndefinite(true);
	} else {
		pd.setDuration((float)duration);
		pd.setDurationIndefinite(false);
	}
	if (null == when) when = RabbitUtils.getXMLGregorianCalendarNow();
	pd.setStartDateTime(when);

	PanPricingDetail ppd = new PanPricingDetail();
    ppd.setPrice(new BigDecimal((Math.floor(price*100)/100), new java.math.MathContext(6)));
	ppd.setUnitOfMeasure(currency);

	pd.getPanPricingDetails().add(ppd);
	return pd;
}

	public EndDeviceTiming CreateEndDeviceTiming (
		javax.xml.datatype.XMLGregorianCalendar since,
		javax.xml.datatype.XMLGregorianCalendar until,
		double duration) throws Exception {

		if (null != until && 0 != duration) throw new Exception("error: you defind both 'until' and 'duration' in a Timing");

		if (null == since) since = RabbitUtils.getXMLGregorianCalendarNow();
		DateTimeInterval interval = new DateTimeInterval();
		interval.setStart(since);
		interval.setEnd(until); // should it be "null", it's no problem. Anyway, either this one, or duration
		EndDeviceTiming primaryDeviceTiming = new EndDeviceTiming();
		if (0 != duration) {
			if (duration > 0)
				primaryDeviceTiming.setDuration((float)duration);
			else
				primaryDeviceTiming.setDurationIndefinite(true);
		}
		primaryDeviceTiming.setInterval(interval);
		return primaryDeviceTiming;
	}

	public EndDeviceControl AddConsumptionOrder(
			String device_id, // cell_id is first part of device_id
			String cell_id, // cell_id is first part of device_id
			javax.xml.datatype.XMLGregorianCalendar datefrom,
			javax.xml.datatype.XMLGregorianCalendar dateto,
			double value // measure_type_id = "", unit_type_id = "KWh"
		) throws Exception {
		if (!"CONSUMPTIONORDER".equals(getSemantics())) throw new Exception("wrong semantics for a consumption order");

		EndDeviceControl control = new EndDeviceControl();

		String iecEndDeviceEventType = CimCodes.eventTypeFromGneraType(getSemantics()).ToCIMString();
		control.setEndDeviceControlType(new EndDeviceControl.EndDeviceControlType());
		control.getEndDeviceControlType().setRef(iecEndDeviceEventType);

		control.setPrimaryDeviceTiming(CreateEndDeviceTiming(datefrom, dateto, 0));

		PanDemandResponse pd = new PanDemandResponse();
		pd.setCommand(""+value);
		control.setPanDemandResponse(pd);

		EndDevice ed = new EndDevice();
		ed.setMRID(cell_id+"."+device_id);
		control.getEndDevices().add(ed);
		m_controls.add(control);
		return control;
	}

	public EndDeviceControl AddCellSurplusNeeds(
			String cell_id,
			javax.xml.datatype.XMLGregorianCalendar timestamp,
			double value // unit_type_id = "KWh"; POSITIVE is SURPLUS
		) throws Exception {
		if (!"SURPLUSNEEDS".equals(getSemantics())) throw new Exception("wrong semantics for a surplus needs");

		EndDeviceControl control = new EndDeviceControl();

		String iecEndDeviceEventType = CimCodes.eventTypeFromGneraType(getSemantics()).ToCIMString();
		control.setEndDeviceControlType(new EndDeviceControl.EndDeviceControlType());
		control.getEndDeviceControlType().setRef(iecEndDeviceEventType);

		control.setPrimaryDeviceTiming(CreateEndDeviceTiming(timestamp, null, 0));

		PanDemandResponse pd = new PanDemandResponse();
		pd.setCommand(""+value);
		control.setPanDemandResponse(pd);

		EndDevice ed = new EndDevice();
		ed.setMRID(cell_id);
		control.getEndDevices().add(ed);
		m_controls.add(control);
		return control;
	}

	public boolean AddCellFlexibility(
			String cell_id,
			javax.xml.datatype.XMLGregorianCalendar datefrom,
			javax.xml.datatype.XMLGregorianCalendar dateto,
			double energy_in,
			double energy_out,
			double power_in,
			double power_out // unit_energy_id = "KW"; unit_store_id = "KWH".
		) throws Exception {
		if (!"CELLFLEXIBILITY".equals(getSemantics())) throw new Exception("wrong semantics for a cell flexibility");

		double value = 0;
		String gnera_type = null;
		for (int i=0;i<4;i++) {
			switch (i) {
			case 0:
				value = energy_in;
				gnera_type = "MAXINENERGY";
				break;
			case 1:
				value = energy_out;
				gnera_type = "MAXOUTENERGY";
				break;
			case 2:
				value = power_in;
				gnera_type = "MAXINPOWER";
				break;
			case 3:
				value = power_out;
				gnera_type = "MAXOUTPOWER";
				break;
			default:
				throw new Exception("wrong switch/case variable in AddCellFlexibility");
//				break;
			}
			EndDeviceControl control = new EndDeviceControl();
			String iecEndDeviceEventType = CimCodes.eventTypeFromGneraType(gnera_type).ToCIMString();
			control.setEndDeviceControlType(new EndDeviceControl.EndDeviceControlType());
			control.getEndDeviceControlType().setRef(iecEndDeviceEventType);

			control.setPrimaryDeviceTiming(CreateEndDeviceTiming(datefrom, dateto, 0));
			PanDemandResponse pd = new PanDemandResponse();
			pd.setCommand(""+value);
			control.setPanDemandResponse(pd);

			EndDevice ed = new EndDevice();
			ed.setMRID(cell_id);
			control.getEndDevices().add(ed);
			m_controls.add(control);
		}
		return true;
	}


	public EndDeviceControl AddEndDeviceControlText(
		String command_id,
		javax.xml.datatype.XMLGregorianCalendar since,
		double duration,
		String issuerID,
		String reason,
		String endDevicemRID,
		String msgPanDisplay
	) throws Exception {
		if (!"TEXT".equals(getSemantics())) throw new Exception("wrong semantics for a text message");
		if (
				!command_id.equals("Alarm") &&
				!command_id.equals("Alert") &&
				!command_id.equals("Advice")
			) throw new Exception("BAD command_id: should be Alarm, Alert or Advice");

		EndDeviceControl control = new EndDeviceControl();

		String iecEndDeviceEventType = CimCodes.eventTypeFromGneraType(getSemantics()).ToCIMString();
		control.setEndDeviceControlType(new EndDeviceControl.EndDeviceControlType());
		control.getEndDeviceControlType().setRef(iecEndDeviceEventType);

		control.setPrimaryDeviceTiming(CreateEndDeviceTiming(since, null, duration));
		control.setIssuerID(issuerID);
		control.setReason(reason);
		control.setPanDisplay(CreatePanMessage(command_id, msgPanDisplay, duration, since));

		EndDevice ed = new EndDevice();
		ed.setMRID(endDevicemRID);
		control.getEndDevices().add(ed);
		m_controls.add(control);
		return control;
	}

	public EndDeviceControl AddEndDeviceControlPrice(
		javax.xml.datatype.XMLGregorianCalendar since,
		javax.xml.datatype.XMLGregorianCalendar time_of_application,// if the price is a forecast / future intraday price
		String macrocell_id,
		double price,
		String currency
	) throws Exception {
		String issuerID;
		String measure_type_id = null;

		if ("PRICE_UTIL".equals(getSemantics())) {issuerID = "utilities";}
		else if ("PRICE_MKT".equals(getSemantics())) {issuerID = "market";}
		else if ("PRICE_BROKER".equals(getSemantics())) {issuerID = "EB";}
		else if ("PRICE_INTRADAY".equals(getSemantics())) {issuerID = "mkt_intraday";}
		else throw new Exception("wrong semantics for a pricing command");

		if (null == measure_type_id) {
			if ("EKWH".equals(currency)) measure_type_id = "E-PRICEEU";
			else if ("DKWH".equals(currency)) measure_type_id = "E-PRICEDK";
			else throw new Exception("bad price unit in a local market");
		}

		EndDeviceControl control = new EndDeviceControl();

		String iecEndDeviceEventType = CimCodes.eventTypeFromGneraType(getSemantics()).ToCIMString();
		control.setEndDeviceControlType(new EndDeviceControl.EndDeviceControlType());
		control.getEndDeviceControlType().setRef(iecEndDeviceEventType);

		if (null!=issuerID) control.setIssuerID(issuerID);
//		control.setReason(reason);
		if (time_of_application == null) {
			control.setPanPricing(CreatePanPricing(currency, price, 0, since));
		} else {
			control.setPrimaryDeviceTiming(CreateEndDeviceTiming(since, null, 0));
			control.setPanPricing(CreatePanPricing(currency, price, 0, time_of_application));
		}

		EndDevice ed = new EndDevice();
		ed.setMRID(macrocell_id);
		control.getEndDevices().add(ed);
		m_controls.add(control);
		return control;
	}



/**
	If timestamp is not specified, it defaults to "now".
	the control type is in the GNERA format (see CimCodes.java for the list of the codes)
	when having an explicit type, insert the EndDeviceControlType into the list of EndDeviceControlTypes. Usually it's done for configuration only
*/
	public EndDeviceControl AddUserControl(
		javax.xml.datatype.XMLGregorianCalendar since,
//		javax.xml.datatype.XMLGregorianCalendar until,
		double duration,
		String issuerID,
		String reason,
		String control_type_id,
		String control_value,
		String endDevicemRID
//		boolean typeExplicit,
	) throws Exception {
		if (!"USERCMD".equals(getSemantics())) throw new Exception("wrong semantics for a user command");
		EndDeviceControl control = new EndDeviceControl();

		String iecEndDeviceEventType = CimCodes.eventTypeFromGneraType(control_type_id/*getSemantics()*/).ToCIMString();
		control.setEndDeviceControlType(new EndDeviceControl.EndDeviceControlType());
		control.getEndDeviceControlType().setRef(iecEndDeviceEventType);

		control.setPrimaryDeviceTiming(CreateEndDeviceTiming(since, null, duration));
		control.setIssuerID(issuerID);
		control.setReason(reason);

		control.setPanDemandResponse(CreatePanDemandResponse(control_value, /*control_type_id, */duration, since));

		EndDevice ed = new EndDevice();
		ed.setMRID(endDevicemRID);
		control.getEndDevices().add(ed);
		m_controls.add(control);
		return control;
	}


	
	/** fills up all the fields of a EndDeviceControlType's XML from its CIM name */
	private EndDeviceControlType BuildEndDeviceControlType(String controlName) {
		String[] p = controlName.split("\\.");
		EndDeviceControlType aEndDeviceControlType = new EndDeviceControlType();
	
		Name name = new Name();
		name.setName(controlName);
		aEndDeviceControlType.getNames().add(name);
	
		aEndDeviceControlType.setType(p[0]);
		aEndDeviceControlType.setDomain(p[1]);
		aEndDeviceControlType.setSubDomain(p[2]);
		aEndDeviceControlType.setEventOrAction(p[3]);
	
		return aEndDeviceControlType;
	}

	/** builds up a CIM EndDeviceControlType name from its XML */
	private String BuildEndDeviceControlTypeName(EndDeviceControlType aEndDeviceControlType) {
		String[] p = new String[4];
		p[0] = aEndDeviceControlType.getType();
		p[1] = aEndDeviceControlType.getDomain();
		p[2] = aEndDeviceControlType.getSubDomain();
		p[3] = aEndDeviceControlType.getEventOrAction();
	
		StringBuffer EndDeviceControlTypeName = new StringBuffer();
		for (int i=0;i<3;i++) EndDeviceControlTypeName.append(p[i]+".");
		EndDeviceControlTypeName.append(p[3]);
	
		Name name = new Name();
		name.setName(EndDeviceControlTypeName.toString());
		aEndDeviceControlType.getNames().add(name);
	
		return EndDeviceControlTypeName.toString();
	}

public String ToSqlCellFlexibility() throws Exception {
	String cell_id=null;
	String date_from=null;
	String date_to=null;
	String energy_in=null;
	String energy_out=null;
	String power_in=null;
	String power_out=null;
	int state = 0;
	StringBuffer sqlCommands = new StringBuffer();
	for (java.util.Iterator<EndDeviceControl> e = m_controls.iterator(); e.hasNext();) {
		EndDeviceControl control = e.next();
		String device_id = control.getEndDevices().get(0).getMRID();
		if (!device_id.startsWith("CELL_")) throw new Exception("invalid cell_id");
		device_id = device_id.substring(5);
		String t_from = CimCodes.DateCIMtoGnera(control.getPrimaryDeviceTiming().getInterval().getStart().toString());
		String t_to = CimCodes.DateCIMtoGnera(control.getPrimaryDeviceTiming().getInterval().getEnd().toString());
		if (0 == state) {
			cell_id = device_id;
			date_from = t_from;
			date_to = t_to;
		} else
			if (!cell_id.equals(device_id) || !date_from.equals(t_from) || !date_to.equals(t_to))
				throw new Exception("mismatch in cell flexibility messsage");

		String value = control.getPanDemandResponse().getCommand();
		String iecEndDeviceEventType = control.getEndDeviceControlType().getRef();
		CimCodes.EventType rt = CimCodes.eventTypeFromCIM(iecEndDeviceEventType);
		String event_id = rt.GneraCode;
		if ("MAXINPOWER".equals(event_id)) power_in = value;
		else if ("MAXOUTPOWER".equals(event_id)) power_out = value;
		else if ("MAXINENERGY".equals(event_id)) energy_in = value;
		else if ("MAXOUTENERGY".equals(event_id)) energy_out = value;
		else throw new Exception("wrong IEC code in cell flexibility message");
		state ++;

		if (4 == state) {
			sqlCommands.append("INSERT INTO MIDDLEWARE.cellflexibility (cell_id, cellflex_dateto, cellflex_datefrom, cellflex_storrelase, cellflex_storcharge, cellflex_powercharge, cellflex_powerrelase, unit_power_id, unit_store_id) VALUES ('");
			sqlCommands.append(cell_id);
			sqlCommands.append("', '");
			sqlCommands.append(date_to);
			sqlCommands.append("', '");
			sqlCommands.append(date_from);
			sqlCommands.append("', ");
			sqlCommands.append(energy_out);
			sqlCommands.append(", ");
			sqlCommands.append(energy_in);
			sqlCommands.append(", ");
			sqlCommands.append(power_in);
			sqlCommands.append(", ");
			sqlCommands.append(power_out);
			sqlCommands.append(", '");
			sqlCommands.append("KW");
			sqlCommands.append("', '");
			sqlCommands.append("KWH");
			sqlCommands.append("');\n");
			state = 0;
		}
	}
	return sqlCommands.toString();
}

public String ToSqlPriceIntraday() throws Exception {
	StringBuffer sqlCommands = new StringBuffer();
	for (java.util.Iterator<EndDeviceControl> e = m_controls.iterator(); e.hasNext();) {
		EndDeviceControl control = e.next();
		String macrocell_id = control.getEndDevices().get(0).getMRID();
		if (!macrocell_id.startsWith("MACRO_")) throw new Exception("invalid macrocell_id");
		macrocell_id = macrocell_id.substring(6);

		String timestamp = CimCodes.DateCIMtoGnera(control.getPrimaryDeviceTiming().getInterval().getStart().toString());
		javax.xml.datatype.XMLGregorianCalendar intraday_time = control.getPanPricing().getStartDateTime();
		int intraday = (int)(intraday_time.getHour()/3);
		PanPricingDetail ppd = control.getPanPricing().getPanPricingDetails().get(0);
		String unit_type_id = ppd.getUnitOfMeasure();
		double value = ppd.getPrice().doubleValue();

		sqlCommands.append("INSERT INTO MIDDLEWARE.price_energy (macrocell_id, price_energy_intraday, price_energy_time, price_energy_intraday_value, unit_type_id) VALUES ('");
		sqlCommands.append(macrocell_id);
		sqlCommands.append("', ");
		sqlCommands.append(intraday);
		sqlCommands.append(", '");
		sqlCommands.append(timestamp);
		sqlCommands.append("', ");
		sqlCommands.append(value);
		sqlCommands.append(", '");
		sqlCommands.append(unit_type_id);
		sqlCommands.append("');\n");
	}
	return sqlCommands.toString();
}

public String ToSqlPriceMarket() throws Exception {
	StringBuffer sqlCommands = new StringBuffer();
	for (java.util.Iterator<EndDeviceControl> e = m_controls.iterator(); e.hasNext();) {
		EndDeviceControl control = e.next();
		String macrocell_id = control.getEndDevices().get(0).getMRID();
		if (!macrocell_id.startsWith("MACRO_")) throw new Exception("invalid macrocell_id");
		macrocell_id = macrocell_id.substring(6);

		String price_local_time = CimCodes.DateCIMtoGnera(control.getPanPricing().getStartDateTime().toString());
		PanPricingDetail ppd = control.getPanPricing().getPanPricingDetails().get(0);
		String unit_type_id = ppd.getUnitOfMeasure();
		double price_local_value = ppd.getPrice().doubleValue();

		sqlCommands.append("INSERT INTO MIDDLEWARE.price_local_markets (macrocell_id, price_local_time, price_local_value, unit_type_id) VALUES ('");
		sqlCommands.append(macrocell_id);
		sqlCommands.append("', '");
		sqlCommands.append(price_local_time);
		sqlCommands.append("', ");
		sqlCommands.append(price_local_value);
		sqlCommands.append(", '");
		sqlCommands.append(unit_type_id);
		sqlCommands.append("');\n");
	}
	return sqlCommands.toString();
}

public String ToSqlPriceBroker() throws Exception {
	StringBuffer sqlCommands = new StringBuffer();
	for (java.util.Iterator<EndDeviceControl> e = m_controls.iterator(); e.hasNext();) {
		EndDeviceControl control = e.next();
		String macrocell_id = control.getEndDevices().get(0).getMRID();
		if (!macrocell_id.startsWith("MACRO_")) throw new Exception("invalid macrocell_id");
		macrocell_id = macrocell_id.substring(6);

		String price_local_time = CimCodes.DateCIMtoGnera(control.getPanPricing().getStartDateTime().toString());
		PanPricingDetail ppd = control.getPanPricing().getPanPricingDetails().get(0);
		String unit_type_id = ppd.getUnitOfMeasure();
		double price_local_value = ppd.getPrice().doubleValue();

		sqlCommands.append("INSERT INTO MIDDLEWARE.price_local_market (macrocell_id, price_local_time, price_local_value, unit_type_id) VALUES ('");
		sqlCommands.append(macrocell_id);
		sqlCommands.append("', '");
		sqlCommands.append(price_local_time);
		sqlCommands.append("', ");
		sqlCommands.append(price_local_value);
		sqlCommands.append(", '");
		sqlCommands.append(unit_type_id);
		sqlCommands.append("');\n");
	}
	return sqlCommands.toString();
}

public String ToSqlPriceUtilities() throws Exception {
	StringBuffer sqlCommands = new StringBuffer();
	sqlCommands.append("NOT SUPPORTED\n");
	return sqlCommands.toString();
}

public String ToSqlUserCommand() throws Exception {
	StringBuffer sqlCommands = new StringBuffer();
	for (java.util.Iterator<EndDeviceControl> e = m_controls.iterator(); e.hasNext();) {
		EndDeviceControl control = e.next();
		String device_id = control.getEndDevices().get(0).getMRID();
		String comanduser_value = control.getPanDemandResponse().getCommand();
		String device_event_time = CimCodes.DateCIMtoGnera(control.getPanDemandResponse().getStartDateTime().toString());
		int duration_time = (int)(control.getPanDemandResponse().getDuration().floatValue());
		String issuerId = control.getIssuerID();

		String iecEndDeviceEventType = control.getEndDeviceControlType().getRef();
		CimCodes.EventType rt = CimCodes.eventTypeFromCIM(iecEndDeviceEventType);
		String event_id = rt.GneraCode;

		sqlCommands.append("INSERT INTO MIDDLEWARE.commanduser (comanduser_value, user_id, device_command_time, device_id, command_id, duration_time_minutes) VALUES (");
		sqlCommands.append(comanduser_value);
		sqlCommands.append(", '");
		sqlCommands.append(issuerId);
		sqlCommands.append("', '");
		sqlCommands.append(device_event_time);
		sqlCommands.append("', '");
		sqlCommands.append(device_id);
		sqlCommands.append("', '");
		sqlCommands.append(event_id);
		sqlCommands.append("', ");
		sqlCommands.append(duration_time);
		sqlCommands.append(");\n");
	}
	return sqlCommands.toString();
}

public String ToSqlSurplusNeeds() throws Exception {
	StringBuffer sqlCommands = new StringBuffer();

	for (java.util.Iterator<EndDeviceControl> e = m_controls.iterator(); e.hasNext();) {
		EndDeviceControl control = e.next();

		String cell_id = control.getEndDevices().get(0).getMRID();
		if (!cell_id.startsWith("CELL_")) throw new Exception("invalid cell_id");
		cell_id = cell_id.substring(5);

		String timestamp = CimCodes.DateCIMtoGnera(control.getPrimaryDeviceTiming().getInterval().getStart().toString());
		String value = control.getPanDemandResponse().getCommand();

		String /*gnera*/unit_type_id = "KW";


		sqlCommands.append("INSERT INTO MIDDLEWARE.cellsurplusneeds (cell_id, cellsurplusneeds_time, cellsurplusneeds_value, unit_type_id) VALUES ('");
		sqlCommands.append(cell_id);
		sqlCommands.append("', '");
		sqlCommands.append(timestamp);
		sqlCommands.append("', ");
		sqlCommands.append(value);
		sqlCommands.append(", '");
		sqlCommands.append(unit_type_id);
		sqlCommands.append("');\n");
	}

	return sqlCommands.toString();
}




public String ToSqlConsumptionOrder() throws Exception {
	StringBuffer sqlCommands = new StringBuffer();

	for (java.util.Iterator<EndDeviceControl> e = m_controls.iterator(); e.hasNext();) {
		EndDeviceControl control = e.next();

		String device_id = control.getEndDevices().get(0).getMRID();
		String cell_id = device_id.substring(0,device_id.lastIndexOf("."));
		device_id = device_id.substring(device_id.lastIndexOf(".")+1);

		javax.xml.datatype.XMLGregorianCalendar datefrom = control.getPrimaryDeviceTiming().getInterval().getStart();
		javax.xml.datatype.XMLGregorianCalendar dateto = control.getPrimaryDeviceTiming().getInterval().getEnd();
		String value = control.getPanDemandResponse().getCommand();

		String consumptionorder_from = CimCodes.DateCIMtoGnera(datefrom.toString());
		String consumptionorder_to = CimCodes.DateCIMtoGnera(dateto.toString());

		String /*gnera*/measure_type_id = "E-ELEC";
		String /*gnera*/unit_type_id = "KWH";


		sqlCommands.append("INSERT INTO MIDDLEWARE.consumptionorders (device_id, cell_id, consumptionorder_dateto, consumptionorder_datefrom, consumptionorder_value, unit_type_id, measure_type_id) VALUES ('");
		sqlCommands.append(device_id);
		sqlCommands.append("', '");
		sqlCommands.append(cell_id);
		sqlCommands.append("', '");
		sqlCommands.append(consumptionorder_to);
		sqlCommands.append("', '");
		sqlCommands.append(consumptionorder_from);
		sqlCommands.append("', ");
		sqlCommands.append(value);
		sqlCommands.append(", '");
		sqlCommands.append(unit_type_id);
		sqlCommands.append("', '");
		sqlCommands.append(measure_type_id);
		sqlCommands.append("');\n");
	}

	return sqlCommands.toString();
}


public String ToSqlTextMessage() throws Exception {
	StringBuffer sqlCommands = new StringBuffer();

	for (java.util.Iterator<EndDeviceControl> e = m_controls.iterator(); e.hasNext();) {
		EndDeviceControl control = e.next();
		String cell_id = control.getEndDevices().get(0).getMRID();
		if (!cell_id.startsWith("CELL_")) throw new Exception("invalid cell_id");
		cell_id = cell_id.substring(5);

		String advice_category_id = control.getPanDisplay().getCommand();

		String advice_text = control.getPanDisplay().getTextMessage();
		String h_advice_to_user_time = CimCodes.DateCIMtoGnera(control.getPanDisplay().getStartDateTime().toString());

		sqlCommands.append("INSERT INTO MIDDLEWARE.h_advice_to_user (advice_category_id, advice_text, cell_id, h_advice_to_user_time) VALUES ('");
		sqlCommands.append(advice_category_id);
		sqlCommands.append("', '");
		sqlCommands.append(advice_text);
		sqlCommands.append("', '");
		sqlCommands.append(cell_id);
		sqlCommands.append("', '");
		sqlCommands.append(h_advice_to_user_time);
		sqlCommands.append("');\n");
	}

	return sqlCommands.toString();
}

public String ToSql() throws Exception {
	if ("PRICE_UTIL".equals(m_semantics)) return ToSqlPriceUtilities();
	if ("PRICE_MKT".equals(m_semantics)) return ToSqlPriceMarket();
	if ("PRICE_BROKER".equals(m_semantics)) return ToSqlPriceBroker();
	if ("PRICE_INTRADAY".equals(m_semantics)) return ToSqlPriceIntraday();
	if ("TEXT".equals(m_semantics)) return ToSqlTextMessage();
	if ("CONSUMPTIONORDER".equals(m_semantics)) return ToSqlConsumptionOrder();
	if ("SURPLUSNEEDS".equals(m_semantics)) return ToSqlSurplusNeeds();
	if ("CELLFLEXIBILITY".equals(m_semantics)) return ToSqlCellFlexibility();
	/*if ("USERCMD".equals(m_semantics)) */return ToSqlUserCommand();

//	throw new Exception("specify the semantics!");
}

	public String Marshal() {
		EncEndDeviceControls encoder = EncEndDeviceControls.CreateEncEndDeviceControls();
		EndDeviceControls edc = new EndDeviceControls();

		for (java.util.Iterator<EndDeviceControlType> e = m_types.iterator(); e.hasNext();) {
			EndDeviceControlType ec = e.next();
			edc.getEndDeviceControlType().add(ec);
		}
		for (java.util.Iterator<EndDeviceControl> e = m_controls.iterator(); e.hasNext();) {
			EndDeviceControl ec = e.next();
			edc.getEndDeviceControl().add(ec);
		}
		return encoder.Marshal(edc);
	}

	public String InferSemantics()
	{
		for (java.util.Iterator<EndDeviceControl> e = m_controls.iterator(); e.hasNext();) {
			EndDeviceControl ec = e.next();
			String cimEventType = ec.getEndDeviceControlType().getRef();
			CimCodes.EventType rt = CimCodes.eventTypeFromCIM(cimEventType);
			m_semantics = rt.GneraCode;
			if ("MAXINPOWER".equals(rt.GneraCode) || "MAXOUTPOWER".equals(rt.GneraCode) ||
					"MAXINENERGY".equals(rt.GneraCode) || "MAXOUTENERGY".equals(rt.GneraCode))
				m_semantics = "CELLFLEXIBILITY";
			return m_semantics;
		}
		return null;
	}

	private static EncEndDeviceControls edcm = encourager.generated.EncEndDeviceControls.CreateEncEndDeviceControls();
	public static EndDeviceControlsManager Unmarshal(String xml) throws Exception {
		EndDeviceControls edc = edcm.Unmarshal(xml);

		EndDeviceControlsManager ret = new EndDeviceControlsManager();
		for (java.util.Iterator<EndDeviceControlType> e = edc.getEndDeviceControlType().iterator(); e.hasNext();) {
			EndDeviceControlType ec = e.next();
			ret.add(ec);
		}
		for (java.util.Iterator<EndDeviceControl> e = edc.getEndDeviceControl().iterator(); e.hasNext();) {
			EndDeviceControl ec = e.next();
			ret.add(ec);
		}
		return ret;
	}

	public static void main(String[] args) throws Exception {
		String device_id = "laser001";
		EndDeviceControlsManager edc = new EndDeviceControlsManager("USERCMD");

		String issuer="UNUSED FIELD";

		javax.xml.datatype.XMLGregorianCalendar when = RabbitUtils.getXMLGregorianCalendarNow();
		when.setDay(15);
		when.setMonth(9);
		when.setYear(2013);
		when.setTime(5, 15, 0);

		edc.AddUserControl(when, 1, issuer, null, "LOADCONTROLSTATUS", "enable", device_id);
		edc.AddUserControl(when, 1, issuer, null, "SETPOINTCHANGE", "22", device_id);
		edc.AddUserControl(when, 1, issuer, null, "SETPOINTRESET", "default", device_id);
		edc.AddUserControl(when, 1, issuer, null, "SETPOINTENABLE", "FALSE", device_id);
		edc.AddUserControl(when, 1, issuer, null, "FLOWCHANGE", "0", device_id);


		String sql1 = edc.ToSql();
		System.out.println(sql1);
		String xmldata = edc.Marshal();
		System.out.println("XML data:\n"+xmldata);
		System.out.println("XML semantics: '"+CIMWrapper.InferSemantics(xmldata)+"'");
		edc.ResetEndDeviceControls();
		String sqltrash = edc.ToSql();
		System.out.println("new SQL: "+sqltrash);
		String xmldatatrash = edc.Marshal();
		System.out.println("XML data:\n"+xmldatatrash);
		System.out.println("XML semantics: '"+CIMWrapper.InferSemantics(xmldatatrash)+"'");

		EndDeviceControlsManager edc2 = EndDeviceControlsManager.Unmarshal(xmldata);
		edc2.InferSemantics();

		String sql2 = edc2.ToSql();
		System.out.println("last SQL: "+sql2);
		if (!sql2.equals(sql1)) throw new Exception("inconsistencies in SQL!");

	}

}

