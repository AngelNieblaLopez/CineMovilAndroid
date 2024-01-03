package com.example.magic.retrofit.sales;


import java.util.ArrayList;
import java.util.List;

public class ApiSendSaleRegister {

    public String functionId;
    public String clientId;
    public String owner;
    public String cardNumber;
    public String cvv;
    public String expirationDate;
    public List<String> seatsIds = new ArrayList<>();

    public ApiSendSaleRegister(String functionId, String clientId, String owner, String cardNumber, String cvv, String expirationDate, List<String> seatsIds ) {
        this.functionId = functionId;
        this.clientId = clientId;
        this.owner = owner;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.seatsIds = seatsIds;
    }

}
