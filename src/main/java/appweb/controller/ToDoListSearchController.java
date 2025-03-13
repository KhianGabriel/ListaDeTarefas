package appweb.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import appweb.model.ToDoList;
import appweb.util.JpaUtil;
import appweb.util.TextUtil;


public class ToDoListSearchController extends AjaxSearchControllerAdapter {
	private static Logger log = LoggerFactory.getLogger(ToDoListSearchController.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String orderColumnStr = TextUtil.cast(request.getParameter("order[0][column]"));
			String orderDir = TextUtil.cast(request.getParameter("order[0][dir]"));
			String word = TextUtil.cast(request.getParameter("word")).trim();
			String field = TextUtil.cast(request.getParameter("field"));
			//String criterio = TextUtil.cast(request.getParameter("criterio"));
			String strLength = TextUtil.cast(request.getParameter("length"));
			String strStart = TextUtil.cast(request.getParameter("start"));
			
			System.out.println("word: " + word);
			
			int length = 10;
			int start = 0;
			int fieldIndex = 1;
			int orderColumn = 1;

			try {
				length = Integer.parseInt(strLength);
				start = Integer.parseInt(strStart);
				fieldIndex = Integer.parseInt(field);
				fieldIndex = fieldIndex > 5 ? 1 : fieldIndex;
				orderColumn = Integer.parseInt(orderColumnStr);
				orderColumn = orderColumn > 5 ? 1 : orderColumn;
				orderDir = orderDir.equals("desc") ? "desc" : "asc";
			}
			catch (Exception e) {
			}

			//String[] columns = { "id", "inep", "nomeSearch", "apelido", "creche", "situacao", "localizacao" };
			//String[] columns = { "id", "inep", "nomeSearch", "apelido", "creche", "situacao", "localizacao" };
			//String[] columns = { "id", "nomeSearch", "codigo", "estado.sigla", "ddd" };
			String[] columns = {"id", "tituloSearch", "dataCriacao", "status", "prioridade", "dataConclusao"};

			if (fieldIndex == 1) {
				word = TextUtil.retiraAcentosCedil(word);
			}
			// System.out.println( "orderColumn: " + orderColumn );
			// System.out.println( "orderDir: " + orderDir );

			String condition = "%" + word + "%";

			// System.out.println( "Total: " + list.size() );
			System.out.println( "condition: " + condition );

			long totalRecords = (Long) JpaUtil.getManager().createQuery("select count(id) from ToDoList ")
					.getSingleResult();
			setTotalRecords(totalRecords);

			long totalRecordsFiltered = (Long) JpaUtil.getManager()
					.createQuery("select count(id) from ToDoList  where " + columns[fieldIndex] + " like :cond ")
					.setParameter("cond", condition).getSingleResult();
			setTotalRecordsFiltered(totalRecordsFiltered);

			log.debug("Query: {}", "from ToDoList where " + columns[fieldIndex] +
					" like '" + condition + "' order by " +
					columns[fieldIndex] + " " + orderDir);
			List<ToDoList> list = JpaUtil.getManager()
					.createQuery("from ToDoList where " + columns[fieldIndex] + " like :cond  order by "
							+ columns[orderColumn] + " " + orderDir, ToDoList.class)
					.setParameter("cond", condition).setFirstResult(start).setMaxResults(length).getResultList();

			setListObject(list);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
