package org.everit.smssender.api;

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

/**
 * Service interface for the SMSSender service.
 */
public interface SMSSender {

    /**
     * Send messages to the phone number.
     * 
     * @param countryCallCode
     *            the country call code. Cannot be <code>null</code>.
     * @param areaCall
     *            the area call code. Cannot be <code>null</code>.
     * @param subscriber
     *            the number of the subscriber. Cannot be <code>null</code>.
     * @param extension
     *            the extension number.
     * @param message
     *            the message. Cannot be <code>null</code>.
     * @param messageFormat
     *            the message format. Cannot be <code>null</code>.
     * @param synchron
     *            Synchron or not.
     * 
     * @throws IllegalArgumentException
     *             if the countryCallCode or areaCall or subscriberNumber or message or messageFormat parameter is null
     */
    void sendMessage(final String countryCallCode, final String areaCall,
            final String subscriber,
            final String extension,
            final String message,
            MessageFormat messageFormat,
            boolean synchron);

}
