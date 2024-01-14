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
<<<<<<< HEAD

    public void adicionar(Modulo modulo) {
        lista.add(modulo);
    }

    public void visualizarTodos() {
        for (int i = 0; i < lista.size(); i++) {
            visualizarModulo(lista.get(i), i);
        }
    }

//    public void visualizarModulo(Modulo modulo, int index) {
//        System.out.printf("""
//                _______________Modulo %d______________
//                Titulo: %s
//                Autor: %s
//                Classificação: %s
//                """, index + 1, modulo.getTitulo(), modulo.getAutor().getNome(), modulo.getClassificacao());
//        modulo.visualizarDesafios();
//    }

//    public void consultar(int index){
//        Modulo modulo = lista.get(index);
//        visualizarModulo(modulo, index);
//        System.out.println("--------------------------------");
//    }

    public Modulo consultarModuloTitulo(String nome) {
        for (Modulo modulo : lista) {
            if(modulo.getTitulo().equals(nome)){
                return modulo;
            }
=======
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
>>>>>>> develop
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
    }public void editarAprovacaoPorAdmin(Admin admin, Modulo modulo) {
        try {
            boolean conseguiuEditar = moduloRepository.editarAprovacaoPorAdmin(admin, modulo);
            System.out.println("editado? " + conseguiuEditar + "| com id=" + modulo.getId());
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
    @Override
    public String toString() {
        return "Titulo: "+getTitulo() +" - Autor: "+getAutor()+" - Classificação: "+getClassificacao();
    }
}