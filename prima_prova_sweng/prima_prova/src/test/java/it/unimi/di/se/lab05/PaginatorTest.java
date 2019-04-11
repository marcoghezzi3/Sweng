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

}
