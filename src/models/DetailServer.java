/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;

/**
 *
 * @author smartTicket
 */
public class DetailServer {

    String name;
    int messageEn;
    int signal;
    List<Money> money;

    public DetailServer() {
    }

    public DetailServer(String name, int signal) {
        this.name = name;
        this.signal = signal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMessageEn() {
        return messageEn;
    }

    public void setMessageEn(int messageEn) {
        this.messageEn = messageEn;
    }

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public List<Money> getMoney() {
        return money;
    }

    public void setMoney(List<Money> money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "DetailServer{" + "name=" + name + ", messageEn=" + messageEn + ", signal=" + signal + ", money=" + money + '}';
    }

}
