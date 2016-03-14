/**
MeterReadingsManager.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager;

import encourager.generated.meterreadings.*;
import encourager.generated.*;

import java.util.List;
import java.util.ArrayList;

public class MeterReadingsManager {
// if more than one, it means it's an energy exchange
	private List<MeterReading> m_meter_readings;
// all the data is related to ONE meter:
	private Meter m_meter;
// the Readings we are movind around:
	private List<Reading> m_readings;
// should we have "explicit" ReadingTypes, they stay here:
	private List<ReadingType> m_readingTypes;
// endDeviceEvents encode the alarms
	private List<EndDeviceEvent> m_endDeviceEvents;
// set at creation time. It can be: see the creator
	private String m_semantics;

	private static EncMeterReadings edcm = encourager.generated.EncMeterReadings.CreateEncMeterReadings(); // ALBANO remove this
	public static MeterReadingsManager Unmarshal(String xml) throws Exception {
// ALBANO decomment this		EncMeterReadings edcm = encourager.generated.EncMeterReadings.CreateEncMeterReadings();
		MeterReadings edc = edcm.Unmarshal(xml);
//		EncMeterReadings.dump(edc, 0, null);

		MeterReadingsManager ret = new MeterReadingsManager(null);

		for (java.util.Iterator<ReadingType> e = edc.getReadingType().iterator(); e.hasNext();) {
			ReadingType ec = e.next();
			ret.m_readingTypes.add(ec);
		}
		if (0 == edc.getMeterReading().size()%2 && 0 < edc.getMeterReading().size()) {
			// more than 1 MeterReading, so it's an energy exchange:
			for (java.util.Iterator<MeterReading> e = edc.getMeterReading().iterator(); e.hasNext();) {
				MeterReading mr1 = e.next();
				ret.m_meter_readings.add(mr1);
			}
			return ret;
		}
		MeterReading mr1 = null;
		for (java.util.Iterator<MeterReading> e = edc.getMeterReading().iterator(); e.hasNext();) {
			if (null == mr1) {
				mr1 = e.next();
			} else {
				throw new Exception("Currently NOT supporting more than 1 Meter in a MeterReadings XML structure.");
			}
		}
		ret.m_meter = mr1.getMeter();
		for (java.util.Iterator<Reading> e = mr1.getReadings().iterator(); e.hasNext();) {
			Reading ec = e.next();
			ret.m_readings.add(ec);
		}
		for (java.util.Iterator<EndDeviceEvent> e = mr1.getEndDeviceEvents().iterator(); e.hasNext();) {
			EndDeviceEvent ec = e.next();
			ret.m_endDeviceEvents.add(ec);
		}

		return ret;
	}

	public MeterReadingsManager(String semantics) throws Exception {
		if (
			null != semantics &&
			!semantics.equals("ENXCHANGE") &&
			!semantics.equals("WEATHER") &&
			!semantics.equals("FORECAST") &&
			!semantics.equals("COMFORT") &&
			!semantics.equals("MEASURE") &&
			!semantics.equals("OCCUPANCY") &&
			!semantics.equals("LOCALPRICE") &&
			!semantics.equals("ALARM") &&
			!semantics.equals("NILM") &&
			!"".equals(semantics)
		) {throw new Exception("bad semantics");}
		m_semantics = semantics;
		ResetMeterReadings();
	}

	public String getSemantics() {
		return m_semantics;
	}

	public void ResetMeterReadings() {
		m_meter_readings = new ArrayList<MeterReading>();
		m_readings = new ArrayList<Reading>();
		m_readingTypes = new ArrayList<ReadingType>();
		m_endDeviceEvents = new ArrayList<EndDeviceEvent>();
		m_meter = null;
	}

/**
The meter can have a mRID, a serial code, or both.
Optional, we can set the name authority that assigned the serial code.
*/
	public Meter SetMeter(String mRID, String meterSerialCode, String nameAuthority) {
		if (
			(null == mRID && null == meterSerialCode) ||
			(null == meterSerialCode && null != nameAuthority)
		) return null;

		Meter meter = new Meter();
		if (null != mRID) {meter.setMRID(mRID);}
		if (null != meterSerialCode) {
			Name name = new Name();
			name.setName(meterSerialCode);
			if (null != nameAuthority) {
				NameType nametype = new NameType();
				nametype.setDescription("This is an endpoint serial number");
				nametype.setName("EndpointID");
				NameTypeAuthority nta = new NameTypeAuthority();
		//		nta.setDescription("AssetManagementSystem");
				nta.setName(nameAuthority);
				nametype.setNameTypeAuthority(nta);
				name.setNameType(nametype);
			}
			meter.getNames().add(name);
		}
		m_meter = meter;
		return meter;
	}

	public Reading AddReadingsForecast(javax.xml.datatype.XMLGregorianCalendar ts, double value, String measure_type_id, javax.xml.datatype.XMLGregorianCalendar reportedDateTime, boolean typeExplicit) throws Exception {
		Reading reading = AddReadings(ts, value, measure_type_id, typeExplicit);
		ReadingQuality rq = BuildQuality("FORECAST");
		reading.getReadingQualities().add(rq);
		if (null == reportedDateTime) reportedDateTime = RabbitUtils.getXMLGregorianCalendarNow();
		reading.setReportedDateTime((javax.xml.datatype.XMLGregorianCalendar)reportedDateTime.clone());
		return reading;
	}

	private String MakeItMaxThreshold(String measure_type_id) {
		int firstpoint = measure_type_id.indexOf(".");
		int secondpoint = measure_type_id.indexOf(".", firstpoint+1);
		return measure_type_id.substring(0, firstpoint+1) + "5" + measure_type_id.substring(secondpoint);
	}

	private String MakeItMinThreshold(String measure_type_id) {
		int firstpoint = measure_type_id.indexOf(".");
		int secondpoint = measure_type_id.indexOf(".", firstpoint+1);
		return measure_type_id.substring(0, firstpoint+1) + "7" + measure_type_id.substring(secondpoint);
	}

	private int IsItThreshold(String measure_type_id) {
		if (measure_type_id.length()<35) return 0;
		int firstpoint = measure_type_id.indexOf(".");
		int secondpoint = measure_type_id.indexOf(".", firstpoint+1);
		return Integer.parseInt(measure_type_id.substring(firstpoint+1, secondpoint));
	}

	public boolean AddThresholds(javax.xml.datatype.XMLGregorianCalendar starttime, javax.xml.datatype.XMLGregorianCalendar endtime, double max_value, double min_value, String measure_type_id, boolean typeExplicit) throws Exception {
		Reading readingmax = new Reading();
		Reading readingmin = new Reading();
		DateTimeInterval dti = new DateTimeInterval();
		if (null == starttime) starttime = RabbitUtils.getXMLGregorianCalendarNow();
		else starttime = (javax.xml.datatype.XMLGregorianCalendar)starttime.clone();
		if (null == endtime) endtime = RabbitUtils.getXMLGregorianCalendarNow();
		else endtime = (javax.xml.datatype.XMLGregorianCalendar)endtime.clone();
		dti.setStart(starttime);
		dti.setEnd(endtime);
		readingmax.setTimePeriod(dti);
		readingmin.setTimePeriod(dti);
		readingmax.setValue(""+max_value);
		readingmin.setValue(""+min_value);
		String typeName = CimCodes.rtFromMeasureTypeId(measure_type_id).ToCIMString();
		readingmax.setReadingType(new Reading.ReadingType());
		readingmax.getReadingType().setRef(MakeItMaxThreshold(typeName));
		readingmin.setReadingType(new Reading.ReadingType());
		readingmin.getReadingType().setRef(MakeItMinThreshold(typeName));
		if (typeExplicit) {
			m_readingTypes.add(BuildReadingType(typeName));
		}
		m_readings.add(readingmax);
		m_readings.add(readingmin);
		return true;
	}

	public boolean AddEnergyExchange(javax.xml.datatype.XMLGregorianCalendar ts, double value, String cell_from, String cell_to) throws Exception {
		if (null == ts) ts = RabbitUtils.getXMLGregorianCalendarNow();
		else ts = (javax.xml.datatype.XMLGregorianCalendar)ts.clone();

		Reading reading_from = new Reading();
		reading_from.setTimeStamp(ts);
		reading_from.setValue(""+(-value));
		String typeName_from = CimCodes.rtFromMeasureTypeId("E-CONS").ToCIMString();
		reading_from.setReadingType(new Reading.ReadingType());
		reading_from.getReadingType().setRef(typeName_from);
		Meter meter_from = new Meter();
		meter_from.setMRID("CELL_"+cell_from);
		MeterReading meter_reading_from = new MeterReading();
		meter_reading_from.getReadings().add(reading_from);
		meter_reading_from.setMeter(meter_from);
		m_meter_readings.add(meter_reading_from);

		Reading reading_to = new Reading();
		reading_to.setTimeStamp(ts);
		reading_to.setValue(""+value);
		String typeName_to = CimCodes.rtFromMeasureTypeId("E-CONS").ToCIMString();
		reading_to.setReadingType(new Reading.ReadingType());
		reading_to.getReadingType().setRef(typeName_to);
		Meter meter_to = new Meter();
		meter_to.setMRID("CELL_"+cell_to);
		MeterReading meter_reading_to = new MeterReading();
		meter_reading_to.getReadings().add(reading_to);
		meter_reading_to.setMeter(meter_to);
		m_meter_readings.add(meter_reading_to);

		return true;
	}


