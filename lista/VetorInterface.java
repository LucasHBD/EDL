package lista;

public interface VetorInterface {
    public int size();
    public boolean isEmpty();
    public Object elementAtRank(int rank) throws ElemVazia;
    public Object replaceAtRank(int rank, Object novo_elemento) throws ElemVazia;
    public Object removeAtRank(int rank) throws ElemVazia;
    public void insertAtRank(int rank, Object novo_elemento) throws ElemVazia;
}
