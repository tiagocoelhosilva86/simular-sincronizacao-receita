package com.tiago.sincronizacaoreceita;

import java.util.ArrayList;
import java.util.List;

public class CsvConta {
    private String Agencia;
    private String Conta;
    private String Saldo;
    private String Status;

    public String getAgencia() {
        return Agencia;
    }

    public void setAgencia(String agencia) {
        Agencia = agencia;
    }

    public String getConta() {
        return Conta;
    }

    public void setConta(String conta) {
        Conta = conta;
    }

    public String getSaldo() {
        return Saldo;
    }

    public void setSaldo(String saldo) {
        Saldo = saldo;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String[] toCsvString(String serviceRetorno){
        String[] dados = new String[5];
        dados[0] = Agencia;
        dados[1] = Conta;
        dados[2] = String.valueOf(Saldo);
        dados[3] = Status;
        dados[4] = serviceRetorno;

        return dados;
    }

}
