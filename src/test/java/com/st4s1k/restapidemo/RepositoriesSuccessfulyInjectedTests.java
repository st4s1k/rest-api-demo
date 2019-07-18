package com.st4s1k.restapidemo;

import com.st4s1k.restapidemo.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoriesSuccessfulyInjectedTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void userRepositoryInjected() {
		Assert.assertNotNull(userRepository);
	}

}
