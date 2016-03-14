/**
RabbitUtils.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package encourager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RabbitUtils
{
    /*
     * Constants
     */
    private static final String RABBIT_HOST = "localhost";
    // private static final String RABBIT_HOST = "encourage.es.atos.net";
    private static final String RABBIT_USER = "guest";
    private static final String RABBIT_PASSWORD = "guest";
    private static final String RABBIT_VHOST = "/";
    
    private static final String EXCHANGE_VD = "ExchangeVD";
    private static final String EXCHANGE_SC = "ExchangeSC";
    private static final String EXCHANGE_FC = "ExchangeFC";
    private static final String EXCHANGE_EMBI = "ExchangeEMBI";
    private static final String EXCHANGE_VD_TYPE = "topic";
    private static final String EXCHANGE_SC_TYPE = "topic";
    private static final String EXCHANGE_FC_TYPE = "topic";
    private static final String EXCHANGE_EMBI_TYPE = "topic";

    private static final String EXCHANGE_NAME = "";
    private static final String EXCHANGE_TYPE= "";

    private static final String ROUTING_KEY = "";
    
    private static final String COMPONENT_ID = "";
    
    private static final String CONFIGURATION_FILENAME = "middleware_config.properties";

    /*
     * Instance variables
     */

    /*
     * Constructors
     */
    /**
     *
     */
    public RabbitUtils()
    {

    }

    /*
     * Public methods
     */
    /**
     *
     * @return
     */
    public Properties createDefaultPropertiesFile()
    {
	return this.createDefaultProperties();
    }

    /**
     *
     * @param configurationFileName
     * @return
     */
    public static Properties loadPropertiesFile(String configurationFileName)
    {
	String configurationFile = "";

	// Load properties from passed configuration file
	if (configurationFileName != null)
	{
	    configurationFile = configurationFileName;
	}
	else
	{
	    // No configuration file was passed
	    configurationFile = CONFIGURATION_FILENAME;
	}

	return loadProperties(configurationFile);
    }

    /**
     *
     * @param object
     * @param filePath
     * @return
     */
    public boolean storePropertiesFile(Object object, String filePath)
    {
	boolean status = false;
	
	if (object instanceof NodeList)
	{
	    Node n = ((NodeList) object).item(0);
	 // Store properties
	    status = this.storePropertiesFileFromVDConfigurations(n, filePath);
	}
	else if (object instanceof Properties)
	{
	 // Store properties
	    status = this.storeProperties((Properties)object, CONFIGURATION_FILENAME);	    
	}
	
	return status;
    }

    /**
     *
     * @return
     */
    public static XMLGregorianCalendar getXMLGregorianCalendarNow()
    {
	GregorianCalendar gregorianCalendar = new GregorianCalendar();
	DatatypeFactory datatypeFactory = null;
	try
	{
	    datatypeFactory = DatatypeFactory.newInstance();
	}
	catch (DatatypeConfigurationException e)
	{
	    System.out.println(e.toString());
	    System.exit(-1);
	}

	XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);

	return now;
    }

    /*
     * Private methods
     */
    private boolean storeProperties(Properties properties, String filePath)
    {
	try
	{
	    properties.store(new FileOutputStream(filePath), null);

	    return true;
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}

	return false;
    }
    
    private boolean storePropertiesFileFromVDConfigurations(Node configurationNode, String filePath)
    {
	Properties properties = new Properties();
	
	NodeList configsList = configurationNode.getChildNodes();
	
	for(int i = 0; i < configsList.getLength(); i++)
	{
	      Node childNode = configsList.item(i);
	      
	      if(childNode.getNodeType() == Node.ELEMENT_NODE)
	      {
	          properties.setProperty(childNode.getNodeName(), childNode.getTextContent());
	      }	
	}
	
	try
	{
	    properties.store(new FileOutputStream(filePath), null);

	    return true;
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}

	return false;
    }
    
    private static Properties loadProperties(String configurationFileName)
    {
	boolean debug_path = false;
	Properties properties = new Properties();

	if (debug_path) {
		try {
		System.out.println("configuration: "+new File(configurationFileName).getCanonicalFile()+"\n");
	    }
		catch (IOException e)
	    {
		e.printStackTrace();
	    }
	}
	if (new File(configurationFileName).exists())
	{
	    try
	    {
		properties.load(new FileInputStream(configurationFileName));
	    }
	    catch (IOException e)
	    {
		e.printStackTrace();
	    }
	}
	else
	{
	    //properties = createDefaultProperties();
	}

	return properties;
    }

    private Properties createDefaultProperties()
    {
	/* *********************** */
	/* Load default properties */
	/* *********************** */

	Properties properties = new Properties();

	// RabbitMQ Server connection configurations
	properties.setProperty("RabbitHost", RABBIT_HOST);
	properties.setProperty("RabbitUser", RABBIT_USER);
	properties.setProperty("RabbitPassword", RABBIT_PASSWORD);
	properties.setProperty("RabbitVHost", RABBIT_VHOST);

	// Exchanges configurations
	properties.setProperty("ExchangeVD", EXCHANGE_VD);
	properties.setProperty("ExchangeSC", EXCHANGE_SC);
	properties.setProperty("ExchangeFC", EXCHANGE_FC);
	properties.setProperty("ExchangeEMBI", EXCHANGE_EMBI);
	properties.setProperty("ExchangeVDType", EXCHANGE_VD_TYPE);
	properties.setProperty("ExchangeSCType", EXCHANGE_SC_TYPE);
	properties.setProperty("ExchangeFCType", EXCHANGE_FC_TYPE);
	properties.setProperty("ExchangEMBIType", EXCHANGE_EMBI_TYPE);
	
	properties.setProperty("ExchangeName", EXCHANGE_NAME);
	properties.setProperty("ExchangeType", EXCHANGE_TYPE);
	
	properties.setProperty("RoutingKey", ROUTING_KEY);
	
	properties.setProperty("ComponentId", COMPONENT_ID);

	// Store configurations into configuration file
	this.storePropertiesFile(properties, CONFIGURATION_FILENAME);

	return properties;
    }
}
