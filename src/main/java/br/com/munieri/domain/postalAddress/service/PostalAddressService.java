package br.com.munieri.domain.postalAddress.service;

import br.com.munieri.domain.cep.CEP;
import br.com.munieri.domain.postalAddress.PostalAddress;
import br.com.munieri.domain.postalAddress.PostalAddressNotFound;

public interface PostalAddressService {

    /**
     * Busca um Endereco Postal dado o CEP informado.<br />
     * @param cep CEP
     * @return null caso nenhum endereco tenha sido encontrado
     * @throws PostalAddressNotFound caso o CEP nao seja encontrado no repositorio
     */
    PostalAddress find(CEP cep) throws PostalAddressNotFound;
}