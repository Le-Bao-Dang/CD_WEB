package org.uaf.cd_web.services.IServices;

import jakarta.transaction.Transactional;
import org.uaf.cd_web.entity.Cart;
import org.uaf.cd_web.entity.Love;
import org.uaf.cd_web.entity.Product;

import java.util.List;

public interface ICartService {
    List<Cart> getListCart(String idUser);

    Product getListProductInCart(String idUser);

    // hàm tính tổng ở cart
    int sumCart(List<Cart> l);

    void deleteFromCart(String id, String idUser);

    void updateAmountToCart(String idProduct, String idUser, int amount);

    List<Integer> getCountCart(String idUser);

    int sumAmount(List<Cart> l);

    boolean checkExit(String idUser, String idPr);

    void insertToCart(String idUser, String idPr, int amount);

    void updateToCart(String idUser, String idPr, int amount);

    @Transactional
    void deleteFromLove(String idPr, String idUser);
}