/**
	If timestamp is not specified, it defaults to "now".
	the measure type is in the GNERA format (see CimCodes.java for the list of the codes)
	when having an explicit type, insert the ReadingType into the list of ReadingTypes. Usually it's done for configuration only
*/
	public Reading AddReadings(javax.xml.datatype.XMLGregorianCalendar ts, double value, String measure_type_id, boolean typeExplicit) {
		if (null == ts) ts = RabbitUtils.getXMLGregorianCalendarNow();
		else ts = (javax.xml.datatype.XMLGregorianCalendar)ts.clone();

		Reading reading = new Reading();
		reading.setTimeStamp(ts);
		reading.setValue(""+value);
		String typeName = CimCodes.rtFromMeasureTypeId(measure_type_id).ToCIMString();
		reading.setReadingType(new Reading.ReadingType());
		reading.getReadingType().setRef(typeName);
		if (typeExplicit) {
			m_readingTypes.add(BuildReadingType(typeName));
		}
		m_readings.add(reading);
		return reading;
	}


/**
for the NILM. gneraEventType must be "APPLIANCEON" or "APPLIANCEOFF"
*/
public EndDeviceEvent AddApplianceState(
		javax.xml.datatype.XMLGregorianCalendar ts, // la appliance e' la device_id
		String gneraEventType) {
	if (null == ts) ts = RabbitUtils.getXMLGregorianCalendarNow();
	if (!"APPLIANCEON".equals(gneraEventType) && !"APPLIANCEOFF".equals(gneraEventType)) return null;

	EndDeviceEvent event = new EndDeviceEvent();
	event.setCreatedDateTime(ts);
	String iecEndDeviceEventType = CimCodes.eventTypeFromGneraType(gneraEventType).ToCIMString();
	event.setEndDeviceEventType(new EndDeviceEvent.EndDeviceEventType());
	event.getEndDeviceEventType().setRef(iecEndDeviceEventType);
// do u want explicit event type?		if (typeExplicit) m_readingTypes.add(BuildReadingType(endDeviceEventType));
	m_endDeviceEvents.add(event);
	return event;
}



