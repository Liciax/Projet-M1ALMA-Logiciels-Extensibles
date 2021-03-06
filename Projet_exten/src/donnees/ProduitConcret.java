package donnees;

public class ProduitConcret implements IProduit {

	private String nom;
	private String type;
	private float prix;
	private int quantites;
	
	public ProduitConcret() {
		super();
	}

	
	public ProduitConcret(String nom, String type, float prix) {
		super();
		this.nom = nom;
		this.type = type;
		this.prix = prix;
		this.quantites = 0;
	}
	
	/**
	 * @param nom
	 * @param type
	 * @param prix
	 * @param quantites
	 */
	public ProduitConcret(String nom, String type, float prix, int q) {
		super();
		this.nom = nom;
		this.type = type;
		this.prix = prix;
		this.quantites = q;
	}
	
	/* (non-Javadoc)
	 * @see donnees.Produit#getNom()
	 */
	@Override
	public String getNom() {
		return nom;
	}
	
	/* (non-Javadoc)
	 * @see donnees.Produit#setNom(java.lang.String)
	 */
	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/* (non-Javadoc)
	 * @see donnees.Produit#getType()
	 */
	@Override
	public String getType() {
		return type;
	}
	
	/* (non-Javadoc)
	 * @see donnees.Produit#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see donnees.Produit#getPrix()
	 */
	@Override
	public float getPrix() {
		return prix;
	}
	
	/* (non-Javadoc)
	 * @see donnees.Produit#setPrix(float)
	 */
	@Override
	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getQuantites() {
		return quantites;
	}

	public void setQuantites(int quantites) {
		this.quantites = quantites;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Produit=[nom=" + nom + ", type=" + type + ", prix=" + prix + ", quantites=" + quantites + "]";
	}	
}
