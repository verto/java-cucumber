Feature: Login

    Scenario: Logando em meu site
        Given Eu esteja na pagina de login da aplicacao
        When eu tento logar com o login "verto" e senha "123"
        Then devera apresentar a pagina incial como logado

