package com.tanaseb.paymentscheduler.infrastructure.mapper;

import com.tanaseb.paymentscheduler.domain.model.Payment;
import com.tanaseb.paymentscheduler.infrastructure.model.PaymentDto;
import com.tanaseb.paymentscheduler.infrastructure.util.DoubleUtils;
import lombok.RequiredArgsConstructor;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

    private final MonthlyPaymentMapper monthlyPaymentMapper;
    private final FrequencyMapper frequencyMapper;

    public PaymentDto toPaymentDto(Payment payment, String billingFrequency) {
        return PaymentDto.builder()
                .start(payment.getStart().toString(DateTimeFormat.shortDate()))
                .end(payment.getEnd().toString(DateTimeFormat.shortDate()))
                .billingFrequency(frequencyMapper.toFrequency(billingFrequency).name())
                .totalBilledNoOfDays(payment.getBilledNoOfDays())
                .totalBilledAmount(DoubleUtils.formatToFourDecimals(payment.getBilledAmount()))
                .monthlyPayments(monthlyPaymentMapper.toMonthlyPaymentDtos(
                        payment.getMonthlyPayments()))
                .build();
    }
}
