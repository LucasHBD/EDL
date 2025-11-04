package lista;

public class VetorDL implements VetorInterface{
    private int capacidade;
    private No inicio;
    private No fim;

    public VetorDL(){
        this.inicio = null;
        this.fim = null;
        this.capacidade = 0;
    }

    private No search(int rank) throws ElemVazia{
        if(inicio == null && fim == null) throw new ElemVazia("Vetor Vazio");
        No atual = new No();
        if(rank < (size()/2)){
            atual = inicio;
            for(int i = 0; i < rank; i++){
                atual = atual.getProximo();
            }
        }
        else{
            if(rank > size()/2){
                atual = fim;
                for(int i = 0; i > rank; i++){
                    atual = atual.getAnterior();
                }
            }
        }
        return atual;
    }

    public int size(){
        return capacidade;
    }

    public boolean isEmpty(){
        return capacidade == 0;
    }

    public Object elementAtRank(int rank){
        No atual = search(rank);
        return atual.getElemento();
    }

    public void insertAtRank(int rank, Object elemento){
        No atual = search(rank);
        atual.getAnterior();
        No novo_no = new No();
        novo_no.setElemento(elemento);
        novo_no.setAnterior(atual.getAnterior());
        novo_no.setProximo(atual);
        atual.getAnterior().setProximo(novo_no);
        atual.setAnterior(novo_no);
    }

    public Object removeAtRank(int rank){
        No atual = search(rank);
        Object temp = atual.getElemento();
        (atual.getAnterior()).setProximo(atual.getProximo());
        (atual.getProximo()).setAnterior(atual.getAnterior());
        atual.setAnterior(null);
        atual.setProximo(null);
        return temp;
    }

    public Object replaceAtRank(int rank, Object elemento){
        No atual = search(rank);
        Object temp = atual.getElemento();
        atual.setElemento(elemento);
        return temp;
    }
}
