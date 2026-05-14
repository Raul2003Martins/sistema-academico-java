package br.edu.fatecguarulhos.sisacademico.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.edu.fatecguarulhos.sisacademico.daos.AlunoDAO;
import br.edu.fatecguarulhos.sisacademico.models.Aluno;

class TesteAlunoDAO {

	@Test
	void testBuscarAluno() {
		fail("Not yet implemented");
	}

	@Test
	void testInserirAluno() {
		AlunoDAO dao = new AlunoDAO();
		assertDoesNotThrow(()->{
			Aluno aluno = new Aluno(1,"Fulano","fulano@email","123", "Rua tal", "Cidadinha", "SP", "1190000");
			
			assertTrue(dao.inserirAluno(aluno) == 1);
		});
		
		
	}

	@Test
	void testDeletarAluno() {
		fail("Not yet implemented");
	}

}
