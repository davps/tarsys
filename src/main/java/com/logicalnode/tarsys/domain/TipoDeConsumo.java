package com.logicalnode.tarsys.domain;

public enum TipoDeConsumo {

	/*
	 * Tarifa binómicas - Subestación
	 * Tarifa binómicas - Linea
	 * Tarifa Alta Tensión
	 */
	PotenciaReservada,
	
	/*
	 * Tarifa binómicas - Subestación
	 * Tarifa binómicas - Linea
	 */
	ExcesoDePotenciaReservada,
	
	/*
	 * Tarifa binómicas - Subestación
	 * Tarifa binómicas - Linea
	 * Tarifa binómica diferencial - Subestación
	 * Tarifa binómica diferencial - Linea
	 */
	EnergiaEnPunta,
	
	/*
	 * Tarifa binómicas - Subestación
	 * Tarifa binómicas - Linea
	 * Tarifa binómica diferencial - Subestación
	 * Tarifa binómica diferencial - Linea
	 */
	EnergiaFueraDePunta,
	
	/*
	 * Tarifa monómica
	 * Tarifa Alta Tensión
	 */
	Energia,
	
	/*
	 * Tarifa binómica diferencial - Subestación
	 * Tarifa binómica diferencial - Linea
	 */
	PotenciaReservadaEnPunta,
	
	/*
	 * Tarifa binómica diferencial - Subestación
	 * Tarifa binómica diferencial - Linea
	 */
	PotenciaReservadaFueraDePunta,
	
	/*
	 * Tarifa binómica diferencial - Subestación
	 * Tarifa binómica diferencial - Linea
	 */
	ExcesoDePotenciaReservadaEnPunta,
	
	/*
	 * Tarifa binómica diferencial - Subestación
	 * Tarifa binómica diferencial - Linea
	 */
	ExcesoDePotenciaReservadaFueraDePunta

}
