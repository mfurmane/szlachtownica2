package priv.mfurmane.szlachtownica.model.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.mfurmane.szlachtownica.model.main.entities.EntityProvince;

public interface ProvinceRepository extends JpaRepository<EntityProvince, Long> {
}
