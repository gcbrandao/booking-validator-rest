# booking-validator-rest

api feita em springBoot que recebe datas de checkIn e checkOut em baseado nas regras de negocio retorna o numero
de semanas entre as datas e a quantidade de noites extras antes ou apos o numero de semanas.


Intruções de instalação e uso
Projeteto feito com:  maven / SpringBoot 2.2.2.RELEASE / JUnit


1 - Clone esse projeto em um diretorio de dua maquina

2 - Navegue ate a pasta raiz do projeto - [cd booking-validator-rest]
  
3 - Execute o comando maven: [mvn clean install] - Apos a execução do comando o maven ira gerar o .jar executavel
  
4 - Execute o comando [java -jar target/booking-validator-rest-0.0.1.jar] para iniciar a aplicação 

5 - A aplicação criara um endpoint no endereço: http://localhost:8080/bookings

6 - Ela espera receber uma requisição POST e receberá um json no seguinte formato:
  
{
        "checkin" : "2020-01-07",
        "checkout": "2020-01-14"
}

7 - Na raiz do projeto tem o arquivo [Booking Validator Rest.postman_collection.json]. Essejson pode ser importado no programa Postman e nele ja vem com a chamada a API configurada.


  

