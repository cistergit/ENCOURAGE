/**
CIMWrapper.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager;

// utility_contract_price is a CIM.Tariff
// h_advice_to_user is a EndDeviceControl
// price_energy is a CIM.Tariff
// energy_local_provided is a mess. It should be a MeterReading, but it needs a pair (cell_from, cell_to)

/**
use cases:
H_weather, h_measure, H_forecasting
CEP, Cellsurplusneeds, Cellflexibility, , Price_energy, consumptionorders

Energy_local_provided, Price_local_markets, H_advice_to_user, Command_user
*/

import java.util.List;
import java.util.ArrayList;

public class CIMWrapper {

	private static boolean m_silent = false;
	private List<MeterReadingsManager> managers_mr;
	private List<EndDeviceControlsManager> managers_edc;
	private List<MeterConfigManager> managers_mc;
	private UsersManager manager_users;
	private PhysicalConfManager manager_phyconf;
	private MarketManager manager_market;

	public CIMWrapper() {
		Reset();
	}

	public void Reset() {
		managers_mr = new ArrayList<MeterReadingsManager>();
		managers_edc = new ArrayList<EndDeviceControlsManager>();
		managers_mc = new ArrayList<MeterConfigManager>();
		manager_users = new UsersManager();
		manager_phyconf = new PhysicalConfManager();
		manager_market = new MarketManager();
	}

	public boolean CreateComfort(
			String room_id,
			javax.xml.datatype.XMLGregorianCalendar starttime,
			javax.xml.datatype.XMLGregorianCalendar endtime,
			double max_value,
			double min_value,
			String measure_type_id) throws Exception {
		MeterReadingsManager mr = new MeterReadingsManager("COMFORT");
		mr.SetMeter("ROOM_"+room_id, null, null);
		mr.AddThresholds(starttime, endtime, max_value, min_value, measure_type_id, false);
		managers_mr.add(mr);
		return true;
	}

	public boolean CreateMarketParticipant(
			String cell_id,
			String local_market_desc,
			String local_market_id) throws Exception {
		manager_market.AddMarketParticipant("CELL_"+cell_id, local_market_desc, local_market_id);
		return true;
	}

	public boolean CreateAppliance(
			String appliance_activacion_date,
			String appliance_desc,
			String appliance_id,
			String appliance_investment,
			String appliance_leaving_date,
			String appliance_power,
			String load_type_id,
			String room_id,
			String subcategory_id
		) throws Exception {
		try {
			manager_phyconf.AddAppliance(appliance_activacion_date, appliance_desc, appliance_id, appliance_investment, appliance_leaving_date, appliance_power, load_type_id, room_id, subcategory_id);
		} catch (Exception e) {}
		return true;
	}

	public boolean CreateCell(
			String cell_building_shade_coefficient,
			String cell_desc,
			String cell_id,
			String cell_latitud,
			String cell_longitud,
			String cell_surface,
			String cell_volumen,
			String cell_wall_insulation_thickness,
			String macrocell_id
		) throws Exception {
		try {
			manager_phyconf.AddCell(cell_building_shade_coefficient, cell_desc, cell_id, cell_latitud, cell_longitud, cell_surface, cell_volumen, cell_wall_insulation_thickness, macrocell_id);
		} catch (Exception e) {}
		return true;
	}

	public boolean CreateGateway(
			String gateway_desc,
			String gateway_id,
			String gateway_macadr,
			String manufacturer_id,
			String middleware_plugin_id
			) throws Exception {
		try {
			manager_phyconf.AddGateway(gateway_desc, gateway_id, gateway_macadr, manufacturer_id, middleware_plugin_id);
		} catch (Exception e) {}
		return true;
	}

	public boolean CreateMacroCell(
			String macrocell_desc,
			String macrocell_id,
			String region
			) throws Exception {
		try {
			manager_phyconf.AddMacroCell(macrocell_desc, macrocell_id, region);
		} catch (Exception e) {}
		return true;
	}

	public boolean CreateManufacturer(
			String manufacturer_desc,
			String manufacturer_firmware,
			String manufacturer_id,
			String manufacturer_make,
			String manufacturer_model
			) throws Exception {
		try {
			manager_phyconf.AddManufacturer(manufacturer_desc, manufacturer_firmware, manufacturer_id, manufacturer_make, manufacturer_model);
		} catch (Exception e) {}
		return true;
	}

