package heap;

public class heap {
    private No ultimoNo;
    private No raiz;
    private int tamanho;

    public heap(){
        this.raiz = null;
        this.ultimoNo = this.raiz;
        this.tamanho = 0;
    }

    public No root(){
        return this.raiz;
    }

    public boolean isEmpty(){
        return this.tamanho == 0;
    }

    public No pai(No no){
        return no.getPai();
    }

    public No filhoEsquerdo(No no){
        return no.getFilhoEsquerdo();
    }

    public No filhoDireito(No no){
        return no.getFilhoDireito();
    }

    public No irmaoDireito(No no){
        No pai = no.getPai();
        return pai.getFilhoDireito();
    }

    public No irmaoEsquerdo(No no){
        No pai = no.getPai();
        return pai.getFilhoEsquerdo();
    }

    public No ultimoNo(){
        return this.ultimoNo;
    }

    public void removerUltimo(){
        if (isEmpty())
            return;
        
        No removerUltimoNo = ultimoNo;
        No novoUltimoNo = buscarProximoNo(ultimoNo);
        No pai = pai(removerUltimoNo);

        if(pai.getFilhoEsquerdo() == removerUltimoNo){
            pai.setFilhoEsquerdo(null);
        } else {
            pai.setFilhoDireito(null);
        }

        ultimoNo = novoUltimoNo;

        tamanho--;
    }

    public void removerRaiz(){
        if(isEmpty()){
            return;
        }

        if(tamanho == i){
            raiz = null;
            ultimoNo = null;
            tamanho = 0;
            return;
        }

        raiz.setElemento(ultimoNo.getElemento());
        raiz.setKey(ultimoNo.getKey());
        removerUltimo();
        heapDown(raiz);
    }

    private void heapDown(No no){
        if(no == null)
            return;
        No menor = no;

        if(no.getFilhoEsquerdo() != null && no.getFilhoEsquerdo().getKey() < menor.getKey()){
            menor = no.getFilhoEsquerdo();
        }

        if(no.getFilhoDireito() != null && no.getFilhoDireito().getKey() < menor.getKey()){
            menor.getFilhoDireito();
        }

        if(menor != no){
            swap(no, menor);
            heapDown(menor);
        }
    }

    public void insert(int key, Object elemento){
        No novoNo = new No(elemento, key, null);

        if(isEmpty()){
            raiz = novoNo;
            ultimoNo = raiz;
            tamanho++;
            return;
        }

        No ultimo = ultimoNo(this.ultimoNo);

        if(filhoEsquerdo(ultimo) == null){
            novoNo.setPai(ultimo);
            ultimo.setFilhoEsquerdo(novoNo);
        } else {
            novoNo.setPai(ultimo);
            ultimo.setFilhoDireito(novoNo);
        }

        tamanho++;
        this.ultimoNo = novoNo;
        heapUp(ultimoNo);
    }

    private No ultimoNo(No no){
        No pai = pai(no);
        No ultimo;

        while(pai != null && filhoDireito(pai) == no){
            no = pai;
            pai = pai(no);
        }

        if(pai == null){
            ultimo = descer(raiz);
        } else{
            No irmao = irmaoDireito(no);
            if(irmao == null){
                ultimo = pai;
            } else {
                ultimo = descer(irmao);
            }
        }
        return ultimo;
    }

    private No descer(No no){
        while(no.getFilhoEsquerdo() != null){
            no = no.getFilhoEsquerdo();
        }
        return no;
    }

    private void heapUp(No no){
        if(no == raiz){
            return;
        }
        No pai = pai(no);

        if(pai.getKey() > no.getKey()){
            swap(no, pai);
            heapUp(pai);
        }
    }

    private void swap(No no1, No no2){
        Object tempElemento = no1.getElemento();
        int tempKey = no1.getKey();

        no1.setElemento(no2.getElemento());
        no1.setKey(no2.getKey());

        no2.setElemento(tempElemento);
        no2.setKey(tempKey);
    }

    public No buscarProximoNo(No no){
        No pai = pai(no);

        while(pai != null && filhoEsquerdo(pai) == no){
            no = pai;
            pai = pai(pai);
        }

        if(pai == null){
            return descerDireito(raiz);
        } else{
            No irmao = irmaoEsquerdo(no);
            if(irmao == null){
                return pai;
            } else{
                return descerDireito(irmao);
            }
        }
    }

    private No descerDireito(No no){
        if(no.getFilhoDireito() == null){
            return no;
        }

        return descerDireito(no.getFilhoDireito());
    }
}
