import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListagemView extends JFrame {
    private final JTable tabelaProdutos;
    private final DefaultTableModel modeloTabela;
    private final JButton voltarButton;

    public ListagemView() {
        setTitle("Lista de Produtos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel tituloLabel = new JLabel("Lista de Produtos");
        tituloLabel.setBounds(200, 10, 150, 30);
        add(tituloLabel);

        // Configurando a tabela
        String[] colunas = {"ID", "Nome", "Valor", "Status"};
        modeloTabela = new DefaultTableModel(null, colunas);
        tabelaProdutos = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        scrollPane.setBounds(50, 50, 400, 200);
        add(scrollPane);

        // Botão Voltar
        voltarButton = new JButton("Voltar");
        voltarButton.setBounds(200, 300, 100, 30);
        add(voltarButton);

        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CadastroView();
                dispose(); // Fecha a janela atual
            }
        });

        listarProdutos();

        setVisible(true);
    }

    private void listarProdutos() {
        ProdutosDAO dao = new ProdutosDAO();
        ArrayList<ProdutosDTO> lista = dao.listarProdutos();

        for (ProdutosDTO produto : lista) {
            modeloTabela.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getStatus()
            });
        }
    }

    public static void main(String[] args) {
        new ListagemView();
    }
}
