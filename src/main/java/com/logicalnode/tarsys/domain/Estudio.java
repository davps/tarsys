package com.logicalnode.tarsys.domain;


//@Configurable
//@Entity
public class Estudio {

	private String nombre;

	private String empresa;
	
	private String categoria;

	private long numeroDeMedidor;

	private String observaciones;

	public String getNombre() {
        return this.nombre;
    }

	public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public String getEmpresa() {
        return this.empresa;
    }

	public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

	public String getCategoria() {
        return this.categoria;
    }

	public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

	public long getNumeroDeMedidor() {
        return this.numeroDeMedidor;
    }

	public void setNumeroDeMedidor(long numeroDeMedidor) {
        this.numeroDeMedidor = numeroDeMedidor;
    }

	public String getObservaciones() {
        return this.observaciones;
    }

	public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

//	@PersistenceContext
//    transient EntityManager entityManager;
//
//	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private Long id;

//	@Version
//    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

//	@Transactional
//    public void persist() {
//        if (this.entityManager == null) this.entityManager = entityManager();
//        this.entityManager.persist(this);
//    }
//
//	@Transactional
//    public void remove() {
//        if (this.entityManager == null) this.entityManager = entityManager();
//        if (this.entityManager.contains(this)) {
//            this.entityManager.remove(this);
//        } else {
//            Estudio attached = Estudio.findEstudio(this.id);
//            this.entityManager.remove(attached);
//        }
//    }
//
//	@Transactional
//    public void flush() {
//        if (this.entityManager == null) this.entityManager = entityManager();
//        this.entityManager.flush();
//    }
//
//	@Transactional
//    public void clear() {
//        if (this.entityManager == null) this.entityManager = entityManager();
//        this.entityManager.clear();
//    }
//
//	@Transactional
//    public Estudio merge() {
//        if (this.entityManager == null) this.entityManager = entityManager();
//        Estudio merged = this.entityManager.merge(this);
//        this.entityManager.flush();
//        return merged;
//    }
//
//	public static final EntityManager entityManager() {
//        EntityManager em = new Estudio().entityManager;
//        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
//        return em;
//    }
//
//	public static long countEstudios() {
//        return entityManager().createQuery("SELECT COUNT(o) FROM Estudio o", Long.class).getSingleResult();
//    }
//
//	public static List<Estudio> findAllEstudios() {
//        return entityManager().createQuery("SELECT o FROM Estudio o", Estudio.class).getResultList();
//    }
//
//	public static Estudio findEstudio(Long id) {
//        if (id == null) return null;
//        return entityManager().find(Estudio.class, id);
//    }
//
//	public static List<Estudio> findEstudioEntries(int firstResult, int maxResults) {
//        return entityManager().createQuery("SELECT o FROM Estudio o", Estudio.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
//    }

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Categoria: ").append(getCategoria()).append(", ");
        sb.append("Empresa: ").append(getEmpresa()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Nombre: ").append(getNombre()).append(", ");
        sb.append("NumeroDeMedidor: ").append(getNumeroDeMedidor()).append(", ");
        sb.append("Observaciones: ").append(getObservaciones()).append(", ");
        sb.append("Version: ").append(getVersion());
        return sb.toString();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ (int) (numeroDeMedidor ^ (numeroDeMedidor >>> 32));
		result = prime * result
				+ ((observaciones == null) ? 0 : observaciones.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudio other = (Estudio) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (numeroDeMedidor != other.numeroDeMedidor)
			return false;
		if (observaciones == null) {
			if (other.observaciones != null)
				return false;
		} else if (!observaciones.equals(other.observaciones))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	
	
	
}
