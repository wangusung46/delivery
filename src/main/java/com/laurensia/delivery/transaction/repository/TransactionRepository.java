package com.laurensia.delivery.transaction.repository;

import com.laurensia.delivery.transaction.model.Transaction;
import com.laurensia.delivery.transaction.response.TransactionDetailResponse;
import com.laurensia.delivery.transaction.response.TransactionDetailTotalResponse;
import com.laurensia.delivery.transaction.response.TransactionReviewResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT t.id AS id, u.id AS idUser, u.name AS nameUser, i.id AS idItem, u.email AS emailUser, i.name AS nameItem, "
            + "t.countItem AS countItem, t.status AS status, t.countItem * i.price AS total, "
            + "COALESCE(r.rate, '0') AS rate, "
            + "COALESCE(r.review, 'Not Review') AS review "
            + "FROM Transaction t "
            + "LEFT JOIN User u ON t.idUser = u.id "
            + "LEFT JOIN Item i ON t.idItem = i.id "
            + "LEFT JOIN Rating r ON t.id = r.idTransaction "
            + "WHERE u.email = (:email) "
            + "ORDER BY t.id DESC")
    public List<TransactionDetailResponse> findByUserTransactions(@Param("email") String email);

    @Query(value = "SELECT t.id AS id, u.id AS idUser, u.name AS nameUser, i.id AS idItem, i.name AS nameItem, "
            + "t.countItem AS countItem, t.status AS status, t.countItem * i.price AS total, "
            + "COALESCE(r.rate, '0') AS rate, "
            + "COALESCE(r.review, 'Not Review') AS review "
            + "FROM Transaction t "
            + "LEFT JOIN User u ON t.idUser = u.id "
            + "LEFT JOIN Item i ON t.idItem = i.id "
            + "LEFT JOIN Rating r ON t.id = r.idTransaction "
            + "ORDER BY t.id DESC")
    public List<TransactionDetailResponse> findByAdminTransactions();

    @Query(value = "SELECT t.id AS id, u.id AS idUser, u.name AS nameUser, i.id AS idItem, i.name AS nameItem, "
            + "t.countItem AS countItem, t.status AS status, t.countItem * i.price AS total, "
            + "COALESCE(r.rate, '0') AS rate, "
            + "COALESCE(r.review, 'Not Review') AS review "
            + "FROM Transaction t "
            + "LEFT JOIN User u ON t.idUser = u.id "
            + "LEFT JOIN Item i ON t.idItem = i.id "
            + "LEFT JOIN Rating r ON t.id = r.idTransaction "
            + "WHERE t.status = 'Complete' "
            + "ORDER BY t.id DESC")
    public List<TransactionDetailResponse> findByUsers();

    @Query(value = "SELECT t.id AS id, "
            + "i.name AS nameItem, "
            + "SUM(t.countItem) AS countItem, "
            + "t.status AS status, "
            + "i.price AS price, "
            + "SUM(t.countItem * i.price) AS total, "
            + "((SUM(COALESCE(r.rate, 0))) / (COUNT(r.rate) * 5)) * 5 AS rate "
            + "FROM Transaction t "
            + "LEFT JOIN Item i ON t.idItem = i.id "
            + "LEFT JOIN Rating r ON t.id = r.idTransaction "
            + "WHERE t.status = 'Complete' "
            + "GROUP BY i.id "
            + "ORDER BY r.id DESC")
    public List<TransactionDetailTotalResponse> findByAdminTotalRatings();

    @Query(value = "SELECT t.id AS id, u.id AS idUser, u.name AS nameUser, u.email AS emailUser, i.id AS idItem, i.name AS nameItem, "
            + "t.countItem AS countItem, t.status AS status, t.countItem * i.price AS total, "
            + "COALESCE(r.rate, '0') AS rate, "
            + "COALESCE(r.review, 'Not Review') AS review "
            + "FROM Transaction t "
            + "LEFT JOIN User u ON t.idUser = u.id "
            + "LEFT JOIN Item i ON t.idItem = i.id "
            + "LEFT JOIN Rating r ON t.id = r.idTransaction "
            + "ORDER BY t.id DESC")
    public List<TransactionDetailResponse> findByStaffTransactions(@Param("email") String email);

    @Query(value = "SELECT t.id AS id, u.id AS idUser, u.name AS nameUser, u.email AS emailUser, i.id AS idItem, i.name AS nameItem, "
            + "t.countItem AS countItem, t.status AS status, t.countItem * i.price AS total, "
            + "COALESCE(r.rate, '0') AS rate, "
            + "COALESCE(r.review, 'Not Review') AS review "
            + "FROM Transaction t "
            + "LEFT JOIN User u ON t.idUser = u.id "
            + "LEFT JOIN Item i ON t.idItem = i.id "
            + "LEFT JOIN Rating r ON t.id = r.idTransaction "
            + "WHERE u.email = (:email) AND t.id = (:transaction) "
            + "ORDER BY t.id DESC")
    public List<TransactionDetailResponse> findByUserTransaction(@Param("email") String email, @Param("transaction") Long idTransaction);

    @Query(value = "SELECT i.id AS idProduct, i.name AS nameProduct, COALESCE(u.name, 'Empty') AS nameUser, COALESCE(r.rate, 0) AS rate, COALESCE(r.review, 'Not Review') AS review "
            + "FROM Item i "
            + "LEFT JOIN Transaction t ON i.id = t.idItem "
            + "LEFT JOIN User u ON t.idUser = u.id "
            + "LEFT JOIN Rating r ON t.id = r.idTransaction")
    public List<TransactionReviewResponse> findByCustomerTotalRatings();
}
