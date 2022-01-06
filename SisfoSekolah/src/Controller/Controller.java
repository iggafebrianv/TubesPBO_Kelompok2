/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GUI.login;
import Model.Database;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Controller implements ActionListener{
    private login view;
    private Database db;
    
    public Controller() {
        view = new login();
        view.addActionListener(this);
        view.setVisible(true);
        db = new Database();
    };

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.getBtnLogin())) {
            if (view.getUser() == "Siswa") {
                view.dispose();
                new ControllerSiswa(db);
            }
            else if (view.getUser() == "Guru") {
                view.dispose();
                new ControllerGuru(db);
            }
            else if (view.getUser() == "Admin") {
                view.dispose();
                new ControllerAdmin(db);
            }
        }
    }    
}

