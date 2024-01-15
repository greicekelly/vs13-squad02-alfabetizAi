package services;

import exceptions.BancoDeDadosException;
import models.Admin;
import models.Modulo;
import repository.ModuloRepository;

public class ModuloService extends Modulo {
    private ModuloRepository moduloRepository;
    public ModuloService() {
        moduloRepository = new ModuloRepository();
    }

    // criação de um objeto
    public void adicionarModulo(Modulo modulo) {
        try {
            Modulo moduloAdicionado = moduloRepository.adicionar(modulo);
            System.out.println("Módulo adicionado com sucesso! " + moduloAdicionado);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    // remoção
    public void remover(Integer id, Modulo modulo) {
        try {
            boolean conseguiuRemover = moduloRepository.remover(id, modulo);
            System.out.println("removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editar(Integer id, Modulo modulo) {
        try {
            boolean conseguiuEditar = moduloRepository.editar(id, modulo);
            System.out.println("editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }public void editarAprovacaoPorAdmin(Integer idAdmin, Integer idModulo, String aprovacaoModulo) {
        try {
            boolean conseguiuEditar = moduloRepository.editarAprovacaoPorAdmin(idAdmin, idModulo, aprovacaoModulo);
            System.out.println("editado? " + conseguiuEditar + "| com id=" + idModulo);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
    // leitura
    public void listar() {
        try {
            moduloRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarSemAprovacao() {
        try {
            moduloRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Titulo: "+getTitulo() +" - Autor: "+getAutor()+" - Classificação: "+getClassificacao();
    }
}