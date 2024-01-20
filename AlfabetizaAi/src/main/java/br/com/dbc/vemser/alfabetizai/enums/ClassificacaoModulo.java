package br.com.dbc.vemser.alfabetizai.enums;

public enum ClassificacaoModulo {
    INICIANTE,
    INTERMEDIARIO,
    AVANCADO;

    public static ClassificacaoModulo trazEnumPeloOrdinal(int opcao){
        switch (opcao){
            case 1:
                return ClassificacaoModulo.INICIANTE;
            case 2:
                return ClassificacaoModulo.INTERMEDIARIO;
            case 3:
                return ClassificacaoModulo.AVANCADO;
            default:
                System.out.println("Opção inválida.");
                return null;
        }
    }

    public static ClassificacaoModulo ofTipo(Integer tipo) {
        for(ClassificacaoModulo cm : ClassificacaoModulo.values()) {
            if(cm.ordinal() == tipo) {
                return cm;
            }
        }
        return null;
    }

}