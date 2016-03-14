/**
HANGateway.java by César Teixeira - CISTER/INESC-TEC, ISEP, Polytechnic Institute of Porto
This work was supported by National Funds through FCT (Portuguese Foundation for Science and Technology) and
by the EU ARTEMIS JU funding, within ENCOURAGE project, ref. ARTEMIS/0002/2010, JU grant nr. 269354.
*/

package cister.rabbitmq.library;

public class Gateway
{
    /*
     * Constants
     */

    /*
     * Instance variables
     */
    private String gateway_desc;
    private String gateway_id;
    private String gateway_macadr;
    private String manufacturer_id;
    private String middleware_plugin_id;

    /*
     * Constructors
     */
    public Gateway()
    {
        super();
        this.gateway_desc = "";
        this.gateway_id = "";
        this.gateway_macadr = "";
        this.manufacturer_id = "";
        this.middleware_plugin_id = "";
    }
    public Gateway(String gateway_desc, String gateway_id, String gateway_macadr, String manufacturer_id, String middleware_plugin_id) {
        this.gateway_desc = gateway_desc;
        this.gateway_id = gateway_id;
        this.gateway_macadr = gateway_macadr;
        this.manufacturer_id = manufacturer_id;
        this.middleware_plugin_id = middleware_plugin_id;
    }
    
    /*
     * Public methods
     */
    public String getGatewayDesc() {
        return gateway_desc;
    }

    public void setGatewayDesc(String gateway_desc) {
        this.gateway_desc = gateway_desc;
    }

    public String getGatewayId() {
        return gateway_id;
    }

    public void setGatewayId(String gateway_id) {
        this.gateway_id = gateway_id;
    }

    public String getGatewayMacadr() {
        return gateway_macadr;
    }

    public void setGatewayMacadr(String gateway_macadr) {
        this.gateway_macadr = gateway_macadr;
    }

    public String getManufacturerId() {
        return manufacturer_id;
    }

    public void setManufacturerId(String manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public String getMiddlewarePluginId() {
        return middleware_plugin_id;
    }

    public void setMiddlewarePluginId(String middleware_plugin_id) {
        this.middleware_plugin_id = middleware_plugin_id;
    }
    
    /*
     * Private methods
     */
}
