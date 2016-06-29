package br.com.munieri.domain.cep;

import org.springframework.util.StringUtils;

public class CEPValidator {

    public String validate(String cep) {
        checkEmpty(cep);
        cep = removeSlash(cep);
        checkLenght(cep);
        checkOnlyNumbers(cep);

        return cep;
    }

    private void checkOnlyNumbers(String cep) {
        if(!hasOnlyNumbers(cep)){
            invalidCep(cep);
        }
    }

    private void checkEmpty(String cep) {
        if(StringUtils.isEmpty(cep)){
            invalidCep(cep);
        }
    }

    private boolean hasOnlyNumbers(String cep) {
        String regex = "^\\d+$";
        return cep.matches(regex);
    }

    public void checkLenght(String cep) {
        if (cep.length() != CEP.DEFAULT_CEP_LENGTH) {
            invalidCep(cep);
        }
    }

    private String removeSlash(String cep) {
        cep = cep.replace("-", "");
        return cep;
    }

    private static void invalidCep(String cep) {
        throw new InvalidCEP("CEP [" + cep + "] invalido");
    }
}
