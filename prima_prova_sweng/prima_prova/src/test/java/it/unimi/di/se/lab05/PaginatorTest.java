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
}