/**
for the moment, gneraEventType must be "ALARM"
*/
	public EndDeviceEvent AddAlarm(javax.xml.datatype.XMLGregorianCalendar ts, String issuer, String severity, String description, String gneraEventType) {
		if (null == ts) ts = RabbitUtils.getXMLGregorianCalendarNow();

		EndDeviceEvent event = new EndDeviceEvent();

		event.setCreatedDateTime(ts);
		event.setIssuerID(issuer);
		event.setSeverity(severity);
		if (null != description) {
			EndDeviceEventDetail detail = new EndDeviceEventDetail();
			detail.setName("Event Description");
			detail.setValue(description);
			event.getEndDeviceEventDetails().add(detail);
		}
		String iecEndDeviceEventType = CimCodes.eventTypeFromGneraType(gneraEventType).ToCIMString();
		event.setEndDeviceEventType(new EndDeviceEvent.EndDeviceEventType());
		event.getEndDeviceEventType().setRef(iecEndDeviceEventType);
// do u want explicit event type?		if (typeExplicit) m_readingTypes.add(BuildReadingType(endDeviceEventType));
		m_endDeviceEvents.add(event);
		return event;
	}

public String ToSqlMeasure() {return ToSqlMeasure(null);}
public String ToSqlMeasure(String device_id) {
/*
in a meter reading,
Meter.Names.name -> device_id
Readings.timestamp -> h_measure_time
Readings.value -> h_measure_value
CONVERT Readings.ReadingType to unit_type_id:
USE ReadingType.multiplier?
USE ReadingType.unit?
USE Power of 10 mult/Unit of Measure?
*/

	StringBuffer sqlCommands = new StringBuffer();

	if (null == device_id || "".equals(device_id)) device_id = m_meter.getMRID();
	for (java.util.Iterator<Reading> e = m_readings.iterator(); e.hasNext();) {
		Reading reading = e.next();
		String h_measure_time = CimCodes.DateCIMtoGnera(reading.getTimeStamp().toString());

		String cimReadingType = reading.getReadingType().getRef();
		CimCodes.ReadingType rt = CimCodes.rtFromCimName(cimReadingType);
		String /*gnera*/measure_type_id = rt.measure_type_id;
		String /*gnera*/unit_type_id = rt.unit_id;
		String value = reading.getValue();

//		if ("E-USED".equals(measure_type_id) || "E-PROD".equals(measure_type_id)) measure_type_id = "E-ELEC";

		sqlCommands.append("INSERT INTO MIDDLEWARE.h_measure (h_measure_time, device_id, unit_type_id, measure_type_id, h_measure_value) VALUES ('");
		sqlCommands.append(h_measure_time);
		sqlCommands.append("', '");
		sqlCommands.append(device_id);
		sqlCommands.append("', '");
		sqlCommands.append(unit_type_id);
		sqlCommands.append("', '");
		sqlCommands.append(measure_type_id);
		sqlCommands.append("', ");
		sqlCommands.append(value.replace(",", "."));
		sqlCommands.append(");\n");
	}

	return sqlCommands.toString();
}



public String ToSqlOccupancy(String room_id) throws Exception {
/*
INSERT INTO h_occupancy_calculated (h_occup_calc_time, room_id, h_occup_calc_value) VALUES (201201010300, 'UPCTC0101', 0);

h_occupancy_parametrized is weird. I will not produce it:
INSERT INTO h_occupancy_parametrized (room_id, day_type_id, week_type_id, hour_occupancy_id, h_occupancy_param_value) VALUES ('UPCTC0201', 'UPCIS', 'M', 'H6', 0.00);
*/
	StringBuffer sqlCommands = new StringBuffer();
	if (null == room_id) {
		if (null == m_meter) return null;
		String device_id = m_meter.getMRID();
		if (!device_id.startsWith("ROOM_")) throw new Exception("invalid room_id");
		room_id = device_id.substring(5);
	}
	for (java.util.Iterator<Reading> e = m_readings.iterator(); e.hasNext();) {
		Reading reading = e.next();
		String h_occup_calc_time = CimCodes.DateCIMtoGnera(reading.getTimeStamp().toString());

		String cimReadingType = reading.getReadingType().getRef();
		CimCodes.ReadingType rt = CimCodes.rtFromCimName(cimReadingType);
		String /*gnera*/measure_type_id = rt.measure_type_id;
		if (!measure_type_id.equals("A-OCCUP")) throw new Exception("measure_type_id should be A-OCCUP");
//		String /*gnera*/unit_type_id = rt.unit_id;

		String h_occup_calc_value = reading.getValue();

		sqlCommands.append("INSERT INTO h_occupancy_calculated (h_occup_calc_time, room_id, h_occup_calc_value) VALUES ('");
		sqlCommands.append(h_occup_calc_time);
		sqlCommands.append("', '");
		sqlCommands.append(room_id);
		sqlCommands.append("', '");
		sqlCommands.append(h_occup_calc_value);
		sqlCommands.append("');\n");
	}

	return sqlCommands.toString();
}

