package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.Funcionario;
import model.Usuario;

public class DAO {
    private static final String CADASTRAR_FUNCIONARIO = "INSERT INTO FUNCIONARIO (NOME, CNPJ, EMPRESA, ADMISSAO, MATRICULA) VALUES (?, ?, ?, ?, ?)";
    private static final String CONSULTAR_FUNCIONARIO = "SELECT * FROM FUNCIONARIO WHERE ID = ?";
    private static final String ALTERAR_FUNCIONARIO = "UPDATE FUNCIONARIO SET NOME = ?, CNPJ = ?, EMPRESA = ?, ADMISSAO = ?, MATRICULA = ? WHERE ID = ?";
    private static final String EXCLUIR_FUNCIONARIO = "DELETE FROM FUNCIONARIO WHERE ID = ?";
    private static final String LISTAR_FUNCIONARIOS = "SELECT * FROM FUNCIONARIO";
    private static final String CONSULTAR_USUARIO = "SELECT USUARIO, SENHA FROM USUARIO WHERE USUARIO = ? AND SENHA = ?";

    public DAO() {
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        try (Connection connection = Conexao.getInstancia().abrirConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(CADASTRAR_FUNCIONARIO)) {

            int i = 1;
            preparedStatement.setString(i++, funcionario.getNome());
            preparedStatement.setString(i++, funcionario.getCnpj());
            preparedStatement.setString(i++, funcionario.getEmpresa());
            preparedStatement.setString(i++, funcionario.getAdmissao());
            preparedStatement.setString(i++, funcionario.getMatricula());

            preparedStatement.execute();
            connection.commit();

            JOptionPane.showMessageDialog(null, "Funcionario incluído com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Funcionario consultarFuncionario(String id) throws SQLException {
        Funcionario funcionario = null;

        try (Connection connection = Conexao.getInstancia().abrirConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_FUNCIONARIO)) {

            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    funcionario = new Funcionario(
                        resultSet.getString("ID"), 
                        resultSet.getString("NOME"),
                        resultSet.getString("CNPJ"), 
                        resultSet.getString("EMPRESA"), 
                        resultSet.getString("ADMISSAO"),
                        resultSet.getString("MATRICULA")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "Não foi possível localizar o funcionário selecionado", "",
                    JOptionPane.WARNING_MESSAGE);
            throw new SQLException("Não foi possível localizar o funcionário selecionado");
        }
        return funcionario;
    }

    public void alterarFuncionario(String id, Funcionario funcionario) {
        try (Connection connection = Conexao.getInstancia().abrirConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(ALTERAR_FUNCIONARIO)) {

            int i = 1;
            preparedStatement.setString(i++, funcionario.getNome());
            preparedStatement.setString(i++, funcionario.getCnpj());
            preparedStatement.setString(i++, funcionario.getEmpresa());
            preparedStatement.setString(i++, funcionario.getAdmissao());
            preparedStatement.setString(i++, funcionario.getMatricula());
            preparedStatement.setString(i++, id);

            preparedStatement.executeUpdate();
            connection.commit();

            JOptionPane.showMessageDialog(null, "Funcionario alterado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirFuncionario(String id) {
        try (Connection connection = Conexao.getInstancia().abrirConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(EXCLUIR_FUNCIONARIO)) {

            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
            connection.commit();

            JOptionPane.showMessageDialog(null, "Funcionario excluído com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Funcionario> listarFuncionarios() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try (Connection connection = Conexao.getInstancia().abrirConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(LISTAR_FUNCIONARIOS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario(
                    resultSet.getString("ID"), 
                    resultSet.getString("NOME"),
                    resultSet.getString("CNPJ"), 
                    resultSet.getString("EMPRESA"), 
                    resultSet.getString("ADMISSAO"),
                    resultSet.getString("MATRICULA")
                );
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public Usuario consultarUsuario(String nomeUsuario, String senhaCriptografada) throws SQLException {
        Usuario usuario = null;

        try (Connection connection = Conexao.getInstancia().abrirConexao();
             PreparedStatement preparedStatement = connection.prepareStatement(CONSULTAR_USUARIO)) {

            preparedStatement.setString(1, nomeUsuario);
            preparedStatement.setString(2, senhaCriptografada);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = new Usuario(
                        resultSet.getString("ID"), 
                        resultSet.getString("USUARIO"),
                        resultSet.getString("SENHA")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Não foi possível localizar o usuário selecionado", "",
                    JOptionPane.WARNING_MESSAGE);
            throw new SQLException("Não foi possível localizar o usuário selecionado");
        }
        return usuario;
    }
}