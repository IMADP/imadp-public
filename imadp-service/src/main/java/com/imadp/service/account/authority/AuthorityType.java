package com.imadp.service.account.authority;



/**
 * AuthorityType
 *
 * These are common authority names used to define role permissions.
 * They provide a convenient way of identifying authorities,
 * however the actual authorities are stored in the database as Authority objects.
 *
 * In general, the most commonly used authorities are often used as follows:
 * GUEST - A restrictive status usually granted to an unverified account
 * USER - A standard level of access granted to members with valid accounts
 * MODERATOR - A heightened level of access allowing moderation priviledges
 * MANAGER - A level of access designed to manage groups of users
 * ADMIN - An administrative status granted to those with complete access
 *
 * @version 1.0
 * @author Anthony DePalma
 */
public enum AuthorityType {
	GUEST, USER, MODERATOR, MANAGER, ADMIN;
}
