package services;

import models.Desafio;

import java.util.ArrayList;
import java.util.Scanner;

public class DesafioService {


    private ArrayList<Desafio> desafios;

    public DesafioService() {
        this.desafios = new ArrayList<>();
    }

    public DesafioService(ArrayList desafiosDoModulo) {
        this.desafios = desafiosDoModulo;
    }

    public void adicionarDesafio(Desafio desafio) {
        desafios.add(desafio);
    }

    public ArrayList<Desafio> getListaDesafios() {
        return desafios;
    }

    public void visualizarDesafios() {
        if (desafios.isEmpty()) {
            throw new IllegalStateException("Nenhum desafio cadastrado.");
        }
        for (int i = 0; i < desafios.size(); i++) {
            System.out.println("\nId do Desafio: " + ((int) i + 1) + "\n" + desafios.get(i).toString());
        }
    }

//    public void consultarDesafio(int index) {
//        System.out.println("\nTítulo: " + desafios.get(index).getTitulo() + ", Tipo: " + desafios.get(index).getTipoDesafio().name() + ", Autor: " + desafios.get(index).getAutor().getNome());
//    }

//    public void editarDesafio(int index, Desafio desafioEditado) {
//        Desafio desafio = desafios.get(index);
//        desafio.setTitulo(desafioEditado.getTitulo());
//        desafio.setTipoDesafio(desafioEditado.getTipoDesafio());
//        desafio.setAutor(desafioEditado.getAutor());
//        System.out.println("\nEdição realizada com sucesso.");
//    }

    public void removerDesafio(int index) {
        desafios.remove(index);
        System.out.println("\n" + desafios.get(index).getTitulo() + " removido com sucesso.");
    }

}
