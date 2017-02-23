package br.com.caelum.leilao.teste;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.modelo.Leilao;
import br.com.caelum.leilao.modelo.Usuario;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;

public class LeilaoRestServiceTest {
	@Test
	public void deveRetornarUmLeilaoJSON() {
		JsonPath jsonPath = given()
				.header("accept", "application/json")
				.param("leilao.id",1)
				.get("/leiloes/show")
				.jsonPath();
	
		Leilao leilao = jsonPath.getObject("leilao", Leilao.class);
		
		 Usuario mauricio = new Usuario(1l, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
		 Leilao leilaoEsperado = new Leilao(1l, "Geladeira", 800.0, mauricio, false);

		assertEquals(leilaoEsperado, leilao);
	}
	
	@Test
	public void deveRetornarQuantidadeTotalDeLeiloesXML() {
		XmlPath xmlPath = given()
				.header("accept", "application/xml")
				.get("/leiloes/total")
				.xmlPath();
		
		int quantidadeDeLeiloes = xmlPath.getInt("int");
		
		assertEquals(2, quantidadeDeLeiloes);
	}
}
