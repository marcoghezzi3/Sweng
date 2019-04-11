package it.unimi.di.se.lab05;


public class WordsPaginator implements Paginator {
    private static final String ERROR_MESSAGE = "Il valore assegnato al parametro pageSize non è valido.";
    private String[] elements;
    private int pageSize;

    public WordsPaginator(String[] elements, int pageSize) {
        if (pageSize<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        this.elements = elements;
        this.pageSize = pageSize;
    }

    public WordsPaginator(String[] elements) {
        this(elements, 4);
    }

    @Override
    public int pageCount() {
        int count = 0;
        if (elements.length%pageSize!=0)
            count+=1;
        count += elements.length/pageSize;
        return count;
    }

    @Override
    public int itemCount() {
        return elements.length;
    }

    @Override
    public int pageItemCount(int pageIndex) {
        if (checkPageIndex(pageIndex)) return -1;
        if (pageIndex==pageCount())
            return elements.length%pageSize;
        return pageSize;
    }

    private boolean checkPageIndex(int pageIndex) {
        if (pageIndex<=0 || pageIndex>pageCount())
            return true;
        return false;
    }

    @Override
    public String page(int pageIndex) {
        return null;
    }

    @Override
    public String upperCasePage(int pageIndex) {
        return null;
    }

    @Override
    public void remove(int pageIndex) {

    }
}
