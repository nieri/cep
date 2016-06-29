package br.com.munieri.view.endpoint.userAddress;

import br.com.munieri.domain.userAddress.UserAddress;

public class UserAddressDTO {

    private Long id;
    private Long idUser;
    private String cep;
    private String address;
    private String city;
    private String uf;
    private String district;
    private int number;
    private String complement;

    public UserAddressDTO() {
    }

    public UserAddressDTO(UserAddress userAddress) {
        this.id = userAddress.id();
        this.idUser = userAddress.idUser();
        this.address = userAddress.address().address();
        this.district = userAddress.address().district();
        this.city = userAddress.address().city().getDescription();
        this.uf = userAddress.address().city().getUf();
        this.number = userAddress.number();
        this.complement = userAddress.complement();
    }

    public Long getId() {
        return id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public String getCep() {
        return cep;
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

    public int getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }
}