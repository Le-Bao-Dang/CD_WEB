package org.uaf.cd_web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uaf.cd_web.entity.Cart;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.reponsitory.CartReponesitory;
import org.uaf.cd_web.services.IServices.ICartService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImp implements ICartService {
    private final CartReponesitory cartReponesitory;

    @Autowired
    public CartServiceImp(CartReponesitory cartReponesitory) {
        this.cartReponesitory = cartReponesitory;
    }

    @Override
    public List<Cart> getListCart(String idUser) {
//
//        List<Cart> result = cartReponesitory.findAllByIdUser(idUser);
//        for (Cart cart : result) {
//            cart.setProduct(getListProductInCart(cart.getIdUser()));
//        }
//        return result;
        return cartReponesitory.findAllByIdUser(idUser);
    }

    @Override
    public Product getListProductInCart(String idUser) {
        return cartReponesitory.getProductInCart(idUser);
    }

    @Override
    // hàm tính tổng ở cart
    public int sumCart(List<Cart> l) {
        int result = 0;
        for (Cart c : l) {
            result += (c.getProduct().getPrice() - (c.getProduct().getPrice() * c.getProduct().getDiscount()) / 100) * c.getAmount();
        }
        return result;
    }

    public void addCart(Cart cart) {
        int amount = cart.getAmount();
        Cart cartItem = cartReponesitory.findByIdPrAndIdUser(cart.getIdPr(), cart.getIdUser());
        if (cartItem != null) {
            amount = cartItem.getAmount() + cart.getAmount();
            cartItem.setAmount(amount);
        } else {
            cartItem = new Cart();
            cartItem.setAmount(amount);
            cartItem.setIdPr(cart.getIdPr());
            cartItem.setIdUser(cart.getIdUser());
        }
        cartReponesitory.save(cartItem);
    }

    @Override
    @Transactional
    public void deleteFromCart(String idPr, String idUser) {
        cartReponesitory.deleteByIdPrAndIdUser(idPr, idUser);
    }

    public List<Cart> getProdFormCart(String idUser, String[] listIdArray) {
        List<Cart> list = new ArrayList<>();
        for (String idPr : listIdArray) {
            Cart cart = cartReponesitory.findByIdPrAndIdUser(idPr, idUser);
            list.add(cart);
        }
        return list;
    }

    @Override
    public void updateAmountToCart(String idProduct, String idUser, int amount) {
        cartReponesitory.updateAmount(idProduct,idUser,amount);
    }
}



