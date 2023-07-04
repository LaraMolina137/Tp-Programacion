package tipo;

public enum TipoPromocion {
	PORCENTAJE, ABSOLUTO, AXB;

	public boolean esPromocion(final String promocion) {
		return this.name().equalsIgnoreCase(promocion);
	}
}
