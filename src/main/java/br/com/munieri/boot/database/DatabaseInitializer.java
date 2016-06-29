package br.com.munieri.boot.database;

import br.com.munieri.infrastructure.repository.address.AddressEntity;
import br.com.munieri.infrastructure.repository.address.AddressRepository;
import br.com.munieri.infrastructure.repository.district.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("staging")
public class DatabaseInitializer {

    @Autowired
    private Environment env;

    @Autowired
    private AddressRepository repository;

    @PostConstruct
    public void init() {
        AddressEntity entity = new AddressEntity("13300055", "Rua Floriano Peixoto - de 911/912 ao fim", "Centro", new City("Itu", "SP"));
        repository.save(entity);

        entity = new AddressEntity("05426100", "Avenida Brigadeiro Faria Lima - até 1025 - lado ímpar", "Pinheiros", new City("Sao Paulo", "SP"));
        repository.save(entity);

        entity = new AddressEntity("13309020", "Rua Vinte e Quatro de Fevereiro", "Vila Nova", new City("Itu", "SP"));
        repository.save(entity);

        entity = new AddressEntity("77500000", null, null, new City("Porto Nacional", "TO"));
        repository.save(entity);
    }

}