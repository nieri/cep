package br.com.munieri.domain.postalAddress.service;

import br.com.munieri.domain.cep.CEP;
import br.com.munieri.domain.postalAddress.PostalAddress;
import br.com.munieri.domain.postalAddress.PostalAddressNotFound;
import br.com.munieri.infrastructure.repository.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostalAddressServiceImpl implements PostalAddressService {

    @Autowired
    private AddressRepository repository;

    @Override
    public PostalAddress find(CEP cep) {
        PostalAddress address = findPostallAddress(cep);

        if(address == null) {
            throw new PostalAddressNotFound();
        }

        return address;
    }

    private PostalAddress findPostallAddress(CEP cep) {
        PostalAddress address = null;

        int supplementZerosCount = 1;
        do {
            address = repository.findByCep(cep.completeCode());
            cep = tryOtherCEP(cep, supplementZerosCount++);

        } while (address == null && cep.completeCode().length() > supplementZerosCount);

        return address;
    }

    /**
     * Gera um novo CEP adicionando zero(s) a direita a partir de um CEP especifico.<br />
     * Caso seja informado 0153599, o novo CEP retornado sera 0153590 e assim por diante
     */
    private CEP tryOtherCEP(CEP cep, int supplementZerosCount) {
        String completeCode = cep.completeCode();

        String zeros = addZeroToTheRigth(supplementZerosCount);
        String cepCode = completeCode.substring(0, completeCode.length() - supplementZerosCount) + zeros;
        return CEP.from(cepCode);
    }

    private String addZeroToTheRigth(int supplementZerosCount) {
        String zeros = "";
        while (zeros.length() < supplementZerosCount) {
            zeros = zeros + "0";
        }
        return zeros;
    }
}