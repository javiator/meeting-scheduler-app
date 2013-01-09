package com.apps.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DomainEqualHashcodeTest {

    private static <T> void testEqualsCommon(Class<T> type) {
        EqualsVerifier.forClass(type).suppress(Warning.NONFINAL_FIELDS).usingGetClass().verify();
    }

    @Test
    public void testMeetingRequest() {
        testEqualsCommon(MeetingRequest.class);
        testEqualsCommon(OfficeTiming.class);
    }
}
