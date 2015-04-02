package com.example.emil.tddc73_project;

/**
 * Created by Emil on 2015-04-02.
 */
public interface AlgorithmInterface {

    /**
     *  Checks the strength of a given password
     *  @param password
     *  @return passwordStrength. A value between 0-10 describing the strength of the password.
     */
    int passwordStrength(String password);
}
