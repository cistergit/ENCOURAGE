/**
VirtualDevicesModule.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package cister.rabbitmq.library;

import com.rabbitmq.client.ConsumerCancelledException;
import java.io.StringReader;

import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import encourager.generated.confappliance.ConfAppliance;
import encourager.generated.confcell.ConfCell;
import encourager.generated.confgateway.ConfGateway;
import encourager.generated.confmacrocell.ConfMacroCell;
import encourager.generated.confmanufacturer.ConfManufacturer;
import encourager.generated.confroom.ConfRoom;
import encourager.generated.confshadowdevice.ConfShadowDevice;
import encourager.*;
import flexjson.JSONSerializer;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class VirtualDevicesModule implements VirtualDevicesModuleMBean, NotificationListener
{
    /*
     * Constants
     */
    
    private final String EXCHANGE_MANAGEMENT = "MNG_Exchange";
    private final String EXCHANGE_SENSORDATA = "SensorData";
    private final String EXCHANGE_COMMANDS = "Commands";
    private final String EXCHANGE_VDORDERS = "VDOrders";
    private final String EXCHANGE_VDDATABASE = "VDDatabaseExchange";
    private final String EXCHANGE_VDTOAPP = "VDToApp";
    private final String EXCHANGE_WEB = "WEB_Exchange";
    private final String QUEUE_MANAGEMENT = "MNG_Queue";
    private final String QUEUE_SENSORDATA = "SensorData_Queue";
    private final String QUEUE_COMMANDS = "Commands_Queue";
    private final String QUEUE_REPLY = "VDReply_Queue";
    private final String ROUTING_KEY_WILDCARD = "#";
    private ConcurrentHashMap<String, MacroCell> macroCellsList;
    private ConcurrentHashMap<String, Cell> cellsList;
    private ConcurrentHashMap<String, Room> roomsList;
    private ConcurrentHashMap<String, Gateway> gatewaysList;
    private ConcurrentHashMap<String, Appliance> appliancesList;
    private ConcurrentHashMap<String, ShadowDevice> shadowDevicesList;
    private ConcurrentHashMap<String, ConfManufacturer> manufacturersList;
    private ConcurrentHashMap<String, MacroCell> currentVirtualDevicesStructure;
    private RabbitConfiguration rabbitConfiguration = null;
    private RabbitManager rabbitManager = null;
    private ConcurrentHashMap<String, String> messageTracking;
    private static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass().getName());

    /*
     * Instance variables
     */

    /*
     * Constructors
     */
    public VirtualDevicesModule()
    {
        // initialize macrocells container
        this.macroCellsList = new ConcurrentHashMap<>();
        this.cellsList = new ConcurrentHashMap<>();
        this.gatewaysList = new ConcurrentHashMap<>();
        this.roomsList = new ConcurrentHashMap<>();
        this.appliancesList = new ConcurrentHashMap<>();
        this.shadowDevicesList = new ConcurrentHashMap<>();
        this.manufacturersList = new ConcurrentHashMap<>();
        this.currentVirtualDevicesStructure = new ConcurrentHashMap<>();
        this.rabbitConfiguration = new RabbitConfiguration();
        this.messageTracking = new ConcurrentHashMap<>();
        try
        {
            this.rabbitManager = new RabbitManager()
            {
                @Override
                public Runnable handleRabbitMessage(QueueingConsumer.Delivery delivery)
                {
                    // TODO Auto-generated method stub
                    handleRequest(delivery);
                    return null;
                }
            };
        }
        catch (Exception ex)
        {
            System.err.println("Error creating RabbitManager instance.");
        }

        String debug = "\n###########################################################################################################";
        debug += "\n*** Management Module started ***";
        debug += "\n\n--> Listening for messages on:";
        debug += "\nRoutingKey: " + ROUTING_KEY_WILDCARD;
        debug += "\nExchange: " + EXCHANGE_MANAGEMENT;
        debug += "\nQueue: " + QUEUE_MANAGEMENT;
        debug += "\n###########################################################################################################";

        logger.debug(debug);
        try {
            rabbitManager.subscribe(QUEUE_MANAGEMENT, EXCHANGE_MANAGEMENT, ROUTING_KEY_WILDCARD);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Public methods
     */
    /**
     *
     * @param delivery
     */
    public void handleRequest(QueueingConsumer.Delivery delivery)
    {
        String exchangeSource = delivery.getEnvelope().getExchange();
        
        switch (exchangeSource)
        {
            case EXCHANGE_MANAGEMENT:
                this.handleManagementMessage(delivery);
                break;
            case EXCHANGE_COMMANDS:
                this.handleMessage(delivery);
                break;
            case EXCHANGE_SENSORDATA:
                this.handleMessage(delivery);
                break;
            default:
                this.handleMessage(delivery);
                break;
        }
    }

    /*
     * Private methods
     */
    private void handleManagementMessage(QueueingConsumer.Delivery delivery)
    {
        String routingKey = delivery.getEnvelope().getRoutingKey();
        String message = new String(delivery.getBody());
        
        logger.debug(message);

        String[] splittedRoutingKey = routingKey.split("\\.");

        String method = splittedRoutingKey[0];
        String instance = "";

        if (splittedRoutingKey.length > 1 && splittedRoutingKey[1] != null)
        {
            instance = splittedRoutingKey[1];
        }

        switch (method)
        {
            // create element
            case "create":
                switch (instance)
                {
                    // create --> macrocell
                    case "macrocell":
                        this.createMacroCellFromConfigurationFile(message);
                        break;

                    // create --> cell
                    case "cell":
                        this.createCellFromConfigurationFile(message);
                        break;

                    // create --> device
                    case "device":
                        this.createDeviceFromConfigurationFile(message);
                        break;

                    // create --> room
                    case "room":
                        this.createRoomFromConfigurationFile(message);
                        break;

                    // create --> gateway
                    case "gateway":
                        this.createGatewayFromConfigurationFile(message);
                        break;

                    // create --> appliance
                    case "appliance":
                        this.createApplianceFromConfigurationFile(message);
                        break;
                        
                    // create --> manufacturer
                    case "manufacturer":
                        this.createManufacturerFromConfigurationFile(message);
                        break;
                        
                    // create --> user
                    case "user":
                        this.createUserFromConfigurationFile(message);
                        break;
                        
                    // create --> appliance
                    case "marketparticipant":
                        this.createMarketParticipant(message);
                        break;

                    // create --> all
                    case "all":
                        this.createCompleteStructure(message);

                        JSONSerializer serializer = new JSONSerializer();
                        String currentVirtualDevicesStructureJson = serializer.serialize(this.currentVirtualDevicesStructure);
                        logger.debug("Create complete structure: " + currentVirtualDevicesStructureJson);
                        try {
                            this.rabbitManager.subscribe(QUEUE_SENSORDATA, EXCHANGE_SENSORDATA, ROUTING_KEY_WILDCARD);
                            this.rabbitManager.subscribe(QUEUE_COMMANDS, EXCHANGE_COMMANDS, ROUTING_KEY_WILDCARD);
                            this.rabbitManager.subscribe(QUEUE_REPLY, null, null);
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;

                    default:
                        break;
                }
                break;

            case "update":
                break;

            case "delete":
                break;

            case "vdstructure":
                JSONSerializer serializer = new JSONSerializer();
                String macroCellsListJson = serializer.serialize(this.macroCellsList);
                logger.info("Request VDStructure, sending...");
                logger.debug(macroCellsListJson);
                try {
                    this.rabbitManager.publish(EXCHANGE_WEB, "macroCellsStructure", macroCellsListJson, null, false, false);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            default:
                break;
        }
    }
    
    private void createCompleteStructure(String message)
    {
        Document document = this.StringToXMLDocument(message);

        Element rootElement = document.getDocumentElement();

        NodeList nodes = rootElement.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++)
        {
            String stringElement = "";
            Node node = nodes.item(i);

            if (node instanceof Element)
            {
                //a child element to process
                Element child = (Element) node;
                
                switch (child.getTagName())
                {
                    case "ConfMacroCell":
                        stringElement = this.XMLDocumentToString(child);
                        this.createMacroCellFromConfigurationFile(stringElement);
                        break;
                    case "ConfCell":
                        stringElement = this.XMLDocumentToString(child);
                        this.createCellFromConfigurationFile(stringElement);
                        break;
                    case "ConfRoom":
                        stringElement = this.XMLDocumentToString(child);
                        this.createRoomFromConfigurationFile(stringElement);
                        break;
                    case "ConfAppliance":
                        stringElement = this.XMLDocumentToString(child);
                        this.createApplianceFromConfigurationFile(stringElement);
                        break;
                    case "ConfGateway":
                        stringElement = this.XMLDocumentToString(child);
                        this.createGatewayFromConfigurationFile(stringElement);
                        break;
                    case "ConfManufacturer":
                        stringElement = this.XMLDocumentToString(child);
                        this.createManufacturerFromConfigurationFile(stringElement);
                        break;
                    case "ConfShadowDevice":
                        stringElement = this.XMLDocumentToString(child);
                        this.createDeviceFromConfigurationFile(stringElement);
                        break;
                }
            }
        }
    }
    
    private void handleMessage(QueueingConsumer.Delivery delivery)
    {
        logger.entry();
        
        MeterReadingsManager mrm = null;
		EndDeviceControlsManager edcm = null;
        boolean replyRequired = false;
        boolean brokerAck = false;
        String exchange = delivery.getEnvelope().getExchange();
        String routingKey = delivery.getEnvelope().getRoutingKey();
        String correlationId = delivery.getProperties().getCorrelationId();
        String messageReceived = new String(delivery.getBody());

		logger.info("Received message on exchange with routing key and correlation id: [RECV," +exchange+","+routingKey+","+correlationId+"]");
        logger.debug(messageReceived);
        
        if (exchange.equals(EXCHANGE_SENSORDATA))
        {
            logger.debug(correlationId + " BEGIN - SENSOR DATA HANDLING");
            
            try
            {
                logger.debug(correlationId + " BEGIN - Unmarshall MeterReadingsManager object (bind xml message to MeterReadingsManager object)");
                mrm = MeterReadingsManager.Unmarshal(messageReceived);
                logger.debug(correlationId + " END - Unmarshall MeterReadingsManager object (bind xml message to MeterReadingsManager object)");
                
            }
            catch (Exception ex)
            {
                logger.error("Cannot unmarshall message received: " + messageReceived);
                logger.error(ex);
            }

            // get semantics
            String messageType = "";
            try
            {
                logger.debug("Infer semantics MeterReadings");
                messageType = mrm.InferSemantics();
            }
            catch (Exception ex)
            {
                logger.error("Cannot infer semantics of message: " + messageReceived);
                logger.error(ex);
            }

            logger.debug("Received new *reading* of type " + messageType);

            String sqlStatements = null;

            if (messageType != null && !messageType.equals(""))
            {
                logger.debug("Get message SQL");
                try {
                    // get sql commands for received message
                    sqlStatements = mrm.ToSql(null);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
                }

                logger.debug("Insert into database following statements: {}", sqlStatements);
                try {
                    // insert into database
                    this.rabbitManager.publish(EXCHANGE_VDDATABASE, "", sqlStatements, null, replyRequired, brokerAck);
                } catch (Exception ex) {
                    logger.error("Error trying to publish message to VDDatatabaseHandler..." + ex.getMessage());
                }
            }
            else
            {
                logger.error("InferSemantics failed, do not get SQL or publish message to VDDatabaseHandler. " + messageReceived);
            }
            
            try {
                // send message to dummy app
				logger.info("Sending message to exchange with routing key and correlation id: [SEND," +EXCHANGE_VDTOAPP+","+routingKey+","+delivery.getProperties().getCorrelationId()+"]");
                this.rabbitManager.publish(EXCHANGE_VDTOAPP, routingKey, messageReceived, delivery.getProperties().getCorrelationId(), replyRequired, brokerAck);
            } catch (Exception ex) {
                logger.error("Error trying to publish message to Supervisory Control Dummy App..." + ex.getMessage());
            }
            logger.debug(correlationId + " END - SENSOR DATA HANDLING");
        }
        else if (exchange.equals(EXCHANGE_COMMANDS))
        {
            logger.debug(correlationId + " BEGIN - COMMAND HANDLING");
            try
            {
                logger.debug(correlationId + " BEGIN - Unmarshall EndDeviceControl object (bind xml message to EndDeviceControlsManager object)");
                edcm = EndDeviceControlsManager.Unmarshal(messageReceived);
                logger.debug(correlationId + " END - Unmarshall EndDeviceControl object (bind xml message to EndDeviceControlsManager object)");
            }
            catch (Exception ex)
            {
                logger.error("Cannot unmarshall command received: " + messageReceived);
                logger.error(ex);
            }

            // get semantics
            String controlType = "";
            try
            {
                logger.debug("Infer semantics EndDeviceControl");
                controlType = edcm.InferSemantics();
            }
            catch (Exception ex)
            {
                logger.error("Cannot infer semantics of message: " + messageReceived);
                logger.error(ex);
            }

            // update ui
            logger.debug("Received new *control* of type " + controlType);

            String sqlStatements = null;
			try
            {
                logger.debug("Get control message SQL statement");
                // get sql commands for received message
                sqlStatements = edcm.ToSql();

                logger.debug("Insert into database following statements: {}", sqlStatements);

                // insert into database
                this.rabbitManager.publish(EXCHANGE_VDDATABASE, "", sqlStatements, null, replyRequired, brokerAck);
            }
            catch (Exception ex)
            {
				java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
            }
            // publish control
            try
            {
                //String corrId = java.util.UUID.randomUUID().toString();
                String corrId = delivery.getProperties().getCorrelationId();

                // add message to tracking
                this.messageTracking.put(corrId, messageReceived);

                // set replyRequired to true to require reply from middleware plugin
                replyRequired = true;

                // need to publish one message per gateway or will have an unique routing key for entire macrocell ?
                //String routingKeyToPublish = this.getGatewayId() + "." + this.getDeviceId();
//                String routingKeyToPublish = "DUMMY COMMAND";
                String routingKeyToPublish = routingKey;


                logger.debug("Redirect/publish order to middleware plugin");

                // publish message to rabbit
				logger.info("Sending message to exchange with routing key and correlation id: [SEND," +EXCHANGE_VDORDERS+","+routingKeyToPublish+","+corrId+"]");
                this.rabbitManager.publish(EXCHANGE_VDORDERS, routingKeyToPublish, messageReceived, corrId, replyRequired, brokerAck);
            }
            catch (ShutdownSignalException | ConsumerCancelledException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                logger.error("Error publishing SC order to VDOrders Exchange.");
                logger.error("Failed to send message to: " + messageReceived);
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
            }
            logger.debug(correlationId + " END - COMMAND HANDLING");
        }
        else
        {
            // message ack from middleware plugin
            logger.debug("Message ACK received...");
            
            if (correlationId != null)
            {
                if (this.messageTracking.containsKey(correlationId))
                {
                    logger.debug("Remove message from tracking, ack received");
                    logger.debug("Middleware Plugin ACK for message: " + correlationId + "\nMessage content: " + this.messageTracking.get(correlationId));
                    this.messageTracking.remove(correlationId);
                }
            }
        }
        
        logger.debug("Finished message handling");

        logger.exit();
    }

    private void createMacroCellFromConfigurationFile(String msg)
    {
        String message = this.preparePhysicalConfMessage(msg, "ConfMacroCell");
        
        PhysicalConfManager mr = new PhysicalConfManager("");
        PhysicalConfManager mr2 = null;
        String sqlStatements = "";
        try
        {
            mr2 = PhysicalConfManager.Unmarshal(message, mr);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            sqlStatements = mr2.ToSql();
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConfMacroCell confMacroCell = null;
        for (int i = 0; i < mr2.getM_macrocells().size(); i++)
        {
            confMacroCell = mr2.getM_macrocells().get(i);

            if (!macroCellExists(confMacroCell.getMacrocellId()))
            {
                MacroCell macroCell = new MacroCell();
                macroCell.setMacroCellId(confMacroCell.getMacrocellId());
                macroCell.setMacroCellDescription(confMacroCell.getMacrocellDesc());
                macroCell.setMacroCellRegion(confMacroCell.getRegion());

                logger.debug("Added MacroCell (ID=" + macroCell.getMacroCellId() + ")");

                this.macroCellsList.put(macroCell.getMacroCellId(), macroCell);
                
                // web app
                this.currentVirtualDevicesStructure.put(macroCell.getMacroCellId(), macroCell);

                logger.debug("MacroCell created...");
            }
            else
            {
                logger.error("MacroCell " + confMacroCell.getMacrocellId() + " already exists.");
            }
        }

        try
        {
            // insert into database
            this.rabbitManager.publish(EXCHANGE_VDDATABASE, "", sqlStatements, null, false, false);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createCellFromConfigurationFile(String msg)
    {
        String message = this.preparePhysicalConfMessage(msg, "ConfCell");
        
        PhysicalConfManager mr = new PhysicalConfManager("");
        PhysicalConfManager mr2 = null;
        String sqlStatements = "";
        try
        {
            mr2 = PhysicalConfManager.Unmarshal(message, mr);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            sqlStatements = mr2.ToSql();
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConfCell confCell = null;
        for (int i = 0; i < mr2.getM_cells().size(); i++)
        {
            confCell = mr2.getM_cells().get(i);

            if (!macroCellExists(confCell.getMacrocellId()))
            {
                logger.error("Failed creating Cell " + confCell.getCellId() + ". Cause: MacroCell " + confCell.getMacrocellId() + " doesn't exist.");
                break;
            }
            
            if (!cellExists(confCell.getCellId()))
            {
                Cell cell = new Cell();
                cell.setMacroCellId(confCell.getMacrocellId());
                cell.setCellBuildingShadeCoefficient(confCell.getCellBuildingShadeCoefficient());
                cell.setCellDescription(confCell.getCellDesc());
                cell.setCellId(confCell.getCellId());
                cell.setCellLatitude(confCell.getCellLatitud());
                cell.setCellLongitude(confCell.getCellLongitud());
                cell.setCellSurface(confCell.getCellSurface());
                cell.setCellVolumen(confCell.getCellVolumen());
                cell.setCellWallInsulationThickness(confCell.getCellWallInsulationThickness());

                /*
                    Web App
                    Default Room and Appliance
                */
                /*Room defaultRoom = new Room();
                defaultRoom.setCellId(cell.getCellId());
                defaultRoom.setRoomId("0");
                
                Appliance defaultAppliance = new Appliance();
                defaultAppliance.setRoomId(defaultRoom.getRoomId());
                defaultAppliance.setApplianceId("0");
                
                defaultRoom.addAppliance(defaultAppliance);
                cell.addRoom(defaultRoom);*/
                /*
                    ----------------------------
                */
                
                logger.debug("Added Cell (ID=" + cell.getCellId() + ")");

                this.cellsList.put(cell.getCellId(), cell);
                
                // web app
                this.currentVirtualDevicesStructure.get(cell.getMacroCellId()).addCell(cell);

                logger.debug("Cell created...");
            }
            else
            {
                logger.error("Cell " + confCell.getCellId() + " already exists.");
            }
        }

        try
        {
            // insert into database
            this.rabbitManager.publish(EXCHANGE_VDDATABASE, "", sqlStatements, null, false, false);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createRoomFromConfigurationFile(String msg)
    {
        String message = this.preparePhysicalConfMessage(msg, "ConfRoom");
        
        PhysicalConfManager mr = new PhysicalConfManager("");
        PhysicalConfManager mr2 = null;
        String sqlStatements = "";
        try
        {
            mr2 = PhysicalConfManager.Unmarshal(message, mr);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            sqlStatements = mr2.ToSql();
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConfRoom confRoom = null;
        for (int i = 0; i < mr2.getM_rooms().size(); i++)
        {
            confRoom = mr2.getM_rooms().get(i);

            if (!cellExists(confRoom.getCellId()))
            {
                logger.error("Failed creating Room " + confRoom.getRoomId() + ". Cause: Cell " + confRoom.getCellId() + " doesn't exist.");
                break;
            }
            
            if (!roomExists(confRoom.getRoomId()))
            {
                Room room = new Room();
                room.setRoomId(confRoom.getRoomId());
                room.setCellId(confRoom.getCellId());
                room.setRoomDescription(confRoom.getRoomDesc());
                room.setRoomMaxOccupation(confRoom.getRoomMaxOcup());
                room.setRoomSurface(confRoom.getRoomSurface());
                room.setRoomVolume(confRoom.getRoomVolume());

                logger.debug("Added Room (ID=" + room.getRoomId() + ")");

                this.roomsList.put(room.getRoomId(), room);
                
                // web app
                this.currentVirtualDevicesStructure.get(this.cellsList.get(room.getCellId()).getMacroCellId()).getCellsList().get(room.getCellId()).addRoom(room);
            }
            else
            {
                logger.error("Room " + confRoom.getRoomId() + " already exists.");
            }
        }

        try
        {
            // insert into database
            this.rabbitManager.publish(EXCHANGE_VDDATABASE, "", sqlStatements, null, false, false);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createApplianceFromConfigurationFile(String msg)
    {
        String message = this.preparePhysicalConfMessage(msg, "ConfAppliance");
        
        PhysicalConfManager mr = new PhysicalConfManager("");
        PhysicalConfManager mr2 = null;
        String sqlStatements = "";
        try
        {
            mr2 = PhysicalConfManager.Unmarshal(message, mr);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            sqlStatements = mr2.ToSql();
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConfAppliance confAppliance = null;
        for (int i = 0; i < mr2.getM_appliances().size(); i++)
        {
            confAppliance = mr2.getM_appliances().get(i);

            if (!roomExists(confAppliance.getRoomId()))
            {
                logger.error("Failed creating Appliance " + confAppliance.getApplianceId() + ". Cause: Room " + confAppliance.getRoomId() + " doesn't exist.");
                break;
            }
            
            if (!applianceExists(confAppliance.getApplianceId()))
            {
                Appliance appliance = new Appliance();
                appliance.setApplianceId(confAppliance.getApplianceId());
                appliance.setRoomId(confAppliance.getRoomId());
                appliance.setApplianceActivationDate(confAppliance.getApplianceActivacionDate());
                appliance.setApplianceDescription(confAppliance.getApplianceDesc());
                appliance.setApplianceInvestment(confAppliance.getApplianceInvestment());
                appliance.setApplianceLeavingDate(confAppliance.getApplianceLeavingDate());
                appliance.setAppliancePower(confAppliance.getAppliancePower());
                appliance.setSubcategoryId(confAppliance.getSubcategoryId());

                logger.debug("Added Appliance (ID=" + appliance.getApplianceId() + ")");

                this.appliancesList.put(appliance.getApplianceId(), appliance);
                
                // web app
                String macroCellId = this.cellsList.get(this.roomsList.get(appliance.getRoomId()).getCellId()).getMacroCellId();
                String cellId = this.roomsList.get(appliance.getRoomId()).getCellId();
                String roomId = appliance.getRoomId();
                this.currentVirtualDevicesStructure.get(macroCellId).getCellsList().get(cellId).getRoomsList().get(roomId).addAppliance(appliance);
            }
            else
            {
                logger.error("Appliance " + confAppliance.getApplianceId() + " already exists.");
            }
        }

        try
        {
            // insert into database
            this.rabbitManager.publish(EXCHANGE_VDDATABASE, "", sqlStatements, null, false, false);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createGatewayFromConfigurationFile(String msg)
    {
        String message = this.preparePhysicalConfMessage(msg, "ConfGateway");
        
        PhysicalConfManager mr = new PhysicalConfManager("");
        PhysicalConfManager mr2 = null;
        String sqlStatements = "";
        try
        {
            mr2 = PhysicalConfManager.Unmarshal(message, mr);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            sqlStatements = mr2.ToSql();
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConfGateway confGateway = null;
        for (int i = 0; i < mr2.getM_gateways().size(); i++)
        {
            confGateway = mr2.getM_gateways().get(i);
            
            if (!manufacturerExists(confGateway.getManufacturerId()))
            {
                logger.error("Failed creating Gateway " + confGateway.getGatewayId() + ". Cause: Manufacturer " + confGateway.getManufacturerId() + " doesn't exist.");
                break;
            }
            
            if (!gatewayExists(confGateway.getGatewayId()))
            {
                Gateway gateway = new Gateway();
                gateway.setGatewayId(confGateway.getGatewayId());
                gateway.setManufacturerId(confGateway.getManufacturerId());
                gateway.setMiddlewarePluginId("");
                gateway.setGatewayDesc(confGateway.getGatewayDesc());
                gateway.setGatewayMacadr(confGateway.getGatewayMacadr());

                logger.debug("Added Gateway (ID=" + gateway.getGatewayId() + ")");

                this.gatewaysList.put(gateway.getGatewayId(), gateway);
            }
            else
            {
                logger.error("Gateway " + confGateway.getGatewayId() + " already exists.");
            }
        }

        try
        {
            // insert into database
            this.rabbitManager.publish(EXCHANGE_VDDATABASE, "", sqlStatements, null, false, false);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createManufacturerFromConfigurationFile(String msg)
    {
        String message = this.preparePhysicalConfMessage(msg, "ConfManufacturer");
        
        PhysicalConfManager mr = new PhysicalConfManager("");
        PhysicalConfManager mr2 = null;
        String sqlStatements = "";
        try
        {
            mr2 = PhysicalConfManager.Unmarshal(message, mr);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            sqlStatements = mr2.ToSql();
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConfManufacturer confManufacturer = null;
        for (int i = 0; i < mr2.getM_manufacturers().size(); i++)
        {
            confManufacturer = mr2.getM_manufacturers().get(i);
            
            if (!manufacturerExists(confManufacturer.getManufacturerId()))
            {
                logger.debug("Added Manufacturer (ID=" + confManufacturer.getManufacturerId() + ")");
                this.manufacturersList.put(confManufacturer.getManufacturerId(), confManufacturer);
            }
            else
            {
                logger.error("Manufacturer " + confManufacturer.getManufacturerId() + " already exists.");
            }
        }

        try
        {
            // insert into database
            this.rabbitManager.publish(EXCHANGE_VDDATABASE, "", sqlStatements, null, false, false);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createDeviceFromConfigurationFile(String msg)
    {
        String message = this.preparePhysicalConfMessage(msg, "ConfShadowDevice");
        
        PhysicalConfManager mr = new PhysicalConfManager("");
        PhysicalConfManager mr2 = null;
        String sqlStatements = "";
        try
        {
            mr2 = PhysicalConfManager.Unmarshal(message, mr);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            sqlStatements = mr2.ToSql();
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConfShadowDevice confShadowDevice = null;
        for (int i = 0; i < mr2.getM_shadowdevices().size(); i++)
        {
            confShadowDevice = mr2.getM_shadowdevices().get(i);
            
            if (confShadowDevice.getApplianceId().equals(""))
            {
                // default appliance or no appliance for this device
                logger.warn("Problem creating ShadowDevice " + confShadowDevice.getDeviceId() + ". Cause: Default appliance or device without appliance.");
            }
            else if (!applianceExists(confShadowDevice.getApplianceId()))
            {
                logger.error("Failed creating ShadowDevice " + confShadowDevice.getDeviceId() + ". Cause: Appliance " + confShadowDevice.getApplianceId() + " needed and doesn't exist. Solution: Create appliance first.");
                break;
            }
            
            if (!gatewayExists(confShadowDevice.getGatewayId()))
            {
                logger.warn("Problem creating ShadowDevice " + confShadowDevice.getDeviceId() + ". Cause: Gateway " + confShadowDevice.getGatewayId() + " doesn't exist.");
                //break;
            }
            
            if (!manufacturerExists(confShadowDevice.getManufacturerId()))
            {
                logger.warn("Problem creating ShadowDevice " + confShadowDevice.getDeviceId() + ". Cause: Manufacturer " + confShadowDevice.getManufacturerId() + " doesn't exist.");
                //break;
            }

            if (!shadowDeviceExists(confShadowDevice.getDeviceId()))
            {
                Appliance shadowDeviceAppliance = this.appliancesList.get(confShadowDevice.getApplianceId());
                Room shadowDeviceRoom = this.roomsList.get(shadowDeviceAppliance.getRoomId());
                Cell shadowDeviceCell = this.cellsList.get(shadowDeviceRoom.getCellId());
                MacroCell shadowDeviceMacroCell = this.macroCellsList.get(shadowDeviceCell.getMacroCellId());
                
                ShadowDevice shadowDevice = new ShadowDevice();
                shadowDevice.setDeviceId(confShadowDevice.getDeviceId());
                shadowDevice.setApplianceId(confShadowDevice.getApplianceId());
                shadowDevice.setRoomId(shadowDeviceAppliance.getRoomId());
                shadowDevice.setGatewayId(confShadowDevice.getGatewayId());
                shadowDevice.setManufacturerId(confShadowDevice.getManufacturerId());
                shadowDevice.setMiddlewarePluginId(confShadowDevice.getMiddlewarePluginId());
                shadowDevice.setCellId(shadowDeviceCell.getCellId());
                shadowDevice.setMacroCellId(shadowDeviceMacroCell.getMacroCellId());
                shadowDevice.setRoutingKey(shadowDeviceMacroCell.getMacroCellId() + "." + shadowDeviceCell.getCellId() + "." + shadowDevice.getDeviceId());
                shadowDevice.setDeviceDescription(confShadowDevice.getDeviceDesc());
                shadowDevice.setDeviceType(confShadowDevice.getDeviceType());
                shadowDevice.setDeviceOutput(confShadowDevice.getDeviceOutput());
                
                logger.debug("Added ShadowDevice (ID=" + shadowDevice.getDeviceId() + ")");

                this.shadowDevicesList.put(shadowDevice.getDeviceId(), shadowDevice);
                
                // web app
                String macroCellId = shadowDevice.getMacroCellId();
                String cellId = shadowDevice.getCellId();
                String roomId = shadowDevice.getRoomId();
                String applianceId = shadowDevice.getApplianceId();
                this.currentVirtualDevicesStructure.get(macroCellId).getCellsList().get(cellId).getRoomsList().get(roomId).getAppliancesList().get(applianceId).addShadowDevice(shadowDevice);

                logger.debug("ShadowDevice created...");
            }
            else
            {
                logger.error("ShadowDevice " + confShadowDevice.getDeviceId() + " already exists.");
            }
        }

        try
        {
            // insert into database
            this.rabbitManager.publish(EXCHANGE_VDDATABASE, "", sqlStatements, null, false, false);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createUserFromConfigurationFile(String message)
    {
        UsersManager um= new UsersManager("");
        UsersManager um2 = null;
        String sqlStatements = "";
        try
        {
            um2 = UsersManager.Unmarshal(message, um);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            sqlStatements = um2.ToSql();
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        try
        {
            // insert into database
            this.rabbitManager.publish(EXCHANGE_VDDATABASE, "", sqlStatements, null, false, false);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createMarketParticipant(String message)
    {
        MarketManager mm = new MarketManager("");
        MarketManager mm2 = null;
        String sqlStatements = "";
        try
        {
            mm2 = MarketManager.Unmarshal(message, mm);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            sqlStatements = mm2.ToSql();
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        try
        {
            // insert into database
            this.rabbitManager.publish(EXCHANGE_VDDATABASE, "", sqlStatements, null, false, false);
        }
        catch (Exception ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean macroCellExists(String macroCellId)
    {
        return this.macroCellsList.containsKey(macroCellId);
    }

    private boolean cellExists(String cellId)
    {
        return this.cellsList.containsKey(cellId);
    }

    private boolean roomExists(String roomId)
    {
        return this.roomsList.containsKey(roomId);
    }

    private boolean applianceExists(String applianceId)
    {
        return this.appliancesList.containsKey(applianceId);
    }

    private boolean shadowDeviceExists(String shadowDeviceId)
    {
        return this.shadowDevicesList.containsKey(shadowDeviceId);
    }
    
    private boolean gatewayExists(String gatewayId)
    {
        return this.gatewaysList.containsKey(gatewayId);
    }
    
    private boolean manufacturerExists(String manufacturerId)
    {
        return this.manufacturersList.containsKey(manufacturerId);
    }

    private String XMLDocumentToString(Element element)
    {
        String output = "";
        try
        {
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            StringWriter buffer = new StringWriter();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(new DOMSource(element), new StreamResult(buffer));
            output = buffer.toString();
        }
        catch (TransformerConfigurationException ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (TransformerException ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        return output;
    }

    private Document StringToXMLDocument(String xmlString)
    {
        Document document = null;

        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new InputSource(new StringReader(xmlString)));
        }
        catch (ParserConfigurationException | SAXException | IOException ex)
        {
            java.util.logging.Logger.getLogger(VirtualDevicesModule.class.getName()).log(Level.SEVERE, null, ex);
        }

        return document;
    }

    private String preparePhysicalConfMessage(String message, String type)
    {
        String preparedMessage = "";
        
        /*
        <?xml version="1.0" encoding="UTF-8" standalone="yes"?><ConfMacroCell macrocell_desc="NOVA MACROCELL" macrocell_id="NOVA_MACROCELL_ID" region="CISTER" xmlns="http://www.encourage-project.eu/ConfMacroCell#" />
        */
        
        preparedMessage = message.trim();
        
        if (!preparedMessage.contains("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"))
        {
            preparedMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + preparedMessage;
            
            switch (type)
                {
                    case "ConfMacroCell":
                        preparedMessage = preparedMessage.substring(0, preparedMessage.length() - 2);
                        preparedMessage += " xmlns=\"http://www.encourage-project.eu/ConfMacroCell#\" />";
                        break;
                    case "ConfCell":
                        preparedMessage = preparedMessage.substring(0, preparedMessage.length() - 2);
                        preparedMessage += " xmlns=\"http://www.encourage-project.eu/ConfCell#\" />";
                        break;
                    case "ConfRoom":
                        preparedMessage = preparedMessage.substring(0, preparedMessage.length() - 2);
                        preparedMessage += " xmlns=\"http://www.encourage-project.eu/ConfRoom#\" />";
                        break;
                    case "ConfAppliance":
                        preparedMessage = preparedMessage.substring(0, preparedMessage.length() - 2);
                        preparedMessage += " xmlns=\"http://www.encourage-project.eu/ConfAppliance#\" />";
                        break;
                    case "ConfGateway":
                        preparedMessage = preparedMessage.substring(0, preparedMessage.length() - 2);
                        preparedMessage += " xmlns=\"http://www.encourage-project.eu/ConfGateway#\" />";
                        break;
                    case "ConfManufacturer":
                        preparedMessage = preparedMessage.substring(0, preparedMessage.length() - 2);
                        preparedMessage += " xmlns=\"http://www.encourage-project.eu/ConfManufacturer#\" />";
                        break;
                    case "ConfShadowDevice":
                        preparedMessage = preparedMessage.substring(0, preparedMessage.length() - 2);
                        preparedMessage += " xmlns=\"http://www.encourage-project.eu/ConfShadowDevice#\" />";
                        break;
                }
        }
        
        return preparedMessage;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        VirtualDevicesModule virtualDevicesModule = new VirtualDevicesModule();
    }

    @Override
    public void handleNotification(Notification ntfctn, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getConnections() {
        return 10;
    }
}
