# Projeto 2 Dev-Agro

1. Introdução
Considerando os assuntos estudados em todas as semanas anteriores (tipos de dados, operadores, orientação a objetos, tratamento de exceções, SQL, Maven, Spring, Spring Boot, Spring MVC, Spring Data) tente aplicar todos os conceitos estudados no problema abordado abaixo.

2. Requisitos da Aplicação:
 - A aplicação que deverá ser realizada individualmente deve contemplar os seguintes requisitos:
 - O sistema deve ser desenvolvido em Java com Spring.
 - O sistema deve utilizar banco de dados PostgreSQL.
 - O sistema deve seguir o Roteiro da Aplicação.
 - O sistema deve ser uma API que se comunica com outros serviços (por exemplo, front-end web ou Postman) através de requisições HTTP.


3. Roteiro da aplicação:

Você está prestes a entrar para o time de Desenvolvedores da DevInHouse Agro. Para concretizar a sua contratação, você deverá resolver um desafio utilizando os conceitos ensinados em aula. O time de recrutamento necessita que você crie uma aplicação protótipo para o gerenciamento de propriedades rurais, chamada DEV-Agro.

Neste primeiro projeto você deve desenvolver a API de um sistema de gerenciamento de propriedades rurais chamado DEV-Agro.

#

Na aplicação devem existir: 

 - Um endpoint para retornar a lista completa de empresas cadastradas.
 http://localhost:8080/empresas
 
 - Um endpoint que retorna a lista de fazendas de uma empresa.
http://localhost:8080/empresas/1/listaFazenda

 - Um endpoint que retorna a quantidade de fazendas de uma empresa.
http://localhost:8080/empresas/1/qtdefazenda

 - Um endpoint que retorna uma lista de fazendas de uma empresa, onde cada elemento da lista deve ter 3 atributos: ID da fazenda, nome da fazenda e a data prevista da próxima colheita (considerando a data da última colheita e o tempo médio de colheita do grão associado a essa fazenda).
http://localhost:8080/empresas/1/mostraFazenda

 - Um endpoint para registrar colheita em uma fazenda, que aumenta o estoque de grãos daquela fazenda.
 obs: eu tinha criado um endpoint específico mas não estava realizando a operação, por isso em fazenda existe a variavel valor;
http://localhost:8080/fazendas/1/atualizar

 - Um endpoint para registrar retirada de grãos de uma fazenda, que diminui o estoque de grãos daquela fazenda.(Mesmo caso da de cima)
http://localhost:8080/fazendas/1/atualizar

 - Um endpoint que retorna a lista de grãos de uma empresa.
http://localhost:8080/empresas/1/listaGrao

 - Um endpoint que retorna a lista de grãos associados a uma empresa, onde cada elemento da lista deve conter: nome do grão e quantidade em estoque, ordenado de menor para maior quantidade em estoque.
http://localhost:8080/empresas/1/mostraGrao

 - Um endpoint que retorna a lista de funcionários de uma empresa.
http://localhost:8080/empresas/1/listaFuncionario

 - Um endpoint que retorna a quantidade de funcionários de uma empresa.
http://localhost:8080/empresas/1/qtdefuncionario

#
Curso DevInHouse SenaiSC  Senior
