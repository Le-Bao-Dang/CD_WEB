package org.uaf.cd_web.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uaf.cd_web.entity.Cart;
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

    @Override
    public void addOrder(Orders orders) {
        orderReponesitory.save(orders);
    }

    @Override
    public String generateIdOrder() {
        int size = orderReponesitory.getMaxId() + 1;
        return "orders" + size;
    }

    @Override
    public void changeConditionOrder(String idOrder, int status) {
        Orders orders = getOrderById(idOrder);
        if (status >= orders.getStatus()) {
            orderReponesitory.setConditionOrder(idOrder, status);
        }
    }

    @Override
    public Page<Orders> getListOrder(int page) {
        Pageable paging = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "timeOrders"));
        return this.orderReponesitory.findAll(paging);
    }

    @Override
    @Transactional
    public Orders getOrderById(String id) {
        System.out.println(orderReponesitory.getOrdersByIdOrders(id));
        return orderReponesitory.getOrdersByIdOrders(id);
    }

    @Override
    public List<Sold_Pr> getListIdProductInOrder(String idOrder) {
        return this.orderReponesitory.findById(idOrder).get().getPrList();
    }

    @Override
    public long sumOrder(String idOrder) {
        List<Sold_Pr> listPr = this.getListIdProductInOrder(idOrder);
        long sum = 0L;

        Sold_Pr pr;
        for (Iterator var5 = listPr.iterator(); var5.hasNext(); sum += (long) (pr.getAmount() * pr.getPriceHere())) {
            pr = (Sold_Pr) var5.next();
        }

        return sum;
    }

    @Override
    public List<Orders> getListOrderAll() {
        return this.orderReponesitory.findAll();
    }

    // Lấy danh sách sản phẩm đã bán dựa vào ID người dùng
    public List<Sold_Pr> getHistory(String idUser) {
        return soldPrReponesitory.historyUser(idUser);
    }

    // Tạo bản đồ lịch sử giao dịch
    public Map<String, List<Sold_Pr>> getMapHistoryOrders(List<Sold_Pr> soldProductList) {
        Map<String, List<Sold_Pr>> mapResult = new HashMap<>();
        for (Sold_Pr s : soldProductList) {
            mapResult.computeIfAbsent(s.getOrders().getIdOrders(), k -> new ArrayList<>()).add(s);
        }
        return mapResult;
    }

    // Tính tổng tiền trong lịch sử giao dịch
    public Map<String, Integer> sumHistoryOrder(Map<String, List<Sold_Pr>> map) {
        Map<String, Integer> mapResult = new HashMap<>();
        for (Map.Entry<String, List<Sold_Pr>> entry : map.entrySet()) {
            int sum = 0;
            for (Sold_Pr s : entry.getValue()) {
                sum += s.getAmount() * s.getPriceHere();
            }
            mapResult.put(entry.getKey(), sum);
        }
        return mapResult;
    }

    @Override
    public List<Sold_Pr> getManagerOrderUser(String idUser) {
        return soldPrReponesitory.getManagerOrderUser(idUser);
    }

    @Override
    //list quan ly orders của user
    public List<Orders> getManageOrders(String idUser) {
        return orderReponesitory.getManageOrders(idUser);
    }

    // map orders của user
    public Map<String, List<Orders>> getMapOrder(List<Orders> ordersList) {
        Map<String, List<Orders>> mapResult = new HashMap<String, List<Orders>>();

        for (Orders o : ordersList) {
            if (mapResult.containsKey(o.getIdOrders())) {
                mapResult.get(o.getIdOrders()).add(o);
            } else {
                List<Orders> listOrder = new ArrayList<Orders>();
                listOrder.add(o);
                mapResult.put(o.getIdOrders(), listOrder);
            }
        }

        return mapResult;
    }

    // tính tien orders
    @Override
    public Map<String, Integer> sumOrder(Map<String, List<Orders>> map) {
        Map<String, Integer> mapResult = new HashMap<String, Integer>();
        for (Map.Entry<String, List<Orders>> entry : map.entrySet()) {
            int sum = 0;
            for (Orders o : entry.getValue()) {
                sum += o.getAmountPr() * o.getPrList().get(0).getPriceHere();
            }
            mapResult.put(entry.getKey(), sum);
        }
        return mapResult;
    }

    @Override
    public int getTurnover(int month, int year) {
        Integer result = orderReponesitory.getTurnOver(month, year);
        return result != null ? result : 0;
    }

    @Override
    public int getAllTurnover() {
        return orderReponesitory.getAllTurnOver();
    }

    @Override
    public int getSalerPRAll() {
        return orderReponesitory.getAllSalePr();
    }

    @Override
    public int getSalerPR() {
        return orderReponesitory.getAllSalePr();
    }

    @Override
    public List<Orders> searchOrder(String keyword) {
        if (keyword.equals(" ")) return orderReponesitory.findAll();
        return orderReponesitory.findOrdersByIdOrders(keyword);
    }

}
