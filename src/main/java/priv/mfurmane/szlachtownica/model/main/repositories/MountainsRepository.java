package priv.mfurmane.szlachtownica.model.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.mfurmane.szlachtownica.model.main.entities.EntityLake;
import priv.mfurmane.szlachtownica.model.main.entities.EntityMountains;

public interface MountainsRepository extends JpaRepository<EntityMountains, Long> {
}
