package services;

import models.Modulo;

import java.util.ArrayList;

public class ModuloService extends Modulo {

    private ArrayList<Modulo> lista;

    public ModuloService() {
        this.lista = new ArrayList<>();
    }

    public void adicionar(Modulo modulo) {
        lista.add(modulo);
    }

//    public void visualizarTodos() {
//        for (int i = 0; i < lista.size(); i++) {
//            visualizarModulo(lista.get(i), i);
//        }
//    }

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
        }
        return null;
    }

    public void editar(int index, Modulo cadastroEditado) {
        Modulo modulo = lista.get(index);
        modulo.setAutor(cadastroEditado.getAutor());
        modulo.setTitulo(cadastroEditado.getTitulo());
        modulo.setClassificacao(cadastroEditado.getClassificacao());
        modulo.setFoiAprovado(cadastroEditado.isFoiAprovado());
        System.out.println("Modulo atualizado com sucesso.");
        System.out.println("--------------------------------");
    }

    public void adminAprovar(int i) {
        if (i > lista.size() || i <= 0) throw new IllegalArgumentException("Opção de desafio inexistente");
        Modulo modulo = lista.get(i - 1);
        modulo.setFoiAprovado(modulo.isFoiAprovado());
        System.out.println("Modulo aprovado com sucesso");
    }

    public void remover(int index) {
        lista.remove(index - 1);
    }

    @Override
    public String toString() {
        return "Titulo: "+getTitulo() +" - Autor: "+getAutor()+" - Classificação: "+getClassificacao();
    }
}
