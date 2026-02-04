package heap;

public class No {
    private No pai, filhoDireito, filhoEsquerdo;
    private Object elemento;
    private int key;

    public No(Object elemento, int key, No pai){
        this.elemento = elemento;
        this.key = key;
        this.pai = pai;
        setFilhoEsquerdo(null);
        setFilhoDireito(null);
    }

    public No getPai(){
        return pai;
    }

    public void setPai(No pai){
        this.pai = pai;
    }

    public No getFilhoDireito(){
        return filhoDireito;
    }

    public void setFilhoDireito(No filhoDireito){
        this.filhoDireito = filhoDireito;
    }

    public No getFilhoEsquerdo(){
        return filhoEsquerdo;
    }

    public void setFilhoEsquerdo(No filhoEsquerdo){
        this.filhoEsquerdo = filhoEsquerdo;
    }

    public Object getElemento(){
        return elemento;
    }

    public void setElemento(Object elemento){
        this.elemento = elemento;
    }

    public int getKey(){
        return key;
    }

    public void setKey(int key){
        this.key = key;
    }
}
