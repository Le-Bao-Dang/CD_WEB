
package org.uaf.cd_web.services.IServices;

import java.util.List;
import org.springframework.data.domain.Page;
import org.uaf.cd_web.entity.Orders;
import org.uaf.cd_web.entity.Sold_Pr;

public interface IOrderService {
    void addOrder(Orders orders);

    String generateIdOrder();

    void changeConditionOrder(String idOrder, int condition);

    Page<Orders> getListOrder(int page);

    Orders getOrderById(String id);

    List<Sold_Pr> getListIdProductInOrder(String idOrder);

    long sumOrder(String idOrder);

    List<Orders> getListOrderAll();
}