public String ToSqlForecast(String cell_id) throws Exception {
/*
INSERT INTO h_forecasting (h_forecasting_time, cell_id, measure_type_id, supercategory_id, unit_type_id, h_forecasting_value) VALUES (201206301000, 'DKDEM03', 'E-ELEC', 'P', 'KWH', 0.708613395690918000);
*/
	StringBuffer sqlCommands = new StringBuffer();
	if (null == cell_id) {
		if (null == m_meter) return null;
		String device_id = m_meter.getMRID();
		// make a query to get the cell_id from the device_id. For the moment, we suppose that device_id = CELL_xxx with xxx the cell_id
		if (!device_id.startsWith("CELL_")) throw new Exception("invalid cell_id");
		cell_id = device_id.substring(5);
	}

	for (java.util.Iterator<Reading> e = m_readings.iterator(); e.hasNext();) {
		Reading reading = e.next();
		String h_forecasting_time = CimCodes.DateCIMtoGnera(reading.getTimeStamp().toString());
		String generation_date = CimCodes.DateCIMtoGnera(reading.getReportedDateTime().toString());

		String cimReadingType = reading.getReadingType().getRef();
		CimCodes.ReadingType rt = CimCodes.rtFromCimName(cimReadingType);
		String /*gnera*/measure_type_id = rt.measure_type_id;
		String supercategory_id = rt.supercategory_id;
		String /*gnera*/unit_type_id = rt.unit_id;
		String h_forecasting_value = reading.getValue();

		if ("A".equals(supercategory_id)) {
			sqlCommands.append("INSERT INTO MIDDLEWARE.h_forecasting_weather (h_forecasting_time, cell_id, measure_type_id, unit_type_id, h_forecasting_value, generation_date) VALUES ('");
		} else {
			sqlCommands.append("INSERT INTO MIDDLEWARE.h_forecasting (h_forecasting_time, cell_id, measure_type_id, supercategory_id, unit_type_id, h_forecasting_value, generation_date) VALUES ('");
		}

//		if ("E-USED".equals(measure_type_id) || "E-PROD".equals(measure_type_id)) measure_type_id = "E-ELEC";
		sqlCommands.append(h_forecasting_time);
		sqlCommands.append("', '");
		sqlCommands.append(cell_id);
		sqlCommands.append("', '");
		sqlCommands.append(measure_type_id);
		if (!"A".equals(supercategory_id)) {
			sqlCommands.append("', '");
			sqlCommands.append(supercategory_id);
		}
		sqlCommands.append("', '");
		sqlCommands.append(unit_type_id);
		sqlCommands.append("', ");
		sqlCommands.append(h_forecasting_value);
		sqlCommands.append(", '");
		sqlCommands.append(generation_date);
		sqlCommands.append("');\n");
	}

	return sqlCommands.toString();
}

public String ToSql(String where) throws Exception {
	if ("WEATHER".equals(m_semantics)) return ToSqlWeather(where);
	if ("FORECAST".equals(m_semantics)) return ToSqlForecast(where);
	if ("COMFORT".equals(m_semantics)) return ToSqlComfort(where);
	if ("MEASURE".equals(m_semantics)) return ToSqlMeasure(where);
	if ("OCCUPANCY".equals(m_semantics)) return ToSqlOccupancy(where);
	if ("LOCALPRICE".equals(m_semantics)) return ToSqlPriceLocalMarkets(where);
	if ("ALARM".equals(m_semantics)) return ToSqlAlarm(where);
	if ("NILM".equals(m_semantics)) return ToSqlApplianceState(where);
	if ("ENXCHANGE".equals(m_semantics)) return ToSqlEnXchange(where);

	throw new Exception("specify the semantics!");
}

public String ToSqlEnXchange(String dummy) throws Exception {
	StringBuffer sqlCommands = new StringBuffer();

	if (null != dummy) throw new Exception("don't specify the cell_id in Energy Exchanges!");

	boolean giver = true;
	String timestamp = null;
	String value = null;
	String cell_from = null;
	String cell_to = null;

	for (java.util.Iterator<MeterReading> e = m_meter_readings.iterator(); e.hasNext();) {
		MeterReading mr = e.next();
		Reading reading = mr.getReadings().get(0);
		Meter meter = mr.getMeter();
		if (!meter.getMRID().startsWith("CELL_")) throw new Exception("invalid cell_id");
		String meter_name = meter.getMRID().substring(5);

		if (giver) {
			cell_from = meter_name;
			timestamp = CimCodes.DateCIMtoGnera(reading.getTimeStamp().toString());
			value = reading.getValue();
			giver = false;
		} else {
			cell_to = meter_name;
			if (!timestamp.equals(CimCodes.DateCIMtoGnera(reading.getTimeStamp().toString()))) throw new Exception("ambiguous timestamp in energy exchange");
			String positive_value = reading.getValue();
			if (!positive_value.equals(value.substring(1))) throw new Exception("bad value of exchanged energy");
			giver = true;


			sqlCommands.append("INSERT INTO MIDDLEWARE.energy_exchanged (cell_id_to, cell_id_from, unit_type_id, energy_exchanged_value, energy_exchanged_time) VALUES ('");
			sqlCommands.append(cell_to);
			sqlCommands.append("', '");
			sqlCommands.append(cell_from);
			sqlCommands.append("', '");
			sqlCommands.append("KWH");
			sqlCommands.append("', ");
			sqlCommands.append(positive_value);
			sqlCommands.append(", '");
			sqlCommands.append(timestamp);
			sqlCommands.append("');\n");
		}
	}

	return sqlCommands.toString();
}

