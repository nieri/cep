package br.com.munieri.domain.userAddress;

import br.com.munieri.domain.cep.CEP;
import br.com.munieri.domain.postalAddress.PostalAddress;

public interface UserAddress {

    Long id();

    Long idUser();

    CEP cep();

    PostalAddress address();

    Integer number();

    String complement();
}