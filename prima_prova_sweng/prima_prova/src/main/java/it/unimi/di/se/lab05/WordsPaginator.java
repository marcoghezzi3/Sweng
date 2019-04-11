package it.unimi.di.se.lab05;


public class WordsPaginator implements Paginator {
    private static final String ERROR_MESSAGE = "Il valore assegnato al parametro pageSize non è valido.";
    private static final int PAGE_SIZE = 4;
    private String[] elements;
    private int pageSize;

    public WordsPaginator(String[] elements, int pageSize) {
        if (pageSize<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        this.elements = elements;
        this.pageSize = pageSize;
    }

    public WordsPaginator(String[] elements) {
        this(elements, PAGE_SIZE);
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
        StringBuilder output = new StringBuilder();
        int parola = (pageIndex-1)*pageSize;
        for (int j = 0; j < pageItemCount(pageIndex); j++) {
            output.append(elements[parola]).append(" ");
            parola++;
        }
        return output.toString().trim();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= pageCount(); i++) {
            output.append(i).append(":");
            output.append(" ").append(page(i));
            if (i!=pageCount())
                output.append("\n");

        }
        return output.toString();
    }

    @Override
    public String upperCasePage(int pageIndex) {
        return page(pageIndex).toUpperCase();
    }

    @Override
    public void remove(int pageIndex) {

    }
}
