package de.cryfe.api.apis;

import de.cryfe.api.data.Data;

public class DataAPI {

    public DataAPI() {}

    public String getPrefix() { return Data.getPrefix(); }

    public void setPrefix(String arg0) { Data.setPrefix(arg0); }
}
