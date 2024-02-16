package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioCreateDTO;
import br.com.dbc.vemser.alfabetizai.dto.desafio.DesafioDTO;
import br.com.dbc.vemser.alfabetizai.dto.modulo.ModuloDTO;
import br.com.dbc.vemser.alfabetizai.enums.TipoDesafio;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.models.Modulo;
import br.com.dbc.vemser.alfabetizai.repository.IDesafioRepository;
import br.com.dbc.vemser.alfabetizai.repository.IModuloRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class DesafioServiceTest {

    @Mock
    private IDesafioRepository desafioRepository;

    @Mock
    private IModuloRepository moduloRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private DesafioService desafioService;

    DesafioCreateDTO desafioCreateDTO;
    DesafioDTO desafioDTO;
    Desafio desafio;

    @Test
    public void deveriaListarTodosDesafios(){

    }

    private static DesafioCreateDTO retornarDesafioCreateDTO(){
            DesafioCreateDTO desafioCreateDTO = new DesafioCreateDTO();
            desafioCreateDTO.setIdModulo(1);
            desafioCreateDTO.setTitulo("Escolha a letra inicial");
            desafioCreateDTO.setConteudo("Aprenda as consoantes");
            desafioCreateDTO.setTipo(TipoDesafio.valueOf("QUIZ"));
            desafioCreateDTO.setInstrucao("Marque a letra, que corresponde a primeira letra da palavra Banana.");
            desafioCreateDTO.setA("D");
            desafioCreateDTO.setB("T");
            desafioCreateDTO.setC("B");
            desafioCreateDTO.setD("S");
            desafioCreateDTO.setE("R");
            desafioCreateDTO.setAlternativaCorreta("C");
            desafioCreateDTO.setPontos(10);
            desafioCreateDTO.setAtivo("S");

            return desafioCreateDTO;
    }

    private static Desafio retornarDesafio(){
            Desafio desafio = new Desafio();
            desafio.setId(72);
            desafio.setTitulo("Escolha a letra inicial");
            desafio.setConteudo("Aprenda as consoantes");
            desafio.setTipo(TipoDesafio.valueOf("QUIZ"));
            desafio.setInstrucao("Marque a letra, que corresponde a primeira letra da palavra Banana.");
            desafio.setA("D");
            desafio.setB("T");
            desafio.setC("B");
            desafio.setD("S");
            desafio.setE("R");
            desafio.setAlternativaCorreta("C");
            desafio.setPontos(10);

            Modulo modulo = new Modulo();
            modulo.setId(69);
            desafio.setModulo(modulo);

            desafio.setAtivo("S");

            return desafio;
    }

    private static DesafioDTO retornarDesafioDTO(){
            DesafioDTO desafioDTO = new DesafioDTO();
        desafioDTO.setId(72);
        desafioDTO.setTitulo("Escolha a letra inicial");
        desafioDTO.setConteudo("Aprenda as consoantes");
        desafioDTO.setTipo(TipoDesafio.valueOf("QUIZ"));
        desafioDTO.setInstrucao("Marque a letra, que corresponde a primeira letra da palavra Banana.");
        desafioDTO.setA("D");
        desafioDTO.setB("T");
        desafioDTO.setC("B");
        desafioDTO.setD("S");
        desafioDTO.setE("R");
        desafioDTO.setAlternativaCorreta("C");
        desafioDTO.setPontos(10);
        desafioDTO.setAtivo("S");

        ModuloDTO moduloDTO = new ModuloDTO();
        moduloDTO.setId(69);
        desafioDTO.setModulo(moduloDTO);

        return desafioDTO;
    }
}