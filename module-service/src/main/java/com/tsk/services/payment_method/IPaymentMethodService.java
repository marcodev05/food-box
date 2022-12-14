package com.tsk.services.payment_method;

import com.tsk.domain.entities.PaymentMethod;

public interface IPaymentMethodService {

    public void initPaymentMethod();

    public PaymentMethod getMethodByCode(String code);
}
