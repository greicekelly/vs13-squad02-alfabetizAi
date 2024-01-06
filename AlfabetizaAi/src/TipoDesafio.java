public enum TipoDesafio {

    QUIZ,

    JOGO;

    public static TipoDesafio ofTipo(Integer tipo) {
        for(TipoDesafio tp : TipoDesafio.values()) {
            if(tp.ordinal() == tipo) {
                return tp;
            }
        }
        return null;
    }

}
