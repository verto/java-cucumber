package com.couggi.integration;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import cuke4duke.annotation.After;
import cuke4duke.annotation.Before;
import cuke4duke.annotation.Pending;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import cuke4duke.spring.StepDefinitions;

@StepDefinitions
public class LoginSteps {

	private WebDriver webDriver;
	private  WebDriverWait wait;
	
	
	@Before
	public void before() { 
		webDriver = new HtmlUnitDriver(true);
		wait = new WebDriverWait(webDriver, 60);
	}
	
	@After
	public void after() { 
		if (webDriver != null) { 
			webDriver.quit();
		}
	}
	
	@Given ("^Eu esteja na pagina de login da aplicacao$")
	public void euEstejaNaPaginaDeLoginDaAplicacao() {
		 webDriver.get("http://localhost:8080/projeto/login");
	
	 }
	
	@When ("^eu tento logar com o login \"([^\"]*)\" e senha \"([^\"]*)\"$")
	public void euTentoLogarComOLoginVertoESenha123_(String arg1, String arg2) {
		webDriver.findElement(By.name("login")).sendKeys(arg1);
		webDriver.findElement(By.name("senha")).sendKeys(arg2);
		webDriver.findElement(By.name("logar")).click();
	}
	
	@Then ("^devera apresentar a pagina incial como logado$")
	public void deveraApresentarAPaginaIncialComoLogado() {
		Assert.assertEquals("verto logado",webDriver.findElement(By.id("logado")).getText());
	}

}
