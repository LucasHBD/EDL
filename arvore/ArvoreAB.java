package arvore;

import java.util.Arrays;

public class ArvoreAB {
    private No raiz;
    private int tamanho;

    public ArvoreAB(){
        this.raiz = null;
        this.tamanho = 0;
    }

    public int size(){
        return tamanho;
    }

    public No root(){
        return raiz;
    }

    public boolean isExterno(No no){
        return (no.getFilhoDireito() == null && no.getFilhoEsquerdo() == null);
    }

    public boolean isInterno(No no){
        return (no.getFilhoDireito() != null && no.getFilhoEsquerdo() != null);
    }

    public boolean isRoot(No no){
        return no == raiz;
    }

    public No leftChild(No no){
        if(isExterno(no)){
            return null;
        }
        return no.getFilhoEsquerdo();
    }

    public No rightChild(No no){
        if(isExterno(no)){
            return null;
        }
        return no.getFilhoDireito();
    }

    public boolean hasLeft(No no){
        return (no.getFilhoEsquerdo() != null);
    }

    public boolean hasRight(No no){
        return (no.getFilhoDireito() != null);
    }

    public void inOrder(No no){
        if(hasLeft(no)){
            inOrder(leftChild(no));
        }
        System.out.println(no.getElemento());
        if(hasRight(no)){
            inOrder(rightChild(no));
        }
    }

    public int depth(No no){
        if(no == raiz){
            return 0;
        }
        return 1 + depth(no.getPai());
    }

    public int height(No no){
        if(no == null){
            return 0;
        }
        
        int alturaEsquerda = height(leftChild(no));
        int alturaDireita = height(rightChild(no));

        return Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    public No insertRoot(Object elemento) throws noElemen{
        if (raiz != null){
            throw new noElemen("Raiz ja existe!");
        }
        No no = new No(elemento, null);
        raiz = no;
        tamanho++;
        return no;
    }

    public No insertLeft(No pai, Object elemento) throws noElemen{
        if(hasLeft(pai)){
            throw new noElemen("Filho Esquerdo já existe");
        }
        No no = new No(elemento, pai);
        pai.setFilhoEsquerdo(no);
        tamanho++;
        return no;
    }

    public No insertRight(No pai, Object elemento) throws noElemen{
        if(hasRight(pai)){
            throw new noElemen("Filho Direito já existe");
        }
        No no = new No(elemento, pai);
        pai.setFilhoDireito(no);
        tamanho++;
        return no;
    }

    public void remove(No no) throws noElemen{
        if(hasLeft(no) || hasRight(no)){
            throw new noElemen("Nó não pode ser removido pois possui filhos");
        }

        No pai = no.getPai();

        if(pai != null){
            if(pai.getFilhoEsquerdo() == no){
                pai.setFilhoEsquerdo(null);
            } else if(pai.getFilhoDireito() == no){
                pai.setFilhoDireito(null);
            }
        } else {
            raiz = null;
        }
        no.setPai(null);
        tamanho--;
    }

    public void preOrder(No no){
        System.out.println(no.getElemento());
        if(hasLeft(no)){
            preOrder(leftChild(no));
        }
        if(hasRight(no)){
            preOrder(rightChild(no));
        }
    }

    public void postOrder(No no){
        if(hasLeft(no)){
            postOrder(leftChild(no));
        }
        if(hasRight(no)){
            postOrder(rightChild(no));
        }
        System.out.println(no.getElemento());
    }

    public static void printArvore(ArvoreAB arvore) {
        int altura = arvore.height(arvore.root());
        int largura = (int) Math.pow(2, altura) - 1;

        String[][] matriz = new String[altura][largura];

        for (String[] linha : matriz) {
            Arrays.fill(linha, " ");
        }

        preencher(matriz, arvore.root(), 0, 0, largura - 1);

        // Imprimir matriz
        for (String[] linha : matriz) {
            for (String val : linha) {
                System.out.print(val);
            }
            System.out.println();
        }
    }

    private static void preencher(String[][] matriz, No no, int nivel, int inicio, int fim) {
        if (no == null) return;

        int meio = (inicio + fim) / 2;

        matriz[nivel][meio] = no.getElemento().toString();

        preencher(matriz, no.getFilhoEsquerdo(), nivel + 1, inicio, meio - 1);
        preencher(matriz, no.getFilhoDireito(), nivel + 1, meio + 1, fim);
    }


    public static void main(String[] args) throws Exception {
        ArvoreAB arvore = new ArvoreAB();

        // Montando a árvore
        No raiz = arvore.insertRoot("A");

        No b = arvore.insertLeft(raiz, "B");
        No c = arvore.insertRight(raiz, "C");

        No d = arvore.insertLeft(b, "D");
        No e = arvore.insertRight(b, "E");

        No f = arvore.insertLeft(c, "F");
        No g = arvore.insertRight(c, "G");

        No h = arvore.insertLeft(d, "H");

        // Desenhar árvore
        printArvore(arvore);
    }
}
