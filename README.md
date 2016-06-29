# Serviço de Busca de CEP

##Sistema que compreende:
- Busca de endereços pelo CEP
- Salvar endereço do usuario, seguindo as regras de CRUD

##Tecnologias utilizadas:
- Spring 4
- Spring-data
- Hibernate
- Jackson 2
- Embedded Jetty 9 (br.com.munieri.boot.Main.java)

### Para os testes Unitarios
- testng
- Mockito

### Para os testes Integrados
- Rest-assured

### Banco de dados
O Banco de dados esta configurado no pacote [br.com.munieri.boot].
Foi utilizado um banco in-memory(hsqldb) para fins de teste e persistencia.


## Serviços
###Serviço de busca de CEP
O servico esta documentado atraves do teste integrado em PostalAddressEndpointIT.java
O CEP oficial 77599999 nao possui nem endereço e nem bairro, apenas cidade e estado.
Este CEP é de uma única cidade do Tocantins.

###Operacao de CRUD no Endereco do usuario
O servico esta documentado atraves do teste integrado em UserAddressEndpointIT.java