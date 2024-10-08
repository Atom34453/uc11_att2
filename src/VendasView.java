import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class VendasView extends JFrame {
    private JTable tabelaVendas;
    private DefaultTableModel modeloTabela;

    public VendasView() {
        setTitle("Produtos Vendidos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel tituloLabel = new JLabel("Lista de Produtos Vendidos");
        tituloLabel.setBounds(150, 10, 200, 30);
        add(tituloLabel);

        String[] colunas = {"ID", "Nome", "Valor", "Status"};
        modeloTabela = new DefaultTableModel(null, colunas);
        tabelaVendas = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaVendas);
        scrollPane.setBounds(50, 50, 400, 200);
        add(scrollPane);

        listarProdutosVendidos();

        setVisible(true);
    }

    // Método para listar produtos vendidos
    private void listarProdutosVendidos() {
        ProdutosDAO dao = new ProdutosDAO();
        ArrayList<ProdutosDTO> produtosVendidos = dao.listarProdutosVendidos();
        
        for (ProdutosDTO produto : produtosVendidos) {
            modeloTabela.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getValor(),
                produto.getStatus()
            });
        }
    }
}
