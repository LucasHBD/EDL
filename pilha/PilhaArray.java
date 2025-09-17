public class PilhaArray implements Pilha{
    private int capacidade;
    private Object[] a; // array
    private int t; // topo da pilhas
    private int FC; // Fator de Crescimento

    public int size(){
        return t + 1; // retorna o valor do topo mais um
    }
    public boolean isEmpty(){
        return t == -1; // se estiver vazia retorna True
    }
    public Object top() throws PilhaVaziaExcecao{
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
            for(int i = 0; i <= a.length; i++){
                b[i] = a[i];
            }
            a = b;
        }
        a[++t] = o;
    }
    
    public Object pop() throws PilhaVaziaExcecao{
        t = t - 1;
        return a[t+1];
    }

    
    public PilhaArray(int capacidade, int crescimento){
        this.capacidade = capacidade;
        t = -1;
        FC = crescimento;
        if (crescimento <= 0) FC = 0;
        a = new Object[capacidade];
    }
}