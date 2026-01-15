package arvore;

public class No {
    private Object elemento;
    private No filhoEsquerdo, filhoDireito, pai;

    public No(Object elemento, No pai){
        this.elemento = elemento;
        this.pai = pai;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
    }

    public Object getElemento(){
        return elemento;
    }

    public void setElemento(Object elemento){
        this.elemento = elemento;
    }

    public No getFilhoEsquerdo(){
        return filhoEsquerdo;
    }

    public void setFilhoEsquerdo(No filhoEsquerdo){
        this.filhoEsquerdo = filhoEsquerdo;
    }

    public No getFilhoDireito(){
        return filhoDireito;
    }

    public void setFilhoDireito(No filhoDireito){
        this.filhoDireito = filhoDireito;
    }

    public No getPai(){
        return pai;
    }

    public void setPai(No pai){
        this.pai = pai;
    }

}
