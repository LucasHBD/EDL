package lista;

public class ListaLigada {
    private No inicio;
    private No fim;
    private int capacidade;

    public ListaLigada(){
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
        if(isEmpty()){
            inicio.setProximo(novo_no);
            fim.setAnterior(novo_no);;
        }
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

    public void print(){
        if(isEmpty()){
            System.out.println("Lista Vazia");
        }
        else{
            System.out.print("[");

            for(No n = inicio.getProximo(); n != fim; n = n.getProximo()){
                System.out.print(n.getElemento() + " ");
            }
            System.out.print("]\n");
        }
    }
    public static void main(String[] args) {
        ListaLigada lista = new ListaLigada();

        System.out.println("Lista vazia? " + lista.isEmpty());

        No n1 = lista.insertLast("A");
        No n2 = lista.insertLast("B");
        No n3 = lista.insertLast("C");

        System.out.println("Tamanho: " + lista.size());
        lista.print();

        lista.insertAfter(n2, "X");
        System.out.println("\nApós inserir 'X' depois de 'B':");
        lista.print();

        lista.swapElement(n1, n3);
        System.out.println("\nApós trocar 'A' e 'C':");
        lista.print();

        lista.remove(n2);
        System.out.println("\nApós remover 'B':");
        lista.print();

        lista.insertFirst("Z");
        System.out.println("\nApós inserir 'Z' no início:");
        lista.print();

        System.out.println("\nTamanho final: " + lista.size());
    }

}
