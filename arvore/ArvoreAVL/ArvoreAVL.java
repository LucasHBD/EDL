package arvore.ArvoreAVL;

import java.util.ArrayList;

public class ArvoreAVL {
    private No raiz;
    private Integer tamanho;
    private No desbalanceado;

    private ArrayList<No> nos;

    public ArvoreAVL(){
        this.raiz = null;
        this.tamanho = 0;
        this.desbalanceado = null;
    }

    public Integer size(){
        return tamanho;
    }

    public No root(){
        return raiz;
    }

    private void CalcularFbInsercao(No node){
        No aux = node.getPai();
        if(umFilhoDireito(node)){
            while(aux != null){
                Integer fb = aux.getBalanceamento() - 1;
                aux.setBalanceamento(fb);
                if(aux.getBalanceamento() == -2 || aux.getBalanceamento() == 2){
                    desbalanceado = aux;
                }
                if(aux.getBalanceamento() == 0){
                    break;
                }
                aux = aux.getPai();
            }
        } else {
            while(aux != null){
                Integer fb = aux.getBalanceamento() + 1;
                aux.setBalanceamento(fb);
                if(aux.getBalanceamento() == -2 || aux.getBalanceamento() == 2){
                    desbalanceado = aux;
                }
                if(aux.getBalanceamento() == 0){
                    break;
                }
                aux = aux.getPai();
            }
        }
    }
    
    private void calcularFbRemocao(No node){
        No aux = node.getPai();
        if(umFilhoDireito(node)){
            while(aux != null){
                Integer fb = aux.getBalanceamento() + 1;
                aux.setBalanceamento(fb);
                if(aux.getBalanceamento() == -2 || aux.getBalanceamento() == 2){
                    desbalanceado = aux;
                }
                if(aux.getBalanceamento() != 0){
                    break;
                }
                aux = aux.getPai();
            }
        } else {
            while(aux != null){
                Integer fb = aux.getBalanceamento() - 1;
                aux.setBalanceamento(fb);
                if(aux.getBalanceamento() == -2 || aux.getBalanceamento() == 2){
                    desbalanceado = aux;
                }
                if(aux.getBalanceamento() != 0){
                    break;
                }
                aux = aux.getPai();
            }
        }
    }

    private void rotacaoEsquerda(No node){
        No novo_pai = node.getFilhoDireito();
        node.setFilhoDireito(novo_pai.getFilhoEsquerdo());
        if(novo_pai.getFilhoEsquerdo() != null){
            novo_pai.getFilhoEsquerdo().setPai(node);
        }
        novo_pai.setPai(node.getPai());
        if(node.getPai() == null){
            raiz = novo_pai;
        } else if(node == node.getPai().getFilhoEsquerdo()){
            node.getPai().setFilhoEsquerdo(novo_pai);
        } else {
            node.getPai().setFilhoDireito(novo_pai);
        }

        novo_pai.setFilhoEsquerdo(node);
        node.setPai(novo_pai);
    }

    private void rotacaoDireita(No node){
        No novo_pai = node.getFilhoEsquerdo();

        node.setFilhoEsquerdo(novo_pai.getFilhoDireito());
        if(novo_pai.getFilhoDireito() != null){
            novo_pai.getFilhoDireito().setPai(node);
        }

        novo_pai.setPai(node.getPai());

        if(node.getPai() == null){
            raiz = novo_pai;
        } else if(node == node.getPai().getFilhoEsquerdo()){
            node.getPai().setFilhoEsquerdo(novo_pai);
        } else {
            node.getPai().setFilhoDireito(novo_pai);
        }

        novo_pai.setFilhoDireito(node);
        node.setPai(novo_pai);
    }

    public Boolean isEmpty(){
        return raiz == null;
    }

    public No pai(No node){
        return node.getPai();
    }

    public No filhoEsquerdo(No node){
        return node.getFilhoEsquerdo();
    }

    public No filhoDireito(No node){
        return node.getFilhoDireito();
    }

    public Boolean umFilhoDireito(No node){
        if(node.getPai() == null) return false;
        return node.getPai().getFilhoDireito() == node;
    }

