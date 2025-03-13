package appweb.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import appweb.data.SelectDataItem;
import appweb.model.ToDoList;
import appweb.util.BOMessages;
import appweb.util.JpaUtil;
import appweb.util.MessageAlert;
import appweb.util.TextUtil;


public class ToDoListDao extends AbstractDao<ToDoList> {

	public ToDoListDao() {
		super(ToDoList.class);
	}
	
	public void format(ToDoList toDoList) {
		toDoList.setTitulo(TextUtil.capitalise(toDoList.getTitulo()));
		toDoList.setTituloSearch(TextUtil.retiraAcentosCedil(toDoList.getTitulo()));
	}

	@Override
	public boolean validate(ToDoList toDoList) {
		if (TextUtil.isEmpty(toDoList.getTitulo()))
			getListErros().add(new MessageAlert("titulo", BOMessages.nullField("Titulo")));
		if (TextUtil.isEmpty(toDoList.getPrioridade()))
			getListErros().add(new MessageAlert("prioridade", BOMessages.nullField("Prioridade")));
		if (TextUtil.isEmpty(toDoList.getDescricao()))
			getListErros().add(new MessageAlert("descricao", BOMessages.nullField("Descrição")));
		
		return !hasErros();
	}

	@Override
	public boolean validateSave(ToDoList toDoList) {
		try {
			if (validate(toDoList)) {
				EntityManager em = JpaUtil.getManager();
				Long count = (Long) em.createQuery("select count(*) from ToDoList where titulo = :titulo")
						.setParameter("titulo", toDoList.getTitulo()).getSingleResult();
				if (count != 0L) {}
					//getListErros().add(new MessageAlert("titulo", BOMessages.uniqueKey("Titulo")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return !hasErros();
	}

	@Override
	public boolean validateUpdate(ToDoList toDoList) {
		try {
			if (validate(toDoList)) {
				EntityManager em = JpaUtil.getManager();
				Long count = (Long) em.createQuery("select count(*) from ToDoList where id <> :id and titulo = :titulo")
						.setParameter("id", toDoList.getId()).setParameter("titulo", toDoList.getTitulo())
						.getSingleResult();
				if (count != 0L) {}
					//getListErros().add(new MessageAlert("titulo", BOMessages.uniqueKey("Titulo")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return !hasErros();
	}

	@Override
	public boolean validateDelete(ToDoList toDoList) {
		
		if (toDoList.getId() == null)
			getListErros().add(new MessageAlert(
					"Nâo é possivél remover!"));
	
		return !hasErros();
	}
	
	
	public void updateStatus(ToDoList toDoList) {
		if (toDoList.getId() != null) {

			ToDoList tdl = find(toDoList.getId());
			tdl.setStatus(toDoList.getStatus());
			tdl.setDataConclusao(new Date());
			toDoList.setDataConclusao(tdl.getDataConclusao());
			update(tdl);
		}
	}
	
	
	public List<SelectDataItem> listAll() {
		List<SelectDataItem> list = new ArrayList<>();
		try {
			List<Object[]> listQ = getEm().createQuery("select id, titulo from ToDoList order by titulo", Object[].class)
					.getResultList();
			Iterator<Object[]> it = listQ.iterator();
			while (it.hasNext()) {
				Object[] obj = it.next();
				list.add(new SelectDataItem(String.valueOf(obj[0]), String.valueOf(obj[1])));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
