package it.unimi.di.se.lab05;


public class WordsPaginator implements Paginator {
    private String[] elements;
    private int pageSize;

    public WordsPaginator(String[] elements, int pageSize) {
        this.elements = elements;
        this.pageSize = pageSize;
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
        if (pageIndex<=0 || pageIndex>pageCount())
            return -1;
        if (pageIndex==pageCount())
            return elements.length%pageSize;
        return pageSize;
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
