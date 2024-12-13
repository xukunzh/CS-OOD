package Problem1;

<<<<<<< HEAD
/**
 * Class representing a property.
 */
public abstract class Property {
=======
import java.util.Objects;

/**
 * Class representing a property.
 */
public class Property {
>>>>>>> origin/Chen_Haoyuan
    private String address;
    private Integer size;

    /**
     * Constructs a new Property object.
     *
     * @param address The address of the property, as a String
     * @param size The size of the property, as an Integer
     */
<<<<<<< HEAD
    protected Property(String address, Integer size) {
=======
    public Property(String address, Integer size) {
>>>>>>> origin/Chen_Haoyuan
        validateSize(size);
        this.address = address;
        this.size = size;
    }

    /**
     * Helper function to validate the size.
     *
     * @param size The size, as an Integer
     * @throws IllegalArgumentException If the size is less than 0
     */
    private void validateSize(Integer size) throws IllegalArgumentException {
        if (size <= 0)
            throw new IllegalArgumentException("Size must be greater than 0.");
    }

    /**
     * Gets the address.
     *
     * @return The address, as a String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address The address, as a String
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the size.
     *
     * @return The size, as an Integer
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Sets the size.
     *
     * @param size The size, as an Integer
     */
    public void setSize(Integer size) {
        validateSize(size);
        this.size = size;
    }
<<<<<<< HEAD
=======

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Property property = (Property) o;
        return Objects.equals(address, property.address) && Objects.equals(size,
            property.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, size);
    }

    @Override
    public String toString() {
        return "Property{" +
            "address='" + address + '\'' +
            ", size=" + size +
            '}';
    }
>>>>>>> origin/Chen_Haoyuan
}