	public boolean CreateRoom(
			String cell_id,
			String room_desc,
			String room_id,
			String room_max_ocup,
			String room_surface,
			String room_volume
			) throws Exception {
		try {
			manager_phyconf.AddRoom(cell_id, room_desc, room_id, room_max_ocup, room_surface, room_volume);
		} catch (Exception e) {}
		return true;
	}

	public boolean CreateShadowDevice(
			String appliance_id,
			String device_desc,
			String device_id,
			String device_output,
			String manufacturer_id,
			String gateway_id,
			String middleware_plugin_id
			) throws Exception {
		try {
			manager_phyconf.AddShadowDevice(appliance_id, device_desc, device_id, device_output, manufacturer_id, gateway_id, middleware_plugin_id);
		} catch (Exception e) {}
		return true;
	}

	public boolean CreateUser(String user_id, String password, String access_id) {
		try {
			manager_users.AddAccount(user_id, password, access_id);
		} catch (Exception e) {}
		return true;
	}

	public boolean CreatePrivilege(String privileges_id, String desc) {
		try {
			manager_users.AddIdentity(privileges_id, desc);
		} catch (Exception e) {}
		return true;
	}

	public boolean CreateAccess(String access_id, String privileges_id) {
		try {
			manager_users.AddAssignedIdentity(access_id, privileges_id);
		} catch (Exception e) {}
		return true;
	}

	public boolean CreateEnergyExchange(javax.xml.datatype.XMLGregorianCalendar ts, double value, String cell_from, String cell_to) throws Exception {
		MeterReadingsManager mr = new MeterReadingsManager("ENXCHANGE");
		mr.AddEnergyExchange(ts, value, cell_from, cell_to);
		managers_mr.add(mr);
		return true;
	}

/**
Should h_measure_time be null, it saves the current time. Corresponds to:
	h_occupancy_calculated is now a h_measure, with measure_type_id == A-OCCUP
	INSERT INTO h_measure if measure_type_id == E_ELEC
*/
	public boolean CreateMeasure(String device_id, String measure_type_id,
		double h_measure_value) throws Exception {
		return CreateMeasure(device_id, measure_type_id, null, h_measure_value, null);
	}
	public boolean CreateMeasure(String device_id, String measure_type_id, String unit_type_id,
		double h_measure_value, javax.xml.datatype.XMLGregorianCalendar h_measure_time) throws Exception {

		MeterReadingsManager mr = new MeterReadingsManager("MEASURE");
		mr.SetMeter(device_id, null, null);
		String cimReadingType = mr.AddReadings(h_measure_time, h_measure_value, measure_type_id, false).getReadingType().getRef();
		if (null != unit_type_id && !"".equals(unit_type_id)) {
			CimCodes.ReadingType rt = CimCodes.rtFromCimName(cimReadingType);
			String inferred_unit_type_id = rt.unit_id;
			if (!inferred_unit_type_id.equals(unit_type_id))
				throw new Exception("inconsistent unit_type_id: "+inferred_unit_type_id+" is not "+unit_type_id);
		}
		managers_mr.add(mr);
		return true;
	}

/**
management of the table:
	INSERT INTO h_weather if measure_type_id == W-RAIN or W-TMEAN or W-H or W-DD
*/
	public boolean CreateWeather(String macrocell_id, String measure_type_id,
		double h_weather_value) throws Exception {
		return CreateWeather(macrocell_id, measure_type_id, null, h_weather_value, null);
	}
	public boolean CreateWeather(String macrocell_id, String measure_type_id, String unit_type_id,
		double h_weather_value, javax.xml.datatype.XMLGregorianCalendar h_measure_time) throws Exception {
		MeterReadingsManager mr = new MeterReadingsManager("WEATHER");
		mr.SetMeter("MACRO_"+macrocell_id, null, null);
		String cimReadingType = mr.AddReadings(h_measure_time, h_weather_value, measure_type_id, false).getReadingType().getRef();
		if (null != unit_type_id && !"".equals(unit_type_id)) {
			CimCodes.ReadingType rt = CimCodes.rtFromCimName(cimReadingType);
			String inferred_unit_type_id = rt.unit_id;
			if (!inferred_unit_type_id.equals(unit_type_id))
				throw new Exception("inconsistent unit_type_id: "+inferred_unit_type_id+" is not "+unit_type_id);
		}
		managers_mr.add(mr);
		return true;
	}

/**
management of the table:
	Price_local_markets
BAD, it should be a EndDeviceCommand...
*/
	public boolean CreatePriceLocalMarketsEvent(String macrocell_id, String unit_type_id,
		double local_price_value, javax.xml.datatype.XMLGregorianCalendar h_measure_time) throws Exception {
		String measure_type_id = "";
		if ("EKWH".equals(unit_type_id)) measure_type_id = "E-PRICEEU";
		else if ("DKWH".equals(unit_type_id)) measure_type_id = "E-PRICEDK";
		else throw new Exception("bad price unit in a local market");

		MeterReadingsManager mr = new MeterReadingsManager("LOCALPRICE");
		mr.SetMeter("MACRO_"+macrocell_id, null, null);
		mr.AddReadings(h_measure_time, local_price_value, measure_type_id, false);
		managers_mr.add(mr);
		return true;
	}


/**
Should h_measure_time be null, it saves the current time. Corresponds to:
	INSERT INTO h_forecasting if measure_type_id == .............
*/
	public boolean CreateForecasting(
			// supercategory_id implicit
			String cell_id,
			javax.xml.datatype.XMLGregorianCalendar forecasting_time,
			double h_forecasting_value, 
			javax.xml.datatype.XMLGregorianCalendar generation_date,
			String measure_type_id
			// unit_type_id reportedDateTime
		) throws Exception {
		MeterReadingsManager mr = new MeterReadingsManager("FORECAST");
		mr.SetMeter("CELL_"+cell_id, null, null);
//		String cimReadingType = mr.AddReadingsForecast(forecasting_time, h_forecasting_value, measure_type_id, generation_date, false).getReadingType().getRef();
		mr.AddReadingsForecast(forecasting_time, h_forecasting_value, measure_type_id, generation_date, false);
		managers_mr.add(mr);
		return true;
	}


