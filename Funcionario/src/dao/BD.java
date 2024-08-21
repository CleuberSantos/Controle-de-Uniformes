package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Conexao instancia;
    private static final String DRIVE = "org.sqlite.JDBC";
    private static final String BD = "jdbc:sqlite:resources/bdfuncionario.db";
    private static Connection conexao;

    private Conexao() {
    }

    public static Conexao getInstancia() {
        if (instancia == null) {
            instancia = new Conexao();
        }
        return instancia;
    }

    public Connection abrirConexao() {
        try {
            Class.forName(DRIVE);
            conexao = DriverManager.getConnection(BD);
            conexao.setAutoCommit(false);
            System.err.println("Conexão com o banco de dados estabelecida.");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return conexao;
    }

    public void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.err.println("Conexão com o banco de dados fechada.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            e.printStackTrace();
        } finally {
            conexao = null;
        }
    }
}