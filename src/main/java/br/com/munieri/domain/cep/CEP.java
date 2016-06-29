package br.com.munieri.domain.cep;

/**
 * Value Object de CÓDIGO DE ENDEREÇAMENTO POSTAL<br /><br />
 */
public final class CEP {

    public static final int DEFAULT_CEP_LENGTH = 8;

    private String prefix;
    private String suffix;

    private CEP(String cep) {
        cep = checkIntegrity(cep);

        suffix = cep.substring(5);
        prefix = cep.substring(0, 5);
    }

    /**
     * Instancia um novo {@link CEP}. Para ser valido ele deve possuir 8 digitos.
     * Apenas o caracter - (traço) eh aceito<br />
     * Este metodo pode lancar um {@link IllegalArgumentException} caso o parametro esteja incorreto
     * @param cep
     * @return
     */
    public static CEP from(String cep) {
        return new CEP(cep);
    }

    /**
     * Retorna apenas o sufixo do CEP.<br />
     */
    public String suffix() {
        return suffix;
    }

    /**
     * Retorna apenas o prefixo do CEP.<br />
     */
    public String prefix() {
        return prefix;
    }

    /**
     * Retorna o codigo do CEP sem o traço. <br />
     */
    public String completeCode() {
        return prefix() + suffix();
    }

    private String checkIntegrity(String cep) {

        CEPValidator validator = new CEPValidator();
        cep = validator.validate(cep);

        return cep;
    }
}