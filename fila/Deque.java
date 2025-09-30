public class Deque implements Fila{
    Object O[];
    int i = 0, f = 0, N, incremento;

    public int pushFront(Object o){
        if(size() == N-1){
            int novoTamanho;
            if (incremento == 0) novoTamanho = N * 2;
            else novoTamanho = N + incremento;
        }
    }
}