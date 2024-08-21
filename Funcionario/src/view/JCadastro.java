package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import dao.DAO;
import model.Funcionario;  // Certifique-se de que esta classe está no pacote correto

public class JCadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNome;
    private JTextField textFieldCNPJ;
    private JTextField textFieldEmpresa;
    private JTextField textFieldAdmissao;
    private JTextField textFieldMatricula;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JCadastro frame = new JCadastro(null);
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
    public JCadastro(Funcionario funcionarioSelecionado) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(10, 11, 46, 14);
        contentPane.add(lblNome);

        textFieldNome = new JTextField();
        textFieldNome.setBounds(10, 26, 384, 20);
        contentPane.add(textFieldNome);
        textFieldNome.setColumns(10);

        JLabel lblCNPJ = new JLabel("CNPJ");
        lblCNPJ.setBounds(10, 57, 46, 14);
        contentPane.add(lblCNPJ);

        textFieldCNPJ = new JTextField();
        textFieldCNPJ.setBounds(10, 73, 181, 20);
        contentPane.add(textFieldCNPJ);
        textFieldCNPJ.setColumns(10);

        JLabel lblEmpresa = new JLabel("Empresa");
        lblEmpresa.setBounds(213, 57, 46, 14);
        contentPane.add(lblEmpresa);

        textFieldEmpresa = new JTextField();
        textFieldEmpresa.setColumns(10);
        textFieldEmpresa.setBounds(213, 73, 181, 20);
        contentPane.add(textFieldEmpresa);

        JLabel lblAdmissao = new JLabel("Admissão");
        lblAdmissao.setBounds(10, 104, 59, 14);
        contentPane.add(lblAdmissao);

        textFieldAdmissao = new JTextField();
        textFieldAdmissao.setColumns(10);
        textFieldAdmissao.setBounds(10, 119, 181, 20);
        contentPane.add(textFieldAdmissao);

        JLabel lblMatricula = new JLabel("Matrícula");
        lblMatricula.setBounds(213, 104, 59, 14);
        contentPane.add(lblMatricula);

        textFieldMatricula = new JTextField();
        textFieldMatricula.setColumns(10);
        textFieldMatricula.setBounds(213, 119, 181, 20);
        contentPane.add(textFieldMatricula);

        JButton btnIncluir = new JButton(funcionarioSelecionado==null?"Incluir":"Alterar");
        btnIncluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText().trim();
                String cnpj = textFieldCNPJ.getText().trim();
                String empresa = textFieldEmpresa.getText().trim();
                String admissao = textFieldAdmissao.getText().trim();
                String matricula = textFieldMatricula.getText().trim();

                if (nome.isEmpty() || cnpj.isEmpty() || empresa.isEmpty() || admissao.isEmpty() || matricula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    DAO dao = new DAO();
                    dao.cadastrarFuncionario(new Funcionario(null, nome, cnpj, empresa, admissao, matricula));
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        btnIncluir.setBounds(305, 183, 89, 23);
        contentPane.add(btnIncluir);
    }
}