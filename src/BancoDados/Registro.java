package BancoDados;

import java.util.Date;
import java.util.Objects;

public class Registro {
	private String nomeProduto;
	private String nomePessoa;
	private Date dataLocal;
	private Integer quantidade;
	private Double valorBase;
	private Departamento departamento; // Composição
	
	// CONSTRUCTOS
	public Registro() {
	}

	public Registro(String nomeProduto, String nomePessoa, Date dataLocal, Integer quantidade, Double valorBase,
			Departamento departamento) {
		super();
		this.nomeProduto = nomeProduto;
		this.nomePessoa = nomePessoa;
		this.dataLocal = dataLocal;
		this.quantidade = quantidade;
		this.valorBase = valorBase;
		this.departamento = departamento;
	}
	
	// GETTER AND SETTER
	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public Date getDataLocal() {
		return dataLocal;
	}

	public void setDataLocal(Date dataLocal) {
		this.dataLocal = dataLocal;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorBase() {
		return valorBase;
	}

	public void setValorBase(Double valorBase) {
		this.valorBase = valorBase;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	// HASHCODE AND EQUALS
	@Override
	public int hashCode() {
		return Objects.hash(dataLocal, departamento, nomePessoa, nomeProduto, quantidade, valorBase);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		return Objects.equals(dataLocal, other.dataLocal) && Objects.equals(departamento, other.departamento)
				&& Objects.equals(nomePessoa, other.nomePessoa) && Objects.equals(nomeProduto, other.nomeProduto)
				&& Objects.equals(quantidade, other.quantidade) && Objects.equals(valorBase, other.valorBase);
	}
	
	// TO_STRING
	@Override
	public String toString() {
		return "Registro [nomeProduto=" + nomeProduto + ", nomePessoa=" + nomePessoa + ", dataLocal=" + dataLocal
				+ ", quantidade=" + quantidade + ", valorBase=" + valorBase + ", departamento=" + departamento + "]";
	}
	
	
	
}
