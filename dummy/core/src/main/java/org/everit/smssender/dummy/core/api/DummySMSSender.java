package org.everit.smssender.dummy.core.api;

/*
 * Copyright (c) 2011, Everit Kft.
 *
 * All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

import java.util.List;

import org.everit.smssender.api.SMSSender;
import org.everit.smssender.dummy.core.api.dto.DummySMS;

/**
 * Service for the dummy SMS sender.
 */
public interface DummySMSSender extends SMSSender {

    /**
     * Get all DummySMSs and return sorted list. The first element the first message, and the last list element the last
     * SMS.
     * 
     * @return the all DummySMSes in list. If no one return empty list.
     */
    List<DummySMS> getDummySMSs();

    /**
     * Get all DummySMSs which corresponds to the parameters.
     * 
     * @param countryCallCode
     *            the country call code.
     * @param areaCall
     *            the area code.
     * @param subscriberNumber
     *            the number of the subscriber.
     * @param extension
     *            the number of the extension.
     * @return the DummySMSs which corresponds to the parameters. If no one return empty list.
     */
    List<DummySMS> getDummySMSs(final String countryCallCode, final String areaCall,
            final String subscriberNumber, final String extension);

    /**
     * Get latest DummySMS.
     * 
     * @return the latest dummy SMS. If no one return <code>null</code>.
     */
    DummySMS getLatestDummySMS();

    /**
     * Get latest DummySMS which corresponds to the parameters.
     * 
     * @param countryCallCode
     *            the country call code.
     * @param areaCall
     *            the area code.
     * @param subscriberNumber
     *            the number of the subscriber.
     * @param extension
     *            the number of the extension.
     * @return the latest dummy SMS which corresponds to the parameters. If no one return <code>null</code>.
     */
    DummySMS getLatestDummySMS(final String countryCallCode, final String areaCall,
            final String subscriberNumber, final String extension);
}
