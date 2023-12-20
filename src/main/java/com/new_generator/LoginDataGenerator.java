package com.new_generator;

import com.google.common.hash.Hasher;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

public class LoginDataGenerator extends  BasicDataGenerator{


    public LoginDataGenerator(Object typeOfData) {
        super(typeOfData);
    }

    /**
     * Generacja danych logowania dla użytkownika
     * @param id identyfikator użytkownika
     * @return pojedynczy rekord
     */
    public String generate(int id){

        String oneRecord = "";
        String password = this.getFaker().internet().password(10,20);
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(password, Charsets.UTF_8);
        HashCode sha256 = hasher.hash();
        String sha256Text = sha256.toString();
        oneRecord = oneRecord + id + "," + this.getFaker().name().username() + "," + sha256Text + ";";
        return oneRecord;

    }
}
