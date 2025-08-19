package net.kingchev.cdn.repository

import net.kingchev.entity.cdn.AvatarEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
public interface AvatarRepository : JpaRepository<AvatarEntity, String>