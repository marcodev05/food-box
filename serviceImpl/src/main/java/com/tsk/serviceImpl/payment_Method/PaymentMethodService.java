package com.tsk.serviceImpl.payment_Method;

import com.tsk.dao.PaymentMethodRepository;
import com.tsk.domain.entities.PaymentMethod;
import com.tsk.exception.ResourceNotFoundException;
import com.tsk.service.payment_method.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@Transactional
public class PaymentMethodService implements IPaymentMethodService {

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Override
    @PostConstruct
    public void initPaymentMethod() {
        paymentMethodRepository.save(new PaymentMethod("MC", "MasterCard"));
        paymentMethodRepository.save(new PaymentMethod("PAYPAL", "PAYPAL"));
        paymentMethodRepository.save(new PaymentMethod("STRIPE", "STRIPE"));
    }

    @Override
    public PaymentMethod getMethodByCode(String code) {
        return paymentMethodRepository.findById(code)
                .orElseThrow(()-> new ResourceNotFoundException("Method not found"));
    }
}
