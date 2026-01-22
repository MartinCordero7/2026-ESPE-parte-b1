package es.upm.grise.profundizacion.subscriptionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.grise.profundizacion.exceptions.ExistingUserException;
import es.upm.grise.profundizacion.exceptions.LocalUserDoesNotHaveNullEmailException;
import es.upm.grise.profundizacion.exceptions.NullUserException;

public class SubscriptionServiceTest {

	private SubscriptionService service;

	@BeforeEach
	public void setUp() {
		service = new SubscriptionService();
	}

	@Test
	public void startsWithNoSubscribers() {
		assertEquals(0, service.getSubscribers().size());
	}

	@Test
	public void addingNullUserThrowsNullUserException() {
		assertThrows(NullUserException.class, () -> service.addSubscriber(null));
	}

	@Test
	public void addingExistingUserThrowsExistingUserException() throws Exception {
		User user = new User();
		service.addSubscriber(user);
		assertThrows(ExistingUserException.class, () -> service.addSubscriber(user));
	}

	@Test
	public void localUserWithEmailThrowsLocalUserDoesNotHaveNullEmailException() throws Exception {
		User user = new User();
		user.setDelivery(Delivery.LOCAL);
		user.setEmail("test@example.com");
		assertThrows(LocalUserDoesNotHaveNullEmailException.class, () -> service.addSubscriber(user));
	}

}