    public Boolean umFilhoEsquerdo(No node){
        if(node.getPai() == null) return false;
        return node.getPai().getFilhoEsquerdo() == node;
    }

    public Boolean temFilhoDireito(No node){
        if(node.getFilhoDireito() != null) return true;
        return false;
    }

    public Boolean temFilhoEsquerdo(No node){
        if(node.getFilhoEsquerdo() != null) return true;
        return false;
    }

    public Boolean noExterno(No node){
        if(node.getFilhoEsquerdo() == null && node.getFilhoDireito() == null) return true;
        return false;
    }

    public Boolean noInterno(No node){
        if(!noExterno(node)) return true;
        return false;
    }

    public Boolean noRaiz(No node){
        if(node == raiz) return true;
        return false;
    }

    public Integer altura(No node){
        if(noExterno(node)) return 0;
        else{
            Integer alturaEsquerda = 0;
            Integer alturaDireita = 0;
            Integer altura = 0;
            if(node.getFilhoEsquerdo() != null) alturaEsquerda = altura(node.getFilhoEsquerdo()) + 1;
            if(node.getFilhoDireito() != null) alturaDireita = altura(node.getFilhoDireito()) + 1;
            altura = alturaEsquerda;
            if(alturaDireita > alturaEsquerda){
                altura = alturaDireita;
            }
            return altura;
        }
    }

    public Integer profundidade(No node){
        if(noRaiz(node)) return 0;
        else return profundidade(node.getPai()) + 1;
    }

    public void inserirNo(No node){
        desbalanceado = null;
        No aux = raiz;
        if(isEmpty()){
            raiz = node;
            tamanho++;
        } else{
            while(node.getPai() == null){
                if(node.getElemento() > aux.getElemento()){
                    if(temFilhoDireito(aux)){
                        aux = aux.getFilhoDireito();
                    }
                    else {
                        aux.setFilhoDireito(node);
                        node.setPai(aux);
                    }
                }
                if(node.getElemento() < aux.getElemento()){
                    if(temFilhoEsquerdo(aux)){
                        aux = aux.getFilhoEsquerdo();
                    }
                    else {
                        aux.setFilhoEsquerdo(node);
                        node.setPai(aux);
                    }
                }
            }
            tamanho++;
            CalcularFbInsercao(node);
        }
        if(desbalanceado != null){
            if(desbalanceado.getBalanceamento() == 2){
                if(desbalanceado.getFilhoEsquerdo() != null &&
                desbalanceado.getFilhoEsquerdo().getBalanceamento() == -1){
                    rotacaoEsquerda(desbalanceado.getFilhoEsquerdo());
                }
                rotacaoDireita(desbalanceado);
            }
            else if(desbalanceado.getBalanceamento() == -2){
                if(desbalanceado.getFilhoDireito() != null &&
                desbalanceado.getFilhoDireito().getBalanceamento() == 1){
                    rotacaoDireita(desbalanceado.getFilhoDireito());
                }
                rotacaoEsquerda(desbalanceado);
            }
        }
    }

