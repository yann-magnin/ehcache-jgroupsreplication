package test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jgroups.stack.IpAddress;
import org.junit.jupiter.api.Test;

import net.sf.ehcache.distribution.jgroups.AddressWrapper;

public class TestAddressWrapper {

	@Test
	void testSerialization() throws Exception {
		IpAddress address = new IpAddress("127.0.0.1");
		AddressWrapper wrapper = AddressWrapper.from(address);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		new ObjectOutputStream(baos).writeObject(wrapper);
		Object deserialized = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
		assertThat(deserialized).isEqualTo(wrapper);
		assertThat(deserialized).isInstanceOf(AddressWrapper.class);
		assertThat((AddressWrapper) deserialized).extracting(AddressWrapper::getAddress).isEqualTo(address);
	}

}
