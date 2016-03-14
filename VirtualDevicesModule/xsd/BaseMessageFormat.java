/**
BaseMessageFormat.java by Michele Albano - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package eu.encourage_project;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.SAXException;

/*
 howto:
 put the java/bin directory into the path
 from cmd line, do "xjc.exe midd.xsd" to generate many java files
 do "javac en/encourage_project/*.java", since the stupid javac forgets to compile some important files if you work directly on this file
 do "javac jaxbtry.java"
 do "java jaxbtry"
 */


public abstract class BaseMessageFormat {




public static String dump(Object o, int callCount, List excludeList) {

//add this object to the exclude list to avoid circual references in the future
	if (excludeList == null) excludeList = new java.util.ArrayList();
	if (excludeList.contains(o)) {
		return("circular reference");
    }
	excludeList.add(o);

    callCount++;
//	if (callCount>6) return "limit";
    StringBuffer tabs = new StringBuffer();
    for (int k = 0; k < callCount; k++) tabs.append("  ");

//	if (null == o) return "null";
    StringBuffer buffer = new StringBuffer();
    Class oClass = o.getClass();
    if (oClass.isArray()) {
        buffer.append("\n");
        buffer.append(tabs.toString());
        buffer.append("[");
        for (int i = 0; i < Array.getLength(o); i++) {
            if (i < 0)
                buffer.append(",");
            Object value = Array.get(o, i);
            if (null == value ||
			value.getClass().isPrimitive() ||
                    value.getClass() == java.lang.Long.class ||
                    value.getClass() == java.lang.String.class ||
                    value.getClass() == java.lang.Integer.class ||
                    value.getClass() == java.lang.Boolean.class
                    ) {
                buffer.append(value);
            } else {
                buffer.append(dump(value, callCount, excludeList));
            }
        }
        buffer.append(tabs.toString());
        buffer.append("]\n");
    } else if (o instanceof List) {
		java.util.List lo = (List) o;
        buffer.append("\n");
        buffer.append(tabs.toString());
        buffer.append("[");
        for (int i = 0; i < lo.size(); i++) {
            if (i < 0)
                buffer.append(",");
            Object value = lo.get(i);
            if (null == value ||
			value.getClass().isPrimitive() ||
                    value.getClass() == java.lang.Long.class ||
                    value.getClass() == java.lang.String.class ||
                    value.getClass() == java.lang.Integer.class ||
                    value.getClass() == java.lang.Boolean.class
                    ) {
                buffer.append(value);
            } else {
                buffer.append(dump(value, callCount, excludeList));
            }
        }
        buffer.append(tabs.toString());
        buffer.append("]\n");
	} else {
        buffer.append("\n");
        buffer.append(tabs.toString());
        buffer.append("{\n");
        while (oClass != null) {
            Field[] fields = oClass.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                buffer.append(tabs.toString());
                fields[i].setAccessible(true);
                buffer.append(fields[i].getName());
                buffer.append("=");
                try {
                    Object value = fields[i].get(o);
                    if (value != null) {
                        if (value.getClass().isPrimitive() ||
                                value.getClass() == java.lang.Long.class ||
                                value.getClass() == java.lang.String.class ||
                                value.getClass() == java.lang.Integer.class ||
                                value.getClass() == java.lang.Boolean.class
                                ) {
                            buffer.append(value);
                        } else if (
							("timeStamp" == fields[i].getName()) ||
							("createdDateTime" == fields[i].getName())
							) {
							buffer.append(value.toString());
                        } else {
                           buffer.append(dump(value, callCount, excludeList));
                        }
                    }
                } catch (IllegalAccessException e) {
                    buffer.append(e.getMessage());
                }
                buffer.append("\n");
            }
            oClass = oClass.getSuperclass();
        }
        buffer.append(tabs.toString());
        buffer.append("}\n");
    }
    return buffer.toString();
}






	protected Marshaller m=null;
	protected Unmarshaller u=null;
	protected JAXBContext jc = null;
	protected String namespace = null;
	protected String filenamexsd = null;
	protected Class xmlrootclass = null;

	protected BaseMessageFormat() {
	}

	protected boolean Init(Class rc) {
		xmlrootclass = rc;
		String sn = xmlrootclass.getSimpleName();
		namespace = "http://www.encourage-project.eu/"+sn+"#";
		filenamexsd = "xsd\\"+sn+".xsd";

		try {
			jc = JAXBContext.newInstance(xmlrootclass.getPackage().getName());
			u = jc.createUnmarshaller();

			javax.xml.validation.SchemaFactory factory = javax.xml.validation.SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			javax.xml.transform.Source schemaFile = new javax.xml.transform.stream.StreamSource(new java.io.File(filenamexsd));
			javax.xml.validation.Schema schema = factory.newSchema(schemaFile);
			u.setSchema(schema);

			m = jc.createMarshaller();
			m.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		}
		catch (PropertyException e) {e.printStackTrace();return false;}
		catch (JAXBException e) {e.printStackTrace();return false;}
		catch (SAXException e) {e.printStackTrace();return false;}

		return true;
	}

	public Object Unmarshal(String xmldata) throws JAXBException {
		StringReader xmlReader = new StringReader( xmldata );
		JAXBElement o=null;
//		try {
		o = (JAXBElement)u.unmarshal(xmlReader);
//		} catch (JAXBException e) {e.printStackTrace();return null;}

//	ALBANO: is this faster?	Object o = u.unmarshal( new StreamSource( new StringReader( xmlStr.toString() ) ) );

		return o.getValue();
	}

	public String Marshal(Object mr) {
        final StringWriter stringWriter = new StringWriter();
		try {
			m.marshal(new JAXBElement(
				new javax.xml.namespace.QName(namespace, xmlrootclass.getSimpleName()),
				xmlrootclass, mr), stringWriter);
		}
		catch (JAXBException e) {e.printStackTrace();return null;}
		return stringWriter.toString();
	}

	public static String ReadFileIntoString(String filename) {
        StringBuffer fileData = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			char[] buf = new char[1024];
			int numRead=0;
			while((numRead=reader.read(buf)) != -1){
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
			}
			reader.close();
		}
		catch (FileNotFoundException e) {e.printStackTrace();return null;}
		catch (IOException e) {e.printStackTrace();return null;}

		return fileData.toString();
	}
}

