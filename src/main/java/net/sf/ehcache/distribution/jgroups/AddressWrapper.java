package net.sf.ehcache.distribution.jgroups;

import static java.util.Optional.ofNullable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jgroups.Address;
import org.jgroups.util.Util;

/**
 * Serializable {@link Address} wrapper.
 */
public class AddressWrapper implements Serializable {

	private static final long serialVersionUID = -5456208013156616604L;

	private Address address;

	private AddressWrapper() {
	}

	public AddressWrapper(Address address) {
		this();
		this.address = address;
	}

	private void readObject(ObjectInputStream inputStream) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		address = Util.readAddress(inputStream);
	}

	private void writeObject(ObjectOutputStream outputStream) throws IOException {
		Util.writeAddress(address, outputStream);
	}

	public Address getAddress() {
		return address;
	}

	public static AddressWrapper from(Address address) {
		return new AddressWrapper(address);
	}

	@Override
	public int hashCode() {
		if (address == null) {
			return 0;
		}
		return address.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof AddressWrapper)) {
			return false;
		}
		AddressWrapper other = (AddressWrapper) obj;
		if (address == null && other.getAddress() == null) {
			return true;
		} else if (address == null && other.getAddress() != null) {
			return false;
		} else {
			return address.equals(other.getAddress());
		}
	}

	@Override
	public String toString() {
		return ofNullable(address).map(Address::toString).orElse("null");
	}
}
