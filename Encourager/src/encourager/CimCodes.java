/**
CimCodes.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager;

import java.util.List;
import java.util.ArrayList;

public class CimCodes {

static {
listReadingType = new ArrayList<ReadingType>();
addReadingType(0, 0, 0, 46, 0, 23, 0, "W-T", "A", "AMB", "C"); // TEMPERATURE (INSTANT)
addReadingType(11, 0, 0, 37, 0, 179, 0, "W-SRAD", "A", "AMB", "WM2"); // SOLAR RADIATION
addReadingType(11, 0, 0, 58, -3, 2, 0, "W-RAIN", "A", "AMB", "MM"); // PRECIPITATION IN MM
addReadingType(11, 0, 0, 14, 0, 43, 0, "W-WS", "A", "AMB", "MS"); // WIND SPEED
addReadingType(11, 0, 0, 0, -2, 0, 0, "W-H", "A", "AMB", "P"); // HUMIDITY

addReadingType(11, 0, 0, 46, 0, 23, 0, "W-TMEAN", "A", "AMB", "C"); // DAILY MEAN TEMPERATURE
addReadingType(11, 4, 0, 46, 0, 23, 0, "W-DD", "A", "AMB", "AVGC"); // DEGREE DAYS
addReadingType(0, 0, 0, 155, 0, 34, 0, "W_LIGHT_I", "A", "AMB", "LUX"); // Light - Infrarred
addReadingType(0, 0, 0, 37, 0, 34, 0, "W_LIGHT_V", "A", "AMB", "LUX"); // Light - Visible
addReadingType(11, 0, 0, 0, 0, 0, 0, "W-MOV", "A", "AMB", "BIN"); // MOVEMENT
addReadingType(0, 0, 0, 90, 0, 111, 0, "W-DOOR", "A", "AMB", "BIN"); // Door opnening or closure
addReadingType(0, 0, 0, 37, 0, 150, 0, "W-UV", "A", "AMB", "AD"); // UV INDEX
addReadingType(0, 0, 0, 5, 0, 9, 0, "W-WD", "A", "AMB", "O"); // Wind direction
addReadingType(0, 0, 0, 90, 0, 0, 0, "W-OCCUP", "A", "AMB", "BIN"); // keys: measure_type_id
addReadingType(0, 0, 18, 32, -6, 0, 0, "W-CO2", "A", "AMB", "PPM"); // 
addReadingType(0, 0, 32, 32, 0, 0, 0, "W-SMK", "A", "AMB", "BIN"); // 
addReadingType(0, 0, 9, 32, 0, 0, 0, "W-WAT", "A", "AMB", "BIN"); // 
addReadingType(0, 0, 7, 32, 0, 0, 0, "W-GAS", "A", "AMB", "BIN"); // 

addReadingType(0, 0, 4, 0, 2, 39, 0, "W-BAR", "A", "AMB", "HPA"); // 
addReadingType(0, 1, 2, 54, 0, 29, 0, "W-V", "A", "AMB", "V"); // 
addReadingType(0, 1, 2, 4, 0, 5, 0, "W-I", "A", "AMB", "A"); // 



addReadingType(0, 1, 0, 12, 3, 72, 0, "E-CONS", "C", "GEN", "KWH"); // keys: measure_type_id, category_id
addReadingType(0, 19, 0, 12, 3, 72, 0, "E-PROD", "P", "POTHER", "KWH"); // keys: measure_type_id
addReadingType(0, 1, 0, 35, 3, 73, 0, "E-CONSR", "C", "GEN", "KVAH"); // keys: measure_type_id, category_id
addReadingType(0, 19, 0, 35, 3, 73, 0, "E-PRODR", "P", "POTHER", "KVAH"); // keys: measure_type_id
/* ISA */ addReadingType(3, 20, 0, 12, 3, 72, 0, "E-CONS", "C", "GEN", "KWH"); // total energy flow. Positive = consumed
/* UPC */ addReadingType(7, 20, 0, 12, 3, 72, 0, "E-CONS", "C", "GEN", "KWH"); // total energy flow. Positive = consumed
addReadingType(0, 1, 0, 37, 3, 38, 0, "E-POWCONS", "C", "GEN", "KW"); // keys: measure_type_id, category_id
addReadingType(0, 19, 0, 37, 3, 38, 0, "E-POWPROD", "P", "POTHER", "KW"); // keys: measure_type_id, category_id
addReadingType(0, 1, 0, 35, 3, 63, 0, "E-POWCONSR", "C", "GEN", "KVA"); // keys: measure_type_id, category_id
addReadingType(0, 19, 0, 35, 3, 63, 0, "E-POWPRODR", "P", "POTHER", "KVA"); // keys: measure_type_id, category_id
/* ISA */ addReadingType(12, 1, 0, 8, 3, 38, 0, "E-POWCONS", "C", "GEN", "KW"); // swung value
addReadingType(0, 19, 0, 12, 3, 72, 978, "E-PRICEEU", "P", "POTHER", "EKWH"); // keys: measure_type_id
addReadingType(0, 19, 0, 12, 3, 72, 208, "E-PRICEDK", "P", "POTHER", "DKWH"); // keys: measure_type_id
addReadingType(0, 1, 9, 58, 0, 42, 0, "E-WAT", "A", "AMB", "M3"); // 
addReadingType(0, 1, 7, 58, 0, 42, 0, "E-GAS", "A", "AMB", "M3"); // 
addReadingType(0, 1, 6, 58, 0, 42, 0, "E-OIL", "A", "AMB", "M3"); // 


