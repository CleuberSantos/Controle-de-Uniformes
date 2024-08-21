package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
	
public class Conexao {
    private static final String DRIVE = "org.sqlite.JDBC";
    private static final String BD = "jdbc:sqlite:resources/bdfuncionario.db";
    private Connection conexao;

    private Conexao() {
        try {
            Class.forName(DRIVE);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar o driver do banco de dados: " + e.getMessage());
        }
    }

    private static class ConexaoHolder {
        private static final Conexao INSTANCE = new Conexao();
    }

    public static Conexao getInstancia() {
        return ConexaoHolder.INSTANCE;
    }

    public Connection abrirConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection(BD);
                conexao.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
        return conexao;
    }

    public void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão: " + e.getMessage());
        } finally {
            conexao = null;
        }
    }
}