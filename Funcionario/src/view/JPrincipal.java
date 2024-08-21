package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import javax.swing.border.BevelBorder;

import dao.DAO;

import javax.swing.JTable;


import model.Funcionario;
import model.ModeloTabela;

import javax.swing.JScrollPane;

public class JPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private ArrayList<Funcionario> funcionario;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JPrincipal frame = new JPrincipal();
                    frame.setLocationRelativeTo(frame);
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
    public JPrincipal() {
        funcionario = new ArrayList<>();
        funcionario.add(new Funcionario("1","Cleuber","3255","Semear","20/06/2024", "Não Informado"));
        funcionario.add(new Funcionario("2","Renato","3355","Semear","11/04/2024", "Não Informado"));
        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 755, 463);
        contentPane = new JPanel();
        contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        setContentPane(contentPane);
        
        JButton btnNewButton = new JButton("Cadastrar");
        btnNewButton.setBounds(29, 10, 102, 23);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JCadastro jCadastro = new JCadastro(null);
                jCadastro.setLocationRelativeTo(jCadastro);
                jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                jCadastro.setVisible(true);
            }
        });
        
        textField = new JTextField();
        textField.setBounds(166, 11, 371, 20);
        textField.setColumns(10);
        contentPane.setLayout(null);
        contentPane.add(btnNewButton);
        contentPane.add(textField);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(47, 75, 665, 291);
        contentPane.add(scrollPane);
        
        ModeloTabela modeloTabela = new ModeloTabela(funcionario);
        table = new JTable();
        table.setModel(modeloTabela);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1) {
                    try {
                        Funcionario funcionarioSelecionado = DAO.consultarFuncionario(modeloTabela.getValueAt(table.getSelectedRow(), 0).toString());
                        JCadastro jCadastro = new JCadastro(funcionarioSelecionado);
                        jCadastro.setLocationRelativeTo(jCadastro);
                        jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        jCadastro.setVisible(true);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        scrollPane.setViewportView(table);
    }
}