addReadingType(0, 0, 0, 12, 3, 72, 0, "S-ELEC", "S", "SEV", "KWH"); // keys: measure_type_id, category_id
addReadingType(0, 0, 9, 58, 0, 42, 0, "S-WAT", "A", "AMB", "M3"); // 
addReadingType(0, 0, 32, 58, 0, 42, 0, "S-FUEL", "A", "AMB", "M3"); // 
addReadingType(0, 0, 7, 58, 0, 42, 0, "S-GAS", "A", "AMB", "M3"); // 
addReadingType(0, 0, 6, 58, 0, 42, 0, "S-OIL", "A", "AMB", "M3"); // 
}

	public static class ReadingType {
		public int timeAttribute, dof, commodity, kind, pot, uom, currency;
		public String measure_type_id, supercategory_id, category_id, unit_id;

		public String DumpGnera() {return "measure_type_id: "+measure_type_id+"\nsupercategory_id: "+supercategory_id+"\ncategory_id: "+category_id+"\nunit_id: "+unit_id+"\n";}

		public String ToCIMString() {
			return timeAttribute+".0.0.0."+dof+"."+commodity+"."+kind+".0.0.0.0.0.0.0.0."+pot+"."+uom+"."+currency;
		}

		public ReadingType(int mtimeAttribute, int mdof, int mcommodity, int mkind, int mpot, int muom, int mcurrency,
			String mmeasure_type_id, String msupercategory_id, String mcategory_id, String munit_id) {
timeAttribute = mtimeAttribute;
dof = mdof;
commodity = mcommodity;
kind = mkind;
pot = mpot;
uom = muom;
currency = mcurrency;
measure_type_id = mmeasure_type_id;
supercategory_id = msupercategory_id;
category_id = mcategory_id;
unit_id = munit_id;
		}
	}

	private static List<ReadingType> listReadingType;
	private static boolean addReadingType(int mtimeAttribute, int mdof, int commodity, int mkind, int mpot, int muom, int mcurrency,
			String mmeasure_type_id, String msupercategory_id, String mcategory_id, String munit_id) {
		return listReadingType.add(new ReadingType(mtimeAttribute, mdof, commodity, mkind, mpot, muom, mcurrency,
			mmeasure_type_id, msupercategory_id, mcategory_id, munit_id));
	}

	public static ReadingType rtFromCategoryId(String mcategory_id) {
		for (java.util.Iterator<ReadingType> e = listReadingType.iterator(); e.hasNext();) {
			ReadingType ec = e.next();
			if (mcategory_id.equals(ec.category_id)) return ec;
			}
		return null;
	}

	public static ReadingType rtFromMeasureTypeId(String mmeasure_type_id) {
		for (java.util.Iterator<ReadingType> e = listReadingType.iterator(); e.hasNext();) {
			ReadingType ec = e.next();
			if (mmeasure_type_id.equals(ec.measure_type_id)) return ec;
			}
		return null;
	}

	public static ReadingType rtFromCimName(int mtimeAttribute, int mdof, int mcommodity, int mkind, int mpot, int muom, int mcurrency) {
		for (java.util.Iterator<ReadingType> e = listReadingType.iterator(); e.hasNext();) {
			ReadingType ec = e.next();
			if (
ec.timeAttribute == mtimeAttribute &&
ec.dof == mdof &&
ec.commodity == mcommodity &&
ec.kind == mkind &&
ec.pot == mpot &&
ec.uom == muom &&
ec.currency == mcurrency) return ec;
			}
		return null;
	}

	public static ReadingType rtFromCimName(String cimName) {
		int mtimeAttribute=0, mdof=0, mcommodity=0, mkind=0, mpot=0, muom=0, mcurrency=0;
		String[] p = cimName.split("\\.");
		// mtimeAttribute is [0] for both versions
		// mdof is [4] for new, [3] for old
		// mkind is [6] for new, [5] for old se [4]==0 for old
		//                       90+[5] se [4]==1 for old
		//                       118+[5] se [4]==2 for old
		// mpot is [15] for new, [9]
		// muom is [16] for new, [10] for old
		// mcurrency is [17] for new, not there

		if (18 == p.length) {
			mtimeAttribute = Integer.parseInt(p[0]);
			mdof = Integer.parseInt(p[4]);
			mcommodity = Integer.parseInt(p[5]);
			mkind = Integer.parseInt(p[6]);
			mpot = Integer.parseInt(p[15]);
			muom = Integer.parseInt(p[16]);
			mcurrency = Integer.parseInt(p[17]);
		} else if (11 == p.length) {
			mtimeAttribute = Integer.parseInt(p[0]);
			mdof = Integer.parseInt(p[3]);
			mkind = Integer.parseInt(p[5]);
			if (1 == Integer.parseInt(p[4])) mkind += 90;
			if (2 == Integer.parseInt(p[4])) mkind += 118;
			mpot = Integer.parseInt(p[9]);
			muom = Integer.parseInt(p[10]);
			mcurrency = 0;
		} else {
			System.out.println("length = "+p.length);
			System.exit(-1);
		}

		return rtFromCimName(mtimeAttribute, mdof, mcommodity, mkind, mpot, muom, mcurrency);
	}

	public static String DateCIMtoGneraWeird(String cimdate) {
		javax.xml.datatype.DatatypeFactory datatypeFactory=null;
		try {
			datatypeFactory = javax.xml.datatype.DatatypeFactory.newInstance();
		} catch (javax.xml.datatype.DatatypeConfigurationException e) {System.out.println(e.toString()); System.exit(-1);}
		javax.xml.datatype.XMLGregorianCalendar parsedDate = null;
		if (cimdate == null) {
			cimdate = RabbitUtils.getXMLGregorianCalendarNow().toString();
		}
		parsedDate = 
			datatypeFactory.newXMLGregorianCalendar(cimdate);

		String result = "";
		result += parsedDate.getYear();
		if (parsedDate.getMonth() < 10) result += "0";
		result += parsedDate.getMonth();
		if (parsedDate.getDay() < 10) result += "0";
		result += parsedDate.getDay();
		if (parsedDate.getHour() < 10) result += "0";
		result += parsedDate.getHour();
		if (parsedDate.getMinute() < 10) result += "0";
		result += parsedDate.getMinute();
		if (parsedDate.getSecond() < 10) result += "0";
		result += parsedDate.getSecond();

		return result;
	}

	public static String DateCIMtoGneraSQL(String cimdate) {
		javax.xml.datatype.DatatypeFactory datatypeFactory=null;
		try {
			datatypeFactory = javax.xml.datatype.DatatypeFactory.newInstance();
		} catch (javax.xml.datatype.DatatypeConfigurationException e) {System.out.println(e.toString()); System.exit(-1);}
		javax.xml.datatype.XMLGregorianCalendar parsedDate = null;
		if (cimdate == null) {
			cimdate = RabbitUtils.getXMLGregorianCalendarNow().toString();
		}
		parsedDate = 
			datatypeFactory.newXMLGregorianCalendar(cimdate);

		java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.toGregorianCalendar().getTimeInMillis());
		return timestamp.toString();
	}

	public static String DateCIMtoGnera(String cimdate) {
		return DateCIMtoGneraWeird(cimdate);
	}

	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes("UTF-8"));
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		} catch (java.io.UnsupportedEncodingException e) {
		}
		return null;
	}

