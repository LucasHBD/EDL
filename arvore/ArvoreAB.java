package arvore;

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

    public void insertRoot(Object elemento) throws noElemen{
        if (raiz != null){
            throw new noElemen("Raiz ja existe!");
        }
        No no = new No(elemento, null);
        raiz = no;
        tamanho++;
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


    public static void main(String[] args) {

        try {
            ArvoreAB arvore = new ArvoreAB();

            // Inserindo a raiz
            arvore.insertRoot("A");

            // Referência para a raiz
            No raiz = arvore.root();

            // Inserindo filhos da raiz
            No b = arvore.insertLeft(raiz, "B");
            No c = arvore.insertRight(raiz, "C");

            // Inserindo filhos de B
            No d = arvore.insertLeft(b, "D");
            No e = arvore.insertRight(b, "E");

            // Inserindo filhos de C
            No f = arvore.insertLeft(c, "F");
            No g = arvore.insertRight(c, "G");

            // Percursos
            System.out.println("Pré-Ordem:");
            arvore.preOrder(raiz);

            System.out.println("\nEm-Ordem:");
            arvore.inOrder(raiz);

            System.out.println("\nPós-Ordem:");
            arvore.postOrder(raiz);

            // Testes adicionais
            System.out.println("\nTamanho da árvore: " + arvore.size());
            System.out.println("Altura da árvore: " + arvore.height(raiz));
            System.out.println("Profundidade do nó E: " + arvore.depth(e));

            // Removendo um nó folha
            System.out.println("\nRemovendo o nó G...");
            arvore.remove(g);

            System.out.println("\nEm-Ordem após remoção:");
            arvore.inOrder(raiz);

            System.out.println("\nNovo tamanho da árvore: " + arvore.size());

        } catch (noElemen e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
