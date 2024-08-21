package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModeloTabela extends AbstractTableModel {

    private static final String[] colunas = { "ID", "CNPJ", "Empresa", "Admissão", "Nome", "Matricula" };
    private ArrayList<Funcionario> funcionarios;

    public ModeloTabela(ArrayList<Funcionario> funcionarios) {
        super();
        this.funcionarios = funcionarios;
    }

    @Override
    public int getRowCount() {
        return funcionarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Funcionario funcionario = funcionarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return funcionario.getId();
            case 1:
                return funcionario.getCnpj();
            case 2:
                return funcionario.getEmpresa();
            case 3:
                return funcionario.getAdmissao();
            case 4:
                return funcionario.getNome();
            case 5:
                return funcionario.getMatricula();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}