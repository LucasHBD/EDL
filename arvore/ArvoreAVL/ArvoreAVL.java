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
        if(umFilhoDireita(node)){
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
}
