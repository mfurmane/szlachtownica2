package priv.mfurmane.szlachtownica.model.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.mfurmane.szlachtownica.model.main.entities.EntityLake;
import priv.mfurmane.szlachtownica.model.main.entities.EntitySubProvince;

public interface LakeRepository extends JpaRepository<EntityLake, Long> {
}
