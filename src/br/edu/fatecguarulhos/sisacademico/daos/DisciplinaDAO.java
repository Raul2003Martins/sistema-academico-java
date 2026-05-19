package br.edu.fatecguarulhos.sisacademico.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.edu.fatecguarulhos.sisacademico.models.Disciplina;
import br.edu.fatecguarulhos.sisacademico.utils.ConnectionFactory;

public class DisciplinaDAO {
	public Disciplina buscarDisciplina(Integer codigo) throws Exception {

	    Disciplina disciplina = new Disciplina();

	    String query = "SELECT "
	            + "nome, faltas, nota, semestre "
	            + "FROM disciplina WHERE codigo = ?";

	    try {

	        Connection connection = ConnectionFactory.getConnection();

	        PreparedStatement pstmt = connection.prepareStatement(query);

	        pstmt.setInt(1, codigo);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {

	            disciplina.setCodigo(codigo);
	            disciplina.setNome(rs.getString(1));
	            disciplina.setFaltas(rs.getInt(2));
	            disciplina.setNota(rs.getFloat(3));
	            disciplina.setSemestre(rs.getString(4));

	        } else {

	            disciplina = null;
	        }

	        connection.close();

	        if (disciplina == null)
	            throw new Exception("Disciplina não encontrada");

	        return disciplina;

	    } catch (SQLException e) {

	        throw e;
	    }
	}
	
	public int inserirDisciplina(Disciplina disciplina) throws Exception {
		disciplina.validarDados();
		String sql = "INSERT INTO disciplina "
				+ "(codigo, nome, faltas, nota, semestre) "
				+ "VALUES (?,?,?,?,?)";
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, disciplina.getCodigo());
			pstmt.setString(2, disciplina.getNome());
			pstmt.setInt(3, disciplina.getFaltas());
			pstmt.setFloat(4, disciplina.getNota());
			pstmt.setString(5, disciplina.getSemestre());
			int valoresInseridos = pstmt.executeUpdate();
			connection.close();
			return valoresInseridos;
		}
		catch (SQLException e) {
			if(e.getErrorCode() == 1062 && e.getMessage().contains(String.valueOf(disciplina.getCodigo())))
				throw new Exception("Disciplina com este codigo já cadastrado.");
			else throw e;
		}
	}
	
	public void deletarDisciplina(Disciplina disciplina) {
		String sql = "DELETE FROM disciplina WHERE codigo = ?";
		try {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, disciplina.getCodigo());
			pstmt.execute();
			connection.close();	
			}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	public Disciplina buscarDisciplinaPorNome(String nome)
			throws Exception {

		Disciplina disciplina = new Disciplina();

		String query =
				"SELECT codigo, faltas, nota, semestre " +
				"FROM disciplina WHERE nome = ?";

		try {

			Connection connection =
					ConnectionFactory.getConnection();

			PreparedStatement pstmt =
					connection.prepareStatement(query);

			pstmt.setString(1, nome);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {

				disciplina.setCodigo(rs.getInt(1));
				disciplina.setNome(nome);
				disciplina.setFaltas(rs.getInt(2));
				disciplina.setNota(rs.getFloat(3));
				disciplina.setSemestre(rs.getString(4));

			}
			else {
				disciplina = null;
			}

			connection.close();

			if(disciplina == null)
				throw new Exception("Disciplina não encontrada");

			return disciplina;

		}
		catch(SQLException e) {
			throw e;
		}
	}
	
	public void atualizarDisciplina(Disciplina disciplina) {

	    disciplina.validarDados();

	    String sql = "UPDATE disciplina SET "
	            + "nome = ?, faltas = ?, nota = ?, semestre = ? "
	            + "WHERE codigo = ?";

	    try {

	        Connection connection = ConnectionFactory.getConnection();

	        PreparedStatement pstmt = connection.prepareStatement(sql);

	        pstmt.setString(1, disciplina.getNome());
	        pstmt.setInt(2, disciplina.getFaltas());
	        pstmt.setFloat(3, disciplina.getNota());
	        pstmt.setString(4, disciplina.getSemestre());
	        pstmt.setInt(5, disciplina.getCodigo());

	        pstmt.execute();

	        connection.close();

	    } catch (Exception e) {

	        throw new RuntimeException(e);
	    }
	}
	
	}
