package br.com.munieri.infrastructure.repository.district;

import br.com.munieri.infrastructure.repository.address.AddressEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class City {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String description;

    @Column
    private String uf;

    @OneToMany
    private Set<AddressEntity> addressEntities;

    public City() {
    }

    public City(String description, String uf) {
        this.description = description;
        this.uf = uf;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Set<AddressEntity> getAddressEntities() {
        return addressEntities;
    }

    public void setAddressEntities(
            Set<AddressEntity> addressEntities) {
        this.addressEntities = addressEntities;
    }
}