	public boolean CreateOccupancy(String room_id,
		double occup_value, javax.xml.datatype.XMLGregorianCalendar h_measure_time) throws Exception {
		MeterReadingsManager mr = new MeterReadingsManager("OCCUPANCY");
		mr.SetMeter("ROOM_"+room_id, null, null);
//		String cimReadingType = mr.AddReadings(h_measure_time, occup_value, "A-OCCUP", false).getReadingType().getRef();
		mr.AddReadings(h_measure_time, occup_value, "A-OCCUP", false);
		managers_mr.add(mr);
		return true;
	}

	public boolean CreateCellFlexibility(String cell_id,
		double cellflex_powercharge,
		double cellflex_powerrelease,
		double cellflex_storecharge,
		double cellflex_storerelease) throws Exception {
		MeterConfigManager mc = new MeterConfigManager("CELLFLEXIBILITY");
		mc.AddCellFlexibility(cell_id, null, null, cellflex_powercharge, cellflex_powerrelease, cellflex_storecharge, cellflex_storerelease);
		managers_mc.add(mc);
		return true;
	}

// for compatibility with existing code. Will be remove on next release.
	public boolean CreateAlarm(String device_id, String issuer_id,
			String description, String alarm_id_in_alarm_table, javax.xml.datatype.XMLGregorianCalendar h_alarm_time) throws Exception {
		return CreateCepAlarm(device_id, issuer_id, description, alarm_id_in_alarm_table, h_alarm_time);
	}

	public boolean CreateCepAlarm(String device_id, String issuer_id,
		String description, String alarm_id_in_alarm_table, javax.xml.datatype.XMLGregorianCalendar h_alarm_time) throws Exception {
		MeterReadingsManager mr = new MeterReadingsManager("ALARM");
		mr.SetMeter(device_id, null, null);
		mr.AddAlarm(h_alarm_time, issuer_id, alarm_id_in_alarm_table, description, "ALARM");
		managers_mr.add(mr);
		return true;
	}

	public boolean 	CreateApplianceState(String appliance_id,
			javax.xml.datatype.XMLGregorianCalendar h_state_time,
			String state) throws Exception {
			MeterReadingsManager mr = new MeterReadingsManager("NILM");
			mr.SetMeter(appliance_id, null, null);
			mr.AddApplianceState(h_state_time, state);
			managers_mr.add(mr);
			return true;
		}

