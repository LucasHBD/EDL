package lista;

public class Sequencia{
    private No inicio;
    private No fim;
    private int capacidade;

    public Sequencia(){
        this.inicio = new No();
        this.fim = new No();
        fim.setAnterior(inicio);
        inicio.setProximo(fim);
        this.capacidade = 0;
    }

    public int size(){
        return capacidade;
    }

    public boolean isEmpty(){
        return capacidade == 0;
    }

    public No atRank(int rank) throws ElemVazia{
        System.out.println("Tamanho: " + size());
        imprimirSequencia();
        if(inicio.getProximo() == null) throw new ElemVazia("Vetor Vazio");
        No atual = new No();
        if(rank <= (size()/2)){
            atual = inicio.getProximo();
            for(int i = 0; i < rank; i++){
                System.out.println("Atual: " + atual.getElemento());
                atual = atual.getProximo();
            }
        }
        else{
            atual = fim.getAnterior();
            for(int i = 0; i >= rank; i++){
                System.out.println("Atual: " + atual.getElemento());
                atual = atual.getAnterior();
            }
        }
        atual.setAnterior(atual.getAnterior());
        atual.setProximo(atual.getProximo());
        return atual;
    }

    public int rankOf(No n){
        n = inicio.getProximo();
        int r = 0;
        while(n != n.getElemento() && n != fim){
            n = n.getProximo();
            r++;
        }
        return r;
    }

    public No insertAfter(No p, Object e){
        if(p == null)
            return null;
        No novo_no = new No();
        novo_no.setElemento(e);
        novo_no.setAnterior(p);
        novo_no.setProximo(p.getProximo());

        if(p.getProximo() != null){
            (p.getProximo()).setAnterior(novo_no);
        }
        else{
            fim = novo_no;
        }
        p.setProximo(novo_no);
        capacidade++;
        return novo_no;
    }
    
    public No insertBefore(No p, Object e){
        if(p == null)
            return null;
        No novo_no = new No();
        novo_no.setElemento(e);
        novo_no.setProximo(p);
        novo_no.setAnterior(p.getAnterior());
        if(p.getAnterior() != null){
            (p.getAnterior()).setProximo(novo_no);
        }
        else{
            inicio = novo_no;
        }
        p.setAnterior(novo_no);
        capacidade++;
        return novo_no;
    }

    public Object remove(No p){
        Object temp = p.getElemento();
        (p.getAnterior()).setProximo(p.getProximo());
        (p.getProximo()).setAnterior(p.getAnterior());
        p.setAnterior(null);
        p.setProximo(null);
        capacidade--;
        return temp;
    }

    public No first(){
       return inicio;
    }

    public No last(){
        return fim;
    }

    public No before(No p){
        return p.getAnterior();
    }

    public No after(No p){
        return p.getProximo();
    }

    public No insertFirst(Object e){
        No novo_no = new No();
        novo_no.setElemento(e);
        // if(isEmpty()){
        //     inicio.setProximo(novo_no);
        //     fim.setAnterior(novo_no);
        // }
        novo_no.setAnterior(inicio);
        novo_no.setProximo(inicio.getProximo());
        inicio.getProximo().setAnterior(novo_no);
        inicio.setProximo(novo_no);
        capacidade++;
        return novo_no;
    }

    public No insertLast(Object e){
        No novo_no = new No();
        novo_no.setElemento(e);
        if(isEmpty()){
            inicio.setProximo(novo_no);
            fim.setAnterior(novo_no);;
        }
        novo_no.setAnterior(fim.getAnterior());
        novo_no.setProximo(fim);
        fim.getAnterior().setProximo(novo_no);
        fim.setAnterior(novo_no);
        capacidade++;
        return novo_no;
    }

    public No replaceElement(No p, Object e){
        No novo_no = new No();
        novo_no.setElemento(e);
        novo_no.setAnterior(p.getAnterior());
        novo_no.setProximo(p.getProximo());
        p.getAnterior().setProximo(novo_no);
        p.getProximo().setAnterior(novo_no);

        return novo_no;
    }

    public void swapElement(No p, No q){
        Object temp = p.getElemento();
        p.setElemento(q.getElemento());
        q.setElemento(temp);
    }
    
    public Object elementAtRank(int rank){
        No atual = atRank(rank);
        return atual.getElemento();
    }

    public void insertAtRank(int rank, Object elemento){
        No atual = atRank(rank);
        atual.getAnterior();
        No novo_no = new No();
        novo_no.setElemento(elemento);
        novo_no.setAnterior(atual.getAnterior());
        novo_no.setProximo(atual);
        atual.getAnterior().setProximo(novo_no);
        atual.setAnterior(novo_no);
        capacidade++;
    }

    public Object removeAtRank(int rank){
        No atual = atRank(rank);
        Object temp = atual.getElemento();
        (atual.getAnterior()).setProximo(atual.getProximo());
        (atual.getProximo()).setAnterior(atual.getAnterior());
        atual.setAnterior(null);
        atual.setProximo(null);
        capacidade--;
        return temp;
    }

    public Object replaceAtRank(int rank, Object elemento){
        No atual = atRank(rank);
        Object temp = atual.getElemento();
        atual.setElemento(elemento);
        return temp;
    }

    public void imprimirSequencia() {
        System.out.print("Sequência: ");
        No atual = first();

        // Pula o nó sentinela
        atual = atual.getProximo();

        while (atual != null && atual.getElemento() != null) {
            System.out.print(atual.getElemento() + " ");
            atual = atual.getProximo();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        try {
            Sequencia seq = new Sequencia();

            System.out.println("=== Teste da classe Sequencia ===");

            // Testando inserções básicas
            System.out.println("\nInserindo elementos com insertFirst...");
            seq.insertFirst("C");
            seq.insertFirst("B");
            seq.insertFirst("A");

            // Agora lista deve ser: A, B, C
            seq.imprimirSequencia();

            System.out.println("\nInserindo elementos com insertLast...");
            seq.insertLast("D");
            seq.insertLast("E");

            // Agora lista deve ser: A, B, C, D, E
            seq.imprimirSequencia();

            // Testando acesso por rank
            System.out.println("\nElemento no rank 2 (esperado: C): " + seq.elementAtRank(2));

            // Testando insertAtRank
            System.out.println("\nInserindo 'X' no rank 1...");
            seq.insertAtRank(1, "X");
            // Esperado: A, X, B, C, D, E
            seq.imprimirSequencia();

            // Testando removeAtRank
            System.out.println("\nRemovendo elemento no rank 3...");
            Object removido = seq.removeAtRank(3);
            System.out.println("Elemento removido: " + removido);
            seq.imprimirSequencia();

            // Testando replaceAtRank
            System.out.println("\nSubstituindo elemento no rank 2 por 'Z'...");
            seq.replaceAtRank(2, "Z");
            seq.imprimirSequencia();

            // Fim dos testes
            System.out.println("\nFim dos testes.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}