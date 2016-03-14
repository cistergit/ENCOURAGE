/**
MacroCell.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
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

@XmlRootElement(name = "VirtualMacroCell")
public class MacroCell
{
    private static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
    /*
     * Constants
     */

    /*
     * Instance variables
     */

    private String macroCellId;
    private String macroCellDescription;
    private String macroCellRegion;
    private ArrayList<Cell> auxCellsList;
    private ConcurrentHashMap<String, Cell> cellsList;
    private RabbitConfiguration rabbitConfiguration;
    private ConcurrentHashMap<String, String> messageTracking;
    private SimpleGUI simpleGUI;
    private boolean debugGraphicalUI;
    private MeterReadingsManager meterReadingsManager;

    /*
     * Constructors
     */
    /**
     *
     */
    public MacroCell()
    {
	super();
	this.macroCellId = "";
	this.macroCellDescription = "";
	this.macroCellRegion = "";
	this.auxCellsList = new ArrayList<>();
	this.cellsList = new ConcurrentHashMap<>();
        this.messageTracking = new ConcurrentHashMap<>();
	this.rabbitConfiguration = new RabbitConfiguration();
        this.simpleGUI = null;
    }

    /**
     *
     * @param macroCellId
     * @param macroCellDescription
     * @param macroCellRegion
     * @param auxCellsList
     */
    public MacroCell(String macroCellId, String macroCellDescription, String macroCellRegion, ArrayList<Cell> auxCellsList)
    {
	super();
	this.macroCellId = macroCellId;
	this.macroCellDescription = macroCellDescription;
	this.macroCellRegion = macroCellRegion;
	this.auxCellsList = auxCellsList;
	this.cellsList = new ConcurrentHashMap<>();
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
    @XmlAttribute(name = "macroCellDescription")
    public String getMacroCellDescription()
    {
	return macroCellDescription;
    }

    /**
     *
     * @param macroCellDescription
     */
    public void setMacroCellDescription(String macroCellDescription)
    {
	this.macroCellDescription = macroCellDescription;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "macroCellRegion")
    public String getMacroCellRegion()
    {
	return macroCellRegion;
    }

    /**
     *
     * @param macroCellRegion
     */
    public void setMacroCellRegion(String macroCellRegion)
    {
	this.macroCellRegion = macroCellRegion;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "VirtualCell")
    public ArrayList<Cell> getAuxCellsList()
    {
	return auxCellsList;
    }

    /**
     *
     * @param auxCellsList
     */
    public void setAuxCellsList(ArrayList<Cell> auxCellsList)
    {
	this.auxCellsList = auxCellsList;
    }

    /**
     *
     * @return
     */
    @JSON(include=true)
    public ConcurrentHashMap<String, Cell> getCellsList()
    {
	return cellsList;
    }

    /**
     *
     * @param cellsList
     */
    public void setCellsList(ConcurrentHashMap<String, Cell> cellsList)
    {
	this.cellsList = cellsList;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "RabbitConfiguration")
    public RabbitConfiguration getRabbitConfiguration()
    {
	return rabbitConfiguration;
    }

    /**
     *
     * @param rabbitConfiguration
     */
    public void setRabbitConfiguration(RabbitConfiguration rabbitConfiguration)
    {
	this.rabbitConfiguration = rabbitConfiguration;
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
    
    public void addCell(Cell cell)
    {
        this.cellsList.put(cell.getCellId(), cell);
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