import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListagemView extends JFrame {
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;
    private JComboBox<String> statusComboBox;
    private JButton filtrarButton;

    public ListagemView() {
        setTitle("Listagem de Produtos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel tituloLabel = new JLabel("Lista de Produtos");
        tituloLabel.setBounds(200, 10, 150, 30);
        add(tituloLabel);

        // Configurando a tabela de produtos
        String[] colunas = {"ID", "Nome", "Valor", "Status"};
        modeloTabela = new DefaultTableModel(null, colunas);
        tabelaProdutos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        scrollPane.setBounds(50, 50, 500, 200);
        add(scrollPane);

        // Filtro de status
        JLabel filtroLabel = new JLabel("Filtrar por Status:");
        filtroLabel.setBounds(50, 270, 100, 30);
        add(filtroLabel);

        String[] statusOptions = {"Todos", "À venda", "Disponível", "Indisponível", "Vendido"};
        statusComboBox = new JComboBox<>(statusOptions);
        statusComboBox.setBounds(150, 270, 150, 30);
        add(statusComboBox);

        filtrarButton = new JButton("Filtrar");
        filtrarButton.setBounds(320, 270, 100, 30);
        add(filtrarButton);

        // Ação do botão "Filtrar"
        filtrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String statusSelecionado = (String) statusComboBox.getSelectedItem();
                listarProdutos(statusSelecionado);
            }
        });

        // Exibe todos os produtos na primeira vez que a tela for aberta
        listarProdutos("Todos");

        setVisible(true);
    }

    // Método para listar produtos na tabela com base no status selecionado
    private void listarProdutos(String status) {
        ProdutosDAO dao = new ProdutosDAO();
        ArrayList<ProdutosDTO> produtos;
        
        if (status.equals("Todos")) {
            produtos = dao.listarProdutos();  // Lista todos os produtos
        } else {
            produtos = dao.listarProdutosPorStatus(status);  // Lista produtos por status
        }

        // Limpa a tabela antes de adicionar os novos resultados
        modeloTabela.setRowCount(0);

        for (ProdutosDTO produto : produtos) {
            modeloTabela.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getStatus()
            });
        }
    }
}
