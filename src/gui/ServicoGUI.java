package gui;

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

        // Criar e adicionar as listas suspensas
        String[] cadastroOptions = {"Opção 1", "Opção 2", "Opção 3"};
        JComboBox<String> cbCadastro = new JComboBox<>(cadastroOptions);

        String[] alteracoesOptions = {"Opção 1", "Opção 2", "Opção 3"};
        JComboBox<String> cbAlteracoes = new JComboBox<>(alteracoesOptions);

        String[] freteOptions = {"Opção 1", "Opção 2", "Opção 3"};
        JComboBox<String> cbFrete = new JComboBox<>(freteOptions);

        String[] outrosOptions = {"Opção 1", "Opção 2", "Opção 3"};
        JComboBox<String> cbOutros = new JComboBox<>(outrosOptions);

        frame.add(cbCadastro, gbc);
        frame.add(cbAlteracoes, gbc);
        frame.add(cbFrete, gbc);
        frame.add(cbOutros, gbc);

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
