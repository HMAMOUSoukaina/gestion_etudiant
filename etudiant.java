package application;

public class etudiant {
		private String nom;
		private String prenom;
		private int nm_apogee;
		private String téléphone;
		private String email;
		private String date_naissance ;
		private String fillière;
		   public etudiant() {
		       super();
		   }
		   
		   public etudiant(String nom, String prenom, int nm_apogee, String téléphone, String email, String date_naissance, String fillière) {
			    super();
			    this.nom = nom;
			    this.prenom = prenom;
			    this.nm_apogee = nm_apogee;
			    this.téléphone =téléphone;
			    this.email = email;
			    this.date_naissance = date_naissance;
			    this.fillière = fillière;
			}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public int getNm_apogee() {
			return nm_apogee;
		}

		public void setNm_apogee(int nm_apogee) {
			this.nm_apogee = nm_apogee;
		}

		public String getTéléphone() {
			return téléphone;
		}

		public void setTéléphone(String téléphone) {
			this.téléphone = téléphone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getDate_naissance() {
			return date_naissance;
		}

		public void setDate_naissance(String date_naissance) {
			this.date_naissance = date_naissance;
		}

		public String getFillière() {
			return fillière;
		}

		public void setFillière(String fillière) {
			this.fillière = fillière;
		}
		   

}	