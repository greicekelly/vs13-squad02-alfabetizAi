package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.alfabetizai.models.Desafio;
import br.com.dbc.vemser.alfabetizai.repository.DesafioRepository;

import java.util.List;

public class DesafioService {

    private DesafioRepository desafioRepository;

    public DesafioService() {
        desafioRepository = new DesafioRepository();
    }

    public void adicionar(Desafio desafio) {
        try {

            Desafio desafioAdicionado = desafioRepository.adicionar(desafio);

            System.out.println("Desafio adicionado com sucesso! " + desafioAdicionado);
        } catch (BancoDeDadosException e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public List getListaDesafios() {
        try {
            return desafioRepository.listar();
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void visualizarTodos() {
        try {
            List<Desafio> listar = desafioRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void editar(Integer id, Desafio desafioEditado) {
        try {
            boolean conseguiuEditar = desafioRepository.editar(id, desafioEditado);
            System.out.println("Desafio editado com sucesso? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Integer id, Desafio desafio) {
        try {
            boolean conseguiuRemover = desafioRepository.remover(id, desafio);
            System.out.println("Desafio removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarDesafiosPorModulo(int idModuloEscolhido) {
        try {
            List<Desafio> listar = desafioRepository.listarPorModulo(idModuloEscolhido);
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

}