package org.uaf.cd_web.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uaf.cd_web.entity.Cart;
import org.uaf.cd_web.entity.Product;
import org.uaf.cd_web.reponsitory.CartReponesitory;
import org.uaf.cd_web.reponsitory.LoveReponesitory;
import org.uaf.cd_web.services.IServices.ICartService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImp implements ICartService {
    private final CartReponesitory cartReponesitory;
    private final LoveReponesitory loveReponesitory;

    @Autowired
    public CartServiceImp(CartReponesitory cartReponesitory, LoveReponesitory loveReponesitory) {
        this.cartReponesitory = cartReponesitory;
        this.loveReponesitory = loveReponesitory;
    }

    @Override
    public List<Cart> getListCart(String idUser) {
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
    public List<Integer> getCountCart(String idUser) {
        // Implement logic to get listCart by user id
        return cartReponesitory.getCountCart(idUser);
    }

    @Override
    public int sumAmount(List<Cart> l) {
        int result = 0;
        for (Cart c : l) {
            result += c.getAmount();
        }
        return result;

    }

    @Override
    public boolean checkExit(String idUser, String idPr) {
        return cartReponesitory.checkExit(idUser, idPr);
    }

    // them sp
    @Override
    public void insertToCart(String idUser, String idPr, int amount) {
        Cart c = new Cart();
        c.setIdUser(idUser);
        c.setIdPr(idPr);
        c.setAmount(amount);
        cartReponesitory.save(c);
    }

    @Override
    public void updateToCart(String idUser, String idPr, int amount) {
        cartReponesitory.updateToCart(idUser, idPr, amount);
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
    public void deleteFromLove(String idPr, String idUser) {
        loveReponesitory.deleteByIdPrAndIdUser(idPr, idUser);
    }

}



