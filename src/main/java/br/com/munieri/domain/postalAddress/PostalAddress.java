package br.com.munieri.domain.postalAddress;

import br.com.munieri.infrastructure.repository.district.City;

/**
 * Representa um Endereco Postal.<br />
 * Composto de um endereco de rua, um bairro e a cidade.
 */
public interface PostalAddress {

    /**
     * Representa a rua do Endereço postal.<br />
     * Em cidades menos populosas, os codigos postais representam a propria cidade.<br />
     * Portanto, em caso de CEPs de cidades, este campo pode vir nulo
     *
     * @return o nome da rua <br />
     * null caso o CEP seja de cidade
     */
    String address();

    /**
     * Representa o bairro
     */
    String district();

    City city();
}