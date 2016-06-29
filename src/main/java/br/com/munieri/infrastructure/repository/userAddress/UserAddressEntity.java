package br.com.munieri.infrastructure.repository.userAddress;

import br.com.munieri.domain.cep.CEP;
import br.com.munieri.domain.postalAddress.PostalAddress;
import br.com.munieri.domain.userAddress.UserAddress;
import br.com.munieri.infrastructure.repository.address.AddressEntity;

import javax.persistence.*;

@Entity
public class UserAddressEntity implements UserAddress {


    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long userId;

    @ManyToOne(targetEntity = AddressEntity.class)
    public PostalAddress address;

    @Column
    private Integer number;
    @Column
    private String complement;

    @Transient
    private transient CEP cep;

    public UserAddressEntity() {
    }

    public UserAddressEntity(Long id, Long userId, PostalAddress postalAddress, Integer number, String complement){
        this.id = id;
        this.userId = userId;
        this.address = postalAddress;
        this.number = number;
        this.complement = complement;
    }

    public UserAddressEntity(long id) {
        this.id = id;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Long idUser() {
        return userId;
    }

    @Override
    public CEP cep() {
        return cep;
    }

    @Override
    public PostalAddress address() {
        return address;
    }

    @Override
    public Integer number() {
        return number;
    }

    @Override
    public String complement() {
        return complement;
    }
}