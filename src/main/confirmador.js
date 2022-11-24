/**
 * TELA DE PACIENTE
 * CONFIRMAÇÃO DE EXCLUSÃO DE UM PACIENTE
 */
 
 function confirmar(cod_paciente) {
	let resposta = confirm("Esta ação não pode ser desfeita! Você tem certeza desta exclusão?")
	if (resposta === true) {
		//alert(cod_paciente)
		window.location.href = "deletePaciente?cod_paciente=" + cod_paciente
	}
}