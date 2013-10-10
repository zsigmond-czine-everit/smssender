package org.everit.smssender.dummy.tests;

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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.everit.smssender.api.enums.MessageFormat;
import org.everit.smssender.dummy.core.api.DummySMSSender;
import org.everit.smssender.dummy.core.api.dto.DummySMS;

/**
 * Implementation of {@link DummySMSSenderTest}.
 */
public class DummySMSSenderTestImpl implements DummySMSSenderTest {

    /**
     * The test datas.
     */
    private static final List<DummySMS> NOT_SEND_DUMMY_MESSAGES = Arrays
            .asList(
                    new DummySMS("06", "11", "454876432158", "", "TEST MESSAGE", MessageFormat.UNICODE, false,
                            null),
                    new DummySMS("06", "70", "454876432158", "", "TEST MESSAGE", MessageFormat.BINARY, true,
                            null),
                    new DummySMS("06", "11", "454876432158", "", "TEST MESSAGE", MessageFormat.UNICODE, true,
                            null),
                    new DummySMS("06", "11", "454876432158", "", "TEST MESSAGE", MessageFormat.BINARY, false,
                            null),
                    new DummySMS("06", "11", "454876432158", "", "NEW TEST MESSAGE", MessageFormat.UNICODE, false,
                            null),
                    new DummySMS("06", "11", "454876432158", "254", "TEST MESSAGE", MessageFormat.UNICODE, false,
                            null),
                    new DummySMS("06", "11", "4548764321581", "", "TEST MESSAGE", MessageFormat.UNICODE, false,
                            null),
                    new DummySMS("06", "111", "454876432158", "", "TEST MESSAGE", MessageFormat.UNICODE, false,
                            null),
                    new DummySMS("1234", "700", "454876432158", "", "TEST MESSAGE", MessageFormat.UNICODE, true,
                            null),
                    new DummySMS("06", "72340", "454876432158", "", "TEST MESSAGE", MessageFormat.UNICODE, false,
                            null),
                    new DummySMS("234", "10", "454876432158", "", "TEST MESSAGE", MessageFormat.GSM_03_38, false,
                            null),
                    new DummySMS("123", "450", "454876432158", "", "TEST MESSAGE", MessageFormat.UNICODE, true,
                            null),
                    new DummySMS("06", "234", "454876432158", "", "TEST MESSAGE", MessageFormat.BINARY, true,
                            null),
                    new DummySMS("06", "70", "454876432158", "", "TEST MESSAGE", MessageFormat.UNICODE, false,
                            null),
                    new DummySMS("06", "820", "454876432158", "", "TEST MESSAGE", MessageFormat.UNICODE, false,
                            null),
                    new DummySMS("23", "4", "454876432158", "", "TEST MESSAGE", MessageFormat.UNICODE, true,
                            null),
                    new DummySMS("06", "42", "454876432158", "", "TEST MESSAGE", MessageFormat.BINARY, false,
                            null),
                    new DummySMS("06", "243", "454876432158", "", "TEST MESSAGE", MessageFormat.UNICODE, true,
                            null),
                    new DummySMS("0786", "30", "454876432158", "", "TEST MESSAGE", MessageFormat.GSM_03_38, true,
                            null),
                    new DummySMS("56", "35", "454876432158", "", "TEST MESSAGE", MessageFormat.GSM_03_38, true,
                            null),
                    new DummySMS("43", "476", "454876432158", "", "TEST MESSAGE", MessageFormat.UNICODE, true,
                            null),
                    new DummySMS("32", "312", "454876432158", "", "TEST MESSAGE", MessageFormat.GSM_03_38, true,
                            null),
                    new DummySMS("64", "1", "454876432158", "", "TEST MESSAGE", MessageFormat.UNICODE, true,
                            null),
                    new DummySMS("90123", "87", "454876432158", "", "TEST MESSAGE", MessageFormat.UNICODE, true,
                            null),
                    new DummySMS("65", "78", "454876432158", "", "TEST MESSAGE", MessageFormat.GSM_03_38, true,
                            null),
                    new DummySMS("32", "78", "454876432158", "", "TEST MESSAGE", MessageFormat.BINARY, false,
                            null));

    /**
     * The {@link DummySMSSender} instance.
     */
    private DummySMSSender dummySMSSender;

