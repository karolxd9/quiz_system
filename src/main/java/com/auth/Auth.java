package com.auth;

import com.conf.QueryExecutor;

import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

//klasa odpowiada za poprawną autoryzację użytkownika
public class Auth {

    public boolean login1step(String username, String pass, Socket socket) throws SQLException {

        String query = "SELECT * FROM user_login WHERE login="+"'"+username+"'"+
                " AND PASSWORD="+"'"+SHA256Hashing.hashStringToSHA256(pass)+"'";

        ResultSet result = QueryExecutor.result(query,socket);
        if(QueryExecutor.countRows(result) == 1) return true;
        return false;

    }

}