	public String GetSql() throws Exception {
		StringBuffer sqlCommands = new StringBuffer();
		for (java.util.Iterator<MeterReadingsManager> e = managers_mr.iterator(); e.hasNext();) {
			MeterReadingsManager mr = e.next();
			sqlCommands.append(mr.ToSql(null));
		}
		for (java.util.Iterator<EndDeviceControlsManager> e = managers_edc.iterator(); e.hasNext();) {
			EndDeviceControlsManager edc = e.next();
			sqlCommands.append(edc.ToSql());
		}
		for (java.util.Iterator<MeterConfigManager> e = managers_mc.iterator(); e.hasNext();) {
			MeterConfigManager edc = e.next();
			sqlCommands.append(edc.ToSql());
		}
		sqlCommands.append(this.manager_users.ToSql());
		sqlCommands.append(this.manager_phyconf.ToSql());
		sqlCommands.append(this.manager_market.ToSql());
		return sqlCommands.toString();
	}

	public String GetXml() throws Exception {
		StringBuffer xmlStuff = new StringBuffer();
		for (java.util.Iterator<MeterReadingsManager> e = managers_mr.iterator(); e.hasNext();) {
			MeterReadingsManager mr = e.next();
			xmlStuff.append(mr.Marshal());
		}
		for (java.util.Iterator<EndDeviceControlsManager> e = managers_edc.iterator(); e.hasNext();) {
			EndDeviceControlsManager edc = e.next();
			xmlStuff.append(edc.Marshal());
		}
		for (java.util.Iterator<MeterConfigManager> e = managers_mc.iterator(); e.hasNext();) {
			MeterConfigManager edc = e.next();
			xmlStuff.append(edc.Marshal());
		}
		xmlStuff.append(manager_users.Marshal());
		xmlStuff.append(manager_phyconf.Marshal());
		xmlStuff.append(manager_market.Marshal());
		return xmlStuff.toString();
	}


	public boolean CreateUserCommand(String issuer, String device_id, String control_type_id, String control_value,
		javax.xml.datatype.XMLGregorianCalendar device_event_time, double duration) throws Exception {
		EndDeviceControlsManager edc = new EndDeviceControlsManager("USERCMD");

		edc.AddUserControl (
			device_event_time,
			duration,
			issuer,
			null,
			control_type_id,
			control_value,
			device_id);

		managers_edc.add(edc);
		return true;
	}

	public boolean CreateCellFlexibility(
			String cell_id,
			javax.xml.datatype.XMLGregorianCalendar datefrom,
			javax.xml.datatype.XMLGregorianCalendar dateto,
			double energy_in,
			double energy_out,
			double power_in,
			double power_out) throws Exception {
		EndDeviceControlsManager edc = new EndDeviceControlsManager("CELLFLEXIBILITY");
		edc.AddCellFlexibility("CELL_"+cell_id, datefrom, dateto, energy_in, energy_out, power_in, power_out);
		managers_edc.add(edc);
		return true;
	}

	public boolean CreateConsumptionOrder(String cell_id, String device_id, double value,
			javax.xml.datatype.XMLGregorianCalendar datefrom,
			javax.xml.datatype.XMLGregorianCalendar dateto) throws Exception {
		EndDeviceControlsManager edc = new EndDeviceControlsManager("CONSUMPTIONORDER");
		edc.AddConsumptionOrder(device_id, cell_id, datefrom, dateto, value);
		managers_edc.add(edc);
		return true;
	}

	public boolean CreateCellSurplusNeeds(String cell_id, double value,
			javax.xml.datatype.XMLGregorianCalendar timestamp) throws Exception {
		EndDeviceControlsManager edc = new EndDeviceControlsManager("SURPLUSNEEDS");
		edc.AddCellSurplusNeeds("CELL_"+cell_id, timestamp, value);
		managers_edc.add(edc);
		return true;
	}

	public boolean CreateTextMessage(String command_id, String issuer, String cell_id,
		String msg, javax.xml.datatype.XMLGregorianCalendar h_measure_time) throws Exception {
		EndDeviceControlsManager edc = new EndDeviceControlsManager("TEXT");
		edc.AddEndDeviceControlText(command_id, h_measure_time, -1, issuer, null, "CELL_"+cell_id, msg);
		managers_edc.add(edc);
		return true;
	}

