package it.unimi.di.se.lab05;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class PaginatorTest {

	@Rule
  public Timeout globalTimeout = Timeout.seconds(2);


	@Test
	public void noElementsTest() {
		Paginator paginator = new WordsPaginator(new String[]{}, 2);
		assertThat(paginator.pageCount()).isEqualTo(0);
		assertThat(paginator.itemCount()).isEqualTo(0);
		//fail("Not yet implemented.");
	}

	@Test
	public void constructorTest() {
		Paginator paginator = new WordsPaginator(new String[]{"Lorem", "ipsum", "dolor", "sit", "amet"}, 2);
		assertThat(paginator.pageCount()).isEqualTo(3);
		assertThat(paginator.itemCount()).isEqualTo(5);
	}

	@Test
	public void pageItemCountTest() {
		Paginator paginator = new WordsPaginator(new String[]{"Lorem", "ipsum", "dolor", "sit", "amet"}, 2);
		assertThat(paginator.pageCount()).isEqualTo(3);
		assertThat(paginator.itemCount()).isEqualTo(5);
		assertThat(paginator.pageItemCount(1)).isEqualTo(2);
		assertThat(paginator.pageItemCount(3)).isEqualTo(1);
		assertThat(paginator.pageItemCount(0)).isEqualTo(-1);
		assertThat(paginator.pageItemCount(10)).isEqualTo(-1);
	}

	@Test
	public void illegalArgumentTest() {
		assertThatThrownBy(() -> {
			Paginator paginator = new WordsPaginator(new String[]{"ciao", "miao"}, -1);
		})
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Il valore assegnato al parametro pageSize non è valido.");
	}

	@Test
	public void defaultConstructorTest() {
		Paginator paginator = new WordsPaginator(new String[]{"Lorem", "ipsum", "dolor", "sit", "amet"});
		assertThat(paginator.pageCount()).isEqualTo(2);
		assertThat(paginator.itemCount()).isEqualTo(5);
		assertThat(paginator.pageItemCount(1)).isEqualTo(4);
		assertThat(paginator.pageItemCount(2)).isEqualTo(1);
		assertThat(paginator.pageItemCount(10)).isEqualTo(-1);

	}

	@Test
	public void pageTest() {
		Paginator paginator = new WordsPaginator(new String[]{"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing"}, 3);
		assertThat(paginator.page(2)).isEqualTo("sit amet consectetur");
		assertThat(paginator.page(1)).isEqualTo("Lorem ipsum dolor");
		assertThat(paginator.page(3)).isEqualTo("adipiscing");
	}
}
