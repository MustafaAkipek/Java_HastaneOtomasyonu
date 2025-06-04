package Model;

public class Bashekim extends User{

	// Bashekim nesnesi oluşturulurken User'ın içindeki id, tcno gibi alanlara direkt atanır.
	public Bashekim(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type); 
	}
	
	public Bashekim() {}; // arka planda super() çağırır bu yüzden User() {} lazım

	
	
}