	public boolean CreatePriceBrokerControl(String macrocell_id, String unit_type_id, double price_value) throws Exception {
		EndDeviceControlsManager edc = new EndDeviceControlsManager("PRICE_BROKER");
		edc.AddEndDeviceControlPrice(null, null, "MACRO_"+macrocell_id, price_value, unit_type_id); managers_edc.add(edc); return true;}

	public boolean CreatePriceUtilitiesControl(String macrocell_id, String unit_type_id, double price_value) throws Exception {
		EndDeviceControlsManager edc = new EndDeviceControlsManager("PRICE_UTIL");
		edc.AddEndDeviceControlPrice(null, null, "MACRO_"+macrocell_id, price_value, unit_type_id); managers_edc.add(edc); return true;}

	public boolean CreatePriceLocalMarket(javax.xml.datatype.XMLGregorianCalendar price_local_time, String macrocell_id, String unit_type_id, double price_value) throws Exception {
		EndDeviceControlsManager edc = new EndDeviceControlsManager("PRICE_MKT");
		edc.AddEndDeviceControlPrice(price_local_time, null, "MACRO_"+macrocell_id, price_value, unit_type_id); managers_edc.add(edc); return true;}

	public boolean CreatePriceIntraday(String macrocell_id, int intraday, javax.xml.datatype.XMLGregorianCalendar price_time, double price_value, String unit_type_id) throws Exception {
		if (null == price_time) price_time = RabbitUtils.getXMLGregorianCalendarNow();
		EndDeviceControlsManager edc = new EndDeviceControlsManager("PRICE_INTRADAY");
		javax.xml.datatype.XMLGregorianCalendar time_intraday = (javax.xml.datatype.XMLGregorianCalendar)price_time.clone();
		if (time_intraday.getHour()>=3*intraday) time_intraday.setHour(time_intraday.getHour()+1);
		time_intraday.setTime(3*intraday, 0, 0);
		edc.AddEndDeviceControlPrice(price_time, time_intraday, "MACRO_"+macrocell_id, price_value, unit_type_id); managers_edc.add(edc); return true;}


	public static CIMWrapper Unmarshal(String xmldata) throws Exception {
		return Unmarshal(xmldata, null);
	}

	public static CIMWrapper Unmarshal(String xmldata, CIMWrapper oldcim) throws Exception {
		if (null == xmldata) return null;
		String semantics = InferSemantics(xmldata);
		CIMWrapper cim = oldcim;
		if (null == cim) cim = new CIMWrapper();
		if (
				"MarketParticipant".equals(semantics)
			) {
			cim.manager_market = MarketManager.Unmarshal(xmldata, cim.manager_market);
		}
		if (
				"CIM_AssignedIdentity".equals(semantics) ||
				"CIM_Identity".equals(semantics) ||
				"CIM_Account".equals(semantics)
			) {
			cim.manager_users = UsersManager.Unmarshal(xmldata, cim.manager_users);
		}
		if (
				"ConfAppliance".equals(semantics) ||
				"ConfCell".equals(semantics) ||
				"ConfGateway".equals(semantics) ||
				"ConfMacroCell".equals(semantics) ||
				"ConfManufacturer".equals(semantics) ||
				"ConfRoom".equals(semantics) ||
				"ConfShadowDevice".equals(semantics)
			) {
			cim.manager_phyconf = PhysicalConfManager.Unmarshal(xmldata, cim.manager_phyconf);
		}
		if ("MeterConfig".equals(semantics)) {
			MeterConfigManager edc = MeterConfigManager.Unmarshal(xmldata);
			edc.InferSemantics();
//			System.out.println("semantics: "+edc.InferSemantics());
			cim.managers_mc.add(edc);
		}
		if ("EndDeviceControls".equals(semantics)) {
			EndDeviceControlsManager edc = EndDeviceControlsManager.Unmarshal(xmldata);
			edc.InferSemantics();
//			System.out.println("semantics: "+edc.InferSemantics());
			cim.managers_edc.add(edc);
		}
		if ("MeterReadings".equals(semantics)) {
			MeterReadingsManager mr = MeterReadingsManager.Unmarshal(xmldata);
			mr.InferSemantics();
//			System.out.println("semantics: "+mr.InferSemantics());
			cim.managers_mr.add(mr);
		}
		return cim;
	}

