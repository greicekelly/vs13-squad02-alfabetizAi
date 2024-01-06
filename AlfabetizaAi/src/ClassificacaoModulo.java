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
}