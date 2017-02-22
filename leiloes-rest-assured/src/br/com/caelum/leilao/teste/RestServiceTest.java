package br.com.caelum.leilao.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.modelo.Usuario;

import com.jayway.restassured.path.xml.XmlPath;

import static com.jayway.restassured.RestAssured.*;

public class RestServiceTest {

	@Test
	public void deveRetornarDoisUsuariosXML() {
		
		XmlPath xmlPath = given()
				.header("Accept", "application/xml")
				.get("/usuarios?_format=xml").xmlPath();
		
//		Usuario mauricioXML = xmlPath.getObject("list.usuario[0]", Usuario.class);
//		Usuario guilhermeXML = xmlPath.getObject("list.usuario[1]", Usuario.class);

		List<Usuario> usuarios= xmlPath.getList("lista.usuario", Usuario.class);
		
		Usuario mauricio = new Usuario(1L, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
		Usuario guilherme = new Usuario(2L, "Guilherme Silveira", "guilherme.silveira@caelum.com.br");
		
		assertEquals(mauricio, usuarios.get(0));
		assertEquals(guilherme, usuarios.get(1));		
	}

}
