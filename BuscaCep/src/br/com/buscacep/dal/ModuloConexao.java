package br.com.buscacep.dal;

import java.sql.*;


public class ModuloConexao {
// a classe abaixo cria um m�todo de conex�o com retorno
	// Criando vari�veis de apoio
	Connection con = ModuloConexao.conector(); // Conecta com o banco
	PreparedStatement pst = null; //executa
	ResultSet rs = null;// Resultado do banco
	public static Connection conector() {
		
		//Criando uma variavel para receber a conex�o
		java.sql.Connection conexao = null;
		String driver = "com.mysql.jdbc.Driver";
	
		
		// vari�vel que recebe as informa��es do banco. Caminho, banco, ssl
		String url = "jdbc:mysql://10.26.45.103:3306/achaCEP?useSSL=false";
		
		//autentica��o
		String user = "admin";
		String password = "123@Senac";
		
		//tratamento de exce��es
		try {
			Class.forName(driver);//usar o driver
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}



	
