//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.17 at 05:26:22 PM GMT 
//


package encourager.generated.cim_assignedidentity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CIM_AssignedIdentity_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CIM_AssignedIdentity_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.encourage-project.eu/CIM_AssignedIdentity#}IdentityInfo"/>
 *         &lt;element ref="{http://www.encourage-project.eu/CIM_AssignedIdentity#}ManagedElement"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CIM_AssignedIdentity_Type", propOrder = {
    "identityInfo",
    "managedElement"
})
public class CIM_AssignedIdentity {

    @XmlElement(name = "IdentityInfo", required = true)
    protected String identityInfo;
    @XmlElement(name = "ManagedElement", required = true)
    protected String managedElement;

    /**
     * Gets the value of the identityInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentityInfo() {
        return identityInfo;
    }

    /**
     * Sets the value of the identityInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentityInfo(String value) {
        this.identityInfo = value;
    }

    /**
     * Gets the value of the managedElement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManagedElement() {
        return managedElement;
    }

    /**
     * Sets the value of the managedElement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManagedElement(String value) {
        this.managedElement = value;
    }

}