package br.edu.fatecguarulhos.sisacademico.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.edu.fatecguarulhos.sisacademico.daos.AlunoDAO;
import br.edu.fatecguarulhos.sisacademico.daos.CursoDAO;
import br.edu.fatecguarulhos.sisacademico.daos.DisciplinaDAO;
import br.edu.fatecguarulhos.sisacademico.models.Aluno;
import br.edu.fatecguarulhos.sisacademico.models.Curso;
import br.edu.fatecguarulhos.sisacademico.models.Disciplina;

public class UIPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField txtNome;
	private JTextField txtRgm;
	private JTextField txtEmail;
	private JTextField txtEnd;
	private JTextField txtMunicipio;
	private JTextField txtCelular;
	private JTextField txtRgmCurso;
	private JTextField txtRgm2;
	private JTextField txtCurso2;
	private JTextField txtNome2;
	private JTextField txtNota;
	private JTextField txtFaltas;

	private JFormattedTextField txtCpf;
	private JFormattedTextField txtData;

	private JComboBox<String> comboBoxUf;

	private AlunoDAO alunoDAO = new AlunoDAO();
	private CursoDAO cursoDAO = new CursoDAO();
	private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	
	// para uso na área de cursos
	private String[][] disciplinas;
	// uso para criar o codigo curso
	private int idSemestre, idCurso, idDisciplina, idAluno;
	
	
	private String gerarCodigoDisciplina() {
		return String.valueOf(idCurso) + String.valueOf(idSemestre) + String.valueOf(idDisciplina) + String.valueOf(idAluno);
	}
	private String[][] getArrayDisciplinas(String curso) {
		
		// =========================================================================
        // 1. ANÁLISE E DESENVOLVIMENTO DE SISTEMAS (ADS) - 6 Semestres
        // =========================================================================
        String[][] ads = new String[6][];
        
        // 1º Semestre (7 matérias)
        ads[0] = new String[]{
            "Algoritmos e Lógica de Programação", "Modelagem de Banco de Dados", 
            "Estruturas de Computadores", "Fundamentos da Rede de Computadores", 
            "Comunicação e Expressão", "Inglês I", "Matemática Discreta"
        };
        // 2º Semestre (7 matérias)
        ads[1] = new String[]{
            "Engenharia de Software I", "Linguagem de Programação", 
            "Sistemas Operacionais", "Contabilidade", 
            "Inglês II", "Cálculo", "Metodologia da Pesquisa Científico-Tecnológica"
        };
        // 3º Semestre (7 matérias)
        ads[2] = new String[]{
            "Engenharia de Software II", "Estrutura de Dados", 
            "Sistemas de Informação", "Programação Linear", 
            "Inglês III", "Estatística Aplicada", "Sociedade e Tecnologia"
        };
        // 4º Semestre (7 matérias)
        ads[3] = new String[]{
            "Engenharia de Software III", "Programação Orientada a Objetos", 
            "Banco de Dados", "Redes de Computadores", 
            "Inglês IV", "Metodologia de Projetos", "Administração Geral"
        };
        // 5º Semestre (6 matérias)
        ads[4] = new String[]{
            "Programação para Web", "Segurança da Informação", 
            "Laboratório de Engenharia de Software", "Sistemas Distribuídos", 
            "Inglês V", "Economia e Finanças"
        };
        // 6º Semestre (6 matérias)
        ads[5] = new String[]{
            "Gestão de Projetos de Software", "Inteligência Artificial", 
            "Tópicos Especiais em Informática", "Inglês VI", 
            "Ética e Responsabilidade Profissional", "Trabalho de Graduação (TG)"
        };


        // =========================================================================
        // 2. LOGÍSTICA - 6 Semestres
        // =========================================================================
        String[][] logistica = new String[6][];
        
        // 1º Semestre (7 matérias)
        logistica[0] = new String[]{
            "Fundamentos de Logística", "Administração Geral", 
            "Contabilidade Geral e Custos", "Matemática Financeira", 
            "Comunicação e Expressão", "Inglês I", "Informática Aplicada"
        };
        // 2º Semestre (7 matérias)
        logistica[1] = new String[]{
            "Gestão de Transportes", "Gestão de Suprimentos", 
            "Estatística Aplicada", "Economia", 
            "Inglês II", "Metodologia da Pesquisa Científico-Tecnológica", "Direito Aplicado aos Negócios"
        };
        // 3º Semestre (7 matérias)
        logistica[2] = new String[]{
            "Logística de Armazenagem e Distribuição", "Gestão de Operações de Serviços", 
            "Planejamento, Programação e Controle da Produção (PPCP)", "Custos Logísticos", 
            "Inglês III", "Comportamento Organizacional", "Sistemas de Informação Gerencial"
        };
        // 4º Semestre (6 matérias)
        logistica[3] = new String[]{
            "Logística Internacional", "Gestão de Frotas e Roteirização", 
            "Pesquisa Operacional Aplicada", "Inglês IV", 
            "Gestão Ambiental e Sustentabilidade", "Gestão da Qualidade"
        };
        // 5º Semestre (6 matérias)
        logistica[4] = new String[]{
            "Estratégia e Planejamento Logístico", "Cadeia de Suprimentos (Supply Chain)", 
            "Logística Reversa", "Inglês V", 
            "Empreendedorismo", "Gerenciamento de Riscos e Seguros"
        };
        // 6º Semestre (5 matérias)
        logistica[5] = new String[]{
            "Tópicos Avançados em Logística", "Negociação Internacional", 
            "Inglês VI", "Ética e Cidadania", "Trabalho de Graduação (TG)"
        };


        // =========================================================================
        // 3. COMÉRCIO EXTERIOR - 6 Semestres
        // =========================================================================
        String[][] comercioExterior = new String[6][];
        
        // 1º Semestre (7 matérias)
        comercioExterior[0] = new String[]{
            "Introdução ao Comércio Exterior", "Teoria do Comércio Internacional", 
            "Geografia Econômica e Geopolítica", "Instituições de Direito Público e Privado", 
            "Comunicação e Expressão", "Inglês I", "Matemática Elementar"
        };
        // 2º Semestre (7 matérias)
        comercioExterior[1] = new String[]{
            "Sistemática de Exportação", "Logística Internacional", 
            "Contabilidade e Análise de Balanços", "Microeconomia", 
            "Inglês II", "Espanhol I", "Estatística Aplicada"
        };
        // 3º Semestre (7 matérias)
        comercioExterior[2] = new String[]{
            "Sistemática de Importação", "Direito do Comércio Internacional", 
            "Macroeconomia", "Cálculo de Preços no Comércio Exterior", 
            "Inglês III", "Espanhol II", "Metodologia da Pesquisa"
        };
        // 4º Semestre (7 matérias)
        comercioExterior[3] = new String[]{
            "Contratos e Financiamento Internacional", "Câmbio e Operações Financeiras", 
            "Marketing Internacional", "Sistemas de Informação em Comércio Exterior", 
            "Inglês IV", "Espanhol III", "Gestão de Custos e Preços"
        };
        // 5º Semestre (6 matérias)
        comercioExterior[4] = new String[]{
            "Acordos Internacionais e Blocos Econômicos", "Negociação Comercial Internacional", 
            "Gestão de Transportes e Seguros", "Inglês V", 
            "Espanhol IV", "Ética e Legislação Aduaneira"
        };
        // 6º Semestre (5 matérias)
        comercioExterior[5] = new String[]{
            "Planejamento Estratégico Internacional", "Tópicos Especiais de Comércio Exterior", 
            "Inglês VI", "Espanhol V", "Trabalho de Graduação (TG)"
        };
		
		if(curso.equals("Comércio Exterior")) {
			idCurso = 10;
			return comercioExterior;
		}
		if(curso.equals("Análise e Desenvolvimento de Sistemas")) {
			idCurso = 20;
			return ads;
		}
			if(curso.equals("Logística")) {
				idCurso = 30;
				return logistica;
			}
			return null;
	}
	
	public void carregarDisciplinasSemestre(String semestre, String[][] disciplinasCurso,
	        JComboBox<String> comboDisciplina) {
			comboDisciplina.removeAllItems();
			String[] semestreFatiado = semestre.split(" ");
			int nSemestre = (Integer.parseInt(semestreFatiado[0])) -1;
			idSemestre = nSemestre + 1;
			for(int i = 0; i < disciplinasCurso[nSemestre].length; i++) {
				comboDisciplina.addItem(disciplinasCurso[nSemestre][i]);
			}
	}
	
	
	private void carregarDisciplinas(String curso,
	        JComboBox<String> comboDisciplina) {

	    comboDisciplina.removeAllItems();

	    if (curso.equals(
	            "Análise e Desenvolvimento de Sistemas")) {

	        comboDisciplina.addItem("Algoritmos e Lógica de Programação");
	        comboDisciplina.addItem("Arquitetura e Organização de Computadores");
	        comboDisciplina.addItem("Banco de Dados");
	        comboDisciplina.addItem("Engenharia de Software");
	        comboDisciplina.addItem("Estrutura de Dados");
	        comboDisciplina.addItem("Linguagem de Programação");
	        comboDisciplina.addItem("Matemática Discreta");
	        comboDisciplina.addItem("Programação Orientada a Objetos");
	        comboDisciplina.addItem("Sistemas Operacionais");
	        comboDisciplina.addItem("Desenvolvimento Web");
	        comboDisciplina.addItem("Desenvolvimento Mobile");
	        comboDisciplina.addItem("Redes de Computadores");
	        comboDisciplina.addItem("Segurança da Informação");
	        comboDisciplina.addItem("Engenharia de Requisitos");
	        comboDisciplina.addItem("Interação Humano Computador");
	        comboDisciplina.addItem("Gestão de Projetos");
	        comboDisciplina.addItem("Inteligência Artificial");
	        comboDisciplina.addItem("Computação em Nuvem");
	        comboDisciplina.addItem("Qualidade de Software");
	        comboDisciplina.addItem("Empreendedorismo");
	    }

	    else if (curso.equals("Logística")) {

	        comboDisciplina.addItem("Gestão da Cadeia de Suprimentos");
	        comboDisciplina.addItem("Armazenagem e Movimentação");
	        comboDisciplina.addItem("Logística Internacional");
	        comboDisciplina.addItem("Gestão de Transportes");
	        comboDisciplina.addItem("Planejamento Logístico");
	        comboDisciplina.addItem("Custos Logísticos");
	        comboDisciplina.addItem("Gestão de Estoques");
	        comboDisciplina.addItem("Pesquisa Operacional");
	        comboDisciplina.addItem("Distribuição Física");
	        comboDisciplina.addItem("Administração Geral");
	        comboDisciplina.addItem("Matemática Financeira");
	        comboDisciplina.addItem("Empreendedorismo");
	    }

	    else if (curso.equals("Comércio Exterior")) {

	        comboDisciplina.addItem("Importação");
	        comboDisciplina.addItem("Exportação");
	        comboDisciplina.addItem("Logística Internacional");
	        comboDisciplina.addItem("Legislação Aduaneira");
	        comboDisciplina.addItem("Economia Internacional");
	        comboDisciplina.addItem("Marketing Internacional");
	        comboDisciplina.addItem("Câmbio");
	        comboDisciplina.addItem("Negociação Internacional");
	        comboDisciplina.addItem("Gestão Portuária");
	        comboDisciplina.addItem("Direito Internacional");
	        comboDisciplina.addItem("Administração Geral");
	        comboDisciplina.addItem("Empreendedorismo");
	    }
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIPrincipal frame = new UIPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UIPrincipal() throws Exception {

		setTitle("Sistema Acadêmico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mnArquivo.add(mntmSalvar);

		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mnArquivo.add(mntmConsultar);

		JMenuItem mntmAlterar = new JMenuItem("Atualizar");
		mntmAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnArquivo.add(mntmAlterar);

		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mnArquivo.add(mntmExcluir);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 780, 410);
		contentPane.add(tabbedPane);

		// =========================
		// DADOS PESSOAIS
		// =========================

		JPanel panelDados = new JPanel();
		panelDados.setLayout(null);
		tabbedPane.addTab("Dados Pessoais", null, panelDados, null);

		JLabel lblRgm = new JLabel("RGM:");
		lblRgm.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblRgm.setBounds(10, 15, 80, 40);
		panelDados.add(lblRgm);

		txtRgm = new JTextField();
		txtRgm.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtRgm.setBounds(90, 15, 120, 40);
		panelDados.add(txtRgm);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNome.setBounds(230, 15, 90, 40);
		panelDados.add(lblNome);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtNome.setBounds(320, 15, 420, 40);
		panelDados.add(txtNome);

		JLabel lblData = new JLabel("Data de Nascimento:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblData.setBounds(10, 80, 220, 40);
		panelDados.add(lblData);

		txtData = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtData.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtData.setBounds(230, 80, 150, 40);
		panelDados.add(txtData);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblCpf.setBounds(440, 80, 70, 40);
		panelDados.add(lblCpf);

		txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtCpf.setBounds(500, 80, 240, 40);
		panelDados.add(txtCpf);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblEmail.setBounds(10, 145, 90, 40);
		panelDados.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtEmail.setBounds(100, 145, 640, 40);
		panelDados.add(txtEmail);

		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblEndereco.setBounds(10, 210, 120, 40);
		panelDados.add(lblEndereco);

		txtEnd = new JTextField();
		txtEnd.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtEnd.setBounds(140, 210, 600, 40);
		panelDados.add(txtEnd);

		JLabel lblMunicipio = new JLabel("Município:");
		lblMunicipio.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblMunicipio.setBounds(10, 280, 120, 40);
		panelDados.add(lblMunicipio);

		txtMunicipio = new JTextField();
		txtMunicipio.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtMunicipio.setBounds(140, 280, 180, 40);
		panelDados.add(txtMunicipio);

		JLabel lblUf = new JLabel("UF:");
		lblUf.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblUf.setBounds(340, 280, 50, 40);
		panelDados.add(lblUf);

		String[] ufs = {
				"AC", "AL", "AP", "AM", "BA", "CE", "DF",
				"ES", "GO", "MA", "MT", "MS", "MG", "PA",
				"PB", "PR", "PE", "PI", "RJ", "RN", "RS",
				"RO", "RR", "SC", "SP", "SE", "TO"
		};

		comboBoxUf = new JComboBox<>(ufs);
		comboBoxUf.setFont(new Font("Tahoma", Font.PLAIN, 23));
		comboBoxUf.setBounds(390, 280, 90, 40);
		panelDados.add(comboBoxUf);

		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblCelular.setBounds(500, 280, 100, 40);
		panelDados.add(lblCelular);

		txtCelular = new JTextField();
		txtCelular.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtCelular.setBounds(584, 280, 156, 40);
		panelDados.add(txtCelular);

		// =========================
		// DADOS CURSO
		// =========================

		JPanel panelCurso = new JPanel();
		panelCurso.setLayout(null);
		tabbedPane.addTab("Dados do Curso", null, panelCurso, null);

		JLabel lblRgmCurso = new JLabel("RGM:");
		lblRgmCurso.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblRgmCurso.setBounds(10, 20, 80, 40);
		panelCurso.add(lblRgmCurso);

		txtRgmCurso = new JTextField();
		txtRgmCurso.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtRgmCurso.setBounds(90, 20, 220, 40);
		panelCurso.add(txtRgmCurso);

		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblCurso.setBounds(10, 90, 90, 40);
		panelCurso.add(lblCurso);

		JComboBox<String> comboCurso = new JComboBox<>();
		comboCurso.setModel(new DefaultComboBoxModel<>(
				new String[] {
						"Análise e Desenvolvimento de Sistemas",
						"Logística",
						"Comércio Exterior"
				}
		));
		comboCurso.setFont(new Font("Tahoma", Font.PLAIN, 23));
		comboCurso.setBounds(100, 90, 600, 40);
		panelCurso.add(comboCurso);
		
		String[] campus = {
				"Adamantina",
				"Americana",
				"Araçatuba",
				"Araraquara",
				"Araras",
				"Assis",
				"Atibaia",
				"Barretos",
				"Barueri",
				"Bauru",
				"Bebedouro",
				"Botucatu",
				"Bragança Paulista",
				"Campinas",
				"Capão Bonito",
				"Carapicuíba",
				"Catanduva",
				"Cotia",
				"Cruzeiro",
				"Diadema",
				"Ferraz de Vasconcelos",
				"Franca",
				"Franco da Rocha",
				"Garça",
				"Guaratinguetá",
				"Guarulhos",
				"Itu",
				"Jacareí",
				"Jundiaí",
				"Mauá",
				"Mogi das Cruzes",
				"Osasco",
				"Praia Grande",
				"Santos",
				"Santo André",
				"São Bernardo do Campo",
				"São Caetano do Sul",
				"São José dos Campos",
				"São Paulo",
				"Sorocaba",
				"Taubaté"
			};

			JLabel lblCampus = new JLabel("Campus:");
			lblCampus.setFont(new Font("Tahoma", Font.PLAIN, 23));
			lblCampus.setBounds(10, 145, 100, 40);
			panelCurso.add(lblCampus);

			JComboBox<String> comboCampus = new JComboBox<>(campus);
			comboCampus.setFont(new Font("Tahoma", Font.PLAIN, 23));
			comboCampus.setBounds(120, 145, 580, 40);
			panelCurso.add(comboCampus);
		
		JLabel lblPeriodo = new JLabel("Período:");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblPeriodo.setBounds(10, 203, 100, 40);
		panelCurso.add(lblPeriodo);

		JRadioButton rbMatutino = new JRadioButton("Matutino");
		rbMatutino.setFont(new Font("Tahoma", Font.PLAIN, 23));
		rbMatutino.setBounds(120, 203, 150, 40);
		panelCurso.add(rbMatutino);

		JRadioButton rbVespertino = new JRadioButton("Vespertino");
		rbVespertino.setFont(new Font("Tahoma", Font.PLAIN, 23));
		rbVespertino.setBounds(280, 203, 170, 40);
		panelCurso.add(rbVespertino);

		JRadioButton rbNoturno = new JRadioButton("Noturno");
		rbNoturno.setFont(new Font("Tahoma", Font.PLAIN, 23));
		rbNoturno.setBounds(470, 203, 150, 40);
		panelCurso.add(rbNoturno);

		ButtonGroup grupoPeriodo = new ButtonGroup();
		grupoPeriodo.add(rbMatutino);
		grupoPeriodo.add(rbVespertino);
		grupoPeriodo.add(rbNoturno);

		// Botões curso

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    CursoDAO cursoDao = new CursoDAO();
                    Curso c = cursoDao.buscarCurso(Integer.parseInt(txtRgmCurso.getText().toString()));
                    comboCurso.setSelectedItem(c.getNome());
                    comboCampus.setSelectedItem(c.getCampus());
                    String periodo = c.getCampus();
                    if(periodo.equals("noturno"))
                        rbNoturno.setSelected(true);
                    	rbMatutino.setSelected(false);
                    	rbVespertino.setSelected(false);
                    if(periodo.equals("matutino"))
                        rbMatutino.setSelected(true);
                    	rbVespertino.setSelected(false);
                    	rbNoturno.setSelected(false);
                    if(periodo.equals("vespertino"))
                        rbVespertino.setSelected(true);
                    	rbNoturno.setSelected(false);
                    	rbMatutino.setSelected(false);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
                }
			}
		});
		btnBuscar.setIcon(
				new ImageIcon("E:\\Git\\sistema-academico-java\\src\\resources\\buscar.png")
			);
		btnBuscar.setBounds(27, 280, 90, 80);
		panelCurso.add(btnBuscar);

		JButton btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    AlunoDAO alunoDao = new AlunoDAO();
                    Aluno a = alunoDao.buscarAluno(Integer.parseInt(txtRgmCurso.getText()));
                    CursoDAO cursoDao = new CursoDAO();
                    Curso c = new Curso();
                    c.setCodigo(a.getRgm());
                    c.setNome(comboCurso.getSelectedItem().toString());
                    c.setCampus(comboCampus.getSelectedItem().toString());
                    String turno = "";
                    if(rbMatutino.isSelected())
                        turno = "matutino";
                    if(rbVespertino.isSelected())
                        turno = "vespertino";
                    if(rbNoturno.isSelected())
                        turno = "noturno";
                    if(turno.equals("")) {
                        // TODO mensagem de erro e talz
                        return;
                    } 
                    c.setTurno(turno);
                    a.setCurso(c);
                    alunoDao.atualizarAluno(a);
                    cursoDao.inserirCurso(c);
                    System.out.println(c.getNome());
                } catch (Exception excp){
                    System.out.println("Erro -> " + excp.getMessage());
					JOptionPane.showMessageDialog(null, excp.getMessage());
                }
			}
		});
		btnSalvar.setIcon(new ImageIcon("E:\\Git\\sistema-academico-java\\src\\resources\\save.png"));
		btnSalvar.setBounds(170, 280, 90, 80);
		panelCurso.add(btnSalvar);

		JButton btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    CursoDAO cursoDao = new CursoDAO();
                    Curso c = cursoDao.buscarCurso(Integer.parseInt(txtRgmCurso.getText()));
                    c.setNome(comboCurso.getSelectedItem().toString());
                    c.setCampus(comboCampus.getSelectedItem().toString());
                    String turno = "";
                    if(rbMatutino.isSelected())
                        turno = "matutino";
                    if(rbVespertino.isSelected())
                         turno = "vespertino";
                    if(rbNoturno.isSelected())
                         turno = "noturno";
                    if(turno.equals("")) {
                         // TODO mensagem de erro e talz
                         return;
                         }
                    c.setTurno(turno);
                    cursoDao.atualizarCurso(c);
                } catch (Exception e2) {
                    System.out.println("Erro " + e2.getMessage());

					JOptionPane.showMessageDialog(null, e2.getMessage());
                }
			}
		});
		btnAtualizar.setIcon(
				new ImageIcon("E:\\Git\\sistema-academico-java\\src\\resources\\atualizar.png")
			);
		btnAtualizar.setBounds(335, 280, 90, 80);
		panelCurso.add(btnAtualizar);

		JButton btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    CursoDAO cursoDao = new CursoDAO();
                    Curso c = cursoDao.buscarCurso(Integer.parseInt(txtRgmCurso.getText().toString()));
                    cursoDao.deletarCurso(c);

                    rbNoturno.setSelected(false);
                    rbMatutino.setSelected(false);
                    rbVespertino.setSelected(false);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage());
                }
			}
		});
		btnExcluir.setIcon(
				new ImageIcon("E:\\Git\\sistema-academico-java\\src\\resources\\apagar.png")
			);
		btnExcluir.setBounds(482, 280, 90, 80);
		panelCurso.add(btnExcluir);
		
		JButton btnSair = new JButton("");
		btnSair.setIcon(
				new ImageIcon("E:\\Git\\sistema-academico-java\\src\\resources\\sair.png")
			);
		btnSair.setBounds(616, 280, 90, 80);

		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		panelCurso.add(btnSair);

		// =========================
		// NOTAS E FALTAS
		// =========================

		JPanel panelNotas = new JPanel();
		panelNotas.setLayout(null);
		tabbedPane.addTab("Notas e Faltas", null, panelNotas, null);

		JLabel lblRgm2 = new JLabel("RGM:");
		lblRgm2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblRgm2.setBounds(10, 20, 80, 40);
		panelNotas.add(lblRgm2);

		txtRgm2 = new JTextField();
		txtRgm2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtRgm2.setBounds(90, 20, 180, 40);
		panelNotas.add(txtRgm2);

		txtNome2 = new JTextField();
		txtNome2.setEditable(false);
		txtNome2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtNome2.setBounds(290, 20, 430, 40);
		panelNotas.add(txtNome2);

		txtCurso2 = new JTextField();
		txtCurso2.setEditable(false);
		txtCurso2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtCurso2.setBounds(10, 81, 710, 40);
		panelNotas.add(txtCurso2);

		JComboBox<String> comboSemestre_1 = new JComboBox<>();
		comboSemestre_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		comboSemestre_1.setModel(new DefaultComboBoxModel<>(new String[] {
		    "1 Semestre",
		    "2 Semestre",
		    "3 Semestre",
		    "4 Semestre",
		    "5 Semestre",
		    "6 Semestre",
		}));
		comboSemestre_1.setBounds(130, 215, 120, 40);
		panelNotas.add(comboSemestre_1);
		
		JLabel lblSemestre = new JLabel("Semestre:");
		lblSemestre.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblSemestre.setBounds(10, 215, 120, 40);
		panelNotas.add(lblSemestre);
		
		JLabel lblDisciplina = new JLabel("Disciplina:");
		lblDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblDisciplina.setBounds(10, 143, 120, 40);
		panelNotas.add(lblDisciplina);

		JComboBox<String> comboDisciplina = new JComboBox<>();
		comboDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 23));
		comboDisciplina.setBounds(140, 143, 580, 40);
		panelNotas.add(comboDisciplina);

		JLabel lblNota = new JLabel("Nota:");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNota.setBounds(283, 215, 64, 40);
		panelNotas.add(lblNota);

		txtNota = new JTextField();
		txtNota.setHorizontalAlignment(SwingConstants.CENTER);
		txtNota.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtNota.setBounds(340, 215, 100, 40);
		panelNotas.add(txtNota);

		JLabel lblFaltas = new JLabel("Faltas:");
		lblFaltas.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblFaltas.setBounds(477, 215, 90, 40);
		panelNotas.add(lblFaltas);

		txtFaltas = new JTextField();
		txtFaltas.setHorizontalAlignment(SwingConstants.CENTER);
		txtFaltas.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtFaltas.setBounds(562, 215, 100, 40);
		panelNotas.add(txtFaltas);

		//Botões notas e falta
		
		JButton btnBuscar2 = new JButton("");
		btnBuscar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	//			btnBuscar2.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {

						try {

							int rgm = Integer.parseInt(txtRgm2.getText());

							// BUSCA ALUNO
							Aluno aluno = alunoDAO.buscarAluno(rgm);

							txtNome2.setText(aluno.getNome());
							idAluno = aluno.getRgm();
							// BUSCA CURSO
							Curso curso = cursoDAO.buscarCurso(rgm);

							txtCurso2.setText(curso.getNome());

							// CARREGA DISCIPLINAS DO CURSO
							disciplinas = getArrayDisciplinas(curso.getNome());
							carregarDisciplinasSemestre(comboSemestre_1.getSelectedItem().toString(), disciplinas, comboDisciplina);
							//System.out.println(disciplinas[0][1]);
							//carregarDisciplinas(curso.getNome(), comboDisciplina);

							// PEGA DISCIPLINA SELECIONADA
							String nomeDisciplina = comboDisciplina.getSelectedItem().toString();
/*							
 
							Disciplina disciplina = disciplinaDAO.buscarDisciplinaPorNome(nomeDisciplina);

							// PREENCHE CAMPOS
							txtNota.setText(String.valueOf(disciplina.getNota()));

							txtFaltas.setText(
									String.valueOf(disciplina.getFaltas()));

							comboSemestre_1.setSelectedItem(
									disciplina.getSemestre());
*/
						}
						catch (Exception ex) {

							ex.printStackTrace();

							JOptionPane.showMessageDialog(null,
									"Erro ao buscar dados!");

						}
					}
				});
				
