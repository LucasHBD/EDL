public class Deque implements Fila{
    Object O[];
    int i = 0, f = 0, N, incremento;

    public boolean isEmpty(){
        return f == i;
    }

    public int size(){
        return (N - i + f)% N;
    }

    public int pushFront(Object o){
        if(size() == N-1){
            int novoTamanho;
            if (incremento == 0) novoTamanho = N * 2;
            else novoTamanho = N + incremento;

            Object b[] = new Object[novoTamanho];

            int ii = i;
            for(int ff = 0; ff < size(); ff++){
                b[ff] = O[ii];
                ii = (ii + 1)% N;
            }
            f = size();
            i = 0;
            N = novoTamanho;
            O = b;
        }
        O[i] = o;
        i = (i + 1)% N;
    }

    public Object removeFront(){
        if(isEmpty()) throw new FilaVaziaExcecao("Fila Vazia!");
        Object temp = O[i];
        i = (i + 1)% N;
        return temp;
    }

    public int pushBack(Object o){
        if(size() == N-1){
            int novoTamanho;
            if(incremento == 0) novoTamanho = N * 2;
            else novoTamanho = N + incremento;

            Object b[] = new Object[novoTamanho];

            int ii = i;
            for(int ff = 0; f < size(); ff++){
                b[ff] = O[ii];
                ii = (ii + 1)% N;
            }
            f = size();
            i = 0;
            N = novoTamanho;
            O = b;
        }
        O[f] = o;
        f = (f + 1)%N;
    }

    public int removeBack(){
        if (isEmpty()) throw new FilaVaziaExcecao("Fila Vazia!");
        Object temp = O[f];
        f = (f + 1)% N;
        return temp;
    }

    public static void main(String[] args){
        Deque d = new Deque(5,0);
    }
}