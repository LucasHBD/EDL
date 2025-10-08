public class FilaArray implements Fila{
    int i = 0, f = 0, N, incremento;
    Object O[];

    public FilaArray(int N, int incremento){
        O = new Object[N];
        this.N = N;
        this.incremento = incremento;
    }

    public Object first(){
        return O[i];
    }

    public boolean isEmpty(){
        return f == i;
    }

    public int size(){
        return (N - i+f )%N;
    }

    public void enqueue(Object o){
        if(size() == N-1){ // se estiver cheio ( tamanho for igual ao ultimo elemento do array)
            int novoTamanho; // define variável para novo tamanho
            if(incremento == 0) novoTamanho = N * 2; // estratégia de duplicação
            else novoTamanho = N + incremento; // ou estratégia incremental

            Object b[] = new Object[novoTamanho]; // instância novo array

            int ii = i; // variável para guardar o valor do inicio da fila
            for(int ff = 0; ff < size(); ff++){
                b[ff] = O[ii];
                ii = (ii + 1)%N;
            } // o inicio do array antigo vira o primeiro indice do array novo, mesmo o array sendo quebrado ou não
            f = size(); // final da fila
            i = 0; // inicio da fila vira inicio do array
            N = novoTamanho; // novo tamanho do array
            O = b; // array recebe array novo
        }
        O[f] = o;
        f = (f + 1)%N;
    }

    public Object dequeue(){
        if(isEmpty()) throw new FilaVaziaExcecao("Fila Vazia!"); //se estiver vazia, lança exceção
        Object temp = O[i]; // armazena o valor do inicio da fila em uma variável temporária
        //O[i] = null; // atualiza a posição de inicio da fila para o próximo
        i = (i+1)%N; // atualiza a posição de inicio da fila para o próximo
        return temp; // retorna o valor armazenado
    }

    public void mostrar(){
        int ii = this.i;
        for(int i = 0; i < size(); i++, ii=(ii+1)%N){
            System.out.print(O[ii]);
            System.out.print(", ");
        }
    }

    public static void main(String[] args){
        FilaArray fila = new FilaArray(5,0);
        fila.enqueue(5);
        fila.enqueue(6);
        fila.dequeue();
        fila.enqueue(8);
        fila.enqueue(2);


        fila.mostrar();
        System.out.println();
        System.out.println(fila.first());

        fila.dequeue();
        fila.dequeue();
        fila.enqueue(1);
        fila.enqueue(10);
        fila.enqueue(16);
        fila.enqueue(19);
        fila.enqueue(20);
        fila.enqueue(22);
        fila.dequeue();
        fila.dequeue();
        fila.mostrar();

        System.out.println();
        System.out.println(fila.first());

        fila.enqueue(37);
        fila.enqueue(45);
        fila.dequeue();
        fila.dequeue();
        fila.mostrar();

        System.out.println();
        System.out.println(fila.first());

        fila.dequeue();
        fila.dequeue();
        fila.mostrar();

        System.out.println();
        System.out.println(fila.first());

        fila.dequeue();
        fila.dequeue();
        fila.dequeue();
        fila.mostrar();
        //fila.dequeue();
        System.out.println();
    }
}