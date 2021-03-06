package it.unimi.di.se.lab05;


import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

public class WordsPaginator implements Paginator {
    private static final String ERROR_MESSAGE = "Il valore assegnato al parametro pageSize non è valido.";
    private static final int PAGE_SIZE = 4;
    private ArrayList<String> elements;
    private int pageSize;


    public WordsPaginator(String[] elements, int pageSize) {
        if (pageSize<=0)
            throw new IllegalArgumentException(ERROR_MESSAGE);
        this.elements = new ArrayList<>(Arrays.asList(elements));
        this.pageSize = pageSize;
    }

    public WordsPaginator(String[] elements) {
        this(elements, PAGE_SIZE);
    }

    public WordsPaginator(Reader reader, int pageSize) throws IOException {
        String s = getString(reader);
        String regex="[ \n]";
        String[] parole = s.split(regex);
        this.elements = new ArrayList<>(Arrays.asList(parole));
        this.pageSize = pageSize;
    }

    public WordsPaginator(Reader reader, String[] stopWords, int pageSize) throws IOException {

        String s = getString(reader);
        String regex="\\s+|(?=\\p{Punct})|(?<=\\p{Punct})";
        String[] parole = s.split(regex);
        this.elements = new ArrayList<>(Arrays.asList(parole));
        removeStopWords(stopWords);

        this.pageSize = pageSize;

    }

    private void removeStopWords(String[] stopWords) {
        for (int i = 0; i < elements.size(); i++) {
            for (int j = 0; j < stopWords.length; j++) {
                if (stopWords[j].equals(elements.get(i)) ) {
                    elements.remove(i);
                    i--;
                }
            }
        }
    }

    private String getString(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            char ch = (char)reader.read();
            sb.append(ch);
        }
        reader.close();
        return sb.toString();
    }

    @Override
    public int pageCount() {
        int count = 0;
        if (elements.size()%pageSize!=0)
            count+=1;
        count += elements.size()/pageSize;
        return count;
    }

    @Override
    public int itemCount() {
        return elements.size();
    }

    @Override
    public int pageItemCount(int pageIndex) {
        if (checkPageIndex(pageIndex)) return -1;
        if (pageIndex==pageCount())
            return elements.size()%pageSize;
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
            output.append(elements.get(parola)).append(" ");
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
        int parola = (pageIndex-1)*pageSize;
        for (int j = 0; j < pageItemCount(pageIndex); j++) {
            elements.remove(j);
            ++parola;
        }
    }
}
