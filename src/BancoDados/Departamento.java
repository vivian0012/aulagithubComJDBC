package BancoDados;

import java.util.Objects;

public class Departamento {

	private Integer Id;
	private String names;

	// CONSTRUCTORS
	public Departamento() {
	}

	public Departamento(Integer id, String names) {
		super();
		Id = id;
		this.names = names;
	}

	// GETTERS
	public Integer getId() {
		return Id;
	}
	
	public void setId(Integer id) {
		Id = id;
	}

	public String getNames() {
		return names;
	}

	// HASHCODE AND EQUALS
	@Override
	public int hashCode() {
		return Objects.hash(Id, names);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return Objects.equals(Id, other.Id) && Objects.equals(names, other.names);
	}

	// TO_STRING
	@Override
	public String toString() {
		return "Departamento [Id=" + Id + ", names=" + names + "]";
	}

}
