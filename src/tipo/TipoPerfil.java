package tipo;

public enum TipoPerfil {	
	FRODO(TipoAtraccion.AVENTURA, 10, 8),
	GALARDIEL(TipoAtraccion.PAISAJE, 100, 5),
	SAM(TipoAtraccion.DEGUSTACION, 36, 8);
	
	private TipoAtraccion preferencia;
	private int presupuesto;
	private float tiempo;
	
	public TipoAtraccion getPreferencia() {
		return preferencia;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public float getTiempo() {
		return tiempo;
	}

	private TipoPerfil(final TipoAtraccion preferencia, final int presupuesto, final int tiempo) {
		this.preferencia = preferencia; 
		this.presupuesto = presupuesto;
		this.tiempo = tiempo;
	}

	public boolean esPerfil(final String perfil) {
		return this.name().equalsIgnoreCase(perfil);
	}
}
