public class DequeDLinkedList{
    private No inicio;
    private No fim;

    public class No{
        private Object elemento;
        private No proximo;
        private No anterior;

        public No(){
            this.elemento = null;
            this.proximo = null;
            this.anterior = null;
        }

        public Object getElemento() {
            return elemento;
        }

        public void setElemento(Object elemento) {
            this.elemento = elemento;
        }

        public No getProximo() {
            return proximo;
        }

        public void setProximo(No proximo) {
            this.proximo = proximo;
        }

        public No getAnterior() {
            return anterior;
        }

        public void setAnterior(No anterior) {
            this.anterior = anterior;
        }
    }


    public DequeDLinkedList(){
        this.inicio = null;
        this.fim = null;
    }

    
    public void inserirInicio(Object o){
        if(inicio == null && fim == null){
            No novo = new No();
            novo.setElemento(o);
            inicio = novo;
            fim = novo;
        }
        else{
            No novo = new No();
            novo.setElemento(o);
            novo.setProximo(inicio);
            inicio.setAnterior(novo);
            inicio = novo;
        }
    }

    public void inserirFim(Object o){
        if(inicio == null && fim == null){
            No novo = new No();
            novo.setElemento(o);
            inicio = novo;
            fim = novo;
        }
        else{
            No novo = new No();
            novo.setElemento(o);
            fim.setProximo(novo);
            novo.setAnterior(fim);
            fim = novo;
        }
    }

    public Object removerInicio() throws DequeVazioExcecao{
        Object temp = inicio.getElemento();
        if (inicio == null && fim == null) throw new DequeVazioExcecao("Deque Vazio!");
        else{
            inicio = inicio.getProximo();
            if(inicio != null){
                inicio.setAnterior(null);
            }
            else fim = null;
        }
        return temp;
    }

    public Object removerFim() throws DequeVazioExcecao{
        Object temp = fim.getElemento();
        if (inicio == null && fim == null) throw new DequeVazioExcecao("Deque Vazio!");
        else{
            fim = fim.getAnterior();
            if(fim != null){
                fim.setProximo(null);
            }
            else inicio = null;
        }
        return temp;
    }

    public static void main(String[] args) {
        DequeDLinkedList deque = new DequeDLinkedList();
        deque.inserirInicio(1);
        deque.inserirInicio(2);
        deque.inserirFim(3);
        deque.inserirFim(4);

        for(No n = deque.inicio; n != null; n = n.getProximo()){
            System.out.print(n.getElemento() + " ");
        }

        System.out.println("\nRemovendo elemento:");
        System.out.println(deque.removerInicio()); // 2
        for(No n = deque.inicio; n != null; n = n.getProximo()){
            System.out.print(n.getElemento() + " ");
        }
        System.out.println("\nRemovendo elemento:");
        System.out.println(deque.removerFim());   // 4
        for(No n = deque.inicio; n != null; n = n.getProximo()){
            System.out.print(n.getElemento() + " ");
        }
        System.out.println("\nRemovendo elemento:");
        System.out.println(deque.removerInicio()); // 1
        for(No n = deque.inicio; n != null; n = n.getProximo()){
            System.out.print(n.getElemento() + " ");
        }
        System.out.println("\nRemovendo elemento:");
        System.out.println(deque.removerFim());   // 3

        deque.inserirInicio(5);
        deque.inserirInicio(10);
        deque.inserirFim(20);

        for(No n = deque.inicio; n != null; n = n.getProximo()){
            System.out.print(n.getElemento() + " ");
        }
        //System.out.println(deque.removerFim());   // Exceção: Deque Vazio!  
    }
}