    public void removerNo(No node){
        if(noExterno(node)){
            if(node != raiz){
                if(umFilhoEsquerdo(node)){
                    node.getPai().setFilhoEsquerdo(null);
                    calcularFbRemocao(node);
                } else if(umFilhoDireito(node)){
                    node.getPai().setFilhoDireito(null);
                    calcularFbRemocao(node);
                }
            }
            else{
                raiz = null;
            }
            node = null;
            tamanho--;
        }
        else if(node.oneChild()){
            No node_pai = node.getPai();
            if(node_pai != null){
                No node_filho;
                if(umFilhoEsquerdo(node)){
                    if(temFilhoEsquerdo(node)){
                        node_filho = node.getFilhoEsquerdo();
                    }
                    else {
                        node_filho = node.getFilhoDireito();
                    }
                    node_pai.setFilhoEsquerdo(node_filho);
                    node_filho.setPai(node_pai);
                }
                else {
                    if(temFilhoEsquerdo(node)){
                        node_filho = node.getFilhoEsquerdo();
                    }
                    else{
                        node_filho = node.getFilhoDireito();
                    }
                    node_pai.setFilhoDireito(node_filho);
                    node_filho.setPai(node_pai);
                }
                calcularFbRemocao(node_filho);
            }
            else {
                if(temFilhoDireito(node)){
                    node.getFilhoDireito().setPai(null);
                    raiz = node.getFilhoDireito();
                    node.setFilhoDireito(null);
                    node = null;
                }
                else {
                    node.getFilhoEsquerdo().setPai(null);
                    raiz = node.getFilhoEsquerdo();
                    node.setFilhoEsquerdo(null);
                    node = null;
                }
                calcularFbRemocao(raiz);
            }
            tamanho--;
        }

        else {
            No sucessor = noSub(node.getFilhoDireito());
            node.setElemento(sucessor.getElemento());
            removerNo(sucessor);
        }
        if(desbalanceado != null && desbalanceado != node){

            if(desbalanceado.getBalanceamento() == 2){

                if(desbalanceado.getFilhoEsquerdo() != null &&
                desbalanceado.getFilhoEsquerdo().getBalanceamento() == -1){
                    rotacaoEsquerda(desbalanceado.getFilhoEsquerdo());
                }

                rotacaoDireita(desbalanceado);

            } else if(desbalanceado.getBalanceamento() == -2){

                if(desbalanceado.getFilhoDireito() != null &&
                desbalanceado.getFilhoDireito().getBalanceamento() == 1){
                    rotacaoDireita(desbalanceado.getFilhoDireito());
                }

                if(desbalanceado.getFilhoDireito() != null){
                    rotacaoEsquerda(desbalanceado);
                }
            }
        }
    }

    private No noSub(No node){
        if(node.getFilhoEsquerdo() != null){
            return noSub(node.getFilhoEsquerdo());
        }
        return node;
    }

    private void inOrderNos(No node){
        if(noInterno(node) && node.getFilhoEsquerdo() != null){
            inOrderNos(node.getFilhoEsquerdo());
        }
        nos.add(node);
        if(noInterno(node) && node.getFilhoDireito() != null){
            inOrderNos(node.getFilhoDireito());
        }
    }

    private ArrayList<No> inOrderNosArray(){
        nos = new ArrayList<No>();
        inOrderNos(raiz);
        return nos;
    }

    public void desenharArvore(){
        Integer altura = altura(raiz);

        ArrayList<No> nodes = inOrderNosArray(); // primeiro pega os nós

        Integer[][] matriz = new Integer[altura+1][nodes.size()]; // usa nodes.size()

        Integer k = 0;

        for(No node : nodes){
            matriz[profundidade(node)][k] = node.getElemento();
            k++;  
        }

        for(int i = 0; i <= altura; i++){
            for(int j = 0; j < nodes.size(); j++){
                if(matriz[i][j] == null)
                    System.out.print(" ");
                else
                    System.out.print(matriz[i][j]);

                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static No buscar(No raiz, int valor) {
        if (raiz == null || raiz.getElemento() == null) return null;

        if (raiz.getElemento() == valor) return raiz;

        if (valor < raiz.getElemento())
            return buscar(raiz.getFilhoEsquerdo(), valor);
        else
            return buscar(raiz.getFilhoDireito(), valor);
    }
    public static void main(String[] args) {

        ArvoreAVL arvore = new ArvoreAVL();

        int[] valores = {10, 5, 15, 2, 8, 22};

        System.out.println("=== INSERÇÕES INICIAIS ===");

        for (int v : valores) {
            System.out.println("\nInserindo: " + v);

            No novo = new No(v, null); // <-- ajuste aqui
            arvore.inserirNo(novo);

            arvore.desenharArvore();
        }

        System.out.println("\n=== INSERINDO 25 ===");
        No n25 = new No(25, null);
        arvore.inserirNo(n25);

        arvore.desenharArvore();

        System.out.println("\n=== REMOVENDO 5 ===");

        No noRemover = buscar(arvore.root(), 5);

        if (noRemover != null) {
            arvore.removerNo(noRemover);
            arvore.desenharArvore();
        } else {
            System.out.println("Nó 5 não encontrado!");
        }
    }
}
