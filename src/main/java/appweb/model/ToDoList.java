package appweb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ToDoList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 80)
	private String titulo;
	
	@Column(length = 80)
	private String tituloSearch;
	
	@Column(length = 255)
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", insertable = true, updatable = false)
	private Date dataCriacao;
	
	private Boolean status;
	
	@Column(length = 5)
	private String prioridade;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_conclusao", insertable = false, updatable = true)
	private Date dataConclusao;
	

	public ToDoList() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTituloSearch() {
		return tituloSearch;
	}

	public void setTituloSearch(String tituloSearch) {
		this.tituloSearch = tituloSearch;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date localDateTime) {
		this.dataCriacao = localDateTime;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	
	public void setCreatedAt(Date createdAt) {
	    this.dataCriacao = createdAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
	    this.dataConclusao = updatedAt;
	}


}
