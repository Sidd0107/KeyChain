package com.example.siddharthgautam.keychain;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;

/**
 * Created by siddharthgautam on 28/02/15.
 */
public class appObj {
    String use;
    String username;
    String password;

    public appObj(String use, String usernm, String pass){
        this.use=use;
        this.username=usernm;
        this.password=pass;
    }

}
