/**
Room.java by César Teixeira and Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package cister.rabbitmq.library;

import com.rabbitmq.client.QueueingConsumer;
import flexjson.JSON;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@XmlRootElement(name = "VirtualRoom")
public class Room
{
    private static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
    /*
     * Constants
     */

    /*
     * Instance variables
     */
    private String roomId;
    private String cellId;
    private String macroCellId;
    private String roomSurface;
    private String roomVolume;
    private String roomMaxOccupation;
    private String roomDescription;
    private ArrayList<Appliance> auxAppliancesList;
    private ConcurrentHashMap<String, Appliance> appliancesList;
    private RabbitConfiguration rabbitConfiguration;
    private SimpleGUI simpleGUI;
    private boolean debugGraphicalUI;

    /*
     * Constructors
     */
    /**
     *
     */
    public Room()
    {
        this.roomId = "";
        this.cellId = "";
        this.roomSurface = "";
        this.roomVolume = "";
        this.roomMaxOccupation = "";
        this.roomDescription = "";
        this.auxAppliancesList = new ArrayList<>();
        this.appliancesList = new ConcurrentHashMap<>();
        this.rabbitConfiguration = new RabbitConfiguration();
    }

    /**
     *
     * @param roomId
     * @param cellId
     * @param roomSurface
     * @param roomVolume
     * @param roomMaxOccupation
     * @param roomDescription
     */
    public Room(String roomId, String cellId, String roomSurface, String roomVolume, String roomMaxOccupation, String roomDescription)
    {
	this.roomId = roomId;
        this.cellId = cellId;
        this.roomSurface = roomSurface;
        this.roomVolume = roomVolume;
        this.roomMaxOccupation = roomMaxOccupation;
        this.roomDescription = roomDescription;
        this.auxAppliancesList = new ArrayList<>();
        this.appliancesList = new ConcurrentHashMap<>();
        this.rabbitConfiguration = new RabbitConfiguration();
    }

    /*
     * Public methods
     */
    /**
     *
     * @return
     */
    @XmlAttribute(name = "roomId")
    public String getRoomId()
    {
	return roomId;
    }

    /**
     *
     * @param roomId
     */
    public void setRoomId(String roomId)
    {
	this.roomId = roomId;
    }

    /**
     *
     * @return
     */
    public String getCellId()
    {
	return cellId;
    }

    /**
     *
     * @param cellId
     */
    public void setCellId(String cellId)
    {
	this.cellId = cellId;
    }
    
    /**
     *
     * @return
     */
    public String getMacroCellId()
    {
	return macroCellId;
    }

    /**
     *
     * @param macroCellId
     */
    public void setMacroCellId(String macroCellId)
    {
	this.macroCellId = macroCellId;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "roomSurface")
    public String getRoomSurface()
    {
	return roomSurface;
    }

    /**
     *
     * @param roomSurface
     */
    public void setRoomSurface(String roomSurface)
    {
	this.roomSurface = roomSurface;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "roomVolume")
    public String getRoomVolume()
    {
	return roomVolume;
    }

    /**
     *
     * @param roomVolume
     */
    public void setRoomVolume(String roomVolume)
    {
	this.roomVolume = roomVolume;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "roomMaxOccupation")
    public String getRoomMaxOccupation()
    {
	return roomMaxOccupation;
    }

    /**
     *
     * @param roomMaxOccupation
     */
    public void setRoomMaxOccupation(String roomMaxOccupation)
    {
	this.roomMaxOccupation = roomMaxOccupation;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "roomDescription")
    public String getRoomDescription()
    {
	return roomDescription;
    }

    /**
     *
     * @param roomDescription
     */
    public void setRoomDescription(String roomDescription)
    {
	this.roomDescription = roomDescription;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "VirtualAppliance")
    public ArrayList<Appliance> getAuxAppliancesList()
    {
        return auxAppliancesList;
    }

    /**
     *
     * @param auxAppliancesList
     */
    public void setAuxAppliancesList(ArrayList<Appliance> auxAppliancesList)
    {
        this.auxAppliancesList = auxAppliancesList;
    }

    /**
     *
     * @return
     */
    @JSON(include=true)
    public ConcurrentHashMap<String, Appliance> getAppliancesList()
    {
        return appliancesList;
    }

    /**
     *
     * @param appliancesList
     */
    public void setAppliancesList(ConcurrentHashMap<String, Appliance> appliancesList)
    {
        this.appliancesList = appliancesList;
    }

    public boolean isDebugGraphicalUI()
    {
        return debugGraphicalUI;
    }

    public void setDebugGraphicalUI(boolean debugGraphicalUI)
    {
        this.debugGraphicalUI = debugGraphicalUI;
    }
    
    public void addAppliance(Appliance appliance)
    {
        this.appliancesList.put(appliance.getApplianceId(), appliance);
    }

    /*
     * Private methods
     */

    private void logReceived(String logMessage)
    {
        if (this.debugGraphicalUI && this.simpleGUI != null)
        {
            this.simpleGUI.addTextSubscribe(logMessage);
        }
        
        logger.debug(logMessage);
    }
    
    private void logPublished(String logMessage)
    {
        if (this.debugGraphicalUI && this.simpleGUI != null)
        {
            this.simpleGUI.addTextPublish(logMessage);
        }
        
        logger.debug(logMessage);
    }
}