//			}
//		});
		btnBuscar2.setIcon(
				new ImageIcon("E:\\Git\\sistema-academico-java\\src\\resources\\buscar.png")
			);
		btnBuscar2.setBounds(27, 280, 90, 80);
		panelNotas.add(btnBuscar2);

		JButton btnSalvar2 = new JButton("");
		btnSalvar2.addActionListener(new ActionListener() {

			    public void actionPerformed(ActionEvent e) {

			        try {
			        	
						int rgm = Integer.parseInt(txtRgm2.getText());

						// BUSCA ALUNO
						Aluno aluno = alunoDAO.buscarAluno(rgm);
			        	//AlunoDAO alunoDao = new AlunoDAO();
	                    //Aluno a = alunoDao.buscarAluno(Integer.parseInt(txtRgmCurso.getText()));
			            Disciplina disciplina = new Disciplina();

			            disciplina.setCodigo(Integer.valueOf(gerarCodigoDisciplina()));
			            disciplina.setNome(comboDisciplina.getSelectedItem().toString());
			            disciplina.setNota(Float.parseFloat(txtNota.getText().toString()));
			            disciplina.setFaltas(Integer.parseInt(txtFaltas.getText().toString()));
			            disciplina.setSemestre(comboSemestre_1.getSelectedItem().toString());
			            disciplina.setAluno(aluno);
			            Curso c = new Curso();
			            c.setCodigo(aluno.getRgm());
			            disciplina.setCurso(c);
			            disciplinaDAO.inserirDisciplina(disciplina);

			            JOptionPane.showMessageDialog(null,
			                    "Disciplina salva com sucesso!");

			        } catch (Exception e1) {

			            e1.printStackTrace();

			            JOptionPane.showMessageDialog(null,
			                    "Erro ao salvar disciplina!");
			        }
			    }
			});
		
		btnSalvar2.setIcon(
				new ImageIcon("E:\\Git\\sistema-academico-java\\src\\resources\\save.png")
			);
		btnSalvar2.setBounds(180, 280, 90, 80);
		panelNotas.add(btnSalvar2);

		JButton btnAtualizar2 = new JButton("");
		btnAtualizar2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
		            Disciplina disciplina = disciplinaDAO.buscarDisciplina(Integer.valueOf(gerarCodigoDisciplina()));

		            disciplina.setNome(comboDisciplina.getSelectedItem().toString());
		            disciplina.setNota(Float.parseFloat(txtNota.getText().toString()));
		            disciplina.setFaltas(Integer.parseInt(txtFaltas.getText().toString()));
		            disciplina.setSemestre(comboSemestre_1.getSelectedItem().toString());

		            disciplinaDAO.atualizarDisciplina(disciplina);
		            
		        } catch (Exception eUpdate) {
					System.out.println(eUpdate.getMessage());
					JOptionPane.showMessageDialog(null, "");
				}
			}});
		btnAtualizar2.setIcon(
				new ImageIcon("E:\\Git\\sistema-academico-java\\src\\resources\\atualizar.png")
			);
		btnAtualizar2.setBounds(340, 280, 90, 80);
		panelNotas.add(btnAtualizar2);

		JButton btnExcluir2 = new JButton("");
		btnExcluir2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {

		            Disciplina disciplina = new Disciplina();

		            disciplina.setCodigo(Integer.valueOf(gerarCodigoDisciplina()));
		            disciplina.setNome(comboDisciplina.getSelectedItem().toString());
		            disciplina.setNota(Float.parseFloat(txtNota.getText().toString()));
		            disciplina.setFaltas(Integer.parseInt(txtFaltas.getText().toString()));
		            disciplina.setSemestre(comboSemestre_1.getSelectedItem().toString());

		            disciplinaDAO.deletarDisciplina(disciplina);;
		            
		            txtNota.setText("");
		            txtFaltas.setText("");
				} catch (Exception eExc) {
					eExc.getStackTrace();
					JOptionPane.showMessageDialog(null, eExc.getMessage());
				}
				
			}});
		btnExcluir2.setIcon( 
				new ImageIcon("E:\\Git\\sistema-academico-java\\src\\resources\\apagar.png")
				);
		btnExcluir2.setBounds(495, 280, 90, 80);
		panelNotas.add(btnExcluir2);
		
		JButton btnSair2 = new JButton("");
		btnSair2.setIcon(
				new ImageIcon("src\resources\sair.png")
			);
		btnSair2.setBounds(650, 280, 90, 80);

		btnSair2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		panelNotas.add(btnSair2);
		
		// acçoes na seleção das combobox
		comboSemestre_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("Mudou semestre");
				carregarDisciplinasSemestre(comboSemestre_1.getSelectedItem().toString(), disciplinas, comboDisciplina);
			}
			
		});
		comboDisciplina.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Mudou disciplina");
				try {
					idDisciplina = comboDisciplina.getSelectedIndex();
					System.out.println(
							"Id aluno " + idAluno
							+ "IdCurso " + idCurso
							+ "idSemestre " + idSemestre
							+ "id Disciplina " + idDisciplina
 							);
					Disciplina disciplina = disciplinaDAO.buscarDisciplina(Integer.valueOf(gerarCodigoDisciplina()));
					txtNota.setText(String.valueOf(disciplina.getNota()));
					txtFaltas.setText(String.valueOf(disciplina.getFaltas()));
				} catch (Exception e6) {
					System.out.println("Erro: " + e6.getMessage());
					txtNota.setText("");
					txtFaltas.setText("");
				}
			}
			
		});
		//==========================
		// BOLETIM
		//==========================
		JPanel panelBoletim = new JPanel();
		panelBoletim.setLayout(null);
		
		tabbedPane.addTab("Boletim", null, panelBoletim, null);
				
		JLabel lblRgmBoletim = new JLabel("RGM:");
		lblRgmBoletim.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblRgmBoletim.setBounds(10, 20, 80, 40);
		panelBoletim.add(lblRgmBoletim);
						
		JTextField txtRgmBoletim = new JTextField();
		txtRgmBoletim.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtRgmBoletim.setBounds(90, 20, 180, 40);
		panelBoletim.add(txtRgmBoletim);
		
		
		
		JButton btnBltBuscar = new JButton("Buscar Rgm");
		
		btnBltBuscar.setBounds(309, 5, 90, 80);
		panelBoletim.add(btnBltBuscar);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true); 

		JScrollPane scrollPane = new JScrollPane(textArea);

		scrollPane.setBounds(59, 94, 488, 241);

		panelBoletim.add(scrollPane);
		
		// =========================
		// EVENTOS
		// =========================
		btnBltBuscar.addActionListener(new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	            try {
	            	textArea.setText("");
	                int rgm = Integer.parseInt(txtRgmBoletim.getText().toString());
	                System.out.println("linha1");
	                DisciplinaDAO dao = new DisciplinaDAO();
	                System.out.println("linha 2");
	                ArrayList<Disciplina> disciplinas = dao.BuscarRgm(rgm);
	                String texto = "";


	                for(Disciplina d : disciplinas) {

	                    //texto += "RGM: " + d.getRgm_aluno() + "\n";
	                	texto += "Nome: " + d.getNome() + "\n";
	                    texto += "Código: " + d.getCodigo() + "\n";
	                    //texto += "Código Curso: " + d.getCodigo_curso() + "\n";
	                    texto += "Semestre: " + d.getSemestre() + "\n";
	                    texto += "Faltas: " + d.getFaltas() + "\n";
	                    texto += "Nota: " + d.getNota() + "\n";

	                    texto += "-----------------------------\n";
	                }

	                textArea.setText(texto);


	            } catch (NumberFormatException ex) {

	                JOptionPane.showMessageDialog(null,
	                        "RGM inválido");

	            } catch (Exception ex) {

	                JOptionPane.showMessageDialog(null,
	                        ex.getMessage());

	                ex.printStackTrace();
	            }
	        }


	    });
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Aluno aluno = new Aluno();

					aluno.setRgm(Integer.parseInt(txtRgm.getText()));
					aluno.setNome(txtNome.getText());
					aluno.setEmail(txtEmail.getText());
					aluno.setCpf(txtCpf.getText());
					aluno.setEndereco(txtEnd.getText());
					aluno.setMunicipio(txtMunicipio.getText());
					aluno.setUf(comboBoxUf.getSelectedItem().toString());
					aluno.setCelular(txtCelular.getText());

					String[] data = txtData.getText().split("/");

					int dia = Integer.parseInt(data[0].trim());
					int mes = Integer.parseInt(data[1].trim());
					int ano = Integer.parseInt(data[2].trim());

					aluno.setDataNascimento(dia, mes, ano);

					alunoDAO.inserirAluno(aluno);

					JOptionPane.showMessageDialog(null, "Aluno salvo com sucesso!");

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao salvar aluno.");
				}
			}
		});

		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					int rgm = Integer.parseInt(txtRgm.getText());

					Aluno aluno = alunoDAO.buscarAluno(rgm);

					if (aluno == null) {
						JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
						return;
					}

					txtNome.setText(aluno.getNome());
					txtEmail.setText(aluno.getEmail());
					txtEnd.setText(aluno.getEndereco());
					txtCpf.setText(aluno.getCpf());
					txtMunicipio.setText(aluno.getMunicipio());
					txtCelular.setText(aluno.getCelular());
					txtData.setText(aluno.getDataNascimentoFormatada());

					// EVITA NULLPOINTER
					if (aluno.getDataNascimento() != null) {
						txtData.setText(aluno.getDataNascimentoFormatada());
					} else {
						txtData.setText("");
					}

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao consultar aluno.");
				}
			}
		});
		
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Aluno aluno = new Aluno();

					aluno.setRgm(Integer.parseInt(txtRgm.getText()));

					alunoDAO.deletarAluno(aluno);

					JOptionPane.showMessageDialog(null, "Aluno deletado com sucesso!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		mntmAlterar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {

		        try {

		            Aluno aluno = new Aluno();

		            aluno.setRgm(Integer.parseInt(txtRgm.getText()));
		            aluno.setNome(txtNome.getText());
		            aluno.setEmail(txtEmail.getText());
		            aluno.setCpf(txtCpf.getText());
		            aluno.setEndereco(txtEnd.getText());
		            aluno.setMunicipio(txtMunicipio.getText());
		            aluno.setUf(comboBoxUf.getSelectedItem().toString());
		            aluno.setCelular(txtCelular.getText());

		            String[] data = txtData.getText().trim().split("/");

		            int dia = Integer.parseInt(data[0]);
		            int mes = Integer.parseInt(data[1]);
		            int ano = Integer.parseInt(data[2]);

		            aluno.setDataNascimento(dia, mes, ano);

		            alunoDAO.atualizarAluno(aluno);

		            JOptionPane.showMessageDialog(null,
		                    "Aluno atualizado com sucesso!");

		        } catch (Exception e1) {

		            e1.printStackTrace();

		            JOptionPane.showMessageDialog(null,
		                    "Erro ao atualizar aluno!");
		        }
		    }
		});

		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// ==================
		// Metodos
		// ==================
		
		
	}
}