	public static void SmallTest(CIMWrapper cim) throws Exception {
		SmallTest(cim, m_silent);
	}

	public static void SmallTest(CIMWrapper cim, boolean silent) throws Exception {
		String sql1 = cim.GetSql();
		System.out.println(sql1);
		String xmldata = cim.GetXml();
		if (!silent) System.out.println("XML data:\n"+xmldata);
		if (!silent) System.out.println("XML semantics: '"+InferSemantics(xmldata)+"'");
		cim.Reset();
		CIMWrapper.Unmarshal(xmldata, cim);
		String sql2 = cim.GetSql();
		if (!silent) System.out.println("SQL:\n"+sql2);
		if (!sql2.equals(sql1)) throw new Exception("inconsistencies in SQL!");
		cim.Reset();
	}

	public static String InferSemantics(String xmldata) {
		String pattern = "<(\\w+)";
		java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
		java.util.regex.Matcher m = r.matcher(xmldata);
		if (m.find()) {
			return m.group(0).substring(1);
		}
		return null;
	}

	public void TestLongCmd() throws Exception {

		EndDeviceControlsManager edc = new EndDeviceControlsManager("USERCMD");
for (int i=1;i<=10;i++) {
		edc.AddUserControl (
			null,
			1,
			"ISEP",
			null,
			"LOADCONTROLSTATUS", "enable",
			"ACTUATOR"+i);
		edc.AddUserControl (
				null,
				1,
				"ISEP",
				null,
				"SETPOINTCHANGE", "22",
				"ACTUATOR"+i);
		edc.AddUserControl (
				null,
				1,
				"ISEP",
				null,
				"SETPOINTENABLE", "TRUE",
				"ACTUATOR"+i);
}
/*		cim.CreateUserCommand("ISEP", "ACTUATOR4", "SETPOINTRESET", "default", null, 1);SmallTest(cim);
		cim.CreateUserCommand("ISEP", "ACTUATOR4", "FLOWCHANGE", "0", null, 1);SmallTest(cim);*/
		
		managers_edc.add(edc);
		SmallTest(this);
	}


	public static void CheckUpc() throws Exception {
		CIMWrapper cim = new CIMWrapper();
		String msg=
	"<?xml version=\"1.0\"?>\n"+
	"<MeterReadings xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://www.encourage-project.eu/MeterReadings#\">\n"+
	  "<MeterReading>\n"+
"      <Meter>"+
 "   <mRID>zwave01625CBE_513</mRID>"+
"</Meter>"+
	    "<Readings>\n"+
	      "<timeStamp>2013-10-17T09:00:00Z</timeStamp>\n"+
	      "<value>1,21716</value>\n"+
	     " <ReadingType ref=\"7.6.7.20.0.12.0.0.0.3.72\" />\n"+
	     " <timePeriod />\n"+
	   " </Readings>\n"+
	  "  <valuesInterval />\n"+
	 " </MeterReading>\n"+
	"</MeterReadings>";
		cim.Reset();
		CIMWrapper.Unmarshal(msg, cim);
		String sql2 = cim.GetSql();
		System.out.println("SQL:\n"+sql2);
		String example2 = "<?xml version=\"1.0\"?>\n"+
"		<MeterReadings xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://www.encourage-project.eu/MeterReadings#\">\n"+
"	  <MeterReading>\n"+
"      <Meter>"+
"   <mRID>zwave01625CBE_513</mRID>"+
"</Meter>"+
"		    <Readings>\n"+
		"      <timeStamp>2013-10-17T09:00:00Z</timeStamp>\n"+
		 "     <value>1,17202</value>\n"+
	"      <ReadingType ref=\"12.0.6.1.0.8.0.0.0.3.38\" />\n"+
		     "  <timePeriod />\n"+
		   " </Readings>\n"+
		  "  <valuesInterval />\n"+
		 " </MeterReading>\n"+
		"</MeterReadings>\n";
		cim.Reset();
		CIMWrapper.Unmarshal(example2, cim);
		String sql3 = cim.GetSql();
		System.out.println("SQL:\n"+sql3);
	}

