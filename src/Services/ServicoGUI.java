package Services;

import javax.swing.*;
import java.awt.*;

public class ServicoGUI {

    public ServicoGUI() {
        // Criar o frame principal
        JFrame frame = new JFrame("Sistema de Gerenciamento de Cargas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridBagLayout()); // Usando GridBagLayout

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        // Criar e adicionar os botões
        JButton btnCadastro = new JButton("Cadastro");
        JButton btnAlteracoes = new JButton("Alterações");
        JButton btnFrete = new JButton("Frete");
        JButton btnOutros = new JButton("Outros");

        frame.add(btnCadastro, gbc);
        frame.add(btnAlteracoes, gbc);
        frame.add(btnFrete, gbc);
        frame.add(btnOutros, gbc);

        // Configurar a visibilidade do frame
        frame.setVisible(true);
    }

    public void exibir() {
        // Método para criar e exibir a GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ServicoGUI();
            }
        });
    }
}
