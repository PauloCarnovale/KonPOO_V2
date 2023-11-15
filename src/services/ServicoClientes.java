package services;

import model.Cliente;

import java.util.ArrayList;
import java.util.List;


public class ServicoClientes {
    private List<Cliente> listaClientes;

    public ServicoClientes() {
        listaClientes = new ArrayList<>();
        //inicializarClientes();
    }

    public void inicializarClientes() {
        listaClientes.add(new Cliente("CLT-1", "João Silva", "11 98765-4321", "12345678901"));
        listaClientes.add(new Cliente("CLT-2", "Maria Oliveira", "21 98765-4321", "23456789012"));
        listaClientes.add(new Cliente("CLT-3", "Lucas Souza", "31 98765-4321", "34567890123"));
        listaClientes.add(new Cliente("CLT-4", "Ana Santos", "41 98765-4321", "45678901234"));
        listaClientes.add(new Cliente("CLT-5", "Pedro Costa", "51 98765-4321", "56789012345"));
    }




    // Método para verificar a validade do CPF
    public static boolean verificarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            return false;
        }

        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < cpf.length() -1; nCount++) {
            digitoCPF = Integer.valueOf(cpf.substring(nCount -1, nCount)).intValue();

            // multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
            d1 = d1 + ( 11 - nCount ) * digitoCPF;

            // para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
            d2 = d2 + ( 12 - nCount ) * digitoCPF;
        };

        // Primeiro resto da divisão por 11.
        resto = (d1 % 11);

        // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2)
            digito1 = 0;
        else
            digito1 = 11 - resto;

        d2 += 2 * digito1;

        // Segundo resto da divisão por 11.
        resto = (d2 % 11);

        // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2)
            digito2 = 0;
        else
            digito2 = 11 - resto;

        // Digito verificador do CPF que está sendo validado.
        String nDigVerific = cpf.substring(cpf.length()-2, cpf.length());

        // Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        // comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
        return nDigVerific.equals(nDigResult);
    }
}