public String ToSqlAlarm(String device_id) throws Exception {
	StringBuffer sqlCommands = new StringBuffer();
	String issuer = null;

	if (null == device_id || "".equals(device_id)) device_id = m_meter.getMRID();
	for (java.util.Iterator<EndDeviceEvent> e = m_endDeviceEvents.iterator(); e.hasNext();) {
		EndDeviceEvent alarm = e.next();
		String timestamp = CimCodes.DateCIMtoGnera(alarm.getCreatedDateTime().toString());
		if (null == issuer) {issuer = alarm.getIssuerID();}
		String Severity = alarm.getSeverity();
		for (java.util.Iterator<EndDeviceEventDetail> e2 = alarm.getEndDeviceEventDetails().iterator(); e2.hasNext();) {
			EndDeviceEventDetail ed = e2.next();
//			String Description = ed.getValue(); TODO this one will be used for the CEP's details
		}
		String cimEventType = alarm.getEndDeviceEventType().getRef();
		CimCodes.EventType rt = CimCodes.eventTypeFromCIM(cimEventType);
//		System.out.println("event type "+cimEventType);
//		System.out.println("event type "+rt.GneraCode);
		String /*gnera*/gneraType = rt.GneraCode;
		if (!"ALARM".equals(gneraType)) {throw new Exception("Invalid Alarm EventType");}

		sqlCommands.append("INSERT INTO MIDDLEWARE.cep (alarm_id, cep_time, device_id) VALUES ('");

		sqlCommands.append(Severity);
		sqlCommands.append("', '");
		sqlCommands.append(timestamp);
		sqlCommands.append("', '");
		sqlCommands.append(device_id);
		sqlCommands.append("');\n");
	}

	return sqlCommands.toString();
}

public String ToSqlApplianceState(String device_id) throws Exception {
	StringBuffer sqlCommands = new StringBuffer();
	String issuer = null;

	if (null == device_id || "".equals(device_id)) device_id = m_meter.getMRID();

	for (java.util.Iterator<EndDeviceEvent> e = m_endDeviceEvents.iterator(); e.hasNext();) {
		EndDeviceEvent appliancestate = e.next();
		String timestamp = CimCodes.DateCIMtoGnera(appliancestate.getCreatedDateTime().toString());
		if (null == issuer) {issuer = appliancestate.getIssuerID();}
		String cimEventType = appliancestate.getEndDeviceEventType().getRef();
		CimCodes.EventType rt = CimCodes.eventTypeFromCIM(cimEventType);
		String /*gnera*/gneraType = rt.GneraCode;

//		sqlCommands.append("INSERT INTO MIDDLEWARE.commanduser (comanduser_value, user_id, device_command_time, device_id, command_id, duration_time_minutes) VALUES (");
		sqlCommands.append("INSERT INTO MIDDLEWARE.commanduser (device_command_time, device_id, command_id) VALUES ('");
		sqlCommands.append(timestamp);
		sqlCommands.append("', '");
		sqlCommands.append(device_id);
		sqlCommands.append("', '");
		sqlCommands.append(gneraType);
		sqlCommands.append("');\n");
	}

	return sqlCommands.toString();
}

public String ToSqlComfort(String room_id) throws Exception {
/*
INSERT INTO comfort (comfort_datefrom, comfort_dateto, comfort_max, comfort_min, measure_type_id, room_id, unit_type_id) VALUES (...);
*/
	StringBuffer sqlCommands = new StringBuffer();
	if (null == room_id) {
		if (null == m_meter) return null;
		String device_id = m_meter.getMRID();

		if (!device_id.startsWith("ROOM_")) throw new Exception("invalid cell_id");
		room_id = device_id.substring(5);
	}

	String valmax=null, valmin=null, datefrom=null, dateto=null, measure_type_id=null, unit_type_id=null;

	for (java.util.Iterator<Reading> e = m_readings.iterator(); e.hasNext();) {
		Reading reading = e.next();
		DateTimeInterval dti = reading.getTimePeriod();
		datefrom = CimCodes.DateCIMtoGnera(dti.getStart().toString());
		dateto = CimCodes.DateCIMtoGnera(dti.getEnd().toString());
		String cimReadingType = reading.getReadingType().getRef();
		CimCodes.ReadingType rt = CimCodes.rtFromCimName(cimReadingType);
		/*gnera*/measure_type_id = rt.measure_type_id;
		/*gnera*/unit_type_id = rt.unit_id;
		if (IsItThreshold(cimReadingType) == 5) valmax = reading.getValue();
		else valmin = reading.getValue();
	}
		sqlCommands.append(
	"INSERT INTO comfort (comfort_datefrom, comfort_dateto, comfort_max, comfort_min, measure_type_id, room_id, unit_type_id) VALUES ('");
		sqlCommands.append(datefrom);
		sqlCommands.append("', '");
		sqlCommands.append(dateto);
		sqlCommands.append("', '");
		sqlCommands.append(valmax);
		sqlCommands.append("', '");
		sqlCommands.append(valmin);
		sqlCommands.append("', '");
		sqlCommands.append(measure_type_id);
		sqlCommands.append("', '");
		sqlCommands.append(room_id);
		sqlCommands.append("', '");
		sqlCommands.append(unit_type_id);
		sqlCommands.append("');\n");

	return sqlCommands.toString();
}