	public boolean CreateSpanishMeasurement(String device_id, javax.xml.datatype.XMLGregorianCalendar h_measure_time) throws Exception {
		MeterReadingsManager mr = new MeterReadingsManager("MEASURE");
		mr.SetMeter(device_id, null, null);
		String cimReadingType1 = mr.AddReadings(h_measure_time, 10.0, "E-POWCONS", false).getReadingType().getRef();
		String cimReadingType2 = mr.AddReadings(h_measure_time, 8.0, "E-POWCONSR", false).getReadingType().getRef();
		String cimReadingType3 = mr.AddReadings(h_measure_time, 1, "W-MOV", false).getReadingType().getRef(); // a binary that should be connection status
		String cimReadingType4 = mr.AddReadings(h_measure_time, 11.9, "W-V", false).getReadingType().getRef();
		managers_mr.add(mr);
		return true;
	}

	public static void main(String[] argv) throws Exception {
		CIMWrapper cim0 = new CIMWrapper();
		cim0.CreateSpanishMeasurement("zwave01625CBE_513", RabbitUtils.getXMLGregorianCalendarNow());SmallTest(cim0);

		System.exit(0);
		cim0.CreateMeasure("zwave01625CBE_513", "W-T", null, 34.06, null);SmallTest(cim0);
		CheckUpc();


		CIMWrapper cim = new CIMWrapper();
//		cim.TestLongCmd();

		boolean normal_tests = true;
		normal_tests = false;
		javax.xml.datatype.XMLGregorianCalendar beginning = RabbitUtils.getXMLGregorianCalendarNow();
		javax.xml.datatype.XMLGregorianCalendar ending = RabbitUtils.getXMLGregorianCalendarNow();
		if (ending.getMonth() > 11)
			ending.setYear(ending.getYear()+1);
		else
			ending.setMonth(ending.getMonth()+1);
if (normal_tests) {

		cim.CreateCellFlexibility("DKDEM01", beginning, ending, 800, 300, 20, 10);SmallTest(cim);

		cim.CreateCellSurplusNeeds("DKDEM01", 200, null);SmallTest(cim);

		// available "user commands":
		cim.CreateUserCommand("ISEP", "DKDJ010101", "LOADCONTROLSTATUS", "1", null, 1);SmallTest(cim);
		cim.CreateUserCommand("ISEP", "DKDJ010101", "SETPOINTCHANGE", "22", null, 1);SmallTest(cim);
		cim.CreateUserCommand("ISEP", "DKDJ010101", "SETPOINTRESET", "1", null, 1);SmallTest(cim);
		cim.CreateUserCommand("ISEP", "DKDJ010101", "SETPOINTENABLE", "0", null, 1);SmallTest(cim);
		cim.CreateUserCommand("ISEP", "DKDJ010101", "FLOWCHANGE", "0", null, 1);SmallTest(cim);

		cim.CreateConsumptionOrder("DKDEM01", "DKDJ010101", 200, beginning, ending);SmallTest(cim);

		cim.CreateEnergyExchange(null, 10, "DKDEM01", "DKDEM02");SmallTest(cim);

		// an "advice_to_user":
		// a text message is permanent until dismissed by the user
		// the first field must be "Alam", "Alert" or "Advice" - see Per's email of 10/6/2013
		cim.CreateTextMessage("Alarm", "SUPERVISORY CONTROL", "DKDEM01", "you are doing fine", null);SmallTest(cim);
		cim.CreateTextMessage("Alert", "SUPERVISORY CONTROL", "DKDEM01", "you are doing fine", null);SmallTest(cim);
		cim.CreateTextMessage("Advice", "SUPERVISORY CONTROL", "DKDEM01", "you are doing fine", null);SmallTest(cim);

		// forecasting.
		// If supercategory is "AMB", it is for the table h_forecasting_table - it is related to a cell, not a macrocell!!!
		// If it is related to energy, it gets into h_forecasting.
		cim.CreateForecasting("DKDEM01", null, 5, null, "E-CONS");SmallTest(cim);
		cim.CreateForecasting("DKDEM01", null, 5, null, "W-T");SmallTest(cim);
		
		// like CreateMeasure, but "device_id" is a "macrocell_id". measure_type_id can be "W-T", W-SRAND, W-RAIN, W-WS, W-H, W-TMEAN, W-DD
		cim.CreateWeather("DKD", "W-T", 12);SmallTest(cim);

		// for the "price_energy" table. Intradays are indexed 0-7, and are related to hours: midnight, 3am, 6am, 9am, noon, 3pm, 6pm, 9pm.
		// please comment on this!
		cim.CreatePriceIntraday("DKD", 3, null, 11.233, "EKWH");SmallTest(cim);


		// control message to set a new energy price to a macrocell:
		cim.CreatePriceLocalMarket(null, "DKD", "DKWH", 4.2);SmallTest(cim);
		cim.CreatePriceLocalMarket(null, "DKD", "DKWH", 4.2222);SmallTest(cim);

		// in the future, we will have the configuration messages described here:
//		cim.CreatePriceUtilitiesControl("TERRASSA", "EKWH", 6.22);SmallTest(cim);
//		cim.CreatePriceBrokerControl("PISA2", "EKWH", 5.22);SmallTest(cim);
		// but they are NOT to be used now.


		cim.CreateAccess("cister", "superuser");SmallTest(cim);
		cim.CreatePrivilege("superuser", "everything good and bad");SmallTest(cim);
		cim.CreateUser("michele", "Don't guess it please", "cister");SmallTest(cim);
		cim.CreateUser("LLF", "barco rapido", "cister");SmallTest(cim);



		cim.CreateComfort("casa", null, null, 20, 10, "W-T");SmallTest(cim);
		cim.CreateMarketParticipant("CISTER", "codefish broker", "Porto cell1");SmallTest(cim);

		// Cep generated Alarms:
		cim.CreateCepAlarm("DKDJ010101", "CEP", "alarm computed by CEP", "111", null);SmallTest(cim);

}


		// measure_type_id can be the ones reported in the following:
cim.CreateMeasure("DKDJ010101", "W-T", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-SRAD", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-RAIN", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-WS", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-H", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-TMEAN", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-DD", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W_LIGHT_I", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W_LIGHT_V", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-MOV", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-DOOR", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-UV", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-WD", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-OCCUP", null, 1, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-CO2", null, 1, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-SMK", null, 1, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-WAT", null, 1, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-GAS", null, 1, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-BAR", null, 1, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-V", null, 1, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "W-I", null, 1, null);SmallTest(cim);


cim.CreateMeasure("DKDJ010101", "E-CONS", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "E-PROD", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "E-POWCONS", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "E-POWPROD", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "E-CONSR", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "E-PRODR", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "E-POWCONSR", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "E-POWPRODR", null, 5, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "E-WAT", null, 1, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "E-GAS", null, 1, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "E-OIL", null, 1, null);SmallTest(cim);

cim.CreateMeasure("DKDJ010101", "S-ELEC", null, 1, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "S-WAT", null, 1, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "S-FUEL", null, 1, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "S-GAS", null, 1, null);SmallTest(cim);
cim.CreateMeasure("DKDJ010101", "S-OIL", null, 1, null);SmallTest(cim);


		
//m_silent = false;
		// appliance states, for the NILM:
		cim.CreateApplianceState("WASHINGMACHINE", beginning, "APPLIANCEON");SmallTest(cim);
		cim.CreateApplianceState("WASHINGMACHINE", ending, "APPLIANCEOFF");SmallTest(cim);

		cim.CreateManufacturer("my students doing footballs in the underground office", "really??", "CISTER", "mialb", "sturdy");SmallTest(cim);
		cim.CreateGateway("data gateway prison room", "42", "11:22:33", "CISTER", "41");SmallTest(cim);
		cim.CreateMacroCell("Porto", "PT1", "Portugal");SmallTest(cim);
		cim.CreateCell("21", "CISTER premises", "CISTER", "11", "22", "10", "20", "0", "PT1");SmallTest(cim);
		cim.CreateRoom("CISTER", "office for the students", "PRISON", "40", "10", "20");SmallTest(cim);
		cim.CreateAppliance("today", "heating system", "HEAT1.1", "1 dollar", "yesterday", "0.1W", "heating", "PRISON", "do not know");SmallTest(cim);
		cim.CreateShadowDevice("HEAT1.1", "fake heating sensor", "temp1.1.1", "data", "CISTER", "42", "41");SmallTest(cim);
		cim.CreateWeather("UPC", "W-SRAD", 42);SmallTest(cim);

		System.exit(0);
	}
}
