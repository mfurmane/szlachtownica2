package priv.mfurmane.szlachtownica.model.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.mfurmane.szlachtownica.model.main.entities.EntitySeaPart;
import priv.mfurmane.szlachtownica.model.main.entities.EntitySubProvince;

public interface SeaPartRepository extends JpaRepository<EntitySeaPart, Long> {
}