public String ToSqlWeather(String macrocell_id) throws Exception {
/*
INSERT INTO h_weather (h_weather_time, measure_type_id, h_weather_value, unit_type_id, macrocell_id) VALUES (201205050000, 'W-RAIN', 0.000, 'MM', 'UPC');
*/
	StringBuffer sqlCommands = new StringBuffer();
	if (null == macrocell_id) {
		if (null == m_meter) return null;
		String device_id = m_meter.getMRID();

		if (!device_id.startsWith("MACRO_")) throw new Exception("invalid macrocell_id");
		macrocell_id = device_id.substring(6);
	}

	for (java.util.Iterator<Reading> e = m_readings.iterator(); e.hasNext();) {
		Reading reading = e.next();
		String h_weather_time = CimCodes.DateCIMtoGnera(reading.getTimeStamp().toString());

		String cimReadingType = reading.getReadingType().getRef();
		CimCodes.ReadingType rt = CimCodes.rtFromCimName(cimReadingType);
		String /*gnera*/measure_type_id = rt.measure_type_id;
//		String supercategory_id = rt.supercategory_id;
		String /*gnera*/unit_type_id = rt.unit_id;
		String h_weather_value = reading.getValue();

		sqlCommands.append("INSERT INTO MIDDLEWARE.h_weather (h_weather_time, measure_type_id, h_weather_value, unit_type_id, macrocell_id) VALUES ('");

		sqlCommands.append(h_weather_time);
		sqlCommands.append("', '");
		sqlCommands.append(measure_type_id);
		sqlCommands.append("', ");
		sqlCommands.append(h_weather_value);
		sqlCommands.append(", '");
		sqlCommands.append(unit_type_id);
		sqlCommands.append("', '");
		sqlCommands.append(macrocell_id);
		sqlCommands.append("');\n");
	}

	return sqlCommands.toString();
}

public String ToSqlPriceLocalMarkets(String macrocell_id) throws Exception {
/*
INSERT INTO price_local_markets (price_local_time, macrocell_id, unit_type_id, price_local_value) VALUES (201205050000, 'CISTER', 'EKWH', 0.000);
*/
	StringBuffer sqlCommands = new StringBuffer();
	if (null == macrocell_id) {
		if (null == m_meter) return null;
		String device_id = m_meter.getMRID();

		if (!device_id.startsWith("MACRO_")) throw new Exception("invalid macrocell_id");
		macrocell_id = device_id.substring(6);
	}

	for (java.util.Iterator<Reading> e = m_readings.iterator(); e.hasNext();) {
		Reading reading = e.next();
		String price_local_time = CimCodes.DateCIMtoGnera(reading.getTimeStamp().toString());

		String cimReadingType = reading.getReadingType().getRef();
		CimCodes.ReadingType rt = CimCodes.rtFromCimName(cimReadingType);
//		String /*gnera*/measure_type_id = rt.measure_type_id;
//		String supercategory_id = rt.supercategory_id;
		String /*gnera*/unit_type_id = rt.unit_id;
		String price_local_value = reading.getValue();

		sqlCommands.append("INSERT INTO price_local_markets (price_local_time, macrocell_id, unit_type_id, price_local_value) VALUES (");

		sqlCommands.append(price_local_time);
		sqlCommands.append(", '");
		sqlCommands.append(macrocell_id);
		sqlCommands.append("', '");
		sqlCommands.append(unit_type_id);
		sqlCommands.append("', '");
		sqlCommands.append(price_local_value);
		sqlCommands.append("');\n");
	}

	return sqlCommands.toString();
}

private ReadingQuality BuildQuality(String typeName) throws Exception {
	if (!"FORECAST".equals(typeName)) throw new Exception("I only support the 'FORECAST' quality");
	ReadingQuality rq = new ReadingQuality();
	ReadingQuality.ReadingQualityType rqt = new ReadingQuality.ReadingQualityType();
	rqt.setRef("2.12.0"); // dataCollectionNetwork.Forecast.GenericProjection
	rq.setReadingQualityType(rqt);
	return rq;
}

/** fills up all the fields of a ReadingType's XML from its CIM name */
private ReadingType BuildReadingType(String readingName) {
	String[] p = readingName.split("\\.");
	ReadingType aReadingType = new ReadingType();

	Name name = new Name();
	name.setName(readingName);
	aReadingType.getNames().add(name);

	aReadingType.setMacroPeriod(p[0]);
	aReadingType.setAggregate(p[1]);
	aReadingType.setMeasuringPeriod(p[2]);
	aReadingType.setAccumulation(p[3]);
	aReadingType.setFlowDirection(p[4]);
	aReadingType.setCommodity(p[5]);
	aReadingType.setMeasurementKind(p[6]);
	ReadingInterharmonic seca = new ReadingInterharmonic();
	seca.setNumerator(new java.math.BigInteger(p[7]));
	seca.setDenominator(new java.math.BigInteger(p[8]));
	aReadingType.setInterharmonic(seca);
	RationalNumber seca2 = new RationalNumber();
	seca2.setNumerator(new java.math.BigInteger(p[9]));
	seca2.setDenominator(new java.math.BigInteger(p[10]));
	aReadingType.setArgument(seca2);
	aReadingType.setTou(new java.math.BigInteger(p[11]));
	aReadingType.setCpp(new java.math.BigInteger(p[12]));
	aReadingType.setConsumptionTier(new java.math.BigInteger(p[13]));
	aReadingType.setPhases(p[14]);
	aReadingType.setMultiplier(p[15]);
	aReadingType.setUnit(p[16]);
	aReadingType.setCurrency(p[17]);
	return aReadingType;
}

