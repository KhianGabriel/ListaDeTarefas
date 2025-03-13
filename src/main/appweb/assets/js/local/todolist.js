
var $dao = {
	form: $("#register-form"),
	searchForm: $("#search-form"),
	table: {},
	obj: {},
	init: function() {

		$("#search-form").submit(function() {
			$dao.table.draw();
			return false;
		});

		$("#btnToSearch").click(function() {
			//$dao.table.draw();			
			//$dao.table.columns.adjust().draw();
			$dao.backToSearch();
		});
		
		$("#btn-save").click(function() {
			$dao.save();
			
			return false;
		});

		$("#btn-remove").click(function() {
			$dao.delete();
		});

		$("#btn-add").click(function() {
			$dao.backToRegister();
		});

		//let table = new DataTable('#example');

		//$dao.createForm();
		//$dao.initForm();
		$dao.createTable();
		$dao.table.draw();

	},

	goToRegister: function() {
		//$dao.new();
		$("#search-card").hide();
		$("#register-card").fadeIn(600);
		//table.destroy();
	},

	goToSearch: function() {
		$dao.table.ajax.reload();
		$("#register-card").hide();
		$("#search-card").fadeIn(600);
	},

	backToSearch: function() {
		//location.hash = "#search-card";
		$dao.table.draw();
		$("#register-card").hide();
		$("#search-card").fadeIn(600);
	},

	backToRegister: function() {
		//location.hash = "#register-card";
		$("#search-card").hide();
		$("#register-card").fadeIn(600);
	},

	createForm: function() {
		$.post(contextPath + "/xmvc?cl=Modulo", { opt: "listAll" }, function(data) {
			if (data.sucess)
				$dao.modulos = data.results;
			$("#modulo").loadValues($dao.modulos, "Selecione o módulo");
		});
	},

	initForm: function() {
		if (!$("#modulo")[0].length)
			$("#modulo").loadValues($dao.modulos, "Selecione o módulo");
		$("#ativo").prop('checked', true);
	},
	
			
	createTable: function() {
		$dao.table = $('#searchTable').DataTable({
			"order": [[1, "asc"]],
			"pagingType": "numbers",
			"scrollX": true,
			"scrollCollapse": true,
			"processing": true,
			"serverSide": true,
			"searching": false,
			"lengthchange": true,
			"deferLoading": [0],
			"responsive": true,
			"autoWidth": true,
			"buttons": ['copy', 'excel', 'pdf', 'print', 'colvis'],
			"dom": 'Bfrtipl',
			"ajax": {
				"url": $("#search-form").prop("action"),
				"type": "POST",
				"data": function(data) {
					data.word = $('#word').val();
				}
			},
			"language": {
				"url": dataTablesLang
			},
			"columnDefs": [{
				"targets": 6,
				"data": null,
				"orderable": false,
				"render": function(data, type, row, meta) {
					return `<button class='btn btn-primary' id='btn-edit-${row.id}' data-toggle='tooltip' data-placement='top'
						title='Editar' onclick='$dao.load(${row.id})' ${row.status ? "disabled":""}><i class='fa fa-pencil-alt'></i></button>
						<button class='btn btn-info ml-1' id='btn-info-${row.id}' data-toggle='modal' data-placement='top' title='Info'
						onclick='$dao.showInfo(${meta.row})' data-target='#infoModal' ${row.status ? "disabled":""}><i class='fa fa-info-circle'></i></button> 
						<button class='btn btn-danger ml-1' id='btn-remove-${row.id}' data-toggle='tooltip' data-placement='top'  
						title='Excluir' onclick='$dao.delete(${row.id})' ${row.status ? "disabled":""}><i class='fa fa-trash-alt'></i></button>`;
				}
			}],
			"columns": [
				{ "targets": 0,
				  "data": null,
				  "className": 'text-center',
				  "render": function(data, type, row, meta) {
					return `<input type="checkbox" name="status_${row.id}" id="status_${row.id}"
					 onclick="$dao.verificaStatus(${row.id}, ${meta.row})" ${row.status ? "checked":""}>`;
				  }
			  	},
				{ "targets": 1, "data": "id" },
				{ "targets": 2, "data": "titulo" },
				{
					"targets": 3,
					"data": null,
					"render": function(data, type, row, meta) {
						if (row.dataCriacao != null) {
						    let isoDateString = row.dataCriacao;
							console.log(row.dataCriacao);
						    if (typeof isoDateString !== 'string') {
						        isoDateString = isoDateString.toISOString();
						    }
						    return $sm.parseIsoDate(isoDateString).format('dd/mm/yyyy HH:MM:ss');
						} else {
						    console.log("Data de criação nula");
							console.log(row.dataCriacao);
						    row.dataCriacao = new Date();
						    return $sm.parseIsoDate(row.dataCriacao.toISOString()).format('dd/mm/yyyy HH:MM:ss');
						};
					}
				},
				{ "targets": 4, "data": "prioridade" },
				{
					"targets": 5,
					"data": null,
					"render": function(data, type, row, meta) {

						if (row.dataConclusao && row.status) {
							return $sm.parseIsoDate(row.dataConclusao).format('dd/mm/yyyy HH:MM:ss');
						} else {
							return '';
						}
					}
				}
			]
		});
		
		$dao.goToSearch();
	},
	showMessages: function($form, data) {
		erros = data.messages.erros;
		$sm.clearFeedback($form);

		for (i = 0; i < erros.length; i++) {
			if (erros[i].typeMessage == 0) {
				$ctrl = $("#" + erros[i].fieldName);
				if ($ctrl[0]) {
					if (erros[i].fieldName == "gt1") {
						$('#gtTitle').after('<div id="sm-gt1" class="sm-feedback"><div class="invalid-feedback sm-invalid-feedback">' + erros[i].message + '</div></div>');
					}
					else {
						$ctrl.addClass("is-invalid");
						if ($ctrl.attr('class').indexOf('select2') != -1) {
							$ctrl.next().addClass("is-invalid");
							$ctrl.next().after('<div class="invalid-feedback">' + erros[i].message + '</div>');
						}
						else if ($("#" + erros[i].fieldName).next(".input-group-append").length) {
							$ctrl.next(".input-group-append").after('<div class="invalid-feedback">' + erros[i].message + '</div>');
						}
						else {
							$ctrl.after('<div class="invalid-feedback">' + erros[i].message + '</div>');
						}
					}
				}
				else
					console.log("Campo do formulário: '%s' não encontrado, Msg: %s", erros[i].fieldName, erros[i].message);
			}
			else {
				$('.card-body', $form).append('<div class="form-group sm-feedback is-invalid"><div class="invalid-feedback sm-invalid-feedback">' + erros[i].message + '</div></div>');
				console.log("Erro: " + erros[i].message);
			}
		}
		$('body').scrollTo(".invalid-feedback", 700, { offset: -70 });
	},

	showInfo: function(id) {
		//console.log(id);
		//console.log($dao.table.row(id).data());
		let row = $dao.table.row(id).data();
		let dlStr = '<dl>' +
			'<dt>Id</dt>' +
			'<dd>' + row.id + '</dd>' +
			'<dt>Titulo</dt>' +
			'<dd>' + row.titulo + '</dd>' +
			'<dt>Descricao</dt>' +
			'<dd>' + row.descricao + '</dd>' +
			'<dt>Prioridade</dt>' +
			'<dd>' + row.prioridade + '</dd>' +
			(row.usuarioCriador ?
				'<dt>Criado por</dt>' +
				'<dd>' + row.usuarioCriador.titulo + '</dd>' : "") +
			(row.dataCriacao ?
				'<dt>Data de criação</dt>' +
				'<dd>' + $sm.parseIsoDate(row.dataCriacao).format('dd/mm/yyyy "as" HH:MM:ss') + '</dd>' : "") +
			(row.usuarioAlterador ?
				'<dt>Alterado por</dt>' +
				'<dd>' + row.usuarioAlterador.titulo + '</dd>' : "") +
			(row.dataAlteracao ?
				'<dt>Data de alteração</dt>' +
				'<dd>' + $sm.parseIsoDate(row.dataConclusao).format('dd/mm/yyyy "as" HH:MM:ss') + '</dd>' : "") +
			'</dl>';
		$('.modal-body').html(dlStr);
	},

	reset: function() {
		$dao.form.clearForm();
	},

	load: function(id) {
			$.post($dao.form.prop("action"), { opt: "load", id: id }, function(data) {
				if (data.sucess) {
					$dao.obj = data.results[0];
					//alert("setValues:" + data.sucess);	
					//$dao.reset();
					//console.log("new: %s", JSON.stringify($dao.obj));
					$dao.form.setValues($dao.obj);
					//console.log("setValues: %s", JSON.stringify($dao.obj));				
					$dao.backToRegister();
				}
			});
		},

	save: function() {
		console.log("save : " + $dao.form.prop("action"));
		$("#opt").val($("#id").val() == "" ? "save" : "update");
		$.post($dao.form.prop("action"), $dao.form.serialize(), function(data) {
			if (data.sucess) {
				$sm.showDialog($("#opt").val());
				$dao.reset();
				$dao.backToSearch();
			}
			else
				$dao.form.showMessages(data);
		});
	},

	delete: function(id) {
			if (confirm("Tem certeza que deseja remover essa tarefa?")) { 
				$.post($dao.form.prop("action"),{ opt: "delete", id: id }, function(data) {
					if (data.sucess){
						$sm.showDialog("Deletado com sucesso!");
						      $dao.goToSearch();
					}else
						$dao.showMessages($dao.form, data);
				});
			}
		},
		
	verificaStatus: function(id, rowIndex) {
		let status = $(`#status_${id}`).prop('checked');
		if (status) {
			$(`#btn-edit-${id}`).prop('disabled', true);
			$(`#btn-info-${id}`).prop('disabled', true);
			$(`#btn-remove-${id}`).prop('disabled', true);
		} else {
			$(`#btn-edit-${id}`).prop('disabled', false);
			$(`#btn-info-${id}`).prop('disabled', false);
			$(`#btn-remove-${id}`).prop('disabled', false);
		}
		
		console.log("Status: " + status);
		$.post($dao.form.prop("action"),{ opt: "updateStatus", status: status, id: id }, function(data) {
			if (data.sucess) {
				$sm.showDialog("Status alterado com sucesso!");
				$dao.table.draw();
			} else
				$dao.showMessages($dao.form, data);
		});
	}
};


$(document).ready(function() {
	$dao.init();
});
