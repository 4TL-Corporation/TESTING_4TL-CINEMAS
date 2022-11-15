package Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class JpaUtils {
	public static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("PS19510").createEntityManager();

	}
}
