package com.tanaseb.paymentscheduler.infrastructure.mapper;

import com.tanaseb.paymentscheduler.domain.model.Frequency;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class FrequencyMapperTest {

    @InjectMocks
    private FrequencyMapper frequencyMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toFrequencyWhenInputValid() {
        Frequency frequency = frequencyMapper.toFrequency("monthly");

        assertEquals(Frequency.MONTHLY, frequency);
    }

    @Test(expected = IllegalArgumentException.class)
    public void toFrequencyWhenInputNotValid() {
        frequencyMapper.toFrequency("daily");
    }
}