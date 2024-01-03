package com.example.magic.retrofit.clients;

public class ApiSendClientRegister {


    public ApiSendClientRegister(String password, String name, String lastName, String secondLastName,String email ){
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.email = email;

    }
    public String password;
       public String name;
       public String lastName;
       public String secondLastName;
       public String email;

}
