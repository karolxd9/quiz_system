package com.modification;

import com.auth.Register;
import com.auth.SHA256Hashing;
import com.conf.GlobalSettings;
import com.conf.QueryExecutor;
import com.db.DMLHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Modification {
    private DMLHandler modificationHandler;



    public void changeUserData(String name,int userID){
        ArrayList<String>list = new ArrayList<>();
        String query = "UPDATE user SET first_name="+"'"+name+"'"+" WHERE user_id="+userID+"";
        list.add(query);
        this.modificationHandler = new DMLHandler(GlobalSettings.socket,list);
        this.modificationHandler.run();
    }

    public void changePassword(int userID,String currentPassword,String newPassword) throws SQLException {
        QueryExecutor queryExecutor = new QueryExecutor();
        ResultSet resultSet = queryExecutor.executeSelect("SELECT * FROM user_login WHERE PASSWORD="+"'"+ SHA256Hashing.hashStringToSHA256(currentPassword)+"'");
        int count = QueryExecutor.countRows(resultSet);
        if(count == 1){
            if(Register.includePasswordConditions(newPassword)){
                ArrayList<String>list = new ArrayList<>();
                String query = "UPDATE user_login SET PASSWORD="+"'"+SHA256Hashing.hashStringToSHA256(newPassword)+"'"+" WHERE user_id = "+userID;
                list.add(query);
                this.modificationHandler = new DMLHandler(GlobalSettings.socket,list);
                this.modificationHandler.run();
            }
        }
    }

    public void changeLogin(int userID,String newLogin) throws SQLException {
        if(Register.includeLoginConditions(newLogin)){
            ArrayList<String>list = new ArrayList<>();
            String query = "UPDATE user_login SET login="+"'"+newLogin+"'"+" WHERE user_id = "+userID;
            list.add(query);
            this.modificationHandler = new DMLHandler(GlobalSettings.socket,list);
            this.modificationHandler.run();
        }
    }
}
