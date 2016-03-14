/**
ShadowDevice.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package cister.rabbitmq.library;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import flexjson.JSON;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.logging.log4j.LogManager;

import encourager.MeterReadingsManager;

@XmlRootElement(name = "ShadowDevice")
public class ShadowDevice
{
    /*
     * Constants
     */

    /*
     * Instance variables
     */
    // architecture variables
    private String macroCellId;
    private String cellId;
    private String gatewayId;
    private String roomId;
    private String applianceId;
    private String deviceId;
    private String middlewarePluginId;
    private boolean isActuator;
    private boolean isSensor;

    // instance variables
    private String deviceType;
    private String manufacturerId;
    private String deviceDescription;
    private String deviceOutput;
    private MeterReadingsManager meterReadingsManager;
    private ConcurrentHashMap<String, String> messageTracking;
// ALBANO removed    private DatabaseHandler databaseHandler;
    private SimpleGUI simpleGUI;
    private boolean debugGraphicalUI;

    // rabbit variables
    private String routingKey;

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());


    /*
     * Constructors
     */
    /**
     *
     */
    public ShadowDevice()
    {
        super();
        this.macroCellId = "";
        this.cellId = "";
        this.gatewayId = "";
        this.roomId = "";
        this.applianceId = "";
        this.deviceId = "";
        this.middlewarePluginId = "";
        this.deviceType = "";
        this.manufacturerId = "";
        this.deviceDescription = "";
        this.routingKey = "";
        this.isActuator = false;
        this.isSensor = false;
        this.messageTracking = new ConcurrentHashMap<>();

        this.simpleGUI = null;
    }

    /**
     *
     * @param macroCellId
     * @param cellId
     * @param gatewayId
     * @param roomId
     * @param applianceId
     * @param deviceId
     * @param middlewarePluginId
     * @param deviceType
     * @param manufacturerId
     * @param deviceDescription
     * @param routingKey
     */
    public ShadowDevice(String macroCellId, String cellId, String gatewayId, String roomId, String applianceId, String deviceId, String middlewarePluginId, String deviceType, String manufacturerId,
                        String deviceDescription, String routingKey)
    {
        super();
        this.macroCellId = macroCellId;
        this.cellId = cellId;
        this.gatewayId = gatewayId;
        this.roomId = roomId;
        this.applianceId = applianceId;
        this.deviceId = deviceId;
        this.middlewarePluginId = middlewarePluginId;
        this.deviceType = deviceType;
        this.manufacturerId = manufacturerId;
        this.deviceDescription = deviceDescription;
        this.routingKey = routingKey;
        this.isActuator = false;
        this.isSensor = false;
        this.messageTracking = new ConcurrentHashMap<>();

        this.simpleGUI = null;
    }

    /*
     * Public methods
     */
    public boolean isIsActuator()
    {
        return isActuator;
    }

    public void setIsActuator(boolean isActuator)
    {
        this.isActuator = isActuator;
    }

    public boolean isIsSensor()
    {
        return isSensor;
    }

    public void setIsSensor(boolean isSensor)
    {
        this.isSensor = isSensor;
    }

    @JSON(include = false)
    public MeterReadingsManager getMeterReadingsManager()
    {
        return meterReadingsManager;
    }

    public void setMeterReadingsManager(MeterReadingsManager meterReadingsManager)
    {
        this.meterReadingsManager = meterReadingsManager;
    }

    @JSON(include = true)
    @XmlTransient
    public ConcurrentHashMap<String, String> getMessageTracking()
    {
        return messageTracking;
    }

    public void setMessageTracking(ConcurrentHashMap<String, String> messageTracking)
    {
        this.messageTracking = messageTracking;
    }

	/* ALBANO removed
    @JSON(include = false)
    @XmlTransient
    public DatabaseHandler getDatabaseHandler()
    {
        return databaseHandler;
    }

    public void setDatabaseHandler(DatabaseHandler databaseHandler)
    {
        this.databaseHandler = databaseHandler;
    }
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
    @XmlAttribute(name = "gatewayId")
    public String getGatewayId()
    {
        return gatewayId;
    }

    /**
     *
     * @param gatewayId
     */
    public void setGatewayId(String gatewayId)
    {
        this.gatewayId = gatewayId;
    }

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
    @XmlAttribute(name = "applianceId")
    public String getApplianceId()
    {
        return applianceId;
    }

    /**
     *
     * @param applianceId
     */
    public void setApplianceId(String applianceId)
    {
        this.applianceId = applianceId;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "deviceId")
    public String getDeviceId()
    {
        return deviceId;
    }

    /**
     *
     * @param deviceId
     */
    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "middlewarePluginId")
    public String getMiddlewarePluginId()
    {
        return middlewarePluginId;
    }

    /**
     *
     * @param middlewarePluginId
     */
    public void setMiddlewarePluginId(String middlewarePluginId)
    {
        this.middlewarePluginId = middlewarePluginId;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "deviceType")
    public String getDeviceType()
    {
        return deviceType;
    }

    /**
     *
     * @param deviceType
     */
    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "manufacturerId")
    public String getManufacturerId()
    {
        return manufacturerId;
    }

    /**
     *
     * @param manufacturerId
     */
    public void setManufacturerId(String manufacturerId)
    {
        this.manufacturerId = manufacturerId;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "deviceDescription")
    public String getDeviceDescription()
    {
        return deviceDescription;
    }

    /**
     *
     * @param deviceDescription
     */
    public void setDeviceDescription(String deviceDescription)
    {
        this.deviceDescription = deviceDescription;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "routingKey")
    public String getRoutingKey()
    {
        return routingKey;
    }

    /**
     *
     * @param routingKey
     */
    public void setRoutingKey(String routingKey)
    {
        this.routingKey = routingKey;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "deviceOutput")
    public String getDeviceOutput()
    {
        return deviceOutput;
    }

    /**
     *
     * @param deviceOutput
     */
    public void setDeviceOutput(String deviceOutput)
    {
        this.deviceOutput = deviceOutput;
    }
    
    /**
     *
     * @return
     */
    @JSON(include = false)
    public SimpleGUI getSimpleGUI()
    {
        return simpleGUI;
    }

    public boolean isSensor()
    {
        if (this.isSensor == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isActuator()
    {
        if (this.isActuator == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isDebugGraphicalUI()
    {
        return debugGraphicalUI;
    }

    public void setDebugGraphicalUI(boolean debugGraphicalUI)
    {
        this.debugGraphicalUI = debugGraphicalUI;
    }

    /**
     *
     */
    private void classifyDevice()
    {
        switch (this.getDeviceType())
        {
            case "1": // is a sensor
                this.isSensor = true;
                this.isActuator = false;
                break;

            case "2": // is an actuator
                this.isActuator = true;
                this.isSensor = false;
                break;

            case "3": // is a sensor and an actuator
                this.isActuator = true;
                this.isSensor = true;
                break;

            default: // sensor by default
                this.isSensor = true;
                this.isActuator = false;
                break;
        }
    }

    private void logReceived(String logMessage)
    {
        if (this.debugGraphicalUI && this.simpleGUI != null)
        {
            this.simpleGUI.addTextSubscribe(logMessage);
        }

        logger.info(logMessage);
    }

    private void logPublished(String logMessage)
    {
        if (this.debugGraphicalUI && this.simpleGUI != null)
        {
            this.simpleGUI.addTextPublish(logMessage);
        }

        logger.info(logMessage);
    }

    /*
     * Private methods
     */
}
