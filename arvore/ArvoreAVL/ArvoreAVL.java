package arvore.ArvoreAVL;

import java.util.ArrayList;
import java.util.Scanner;

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

    //private Integer fatorBalanceamento(No node){
       // No pai = node.getPai();
      //  Integer fb = altura(pai.getFilhoEsquerdo()) - altura(pai.getFilhoDireito());
      //  return fb;
   // }

    // private void CalcularFbInsercao(No node){
    //     No aux = node.getPai();
    //     if(umFilhoDireito(node)){
    //         while(aux != null){
    //             aux.setBalanceamento(aux.getBalanceamento());
    //             if(aux.getBalanceamento() == -2 || aux.getBalanceamento() == 2){
    //                 desbalanceado = aux;
    //                 break;
    //             }
    //             if(aux.getBalanceamento() == 0){
    //                 break;
    //             }
    //             aux = aux.getPai();
    //         }
    //     } else {
    //         while(aux != null){
    //             aux.setBalanceamento(aux.getBalanceamento());
    //             if(aux.getBalanceamento() == -2 || aux.getBalanceamento() == 2){
    //                 desbalanceado = aux;
    //                 break;
    //             }
    //             if(aux.getBalanceamento() == 0){
    //                 break;
    //             }
    //             aux = aux.getPai();
    //         }
    //     }
    // }
    
    private void calcularFbRemocao(No node){
        No aux = node.getPai();
        if(umFilhoDireito(node)){
            while(aux != null){
                aux.setBalanceamento(aux.getBalanceamento() + 1);
                if(aux.getBalanceamento() == -2 || aux.getBalanceamento() == 2){
                    desbalanceado = aux;
                    break;
                }
                if(aux.getBalanceamento() == 0){
                    node = aux;
                    aux = aux.getPai();
                }
                else {
                    break;
                }
            }
        } else {
            while(aux != null){
                aux.setBalanceamento(aux.getBalanceamento() - 1);
                if(aux.getBalanceamento() == -2 || aux.getBalanceamento() == 2){
                    desbalanceado = aux;
                    break;
                }
                if(aux.getBalanceamento() == 0){
                    node = aux;
                    aux = aux.getPai();
                }
                else {
                    break;
                }
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

        atualizarFbRotacaoEsquerda(node, novo_pai);
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

        atualizarFbRotacaoDireita(node, novo_pai);
    }

    private void atualizarFbRotacaoEsquerda(No node, No novo_pai){
        if(novo_pai.getBalanceamento() == -1){
            node.setBalanceamento(0);
            novo_pai.setBalanceamento(0);
        } else if(novo_pai.getBalanceamento() == 0){
            node.setBalanceamento(1);
            novo_pai.setBalanceamento(-1);
        }
    }

    private void atualizarFbRotacaoDireita(No node, No novo_pai){
        if(novo_pai.getBalanceamento() == 1){
            node.setBalanceamento(0);
            novo_pai.setBalanceamento(0);
        } else if(novo_pai.getBalanceamento() == 0){
            node.setBalanceamento(-1);
            novo_pai.setBalanceamento(1);
        }
    }

    private void rebalancear(){
        if(desbalanceado == null){
            return;
        }

        if(desbalanceado.getBalanceamento() == 2){
            No filhoEsq = desbalanceado.getFilhoEsquerdo();
            if(filhoEsq != null && filhoEsq.getBalanceamento() == -1){
                Integer fbNeto = (filhoEsq.getFilhoDireito() != null) ? filhoEsq.getFilhoDireito().getBalanceamento() : 0;
                rotacaoEsquerda(filhoEsq);
                rotacaoDireita(desbalanceado);
                ajustarFbRotacaoDupla(desbalanceado.getPai(), fbNeto, true);
            } else {
                rotacaoDireita(desbalanceado);
            }
        } else if(desbalanceado.getBalanceamento() == -2){
            No filhoDir = desbalanceado.getFilhoDireito();
            if(filhoDir == null){
                return;
            }
            if(filhoDir != null && filhoDir.getBalanceamento() == 1){
                Integer fbNeto = (filhoDir.getFilhoEsquerdo() != null) ? filhoDir.getFilhoEsquerdo().getBalanceamento() : 0;
                rotacaoDireita(filhoDir);
                rotacaoEsquerda(desbalanceado);
                ajustarFbRotacaoDupla(desbalanceado.getPai(), fbNeto, false);
            } else {
                rotacaoEsquerda(desbalanceado);
            }
        }
        desbalanceado = null;
    }

    private void ajustarFbRotacaoDupla(No novoTopo, Integer fbNeto, Boolean casoPai_dupla){
        if(novoTopo == null) return;
        No esq = novoTopo.getFilhoEsquerdo();
        No dir = novoTopo.getFilhoDireito();
        novoTopo.setBalanceamento(0);

        if(casoPai_dupla){
            if(fbNeto == 1){
                if(esq != null) esq.setBalanceamento(0);
                if(dir != null) dir.setBalanceamento(-1);
            } else if(fbNeto == -1){
                if(esq != null) esq.setBalanceamento(1);
                if(dir != null) dir.setBalanceamento(0);
            } else {
                if(esq != null) esq.setBalanceamento(0);
                if(dir != null) dir.setBalanceamento(0);
            }
        } else {
            if(fbNeto == -1){
                if(esq != null) esq.setBalanceamento(1);
                if(dir != null) dir.setBalanceamento(0);
            } else if(fbNeto == 1){
                if(esq != null) esq.setBalanceamento(0);
                if(dir != null) dir.setBalanceamento(-1);
            } else {
                if(esq != null) esq.setBalanceamento(0);
                if(dir != null) dir.setBalanceamento(0);
            }
        }
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
        //if(noExterno(node)) return 0;1
        if(node == null){
            return 0;
        }
        
        int alturaEsquerda = altura(filhoEsquerdo(node));
        int alturaDireita = altura(filhoDireito(node));

        return Math.max(alturaEsquerda, alturaDireita) + 1;
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
                else if(node.getElemento() < aux.getElemento()){
                    if(temFilhoEsquerdo(aux)){
                        aux = aux.getFilhoEsquerdo();
                    }
                    else {
                        aux.setFilhoEsquerdo(node);
                        node.setPai(aux);
                    }
                } else {
                    return;
                }
            }
            tamanho++;
            No pai = node.getPai();
            while(pai != null){
                if(umFilhoDireito(node)){     
                    pai.setBalanceamento(pai.getBalanceamento() - 1);
                    if(pai.getBalanceamento() == -2 || pai.getBalanceamento() == 2){
                        desbalanceado = pai;
                        break;
                    }
                    if(pai.getBalanceamento() == 0){
                        break;
                    }
                    pai = pai.getPai();
                } else {
                    pai.setBalanceamento(pai.getBalanceamento() + 1);
                    if(pai.getBalanceamento() == -2 || pai.getBalanceamento() == 2){
                        desbalanceado = pai;
                        break;
                    }
                    if(pai.getBalanceamento() == 0){
                        break;
                    }
                    pai = pai.getPai();
                    }
                }
            }
        rebalancear();
    }

    public void removerNo(No node){
        desbalanceado = null;
        removerNoInterno(node);
        rebalancear();
    }

    public void removerNoInterno(No node){
        if(noExterno(node)){
            if(node != raiz){
                No nodePai = node.getPai();
                if(umFilhoEsquerdo(node)){
                    nodePai.setFilhoEsquerdo(null);
                } else {
                    nodePai.setFilhoDireito(null);
                }
                calcularFbRemocao(node);
                node.setPai(null);
            }
            else{
                raiz = null;
            }
            node = null;
            tamanho--;
        } else if(node.oneChild()){
            No node_pai = node.getPai();
            No nodeFilho = temFilhoEsquerdo(node) ? node.getFilhoEsquerdo() : node.getFilhoDireito();

            if(node_pai != null){
                if(umFilhoEsquerdo(node)){
                    node_pai.setFilhoEsquerdo(nodeFilho);
                } else {
                    node_pai.setFilhoDireito(nodeFilho);
                    }
                nodeFilho.setPai(node_pai);
                calcularFbRemocao(nodeFilho);
                } else {
                    nodeFilho.setPai(null);
                    raiz = nodeFilho;
                    calcularFbRemocao(raiz);
                }
                node.setFilhoEsquerdo(null);
                node.setFilhoDireito(null);
                node.setPai(null);
                tamanho --;
            } else {
                No sucessor = menorNo(node.getFilhoDireito());
                node.setElemento(sucessor.getElemento());
                removerNoInterno(sucessor);
            }
        }
    
    private No menorNo(No node){
        if(node.getFilhoEsquerdo() != null){
            return menorNo(node.getFilhoEsquerdo());
        }
        return node;
    }

    public void desenharArvore(){
        if(isEmpty()){
            System.out.println("Árvore vazia.");
            return;
        }
 
        ArrayList<No> inOrder = new ArrayList<>();
        inOrderNos(raiz, inOrder);
 
        int n = inOrder.size();
        int altura = altura(raiz);
 
        int[] posInOrder = new int[n];
        for(int i = 0; i < n; i++){
            posInOrder[i] = i;
        }
 
        int larguraCelula = 12;
        int larguraTotal = n * larguraCelula;
 
        int[] posX = new int[n];
        for(int i = 0; i < n; i++){
            posX[i] = i * larguraCelula + larguraCelula / 2;
        }
 
        StringBuilder[] linhas = new StringBuilder[altura + 1];
        for(int i = 0; i <= altura; i++){
            linhas[i] = new StringBuilder();
            for(int j = 0; j < larguraTotal; j++) linhas[i].append(' ');
        }
 
        for(int i = 0; i < n; i++){
            No node = inOrder.get(i);
            int prof = profundidade(node);
            int col  = posX[i];
            String label = node.toString();
            for(int c = 0; c < label.length() && col + c < larguraTotal; c++){
                linhas[prof].setCharAt(col + c, label.charAt(c));
            }
        }
 
        System.out.println();
        for(int i = 0; i <= altura; i++){
            System.out.println(linhas[i].toString().stripTrailing());
        }
        System.out.println();
    }
 
    private void inOrderNos(No node, ArrayList<No> lista){
        if(node == null) return;
        if(noInterno(node) && node.getFilhoEsquerdo() != null){
            inOrderNos(node.getFilhoEsquerdo(), lista);
        }
        lista.add(node);
        if(noInterno(node) && node.getFilhoDireito() != null){
            inOrderNos(node.getFilhoDireito(), lista);
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
        Scanner sc = new Scanner(System.in);

        int opcao;

        do {
            System.out.println("\n===== MENU AVL =====");
            System.out.println("1 - Inserir nó");
            System.out.println("2 - Remover nó");
            System.out.println("3 - Buscar nó");
            System.out.println("4 - Mostrar árvore");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    System.out.print("Valor para inserir: ");
                    int valorInserir = sc.nextInt();

                    No novo = new No(valorInserir);
                    arvore.inserirNo(novo);

                    System.out.println("Inserido!");
                    break;

                case 2:
                    System.out.print("Valor para remover: ");
                    int valorRemover = sc.nextInt();

                    No noRemover = buscar(arvore.root(), valorRemover);

                    if (noRemover != null) {
                        arvore.removerNo(noRemover);
                        System.out.println("Removido!");
                    } else {
                        System.out.println("Valor não encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Valor para buscar: ");
                    int valorBuscar = sc.nextInt();

                    No resultado = buscar(arvore.root(), valorBuscar);

                    if (resultado != null) {
                        System.out.println("Nó encontrado!");
                    } else {
                        System.out.println("Nó não encontrado.");
                    }
                    break;

                case 4:
                    if (!arvore.isEmpty()) {
                        arvore.desenharArvore();
                    } else {
                        System.out.println("Árvore vazia.");
                    }
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}
