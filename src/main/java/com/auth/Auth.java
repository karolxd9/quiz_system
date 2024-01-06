package com.auth;

import com.conf.DBConnector;
import com.conf.QueryExecutor;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import db.ClientHandler;

import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

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
