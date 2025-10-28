package lista;

public class Vetor implements VetorInterface{
    private int capacidade, FC, ultimo_elemento;
    Object vetor[];

    public Vetor(int capacidade, int FC){
        this.FC = FC;
        this.capacidade = capacidade;
        if(FC <= 0){
            this.FC = 0;
        }
        this.ultimo_elemento = 0;

        vetor = new Object[capacidade];
    }

    public int size(){
        return capacidade;
    }

    public boolean isEmpty(){
        return ultimo_elemento == 0;
    }

    public Object elementAtRank(int rank) throws ElemVazia{ 
        if(rank >= ultimo_elemento || rank < 0){
            throw new ElemVazia("Índice Inválido");
        }
        else{
            return vetor[rank];
        }
    }
    public void insertAtRank(int rank, Object novo_elemento) throws ElemVazia{
        if(rank > ultimo_elemento || rank < 0){
            throw new ElemVazia("Índice Inválido");
        }
        else{
            if(ultimo_elemento == capacidade){
                capacidade += FC;
                if(FC == 0){
                    capacidade *=2;
                }
                Object novo_vetor[] = new Object[capacidade];
                for(int i = 0; i < ultimo_elemento; i++){
                    novo_vetor[i] = vetor[i];
                }
                vetor = novo_vetor;
            }
            for(int i = ultimo_elemento; i > rank; i--){
                vetor[i] = vetor[i-1];
            }
            vetor[rank] = novo_elemento;
            ultimo_elemento++;
        }
    }
    public Object replaceAtRank(int rank, Object novo_elemento) throws ElemVazia{
        if(rank >= ultimo_elemento || rank < 0){
            throw new ElemVazia("Índice Inválido");
        }
        else{
            Object elemento_antigo = vetor[rank];
            vetor[rank] = novo_elemento;
            return elemento_antigo;
        }
    }

    public Object removeAtRank(int rank) throws ElemVazia{
        if(rank >= ultimo_elemento || rank < 0){
            throw new ElemVazia("Índice Inválido");
        }
        else{
            Object elemento_antigo = vetor[rank];
            for(int i = rank; i < ultimo_elemento; i++){
                vetor[i] = vetor[i+1];
            }
            vetor[ultimo_elemento - 1] = null;
            ultimo_elemento--;
            return elemento_antigo;
        }
    }

    public void printVetor(){
        if(isEmpty()){
            System.out.println("Vetor Vazio");
        }
        else{
            System.out.print("[");

            for(int i = 0; i < ultimo_elemento; i++){
                System.out.print(vetor[i] + ", ");
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
