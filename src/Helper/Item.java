package Helper;

public class Item {
	private int key;
	private String value;
	
	public Item(int key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	// JComboBox nesneleri ekrana yazarken toString() metodunu kullanır. Sen kendi sınıfında toString()'i override etmezsen, 
	// liste “anlamsız” sistem çıktısı (örneğin Item@4e25154f) gösterir. 
	@Override
	public String toString() {
		return this.value;
	}
	
}
