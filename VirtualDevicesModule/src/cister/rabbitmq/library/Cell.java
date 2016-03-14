/**
Cell.java by César Teixeira and Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package cister.rabbitmq.library;

import flexjson.JSON;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import encourager.MeterReadingsManager;

@XmlRootElement(name = "VirtualCell")
public class Cell
{
    private static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
    /*
     * Constants
     */

    /*
     * Instance variables
     */
    private String macroCellId;
    private String cellId;
    private String cellLatitude;
    private String cellLongitude;
    private String cellSurface;
    private String cellVolumen;
    private String cellBuildingShadeCoefficient;
    private String cellWallInsulationThickness;
    private String cellDescription;
    private ArrayList<Room> auxRoomsList;
    private ConcurrentHashMap<String, Room> roomsList;
    private ArrayList<Gateway> auxHANGatewaysList;
    private ConcurrentHashMap<String, Gateway> HANGatewaysList;
    private ConcurrentHashMap<String, String> messageTracking;
    private RabbitConfiguration rabbitConfiguration;
    private SimpleGUI simpleGUI;
    private boolean debugGraphicalUI;
    private MeterReadingsManager meterReadingsManager;

    /*
     * Constructors
     */
    /**
     *
     */
    public Cell()
    {
	super();
	this.macroCellId = "";
	this.cellId = "";
	this.cellLatitude = "";
	this.cellLongitude = "";
	this.cellSurface = "";
	this.cellVolumen = "";
	this.cellBuildingShadeCoefficient = "";
	this.cellWallInsulationThickness = "";
	this.cellDescription = "";
	this.auxRoomsList = new ArrayList<>();
	this.roomsList = new ConcurrentHashMap<>();
	this.auxHANGatewaysList = new ArrayList<>();
	this.HANGatewaysList = new ConcurrentHashMap<>();
        this.messageTracking = new ConcurrentHashMap<>();
        this.rabbitConfiguration = new RabbitConfiguration();
        this.simpleGUI = null;
    }
    
    /**
     *
     * @param macroCellId
     * @param cellId
     * @param cellLatitude
     * @param cellLongitude
     * @param cellSurface
     * @param cellVolumen
     * @param cellBuildingShadeCoefficient
     * @param cellWallInsulationThickness
     * @param cellDescription
     * @param auxRoomsList
     * @param auxHANGatewaysList
     */
    public Cell(String macroCellId, String cellId, String cellLatitude, String cellLongitude, String cellSurface, String cellVolumen, String cellBuildingShadeCoefficient,
	    String cellWallInsulationThickness, String cellDescription, ArrayList<Room> auxRoomsList, ArrayList<Gateway> auxHANGatewaysList)
    {
	super();
	this.macroCellId = macroCellId;
	this.cellId = cellId;
	this.cellLatitude = cellLatitude;
	this.cellLongitude = cellLongitude;
	this.cellSurface = cellSurface;
	this.cellVolumen = cellVolumen;
	this.cellBuildingShadeCoefficient = cellBuildingShadeCoefficient;
	this.cellWallInsulationThickness = cellWallInsulationThickness;
	this.cellDescription = cellDescription;
	this.auxRoomsList = auxRoomsList;
	this.roomsList = new ConcurrentHashMap<>();
	this.auxHANGatewaysList = new ArrayList<>();
	this.HANGatewaysList = new ConcurrentHashMap<>();
        this.messageTracking = new ConcurrentHashMap<>();
        this.rabbitConfiguration = new RabbitConfiguration();
        this.simpleGUI = null;
    }

    /*
     * Public methods
     */
    /**
     *
     * @return
     */
    @XmlAttribute(name = "macroCellId")
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
    @XmlAttribute(name = "cellId")
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
    @XmlAttribute(name = "cellLatitude")
    public String getCellLatitude()
    {
        return cellLatitude;
    }

    /**
     *
     * @param cellLatitude
     */
    public void setCellLatitude(String cellLatitude)
    {
        this.cellLatitude = cellLatitude;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "cellLongitude")
    public String getCellLongitude()
    {
        return cellLongitude;
    }

    /**
     *
     * @param cellLongitude
     */
    public void setCellLongitude(String cellLongitude)
    {
        this.cellLongitude = cellLongitude;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "cellSurface")
    public String getCellSurface()
    {
        return cellSurface;
    }

    /**
     *
     * @param cellSurface
     */
    public void setCellSurface(String cellSurface)
    {
        this.cellSurface = cellSurface;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "cellVolumen")
    public String getCellVolumen()
    {
        return cellVolumen;
    }

    /**
     *
     * @param cellVolumen
     */
    public void setCellVolumen(String cellVolumen)
    {
        this.cellVolumen = cellVolumen;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "cellBuildingShadeCoefficient")
    public String getCellBuildingShadeCoefficient()
    {
        return cellBuildingShadeCoefficient;
    }

    /**
     *
     * @param cellBuildingShadeCoefficient
     */
    public void setCellBuildingShadeCoefficient(String cellBuildingShadeCoefficient)
    {
        this.cellBuildingShadeCoefficient = cellBuildingShadeCoefficient;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "cellWallInsulationThickness")
    public String getCellWallInsulationThickness()
    {
        return cellWallInsulationThickness;
    }

    /**
     *
     * @param cellWallInsulationThickness
     */
    public void setCellWallInsulationThickness(String cellWallInsulationThickness)
    {
        this.cellWallInsulationThickness = cellWallInsulationThickness;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "cellDescription")
    public String getCellDescription()
    {
        return cellDescription;
    }

    /**
     *
     * @param cellDescription
     */
    public void setCellDescription(String cellDescription)
    {
        this.cellDescription = cellDescription;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "VirtualRoom")
    public ArrayList<Room> getAuxRoomsList()
    {
        return auxRoomsList;
    }

    /**
     *
     * @param auxRoomsList
     */
    public void setAuxRoomsList(ArrayList<Room> auxRoomsList)
    {
        this.auxRoomsList = auxRoomsList;
    }

    /**
     *
     * @return
     */
    @JSON(include=true)
    public ConcurrentHashMap<String, Room> getRoomsList()
    {
        return roomsList;
    }

    /**
     *
     * @param roomsList
     */
    public void setRoomsList(ConcurrentHashMap<String, Room> roomsList)
    {
        this.roomsList = roomsList;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "VirtualHANGateway")
    public ArrayList<Gateway> getAuxHANGatewaysList()
    {
        return auxHANGatewaysList;
    }

    /**
     *
     * @param auxHANGatewaysList
     */
    public void setAuxHANGatewaysList(ArrayList<Gateway> auxHANGatewaysList)
    {
        this.auxHANGatewaysList = auxHANGatewaysList;
    }

    /**
     *
     * @return
     */
    public ConcurrentHashMap<String, Gateway> getHANGatewaysList()
    {
        return HANGatewaysList;
    }

    /**
     *
     * @param hANGatewaysList
     */
    public void setHANGatewaysList(ConcurrentHashMap<String, Gateway> hANGatewaysList)
    {
        HANGatewaysList = hANGatewaysList;
    }
    
    public void addRoom(Room room)
    {
        this.roomsList.put(room.getRoomId(), room);
    }
    
    /**
     *
     * @return
     */
    @JSON(include=false)
    public SimpleGUI getSimpleGUI()
    {
        return simpleGUI;
    }

    public boolean isDebugGraphicalUI()
    {
        return debugGraphicalUI;
    }

    public void setDebugGraphicalUI(boolean debugGraphicalUI)
    {
        this.debugGraphicalUI = debugGraphicalUI;
    }
    
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
    
    /*
     * Private methods
     */
}
