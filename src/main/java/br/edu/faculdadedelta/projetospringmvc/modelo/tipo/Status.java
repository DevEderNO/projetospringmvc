package br.edu.faculdadedelta.projetospringmvc.modelo.tipo;

public enum Status {
	ASSISTIR("Assitir"), ASSISTINDO("Assistindo"), ASSISTIDO("Assistido");

	private String descricao;

	private Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