/** builds up a CIM ReadingType name from its XML */
private String BuildReadingTypeName(ReadingType aReadingType) {
	String[] p = new String[18];
	p[0] = aReadingType.getMacroPeriod();
	p[1] = aReadingType.getAggregate();
	p[2] = aReadingType.getMeasuringPeriod();
	p[3] = aReadingType.getAccumulation();
	p[4] = aReadingType.getFlowDirection();
	p[5] = aReadingType.getCommodity();
	p[6] = aReadingType.getMeasurementKind();
	if (null == aReadingType.getInterharmonic()) {
		p[7] = "0";
		p[8] = "0";
	} else {
		p[7] = aReadingType.getInterharmonic().getNumerator().toString();
		p[8] = aReadingType.getInterharmonic().getDenominator().toString();
	}
	if (null == aReadingType.getArgument()) {
		p[9] = "0";
		p[10] = "0";
	} else {
		p[9] = aReadingType.getArgument().getNumerator().toString();
		p[10] = aReadingType.getArgument().getDenominator().toString();
	}
	p[11] = aReadingType.getTou().toString();
	p[12] = aReadingType.getCpp().toString();
	p[13] = aReadingType.getConsumptionTier().toString();
	p[14] = aReadingType.getPhases();
	p[15] = aReadingType.getMultiplier();
	p[16] = aReadingType.getUnit();
	p[17] = aReadingType.getCurrency();

	StringBuffer readingName = new StringBuffer();
	for (int i=0;i<17;i++) readingName.append(p[i]+".");
	readingName.append(p[18]);

	Name name = new Name();
	name.setName(readingName.toString());
	aReadingType.getNames().add(name);

	return readingName.toString();
}

	public String Marshal() {
		EncMeterReadings emr = EncMeterReadings.CreateEncMeterReadings();
		MeterReadings mr = new MeterReadings();
/*
 * safe version:
		if (m_meter_readings.size()>0 && 0 == m_meter_readings.size()%2) {
			for (java.util.Iterator<MeterReading> e = m_meter_readings.iterator(); e.hasNext();) {
				MeterReading ec = e.next();
				mr.getMeterReading().add(ec);
			}
			return emr.Marshal(mr);
		}

 */
		for (java.util.Iterator<MeterReading> e = m_meter_readings.iterator(); e.hasNext();) {
			MeterReading ec = e.next();
			mr.getMeterReading().add(ec);
		}


		for (java.util.Iterator<ReadingType> e = m_readingTypes.iterator(); e.hasNext();) {
			ReadingType ec = e.next();
			mr.getReadingType().add(ec);
		}
		boolean mr1_is_empty = true;
		MeterReading mr1 = new MeterReading();
		mr1.setMeter(m_meter);
		for (java.util.Iterator<Reading> e = m_readings.iterator(); e.hasNext();) {
			Reading ec = e.next();
			mr1.getReadings().add(ec);
			mr1_is_empty = false;
		}
		for (java.util.Iterator<EndDeviceEvent> e = m_endDeviceEvents.iterator(); e.hasNext();) {
			EndDeviceEvent ec = e.next();
			mr1.getEndDeviceEvents().add(ec);
			mr1_is_empty = false;
		}
		if (mr1_is_empty == false)
			mr.getMeterReading().add(mr1);
		return emr.Marshal(mr);
	}

	public String InferSemantics() throws Exception {
		String gnera_type_id = null;
		String gnera_event_id = null;
		boolean thresholds = false;
		boolean forecast = false;
		if (0 == m_meter_readings.size()%2 && 0 < m_meter_readings.size()) {
			m_semantics = "ENXCHANGE";
			return m_semantics;
		}
		// if there is no Reading, gnera_reading_type defaults to null
		for (java.util.Iterator<Reading> e = m_readings.iterator(); e.hasNext();) {
			Reading reading = e.next();
			for (java.util.Iterator<ReadingQuality> q = reading.getReadingQualities().iterator(); q.hasNext();) {
				ReadingQuality rq = q.next();
				if (rq.getReadingQualityType().getRef().equals("2.12.0")) forecast = true;
				else throw new Exception("I only support the 'FORECAST' quality");
			}
			String cimReadingType = reading.getReadingType().getRef();
			if (5 == IsItThreshold(cimReadingType)) thresholds = true;
			CimCodes.ReadingType rt = CimCodes.rtFromCimName(cimReadingType);
			gnera_type_id = rt.measure_type_id;
		}

		// if there is no Event, gnera_event_type defaults to null
		for (java.util.Iterator<EndDeviceEvent> e = m_endDeviceEvents.iterator(); e.hasNext();) {
			EndDeviceEvent event = e.next();
			String cimEventType = event.getEndDeviceEventType().getRef();
			CimCodes.EventType rt = CimCodes.eventTypeFromCIM(cimEventType);
			gnera_event_id = rt.GneraCode;
		}

		String semantics = null;
		// check if there are events. In that case, this is an alarm
		if(0 < m_endDeviceEvents.size()) {
			if (gnera_event_id.equals("APPLIANCEON") || gnera_event_id.equals("APPLIANCEOFF"))
				semantics = "NILM";
			else
				semantics = "ALARM";
		} else if (forecast) {
			semantics = "FORECAST";
		} else if (thresholds) {
			semantics = "COMFORT";
		} else if (m_meter.getMRID().startsWith("MACRO_") && 
			("W-T".equals(gnera_type_id)||
			"W-SRAD".equals(gnera_type_id)||
			"W-RAIN".equals(gnera_type_id)||
			"W-WS".equals(gnera_type_id)||
			"W-H".equals(gnera_type_id)||
			"W-TMEAN".equals(gnera_type_id)||
			"W-DD".equals(gnera_type_id)||
			"W_LUX".equals(gnera_type_id)||
			"W_LIGHT_I".equals(gnera_type_id)||
			"W_LIGHT_V".equals(gnera_type_id)||
			"UV".equals(gnera_type_id)||
			"W_WD".equals(gnera_type_id)||
			"W_P".equals(gnera_type_id)||
			"W_V".equals(gnera_type_id)
			))
		{
			semantics = "WEATHER";
		} else {
			semantics = "MEASURE";
		}
/*
	// check if there is at least one FORECAST quality, and in that case this is a forecast
	for (java.util.Iterator<EndDeviceEvent> e = m_endDeviceEvents.iterator(); e.hasNext();) {

	ReadingQuality rq = BuildQuality("FORECAST");
		reading.getReadingQualities().add(rq);
		
		for (java.util.Iterator<EndDeviceControl> e = m_controls.iterator(); e.hasNext();) {
			EndDeviceControl ec = e.next();
			String cimEventType = ec.getEndDeviceControlType().getRef();
			CimCodes.EventType rt = CimCodes.eventTypeFromCIM(cimEventType);
			m_semantics = rt.GneraCode;
			return rt.GneraCode;
		}
*/                
		m_semantics = semantics;
		return semantics;
	}

