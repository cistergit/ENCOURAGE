/**
Appliance.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
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

@XmlRootElement(name = "VirtualAppliance")
public class Appliance
{
    private static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
    /*
     * Constants
     */

    /*
     * Instance variables
     */
    private String applianceId;
    private String roomId;
    private String cellId;
    private String macroCellId;
    private String subcategoryId;
    private String appliancePower;
    private String applianceDescription;
    private String applianceInvestment;
    private String applianceActivationDate;
    private String applianceLeavingDate;
    private ArrayList<ShadowDevice> auxDevicesList;
    private ConcurrentHashMap<String, ShadowDevice> devicesList;
    private RabbitConfiguration rabbitConfiguration;
    private SimpleGUI simpleGUI;
    private boolean debugGraphicalUI;

    /*
     * Constructors
     */
    /**
     *
     */
    public Appliance()
    {
        this.applianceId = "";
        this.roomId = "";
        this.subcategoryId = "";
        this.appliancePower = "";
        this.applianceDescription = "";
        this.applianceInvestment = "";
        this.applianceActivationDate = "";
        this.applianceLeavingDate = "";
        this.auxDevicesList = new ArrayList<>();
        this.devicesList = new ConcurrentHashMap<>();
    }

    /**
     *
     * @param applianceId
     * @param roomId
     * @param subcategoryId
     * @param appliancePower
     * @param applianceDescription
     * @param applianceInvestment
     * @param applianceActivationDate
     * @param applianceLeavingDate
     */
    public Appliance(String applianceId, String roomId, String subcategoryId, String appliancePower, String applianceDescription, String applianceInvestment, String applianceActivationDate,
            String applianceLeavingDate)
    {
        this.applianceId = applianceId;
        this.roomId = "";
        this.subcategoryId = subcategoryId;
        this.appliancePower = appliancePower;
        this.applianceDescription = applianceDescription;
        this.applianceInvestment = applianceInvestment;
        this.applianceActivationDate = applianceActivationDate;
        this.applianceLeavingDate = applianceLeavingDate;
        this.auxDevicesList = new ArrayList<>();
        this.devicesList = new ConcurrentHashMap<>();
        this.rabbitConfiguration = new RabbitConfiguration();
    }

    /*
     * Public methods
     */
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
    @XmlAttribute(name = "subcategoryId")
    public String getSubcategoryId()
    {
        return subcategoryId;
    }

    /**
     *
     * @param subcategoryId
     */
    public void setSubcategoryId(String subcategoryId)
    {
        this.subcategoryId = subcategoryId;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "appliancePower")
    public String getAppliancePower()
    {
        return appliancePower;
    }

    /**
     *
     * @param appliancePower
     */
    public void setAppliancePower(String appliancePower)
    {
        this.appliancePower = appliancePower;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "applianceDescription")
    public String getApplianceDescription()
    {
        return applianceDescription;
    }

    /**
     *
     * @param applianceDescription
     */
    public void setApplianceDescription(String applianceDescription)
    {
        this.applianceDescription = applianceDescription;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "applianceInvestment")
    public String getApplianceInvestment()
    {
        return applianceInvestment;
    }

    /**
     *
     * @param applianceInvestment
     */
    public void setApplianceInvestment(String applianceInvestment)
    {
        this.applianceInvestment = applianceInvestment;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "applianceActivationDate")
    public String getApplianceActivationDate()
    {
        return applianceActivationDate;
    }

    /**
     *
     * @param applianceActivationDate
     */
    public void setApplianceActivationDate(String applianceActivationDate)
    {
        this.applianceActivationDate = applianceActivationDate;
    }

    /**
     *
     * @return
     */
    @XmlAttribute(name = "applianceLeavingDate")
    public String getApplianceLeavingDate()
    {
        return applianceLeavingDate;
    }

    /**
     *
     * @param applianceLeavingDate
     */
    public void setApplianceLeavingDate(String applianceLeavingDate)
    {
        this.applianceLeavingDate = applianceLeavingDate;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "ShadowDevice")
    public ArrayList<ShadowDevice> getAuxDevicesList()
    {
        return auxDevicesList;
    }

    /**
     *
     * @param auxDevicesList
     */
    public void setAuxDevicesList(ArrayList<ShadowDevice> auxDevicesList)
    {
        this.auxDevicesList = auxDevicesList;
    }

    /**
     *
     * @return
     */
    @JSON(include=true)
    public ConcurrentHashMap<String, ShadowDevice> getDevicesList()
    {
        return devicesList;
    }

    /**
     *
     * @param devicesList
     */
    public void setDevicesList(ConcurrentHashMap<String, ShadowDevice> devicesList)
    {
        this.devicesList = devicesList;
    }

    /**
     *
     * @return
     */
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
     * @param delivery
     */
    public void handleApplianceMessage(QueueingConsumer.Delivery delivery)
    {
        logReceived("!!!! MESSAGE RECEIVED BY APPLIANCE !!!!");
    }

    public void addShadowDevice(ShadowDevice shadowDevice)
    {
        this.devicesList.put(shadowDevice.getDeviceId(), shadowDevice);
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
}
