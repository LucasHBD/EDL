package arvore.ArvoreAVL;

public class No {
    private Integer elemento;
    private No filhoEsquerdo, filhoDireito, pai;
    private Integer balanceamento;

    public No(){
        this.elemento = null;
        this.pai = null;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
        this.balanceamento = 0;
    }

    public No(Integer elemento, No pai){
        this.elemento = elemento;
        this.pai = pai;
        this.filhoEsquerdo = null;
        this.filhoDireito = null;
        this.balanceamento = 0;
    }

    public Integer getElemento(){
        return elemento;
    }

    public void setElemento(Integer elemento){
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

    public Integer getBalanceamento() {
        return balanceamento;
    }

    public void setBalanceamento(Integer balanceamento) {
        this.balanceamento = balanceamento;
    }

    public Boolean oneChild(){
        if((filhoDireito == null && filhoEsquerdo != null) || (filhoDireito != null && filhoEsquerdo == null)){
            return true;
        }
        return false;
    }
}
