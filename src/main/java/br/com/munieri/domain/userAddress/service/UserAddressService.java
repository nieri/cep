package br.com.munieri.domain.userAddress.service;

import br.com.munieri.domain.userAddress.UserAddress;
import br.com.munieri.domain.userAddress.UserAddressNotFound;

public interface UserAddressService {

    /**
     * Cria um novo Endereco do Usuario
     * @param userAddress
     * @return
     */
    UserAddress create(UserAddress userAddress);

    /**
     * Busca um UserAddress por ID
     * @param id
     * @return
     */
    UserAddress find(Long id) throws UserAddressNotFound;

    /**
     * Apaga o UserAddress do repositorio de dados
     * @param id
     */
    void delete(Long id) throws UserAddressNotFound;

    /**
     * Atualiza o UserAddress.<br/>
     * O ID dele deve estar preenchido
     * @param address
     * @return
     */
    UserAddress update(UserAddress address) throws UserAddressNotFound;
}