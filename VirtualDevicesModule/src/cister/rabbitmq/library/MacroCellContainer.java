/**
MacroCellContainer.java by César Teixeira and Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package cister.rabbitmq.library;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MacroCellContainer
{
    private static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());
    private static ArrayList<MacroCell> macroCellsList = new ArrayList<MacroCell>();    
    
    /**
     *
     * @param configurationFileName
     * @return
     */
    public static MacroCell createVirtualDevice(String configurationFileName)
    {
	MacroCell macroCell = new MacroCell();
	
	macroCellsList.add(macroCell);
	
	logger.info("New VirtualDevice created... Size: " + macroCellsList.size());
	
	return macroCell;
    }

    /**
     *
     * @return
     */
    public static Cell createCell()
    {
	return new Cell();
    }

    /**
     *
     * @return
     */
    public static Room createRoom()
    {
	return new Room();
    }

    /**
     *
     * @return
     */
    public static Appliance createAppliance()
    {
	return new Appliance();
    }

    /**
     *
     * @return
     */
    public static ShadowDevice createDevice()
    {
	ShadowDevice device = null;

	/*if (isActuator)
	{
	    device = new Actuator();
	}
	else if (isSensor)
	{
	    device = new Sensor();
	}*/
	
	// debug

	return device;
    }
    
    /**
     *
     * @return
     */
    public static int getNumberOfVirtualDevices()
    {
	return macroCellsList.size();
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<MacroCell> getExistentVirtualDevices()
    {
	return macroCellsList;
    }
}