package org.uaf.cd_web.services.IServices;

import org.uaf.cd_web.entity.Cart;
import org.uaf.cd_web.entity.Product;

import java.util.List;

public interface ICartService {
    List<Cart> getListCart(String idUser);

    Product getListProductInCart(String idUser);

    // hàm tính tổng ở cart
    int sumCart(List<Cart> l);

    void deleteFromCart(String id, String idUser);

    void updateAmountToCart(String idProduct, String idUser, int amount);
}
