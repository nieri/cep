package br.com.munieri.view.endpoint.postalAddress;

import br.com.munieri.domain.postalAddress.PostalAddress;
import br.com.munieri.infrastructure.repository.district.City;

public class PostalAddressDTO {

    private String address;
    private String city;
    private String uf;
    private String district;

    public PostalAddressDTO(PostalAddress postalAddress) {
        buildAddress(postalAddress);
        buildCity(postalAddress.city());
    }

    private void buildAddress(PostalAddress postalAddress) {
        this.address = postalAddress.address();
        this.district = postalAddress.district();
    }

    private void buildCity(City city) {
        this.city = city.getDescription();
        this.uf = city.getUf();
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getUf() {
        return uf;
    }

    public String getDistrict() {
        return district;
    }
}