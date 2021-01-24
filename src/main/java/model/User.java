/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;

/**
 * Класс User
 */
public class User {
	private int id;
	private String login;
	private String password;
	private ROLE role;

public User() {

}
		public User(int id, String login, String password, ROLE role) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='id', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=false, isMap=false, isSet=false, isList=false, isPrimitive=true, isString=false, isNumeric=true, isObject=false, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='null', type='int', typeQualifiedName='null', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='login', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=false, isMap=false, isSet=false, isList=false, isPrimitive=false, isString=true, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='String', type='java.lang.String', typeQualifiedName='java.lang.String', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='password', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=false, isMap=false, isSet=false, isList=false, isPrimitive=false, isString=true, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='String', type='java.lang.String', typeQualifiedName='java.lang.String', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public ROLE getRole() {
		return role;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='role', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=false, isMap=false, isSet=false, isList=false, isPrimitive=false, isString=false, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='ROLE', type='ru.javavision.model.User.ROLE', typeQualifiedName='ru.javavision.model.User.ROLE', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=true, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setRole(ROLE role) {
		this.role = role;
	}

	public enum ROLE {
		USER, ADMIN, UNKNOWN
	}
}