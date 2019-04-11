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
        return 0;
    }

    @Override
    public int itemCount() {
        return elements.length;
    }

    @Override
    public int pageItemCount(int pageIndex) {
        return 0;
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
