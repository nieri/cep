package br.com.munieri.domain.cep;

public class InvalidCEP extends RuntimeException {

    public InvalidCEP(String message) {
        super(message);
    }
}