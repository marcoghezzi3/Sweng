package it.unimi.di.se.lab05;

public class WordsPaginator implements Paginator {
    public WordsPaginator(String[] strings, int pageSize) {

    }

    @Override
    public int pageCount() {
        return 1;
    }

    @Override
    public int itemCount() {
        return 1;
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
