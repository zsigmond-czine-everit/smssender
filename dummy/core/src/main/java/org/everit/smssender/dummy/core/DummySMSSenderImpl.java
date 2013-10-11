package org.everit.smssender.dummy.core;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.everit.smssender.api.MessageFormat;
import org.everit.smssender.dummy.core.api.DummySMSSender;
import org.everit.smssender.dummy.core.api.dto.DummySMS;

/**
 * Implementation of the {@link DummySMSSender}.
 */
public class DummySMSSenderImpl implements DummySMSSender {

    /**
     * The maximum stored SMS.
     */
    private int maxStoredSMS = 100;

    /**
     * The list which contains all dummy SMSs.
     */
    private static List<DummySMS> dummySMSs = new ArrayList<DummySMS>();

    /**
     * The {@link ReentrantReadWriteLock} instance.
     */
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * The Lock which use the read.
     */
    private final Lock readLock = readWriteLock.readLock();

    /**
     * The Lock which use the write.
     */
    private final Lock writeLock = readWriteLock.writeLock();

    @Override
    public List<DummySMS> getDummySMSs() {
        readLock.lock();
        try {
            Collections.sort(dummySMSs);
            return dummySMSs;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<DummySMS> getDummySMSs(final String countryCallCode, final String areaCall,
            final String subscriberNumber,
            final String extension) {
        if ((countryCallCode == null) || (areaCall == null) || (subscriberNumber == null)) {
            throw new IllegalArgumentException("The countryCallCode or areaCall or "
                    + "subscriberNumber parameter is null. Cannot be null.");
        }
        readLock.lock();
        try {
            List<DummySMS> result = new ArrayList<DummySMS>();
            for (DummySMS ds : dummySMSs) {
                if (ds.getCountryCallCode().equals(countryCallCode)
                        && ds.getAreaCall().equals(areaCall)
                        && ds.getSubscriberNumber().equals(subscriberNumber)
                        && (ds.getExtensionNumber() != null)
                        && (((ds.getExtensionNumber() != null) && (extension != null) && (ds.getExtensionNumber()
                                .equals(extension))) || ((ds.getExtensionNumber() == null) && (extension == null)))) {
                    result.add(ds);
                }
            }
            return result;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public DummySMS getLatestDummySMS() {
        readLock.lock();
        try {
            DummySMS result = null;
            if (dummySMSs.size() > 0) {
                Collections.sort(dummySMSs);
                result = dummySMSs.get(dummySMSs.size() - 1);
            }
            return result;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public DummySMS getLatestDummySMS(final String countryCallCode, final String areaCall,
            final String subscriberNumber, final String extension) {
        if ((countryCallCode == null) || (areaCall == null) || (subscriberNumber == null)) {
            throw new IllegalArgumentException(
                    "The countryCallCode or areaCall or subscriberNumber parameter is null. Cannot be null.");
        }
        readLock.lock();
        try {
            List<DummySMS> correctSMSes = new ArrayList<DummySMS>();
            for (DummySMS ds : dummySMSs) {
                if (ds.getCountryCallCode().equals(countryCallCode)
                        && ds.getAreaCall().equals(areaCall)
                        && ds.getSubscriberNumber().equals(subscriberNumber)
                        && (((ds.getExtensionNumber() != null) && (extension != null) && (ds.getExtensionNumber()
                                .equals(extension))) || ((ds.getExtensionNumber() == null) && (extension == null)))) {
                    correctSMSes.add(ds);
                }
            }
            if (correctSMSes.size() > 0) {
                Collections.sort(correctSMSes);
                return correctSMSes.get(correctSMSes.size() - 1);
            }
            return null;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void sendMessage(final String countryCallCode, final String areaCall,
            final String subscriber,
            final String extension,
            final String message,
            final MessageFormat messageFormat,
            final boolean synchron) {
        if ((countryCallCode == null) || (areaCall == null) || (subscriber == null)
                || (message == null) || (messageFormat == null)) {
            throw new IllegalArgumentException("The countryCallCode or areaCall or subscriberNumber or "
                    + "message or messageFormat parameter is null. Cannot be null.");
        }
        writeLock.lock();
        try {
            if (dummySMSs.size() >= maxStoredSMS) {
                dummySMSs.remove(0);
            }
            dummySMSs.add(new DummySMS(countryCallCode,
                    areaCall, subscriber, extension,
                    message, messageFormat, synchron,
                    new Date()));
        } finally {
            writeLock.unlock();
        }
    }

    public void setMaxStoredSMS(final int maxStoredSMS) {
        this.maxStoredSMS = maxStoredSMS;
    }

}
