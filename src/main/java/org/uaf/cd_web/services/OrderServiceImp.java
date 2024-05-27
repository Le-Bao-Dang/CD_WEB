
package org.uaf.cd_web.services;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.uaf.cd_web.entity.Orders;
import org.uaf.cd_web.entity.Sold_Pr;
import org.uaf.cd_web.reponsitory.OrderReponesitory;
import org.uaf.cd_web.reponsitory.SoldPrReponesitory;
import org.uaf.cd_web.services.IServices.IOrderService;

@Service
public class OrderServiceImp implements IOrderService {
    public final OrderReponesitory orderReponesitory;
    public final SoldPrReponesitory soldPrReponesitory;

    @Autowired
    public OrderServiceImp(OrderReponesitory orderReponesitory, SoldPrReponesitory soldPrReponesitory) {
        this.orderReponesitory = orderReponesitory;
        this.soldPrReponesitory = soldPrReponesitory;
    }

    public void addOrder(Orders orders) {
        orders.setIdOrders(this.generateIdOrder());
        this.orderReponesitory.save(orders);
    }

    public String generateIdOrder() {
        int size = (int)this.orderReponesitory.count() + 1;
        return "orders" + size;
    }

    public void changeConditionOrder(String idOrder, int condition) {
        Orders orders = (Orders)this.orderReponesitory.getReferenceById(idOrder);
        orders.setCondition(condition);
        this.orderReponesitory.save(orders);
    }

    public Page<Orders> getListOrder(int page) {
        Pageable paging = PageRequest.of(page - 1, 10);
        return this.orderReponesitory.findAll(paging);
    }

    public Orders getOrderById(String id) {
        return (Orders)this.orderReponesitory.getReferenceById(id);
    }

    public List<Sold_Pr> getListIdProductInOrder(String idOrder) {
        return this.soldPrReponesitory.findAllByIdOrders(idOrder);
    }

    public long sumOrder(String idOrder) {
        List<Sold_Pr> listPr = this.getListIdProductInOrder(idOrder);
        long sum = 0L;

        Sold_Pr pr;
        for(Iterator var5 = listPr.iterator(); var5.hasNext(); sum += (long)(pr.getAmount() * pr.getPriceHere())) {
            pr = (Sold_Pr)var5.next();
        }

        return sum;
    }

    public List<Orders> getListOrderAll() {
        return this.orderReponesitory.findAll();
    }
}
