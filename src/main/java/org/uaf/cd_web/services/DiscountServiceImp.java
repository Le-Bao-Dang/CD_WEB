package org.uaf.cd_web.services;

import org.springframework.stereotype.Service;
import org.uaf.cd_web.entity.Discount;
import org.uaf.cd_web.reponsitory.DiscountReponesitory;
import org.uaf.cd_web.services.IServices.IDiscountService;

import java.util.List;

@Service
public class DiscountServiceImp implements IDiscountService {
    public final DiscountReponesitory discountReponesitory;

    public DiscountServiceImp(DiscountReponesitory discountReponesitory) {
        this.discountReponesitory = discountReponesitory;
    }

    @Override
    public List<Discount> getListDiscount() {
        return discountReponesitory.findAll();
    }

    @Override
    public Discount getDiscountByCode(String code) {
        return discountReponesitory.getDiscountByCode(code);
    }

    @Override
    public void subttractQuantity(String code) {
        Discount discount = getDiscountByCode(code);
        int quantity = discount.getSoluong() - 1;
        discount.setValue(quantity);
        discountReponesitory.save(discount);
    }
}
