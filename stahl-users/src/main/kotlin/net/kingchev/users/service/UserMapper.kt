/*
 *  Stahl geist
 *  Copyright (C) 2025 kiNgchev
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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