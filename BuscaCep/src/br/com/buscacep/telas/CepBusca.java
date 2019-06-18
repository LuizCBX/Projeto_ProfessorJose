package br.com.buscacep.telas;



import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.buscacep.dal.ModuloConexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class CepBusca extends JFrame {
	
// Criando variáveis de apoio
	Connection con = ModuloConexao.conector(); // Conecta com o banco
	PreparedStatement pst = null; // executa
	ResultSet rs = null;// Resultado do banco

	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JTextField txtBairro;
	private JTextField txtLogradouro;
	private JButton btnPesquisar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CepBusca frame = new CepBusca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CepBusca() {
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("New label");
		label.setBounds(173, 22, 48, 14);
		getContentPane().add(label);
		setResizable(false);
		setTitle("Busque seu Endere\u00E7o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDigiteSeuCep = new JLabel("Digite seu CEP:");
		lblDigiteSeuCep.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDigiteSeuCep.setBounds(10, 30, 118, 20);
		contentPane.add(lblDigiteSeuCep);
		
		txtCep = new JTextField();
		txtCep.setBounds(120, 24, 142, 29);
		contentPane.add(txtCep);
		txtCep.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCidade.setBounds(10, 68, 48, 14);
		contentPane.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(92, 65, 298, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado.setBounds(10, 113, 48, 14);
		contentPane.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(92, 110, 298, 20);
		contentPane.add(txtEstado);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBairro.setBounds(10, 157, 48, 14);
		contentPane.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(92, 154, 298, 20);
		contentPane.add(txtBairro);
		
		JLabel lblLogradouro = new JLabel("Logradouro:");
		lblLogradouro.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLogradouro.setBounds(10, 200, 72, 14);
		contentPane.add(lblLogradouro);
		
		txtLogradouro = new JTextField();
		txtLogradouro.setColumns(10);
		txtLogradouro.setBounds(92, 197, 298, 20);
		contentPane.add(txtLogradouro);
		
	
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			pesquisar();
			}
		});
		btnPesquisar.setBounds(272, 23, 118, 31);
		contentPane.add(btnPesquisar);
		

		
	}

		private void limpar() {
			txtCep.setText(null);
			txtLogradouro.setText(null);
			txtEstado.setText(null);
			txtCidade.setText(null);
			txtBairro.setText(null);
	
		}
		
		private void pesquisar() {

			String read = "select * from tabelao where CEP =?";
			// Usamos o try catch para tratar exceção
			try {
				pst = (PreparedStatement) con.prepareStatement(read);
				// passagem do parâmetro
				pst.setString(1, txtCep.getText());
				// atribuimos a variavel rs retorno do comando
				rs = pst.executeQuery();// Executar a query(sql)
				//

				if (rs.next()) {
					txtCidade.setText(rs.getString(2));
					txtEstado.setText(rs.getString(3));
					txtBairro.setText(rs.getString(4));
					txtLogradouro.setText(rs.getString(5));
					
				} else {
					
					JOptionPane.showMessageDialog(null, "Não encontrado no banco de dados!");
					limpar();
				}
			} catch (Exception e) {
				System.out.println(e);
		}
	}// fim do construtor
}
