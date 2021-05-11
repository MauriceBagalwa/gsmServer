/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author smartTicket
 */
public class Message {

    String numero, resau, message, date,code;

    public Message(String code) {
        this.code = code;
    }

    public Message(String numero, String resau, String message) {

        this.numero = numero;
        this.resau = resau;
        this.message = message;
    }

    public Message(String date, String numero, String resau, String message) {
        this.date = date;
        this.numero = numero;
        this.resau = resau;
        this.message = message;
    }

    public String getNumero() {
        return numero;
    }

    public String getResau() {
        return resau;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Message{" + "numero=" + numero + ", resau=" + resau + ", message=" + message + '}';
    }

}
