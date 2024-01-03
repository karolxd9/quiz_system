package com.auth;

import com.conf.DBConnector;
import com.conf.QueryExecutor;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

//klasa odpowiada za poprawną autoryzację użytkownika
public class Auth {

    public boolean login1step(String username,String pass) throws SQLException {
        QueryExecutor loginData = new QueryExecutor();
        ResultSet usernameDB;

        usernameDB = loginData.executeSelect("SELECT * FROM user_login WHERE login="+"'"+username+"'"+
                " AND PASSWORD="+"'"+SHA256Hashing.hashStringToSHA256(pass)+"'");

        int howManyLoginAndPass = loginData.countRows(usernameDB);

        if(howManyLoginAndPass == 1) return true;
        return false;

    }

}
