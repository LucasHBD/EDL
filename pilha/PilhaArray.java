public class PilhaArray implements Pilha{
    private int capacidade;
    private Object[] a; // array
    private int t; // topo da pilhas
    private int FC; // Fator de Crescimento
    
    public PilhaArray(int capacidade, int crescimento){
        this.capacidade = capacidade;
        this.t = -1;
        this.FC = crescimento;
        if (crescimento <= 0) FC = 0;
        this.a = new Object[capacidade];
    }

    public int size(){
        return t + 1; // retorna o valor do topo mais um
    }
    public boolean isEmpty(){
        return t == -1; // se estiver vazia retorna True
    }
    public Object top() throws PilhaVaziaExcecao{
        if (isEmpty()) throw new PilhaVaziaExcecao("Pilha Vazia");
        return a[t];
    }
    public void push(Object o){
        if(t >= capacidade -1){
            if(FC == 0){
                capacidade *= 2 ;
            }
            else{
                capacidade += FC;
            }
            Object b[] = new Object[capacidade];
            for(int i = 0; i < a.length; i++){
                b[i] = a[i];
            }
            a = b;
        }
        a[++t] = o;
    }
    
    public Object pop() throws PilhaVaziaExcecao{
        if (isEmpty()) throw new PilhaVaziaExcecao("Pilha Vazia");
        t = t - 1;
        return a[t+1];
    }

    public Object get(int i) throws IndexOutOfBoundsException{
        if (i < 0 || i > t) throw new IndexOutOfBoundsException("Indice Invalido");
        return a[i];
    }
}