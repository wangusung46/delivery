package com.laurensia.delivery.transaction.response;

public interface TransactionDetailResponse {

    Long getId();
    
    Long getIdUser();

    String getNameUser();
    
    Long getIdItem();
    
    String getNameItem();
    
    Integer getCountItem();
    
    String getStatus();
    
    Integer getTotal();
    
    Integer getRate();
    
    String getReview();

}
