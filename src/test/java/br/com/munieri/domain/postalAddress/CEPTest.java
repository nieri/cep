package br.com.munieri.domain.postalAddress;

import br.com.munieri.domain.cep.CEP;
import br.com.munieri.domain.cep.InvalidCEP;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class CEPTest {

    @Test
    public void should_instantiate_from_static_method() {
        CEP cep = CEP.from("01535001");

        String suffix = cep.suffix();
        assertEquals(suffix, "001");
    }

    @Test
    public void should_throw_InvalidCEP_if_cep_has_nine_digits() {
        try {
            CEP.from("123456789");
            fail();
        } catch (InvalidCEP e) {
            assertEquals(e.getMessage(), "CEP [123456789] invalido");
        }
    }

    @Test
    public void should_throw_InvalidCEP_if_cep_has_seven_digits() {
        try {
            CEP.from("1234567");
            fail();
        } catch (InvalidCEP e) {
            assertEquals(e.getMessage(), "CEP [1234567] invalido");
        }
    }

    @Test
    public void should_throw_InvalidCEP_if_cep_contains_chars() {
        try {
            CEP.from("01535X01");
            fail();
        } catch (InvalidCEP e) {
            assertEquals(e.getMessage(), "CEP [01535X01] invalido");
        }
    }

    @Test
    public void should_throw_InvalidCEP_if_cep_empty() {
        try {
            CEP.from("");
            fail();
        } catch (InvalidCEP e) {
            assertEquals(e.getMessage(), "CEP [] invalido");
        }
    }

    @Test
    public void should_instantiate_if_cep_contains_dash() {
        CEP cep = CEP.from("01535-001");
        assertEquals(cep.suffix(), "001");
        assertEquals(cep.completeCode(), "01535001");
    }

}