static {
listEventType = new ArrayList<EventType>();
addEventType(17 /*PAN meter*/, 21 /*Metrology*/, 285 /*An alarm*/, 40/*OutOfRange*/, "ALARM");
addEventType(23 /*energy router*/, 20 /*Billing*/, 83 /*Program*/, 24/*Changed*/, "PRICE_UTIL");
addEventType(23 /*energy router*/, 20 /*Billing*/, 9 /*Pricing*/, 24/*Changed*/, "PRICE_MKT");
addEventType(23 /*energy router*/, 20 /*Billing*/, 16 /*Calculation*/, 24/*Changed*/, "PRICE_BROKER");
addEventType(23 /*energy router*/, 20 /*Billing*/, 95 /*Schedule*/, 24/*Changed*/, "PRICE_INTRADAY");
addEventType(12 /*PAN device*/, 13 /*VideoDisplay*/, 143 /*Display (noun)*/, 77/*Display (verb)*/, "TEXT");
addEventType(16 /*LocalControlDevice*/, 15 /* Load Control */, 17 /* status */, 13 /* change */, "LOADCONTROLSTATUS");
addEventType(16 /*LocalControlDevice*/, 15 /* Load Control */, 101 /* setpoint */, 13 /* change */, "SETPOINTCHANGE");
addEventType(16 /*LocalControlDevice*/, 15 /* Load Control */, 101 /* setpoint */, 214 /* Reset */, "SETPOINTRESET");
addEventType(16 /*LocalControlDevice*/, 15 /* Load Control */, 101 /* setpoint */, 26 /* enable */, "SETPOINTENABLE");
addEventType(16 /*LocalControlDevice*/, 15 /* Load Control */, 48 /* flow */, 13 /* change */, "FLOWCHANGE");

addEventType(12 /*PAN device*/, 26 /* Power */, 17 /* Status */, 4 /* activated */, "APPLIANCEON");
addEventType(12 /*PAN device*/, 26 /* Power */, 17 /* Status */, 19 /* deactivated */, "APPLIANCEOFF");

addEventType(3 /*electric meter*/, 31 /* RCD */, 48 /* config */, 13 /* demand limiting */, "CONSUMPTIONORDER");
addEventType(6 /*grid power*/, 21 /* metrology */, 13 /* outage */, 98 /* Imbalance */, "SURPLUSNEEDS");

addEventType(12 /*PAN device*/, 2 /* Battery */, 6 /* current */, 13 /* change */, "MAXINENERGY");
addEventType(12 /*PAN device*/, 2 /* Battery */, 38 /* voltage */, 296 /* limit changed */, "MAXINPOWER");
addEventType(14 /*generator*/, 2 /* Battery */, 6 /* current */, 13 /* change */, "MAXOUTENERGY");
addEventType(14 /*generator*/, 2 /* Battery */, 38 /* voltage */, 296 /* limit changed */, "MAXOUTPOWER");


}

	private static List<EventType> listEventType;

	private static boolean addEventType(int mType, int mDomain, int mSubdomain, int mEventOrAction, String mGneraCode) {
		EventType et = new EventType(mType, mDomain, mSubdomain, mEventOrAction, mGneraCode);
		return listEventType.add(et);
	}

	public static EventType eventTypeFromGneraType(String gnera_type) {
		for (java.util.Iterator<EventType> e = listEventType.iterator(); e.hasNext();) {
			EventType ec = e.next();
			if (gnera_type.equals(ec.GneraCode)) return ec;
			}
		return null;
	}

	public static EventType eventTypeFromCIM(String cimName) {
		int type, domain, subdomain, eventoraction;
		String[] p = cimName.split("\\.");
		type = Integer.parseInt(p[0]);
		domain = Integer.parseInt(p[1]);
		subdomain = Integer.parseInt(p[2]);
		eventoraction = Integer.parseInt(p[3]);

		return eventTypeFromCIM(type, domain, subdomain, eventoraction);
	}

	public static EventType eventTypeFromCIM(int mType, int mDomain, int mSubdomain, int mEventOrAction) {
		for (java.util.Iterator<EventType> e = listEventType.iterator(); e.hasNext();) {
			EventType ec = e.next();
			if (
				mType == ec.Type &&
				mDomain == ec.Domain &&
				mSubdomain == ec.Subdomain &&
				mEventOrAction == ec.EventOrAction)
					return ec;
			}
		return null;
	}

	public static class EventType {
		int Type; int Domain; int Subdomain; int EventOrAction; String GneraCode;
		public String ToCIMString() {
			return ""+Type+"."+Domain+"."+Subdomain+"."+EventOrAction;
		}

		public EventType(int mType, int mDomain, int mSubdomain, int mEventOrAction, String mGneraCode) {
			Type = mType;
			Domain = mDomain;
			Subdomain = mSubdomain;
			EventOrAction = mEventOrAction;
			GneraCode = mGneraCode;
		}
	}

	private static class EventCode {
		public String Mnemonic;
		public int Code;
		public String Description;
		public EventCode(String m, int c, String d) {
			Mnemonic = m;
			Code = c;
			Description = d;
		}
	}

	public static String[] RetrieveType(int code) {return Retrieve(listType, code);}
	public static String[] RetrieveType(String mnemonic) {return Retrieve(listType, mnemonic);}
	public static String[] RetrieveDomain(int code) {return Retrieve(listDomain, code);}
	public static String[] RetrieveDomain(String mnemonic) {return Retrieve(listDomain, mnemonic);}
	public static String[] RetrieveSubdomain(int code) {return Retrieve(listSubdomain, code);}
	public static String[] RetrieveSubdomain(String mnemonic) {return Retrieve(listSubdomain, mnemonic);}
	public static String[] RetrieveEventOrAction(int code) {return Retrieve(listEventOrAction, code);}
	public static String[] RetrieveEventOrAction(String mnemonic) {return Retrieve(listEventOrAction, mnemonic);}

	private static String[] Retrieve(List<EventCode> list, String mnemonic) {
		for (java.util.Iterator<EventCode> e = list.iterator(); e.hasNext();) {
			EventCode ec = e.next();
			if (mnemonic.equals(ec.Mnemonic)) {
				String[] ret = new String[3];
				ret[0] = ec.Mnemonic;
				ret[1] = ""+ec.Code;
				ret[2] = ec.Description;
				return ret;
			}
		}
		return null;
	}
	private static String[] Retrieve(List<EventCode> list, int code) {
		for (java.util.Iterator<EventCode> e = list.iterator(); e.hasNext();) {
			EventCode ec = e.next();
			if (code == ec.Code) {
				String[] ret = new String[3];
				ret[0] = ec.Mnemonic;
				ret[1] = ""+ec.Code;
				ret[2] = ec.Description;
				return ret;
			}
		}
		return null;
	}

	private static List<EventCode> listType;
	private static List<EventCode> listDomain;
	private static List<EventCode> listSubdomain;
	private static List<EventCode> listEventOrAction;

	private static void addEndDeviceEventType(String mnemonic, int code, String description) {EventCode ec = new EventCode(mnemonic, code, description);listType.add(ec);}
	private static void addEndDeviceEventDomain(String mnemonic, int code, String description) {EventCode ec = new EventCode(mnemonic, code, description);listDomain.add(ec);}
	private static void addEndDeviceEventSubdomain(String mnemonic, int code, String description) {EventCode ec = new EventCode(mnemonic, code, description);listSubdomain.add(ec);}
	private static void addEndDeviceEventEventOrAction(String mnemonic, int code, String description) {EventCode ec = new EventCode(mnemonic, code, description);listEventOrAction.add(ec);}

	static {
		listType = new ArrayList<EventCode>();
		listDomain = new ArrayList<EventCode>();
		listSubdomain = new ArrayList<EventCode>();
		listEventOrAction = new ArrayList<EventCode>();

addEndDeviceEventType("n/a", 0, "Not applicable. Use when a device type is not known.");
addEndDeviceEventType("Collector", 10, "A device that acts as a central point of communication between HES and devices located on premises.");
addEndDeviceEventType("ComDevice", 26, "A communication device");
addEndDeviceEventType("DAPDevice", 1, "A data aggregation point device");
addEndDeviceEventType("DERDevice", 2, "A demand response device");
addEndDeviceEventType("DSPDevice", 6, "A digital signal processing device");
addEndDeviceEventType("ElectricMeter", 3, "A device located on premises to measure electricity usage.");
addEndDeviceEventType("ElectricVehicle", 58, "(or Plug-in Electric Vehicle, PEV) a vehicle that can be plugged into the grid.");
addEndDeviceEventType("EnergyRouter", 23, "An energy router, analogous to the familiar data and communications router, automatically detects demand for power and delivers processed electricity in the required form (AC or DC) at the correct voltage and frequency on an electrical power system.");
addEndDeviceEventType("Feeder", 13, "Feeders carry three-phase power, and tend to follow the major streets near the substation.");
addEndDeviceEventType("GasMeter", 4, "A device located on premises to measure gas usage.");
addEndDeviceEventType("Gateway", 5, "A gateway device.");
addEndDeviceEventType("Generator", 14, "Typically a spinning electrical generator. Something has to spin the generator -- it might be a water wheel in a hydroelectric dam, a large diesel engine or a gas turbine.");
addEndDeviceEventType("InPremisesDisplay(IPD/IHD)", 15, "The In-Premises (In-Home) Display (IPD/IHD) allows utility customers to track their energy usage in chart or graph form based upon kwH used.");
addEndDeviceEventType("LoadControlDevice", 16, "A device used to implement deferrable services  commonly referred to as 'off-peak'.");
addEndDeviceEventType("NetworkRouter", 11, "A router distributes Digital computer information that is contained within a data packet on a network.");
addEndDeviceEventType("PANDevice", 12, "A 'premises area network' device that is not specifically described in further detail.");
addEndDeviceEventType("PANGateway", 7, "A PAN gateway connects an external communications network to energy management devices within the premises.");
addEndDeviceEventType("PANMeter", 17, "A 'premises area network' device whose function is to measure (e.g. electricity usage).");
addEndDeviceEventType("PrepaymentTerminal", 18, "A device that enables the customer to make advance payment before energy can be used.");
addEndDeviceEventType("ProgCtlThermostat(PCT)", 19, "A thermostat device whose settings can be controlled via an API (ie. without human intervention).");
addEndDeviceEventType("RangeExtender", 20, "Wireless range-extenders or wireless repeaters can extend the range of an existing wireless network.");
addEndDeviceEventType("Regulator", 21, "A voltage regulator is an electrical regulator designed to automatically maintain a constant voltage level.");
addEndDeviceEventType("Substation", 22, "An electrical substation is a subsidiary station of an electricity generation, transmission and distribution system where voltage is transformed from high to low or the reverse using transformers.");
addEndDeviceEventType("Transformer", 8, "A device that converts a generator's voltage (which is at the thousands of volts level) up to extremely high voltages for long-distance transmission on the transmission grid.");
addEndDeviceEventType("WasteWaterMeter", 25, "A device that measures waste water usage.");
addEndDeviceEventType("WaterMeter", 24, "A device that measures water usage.");

addEndDeviceEventDomain("n/a", 0, "Not applicable. Use when a domain is not needed. This should rarely be used.");
addEndDeviceEventDomain("AssociatedDevice", 39, "A device (for example, a relay) that can be associated with an end device.");
addEndDeviceEventDomain("Battery", 2, "Any events or controls related to a device battery.");
addEndDeviceEventDomain("Billing", 20, "Events or controls related to cost of energy (Including Pricing, Tariff, TOU, etc.).");
addEndDeviceEventDomain("Cartridge", 3, "Events or controls related to cartridge fuses.");
addEndDeviceEventDomain("Clock", 36, "Events or controls related to a device internal clock.");
addEndDeviceEventDomain("Communication", 1, "Events or controls related to purely communication isssues. Consider other domains before using this one.");
addEndDeviceEventDomain("Configuration", 7, "Events or controls related to device configuration.");
addEndDeviceEventDomain("Demand", 8, "Events or controls related to demand (ie. kW) and demand settings (as opposed to consumption (ie. kWh).");
addEndDeviceEventDomain("Firmware", 11, "Events or controls related to device firmware.");
addEndDeviceEventDomain("GasSupply", 4, "Events or controls related to the supply of natural gas or propane.");
addEndDeviceEventDomain("Installation", 6, "Events or controls related to device installation.");
addEndDeviceEventDomain("KYZPulseCounter", 38, "Pulse counting function inside a meter or other end device.");
addEndDeviceEventDomain("LoadControl", 15, "Events or controls related to the automatic restriction or control of a customers energy consumption.");
addEndDeviceEventDomain("LoadProfile", 16, "Events or controls related to the energy consumption (ie. 'load') over time on a device.");
addEndDeviceEventDomain("Logs", 17, "Events or controls related to device internal logs.");
addEndDeviceEventDomain("Memory", 18, "Events or controls related to device memory.");
addEndDeviceEventDomain("Metrology", 21, "Events or controls related to any type of measurement captured by a device.");
addEndDeviceEventDomain("MobileSecurity", 14, "Events or controls related to device security when the device is accessed via a mobile tool or device.");
addEndDeviceEventDomain("Modem", 19, "Events or controls related to a devices modem.");
addEndDeviceEventDomain("ModuleFirmware", 9, "Events or controls related to firmware on a module contained by a device.");
addEndDeviceEventDomain("Network", 23, "Events or controls generally related to a devices status on the network. Also used for general network events, such as commissioning of a PAN Area network.");
addEndDeviceEventDomain("Pairing", 10, "Events or controls related to linking devices together (e.g. PANDevice to Meter, ComDevice to Meter, etc.).");
addEndDeviceEventDomain("Power", 26, "Events or controls related to device energization status.");
addEndDeviceEventDomain("Pressure", 29, "Events or controls related to device pressure thresholds.");
addEndDeviceEventDomain("RCDSwitch", 31, "Events or controls related to device remote connect/disconnect activities.");
addEndDeviceEventDomain("Recoder", 41, "A device for encoding.");
addEndDeviceEventDomain("Security", 12, "Events or controls related to device security (including SecurityKey, HMAC, Parity, Rotation, other TamperDetection, etc.).");
addEndDeviceEventDomain("Temperature", 35, "Events or controls related to device.");
addEndDeviceEventDomain("VideoDisplay", 13, "Events or controls related to device CRT/display.");
addEndDeviceEventDomain("Volume", 40, "A quantity of 3-dimensional space enclosed by a boundary; the space occupied by a liquid or gas.");
addEndDeviceEventDomain("Watchdog", 37, "A hardware or software function triggered by a timer expiring.");
addEndDeviceEventDomain("WaterSupply", 5, "Events or controls related to the supply of water.");

addEndDeviceEventSubdomain("n/a", 0, "Not applicable. Use when a domain is not needed. This should rarely be used.");
addEndDeviceEventSubdomain("Access", 1, "Related to physical security (ie. Accessability) or electronic permission to read/write digital media.");
addEndDeviceEventSubdomain("Activation", 283, "Initiation of a function.");
addEndDeviceEventSubdomain("AlarmTable", 285, "A table in a device for the tracking of alarms.");
addEndDeviceEventSubdomain("ADConverter", 142, "Related to analog-to-digital conversion.");
addEndDeviceEventSubdomain("AllEvents", 148, "Related to a set of events (typically used in a Load Control Cancel All Events scenario)");
addEndDeviceEventSubdomain("ApparentPower", 290, "The magnitude of the complex power measured in voltamps.");
addEndDeviceEventSubdomain("Allocation", 2, "Related to designation or allotment; Typically related to memory (RAM/ROM)");
addEndDeviceEventSubdomain("Association", 74, "Related to the linking/pairing of one device/object to another device/object.");
addEndDeviceEventSubdomain("AUTDProcess", 3, "Related to Always Up To Date processes; watchdog or keep-alive processes.");
addEndDeviceEventSubdomain("AutoRegistration", 5, "Related to automatic registration process.");
addEndDeviceEventSubdomain("AutoTime", 7, "Related to automatic setting of time.");
addEndDeviceEventSubdomain("BTU", 13, "Related to British Thermal Units.");
addEndDeviceEventSubdomain("Buffer", 14, "Related to temporary data storage.");
addEndDeviceEventSubdomain("Cable", 15, "Related to a physical cable.");
addEndDeviceEventSubdomain("Calculation", 16, "Related to mathematical computation.");
addEndDeviceEventSubdomain("Calibration", 18, "Related to a set of gradations that show positions or values.");
addEndDeviceEventSubdomain("Certificate", 21, "Related to a document testifying to the truth of something; typically a security certificate.");
addEndDeviceEventSubdomain("Charge", 22, "Related to electrical charge; Related to billing charge.");
addEndDeviceEventSubdomain("Checksum", 284, "A fixed-size datum computed from an arbitrary block of digital data for the purpose of detecting accidental errors.");
addEndDeviceEventSubdomain("Concentration", 39, "Related to the density or composition of something.");
addEndDeviceEventSubdomain("Constants", 23, "Related to statically defined values.");
addEndDeviceEventSubdomain("ControlPoint", 26, "Related to load control settings.");
addEndDeviceEventSubdomain("Cover", 29, "Related to something that provides shelter; a covering.");
addEndDeviceEventSubdomain("CRC", 30, "Related to cyclical redundancy check.");
addEndDeviceEventSubdomain("Credit", 8, "Related to the right-hand side of an account; billing.");
addEndDeviceEventSubdomain("Current", 6, "Related to electrical power measured in amperes.");
addEndDeviceEventSubdomain("Data", 31, "Related to factual information.");
addEndDeviceEventSubdomain("DataLog", 33, "Related to a record (ie. Log) of factual information.");
addEndDeviceEventSubdomain("Date", 34, "Related to calendar time.");
addEndDeviceEventSubdomain("Day", 35, "Related to the day portion of calendar time.");
addEndDeviceEventSubdomain("DaylightSavingsTime", 56, "Related to the practice of setting the clock forward one hour in the spring.");
addEndDeviceEventSubdomain("DayLimit", 299, "A limit established for a daily period.");
addEndDeviceEventSubdomain("Decryption", 36, "Related to making encrypted data readable.");
addEndDeviceEventSubdomain("Display", 143, "Related to a CRT, LED, or other form of video device.");
addEndDeviceEventSubdomain("Door", 128, "Related to a moveable barrier used to cover an opening; as in a door to a meter or collector.");
addEndDeviceEventSubdomain("EmergencySupplyCapacityLimit", 138, "Related to emergency supply capacity limits.");
addEndDeviceEventSubdomain("Encoder", 40, "Related to the thing that converts information from one format to another.");
addEndDeviceEventSubdomain("EncoderRegister", 41, "Related to the encoder register (ie. On a meter)");
addEndDeviceEventSubdomain("EPROM", 42, "Related to erasable programmable read-only memory.");
addEndDeviceEventSubdomain("Event", 43, "Related to something that has happened; other, more specific subdomains should be used before using this one.");
addEndDeviceEventSubdomain("EventLog", 44, "Related to a record (ie. Log) of event data.");
addEndDeviceEventSubdomain("EWM", 45, "Related to an external wireless module.");
addEndDeviceEventSubdomain("Feature", 46, "Related to a non-specific characteristic.");
addEndDeviceEventSubdomain("FirmwareReset", 47, "Related to reverting of firmware to original state.");
addEndDeviceEventSubdomain("Flow", 48, "Related to the movement of a substance (electricity, gas, water, etc.)");
addEndDeviceEventSubdomain("FPV", 49, "Related to a form of super-compressibility.");
addEndDeviceEventSubdomain("Frames", 50, "Related to fixed-sized blocks; as in memory.");
addEndDeviceEventSubdomain("Frequency", 4, "Related to the number of cycles per unit of time.");
addEndDeviceEventSubdomain("GCAnalyzer", 51, "Related to gas chromatograph analyzer which is use to measure the component mixture of the natural gas delivers to a site.");
addEndDeviceEventSubdomain("HeadEndSystem", 52, "Related to the metering/AMI system.");
addEndDeviceEventSubdomain("HistoryLog", 53, "Related to a record (ie. Log) of historical data.");
addEndDeviceEventSubdomain("HMAC", 54, "Related to hash-based message authentication code; a specific method for calculated a MAC.");
addEndDeviceEventSubdomain("Holiday", 97, "Related to days set aside having special significance.");
addEndDeviceEventSubdomain("Identity", 10, "Related to a unique identifier.");
addEndDeviceEventSubdomain("Initialisation", 298, "Start-up function.");
addEndDeviceEventSubdomain("Input", 55, "Related to data entered into the system.");
addEndDeviceEventSubdomain("InstallDate", 57, "Related to the prepared for use date.");
addEndDeviceEventSubdomain("IntelligentRegister", 58, "Related to a specific register on a device.");
addEndDeviceEventSubdomain("Interval", 59, "Related to interval energy data; the time between to events.");
addEndDeviceEventSubdomain("IO", 60, "Related to general input/output.");
addEndDeviceEventSubdomain("IPAddress", 127, "Related to an IP Address (Internet Protocol Address)");
addEndDeviceEventSubdomain("LANAddress", 61, "Related to a unique identification of a device on a network of devices.");
addEndDeviceEventSubdomain("LastRead", 62, "Related to the final reading from a meter.");
addEndDeviceEventSubdomain("List", 63, "Related to an internal list [contained in memory or on firmware]");
addEndDeviceEventSubdomain("ListPointers", 64, "Related to a specific set of values kept by a meter.");
addEndDeviceEventSubdomain("Login", 65, "Related to the process by which access is gained to a device, computer, or system.");
addEndDeviceEventSubdomain("LowSpeedBus", 145, "Related to a circuit that connects CPU with other devices; low-speed transmission.");
addEndDeviceEventSubdomain("MagneticSwitch", 66, "Related to any type of magnetic switch.");
addEndDeviceEventSubdomain("MaintMode", 11, "Related to a specific mode of operation into which a device can be set.");
addEndDeviceEventSubdomain("Measurement", 67, "Relating to the magnitude of a quantity.");
addEndDeviceEventSubdomain("MeasurementType", 286, "A code defining the kind of data under measurement.");
addEndDeviceEventSubdomain("Mesh", 68, "Typically related to the type of meter network.");
addEndDeviceEventSubdomain("MeterBus", 147, "Related to a circuit that connects a devices or module to a meter.");
addEndDeviceEventSubdomain("MeteringMode", 12, "Related to a specific mode of operation into which a device can be set.");
addEndDeviceEventSubdomain("Mobile", 69, "Related to devices that are not confined to one place.");
addEndDeviceEventSubdomain("MOL%", 70, "Related to percentage of moles.");
addEndDeviceEventSubdomain("NetworkId", 71, "Related to a unique identification of a device on a network of devices.");
addEndDeviceEventSubdomain("NeutralCurrent", 137, "Related to the essential part of electroweak unification.");
addEndDeviceEventSubdomain("NVRAM", 72, "Related to non-volatile random access memory.");
addEndDeviceEventSubdomain("OptionBoard", 146, "Related to a type of module in a meter.");
addEndDeviceEventSubdomain("Parameter", 75, "Related to a variable passed to a function.");
addEndDeviceEventSubdomain("ParentDevice", 76, "Related to a devices owner.");
addEndDeviceEventSubdomain("Parity", 77, "Typically related to an odd/even or on/off state; a symmetry property.");
addEndDeviceEventSubdomain("Password", 24, "Related to a secret word used for authentication.");
addEndDeviceEventSubdomain("Phase", 25, "Typically related to a means of distributing alternating current; When the specific phase is irrelevant, this should be used as the EndDeviceSubdomain.");
addEndDeviceEventSubdomain("PhaseAngle", 130, "Related to the angular component of the polar coordinates.");
addEndDeviceEventSubdomain("PhaseA", 126, "Related to the A phase of a multi-phase circuit.");
addEndDeviceEventSubdomain("PhaseAApparentPower", 291, "The apparent power on phase A of a multi-phase circuit.");
addEndDeviceEventSubdomain("PhaseACurrent", 287, "Related to the current of the first phase of 3-phase power.");
addEndDeviceEventSubdomain("PhaseAReactivePower", 295, "The reactive power on phase A of a multi-phase circuit.");
addEndDeviceEventSubdomain("PhaseAVoltage", 131, "Related to the voltage of the first phase of 3-phase power.");
addEndDeviceEventSubdomain("PhaseAVoltagePotential", 126, "Related to the voltage potential of the first phase of 3-phase power.");
addEndDeviceEventSubdomain("PhaseB", 134, "Related to the B phase of a multi-phase circuit.");
addEndDeviceEventSubdomain("PhaseBApparentPower", 292, "The apparent power on phase B of a multi-phase circuit.");
addEndDeviceEventSubdomain("PhaseBCurrent", 288, "Related to the current of the second phase of 3-phase power.");
addEndDeviceEventSubdomain("PhaseBReactivePower", 295, "The reactive power on phase B of a multi-phase circuit.");
addEndDeviceEventSubdomain("PhaseBVoltage", 132, "Related to the voltage of the second phase of 3-phase power.");
addEndDeviceEventSubdomain("PhaseBVoltagePotential", 134, "Related to the voltage potential of the second phase of 3-phase power.");
addEndDeviceEventSubdomain("PhaseC", 135, "Related to the C phase of a multi-phase circuit.");
addEndDeviceEventSubdomain("PhaseCApparentPower", 293, "The apparent power on phase C of a multi-phase circuit.");
addEndDeviceEventSubdomain("PhaseCCurrent", 289, "Related to the current of the third phase of 3-phase power.");
addEndDeviceEventSubdomain("PhaseCReactivePower", 296, "The reactive power on phase C of a multi-phase circuit.");
addEndDeviceEventSubdomain("PhaseCVoltage", 133, "Related to the voltage of the third phase of 3-phase power.");
addEndDeviceEventSubdomain("PhaseCVoltagePotential", 135, "Related to the voltage potential of the third phase of 3-phase power.");
addEndDeviceEventSubdomain("PhaseSequence", 78, "Related to the order of the phases in multi-phase power.");
addEndDeviceEventSubdomain("PhaseVoltage", 79, "In single-phase or in situations where the specific phase is irrelevant, this is related to voltage across the phase.");
addEndDeviceEventSubdomain("PowerFactor", 27, "Related to the ratio of the real power flowing to the load to the apparent power in the circuit.");
addEndDeviceEventSubdomain("PowerQuality", 28, "Related to the set of limits of electrical properties that allows electrical systems to function in their intended manner without significant loss of performance.");
addEndDeviceEventSubdomain("PowerQualityRecording", 80, "Related to the capture and storage of power quality data.");
addEndDeviceEventSubdomain("PrepaymentCredit", 81, "Related to the right-hand side of an account; billing for prepayment accounts.");
addEndDeviceEventSubdomain("Pricing", 9, "Related to billing.");
addEndDeviceEventSubdomain("Processor", 82, "Related to a CPU, typically.");
addEndDeviceEventSubdomain("Program", 83, "Related to a pre-defined set of instructions.");
addEndDeviceEventSubdomain("Pulse", 84, "Related to a means by which energy is measured.");
addEndDeviceEventSubdomain("Queue", 197, "Related to a relatively temporary storage area used to hold requests or tasks until they can be processed.");
addEndDeviceEventSubdomain("Radio", 136, "Related to a physical device that processes radio signals.");
addEndDeviceEventSubdomain("RAM", 85, "Related to random access memory.");
addEndDeviceEventSubdomain("Rate", 86, "Related to the speed or velocity.");
addEndDeviceEventSubdomain("ReadAccess", 202, "Related to the permission level one has; as in read, write, update.");
addEndDeviceEventSubdomain("Readings", 87, "Related to the collection of consumption, diagnostic, and status data from a meter.");
addEndDeviceEventSubdomain("ReactivePower", 294, "Power that does not perform work measured in VA reactive (VAr)");
addEndDeviceEventSubdomain("Recoder", 300, "A device for encoding.");
addEndDeviceEventSubdomain("Recovery", 88, "Related to a process of restoring from a broken state.");
addEndDeviceEventSubdomain("Register", 89, "Related to a placeholder for information.");
addEndDeviceEventSubdomain("Registration", 90, "Related to a process by which a device is recognized or added.");
addEndDeviceEventSubdomain("Relay", 91, "Related to an electrically operated switch.");
addEndDeviceEventSubdomain("RemoteAccess", 211, "Related to physical security (ie. accessability) or electronic permission to read/write digital media from a mobile device or from a location other than where the object being accessed is.");
addEndDeviceEventSubdomain("ROM", 92, "Related to read-only memory.");
addEndDeviceEventSubdomain("Rotation", 93, "Related to the movement of an object in a circular motion.");
addEndDeviceEventSubdomain("RTP", 94, "Related to real-time pricing.");
addEndDeviceEventSubdomain("Schedule", 95, "Related to a timetable or plan of future events.");
addEndDeviceEventSubdomain("Season", 228, "Related to the division of a year marked by changes in weather; typically winter, spring, summer, and fall.");
addEndDeviceEventSubdomain("SecondaryCredit", 96, "Related to a non-primary amount of credit.");
addEndDeviceEventSubdomain("SecuredRegister", 98, "Related to a specific register on a device.");
addEndDeviceEventSubdomain("SecuredTable", 99, "Related a table that requires authorization prior to access being granted.");
addEndDeviceEventSubdomain("SecurityKey", 32, "Related to a piece of information that determines the functional output of a cryptographic cipher.");
addEndDeviceEventSubdomain("SecurityKeyLength", 119, "Related to the length of a security key.");
addEndDeviceEventSubdomain("SecurityKeyVersion", 120, "Related to the version of a security key.");
addEndDeviceEventSubdomain("SelfRead", 231, "Related to a process where a device will read itself.");
addEndDeviceEventSubdomain("SelfTest", 100, "Related to a process where a device will run an internal test on itself.");
addEndDeviceEventSubdomain("Sensor", 144, "Related to a mechanical device that transmits a signal to a measuring device.");
addEndDeviceEventSubdomain("Session", 129, "Related to a communication session, typically.");
addEndDeviceEventSubdomain("SetPoint", 101, "Related to the threshold at which a feature is engaged; typically related to load control.");
addEndDeviceEventSubdomain("SigmaticMessage", 102, "Related to sigmatic messages.");
addEndDeviceEventSubdomain("Signature", 103, "Related to electronic security and signing of messages.");
addEndDeviceEventSubdomain("SignatureLength", 104, "Related to the length of a security signature.");
addEndDeviceEventSubdomain("SignatureTimestamp", 105, "Related to the timeframe within which a security signature is valid.");
addEndDeviceEventSubdomain("SignatureUsage", 106, "Related to how a signature is being used.");
addEndDeviceEventSubdomain("SpecificGravity", 240, "Related to the ratio of the density of a substance to the density of water.");
addEndDeviceEventSubdomain("StandardTime", 107, "Related to the opposite of daylight savings time.");
addEndDeviceEventSubdomain("StandbyMode", 108, "Related to a specific mode of operation into which a device can be set.");
addEndDeviceEventSubdomain("Status", 17, "Related to the current state of something.");
addEndDeviceEventSubdomain("Storage", 109, "Related to the medium on which information is kept; also related to the act of storing information.");
addEndDeviceEventSubdomain("SupplyCapacityLimit", 139, "Related to supply capacity limits.");
addEndDeviceEventSubdomain("Table", 110, "Relating to a structure containing rows and columns.");
addEndDeviceEventSubdomain("Tariff", 140, "Billing term relating to cost or amount chaged.");
addEndDeviceEventSubdomain("Test", 111, "Related to a classification that specifies non-production.");
addEndDeviceEventSubdomain("TestMode", 19, "Related to a specific mode of operation into which a device can be set.");
addEndDeviceEventSubdomain("TextMessage", 112, "Related to a message or set of characters that are sent to a device.");
addEndDeviceEventSubdomain("Threshold", 261, "Related to a level or point at which something will happen.");
addEndDeviceEventSubdomain("Tier", 113, "Related to a level.");
addEndDeviceEventSubdomain("Time", 114, "Related to time of day, as in hours:minutes:seconds:miliseconds.");
addEndDeviceEventSubdomain("Timeout", 125, "Related to a specific threshold specifying when to automatically return after having received no response.");
addEndDeviceEventSubdomain("TimeReset", 115, "Related to the resetting of the time of day.");
addEndDeviceEventSubdomain("TimeSync", 116, "Related to the process of adjusting the time of day value on a device to match that of a trusted source for time of day.");
addEndDeviceEventSubdomain("TimeVariance", 117, "Related to the acceptable difference of a device time of day as compared to a trusted source for time of day.");
addEndDeviceEventSubdomain("TimeZone", 118, "Related to the time regions around the Earth defined by the lines of longitude.");
addEndDeviceEventSubdomain("TOU", 121, "Related to time of use.");
addEndDeviceEventSubdomain("Tranceiver", 122, "Related to a device that has both a transmitter and a receiver.");
addEndDeviceEventSubdomain("Usage", 123, "Related to how something is used.");
addEndDeviceEventSubdomain("Version", 124, "Related to a specific iteration or translation.");
addEndDeviceEventSubdomain("Voltage", 38, "Related to the electrical force that would drive an electric current between two points.");
addEndDeviceEventSubdomain("Window", 73, "Related to a period of time during which a device can be linked/paired with a meter or other device.");
addEndDeviceEventSubdomain("WriteAccess", 282, "Related to the permission level one has; as in read, write, update.");

addEndDeviceEventEventOrAction("n/a", 0, "Not applicable. Use when a domain is not needed. This should rarely be used.");
addEndDeviceEventEventOrAction("Aborted", 1, "An event that occurs when some intervention causes the item (identified by the EndDeviceDomain/EndDeviceSubdomain) to stop.");
addEndDeviceEventEventOrAction("Accessed", 2, "Typically a security event that occurs when physical access or access to data has been obtained (whether permitted or not).");
addEndDeviceEventEventOrAction("Acknowledged", 3, "An event that indicates the receipt of the item (identified by the EndDeviceDomain/EndDeviceSubdomain).");
addEndDeviceEventEventOrAction("Activated", 4, "An event that indicates that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) that was inactive is now active.");
addEndDeviceEventEventOrAction("AlmostFull", 283, "An event to indicate that a resource is near capacity.");
addEndDeviceEventEventOrAction("ArmedForClosure", 11, "An event that indicates that an ArmForClosure command has been completed successfully.");
addEndDeviceEventEventOrAction("ArmedForOpen", 12, "An event that indicates that an ArmForOpen command has been completed successfully.");
addEndDeviceEventEventOrAction("ArmForClosure", 5, "A command to indicate a request to arm a switch for closure.");
addEndDeviceEventEventOrAction("ArmForClosureFailed", 226, "An event that indicates that an ArmForClosure has failed.");
addEndDeviceEventEventOrAction("ArmForOpen", 6, "A command to indicate a request to arm a switch for open.");
addEndDeviceEventEventOrAction("ArmForOpenFailed", 222, "An event that indicates that an ArmFor Open has failed.");
addEndDeviceEventEventOrAction("Attempted", 7, "An event that indicates that the item (identified by the EndDeviceDomain/EndDeviceSubdomain), based on the EndDeviceDomain and EndDeviceSubdomain combination, has been tried.");
addEndDeviceEventEventOrAction("Calculated", 21, "An event that indicates that the item (identified by the EndDeviceDomain/EndDeviceSubdomain), based on the EndDeviceDomain and EndDeviceSubdomain combination, has been computed.");
addEndDeviceEventEventOrAction("Cancel", 8, "A command to indicate a request to terminate a prior issued command.");
addEndDeviceEventEventOrAction("CancelFailed", 86, "An event that indicates that a Cancel has failed.");
addEndDeviceEventEventOrAction("Cancelled", 10, "An event that indicates that a prior issued command or set of commands was terminated successfully.");
addEndDeviceEventEventOrAction("Change", 13, "A command to indicate a request to make modifications.");
addEndDeviceEventEventOrAction("Changed", 24, "An event that indicates that a related Change request has completed successfully.");
addEndDeviceEventEventOrAction("ChangedOut", 284, "An event to indicate that an asset has been replaced.");
addEndDeviceEventEventOrAction("ChangeOutRequired", 27, "A command to request that a device is replaced by a new device of the same kind.");
addEndDeviceEventEventOrAction("ChangePending", 14, "An event that indicates that an update has not yet been performed.");
addEndDeviceEventEventOrAction("Charged", 15, "An event that can indicate a billing-related state or in the form of being electrically charged.");
addEndDeviceEventEventOrAction("Cleared", 28, "An event that indicates that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) on the device has been either resolved or emptied.");
addEndDeviceEventEventOrAction("Closed", 16, "An event that indicates the item (identified by the EndDeviceDomain/EndDeviceSubdomain) on the device that had been open is not open anymore.");
addEndDeviceEventEventOrAction("ColdStarted", 31, "An event that indicates the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been started from a stopped state (as opposed to a WarmStart which implies that it was started from an already started state).");
addEndDeviceEventEventOrAction("Confirmed", 17, "An event that indicates the receipt and agreement of the item (identified by the EndDeviceDomain/EndDeviceSubdomain).");
addEndDeviceEventEventOrAction("Connect", 18, "A command to request that a device be put into service.");
addEndDeviceEventEventOrAction("Connected", 42, "An event to indicate that a device has been put into service.");
addEndDeviceEventEventOrAction("ConnectFailed",67, "An event that indicates a Connect request has failed.");
addEndDeviceEventEventOrAction("Corrupted", 43, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been altered from a correct state to an incorrect state.");
addEndDeviceEventEventOrAction("CorruptionCleared", 281, "An event to indicate that a corruption condition has been cleared.");
addEndDeviceEventEventOrAction("Create", 82, "A command to request that something be created.");
addEndDeviceEventEventOrAction("Created", 83, "An event that indicates that a Create request succeeded.");
addEndDeviceEventEventOrAction("CreateFailed", 297, "An event that indicates that a Create request failed.");
addEndDeviceEventEventOrAction("CrossPhaseCleared", 70, "An event that indicates that instability due to cross-phase modulation has been corrected.");
addEndDeviceEventEventOrAction("CrossPhaseDetected", 45, "An event that indicates instability due to cross-phase modulation.");
addEndDeviceEventEventOrAction("Deactivated", 19, "An event that indicates that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) on the device that had previously been in an active state is no longer active.");
addEndDeviceEventEventOrAction("Decreased", 57, "An event that indicates that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has a lower value or magnitude.");
addEndDeviceEventEventOrAction("Delayed", 20, "An event that indicates the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is slower than expected or will complete later than expected.");
addEndDeviceEventEventOrAction("Disable", 22, "A command to request that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) be rendered incapable.");
addEndDeviceEventEventOrAction("Disabled", 66, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) was successfully rendered incapable.");
addEndDeviceEventEventOrAction("DisableFailed", 220, "An event that indicates that a Disable request has failed.");
addEndDeviceEventEventOrAction("Disallowed", 161, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) was not allowed.");
addEndDeviceEventEventOrAction("Disconnect", 23, "A command to request that a device be pulled from service; can also mean a request to sever connection to the item (identified by the EndDeviceDomain/EndDeviceSubdomain).");
addEndDeviceEventEventOrAction("Disconnected", 68, "An event to indicate that a device was successfully pulled from service; can also mean that connection to the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been severed or terminated.");
addEndDeviceEventEventOrAction("DisconnectFailed", 84, "An event that indicates that a Disconnect request has failed.");
addEndDeviceEventEventOrAction("Display", 77, "A command to request the display of something (as in a TextMessage).");
addEndDeviceEventEventOrAction("Displayed", 78, "An event that indicates that a Display request completed successfully.");
addEndDeviceEventEventOrAction("DisplayFailed", 87, "An event that indicates that a Display request failed.");
addEndDeviceEventEventOrAction("Distorted", 91, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been altered from its expected state.");
addEndDeviceEventEventOrAction("Downloaded", 25, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) transmitted from the network to the device.");
addEndDeviceEventEventOrAction("Enable", 26, "A command to request that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) be rendered capable.");
addEndDeviceEventEventOrAction("Enabled", 76, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) was successfully rendered capable.");
addEndDeviceEventEventOrAction("EnableFailed", 221, "An event that indicates that an Enable request failed.");
addEndDeviceEventEventOrAction("Error", 79, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) did not complete successfully.");
addEndDeviceEventEventOrAction("ErrorCleared", 279, "An event to indicate that an error condition has been cleared.");
addEndDeviceEventEventOrAction("Established", 29, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been recognized.");
addEndDeviceEventEventOrAction("EventStarted", 287, "An event to indicate that an event (for example, demand response event) has begun.");
addEndDeviceEventEventOrAction("EventStopped", 288, "An event to indicate that an event (for example, demand response event) has halted.");
addEndDeviceEventEventOrAction("Exceeded", 139, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has gone higher than its expected value.");
addEndDeviceEventEventOrAction("Execute", 30, "A command to request that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) be performed.");
addEndDeviceEventEventOrAction("Expired", 64, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has come to an end, typically by date or time.");
addEndDeviceEventEventOrAction("Failed", 85, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has not succeeded.");
addEndDeviceEventEventOrAction("Frozen", 88, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is in a static state.");
addEndDeviceEventEventOrAction("Full", 32, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is at capacity.");
addEndDeviceEventEventOrAction("HighDistortion", 69, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has had an undesired change in the waveform of a signal.");
addEndDeviceEventEventOrAction("HighDistortionCleared", 71, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is back to normal after having been in a HighDistortion state.");
addEndDeviceEventEventOrAction("ImbalanceCleared", 75, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is back in balance after having been in an imbalanced state.");
addEndDeviceEventEventOrAction("Imbalanced", 98, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is not balanced.");
addEndDeviceEventEventOrAction("Inactive", 100, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is at a dormant state.");
addEndDeviceEventEventOrAction("InactiveCleared", 72, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is now in an active state after having been in a dormant state.");
addEndDeviceEventEventOrAction("Increased", 102, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has gotten larger.");
addEndDeviceEventEventOrAction("Initialized", 33, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been set to starting values.");
addEndDeviceEventEventOrAction("InProgress", 34, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is currently advancing toward a goal or an end.");
addEndDeviceEventEventOrAction("Installed", 105, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been prepared for use.");
addEndDeviceEventEventOrAction("Invalid", 35, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is in a faulty state.");
addEndDeviceEventEventOrAction("LimitChanged", 296, "An event to indicate that the set point for a limit has been changed.");
addEndDeviceEventEventOrAction("LimitReached", 286, "An event to indicate that an upper or lower limit has been breached.");
addEndDeviceEventEventOrAction("Loaded", 36, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is now engaged.");
addEndDeviceEventEventOrAction("LossDetected", 47, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has experienced a deprived condition. This is typically used in relation to power, voltage, or current.");
addEndDeviceEventEventOrAction("MaxLimitChanged", 295, "An event to indicate that the set point for a maximum limit has been changed.");
addEndDeviceEventEventOrAction("MaxLimitCleared", 293, "An event to indicate that a previous MaxLimitReached event has been cleared.");
addEndDeviceEventEventOrAction("MaxLimitReached", 93, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has reached a maximum acceptable value.");
addEndDeviceEventEventOrAction("MaxLimitReachedCleared", 73, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has returned to an acceptable state after having been as a MaxLimitReached state.");
addEndDeviceEventEventOrAction("MinLimitChanged", 294, "An event to indicate that the set point for a minimum limit has been changed.");
addEndDeviceEventEventOrAction("MinLimitCleared", 292, "An event to indicate that a previous MinLimitReached event has been cleared.");
addEndDeviceEventEventOrAction("MinLimitReached", 150, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has reached a minimum acceptable value.");
addEndDeviceEventEventOrAction("Mismatched", 159, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is not compatible with itself or something within its environment.");
addEndDeviceEventEventOrAction("Missing", 285, "An event to indicate that an entity (for example, asset, measurement, etc.) is missing.");
addEndDeviceEventEventOrAction("Normal", 37, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is in its typical state (or norm).");
addEndDeviceEventEventOrAction("NotArmed", 290, "An event to indicate that a device is longer in an armed state.");
addEndDeviceEventEventOrAction("NotAuthorized", 38, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been accessed without permission.");
addEndDeviceEventEventOrAction("NotFound", 160, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is lost or missing.");
addEndDeviceEventEventOrAction("Opened", 39, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is accessible.");
addEndDeviceEventEventOrAction("Opted-In", 80, "An event that indicates that a consumer has agreed to join a program.");
addEndDeviceEventEventOrAction("Opted-Out", 81, "An event that indicates that a consumer does not want to join a program.");
addEndDeviceEventEventOrAction("OutofRange", 40, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has gone outside of acceptable values.");
addEndDeviceEventEventOrAction("OutofRangeCleared", 74, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has gone back to acceptable values.");
addEndDeviceEventEventOrAction("Overflow", 177, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has exceeded its size or volume.");
addEndDeviceEventEventOrAction("Preempted", 41, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) been replaced by another that has precedence over it.");
addEndDeviceEventEventOrAction("Processed", 44, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been successfully been put through the steps of a prescribed procedure.");
addEndDeviceEventEventOrAction("Read", 46, "This can be an event (if treated as the past-tense of the verb, read) or a command (if treated as the verb, read).");
addEndDeviceEventEventOrAction("Ready", 48, "An event that indicates that a ready condition has been reached on a device.");
addEndDeviceEventEventOrAction("ReadyForActivation", 280, "An event to indicate that a device has been made ready.");
addEndDeviceEventEventOrAction("Re-established", 49, "An event that indicates that a condition, typically a connection, has achieved after having been lost.");
addEndDeviceEventEventOrAction("Registered", 50, "An event that indicates that a device or condition of a device has been recorded.");
addEndDeviceEventEventOrAction("Released", 51, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been freed.");
addEndDeviceEventEventOrAction("Removed", 212, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been pulled out of service.");
addEndDeviceEventEventOrAction("Replaced", 52, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) replaced by an new item, usually as a consequence of being old or worn out.");
addEndDeviceEventEventOrAction("Reprogrammed", 213, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has had a change to its directions or program.");
addEndDeviceEventEventOrAction("Reset", 214, "A command to request that the item (identified by the EndDeviceDomain.EndDeviceSubdomain) should be set back to a zero-state or original-state.");
addEndDeviceEventEventOrAction("ResetFailed", 65, "An event that indicates that a Reset request has failed.");
addEndDeviceEventEventOrAction("ResetOccurred", 215, "An event that indicates that a Reset request has completed successfully.");
addEndDeviceEventEventOrAction("Restarted", 53, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has started again typically from an already started state.");
addEndDeviceEventEventOrAction("Restored", 216, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been put back to its prior state.");
addEndDeviceEventEventOrAction("Reversed", 219, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has changed to be the opposite of its normal state. This is typically used for rotation.");
addEndDeviceEventEventOrAction("SagStarted", 223, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has dipped or shrunk from its expected state.");
addEndDeviceEventEventOrAction("SagStopped", 224, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has stopped sagging. Typically used in conjunction with the SagStarted event.");
addEndDeviceEventEventOrAction("Scheduled", 225, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been set to execute at a future date.");
addEndDeviceEventEventOrAction("Sealed", 227, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is in an airtight enclosure or cannot be accessed directly.");
addEndDeviceEventEventOrAction("Start", 54, "A command to request that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) be triggered or begun.");
addEndDeviceEventEventOrAction("Started", 242, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has started.");
addEndDeviceEventEventOrAction("StartFailed", 217, "An event that indicates that a Start request has failed.");
addEndDeviceEventEventOrAction("Stop", 55, "A command to request that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) be shut down normally.");
addEndDeviceEventEventOrAction("StopFailed", 218, "An event that indicates that a Stop request has failed.");
addEndDeviceEventEventOrAction("Stopped", 243, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has ceased. When this has resulted from a Stop command, it is assumed that things have stopped normally (ie. With no errors).");
addEndDeviceEventEventOrAction("Substituted", 56, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) replaced by an alternate item.");
addEndDeviceEventEventOrAction("Succeeded", 58, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) was accomplished.");
addEndDeviceEventEventOrAction("SwellStarted", 248, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has grown from its expected state.");
addEndDeviceEventEventOrAction("SwellStopped", 249, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has stopped swelling. Typically used in conjunction with the SwellStarted event.");
addEndDeviceEventEventOrAction("SwitchPositionChanged", 289, "An event to indicate that a switch position has changed.");
addEndDeviceEventEventOrAction("TamperCleared", 291, "An event to indicate that a tamper alarm has been cleared.");
addEndDeviceEventEventOrAction("TamperDetected", 257, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been secretly modified or altered. These events are typically associated with security or billing.");
addEndDeviceEventEventOrAction("Terminated", 59, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has ended abruptly.");
addEndDeviceEventEventOrAction("Tilted", 263, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been caused to lean, incline, slope, or slant.");
addEndDeviceEventEventOrAction("Uninitialized", 61, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has all its values in their starting state.");
addEndDeviceEventEventOrAction("Unlocked", 62, "An event to indicate that the items (identified by the EndDeviceDomain/EndDeviceSubdomain) lock, physical or software, is undone.");
addEndDeviceEventEventOrAction("Unsealed", 269, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is not sealed.");
addEndDeviceEventEventOrAction("Unsecure", 63, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is in an unprotected state.");
addEndDeviceEventEventOrAction("Unstable", 270, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) is in an irregular state.");
addEndDeviceEventEventOrAction("Uploaded", 60, "An event to indicate that the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been transmitted from the device to the network.");
addEndDeviceEventEventOrAction("WarmStarted", 278, "An event that indicates the item (identified by the EndDeviceDomain/EndDeviceSubdomain) has been started from an already started state (as opposed to a ColdStart which implies that it was started from a stopped state).");
addEndDeviceEventEventOrAction("WriteFailed", 282, "An event to indicate that a write operation has failed");



	}

	public static String DumpCode(String fullcode) {
		String[] codes_s = fullcode.split("\\.");
		int[] codes_i = new int[4];
		for (int i=0;i<4;i++) {
			System.out.println(i+" "+codes_s[i]);
			codes_i[i] = Integer.parseInt(codes_s[i]);
		}
		String ret = fullcode+" means\n";
		ret+="\t"+RetrieveType(codes_i[0])[0]+"\n";
		ret+="\t"+RetrieveDomain(codes_i[1])[0]+"\n";
		ret+="\t"+RetrieveSubdomain(codes_i[2])[0]+"\n";
		ret+="\t"+RetrieveEventOrAction(codes_i[3])[0]+"\n";
		return ret;
	}

	public static void SimpleTryMeasureTypeId(String category) throws Exception{
		ReadingType rt = rtFromMeasureTypeId(category);
		String readingTypeCode = rt.ToCIMString();
		rt = rtFromCimName(readingTypeCode);
		if (!rt.measure_type_id.equals(category)) throw new Exception("problem with "+category+" != "+rt.measure_type_id);
		System.out.println(readingTypeCode+"\n"+rt.DumpGnera()+"\n\n");
	}

	public static void main(String[] args) throws Exception {
//		CimCodes ed = new CimCodes();
		String date1 = RabbitUtils.getXMLGregorianCalendarNow().toString();
		System.out.println("uno "+date1);
		System.out.println("due "+DateCIMtoGnera(date1));
		System.out.println("tre "+DateCIMtoGnera(null));
		System.out.println("quattro "+MD5(date1));
		System.out.println(RetrieveType(5)[2]);
		System.out.println(RetrieveDomain(5)[2]);
		System.out.println(RetrieveSubdomain(5)[2]);
		System.out.println(RetrieveEventOrAction(5)[2]);
		System.out.println("12.3.41.2 =\n"+DumpCode("12.3.41.2"));

		SimpleTryMeasureTypeId("W-T");
		SimpleTryMeasureTypeId("W-SRAD");
		SimpleTryMeasureTypeId("W-RAIN");
		SimpleTryMeasureTypeId("W-WS");
		SimpleTryMeasureTypeId("W-H");
		SimpleTryMeasureTypeId("E-CONS");
		SimpleTryMeasureTypeId("W-TMEAN");
		SimpleTryMeasureTypeId("W-DD");
		SimpleTryMeasureTypeId("W-OCCUP");
//		SimpleTryMeasureTypeId("E-SELLEU");
//		SimpleTryMeasureTypeId("E-SELLDK");
//		SimpleTryMeasureTypeId("E-BUYEU");
//		SimpleTryMeasureTypeId("E-BUYDK");
		SimpleTryMeasureTypeId("E-PRICEEU");
		SimpleTryMeasureTypeId("E-PRICEDK");
		SimpleTryMeasureTypeId("E-PROD");



	}
}

