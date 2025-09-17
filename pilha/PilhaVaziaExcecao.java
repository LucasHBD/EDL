// Começando a implementação pela Exceção caso a pilha esteja vazia

public class PilhaVaziaExcecao extends RuntimeException{
    public PilhaVaziaExcecao(String err){
        super(err);
    }
}