package Services;

import java.util.ArrayList;
import java.util.List;

public class ServicoCaminhoes {
    private List<Caminhao> listaCaminhoes;

    public ServicoCaminhoes() {
        listaCaminhoes = new ArrayList<>();
        inicializarCaminhoes();
    }

    public void inicializarCaminhoes() {
        listaCaminhoes.add(new Caminhao("Volvo FH16", 80, 10, 2.5, "TRK-1"));
        listaCaminhoes.add(new Caminhao("Scania R Series", 85, 8, 2.8, "TRK-2"));
        listaCaminhoes.add(new Caminhao("Mercedes-Benz Actros", 75, 12, 2.0, "TRK-3"));
        listaCaminhoes.add(new Caminhao("MAN TGX", 90, 9, 3.0, "TRK-4"));
        listaCaminhoes.add(new Caminhao("DAF XF", 88, 11, 2.7, "TRK-5"));
    }



}