    public void setDummySMSSender(final DummySMSSender dummySMSSender) {
        this.dummySMSSender = dummySMSSender;
    }

    @Override
    public void testComplex() {
        Date actualDate = new Date();
        DummySMS tmpSMS = NOT_SEND_DUMMY_MESSAGES.get(0);
        List<DummySMS> dummySMSes = dummySMSSender.getDummySMSs();
        Assert.assertTrue(dummySMSes.isEmpty());

        Date sendDate = tmpSMS.getSendDate();
        Assert.assertNull(sendDate);

        dummySMSes = dummySMSSender.getDummySMSs(tmpSMS.getCountryCallCode(), tmpSMS.getAreaCall(),
                tmpSMS.getSubscriberNumber(), tmpSMS.getExtensionNumber());
        Assert.assertTrue(dummySMSes.isEmpty());

        DummySMS lastDummySMSes = dummySMSSender.getLatestDummySMS();
        Assert.assertNull(lastDummySMSes);

        lastDummySMSes = dummySMSSender.getLatestDummySMS(tmpSMS.getCountryCallCode(), tmpSMS.getAreaCall(),
                tmpSMS.getSubscriberNumber(), tmpSMS.getExtensionNumber());
        Assert.assertNull(lastDummySMSes);
        int sendMessagesNumber = 0;
        for (DummySMS ds : NOT_SEND_DUMMY_MESSAGES) {

            dummySMSSender.sendMessage(ds.getCountryCallCode(), ds.getAreaCall(), ds.getSubscriberNumber(),
                    ds.getExtensionNumber(), ds.getMessage(), ds.getMessageFormat(), ds.isSynchron());
            sendMessagesNumber++;

            dummySMSes = dummySMSSender.getDummySMSs();
            Assert.assertFalse(dummySMSes.isEmpty());
            Assert.assertEquals(sendMessagesNumber, dummySMSes.size());

            DummySMS lastDummySMS1 = dummySMSSender.getLatestDummySMS();
            Assert.assertNotNull(lastDummySMS1);
            Assert.assertEquals(ds.getCountryCallCode(), lastDummySMS1.getCountryCallCode());
            Assert.assertEquals(ds.getAreaCall(), lastDummySMS1.getAreaCall());
            Assert.assertEquals(ds.getSubscriberNumber(), lastDummySMS1.getSubscriberNumber());
            Assert.assertEquals(ds.getExtensionNumber(), lastDummySMS1.getExtensionNumber());
            Assert.assertEquals(ds.getMessage(), lastDummySMS1.getMessage());
            Assert.assertEquals(ds.getMessageFormat(), lastDummySMS1.getMessageFormat());
            Assert.assertEquals(ds.isSynchron(), lastDummySMS1.isSynchron());
            Assert.assertTrue(actualDate.getTime() < lastDummySMS1.getSendDate().getTime());

            lastDummySMSes = dummySMSSender.getLatestDummySMS(ds.getCountryCallCode(), ds.getAreaCall(),
                    ds.getSubscriberNumber(), ds.getExtensionNumber());
            Assert.assertNotNull(lastDummySMSes);
            Assert.assertEquals(lastDummySMS1.toString(), lastDummySMSes.toString());
            Assert.assertEquals(lastDummySMS1, lastDummySMSes);
        }

        dummySMSes = dummySMSSender.getDummySMSs(tmpSMS.getCountryCallCode(), tmpSMS.getAreaCall(),
                tmpSMS.getSubscriberNumber(), tmpSMS.getExtensionNumber());
        Assert.assertFalse(dummySMSes.isEmpty());
        Assert.assertEquals(4, dummySMSes.size());

        Assert.assertFalse(tmpSMS.equals(null));
        Assert.assertFalse(tmpSMS.equals("string"));
        Assert.assertFalse(tmpSMS.equals(NOT_SEND_DUMMY_MESSAGES.get(1)));

        dummySMSes = dummySMSSender.getDummySMSs();
        int equals = 0;
        for (DummySMS nsd : dummySMSes) {
            for (DummySMS sd : dummySMSes) {
                if (nsd.equals(sd)) {
                    equals++;
                }
            }
        }
        Assert.assertEquals(equals, dummySMSes.size());

        Assert.assertTrue(dummySMSes.get(0).getSendDate().getTime() < dummySMSes.get(dummySMSes.size() - 1)
                .getSendDate().getTime());
    }
}
