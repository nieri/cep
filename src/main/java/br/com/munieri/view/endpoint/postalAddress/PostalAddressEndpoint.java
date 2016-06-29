package br.com.munieri.view.endpoint.postalAddress;

import br.com.munieri.domain.cep.CEP;
import br.com.munieri.domain.cep.InvalidCEP;
import br.com.munieri.domain.postalAddress.PostalAddress;
import br.com.munieri.domain.postalAddress.PostalAddressNotFound;
import br.com.munieri.domain.postalAddress.service.PostalAddressService;
import br.com.munieri.view.endpoint.ErroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping
public class PostalAddressEndpoint {

    @Autowired
    private PostalAddressService service;

    @RequestMapping(value = "/postal_address", method = RequestMethod.GET)
    public ResponseEntity get(@RequestParam("cep") String cep) {
        PostalAddress postalAddress = service.find(CEP.from(cep));
        return parseToResponse(postalAddress);
    }

    @ExceptionHandler(InvalidCEP.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDTO invalidCepHandler(InvalidCEP ex, HttpServletResponse response) {
        return new ErroDTO("cep_invalid", "CEP invalido");
    }

    @ExceptionHandler(PostalAddressNotFound.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ResponseBody
    public ErroDTO postalAddressNotFoundHandler(PostalAddressNotFound ex, HttpServletResponse response) {
        return new ErroDTO("postal_address_not_found", "CEP nao encontrado");
    }

    private ResponseEntity parseToResponse(PostalAddress postalAddress) {
        return new ResponseEntity<>(new PostalAddressDTO(postalAddress), HttpStatus.OK);
    }

}