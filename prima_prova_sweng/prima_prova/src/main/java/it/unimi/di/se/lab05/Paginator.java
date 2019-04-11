package it.unimi.di.se.lab05;

public interface Paginator {
int pageCount();
int itemCount();
int pageItemCount(final int pageIndex);
String page(final int pageIndex);
String upperCasePage(final int pageIndex);
void remove(final int pageIndex);
}
