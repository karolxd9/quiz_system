package com.auth;

import com.conf.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Register {
    private String first_name;
    private String second_name;
    private String login;
    private String password;

    public Register(String first_name,String second_name, String login, String password){
        this.first_name = first_name;
        this.second_name = second_name;
        this.login = login;
        this.password = password;
    }

    /**
     * Sprawdza warunki bezpieczeństwa hasła
     * @param password hasło, które wprowadza użytkownik podczas rejestracji
     * @return wynik weryfikacji proponowanego hasła
     */
    public boolean includePasswordConditions(String password){
        boolean lengthTest = false;
        boolean bigCharacterTest = false;
        boolean specialCharTest = false;

        int passLength = password.length();
        if(passLength>=8 && passLength<=20) lengthTest = true;
        for(int i=0;i<passLength;i++) {
            if (((int) (password.charAt(i)) >= 33 && (int) (password.charAt(i)) <= 64) ||
                    ((int) (password.charAt(i)) >= 123 && (int) (password.charAt(i)) <= 127)) {
                specialCharTest = true;
            }
            if ((int) password.charAt(i) >= 65 && (int) password.charAt(i) <= 90) {
                bigCharacterTest = true;
            }
        }
        if(lengthTest && bigCharacterTest && specialCharTest) return true;
        return false;
    }



    /**
     * Sprawdza unikalność wartości w kolumnie
     * @param column nazwa kolumny
     * @param table nazwa tabeli
     * @param username nazwa szukanej wartości
     * @return wynik testu unikalności
     * @throws SQLException
     */
    public boolean isUnique(String column, String table, String username) throws SQLException {
        int licznik = 0;
        String query = "SELECT "+ column + " FROM "+ table + " WHERE "+ column + " = "+ "'"+ username + "'";
        try{
            QueryExecutor queryExecutor = new QueryExecutor();
            ResultSet rs = queryExecutor.executeSelect(query);
            while (rs.next()){
                rs.next();
                licznik++;
            }
        }
        catch(SQLException e){
            System.out.println("Błąd z bazą danych");
        }
        if(licznik == 0){
            return true;
        }
        return false;

    }

    /**
     * Sprawdza poprawność warunku loginu
     * @param login proponowany login
     * @return wynik testu poprawności loginu
     */
    public boolean includeLoginConditions(String login) throws SQLException {
        int loginLength = login.length();
        if(loginLength >= 8 && loginLength <=15 && isUnique("login","user_login",login)) return true;
        return false;
    }

    /**
     * Sprawdza czy wszytsko zostało wprowadzone poprawnie
     * @return wynik weryfikacji wprowadzonych danych
     */
    public boolean isOK() throws SQLException {
        boolean loginState = false;
        boolean passState = false;
        passState = includePasswordConditions(this.password);
        loginState = includeLoginConditions(this.login);
        return (passState && loginState);
    }

}
