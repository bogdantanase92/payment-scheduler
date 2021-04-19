package com.tanaseb.paymentscheduler.infrastructure.controller;

import com.tanaseb.paymentscheduler.infrastructure.model.PaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentControllerTest {

    @Autowired
    private PaymentController paymentController;

    @Test
    public void test1() {
        ResponseEntity<PaymentDto> responseEntity = paymentController.calculate(
                1350d, "monthly",
                "2016-02-01", "2017-01-31",
                "monthly");
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void test2() {
        ResponseEntity<PaymentDto> responseEntity = paymentController.calculate(
                1350d, "monthly",
                "2016-02-15", "2017-02-14",
                "monthly");
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void test3() {
        ResponseEntity<PaymentDto> responseEntity = paymentController.calculate(
                1350d, "monthly",
                "2016-02-01", "2016-12-31",
                "monthly");
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void test4() {
        ResponseEntity<PaymentDto> responseEntity = paymentController.calculate(
                1350d, "monthly",
                "2016-01-30", "2017-01-29",
                "monthly");
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void test5() {
        ResponseEntity<PaymentDto> responseEntity = paymentController.calculate(
                1350d, "weekly",
                "2016-02-01", "2017-01-31",
                "monthly");
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void test6() {
        ResponseEntity<PaymentDto> responseEntity = paymentController.calculate(
                1350d, "weekly",
                "2016-02-15", "2017-02-14",
                "monthly");
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void test7() {
        ResponseEntity<PaymentDto> responseEntity = paymentController.calculate(
                1350d, "weekly",
                "2016-02-01", "2016-12-31",
                "monthly");
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void test8() {
        ResponseEntity<PaymentDto> responseEntity = paymentController.calculate(
                1350d, "weekly",
                "2016-02-15", "2017-01-14",
                "monthly");
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void test9() {
        ResponseEntity<PaymentDto> responseEntity = paymentController.calculate(
                1350d, "weekly",
                "2016-01-30", "2017-01-29",
                "monthly");
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void test10() {
        ResponseEntity<PaymentDto> responseEntity = paymentController.calculate(
                1350d, "monthly",
                "2016-02-15", "2017-02-14",
                "quarterly");
        log.info(responseEntity.getBody().toString());
    }
}