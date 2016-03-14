/**
Constants.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package supervisorycontrolapplicationdummy;

public class MessagesConstants
{
    public static String getShortXMLMeasure()
    {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<MeterReadings xmlns=\"http://www.encourage-project.eu/MeterReadings#\">\n" +
                "    <MeterReading>\n" +
                "        <Meter>\n" +
                "            <mRID>RoboticEye3</mRID>\n" +
                "        </Meter>\n" +
                "        <Readings>\n" +
                "            <timeStamp>2013-07-30T17:25:07.013+01:00</timeStamp>\n" +
                "            <value>8.0</value>\n" +
                "            <ReadingType ref=\"0.0.0.0.0.0.90.0.0.0.0.0.0.0.0.0.9000.0\"/>\n" +
                "        </Readings>\n" +
                "    </MeterReading>\n" +
                "</MeterReadings>";
    }
    
    public static String getLongXMLMeasure()
    {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<MeterReadings xmlns=\"http://www.encourage-project.eu/MeterReadings#\">\n" +
                "    <MeterReading>\n" +
                "        <Meter>\n" +
                "            <mRID>DKDJ010101</mRID>\n" +
                "            <Names>\n" +
                "                <name>192.168.1.1</name>\n" +
                "                <NameType>\n" +
                "                    <description>This is an endpoint serial\n" +
                "number</description>\n" +
                "                    <name>EndpointID</name>\n" +
                "                    <NameTypeAuthority>\n" +
                "                        <name>CISTER Research Unit</name>\n" +
                "                    </NameTypeAuthority>\n" +
                "                </NameType>\n" +
                "            </Names>\n" +
                "        </Meter>\n" +
                "        <Readings>\n" +
                "            <timeStamp>2013-09-27T16:47:22.612+01:00</timeStamp>\n" +
                "            <value>12.0</value>\n" +
                "            <ReadingType ref=\"0.0.0.0.0.0.46.0.0.0.0.0.0.0.0.0.23.0\"/>\n" +
                "        </Readings>\n" +
                "        <Readings>\n" +
                "            <timeStamp>2013-09-27T16:47:23.716+01:00</timeStamp>\n" +
                "            <value>20.0</value>\n" +
                "            <ReadingType ref=\"11.0.0.0.0.0.37.0.0.0.0.0.0.0.0.0.179.0\"/>\n" +
                "        </Readings>\n" +
                "        <Readings>\n" +
                "            <timeStamp>2013-09-27T16:47:24.719+01:00</timeStamp>\n" +
                "            <value>20.0</value>\n" +
                "            <ReadingType ref=\"11.0.0.0.0.0.58.0.0.0.0.0.0.0.0.-3.2.0\"/>\n" +
                "        </Readings>\n" +
                "        <Readings>\n" +
                "            <timeStamp>2013-09-27T16:47:25.722+01:00</timeStamp>\n" +
                "            <value>13.0</value>\n" +
                "            <ReadingType ref=\"11.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.43.0\"/>\n" +
                "        </Readings>\n" +
                "        <Readings>\n" +
                "            <timeStamp>2013-09-27T16:47:26.724+01:00</timeStamp>\n" +
                "            <value>12.0</value>\n" +
                "            <ReadingType ref=\"11.0.0.0.0.0.0.0.0.0.0.0.0.0.0.-2.0.0\"/>\n" +
                "        </Readings>\n" +
                "        <Readings>\n" +
                "            <timeStamp>2013-09-27T16:47:27.726+01:00</timeStamp>\n" +
                "            <value>20.0</value>\n" +
                "            <ReadingType ref=\"0.0.0.0.1.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
                "        </Readings>\n" +
                "        <Readings>\n" +
                "            <timeStamp>2013-09-27T16:47:28.728+01:00</timeStamp>\n" +
                "            <value>10.0</value>\n" +
                "            <ReadingType ref=\"11.0.0.0.0.0.46.0.0.0.0.0.0.0.0.0.23.0\"/>\n" +
                "        </Readings>\n" +
                "        <Readings>\n" +
                "            <timeStamp>2013-09-27T16:47:29.730+01:00</timeStamp>\n" +
                "            <value>20.0</value>\n" +
                "            <ReadingType ref=\"11.0.0.0.4.0.46.0.0.0.0.0.0.0.0.0.23.0\"/>\n" +
                "        </Readings>\n" +
                "        <Readings>\n" +
                "            <timeStamp>2013-09-27T16:47:30.733+01:00</timeStamp>\n" +
                "            <value>13.0</value>\n" +
                "            <ReadingType ref=\"0.0.0.0.0.0.90.0.0.0.0.0.0.0.0.0.9000.0\"/>\n" +
                "        </Readings>\n" +
                "        <Readings>\n" +
                "            <timeStamp>2013-09-27T16:47:31.734+01:00</timeStamp>\n" +
                "            <value>10.0</value>\n" +
                "            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.978\"/>\n" +
                "        </Readings>\n" +
                "        <Readings>\n" +
                "            <timeStamp>2013-09-27T16:47:32.736+01:00</timeStamp>\n" +
                "            <value>20.0</value>\n" +
                "            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.208\"/>\n" +
                "        </Readings>\n" +
                "        <Readings>\n" +
                "            <timeStamp>2013-09-27T16:47:33.738+01:00</timeStamp>\n" +
                "            <value>13.0</value>\n" +
                "            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
                "        </Readings>\n" +
                "    </MeterReading>\n" +
                "    <ReadingType>\n" +
                "        <accumulation>0</accumulation>\n" +
                "        <aggregate>0</aggregate>\n" +
                "        <commodity>0</commodity>\n" +
                "        <consumptionTier>0</consumptionTier>\n" +
                "        <cpp>0</cpp>\n" +
                "        <currency>0</currency>\n" +
                "        <flowDirection>0</flowDirection>\n" +
                "        <macroPeriod>0</macroPeriod>\n" +
                "        <measurementKind>46</measurementKind>\n" +
                "        <measuringPeriod>0</measuringPeriod>\n" +
                "        <multiplier>0</multiplier>\n" +
                "        <phases>0</phases>\n" +
                "        <tou>0</tou>\n" +
                "        <unit>23</unit>\n" +
                "        <argument>\n" +
                "            <denominator>0</denominator>\n" +
                "            <numerator>0</numerator>\n" +
                "        </argument>\n" +
                "        <interharmonic>\n" +
                "            <denominator>0</denominator>\n" +
                "            <numerator>0</numerator>\n" +
                "        </interharmonic>\n" +
                "        <Names>\n" +
                "            <name>0.0.0.0.0.0.46.0.0.0.0.0.0.0.0.0.23.0</name>\n" +
                "        </Names>\n" +
                "    </ReadingType>\n" +
                "    <ReadingType>\n" +
                "        <accumulation>0</accumulation>\n" +
                "        <aggregate>0</aggregate>\n" +
                "        <commodity>0</commodity>\n" +
                "        <consumptionTier>0</consumptionTier>\n" +
                "        <cpp>0</cpp>\n" +
                "        <currency>0</currency>\n" +
                "        <flowDirection>0</flowDirection>\n" +
                "        <macroPeriod>11</macroPeriod>\n" +
                "        <measurementKind>0</measurementKind>\n" +
                "        <measuringPeriod>0</measuringPeriod>\n" +
                "        <multiplier>-2</multiplier>\n" +
                "        <phases>0</phases>\n" +
                "        <tou>0</tou>\n" +
                "        <unit>0</unit>\n" +
                "        <argument>\n" +
                "            <denominator>0</denominator>\n" +
                "            <numerator>0</numerator>\n" +
                "        </argument>\n" +
                "        <interharmonic>\n" +
                "            <denominator>0</denominator>\n" +
                "            <numerator>0</numerator>\n" +
                "        </interharmonic>\n" +
                "        <Names>\n" +
                "            <name>11.0.0.0.0.0.0.0.0.0.0.0.0.0.0.-2.0.0</name>\n" +
                "        </Names>\n" +
                "    </ReadingType>\n" +
                "    <ReadingType>\n" +
                "        <accumulation>0</accumulation>\n" +
                "        <aggregate>0</aggregate>\n" +
                "        <commodity>0</commodity>\n" +
                "        <consumptionTier>0</consumptionTier>\n" +
                "        <cpp>0</cpp>\n" +
                "        <currency>0</currency>\n" +
                "        <flowDirection>0</flowDirection>\n" +
                "        <macroPeriod>11</macroPeriod>\n" +
                "        <measurementKind>46</measurementKind>\n" +
                "        <measuringPeriod>0</measuringPeriod>\n" +
                "        <multiplier>0</multiplier>\n" +
                "        <phases>0</phases>\n" +
                "        <tou>0</tou>\n" +
                "        <unit>23</unit>\n" +
                "        <argument>\n" +
                "            <denominator>0</denominator>\n" +
                "            <numerator>0</numerator>\n" +
                "        </argument>\n" +
                "        <interharmonic>\n" +
                "            <denominator>0</denominator>\n" +
                "            <numerator>0</numerator>\n" +
                "        </interharmonic>\n" +
                "        <Names>\n" +
                "            <name>11.0.0.0.0.0.46.0.0.0.0.0.0.0.0.0.23.0</name>\n" +
                "        </Names>\n" +
                "    </ReadingType>\n" +
                "    <ReadingType>\n" +
                "        <accumulation>0</accumulation>\n" +
                "        <aggregate>0</aggregate>\n" +
                "        <commodity>0</commodity>\n" +
                "        <consumptionTier>0</consumptionTier>\n" +
                "        <cpp>0</cpp>\n" +
                "        <currency>978</currency>\n" +
                "        <flowDirection>19</flowDirection>\n" +
                "        <macroPeriod>0</macroPeriod>\n" +
                "        <measurementKind>12</measurementKind>\n" +
                "        <measuringPeriod>0</measuringPeriod>\n" +
                "        <multiplier>3</multiplier>\n" +
                "        <phases>0</phases>\n" +
                "        <tou>0</tou>\n" +
                "        <unit>72</unit>\n" +
                "        <argument>\n" +
                "            <denominator>0</denominator>\n" +
                "            <numerator>0</numerator>\n" +
                "        </argument>\n" +
                "        <interharmonic>\n" +
                "            <denominator>0</denominator>\n" +
                "            <numerator>0</numerator>\n" +
                "        </interharmonic>\n" +
                "        <Names>\n" +
                "            <name>0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.978</name>\n" +
                "        </Names>\n" +
                "    </ReadingType>\n" +
                "</MeterReadings>";
    }
    
    public static String getShortXMLCommand()
    {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<EndDeviceControls xmlns=\"http://www.encourage-project.eu/EndDeviceControls#\">\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>enable</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-08T15:00:23.036+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR4</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-08T15:00:23.003+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "</EndDeviceControls>";
    }
    
    public static String getLongXMLCommand()
    {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<EndDeviceControls xmlns=\"http://www.encourage-project.eu/EndDeviceControls#\">\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>enable</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.410+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.17.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR1</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.352+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>22</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.413+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR1</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.412+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>TRUE</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.415+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.26\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR1</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.414+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>enable</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.418+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.17.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR2</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.417+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>22</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.420+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR2</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.419+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>TRUE</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.422+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.26\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR2</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.421+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>enable</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.424+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.17.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR3</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.423+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>22</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.426+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR3</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.425+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>TRUE</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.436+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.26\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR3</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.435+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>enable</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.438+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.17.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR4</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.437+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>22</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.440+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR4</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.439+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>TRUE</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.460+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.26\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR4</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.441+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>enable</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.461+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.17.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR5</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.461+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>22</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.463+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR5</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.462+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>TRUE</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.465+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.26\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR5</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.464+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>enable</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.466+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.17.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR6</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.466+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>22</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.468+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR6</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.467+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>TRUE</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.469+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.26\"/>\n" +
                "       <EndDevices>\n" +
                "            <mRID>ACTUATOR6</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.469+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>enable</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.471+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.17.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR7</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.470+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>22</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.472+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR7</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.472+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>TRUE</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.474+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.26\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR7</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.473+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>enable</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.475+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.17.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR8</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.475+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>22</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.477+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR8</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.476+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>TRUE</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.478+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.26\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR8</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.478+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>enable</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.480+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.17.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR9</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.479+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>22</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.482+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR9</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.481+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>TRUE</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.483+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.26\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR9</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.482+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>enable</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.485+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.17.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR10</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.484+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>22</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.486+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.13\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR10</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.485+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "    <EndDeviceControl>\n" +
                "        <issuerID>ISEP</issuerID>\n" +
                "        <PanDemandResponse>\n" +
                "            <command>TRUE</command>\n" +
                "            <duration>1.0</duration>\n" +
                "            <durationIndefinite>false</durationIndefinite>\n" +
                "            <startDateTime>2013-08-13T15:53:07.488+01:00</startDateTime>\n" +
                "        </PanDemandResponse>\n" +
                "        <EndDeviceControlType ref=\"16.15.101.26\"/>\n" +
                "        <EndDevices>\n" +
                "            <mRID>ACTUATOR10</mRID>\n" +
                "        </EndDevices>\n" +
                "        <primaryDeviceTiming>\n" +
                "            <duration>1.0</duration>\n" +
                "            <interval>\n" +
                "                <start>2013-08-13T15:53:07.487+01:00</start>\n" +
                "            </interval>\n" +
                "        </primaryDeviceTiming>\n" +
                "    </EndDeviceControl>\n" +
                "</EndDeviceControls>";
    }
    
    public static String getXML()
    {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<EndDeviceControls xmlns=\"http://www.encourage-project.eu/EndDeviceControls#\">\n" +
                    "    <EndDeviceControl>\n" +
                    "        <issuerID>mkt_intraday</issuerID>\n" +
                    "        <PanPricing>\n" +
                    "            <duration>0.0</duration>\n" +
                    "            <durationIndefinite>false</durationIndefinite>\n" +
                    "            <startDateTime>2014-09-26T09:00:00+01:00</startDateTime>\n" +
                    "            <PanPricingDetails>\n" +
                    "                <price>11.2300</price>\n" +
                    "                <unitOfMeasure>EKWH</unitOfMeasure>\n" +
                    "            </PanPricingDetails>\n" +
                    "        </PanPricing>\n" +
                    "        <EndDeviceControlType ref=\"23.20.95.24\"/>\n" +
                    "        <EndDevices>\n" +
                    "            <mRID>MACRO_DKD</mRID>\n" +
                    "        </EndDevices>\n" +
                    "        <primaryDeviceTiming>\n" +
                    "            <interval>\n" +
                    "                <start>2014-09-26T00:04:16.530+01:00</start>\n" +
                    "            </interval>\n" +
                    "        </primaryDeviceTiming>\n" +
                    "    </EndDeviceControl>\n" +
                    "</EndDeviceControls>";
    }
}
