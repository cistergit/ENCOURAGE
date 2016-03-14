/**
RabbitConfiguration.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package cister.rabbitmq.library;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import encourager.RabbitUtils;

@XmlRootElement(name = "RabbitConfiguration")
public class RabbitConfiguration
{
    /*
     *  Constants
     */
    private final String VIRTUAL_DEVICES_MODULE_CONFIGURATION_FILE = "configs/virtual_devices_module.properties";
    
    /*
     *  Instance variables
     */
    private String rabbitHost;
    private String rabbitUser;
    private String rabbitPassword;
    private String rabbitVHost;
    
    /*
     *  Constructors
     */
    /**
     *
     */
    public RabbitConfiguration()
    {
	super();
        
        Properties properties = RabbitUtils.loadPropertiesFile(VIRTUAL_DEVICES_MODULE_CONFIGURATION_FILE);
        
	this.rabbitUser = properties.getProperty("RabbitUser");
	this.rabbitPassword = properties.getProperty("RabbitPassword");
	this.rabbitHost = properties.getProperty("RabbitHost");
	this.rabbitVHost = properties.getProperty("RabbitVHost");
    }
    /**
     *
     * @param rabbitUser
     * @param rabbitPassword
     * @param rabbitHost
     * @param rabbitVHost
     */
    public RabbitConfiguration(String rabbitUser, String rabbitPassword, String rabbitHost, String rabbitVHost)
    {
	super();
	this.rabbitHost = rabbitHost;
	this.rabbitUser = rabbitUser;
	this.rabbitPassword = rabbitPassword;
	this.rabbitVHost = rabbitVHost;
    }

    /*
     *  Public methods
     */
    /**
     *
     * @return
     */
    @XmlElement(name = "RabbitHost")
    public String getRabbitHost()
    {
        return rabbitHost;
    }
    
    /**
     *
     * @param rabbitHost
     */
    public void setRabbitHost(String rabbitHost)
    {
        this.rabbitHost = rabbitHost;
    }
    
    /**
     *
     * @return
     */
    @XmlElement(name = "RabbitUser")
    public String getRabbitUser()
    {
        return rabbitUser;
    }
    
    /**
     *
     * @param rabbitUser
     */
    public void setRabbitUser(String rabbitUser)
    {
        this.rabbitUser = rabbitUser;
    }
    
    /**
     *
     * @return
     */
    @XmlElement(name = "RabbitPassword")
    public String getRabbitPassword()
    {
        return rabbitPassword;
    }
    
    /**
     *
     * @param rabbitPassword
     */
    public void setRabbitPassword(String rabbitPassword)
    {
        this.rabbitPassword = rabbitPassword;
    }
    
    /**
     *
     * @return
     */
    @XmlElement(name = "RabbitVHost")
    public String getRabbitVHost()
    {
        return rabbitVHost;
    }
    
    /**
     *
     * @param rabbitVHost
     */
    public void setRabbitVHost(String rabbitVHost)
    {
        this.rabbitVHost = rabbitVHost;
    }

    /**
     *
     * @param filePath
     * @return
     */
    public boolean storePropertiesFile(String filePath)
    {
	Properties properties = new Properties();

	// RabbitMQ Server connection configurations
	properties.setProperty("RabbitHost", this.rabbitHost);
	properties.setProperty("RabbitUser", this.rabbitUser);
	properties.setProperty("RabbitPassword", this.rabbitPassword);
	properties.setProperty("RabbitVHost", this.rabbitVHost);

	try
	{
	    properties.store(new FileOutputStream(filePath), null);
	}
	catch (FileNotFoundException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	return true;
    }

    /*
     *  Private methods
     */
}
