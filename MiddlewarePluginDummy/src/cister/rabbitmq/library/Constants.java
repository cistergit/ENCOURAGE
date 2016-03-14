/**
Constants.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package cister.rabbitmq.library;
import java.util.Arrays;
import java.util.List;

public class Constants
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
                "            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.17.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.17.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.26\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.17.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.26\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.17.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.26\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.17.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.26\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.17.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.26\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.17.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.26\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.17.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.26\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.17.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.26\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.17.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.26\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.17.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.13\"/>\n" +
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
                "        <EndDeviceControlType ref=\"12.15.101.26\"/>\n" +
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
    
            static private String xml1 = "<MeterReadings xmlns=\"http://www.encourage-project.eu/MeterReadings#\">\n" +
"    <MeterReading>\n" +
"        <Meter>\n" +
"            <mRID>DKDJ050107</mRID>\n" +
"        </Meter>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.512Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.527Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.528Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.530Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.531Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.533Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.534Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.536Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.537Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.539Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.540Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.542Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.543Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.545Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.547Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.548Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.551Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.552Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.554Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.555Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.557Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.558Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.560Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.561Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.563Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.564Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.566Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.567Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.569Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.571Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.572Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.574Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.575Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.577Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.578Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.580Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.581Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.583Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.584Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.586Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.587Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.589Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.590Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.592Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.593Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.595Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.596Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.598Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.599Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2014-01-14T11:45:08.601Z</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n" +
"    </MeterReading>\n" +
"</MeterReadings>";

        static private String xmlhead1 = "<MeterReadings xmlns=\"http://www.encourage-project.eu/MeterReadings#\">\n" +
"    <MeterReading>\n" +
"        <Meter>\n" +
"            <mRID>";
        static private String xmlhead2 = "</mRID>\n" +
"        </Meter>\n";
        static private String xmlbody1 = "<Readings>\n" +
"            <timeStamp>";
                static private String xmlbody2 = "</timeStamp>\n" +
"            <value>5.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.19.0.12.0.0.0.0.0.0.0.0.3.72.0\"/>\n" +
"        </Readings>\n";
static private String xmlfooter = "    </MeterReading>\n" +
"</MeterReadings>";

        
        public static String getBigXML()
    {
String ts = "2014-01-14T11:45:08.587Z";
String device_id = "DKDJ050107";
//        return xml1;
StringBuilder sb = new StringBuilder();
            sb.append(xmlhead1).append(device_id).append(xmlhead2);
        for (int i=0;i<50;i++) sb.append(xmlbody1).append(ts).append(xmlbody2);
        sb.append(xmlfooter);
        return sb.toString();
    }

    public static String getSpanishXMLMeasure()
	{
		return "<MeterReadings xmlns=\"http://www.encourage-project.eu/MeterReadings#\">\n" +
"    <MeterReading>\n" +
"        <Meter>\n" +
"            <mRID>DKDJ050107</mRID>\n" +
"        </Meter>\n" +
"        <Readings>\n" +
"            <timeStamp>2015-12-15T16:32:32.649Z</timeStamp>\n" +
"            <value>10.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.1.0.37.0.0.0.0.0.0.0.0.3.38.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2015-12-15T16:32:32.649Z</timeStamp>\n" +
"            <value>8.0</value>\n" +
"            <ReadingType ref=\"0.0.0.0.1.0.35.0.0.0.0.0.0.0.0.3.63.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2015-12-15T16:32:32.649Z</timeStamp>\n" +
"            <value>1.0</value>\n" +
"            <ReadingType ref=\"11.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0\"/>\n" +
"        </Readings>\n" +
"        <Readings>\n" +
"            <timeStamp>2015-12-15T16:32:32.649Z</timeStamp>\n" +
"            <value>11.9</value>\n" +
"            <ReadingType ref=\"0.0.0.0.1.2.54.0.0.0.0.0.0.0.0.0.29.0\"/>\n" +
"        </Readings>\n" +
"    </MeterReading>\n" +
"</MeterReadings>";
	}

	    public static List<String> all_devices = Arrays.asList(
                "DKD.DKDEM01.DKDJ010101",
                "DKD.DKDEM01.DKDJ010102",
                "DKD.DKDEM01.DKDJ010103",
                "DKD.DKDEM01.DKDJ010104",
                "DKD.DKDEM01.DKDJ010105",
                "DKD.DKDEM01.DKDJ010106",
                "DKD.DKDEM01.DKDJ010107",
                "DKD.DKDEM01.DKDJ010108",
                "DKD.DKDEM01.DKDJ010109",
                "DKD.DKDEM01.DKDJ010110",
                "DKD.DKDEM01.DKDJ010111",
                "DKD.DKDEM01.DKDJ010112",
                "DKD.DKDEM01.DKDJ010113",
                "DKD.DKDEM01.DKDJ090101",
                "DKD.DKDEM01.DKDJ090108",
                "DKD.DKDEM02.DKDJ020101",
                "DKD.DKDEM02.DKDJ020102",
                "DKD.DKDEM02.DKDJ020103",
                "DKD.DKDEM02.DKDJ020104",
                "DKD.DKDEM02.DKDJ020105",
                "DKD.DKDEM02.DKDJ020106",
                "DKD.DKDEM02.DKDJ020107",
                "DKD.DKDEM02.DKDJ020108",
                "DKD.DKDEM02.DKDJ020109",
                "DKD.DKDEM02.DKDJ020110",
                "DKD.DKDEM02.DKDJ020111",
                "DKD.DKDEM03.DKDJ030101",
                "DKD.DKDEM03.DKDJ030102",
                "DKD.DKDEM03.DKDJ030103",
                "DKD.DKDEM03.DKDJ030104",
                "DKD.DKDEM03.DKDJ030105",
                "DKD.DKDEM03.DKDJ030106",
                "DKD.DKDEM03.DKDJ030107",
                "DKD.DKDEM03.DKDJ030108",
                "DKD.DKDEM03.DKDJ030109",
                "DKD.DKDEM03.DKDJ030110",
                "DKD.DKDEM03.DKDJ030111",
                "DKD.DKDEM03.DKDJ030112",
                "DKD.DKDEM03.DKDJ030113",
                "DKD.DKDEM04.DKDJ040101",
                "DKD.DKDEM04.DKDJ040102",
                "DKD.DKDEM04.DKDJ040103",
                "DKD.DKDEM04.DKDJ040104",
                "DKD.DKDEM04.DKDJ040105",
                "DKD.DKDEM04.DKDJ040106",
                "DKD.DKDEM04.DKDJ040107",
                "DKD.DKDEM04.DKDJ040108",
                "DKD.DKDEM04.DKDJ040109",
                "DKD.DKDEM04.DKDJ040110",
                "DKD.DKDEM04.DKDJ040111",
                "DKD.DKDEM05.DKDJ050101",
                "DKD.DKDEM05.DKDJ050102",
                "DKD.DKDEM05.DKDJ050103",
                "DKD.DKDEM05.DKDJ050104",
                "DKD.DKDEM05.DKDJ050105",
                "DKD.DKDEM05.DKDJ050106",
                "DKD.DKDEM05.DKDJ050107",
                "DKD.DKDEM05.DKDJ050108",
                "DKD.DKDEM05.DKDJ050109",
                "DKD.DKDEM05.DKDJ050110",
                "DKD.DKDEM05.DKDJ050111",
                "DKD.DKDEM06.DKDJ060101",
                "DKD.DKDEM06.DKDJ060102",
                "DKD.DKDEM06.DKDJ060103",
                "DKD.DKDEM06.DKDJ060104",
                "DKD.DKDEM06.DKDJ060105",
                "DKD.DKDEM06.DKDJ060106",
                "DKD.DKDEM06.DKDJ060107",
                "DKD.DKDEM06.DKDJ060108",
                "DKD.DKDEM06.DKDJ060109",
                "DKD.DKDEM06.DKDJ060110",
                "DKD.DKDEM06.DKDJ060111",
                "DKD.DKDEM07.DKDJ070101",
                "DKD.DKDEM07.DKDJ070102",
                "DKD.DKDEM07.DKDJ070103",
                "DKD.DKDEM07.DKDJ070104",
                "DKD.DKDEM07.DKDJ070105",
                "DKD.DKDEM07.DKDJ070106",
                "DKD.DKDEM07.DKDJ070107",
                "DKD.DKDEM07.DKDJ070108",
                "DKD.DKDEM07.DKDJ070109",
                "DKD.DKDEM07.DKDJ070110",
                "DKD.DKDEM07.DKDJ070111",
                "DKD.DKDEM08.DKDJ080101",
                "DKD.DKDEM08.DKDJ080102",
                "DKD.DKDEM08.DKDJ080103",
                "DKD.DKDEM08.DKDJ080104",
                "DKD.DKDEM08.DKDJ080105",
                "DKD.DKDEM08.DKDJ080106",
                "DKD.DKDEM08.DKDJ080107",
                "DKD.DKDEM08.DKDJ080108",
                "DKD.DKDEM08.DKDJ080109",
                "DKD.DKDEM08.DKDJ080110",
                "DKD.DKDEM08.DKDJ080111",
                "DKD.DKDEM09.DKDJ090102",
                "DKD.DKDEM09.DKDJ090103",
                "DKD.DKDEM09.DKDJ090104",
                "DKD.DKDEM09.DKDJ090105",
                "DKD.DKDEM09.DKDJ090106",
                "DKD.DKDEM09.DKDJ090107",
                "DKD.DKDEM09.DKDJ090109",
                "DKD.DKDEM09.DKDJ090110",
                "DKD.DKDEM09.DKDJ090111",
                "DKD.DKDEM09.DKDJ090112",
                "DKD.DKDEM09.DKDJ090113",
                "DKD.DKDEM09.DKDJ090114",
                "DKD.DKDEM09.DKDJ090115",
                "DKD.DKDEM09.DKDJ090116",
                "DKD.DKDEM09.DKDJ090117",
                "DKD.DKDEM09.DKDJ090118",
                "DKD.DKDEM09.zwave01625CBE_131075",
                "DKD.DKDEM09.zwave01625CBE_131120",
                "DKD.DKDEM09.zwave01625CBE_131200",
                "DKD.DKDEM09.zwave01625CBE_131460",
                "DKD.DKDEM09.zwave01625CBE_196611",
                "DKD.DKDEM09.zwave01625CBE_196656",
                "DKD.DKDEM09.zwave01625CBE_196736",
                "DKD.DKDEM09.zwave01625CBE_262147",
                "DKD.DKDEM09.zwave01625CBE_262192",
                "DKD.DKDEM09.zwave01625CBE_262272",
                "DKD.DKDEM09.zwave01625CBE_262532",
                "DKD.DKDEM09.zwave01625CBE_327683",
                "DKD.DKDEM09.zwave01625CBE_327712",
                "DKD.DKDEM09.zwave01625CBE_327728",
                "DKD.DKDEM09.zwave01625CBE_327808",
                "DKD.DKDEM09.zwave01625CBE_328068",
                "DKD.DKDEM09.zwave01625CBE_33620017",
                "DKD.DKDEM09.zwave01625CBE_33685553",
                "DKD.DKDEM09.zwave01625CBE_33751089",
                "DKD.DKDEM09.zwave01625CBE_33816625",
                "DKD.DKDEM09.zwave01625CBE_33882161",
                "DKD.DKDEM09.zwave01625CBE_34013233",
                "DKD.DKDEM09.zwave01625CBE_393219",
                "DKD.DKDEM09.zwave01625CBE_393248",
                "DKD.DKDEM09.zwave01625CBE_393253",
                "DKD.DKDEM09.zwave01625CBE_393264",
                "DKD.DKDEM09.zwave01625CBE_393269",
                "DKD.DKDEM09.zwave01625CBE_393277",
                "DKD.DKDEM09.zwave01625CBE_393533",
                "DKD.DKDEM09.zwave01625CBE_393568",
                "DKD.DKDEM09.zwave01625CBE_393789",
                "DKD.DKDEM09.zwave01625CBE_393824",
                "DKD.DKDEM09.zwave01625CBE_394045",
                "DKD.DKDEM09.zwave01625CBE_394080",
                "DKD.DKDEM09.zwave01625CBE_394301",
                "DKD.DKDEM09.zwave01625CBE_394336",
                "DKD.DKDEM09.zwave01625CBE_394557",
                "DKD.DKDEM09.zwave01625CBE_394813",
                "DKD.DKDEM09.zwave01625CBE_395069",
                "DKD.DKDEM09.zwave01625CBE_458755",
                "DKD.DKDEM09.zwave01625CBE_458784",
                "DKD.DKDEM09.zwave01625CBE_458789",
                "DKD.DKDEM09.zwave01625CBE_458800",
                "DKD.DKDEM09.zwave01625CBE_458805",
                "DKD.DKDEM09.zwave01625CBE_458813",
                "DKD.DKDEM09.zwave01625CBE_459069",
                "DKD.DKDEM09.zwave01625CBE_459104",
                "DKD.DKDEM09.zwave01625CBE_459325",
                "DKD.DKDEM09.zwave01625CBE_459581",
                "DKD.DKDEM09.zwave01625CBE_459837",
                "DKD.DKDEM09.zwave01625CBE_460093",
                "DKD.DKDEM09.zwave01625CBE_460349",
                "DKD.DKDEM09.zwave01625CBE_460605",
                "DKD.DKDEM09.zwave01625CBE_50397233",
                "DKD.DKDEM09.zwave01625CBE_50462769",
                "DKD.DKDEM09.zwave01625CBE_50528305",
                "DKD.DKDEM09.zwave01625CBE_50593841",
                "DKD.DKDEM09.zwave01625CBE_50659377",
                "DKD.DKDEM09.zwave01625CBE_50790449",
                "DKD.DKDEM09.zwave01625CBE_524291",
                "DKD.DKDEM09.zwave01625CBE_524320",
                "DKD.DKDEM09.zwave01625CBE_524325",
                "DKD.DKDEM09.zwave01625CBE_524336",
                "DKD.DKDEM09.zwave01625CBE_524341",
                "DKD.DKDEM09.zwave01625CBE_524349",
                "DKD.DKDEM09.zwave01625CBE_524605",
                "DKD.DKDEM09.zwave01625CBE_524640",
                "DKD.DKDEM09.zwave01625CBE_524861",
                "DKD.DKDEM09.zwave01625CBE_524896",
                "DKD.DKDEM09.zwave01625CBE_525117",
                "DKD.DKDEM09.zwave01625CBE_525152",
                "DKD.DKDEM09.zwave01625CBE_525373",
                "DKD.DKDEM09.zwave01625CBE_525408",
                "DKD.DKDEM09.zwave01625CBE_525629",
                "DKD.DKDEM09.zwave01625CBE_525885",
                "DKD.DKDEM09.zwave01625CBE_526141",
                "DKD.DKDEM09.zwave01625CBE_65539",
                "DKD.DKDEM09.zwave01625CBE_65568",
                "DKD.DKDEM09.zwave01625CBE_65584",
                "DKD.DKDEM09.zwave01625CBE_65585",
                "DKD.DKDEM09.zwave01625CBE_65664",
                "DKD.DKDEM09.zwave01625CBE_65924",
                "DKD.DKDEM09.zwave01625CBE_67174449",
                "DKD.DKDEM09.zwave01625CBE_67239985",
                "DKD.DKDEM09.zwave01625CBE_67305521",
                "DKD.DKDEM09.zwave01625CBE_67371057",
                "DKD.DKDEM09.zwave01625CBE_67436593",
                "DKD.DKDEM09.zwave01625CBE_67567665",
                "NEST.NESTB01.NEST0101",
                "NEST.NESTB01.NEST0102",
                "NEST.NESTB01.NEST0103",
                "NEST.NESTB01.NEST0104",
                "NEST.NESTB01.NEST0105",
                "NEST.NESTB01.NEST0106",
                "NEST.NESTB01.NEST0107",
                "NEST.NESTB01.NEST0108",
                "NEST.NESTB01.NEST0109",
                "NEST.NESTB01.NEST0110",
                "NEST.NESTB01.NEST0111",
                "NEST.NESTB01.NEST0112",
                "NEST.NESTB01.NEST0113",
                "NEST.NESTB01.NEST0114",
                "NEST.NESTB01.NEST0115",
                "NEST.NESTB01.NEST0116",
                "NEST.NESTB01.NEST0117",
                "NEST.NESTB01.NEST0118",
                "NEST.NESTB01.NEST0119",
                "NEST.NESTB01.NEST0120",
                "NEST.NESTB01.NEST0121",
                "NEST.NESTB01.NEST0122",
                "NEST.NESTB01.NEST0123",
                "NEST.NESTB01.NEST0124",
                "NEST.NESTB01.NEST0125",
                "NEST.NESTB01.NEST0126",
                "NEST.NESTB01.NEST0127",
                "NEST.NESTB01.NEST0128",
                "NEST.NESTB01.NEST0129",
                "NEST.NESTB01.NEST0130",
                "NEST.NESTB01.NEST0131",
                "NEST.NESTB01.NEST0132",
                "NEST.NESTB01.NEST0133",
                "NEST.NESTB01.NEST0134",
                "NEST.NESTB01.NEST0135",
                "NEST.NESTB01.NEST0136",
                "NEST.NESTB01.NEST0141",
                "NEST.NESTB01.NEST0142",
                "UPC.UPCTC02.UPC02001",
                "UPC.UPCTC02.UPC02002",
                "UPC.UPCTC02.UPC02003",
                "UPC.UPCTC02.UPC02004",
                "UPC.UPCTC02.UPC02005",
                "UPC.UPCTC02.UPC02006"
);

}
