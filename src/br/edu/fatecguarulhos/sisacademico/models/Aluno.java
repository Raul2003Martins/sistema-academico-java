package br.edu.fatecguarulhos.sisacademico.models;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Aluno {
	
	//Atributos
	private int rgm;
	private String nome;
	private String email;
	private String cpf;
	private String endereco;
	private String municipio;
	private String uf;
	private String celular;
	private LocalDate dataNascimento;
	private Curso curso;
	//Construtor
	public Aluno() {}
	
	public Aluno(int rgm, String nome, String email, String cpf, String endereco, String municipio, String uf,String celular) {
		this.rgm = rgm;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.endereco = endereco;
		this.municipio = municipio;
		this.uf = uf;
		this.celular = celular;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	//Getters e Setters
	public int getRgm() {
		return rgm;
	}

	public void setRgm(int rgm) {
		this.rgm = rgm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	public void setDataNascimento(int dia, int mes, int ano) {
		if(ano < 1900 || ano > LocalDateTime.now().getYear())
			throw new RuntimeException("Ano digitado é inválido");
		try {
		
		dataNascimento = LocalDate.of(ano, mes, dia);
		if(dataNascimento.isAfter(LocalDate.now()))
			throw new RuntimeException("Data digitada é inválida");
		}
		catch(DateTimeException dte) {
			if(dte.getMessage().contains("MonthOfYear"))
				throw new RuntimeException("Mês digitado é inválido");
			if(dte.getMessage().contains("DayOfMonth"))
				throw new RuntimeException("Dia do mês digitado é inválido");
			
		}
		}
	public String getDataNascimentoFormatada() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return dataNascimento.format(formato);
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String data) {
	    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    dataNascimento = LocalDate.parse(data, formato);
	}
	
	public void validarDados() {
	    if(nome == null || nome.isBlank())
	        throw new RuntimeException("Nome não pode estar vazio");

	    if(email == null || email.isBlank())
	        throw new RuntimeException("Email não pode estar vazio");

	    if(cpf == null || cpf.isBlank())
	        throw new RuntimeException("CPF não pode estar vazio");
	}
}
