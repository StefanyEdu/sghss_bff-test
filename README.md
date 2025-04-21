# SGHSS

Projeto de testes funcionais do projeto [https://github.com/StefanyEdu/sghss_bff-test](link_do_projeto_de_desenvolvimento).

## Modelo para criação de uma branch

```
[yyyy-mm-dd]-[código da tarefa]-[descrição da task]
```

## Estrutura do projeto

### Passo 1- Configuração da Url.

##### O arquivo onde será configurada a url do projeto se chama configuration-hom.yml que está dentro do diretório resources.

###### Caminho do arquivo: src/test/resources/configuration-hom.yml

### Passo 2- Configuração do Enun.

##### Após a criação da variavél dentro arquivo configuration-hom.yml, você deve informar o nome da variavél criada no arquivo chamado Servico que está no seguinte caminho:

###### Caminho do arquivo: src/main/java/enums/Servico.java

### Passo 3- Criação de uma constante para informar qual endpoint será testado.

##### Deverá ser criado uma classe dentro do pacote constants que está no seguinte caminho:

###### Caminho do arquivo: src/main/java/constants

Obs.: O nome da classe poderá ser o mesmo nome da controller que esse endpoint está e o nome da classe sempre ternimar com Constants.
Exemplo: NomeControllerConstants

### Passo 4- Criação do método de request

##### Deverá ser criado uma classe dentro do pacote basetest que está no seguinte caminho:

###### Caminho do arquivo: src/main/java/basetest

Obs.: O nome da classe poderá ser o mesmo nome da controller que esse endpoint está e o nome da classe sempre ternimar com BaseTest.
Exemplo: NomeControllerBaseTest

### Passo 5- Criação do método dos testes

##### Deverá ser criado uma classe dentro do pacote funcional que está no seguinte caminho:

###### Caminho do arquivo: src/test/java/testcases/funcional

Obs.: O nome da classe poderá ser o mesmo nome da controller que esse endpoint está e o nome da classe sempre ternimar com Test.
Exemplo: NomeControllerTest

### Utilização de DTO e DataFactory

### Criação do DTO

##### Deverá ser criado uma classe dentro do dto que está no seguinte caminho:

###### Caminho do arquivo: src/main/java/dto

Obs.: O nome da classe poderá ser o mesmo nome do DTO que está sendo informado no Swagger e o nome da classe sempre ternimar com DTO.
Exemplo: CreateProductDTO

### Criação do Datafactory

##### Deverá ser criado uma classe dentro do dto que está no seguinte caminho:

###### Caminho do arquivo: src/main/java/datafactory

Obs.: O nome da classe poderá ser o mesmo nome do DTO que será utilizado no DataFactory e o nome da classe sempre ternimar com DataFactory.
Exemplo: CreateProductDataFactory

## Rodar pipeline local

Este projeto usa maven, execute seus testes digitando o comando abaixo no terminal do IntelliJ IDEA:

```
$ mvn test -Psghss-bff-auto -DtestSuite=FuncionalSuite -Denv=dev
```

```
$ mvn test -Psghss-bff-auto -DtestSuite=E2ESuite -Denv=dev
```

```
$ mvn test -Psghss-bff-auto -DtestSuite=ContratoSuite -Denv=dev
```

```
$ mvn test -Psghss-bffs-auto -DtestSuite=HealthCheckSuite -Denv=dev
```

```
$ mvn test -Psghss-bff-auto -DtestSuite=E2ESuite -Denv=hom
```

```
$ mvn test -Psghss-bff-auto -DtestSuite=FuncionalSuite -Denv=hom
```

```
$ mvn test -Psghss-bff-auto -DtestSuite=ContratoSuite -Denv=hom
```

```
$ mvn test -Psghss-bff-auto -DtestSuite=HealthCheckSuite -Denv=hom
```

## Modelo para descrição de um commit

```
[yyyy-mm-dd]-[descrição do que está sendo comitado]
```

## Modelo para descrição de um Merge

```
Colocar título da tarefa.
Colocar observação se houver.

Testes implementados:
* nome do método();
* nome do método();

[código da tarefa][código da tarefa]
```
