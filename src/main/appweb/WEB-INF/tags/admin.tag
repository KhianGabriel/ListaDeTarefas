<%@tag language="java" description="Pagina padrao do sistema" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@attribute name="topImports" fragment="true"%>
<%@attribute name="bottomImports" fragment="true"%>
<%@attribute name="pageHeader" fragment="true"%>
<%@attribute name="footer" fragment="true"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Lista de Tarefas</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/fontawesome-free/css/all.min.css">
  <!-- daterange picker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
  <!-- iCheck for checkboxes and radio inputs -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
  <!-- Bootstrap Color Picker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
  <!-- Tempusdominus Bootstrap 4 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
  <!-- Select2 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/css/select2.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
  <!-- Bootstrap4 Duallistbox -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
  <!-- BS Stepper -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bs-stepper/css/bs-stepper.min.css">
  <!-- dropzonejs -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/dropzone/min/dropzone.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/adminlte.min.css">
  
  
  <jsp:invoke fragment="topImports"/>
  
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars" title="Exibir menu lateral"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="${pageContext.request.contextPath}/index.jsp" class="nav-link" title="Menu">Menu</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" data-widget="fullscreen" href="#" role="button" title="Tela Cheia">
          <i class="fas fa-expand-arrows-alt"></i>
        </a>
      </li>
    </ul>
  </nav>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="${pageContext.request.contextPath}/dist/img/Eu.jpg" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="#" class="d-block">Khian Gabriel</a>
        </div>
      </div>

      <!-- SidebarSearch Form -->
      <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
          <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-sidebar">
              <i class="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-edit"></i>
              <p>
                Projetos
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${pageContext.request.contextPath}/index.jsp" class="nav-link">
                  <i class="nav-icon fas fa-copy"></i>
                  <p>Tarefas</p>
                </a>
              </li>
            </ul>
          </li>
