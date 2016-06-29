package br.com.munieri.domain.userAddress.builder;

import br.com.munieri.domain.postalAddress.PostalAddress;
import br.com.munieri.domain.userAddress.UserAddress;
import br.com.munieri.infrastructure.repository.userAddress.UserAddressEntity;

public class UserAddressBuilder {

    public static AddressBuilderUserStep newBuilder() {
        return new Steps();
    }

    public interface AddressBuilderUserStep {

        AddressBuilderCepStep user(Long idUser);

        AddressBuilderUserStep id(Long userAddressID);
    }

    public interface AddressBuilderCepStep {

        /**
         * Constroi um endereco dado um CEP
         * @param cep
         * @return
         */
        AddressBuilderNumberStep postalAddress(PostalAddress cep);
    }

    public interface AddressBuilderNumberStep {
        /**
         * Metodo <b>Opcional</b>.<br />
         * Insere o numero do endereco
         * @param number
         * @return
         */
        AddressBuilderFinalStep number(int number);

    }

    public interface AddressBuilderFinalStep {


        /**
         * Metodo <b>Opcional</b>.<br />
         * Insere o complemento do endereco
         * @param complement
         * @return
         */
        AddressBuilderFinalStep complement(String complement);


        UserAddress build();
    }


    private static class Steps implements AddressBuilderFinalStep, AddressBuilderCepStep, AddressBuilderUserStep, AddressBuilderNumberStep {

        private int number;
        private String complement;
        private PostalAddress postalAddress;
        private Long iduser;
        private Long userAddressID;

        @Override
        public AddressBuilderNumberStep postalAddress(PostalAddress postalAddress) {
            this.postalAddress = postalAddress;
            return this;
        }

        @Override
        public AddressBuilderFinalStep number(int number) {
            this.number = number;
            return this;
        }

        @Override
        public AddressBuilderFinalStep complement(String complement) {
            this.complement = complement;
            return this;
        }

        @Override
        public UserAddress build() {
            return new UserAddressEntity(userAddressID, iduser, postalAddress, number, complement);
        }

        @Override
        public AddressBuilderCepStep user(Long idUser) {
            this.iduser = idUser;
            return this;
        }

        @Override
        public AddressBuilderUserStep id(Long userAddressID) {
            this.userAddressID = userAddressID;
            return this;
        }
    }
}