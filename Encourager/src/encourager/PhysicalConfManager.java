/**
PhysicalConfManager.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager;

import encourager.generated.confappliance.*;
import encourager.generated.confcell.*;
import encourager.generated.confgateway.*;
import encourager.generated.confmacrocell.*;
import encourager.generated.confmanufacturer.*;
import encourager.generated.confroom.*;
import encourager.generated.confshadowdevice.*;
import encourager.generated.*;

import java.util.List;
import java.util.ArrayList;

public class PhysicalConfManager {
	private List<ConfAppliance> m_appliances;
	private List<ConfCell> m_cells;
	private List<ConfGateway> m_gateways;
	private List<ConfMacroCell> m_macrocells;
	private List<ConfManufacturer> m_manufacturers;
	private List<ConfRoom> m_rooms;
	private List<ConfShadowDevice> m_shadowdevices;

	public static PhysicalConfManager Unmarshal(String xml, PhysicalConfManager existing_manager) throws Exception {
		PhysicalConfManager ret = null;
		if (existing_manager == null)
			ret = new PhysicalConfManager("");
		else
			ret = existing_manager;

		String temp = ""+xml;
		EncConfAppliance enc1 = encourager.generated.EncConfAppliance.CreateEncConfAppliance();
		EncConfCell enc2 = encourager.generated.EncConfCell.CreateEncConfCell();
		EncConfGateway enc3 = encourager.generated.EncConfGateway.CreateEncConfGateway();
		EncConfMacroCell enc4 = encourager.generated.EncConfMacroCell.CreateEncConfMacroCell();
		EncConfManufacturer enc5 = encourager.generated.EncConfManufacturer.CreateEncConfManufacturer();
		EncConfRoom enc6 = encourager.generated.EncConfRoom.CreateEncConfRoom();
		EncConfShadowDevice enc7 = encourager.generated.EncConfShadowDevice.CreateEncConfShadowDevice();

		int next=1;
		while (next>0) {
			next=temp.indexOf("<?", 3);
			String work = null;
			if (next > 0) {
				work = temp.substring(0,next);
				temp = temp.substring(next);
			} else {
				work = temp;
			}
			String semantics = PhysicalConfManager.InferSemantics(work);
			if (0 == "ConfAppliance".compareTo(semantics)) {
				ret.m_appliances.add(enc1.Unmarshal(work));
			}
			if (0 == "ConfCell".compareTo(semantics)) {
				ret.m_cells.add(enc2.Unmarshal(work));
			}
			if (0 == "ConfGateway".compareTo(semantics)) {
				ret.m_gateways.add(enc3.Unmarshal(work));
			}
			if (0 == "ConfMacroCell".compareTo(semantics)) {
				ret.m_macrocells.add(enc4.Unmarshal(work));
			}
			if (0 == "ConfManufacturer".compareTo(semantics)) {
				ret.m_manufacturers.add(enc5.Unmarshal(work));
			}
			if (0 == "ConfRoom".compareTo(semantics)) {
				ret.m_rooms.add(enc6.Unmarshal(work));
			}
			if (0 == "ConfShadowDevice".compareTo(semantics)) {
				ret.m_shadowdevices.add(enc7.Unmarshal(work));
			}
		}

		return ret;
	}

	public PhysicalConfManager() {
		ResetPhysicalConfManager();
	}

	public PhysicalConfManager(String sem) {
//		m_semantics = sem;
		ResetPhysicalConfManager();
	}

	public void ResetPhysicalConfManager() {
		m_appliances = new ArrayList<ConfAppliance>();
		m_cells = new ArrayList<ConfCell>();
		m_gateways = new ArrayList<ConfGateway>();
		m_macrocells = new ArrayList<ConfMacroCell>();
		m_manufacturers = new ArrayList<ConfManufacturer>();
		m_rooms = new ArrayList<ConfRoom>();
		m_shadowdevices = new ArrayList<ConfShadowDevice>();
	}


	public boolean AddAppliance(
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
		ConfAppliance ai = new ConfAppliance();

		ai.setApplianceActivacionDate(appliance_activacion_date);
		ai.setApplianceDesc(appliance_desc);
		ai.setApplianceId(appliance_id);
		ai.setApplianceInvestment(appliance_investment);
		ai.setApplianceLeavingDate(appliance_leaving_date);
		ai.setAppliancePower(appliance_power);
		ai.setLoadTypeId(load_type_id);
		ai.setRoomId(room_id);
		ai.setSubcategoryId(subcategory_id);

		m_appliances.add(ai);
		return true;
	}

	public boolean AddCell(
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
		ConfCell ai = new ConfCell();

		ai.setCellBuildingShadeCoefficient(cell_building_shade_coefficient);
		ai.setCellDesc(cell_desc);
		ai.setCellId(cell_id);
		ai.setCellLatitud(cell_latitud);
		ai.setCellLongitud(cell_longitud);
		ai.setCellSurface(cell_surface);
		ai.setCellVolumen(cell_volumen);
		ai.setCellWallInsulationThickness(cell_wall_insulation_thickness);
		ai.setMacrocellId(macrocell_id);

		m_cells.add(ai);
		return true;
	}

	public boolean AddGateway(
			String gateway_desc,
			String gateway_id,
			String gateway_macadr,
			String manufacturer_id,
			String middleware_plugin_id
			) throws Exception {
		ConfGateway ai = new ConfGateway();

		ai.setGatewayDesc(gateway_desc);
		ai.setGatewayId(gateway_id);
		ai.setGatewayMacadr(gateway_macadr);
		ai.setManufacturerId(manufacturer_id);
		ai.setMiddlewarePluginId(middleware_plugin_id);

		m_gateways.add(ai);
		return true;
	}

	public boolean AddMacroCell(
			String macrocell_desc,
			String macrocell_id,
			String region
			) throws Exception {
		ConfMacroCell ai = new ConfMacroCell();

		ai.setMacrocellDesc(macrocell_desc);
		ai.setMacrocellId(macrocell_id);
		ai.setRegion(region);

		m_macrocells.add(ai);
		return true;
	}

	public boolean AddManufacturer(
			String manufacturer_desc,
			String manufacturer_firmware,
			String manufacturer_id,
			String manufacturer_make,
			String manufacturer_model
			) throws Exception {
		ConfManufacturer ai = new ConfManufacturer();

		ai.setManufacturerDesc(manufacturer_desc);
		ai.setManufacturerFirmware(manufacturer_firmware);
		ai.setManufacturerId(manufacturer_id);
		ai.setManufacturerMake(manufacturer_make);
		ai.setManufacturerModel(manufacturer_model);

		m_manufacturers.add(ai);
		return true;
	}

	public boolean AddRoom(
			String cell_id,
			String room_desc,
			String room_id,
			String room_max_ocup,
			String room_surface,
			String room_volume
			) throws Exception {
		ConfRoom ai = new ConfRoom();

		ai.setCellId(cell_id);
		ai.setRoomDesc(room_desc);
		ai.setRoomId(room_id);
		ai.setRoomMaxOcup(room_max_ocup);
		ai.setRoomSurface(room_surface);
		ai.setRoomVolume(room_volume);

		m_rooms.add(ai);
		return true;
	}
	
	public boolean AddShadowDevice(
			String appliance_id,
			String device_desc,
			String device_id,
			String device_output,
			String manufacturer_id,
			String gateway_id,
			String middleware_plugin_id
			) throws Exception {
		ConfShadowDevice ai = new ConfShadowDevice();

		ai.setApplianceId(appliance_id);
		ai.setDeviceDesc(device_desc);
		ai.setDeviceId(device_id);
		ai.setDeviceOutput(device_output);
		ai.setDeviceType(device_output);
		ai.setGatewayId(gateway_id);
		ai.setManufacturerId(manufacturer_id);
		ai.setMiddlewarePluginId(middleware_plugin_id);

		m_shadowdevices.add(ai);
		return true;
	}
	


public String ToSql() throws Exception {
	StringBuilder sb = new StringBuilder();
        for (java.util.Iterator<ConfMacroCell> e = m_macrocells.iterator(); e.hasNext();) {
		ConfMacroCell ec = e.next();
		sb.append("INSERT INTO middleware.macrocell (macrocell_desc, macrocell_id, region) VALUES ('"+
				 ec.getMacrocellDesc()+
				 "', '"+
				 ec.getMacrocellId()+
				 "', '"+
				 ec.getRegion()+
		 "');\n");
	}
        for (java.util.Iterator<ConfCell> e = m_cells.iterator(); e.hasNext();) {
		 ConfCell ec = e.next();
		sb.append("INSERT INTO middleware.cell (cell_building_shade_coefficient, cell_desc, cell_id, cell_latitud, cell_longitud, cell_surface, cell_volumen, cell_wall_insulation_thickness, macrocell_id) VALUES ('"+
		 ec.getCellBuildingShadeCoefficient()+
		 "', '"+
		 ec.getCellDesc()+
		 "', '"+
		 ec.getCellId()+
		 "', '"+
		 ec.getCellLatitud()+
		 "', '"+
		 ec.getCellLongitud()+
		 "', '"+
		 ec.getCellSurface()+
		 "', '"+
		 ec.getCellVolumen()+
		 "', '"+
		 ec.getCellWallInsulationThickness()+
		 "', '"+
		 ec.getMacrocellId()+
		 "');\n");
	}
        for (java.util.Iterator<ConfRoom> e = m_rooms.iterator(); e.hasNext();) {
		ConfRoom ec = e.next();
		sb.append("INSERT INTO middleware.room (cell_id, room_desc, room_id, room_max_ocup, room_surface, room_volume) VALUES ('"+
				 ec.getCellId()+
				 "', '"+
				 ec.getRoomDesc()+
				 "', '"+
				 ec.getRoomId()+
				 "', '"+
				 ec.getRoomMaxOcup()+
				 "', '"+
				 ec.getRoomSurface()+
				 "', '"+
				 ec.getRoomVolume()+
		 "');\n");
	}
	for (java.util.Iterator<ConfAppliance> e = m_appliances.iterator(); e.hasNext();) {
		ConfAppliance ec = e.next();
		sb.append("INSERT INTO middleware.appliance (appliance_activacion_date, appliance_desc, appliance_id, appliance_investment, appliance_leaving_date, appliance_power, load_type_id, room_id, subcategory_id) VALUES ('"+
		 ec.getApplianceActivacionDate()+
		 "', '"+
		 ec.getApplianceDesc()+
		 "', '"+
		 ec.getApplianceId()+
		 "', '"+
		 ec.getApplianceInvestment()+
		 "', '"+
		 ec.getApplianceLeavingDate()+
		 "', '"+
		 ec.getAppliancePower()+
		 "', '"+
		 ec.getLoadTypeId()+
		 "', '"+
		 ec.getRoomId()+
		 "', '"+
		 ec.getSubcategoryId()+
		 "');\n");
	}
        for (java.util.Iterator<ConfShadowDevice> e = m_shadowdevices.iterator(); e.hasNext();) {
		ConfShadowDevice ec = e.next();
		sb.append("INSERT INTO middleware.device (appliance_id, device_desc, device_id, device_output, manufacturer_id, gateway_id) VALUES ('"+
				 ec.getApplianceId()+
				 "', '"+
				 ec.getDeviceDesc()+
				 "', '"+
				 ec.getDeviceId()+
				 "', '"+
				 ec.getDeviceOutput()+
				 "', '"+
				 ec.getManufacturerId()+
				 "', '"+
				 ec.getGatewayId()+
		 "');\n");
	}
	for (java.util.Iterator<ConfManufacturer> e = m_manufacturers.iterator(); e.hasNext();) {
		ConfManufacturer ec = e.next();
		sb.append("INSERT INTO middleware.manufacturer (manufacturer_desc, manufacturer_firmware, manufacturer_id, manufacturer_make, manufacturer_model) VALUES ('"+
				 ec.getManufacturerDesc()+
				 "', '"+
				 ec.getManufacturerFirmware()+
				 "', '"+
				 ec.getManufacturerId()+
				 "', '"+
				 ec.getManufacturerMake()+
				 "', '"+
				 ec.getManufacturerModel()+
		 "');\n");
	}
	for (java.util.Iterator<ConfGateway> e = m_gateways.iterator(); e.hasNext();) {
		ConfGateway ec = e.next();
		sb.append("INSERT INTO middleware.gateway (gateway_desc, gateway_id, gateway_macadr, manufacturer_id) VALUES ('"+
				 ec.getGatewayDesc()+
				 "', '"+
				 ec.getGatewayId()+
				 "', '"+
				 ec.getGatewayMacadr()+
				 "', '"+
				 ec.getManufacturerId()+
		 "');\n");
	}
	return sb.toString();
}


	public String Marshal() {
		StringBuilder sb = new StringBuilder();
		EncConfAppliance em1 = EncConfAppliance.CreateEncConfAppliance();
		for (java.util.Iterator<ConfAppliance> e = m_appliances.iterator(); e.hasNext();) {
			ConfAppliance ec = e.next();
			sb.append(em1.Marshal(ec));
		}
		EncConfCell em2 = EncConfCell.CreateEncConfCell();
		for (java.util.Iterator<ConfCell> e = m_cells.iterator(); e.hasNext();) {
			ConfCell ec = e.next();
			sb.append(em2.Marshal(ec));
		}
		EncConfGateway em3 = EncConfGateway.CreateEncConfGateway();
		for (java.util.Iterator<ConfGateway> e = m_gateways.iterator(); e.hasNext();) {
			ConfGateway ec = e.next();
			sb.append(em3.Marshal(ec));
		}
		EncConfMacroCell em4 = EncConfMacroCell.CreateEncConfMacroCell();
		for (java.util.Iterator<ConfMacroCell> e = m_macrocells.iterator(); e.hasNext();) {
			ConfMacroCell ec = e.next();
			sb.append(em4.Marshal(ec));
		}
		EncConfManufacturer em5 = EncConfManufacturer.CreateEncConfManufacturer();
		for (java.util.Iterator<ConfManufacturer> e = m_manufacturers.iterator(); e.hasNext();) {
			ConfManufacturer ec = e.next();
			sb.append(em5.Marshal(ec));
		}
		EncConfRoom em6 = EncConfRoom.CreateEncConfRoom();
		for (java.util.Iterator<ConfRoom> e = m_rooms.iterator(); e.hasNext();) {
			ConfRoom ec = e.next();
			sb.append(em6.Marshal(ec));
		}
		EncConfShadowDevice em7 = EncConfShadowDevice.CreateEncConfShadowDevice();
		for (java.util.Iterator<ConfShadowDevice> e = m_shadowdevices.iterator(); e.hasNext();) {
			ConfShadowDevice ec = e.next();
			sb.append(em7.Marshal(ec));
		}
		return sb.toString();
	}

	public static String InferSemantics(String xmldata) throws Exception {
		String semantics = null;
		String pattern = "<(\\w+)";
		java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
		java.util.regex.Matcher m = r.matcher(xmldata);
		if (m.find()) {
			semantics = m.group(0).substring(1);
		}
		return semantics;
	}

    public List<ConfAppliance> getM_appliances()
    {
        return m_appliances;
    }

    public List<ConfCell> getM_cells()
    {
        return m_cells;
    }

    public List<ConfGateway> getM_gateways()
    {
        return m_gateways;
    }

    public List<ConfMacroCell> getM_macrocells()
    {
        return m_macrocells;
    }

    public List<ConfManufacturer> getM_manufacturers()
    {
        return m_manufacturers;
    }

    public List<ConfRoom> getM_rooms()
    {
        return m_rooms;
    }

    public List<ConfShadowDevice> getM_shadowdevices()
    {
        return m_shadowdevices;
    }
        
        

	public static void main(String[] args) throws Exception {
		PhysicalConfManager mr = new PhysicalConfManager("");

		mr.AddManufacturer("my students doing footballs in the underground office", "really??", "CISTER", "mialb", "sturdy");
		mr.AddGateway("data gateway prison room", "42", "11:22:33", "CISTER", "41");
		mr.AddMacroCell("Porto", "PT1", "Portugal");
		mr.AddCell("21", "CISTER premises", "CISTER", "11", "22", "10", "20", "0", "PT1");
		mr.AddRoom("CISTER", "office for the students", "PRISON", "40", "10", "20");
		mr.AddAppliance("today", "heating system", "HEAT1.1", "1 dollar", "yesterday", "0.1W", "heating", "PRISON", "do not know");
		mr.AddShadowDevice("HEAT1.1", "fake heating sensor", "temp1.1.1", "data", "CISTER", "42", "41");
		
		String xmldata = mr.Marshal();
		System.out.println("XML data:\n"+xmldata);
		mr.ResetPhysicalConfManager();
		PhysicalConfManager mr2 = PhysicalConfManager.Unmarshal(xmldata, mr);
		System.out.println("XML data2:\n"+mr2.Marshal());

		String sql1 = mr.ToSql();
		System.out.println(sql1);

				
	}
}

