package br.com.munieri.infrastructure.repository.address;

import br.com.munieri.domain.postalAddress.PostalAddress;
import br.com.munieri.infrastructure.repository.district.City;
import br.com.munieri.infrastructure.repository.userAddress.UserAddressEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AddressEntity implements PostalAddress {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String cep;

    @Column
    private String description;

    @Column
    private String district;

    @ManyToOne(cascade = CascadeType.ALL)
    public City city;

    @OneToMany
    private Set<UserAddressEntity> userAddress;

    public AddressEntity() {
    }

    public AddressEntity(String cep, String logradouro, String district, City city) {
        this.cep = cep;
        this.description = logradouro;
        this.district = district;
        this.city = city;
    }

    @Override
    public String address() {
        return description;
    }

    @Override
    public String district() {
        return district;
    }

    @Override
    public City city() {
        return city;
    }

    public void setId(long id) {
        this.id = id;
    }
}