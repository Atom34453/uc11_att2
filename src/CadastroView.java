import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroView extends JFrame {
    private JTextField nomeField, valorField;
    private JComboBox<String> statusCombo;
    private JButton salvarButton, listarButton;

    public CadastroView() {
        setTitle("Cadastro de Produto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Configurando os componentes da tela

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(50, 50, 100, 30);
        add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(150, 50, 150, 30);
        add(nomeField);

        JLabel valorLabel = new JLabel("Valor:");
        valorLabel.setBounds(50, 100, 100, 30);
        add(valorLabel);

        valorField = new JTextField();
        valorField.setBounds(150, 100, 150, 30);
        add(valorField);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(50, 150, 100, 30);
        add(statusLabel);

        String[] statusOptions = {"Disponível", "Indisponível"};
        statusCombo = new JComboBox<>(statusOptions);
        statusCombo.setBounds(150, 150, 150, 30);
        add(statusCombo);

        salvarButton = new JButton("Salvar");
        salvarButton.setBounds(50, 200, 100, 30);
        add(salvarButton);

        listarButton = new JButton("Listar Produtos");
        listarButton.setBounds(200, 200, 150, 30);
        add(listarButton);

        // Ação do botão Salvar
        salvarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setNome(nomeField.getText());
                produto.setValor(Double.parseDouble(valorField.getText()));
                produto.setStatus(statusCombo.getSelectedItem().toString());

                ProdutosDAO dao = new ProdutosDAO();
                dao.salvarProduto(produto);
            }
        });

        // Ação do botão Listar Produtos
        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ListagemView(); // Abre a tela de listagem de produtos
                dispose(); // Fecha a tela de cadastro
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        // Ponto de entrada principal do programa
        new CadastroView();
    }
}
