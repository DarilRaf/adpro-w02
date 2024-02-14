package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EshopApplicationTests {

	@MockBean
	private EshopApplication eshopApplication;

	@Test
	void contextLoads() {
		assertNotNull(eshopApplication);
	}

	@Test
	void mainMethodStartsApplication() {
		assertDoesNotThrow(() -> EshopApplication.main(new String[] {}));
	}
}
