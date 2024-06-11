package org.uaf.cd_web.services.IServices;

import org.uaf.cd_web.entity.Discount;

import java.util.List;

public interface IDiscountService {
    List<Discount> getListDiscount();

    Discount getDiscountByCode(String code);

    void subttractQuantity(String idDiscount);
}
