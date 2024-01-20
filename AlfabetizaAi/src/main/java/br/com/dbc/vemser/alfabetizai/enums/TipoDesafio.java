package br.com.dbc.vemser.alfabetizai.enums;

public enum TipoDesafio {

    QUIZ("QUIZ"),

    JOGO("JOGO");

    public final String nome;

    TipoDesafio(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public static TipoDesafio ofTipo(Integer tipo) {
        for(TipoDesafio tp : TipoDesafio.values()) {
            if(tp.ordinal() == tipo) {
                return tp;
            }
        }
        return null;
    }

    public static TipoDesafio trazEnumPeloOrdinal(int opcao){
        switch (opcao){
            case 1:
                return TipoDesafio.QUIZ;
            case 2:
                return TipoDesafio.JOGO;
            default:
                System.out.println("Opção inválida.");
                return null;
        }
    }

}
