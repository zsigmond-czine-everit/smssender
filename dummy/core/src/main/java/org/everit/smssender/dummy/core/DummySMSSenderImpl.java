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

import org.everit.smssender.api.enums.MessageFormat;
import org.everit.smssender.dummy.core.api.DummySMSSender;
import org.everit.smssender.dummy.core.api.dto.DummySMS;

/**
 * Implementation of the {@link DummySMSSender}.
 */
public class DummySMSSenderImpl implements DummySMSSender {

    /**
     * The list which contains all dummy SMSs.
     */
    private static List<DummySMS> dummySMSs = new ArrayList<DummySMS>();

    @Override
    public List<DummySMS> getDummySMSs() {
        Collections.sort(dummySMSs);
        return dummySMSs;
    }

    @Override
    public List<DummySMS> getDummySMSs(final String countryCallCode, final String areaCall,
            final String subscriberNumber,
            final String extension) {
        List<DummySMS> result = new ArrayList<DummySMS>();
        for (DummySMS ds : dummySMSs) {
            if (ds.getCountryCallCode().equals(countryCallCode)
                    && ds.getAreaCall().equals(areaCall)
                    && ds.getSubscriberNumber().equals(subscriberNumber)
                    && ds.getExtensionNumber().equals(extension)) {
                result.add(ds);
            }
        }
        return result;
    }

    @Override
    public DummySMS getLatestDummySMS() {
        if (dummySMSs.size() > 0) {
            Collections.sort(dummySMSs);
            return dummySMSs.get(dummySMSs.size() - 1);
        }
        return null;
    }

    @Override
    public DummySMS getLatestDummySMS(final String countryCallCode, final String areaCall,
            final String subscriberNumber, final String extension) {
        List<DummySMS> correctSMSes = new ArrayList<DummySMS>();
        for (DummySMS ds : dummySMSs) {
            if (ds.getCountryCallCode().equals(countryCallCode)
                    && ds.getAreaCall().equals(areaCall)
                    && ds.getSubscriberNumber().equals(subscriberNumber)
                    && ds.getExtensionNumber().equals(extension)) {
                correctSMSes.add(ds);
            }
        }
        if (correctSMSes.size() > 0) {
            Collections.sort(correctSMSes);
            return correctSMSes.get(correctSMSes.size() - 1);
        }
        return null;
    }

    @Override
    public void sendMessage(final String countryCallCode, final String areaCall,
            final String subscriber,
            final String extension,
            final String message,
            final MessageFormat messageFormat,
            final boolean synchron) {
        dummySMSs.add(new DummySMS(countryCallCode,
                areaCall, subscriber, extension,
                message, messageFormat, synchron,
                new Date()));
    }

}
