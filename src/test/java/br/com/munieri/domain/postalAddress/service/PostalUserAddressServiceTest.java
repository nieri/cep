package br.com.munieri.domain.postalAddress.service;

import br.com.munieri.domain.cep.CEP;
import br.com.munieri.domain.postalAddress.PostalAddress;
import br.com.munieri.domain.postalAddress.PostalAddressNotFound;
import br.com.munieri.infrastructure.repository.address.AddressEntity;
import br.com.munieri.infrastructure.repository.address.AddressRepository;
import br.com.munieri.infrastructure.repository.district.City;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class PostalUserAddressServiceTest {

    @InjectMocks
    PostalAddressServiceImpl service;

    @Mock
    AddressRepository addressRepository;

    @BeforeMethod
    public void init(){
        service = new PostalAddressServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = PostalAddressNotFound.class)
    public void should_throw_exception_when_address_does_not_exist(){
        CEP cep = CEP.from("01535001");
        when(addressRepository.findByCep(anyString())).thenReturn(null);

        service.find(cep);
    }

    @Test
    public void should_return_postal_address_from_correct_CEP(){
        CEP cep = CEP.from("01535001");
        AddressEntity mock = new AddressEntity("13300055", "Rua Floriano Peixoto", "Centro", new City("Itu", "SP"));
        mock.setId(1L);
        when(addressRepository.findByCep(eq(cep.completeCode()))).thenReturn(mock);

        PostalAddress postalAddress = service.find(cep);

        assertEquals(postalAddress.address(), "Rua Floriano Peixoto");
    }

    @Test
    public void should_return_district_postal_address_from_correct_CEP(){
        CEP cep = CEP.from("13300055");
        AddressEntity mock = new AddressEntity("13300055", "Rua Floriano Peixoto", "Centro", new City("Itu", "SP"));
        mock.setId(1L);
        when(addressRepository.findByCep(eq(cep.completeCode()))).thenReturn(mock);

        PostalAddress postalAddress = service.find(cep);

        assertEquals(postalAddress.city().getDescription(), "Itu");
    }

    @Test
    public void should_return_postal_address_from_CEP_with_zero_at_the_end(){
        CEP cep = CEP.from("13309020");
        AddressEntity mock = new AddressEntity("13309020", "Rua Vinte e Quatro de Fevereiro", "Vila Nova", new City("Itu", "SP"));
        mock.setId(1L);
        when(addressRepository.findByCep(eq(cep.completeCode()))).thenReturn(null);
        when(addressRepository.findByCep(eq("13309020"))).thenReturn(mock);

        PostalAddress postalAddress = service.find(cep);

        assertEquals(postalAddress.address(), "Rua Vinte e Quatro de Fevereiro");
    }

    @Test
    public void should_return_postal_address_from_CEP_with_double_zero_at_the_end(){
        CEP cep = CEP.from("05426122");
        AddressEntity mock = new AddressEntity("05426100", "Avenida Brigadeiro Faria Lima", "Pinheiros", new City("Sao Paulo", "SP"));
        mock.setId(1L);
        when(addressRepository.findByCep(eq(cep.completeCode()))).thenReturn(null);
        when(addressRepository.findByCep(eq("05426122"))).thenReturn(null);
        when(addressRepository.findByCep(eq("05426120"))).thenReturn(null);
        when(addressRepository.findByCep(eq("05426100"))).thenReturn(null);
        when(addressRepository.findByCep(eq("05426000"))).thenReturn(mock);

        PostalAddress postalAddress = service.find(cep);

        assertEquals(postalAddress.address(), "Avenida Brigadeiro Faria Lima");
    }
}