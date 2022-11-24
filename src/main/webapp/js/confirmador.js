/**
 * TELA DE PACIENTE
 * CONFIRMAÇÃO DE EXCLUSÃO DE UM PACIENTE
 */
 
 function confirmar(cod_paciente) {
	let resposta = confirm("Paciente: "+cod_paciente+" | \u0045\u0073\u0074\u0061\u0020\u0061\u00e7\u00e3\u006f\u0020\u006e\u00e3\u006f\u0020\u0070\u006f\u0064\u0065\u0020\u0073\u0065\u0072\u0020\u0064\u0065\u0073\u0066\u0065\u0069\u0074\u0061\u0021\u0020\u0056\u006f\u0063\u00ea\u0020\u0074\u0065\u006d\u0020\u0063\u0065\u0072\u0074\u0065\u007a\u0061\u0020\u0064\u0065\u0073\u0074\u0061\u0020\u0065\u0078\u0063\u006c\u0075\u0073\u00e3\u006f\u003f")
	if (resposta === true) {
		//alert(cod_paciente)
		window.location.href = "deletePaciente?cod_paciente=" + cod_paciente
	}
}