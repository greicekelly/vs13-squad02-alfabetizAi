package enums;

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
}