<!--           <li class="nav-item"> -->
<%--             <a href="${pageContext.request.contextPath}/pages/widgets.html" class="nav-link"> --%>
<!--               <i class="nav-icon fas fa-th"></i> -->
<!--               <p> -->
<!--                 Widgets -->
<!--                 <span class="right badge badge-danger">New</span> -->
<!--               </p> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="nav-icon fas fa-copy"></i> -->
<!--               <p> -->
<!--                 Layout Options -->
<!--                 <i class="fas fa-angle-left right"></i> -->
<!--                 <span class="badge badge-info right">6</span> -->
<!--               </p> -->
<!--             </a> -->
<!--             <ul class="nav nav-treeview"> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/layout/top-nav.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Top Navigation</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/layout/top-nav-sidebar.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Top Navigation + Sidebar</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/layout/boxed.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Boxed</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/layout/fixed-sidebar.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Fixed Sidebar</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/layout/fixed-sidebar-custom.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Fixed Sidebar <small>+ Custom Area</small></p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/layout/fixed-topnav.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Fixed Navbar</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/layout/fixed-footer.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Fixed Footer</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/layout/collapsed-sidebar.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Collapsed Sidebar</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="nav-icon fas fa-chart-pie"></i> -->
<!--               <p> -->
<!--                 Charts -->
<!--                 <i class="right fas fa-angle-left"></i> -->
<!--               </p> -->
<!--             </a> -->
<!--             <ul class="nav nav-treeview"> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/charts/chartjs.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>ChartJS</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/charts/flot.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Flot</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/charts/inline.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Inline</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/charts/uplot.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>uPlot</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="nav-icon fas fa-tree"></i> -->
<!--               <p> -->
<!--                 UI Elements -->
<!--                 <i class="fas fa-angle-left right"></i> -->
<!--               </p> -->
<!--             </a> -->
<!--             <ul class="nav nav-treeview"> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/UI/general.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>General</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/UI/icons.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Icons</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/UI/buttons.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Buttons</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/UI/sliders.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Sliders</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/UI/modals.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Modals & Alerts</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/UI/navbar.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Navbar & Tabs</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/UI/timeline.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Timeline</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/UI/ribbons.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Ribbons</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </li> -->
<!--           <li class="nav-item menu-open"> -->
<!--             <a href="#" class="nav-link active"> -->
<!--               <i class="nav-icon fas fa-edit"></i> -->
<!--               <p> -->
<!--                 Forms -->
<!--                 <i class="fas fa-angle-left right"></i> -->
<!--               </p> -->
<!--             </a> -->
<!--             <ul class="nav nav-treeview"> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/forms/general.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>General Elements</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/forms/advanced.html" class="nav-link active"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Advanced Elements</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/forms/editors.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Editors</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/forms/validation.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Validation</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="nav-icon fas fa-table"></i> -->
<!--               <p> -->
<!--                 Tables -->
<!--                 <i class="fas fa-angle-left right"></i> -->
<!--               </p> -->
<!--             </a> -->
<!--             <ul class="nav nav-treeview"> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/tables/simple.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Simple Tables</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/tables/data.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>DataTables</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/tables/jsgrid.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>jsGrid</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </li> -->
<!--           <li class="nav-header">EXAMPLES</li> -->
<!--           <li class="nav-item"> -->
<%--             <a href="${pageContext.request.contextPath}/pages/calendar.html" class="nav-link"> --%>
<!--               <i class="nav-icon far fa-calendar-alt"></i> -->
<!--               <p> -->
<!--                 Calendar -->
<!--                 <span class="badge badge-info right">2</span> -->
<!--               </p> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<%--             <a href="${pageContext.request.contextPath}/pages/gallery.html" class="nav-link"> --%>
<!--               <i class="nav-icon far fa-image"></i> -->
<!--               <p> -->
<!--                 Gallery -->
<!--               </p> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<%--             <a href="${pageContext.request.contextPath}/pages/kanban.html" class="nav-link"> --%>
<!--               <i class="nav-icon fas fa-columns"></i> -->
<!--               <p> -->
<!--                 Kanban Board -->
<!--               </p> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="nav-icon far fa-envelope"></i> -->
<!--               <p> -->
<!--                 Mailbox -->
<!--                 <i class="fas fa-angle-left right"></i> -->
<!--               </p> -->
<!--             </a> -->
<!--             <ul class="nav nav-treeview"> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/mailbox/mailbox.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Inbox</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/mailbox/compose.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Compose</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/mailbox/read-mail.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Read</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="nav-icon fas fa-book"></i> -->
<!--               <p> -->
<!--                 Pages -->
<!--                 <i class="fas fa-angle-left right"></i> -->
<!--               </p> -->
<!--             </a> -->
<!--             <ul class="nav nav-treeview"> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/invoice.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Invoice</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/profile.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Profile</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/e-commerce.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>E-commerce</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/projects.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Projects</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/project-add.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Project Add</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/project-edit.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Project Edit</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/project-detail.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Project Detail</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/contacts.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Contacts</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/faq.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>FAQ</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/contact-us.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Contact us</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="nav-icon far fa-plus-square"></i> -->
<!--               <p> -->
<!--                 Extras -->
<!--                 <i class="fas fa-angle-left right"></i> -->
<!--               </p> -->
<!--             </a> -->
<!--             <ul class="nav nav-treeview"> -->
<!--               <li class="nav-item"> -->
<!--                 <a href="#" class="nav-link"> -->
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p> -->
<!--                     Login & Register v1 -->
<!--                     <i class="fas fa-angle-left right"></i> -->
<!--                   </p> -->
<!--                 </a> -->
<!--                 <ul class="nav nav-treeview"> -->
<!--                   <li class="nav-item"> -->
<%--                     <a href="${pageContext.request.contextPath}/pages/examples/login.html" class="nav-link"> --%>
<!--                       <i class="far fa-circle nav-icon"></i> -->
<!--                       <p>Login v1</p> -->
<!--                     </a> -->
<!--                   </li> -->
<!--                   <li class="nav-item"> -->
<!--                     <a href="../examples/register.html" class="nav-link"> -->
<%--                       <i class="fa${pageContext.request.contextPath}/pagesfa-circle nav-icon"></i> --%>
<!--                       <p>Register v1</p> -->
<!--                     </a> -->
<!--                   </li> -->
<!--                   <li class="nav-item"> -->
<%--                     <a href="${pageContext.request.contextPath}/pages/examples/forgot-password.html" class="nav-link"> --%>
<!--                       <i class="far fa-circle nav-icon"></i> -->
<!--                       <p>Forgot Password v1</p> -->
<!--                     </a> -->
<!--                   </li> -->
<!--                   <li class="nav-item"> -->
<%--                     <a href="${pageContext.request.contextPath}/pages/examples/recover-password.html" class="nav-link"> --%>
<!--                       <i class="far fa-circle nav-icon"></i> -->
<!--                       <p>Recover Password v1</p> -->
<!--                     </a> -->
<!--                   </li> -->
<!--                 </ul> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<!--                 <a href="#" class="nav-link"> -->
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p> -->
<!--                     Login & Register v2 -->
<!--                     <i class="fas fa-angle-left right"></i> -->
<!--                   </p> -->
<!--                 </a> -->
<!--                 <ul class="nav nav-treeview"> -->
<!--                   <li class="nav-item"> -->
<%--                     <a href="${pageContext.request.contextPath}/pages/examples/login-v2.html" class="nav-link"> --%>
<!--                       <i class="far fa-circle nav-icon"></i> -->
<!--                       <p>Login v2</p> -->
<!--                     </a> -->
<!--                   </li> -->
<!--                   <li class="nav-item"> -->
<%--                     <a href="${pageContext.request.contextPath}/pages/examples/register-v2.html" class="nav-link"> --%>
<!--                       <i class="far fa-circle nav-icon"></i> -->
<!--                       <p>Register v2</p> -->
<!--                     </a> -->
<!--                   </li> -->
<!--                   <li class="nav-item"> -->
<%--                     <a href="${pageContext.request.contextPath}/pages/examples/forgot-password-v2.html" class="nav-link"> --%>
<!--                       <i class="far fa-circle nav-icon"></i> -->
<!--                       <p>Forgot Password v2</p> -->
<!--                     </a> -->
<!--                   </li> -->
<!--                   <li class="nav-item"> -->
<%--                     <a href="${pageContext.request.contextPath}/pages/examples/recover-password-v2.html" class="nav-link"> --%>
<!--                       <i class="far fa-circle nav-icon"></i> -->
<!--                       <p>Recover Password v2</p> -->
<!--                     </a> -->
<!--                   </li> -->
<!--                 </ul> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/lockscreen.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Lockscreen</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/legacy-user-menu.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Legacy User Menu</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/language-menu.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Language Menu</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/404.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Error 404</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/500.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Error 500</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/pace.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Pace</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/examples/blank.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Blank Page</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<!--                 <a href="../../starter.html" class="nav-link"> -->
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Starter Page</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="nav-icon fas fa-search"></i> -->
<!--               <p> -->
<!--                 Search -->
<!--                 <i class="fas fa-angle-left right"></i> -->
<!--               </p> -->
<!--             </a> -->
<!--             <ul class="nav nav-treeview"> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/search/simple.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Simple Search</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<%--                 <a href="${pageContext.request.contextPath}/pages/search/enhanced.html" class="nav-link"> --%>
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Enhanced</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </li> -->
<!--           <li class="nav-header">MISCELLANEOUS</li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="../../iframe.html" class="nav-link"> -->
<!--               <i class="nav-icon fas fa-ellipsis-h"></i> -->
<!--               <p>Tabbed IFrame Plugin</p> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="https://adminlte.io/docs/3.1/" class="nav-link"> -->
<!--               <i class="nav-icon fas fa-file"></i> -->
<!--               <p>Documentation</p> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li class="nav-header">MULTI LEVEL EXAMPLE</li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="fas fa-circle nav-icon"></i> -->
<!--               <p>Level 1</p> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="nav-icon fas fa-circle"></i> -->
<!--               <p> -->
<!--                 Level 1 -->
<!--                 <i class="right fas fa-angle-left"></i> -->
<!--               </p> -->
<!--             </a> -->
<!--             <ul class="nav nav-treeview"> -->
<!--               <li class="nav-item"> -->
<!--                 <a href="#" class="nav-link"> -->
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Level 2</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<!--                 <a href="#" class="nav-link"> -->
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p> -->
<!--                     Level 2 -->
<!--                     <i class="right fas fa-angle-left"></i> -->
<!--                   </p> -->
<!--                 </a> -->
<!--                 <ul class="nav nav-treeview"> -->
<!--                   <li class="nav-item"> -->
<!--                     <a href="#" class="nav-link"> -->
<!--                       <i class="far fa-dot-circle nav-icon"></i> -->
<!--                       <p>Level 3</p> -->
<!--                     </a> -->
<!--                   </li> -->
<!--                   <li class="nav-item"> -->
<!--                     <a href="#" class="nav-link"> -->
<!--                       <i class="far fa-dot-circle nav-icon"></i> -->
<!--                       <p>Level 3</p> -->
<!--                     </a> -->
<!--                   </li> -->
<!--                   <li class="nav-item"> -->
<!--                     <a href="#" class="nav-link"> -->
<!--                       <i class="far fa-dot-circle nav-icon"></i> -->
<!--                       <p>Level 3</p> -->
<!--                     </a> -->
<!--                   </li> -->
<!--                 </ul> -->
<!--               </li> -->
<!--               <li class="nav-item"> -->
<!--                 <a href="#" class="nav-link"> -->
<!--                   <i class="far fa-circle nav-icon"></i> -->
<!--                   <p>Level 2</p> -->
<!--                 </a> -->
<!--               </li> -->
<!--             </ul> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="fas fa-circle nav-icon"></i> -->
<!--               <p>Level 1</p> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li class="nav-header">LABELS</li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="nav-icon far fa-circle text-danger"></i> -->
<!--               <p class="text">Important</p> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="nav-icon far fa-circle text-warning"></i> -->
<!--               <p>Warning</p> -->
<!--             </a> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a href="#" class="nav-link"> -->
<!--               <i class="nav-icon far fa-circle text-info"></i> -->
<!--               <p>Informational</p> -->
<!--             </a> -->
<!--           </li> -->
<!--         </ul> -->
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <!-- /.content -->
   <div class="container">
   
   	<jsp:doBody/>
   
   </div> 
  
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="float-right d-none d-sm-block">
      <b>Version</b> 3.2.0
    </div>
    <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Select2 -->
<script src="${pageContext.request.contextPath}/plugins/select2/js/select2.full.min.js"></script>
<!-- Bootstrap4 Duallistbox -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
<!-- InputMask -->
<script src="${pageContext.request.contextPath}/plugins/moment/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/inputmask/jquery.inputmask.min.js"></script>
<!-- date-range-picker -->
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
<!-- bootstrap color picker -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Bootstrap Switch -->
<script src="${pageContext.request.contextPath}/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<!-- BS-Stepper -->
<script src="${pageContext.request.contextPath}/plugins/bs-stepper/js/bs-stepper.min.js"></script>
<!-- dropzonejs -->
<script src="${pageContext.request.contextPath}/plugins/dropzone/min/dropzone.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/dist/js/adminlte.min.js"></script>

<!-- AdminLTE for demo purposes -->
<jsp:invoke fragment="bottomImports"/>
</body>
</html>