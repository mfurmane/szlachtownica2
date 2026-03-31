package priv.mfurmane.szlachtownica.model.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.mfurmane.szlachtownica.model.main.entities.EntityRiver;
import priv.mfurmane.szlachtownica.model.main.entities.EntitySubProvince;

public interface RiverRepository extends JpaRepository<EntityRiver, Long> {
}
