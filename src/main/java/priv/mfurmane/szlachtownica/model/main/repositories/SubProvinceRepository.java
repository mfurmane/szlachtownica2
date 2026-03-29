package priv.mfurmane.szlachtownica.model.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.mfurmane.szlachtownica.model.main.entities.EntitySubProvince;

public interface SubProvinceRepository extends JpaRepository<EntitySubProvince, Long> {
}
