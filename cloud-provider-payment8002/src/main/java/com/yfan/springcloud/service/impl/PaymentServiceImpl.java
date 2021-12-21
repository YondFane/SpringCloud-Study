package com.yfan.springcloud.service.impl;

import com.yfan.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yfan.springcloud.dao.PaymentDao;
import com.yfan.springcloud.service.PaymentService;

/*
 * @author YFAN
 * @date 2021/12/20/020
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
