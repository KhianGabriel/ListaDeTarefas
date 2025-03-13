<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:admin>
	<jsp:attribute name="topImports">
		 <!-- DataTables -->
		  <link rel="stylesheet"
			href="${pageContext.request.contextPath}/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
		  <link rel="stylesheet"
			href="${pageContext.request.contextPath}/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
		  <link rel="stylesheet"
			href="${pageContext.request.contextPath}/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
		  <link rel="icon" type="image/png"
		    href="${pageContext.request.contextPath}/docs/assets/img/lista.png">
	</jsp:attribute>

	<jsp:attribute name="bottomImports">
		<!-- DataTables  & Plugins -->
			<!-- jQuery -->
			<script src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
			<!-- DataTables JS -->
			<script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
			<script src="${pageContext.request.contextPath}/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
			<script src="${pageContext.request.contextPath}/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
			<script src="${pageContext.request.contextPath}/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
			<script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
			<script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
			<script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/buttons.flash.min.js"></script>
			<script src="${pageContext.request.contextPath}/plugins/jszip/jszip.min.js"></script>
			<script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
			<script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/buttons.print.min.js"></script>
			<script src="${pageContext.request.contextPath}/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
			<script src="${pageContext.request.contextPath}/plugins/pdfmake/pdfmake.min.js"></script>
			<script src="${pageContext.request.contextPath}/plugins/pdfmake/vfs_fonts.js"></script>
			
			<!-- Page specific script -->
			<script
			src="${pageContext.request.contextPath}/assets/js/local/todolist.js?vs=${initParam.versionNumber}">
				
			</script>
			<script
			src="${pageContext.request.contextPath}/assets/js/local/smtype.js?vs=${initParam.versionNumber}">	
			</script>
			<script>
			var dataTablesLang = "${pageContext.request.contextPath}/assets/plugins/datatables/dataTables.pt-br.lang?vs=${initParam.versionNumber}";
			</script>
	</jsp:attribute>

	<jsp:body>
		<!-- Content Wrapper. Contains page content -->
		<div id="search-card" class="card">
		  <div class="content">
		    <!-- Content Header (Page header) -->
		    <section class="content-header">
		      <div class="container-fluid">
		        <div class="row mb-2">
		          <div class="card-header col-md-12 col-sm-12">
		            <h1 class="card-title">Lista de Tarefas</h1>
		          </div>
            	</div>
            	<div class="card-header">
				    <form id="search-form" action="${pageContext.request.contextPath}/xmvc?cl=ToDoListSearch" method="POST">
				        <div class="form-row px-2 col-md-12 col-sm-12">
				            <div class="form-group col-md-8 col-sm-8">
				                <button id="btn-add" type="button" class="btn btn-primary px-4 mr-2">
				                    <i class="fa fa-plus"></i> Adicionar
				                </button>
				            </div>
				            <div class="form-group col-md-4 col-sm-4">
			                <div class="input-group">
			                    <input id="word" name="word" type="text" class="form-control" placeholder="Pesquisar por titulo">
			                    <div class="input-group-append">
			                        <button class="input-group-text"><i class="fa fa-search"></i></button>
			                    </div>
			                </div>
				        </div>
				        </div>
				    </form>
				</div>
			  </div>
		        <!-- /.container-fluid -->
		      </section>
		    <!-- Main content -->
		    <section class="content">
		      <div class="container-fluid">
		        <div class="row">
		          <div class="col-md-12 col-sm-12">
		            <div class="card">
		              <!-- /.card-header -->
		              <div class="card-body">
		                <table id="searchTable" class="table table-bordered table-striped display" style="width: 100%">				
		                  <thead>
		                  <tr>
		                  	<th>Status</th>
		                    <th>Id</th>
		                    <th>Titulo</th>
		                    <th>Data de Criação</th>
		                    <th>Prioridade</th>
		                    <th>Data de Conclusão</th>
		                    <th>Ações</th>  
		                  </tr>
		                  </thead>
		                  <tbody>
		                  </tbody>
		                  <tfoot>
		                  <tr>
		                    <th>Status</th>
		                    <th>Id</th>
		                    <th>Titulo</th>
		                    <th>Data de Criação</th>
		                    <th>Prioridade</th>
		                    <th>Data de Conclusão</th>
		                    <th>Ações</th> 
		                   </tr>
		                  </tfoot>
		                </table>
		              </div>
		              <!-- /.card-body -->
		            </div>
		            <!-- /.card -->
		          </div>
		          <!-- /.col -->
		        </div>
		        <!-- /.row -->
		      </div>
		      <!-- /.container-fluid -->
		    </section>
		    <!-- /.content -->
		  </div>
		  <!-- /.content-wrapper -->
		 </div>
		 
		 <div class="col mt-2">
			<div id="register-card" class="card">
	  			<div class="card-header">
					<h1 class="card-title" style="font-size: 20px; font-weight: bold;">Nova Tarefa</h1>
	  			</div>		
				<form id="register-form"
					  action="${pageContext.request.contextPath}/xmvc?cl=ToDoList" 
					  method="POST">
				<div class="card-body px-4">
					<input id="id" name="id" type="hidden">
					<input id="opt" name="opt" type="hidden" value="">
					
	  				<div class="form-group">
	  					<label for="titulo">Titulo</label>
	  					<div class="input-group">
	  						<input id="titulo" name="titulo" type="text" class="form-control"
									maxlength="80" value="" autocomplete="off">
					 	</div>
	  				</div>
	  				<div class="form-group">
					    <label for="descricao">Descrição</label>
					    <textarea class="form-control" id="descricao" name="descricao" rows="3" style="resize: none"></textarea>
				   </div>
	  				<div class="form-group">
					    <label for="prioridade">Prioridade</label>
					    <select class="form-control" id="prioridade" name="prioridade">
					    	<option selected="selected"></option>
					    	<option>Alta</option>
					    	<option>Media</option>
					    	<option>Baixa</option>
					    </select>
	  				</div>
	  				<div>
						<button type="reset" class="btn btn-info">Novo</button>
		  				<button id="btn-save" type="submit" class="btn btn-primary">Salvar</button>
					</div>
					</div>
				</form>	
			</div>
		</div>
		
		<div class="modal fade" id="infoModal" tabindex="-1" role="dialog"
			aria-labelledby="infoModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div
					class="modal-content border-left-0 border-right-0 border-bottom-0 border-info"
					style="border-top: 3px solid;">
			      <div class="modal-header ">
			        <h5 class="modal-title" id="infoModalLabel">
							<i class='fa fa-info-circle text-info'></i> Informções da Tarefa</h5>
			        <button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">			       
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-info"
							data-dismiss="modal">Fechar</button>			        
			      </div>
			    </div>
			  </div>
		</div>
</jsp:body>
</t:admin>


