package appweb.test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import appweb.model.ToDoList;
import appweb.util.JpaUtil;

public class ORMTest {

	public static void main(String[] args) {

		EntityManager entityManager = JpaUtil.getEntityManager();

		System.out.println("Starting Transaction");

		entityManager.getTransaction().begin();

		ToDoList toDoList = new ToDoList();
		toDoList.setTitulo("Afazeres");
		toDoList.setDescricao("Lava a louça");
		toDoList.setDataCriacao(Date.from(LocalDateTime.now().toInstant(null)));
		
		entityManager.persist(toDoList);

		System.out.println("Saving toDoList to Database");

		entityManager.getTransaction().commit();
		System.out.println("Generated toDoList ID = " + toDoList.getId());

		// close the entity manager
		entityManager.close();
		// entityManagerFactory.close();

	}

}
