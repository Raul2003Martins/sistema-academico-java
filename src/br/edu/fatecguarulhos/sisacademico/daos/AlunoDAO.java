package br.edu.fatecguarulhos.sisacademico.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

import br.edu.fatecguarulhos.sisacademico.models.Aluno;
import br.edu.fatecguarulhos.sisacademico.utils.ConnectionFactory;

public class AlunoDAO {
	public Aluno buscarAluno(Integer RGM) throws Exception{
		Aluno aluno = new Aluno();
		String query = "SELECT "
				+ "uf, nome, cpf, email, endereco, municipio, celular "
				+ "FROM aluno WHERE rgm = ?";
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, RGM);
			ResultSet rs = pstmt.executeQuery();
			if(rs.first()) {
				aluno.setRgm(RGM);
				aluno.setNome(rs.getString(1));
				aluno.setCpf(rs.getString(2));
			}
			connection.close();
		}
		catch (SQLException e) {
			throw new Exception("Erro no banco de dados");
		}
		return aluno;
	} 
	
	public int inserirAluno( Aluno aluno) throws Exception {
		String sql = "INSERT INTO aluno "
				+ "(rgm, nome, email, cpf, endereco, municipio, uf, celular) "
				+ "VALUES (?,?,?,?,?,?,?,?)";
		try{
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, aluno.getRgm());
			pstmt.setString(2, aluno.getNome());
			pstmt.setString(3, aluno.getEmail());
			pstmt.setString(4, aluno.getCpf());
			pstmt.setString(5, aluno.getEndereco());
			pstmt.setString(6, aluno.getMunicipio());
			pstmt.setString(7, aluno.getUf());
			pstmt.setString(8, aluno.getCelular());
			int valoresInseridos = pstmt.executeUpdate();
			connection.close();
			return valoresInseridos;
		}
		catch (SQLException e) {
			throw e;
		}
	}
	
	public void deletarAluno(Aluno aluno) {
		deletarNotas();
	}
	private void deletarNotas() {}
}
