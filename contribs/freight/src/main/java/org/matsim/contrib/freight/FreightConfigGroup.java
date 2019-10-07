/* *********************************************************************** *
 * project: org.matsim.*
 * Controler.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2007 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */

package org.matsim.contrib.freight;

import org.matsim.core.config.ConfigGroup;
import org.matsim.core.config.ReflectiveConfigGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.net.URL;
import java.util.Map;

public class FreightConfigGroup extends ReflectiveConfigGroup {

    public static final String GROUPNAME="freight" ;

    @NotBlank
    private String carriersFile;
    public static final String CARRIERSFILEDE = "carriersFile";
    private static final String CARRIERSFILEDESC = "Freight Carriers File, according to MATSim freight contrib";

    @NotBlank
    private String carriersVehicleTypesFile;
    public static final String CARRIERSVEHICLETYPED = "carriersVehicleTypeFile";
    private static final String CARRIERSVEHICLETYPEDESC = "Carrier Vehicle Types file, according to MATSim freight contrib";

    @Positive
    private int jSpritTimeSliceWidth = 1800;
    public static final String JSPRITTIMESLICEWIDTH = "jSpritTimeSliceWidth";
    private static final String JSPRITTIMESLICEWIDTHDESC = "time slice width used in JSprit in seconds." +
            " The smaller the value, the more precise the calculation of routing costs but the longer the computation time." +
            " Default value is 1800 seconds.";

    /**
     * Creates an instance which will crash if an unknown parameter name
     * is given.
     *
     * @param name the name of the module in the config file.
     */
    public FreightConfigGroup(String name) {
        super(name);
    }

    /**
     * @return -- {@value #CARRIERSFILEDESC}
     */
//    @StringGetter(CARRIERSFILEDE)
    public String getCarriersFile() {
        return carriersFile;
    }

    URL getCarriersFileUrl(URL context) {
        return ConfigGroup.getInputFileURL(context, this.carriersFile);
    }

    /**
     * @param -- {@value #CARRIERSFILEDESC}
     */
//    @StringSetter(CARRIERSFILEDE)
    public void setCarriersFile(String carriersFile) {
        this.carriersFile = carriersFile;
    }

    /**
     * @return -- {@value #CARRIERSVEHICLETYPEDESC}
     */
//    @StringGetter(CARRIERSVEHICLETYPED)
    public String getCarriersVehicleTypesFile() {
        return carriersVehicleTypesFile;
    }

    URL getCarriersVehicleTypesFileUrl(URL context) {
        return ConfigGroup.getInputFileURL(context, this.carriersVehicleTypesFile);
    }


    /**
     * @param -- {@value #CARRIERSVEHICLETYPEDESC}
     */
//    @StringSetter(CARRIERSVEHICLETYPED)
    public void setCarriersVehicleTypesFile(String carriersVehicleTypesFile) {
        this.carriersVehicleTypesFile = carriersVehicleTypesFile;
    }


    /**
     * @return jspritTimeSliceWidth --{@value #JSPRITTIMESLICEWIDTHDESC}
     */
//    @StringGetter(JSPRITTIMESLICEWIDTH)
    int getJspritTimeSliceWidth() {
        return jSpritTimeSliceWidth;
    }

    /**
     * @param jspritTimeSliceWidth --{@value #JSPRITTIMESLICEWIDTHDESC}
     */
//    @StringSetter(JSPRITTIMESLICEWIDTH)
    public void setjSpritTimeSliceWidth(int jspritTimeSliceWidth) {
        this.jSpritTimeSliceWidth = jspritTimeSliceWidth;
    }



    @Override
    public Map<String, String> getComments() {
        Map<String, String> map = super.getComments();
        map.put(CARRIERSFILEDE, CARRIERSFILEDESC);
        map.put(CARRIERSVEHICLETYPED, CARRIERSVEHICLETYPEDESC);
        map.put(JSPRITTIMESLICEWIDTH,JSPRITTIMESLICEWIDTHDESC);
        return map;
    }

}
