package model;

import model.entities.Seller;

import java.util.Set;

public interface SellerDAO {

    void insert(Seller obj);
    void update(Seller obj);
    void deleteById(Integer id);
    Seller findById(Integer id);
    Set<Seller> findAll();

}
