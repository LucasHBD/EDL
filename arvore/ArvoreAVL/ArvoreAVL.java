package arvore.ArvoreAVL;

import java.util.ArrayList;

public class ArvoreAVL {
    private No raiz;
    private Integer tamanho;
    private No desbalanceado;

    private ArrayList<No> nos;

    public ArvoreAVL(){
        this.raiz = new No();
        this.tamanho = 0;
        this.desbalanceado = new No();
        this.desbalanceado.setBalanceamento(0);
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
        novo_pai.setPai(node.getPai());
        if(novo_pai.getFilhoEsquerdo() == null){
            novo_pai.setFilhoEsquerdo(node);
        } else{
            node.setFilhoDireito(novo_pai.getFilhoEsquerdo());
            novo_pai.getFilhoEsquerdo().setPai(node);
            novo_pai.setFilhoEsquerdo(node);
        }
        node.setPai(novo_pai);
        if(node == raiz) raiz = novo_pai;
        Integer novo_fb = node.getBalanceamento() + 1 - (Math.min(novo_pai.getBalanceamento(), 0));
        node.setBalanceamento(novo_fb);
        novo_fb = novo_pai.getBalanceamento() + 1 + (Math.max(node.getBalanceamento(), 0));
        novo_pai.setBalanceamento(novo_fb);
    }

    private void rotacaoDireita(No node){
        No novo_pai = node.getFilhoEsquerdo();
        novo_pai.setPai(node.getPai());
        if(novo_pai.getFilhoDireito() == null){
            novo_pai.setFilhoDireito(node);
        } else{
            node.setFilhoEsquerdo(novo_pai.getFilhoDireito());
            novo_pai.getFilhoDireito().setPai(node);
            novo_pai.setFilhoDireito(node);
        }
        node.setPai(novo_pai);
        if(node == raiz) raiz = novo_pai;
        Integer novo_fb = node.getBalanceamento() - 1 - (Math.max(novo_pai.getBalanceamento(), 0));
        node.setBalanceamento(novo_fb);
        novo_fb = novo_pai.getBalanceamento() - 1 + (Math.min(node.getBalanceamento(), 0));
        novo_pai.setBalanceamento(novo_fb);
    }

    public Boolean isEmpty(){
        if(tamanho == 0) return true;
        return false;
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

    public Boolean umFilhoEsquerdo(No node){
        if(node.getPai().getFilhoEsquerdo() == node) return true;
        return false;
    }

    public Boolean umFilhoDireito(No node){
        if(node.getPai().getFilhoDireito() == node) return true;
        return false;
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
        if(desbalanceado.getBalanceamento() != null){
            if(desbalanceado.getBalanceamento() == 2){
                if(desbalanceado.getFilhoEsquerdo().getBalanceamento() == -1){
                    rotacaoEsquerda(desbalanceado.getFilhoEsquerdo());
                }
                rotacaoDireita(desbalanceado);
            }
            else{
                if(desbalanceado.getFilhoDireito().getBalanceamento() == 1){
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

        else{
            No node_pai = node.getPai();
            No node_sub = node;
            if(!temFilhoEsquerdo(node_sub)){
                node_sub = node.getFilhoDireito();
            }
            else{
                noSub(node.getFilhoDireito(), node_sub);
                if(temFilhoDireito(node_sub)){
                    node_sub.getPai().setFilhoEsquerdo(node_sub.getFilhoDireito());
                    node.getFilhoDireito().setPai(node_sub.getPai());
                }
                node_sub.setFilhoDireito(node.getFilhoDireito());
                node.getFilhoDireito().setPai(node_sub);
            }
            node_sub.setFilhoEsquerdo(node.getFilhoEsquerdo());
            node.getFilhoEsquerdo().setPai(node_sub);
            if(node != raiz){
                if(umFilhoDireito(node)){
                    node_pai.setFilhoDireito(node_sub);
                }
                else {
                    node_pai.setFilhoEsquerdo(node_sub);
                }
            }
            else {
                raiz = node_sub;
            }
            if(node != node_sub.getPai()){
                calcularFbRemocao(node_sub);
            }
            node_sub.setPai(node_pai);
            calcularFbRemocao(node_sub.getFilhoEsquerdo());
            tamanho--;
        }
        if(desbalanceado.getBalanceamento() != null && desbalanceado != node){
            if(desbalanceado.getBalanceamento() == 2){
                if(desbalanceado.getFilhoEsquerdo().getBalanceamento() == -1){
                    rotacaoEsquerda(desbalanceado.getFilhoEsquerdo());
                }
                rotacaoDireita(desbalanceado);
            }
            else{
                if(desbalanceado.getFilhoDireito().getBalanceamento() == 1){
                    rotacaoDireita(desbalanceado.getFilhoDireito());
                }
                rotacaoEsquerda(desbalanceado);
            }
        }
    }

    private void noSub(No node, No node_sub){
        if(noInterno(node) && node.getFilhoEsquerdo() != null){
            noSub(node.getFilhoEsquerdo(), node_sub);
            return;
        }
        if((umFilhoEsquerdo(node) && noExterno(node)) || (umFilhoEsquerdo(node) && temFilhoDireito(node) && !temFilhoEsquerdo(node))){
            node_sub = node;
            return;
        }
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
        Integer[][] matriz = new Integer[altura+1][size()];
        ArrayList<No> nodes = inOrderNosArray();
        Integer k = 0;
        for(No node : nodes){
            matriz[profundidade(node)][k] = node.getElemento();
            k++;  
        }
        for(int i = 0; i <= altura; i++){
            for(int j = 0; j < size(); j++){
                Integer print;
                if(matriz[i][j] == null)
                    System.out.print(" ");
                
                else
                    System.out.print(matriz[i][j]);
                
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
