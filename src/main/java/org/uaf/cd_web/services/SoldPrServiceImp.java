package org.uaf.cd_web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uaf.cd_web.entity.Sold_Pr;
import org.uaf.cd_web.reponsitory.SoldPrReponesitory;
import org.uaf.cd_web.services.IServices.ISoldPrService;

@Service
public class SoldPrServiceImp implements ISoldPrService {
    private final SoldPrReponesitory soldPrReponesitory;

    @Autowired
    public SoldPrServiceImp(SoldPrReponesitory soldPrReponesitory) {
        this.soldPrReponesitory = soldPrReponesitory;
    }

    @Override
    public void addToSoldProd(Sold_Pr soldPr) {
        soldPrReponesitory.insertSoldPr(soldPr.getProduct().getIdPr(), soldPr.getTimeSold(), soldPr.getAmount(), soldPr.getOrders().getIdOrders(), soldPr.getPriceHere());

    }
}
