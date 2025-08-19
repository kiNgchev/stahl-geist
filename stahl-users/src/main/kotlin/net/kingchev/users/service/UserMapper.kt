package net.kingchev.users.service

import net.kingchev.entity.Authority
import net.kingchev.entity.UserEntity
import net.kingchev.users.dto.AuthorityDto
import net.kingchev.users.dto.UserDto
import org.mapstruct.*

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserMapper {

    @Mapping(target = "authorities", expression = "java(toEntity(userDto.getAuthorities()))")
    public abstract fun toEntity(userDto: UserDto): UserEntity

    @Mapping(target = "authorities", expression = "java(toDto(userEntity.getAuthorities()))")
    public abstract fun toDto(userEntity: UserEntity): UserDto

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract fun partialUpdate(userDto: UserDto, @MappingTarget userEntity: UserEntity): UserEntity

    public abstract fun toEntity(authorityDto: AuthorityDto): Authority

    public abstract fun toDto(authority: Authority): AuthorityDto

    public abstract fun toEntity(users: List<UserDto>): List<UserEntity>

    public abstract fun toDto(users: List<UserEntity>): List<UserDto>

    public fun toEntity(authorities: Set<AuthorityDto>): Set<Authority> =
        authorities.map { toEntity(it) }.toSet()

    public fun toDto(authorities: Set<Authority>): Set<AuthorityDto> =
        authorities.map { toDto(it) }.toSet()

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract fun partialUpdate(authorityDto: AuthorityDto, @MappingTarget authority: Authority): Authority
}