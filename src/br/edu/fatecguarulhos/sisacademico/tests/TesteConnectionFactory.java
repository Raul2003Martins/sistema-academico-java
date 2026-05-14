package br.edu.fatecguarulhos.sisacademico.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import br.edu.fatecguarulhos.sisacademico.utils.ConnectionFactory;

class TesteConnectionFactory {

	@Test
	void testarConexaoComBanco() {
		assertDoesNotThrow(()->{
			Connection conn = ConnectionFactory.getConnection();
			conn.close();
		});
	}

}
