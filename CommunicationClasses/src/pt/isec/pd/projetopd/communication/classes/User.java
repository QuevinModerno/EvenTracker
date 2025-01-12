package pt.isec.pd.projetopd.communication.classes;

import java.io.Serializable;

/**
 * classe para Registo
 */

public class User implements Serializable {

    private String username;
    private String password;
    private String name;
    private int studentNumber;
    private int nif;
    private String id;
    private String address;

    public User(String username, String password, String name, int studentNumber, int nif, String id, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.studentNumber = studentNumber;
        this.nif = nif;
        this.id = id;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public int getNIF() {
        return nif;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }


}
