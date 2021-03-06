package com.laurensia.delivery.transaction.service;

import com.laurensia.delivery.baseresponse.BaseResponse;
import com.laurensia.delivery.transaction.request.TransactionSaveRatingRequest;
import com.laurensia.delivery.transaction.request.TransactionSaveRequest;
import com.laurensia.delivery.transaction.response.TransactionDetailResponse;
import com.laurensia.delivery.transaction.response.TransactionDetailTotalResponse;
import com.laurensia.delivery.transaction.response.TransactionReviewResponse;
import com.laurensia.delivery.transaction.response.TransactionSaveResponse;
import java.util.List;

/**
 * @author Khanza
 *
 */
public interface TransactionService {

    public BaseResponse<TransactionSaveResponse> saveTransaction(TransactionSaveRequest request);

    public BaseResponse<List<TransactionDetailResponse>> getTransactionByUser(String request);

//    public BaseResponse<List<TransactionDetailResponse>> getTransactionByUserAndStatus(String request);

    public BaseResponse<List<TransactionDetailTotalResponse>> getTransactionByAdminTotalRating();
    
    public BaseResponse<List<TransactionDetailResponse>> getTransactionByStaff(String request);

    public BaseResponse<TransactionSaveResponse> updateTransactionByStaff(Long request);

    public BaseResponse<List<TransactionDetailResponse>> saveRatingTransaction(TransactionSaveRatingRequest request);

    public BaseResponse<List<TransactionReviewResponse>> getTransactionByCustomerTotalRating();

}
