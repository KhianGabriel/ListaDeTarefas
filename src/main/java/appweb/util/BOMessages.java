package appweb.util;

public class BOMessages {

	// Mensagens de Erro
	public static String uniqueKey(String field) {
		return "Existe outro registro com o mesmo valor do campo '" + field + "'!";
	}

	public static String nullField(String field) {
		return "O campo '" + field + "' é obrigatório!";
	}
	
	public static String invalidField(String field) {
		return "O campo '" + field + "' possui valor inválido!";
	}

	public static String notSelectedValue(String field) {
		return "O campo '" + field + "' precisa ser selecionado!";
	}

	public static String notFound() {
		return "O registro não foi encontrado!";
	}

	public static String notInsert() {
		return "O registro não pode ser cadastrado!";
	}

	public static String notAltered() {
		return "O registro não pode ser alterado!";
	}

	public static String notRemove() {
		return "Operação de exclusão não realizada!";
	}

	public static String notRemoveConstraint() {
		return "Operação de exclusão não realizada(Existem dados relacionados a esse registro)!";
	}

	// Mensagem de Operação Realizada com Sucesso
	public static String sucess() {
		return "Operação realizada com sucesso!";
	}

	public static String nullFieldWarning(String field) {
		return "O campo '" + field + "' não foi informado!";
	}
}
