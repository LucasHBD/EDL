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

    public void printVetor(){
        if(isEmpty()){
            System.out.println("Vetor Vazio");
        }
        else{
            System.out.print("[");

            for(No n = inicio; n != null; n = n.getProximo()){
                System.out.print(n.getElemento() + " ");
            }
            System.out.print("]\n");
        }
    }

    public static void main(String args[]){
        Vetor vetor = new Vetor(5, 0);
        vetor.insertAtRank(0, 1);
        vetor.printVetor();
        vetor.insertAtRank(1, 2);
        vetor.printVetor();
        vetor.insertAtRank(2, 3);
        vetor.printVetor();
        vetor.insertAtRank(1, 4);
        vetor.printVetor();
        vetor.insertAtRank(3, 5);
        vetor.printVetor();
        vetor.insertAtRank(2, 6);
        vetor.printVetor();
        System.out.println(vetor.removeAtRank(2));
        vetor.printVetor();
        vetor.replaceAtRank(0, 10);
        vetor.printVetor();
        System.out.println(vetor.elementAtRank(1));
        vetor.printVetor();
    }
}
