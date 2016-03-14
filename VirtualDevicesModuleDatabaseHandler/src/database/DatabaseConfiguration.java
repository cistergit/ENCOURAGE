/**
DatabaseConfiguration.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package database;

import java.util.Properties;
import encourager.RabbitUtils;

public class DatabaseConfiguration
{
    /*
     *  Constants
     */
    private final String DATABASE_CONFIGURATION_FILE = "configs/database_config.properties";
    
    /*
     *  Instance variables
     */
    private String databaseUrl;
    private String databaseUser;
    private String databasePassword;
    private String databaseName;
    private String databaseHost;
    
    /*
     *  Constructors
     */
    /**
     *
     */
    public DatabaseConfiguration()
    {
	super();
        
        Properties properties = RabbitUtils.loadPropertiesFile(DATABASE_CONFIGURATION_FILE);
        
        this.databaseUrl = properties.getProperty("DATABASE_URL");
	this.databaseUser = properties.getProperty("DATABASE_USER");
	this.databasePassword = properties.getProperty("DATABASE_PASSWORD");
	this.databaseHost = properties.getProperty("DATABASE_HOST");
	this.databaseName = properties.getProperty("DATABASE_NAME");
        
        if (this.databaseUrl == null || this.databaseUrl.equals(""))
        {
            this.databaseUrl = "jdbc:postgresql://" + this.databaseHost + "/" + this.databaseName;
        }
    }
    /**
     *
     * @param databaseUser
     * @param databasePassword
     * @param databaseHost
     * @param databaseName
     */
    public DatabaseConfiguration(String databaseUser, String databasePassword, String databaseHost, String databaseName)
    {
	super();
	this.databaseUser = databaseUser;
	this.databasePassword = databasePassword;
	this.databaseHost = databaseHost;
	this.databaseName = databaseName;
        
        this.databaseUrl = "jdbc:postgresql://" + this.databaseHost + "/" + this.databaseName;
    }
    
    /**
     *
     * @param databaseUser
     * @param databasePassword
     * @param databaseUrl
     */
    public DatabaseConfiguration(String databaseUser, String databasePassword, String databaseUrl)
    {
	super();
	this.databaseUser = databaseUser;
	this.databasePassword = databasePassword;
        this.databaseUrl = databaseUrl;
    }

    /*
     *  Private methods
     */

        
    /*
     *  Public methods
     */
    public String getDatabaseUrl()
    {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl)
    {
        this.databaseUrl = databaseUrl;
    }

    public String getDatabaseUser()
    {
        return databaseUser;
    }

    public void setDatabaseUser(String databaseUser)
    {
        this.databaseUser = databaseUser;
    }

    public String getDatabasePassword()
    {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword)
    {
        this.databasePassword = databasePassword;
    }

    public String getDatabaseName()
    {
        return databaseName;
    }

    public void setDatabaseName(String databaseName)
    {
        this.databaseName = databaseName;
    }

    public String getDatabaseHost()
    {
        return databaseHost;
    }

    public void setDatabaseHost(String databaseHost)
    {
        this.databaseHost = databaseHost;
    }
}
