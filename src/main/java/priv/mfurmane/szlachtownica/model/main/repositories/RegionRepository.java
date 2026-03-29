package priv.mfurmane.szlachtownica.model.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.mfurmane.szlachtownica.model.main.entities.EntityRegion;
import priv.mfurmane.szlachtownica.model.main.entities.EntitySubProvince;

public interface RegionRepository extends JpaRepository<EntityRegion, Long> {
}
