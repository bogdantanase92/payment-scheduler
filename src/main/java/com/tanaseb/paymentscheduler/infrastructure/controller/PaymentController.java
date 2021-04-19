package com.tanaseb.paymentscheduler.infrastructure.controller;

import com.tanaseb.paymentscheduler.domain.model.Payment;
import com.tanaseb.paymentscheduler.domain.model.Period;
import com.tanaseb.paymentscheduler.domain.model.Rate;
import com.tanaseb.paymentscheduler.domain.service.PaymentService;
import com.tanaseb.paymentscheduler.infrastructure.mapper.PaymentMapper;
import com.tanaseb.paymentscheduler.infrastructure.mapper.PeriodMapper;
import com.tanaseb.paymentscheduler.infrastructure.mapper.RateMapper;
import com.tanaseb.paymentscheduler.infrastructure.model.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;
    private final RateMapper rateMapper;
    private final PeriodMapper periodMapper;

    @GetMapping("/schedule/payment/{amount}/{rateFrequency}/{start}/{end}/{billingFrequency}")
    public ResponseEntity<PaymentDto> calculate(@PathVariable @NonNull Double amount,
                                                @PathVariable @NonNull String rateFrequency,
                                                @PathVariable @NonNull String start,
                                                @PathVariable @NonNull String end,
                                                @PathVariable @NonNull String billingFrequency) {
        Rate rate = rateMapper.toRate(amount, rateFrequency);
        Period period = periodMapper.toPeriod(start, end);
        Payment payment = paymentService.calculate(rate, period);
        return ResponseEntity.ok(paymentMapper.toPaymentDto(payment, billingFrequency));
    }
}
