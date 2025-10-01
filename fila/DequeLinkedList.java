public class DequeLinkedList {
    private No inicio;
    private No fim;

    public class No{
        private Object elemento;
        private No proximo;

        public No(){
            this.elemento = null;
            this.proximo = null;
        }

        public Object getElemento() {
            return elemento;
        }

        public void setElemento(Object elemento){
            this.elemento = elemento;
        }

        public No getProximo() {
            return proximo;
        }

        public void setProximo(No proximo) {
            this.proximo = proximo;
        }
    }

    public DequeLinkedList(){
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
            fim = novo;
        }
    }

    public Object removerInicio() throws DequeVazioExcecao{
        if(inicio == null && fim == null) throw new DequeVazioExcecao("Deque Vazio!");
        Object temp = inicio.getElemento();
        inicio = inicio.getProximo();
        return temp;
    }

    public Object removerFim() throws DequeVazioExcecao{
        if(inicio == null && fim == null) throw new DequeVazioExcecao("Deque Vazio!");
        Object temp = fim.getElemento();
        No n;
        for(n = inicio; n.getProximo() != fim; n = n.getProximo());
        fim = n;
        fim.setProximo(null);
        return temp;
    }

    public static void main(String[] args){
        DequeLinkedList deque = new DequeLinkedList();
        deque.inserirInicio(1);
        deque.inserirInicio(2);
        deque.inserirInicio(3);
        deque.inserirInicio(4);
        deque.inserirFim(10);
        deque.inserirFim(20);
        deque.inserirFim(30);

        for(No n = deque.inicio; n != null; n = n.getProximo()){
            System.out.print(n.getElemento() + " ");
        }
        System.out.println("\nRemovendo elemento:");
        System.out.println(deque.removerInicio());
        System.out.println("Removendo elemento:");
        System.out.println(deque.removerFim());

        for(No n = deque.inicio; n != null; n = n.getProximo()){
            System.out.print(n.getElemento() + " ");
        }
    }
}
