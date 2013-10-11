package org.everit.smssender.dummy.core.api.dto;

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

import java.util.Date;

import org.everit.smssender.api.MessageFormat;

/**
 * Information of the dummy SMS.
 */
public final class DummySMS implements Comparable<DummySMS> {
    /**
     * The country call code.
     */
    private final String countryCallCode;

    /**
     * The area cal code.
     */
    private final String areaCall;

    /**
     * The number of the subscriber.
     */
    private final String subscriberNumber;

    /**
     * The extension number.
     */
    private final String extensionNumber;

    /**
     * The message.
     */
    private final String message;

    /**
     * The message format.
     */
    private final MessageFormat messageFormat;

    /**
     * Synchron or asynchron.
     */
    private final boolean synchron;

    /**
     * The date when sent the SMS.
     */
    private final Date sendDate;

    /**
     * The simple constructor.
     * 
     * @param countryCallCode
     *            the country call code.
     * @param areaCall
     *            the area code.
     * @param subscriberNumber
     *            the number of the subscriber.
     * @param extensionNumber
     *            the number of the extension.
     * @param message
     *            the message.
     * @param messageFormat
     *            the message format.
     * @param synchron
     *            synchron or not.
     * @param sendDate
     *            the date when sent the SMS.
     */
    public DummySMS(final String countryCallCode, final String areaCall, final String subscriberNumber,
            final String extensionNumber,
            final String message, final MessageFormat messageFormat, final boolean synchron, final Date sendDate) {
        this.countryCallCode = countryCallCode;
        this.areaCall = areaCall;
        this.subscriberNumber = subscriberNumber;
        this.extensionNumber = extensionNumber;
        this.message = message;
        this.messageFormat = messageFormat;
        this.synchron = synchron;
        if (sendDate != null) {
            this.sendDate = (Date) sendDate.clone();
        } else {
            this.sendDate = null;
        }
    }

    @Override
    public int compareTo(final DummySMS o) {
        if ((o == null)) {
            return 1;
        }
        DummySMS obj = o;
        return sendDate.compareTo(obj.getSendDate());
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof DummySMS)) {
            return false;
        }
        DummySMS dummyObj = (DummySMS) obj;

        if ((dummyObj.getAreaCall().equals(getAreaCall()))
                && (dummyObj.getCountryCallCode().equals(getCountryCallCode()))
                && (((dummyObj.getExtensionNumber() != null) && (getExtensionNumber() != null)
                && dummyObj.getExtensionNumber().equals(getExtensionNumber()))
                || ((getExtensionNumber() == null) && (dummyObj.getExtensionNumber() == null)))
                && (dummyObj.getMessage().equals(getMessage()))
                && (dummyObj.getSubscriberNumber().equals(getSubscriberNumber()))
                && (dummyObj.getMessageFormat() == getMessageFormat())
                && (dummyObj.isSynchron() == isSynchron())) {
            return true;
        }
        return false;
    }

    public String getAreaCall() {
        return areaCall;
    }

    public String getCountryCallCode() {
        return countryCallCode;
    }

    public String getExtensionNumber() {
        return extensionNumber;
    }

    public String getMessage() {
        return message;
    }

    public MessageFormat getMessageFormat() {
        return messageFormat;
    }

    /**
     * Get the sent date.
     * 
     * @return the sendDate.
     */
    public Date getSendDate() {
        if (sendDate != null) {
            return (Date) sendDate.clone();
        }
        return null;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public boolean isSynchron() {
        return synchron;
    }

    @Override
    public String toString() {
        String result = "countryCallCode: " + countryCallCode
                + "\nareaCall: " + areaCall
                + "\nsubscriber: " + subscriberNumber
                + "\nextension: " + extensionNumber
                + "\nmessage: " + message
                + "\nmessageformat: " + messageFormat
                + "\nsendDate: " + sendDate;

        return result;
    }
}