public static String ManyReadings() throws Exception {
	MeterReadingsManager mr = new MeterReadingsManager("MEASURE");
	mr.SetMeter("DKDJ010101", "192.168.1.1", "CISTER Research Unit");
	mr.AddReadings(null, 12, "W-T", true);
	Thread.sleep(1000);mr.AddReadings(null, 20, "W-SRAD", false);
	Thread.sleep(1000);mr.AddReadings(null, 20, "W-RAIN", false);
	Thread.sleep(1000);mr.AddReadings(null, 13, "W-WS", false);
	Thread.sleep(1000);mr.AddReadings(null, 12, "W-H", true);
	Thread.sleep(1000);mr.AddReadings(null, 20, "E-USED", false);
	Thread.sleep(1000);mr.AddReadings(null, 10, "W-TMEAN", true);
	Thread.sleep(1000);mr.AddReadings(null, 20, "W-DD", false);
//	Thread.sleep(1000);mr.AddReadings(null, 13, "A-OCCUP", false);
//	Thread.sleep(1000);mr.AddReadings(null, 10, "E-PRICEEU", true);
//	Thread.sleep(1000);mr.AddReadings(null, 20, "E-PRICEDK", false);
	Thread.sleep(1000);mr.AddReadings(null, 13, "E-PROD", false);

	return mr.Marshal();
}

	public static void main(String[] args) throws Exception {
/*		MeterReadingsManager mr = new MeterReadingsManager("WEATHER");
		mr.SetMeter("CISTERlab", "192.168.1.1", "CISTER Research Unit");
		mr.AddReadings(null, 10, "W-TMEAN", true);
		mr.AddReadings(null, 12, "W-T", true);
		mr.AddReadings(null, 20, "W-RAIN", false);
		mr.AddReadings(null, 13, "W-T", false);
		System.out.println(mr.Marshal());
		System.out.println(mr.ToSqlMeasure());
		System.out.println(mr.ToSql("MACRO_porto"));
		
		mr.ResetMeterReadings();
		mr.SetMeter("ROOM_CISTERoffice", null, null);
		mr.AddReadings(null, 10, "A-OCCUP", true);
		System.out.println(mr.ToSqlOccupancy("stanza2"));
		System.out.println(mr.ToSqlOccupancy(null));

		mr.ResetMeterReadings();
		mr.SetMeter("CELL_CISTERcenter", null, null);
		mr.AddReadings(null, 10, "A-OCCUP", true);
		System.out.println(mr.ToSqlForecast(null));
*/

/*
			THIS ONE DOES NOT WORK ANYMORE
 		mr.ResetMeterReadings();
		mr.SetMeter("MACRO_Porto", null, null);
		mr.AddReadings(null, 10, "E-SELLEU", true);
		System.out.println(mr.ToSqlPriceLocalMarkets(null));
*/
/*		String manyreadings = ManyReadings();
		System.out.println(manyreadings);
		System.exit(0);*/
String cell_id = "DKD010101";
		MeterReadingsManager mr = new MeterReadingsManager("FORECAST");
		mr.SetMeter("CELL_"+cell_id, null, null);
		javax.xml.datatype.XMLGregorianCalendar generation_date = RabbitUtils.getXMLGregorianCalendarNow();
		generation_date.setDay(1);
		generation_date.setMonth(10);
		generation_date.setYear(2013);
		generation_date.setTime(12, 0, 0);

		javax.xml.datatype.XMLGregorianCalendar forecasting_time = RabbitUtils.getXMLGregorianCalendarNow();
		forecasting_time.setDay(15);
		forecasting_time.setMonth(10);
		forecasting_time.setYear(2013);

		forecasting_time.setTime(5, 15, 0);
		double h_forecasting_value = 22;
//		mr.AddReadingsForecast(forecasting_time, h_forecasting_value, "E-USED", generation_date, false);

		forecasting_time.setTime(5, 30, 0);
		h_forecasting_value = 25;
		mr.AddReadingsForecast(forecasting_time, h_forecasting_value, "E-PROD", generation_date, false);

		forecasting_time.setTime(5, 45, 0);
		h_forecasting_value = 21;
		mr.AddReadingsForecast(forecasting_time, h_forecasting_value, "W-T", generation_date, false);

		String sql1 = mr.ToSql(null);
		System.out.println(sql1);
		String xmldata = mr.Marshal();
		System.out.println("XML data:\n"+xmldata);
		System.out.println("XML semantics: '"+CIMWrapper.InferSemantics(xmldata)+"'");
		mr.ResetMeterReadings();
		String sqltrash = mr.ToSql(null);
		System.out.println("new SQL: "+sqltrash);
		String xmldatatrash = mr.Marshal();
		System.out.println("XML data:\n"+xmldatatrash);
		System.out.println("XML semantics: '"+CIMWrapper.InferSemantics(xmldatatrash)+"'");

		MeterReadingsManager mr2 = MeterReadingsManager.Unmarshal(xmldata);
		mr2.InferSemantics();

		String sql2 = mr2.ToSql(null);
		System.out.println("last SQL: "+sql2);
		if (!sql2.equals(sql1)) throw new Exception("inconsistencies in SQL!");

		mr2.AddThresholds(null, null, 20, 10, "W-T", false);
		
	}
}

