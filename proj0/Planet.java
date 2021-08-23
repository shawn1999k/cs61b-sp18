public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
		            double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public double calcDistance(Planet b) {
		double dx, dy, dist;

		dx = this.xxPos - b.xxPos;
		dy = this.yyPos - b.yyPos;
		dist = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));

		return dist;
	}

	public double calcForceExertedBy(Planet b) {
		double f;

		f = G * this.mass * b.mass / Math.pow(calcDistance(b), 2);

		return f;
	}

	public double calcForceExertedByX(Planet b) {
		double fx;

		fx = calcForceExertedBy(b) * (b.xxPos - this.xxPos) / calcDistance(b);

		return fx;
	}

	public double calcForceExertedByY(Planet b) {
		double fy;

		fy = calcForceExertedBy(b) * (b.yyPos - this.yyPos) / calcDistance(b);

		return fy;
	}

	public double calcNetForceExertedByX(Planet[] allp) {
		double allfx = 0;

		for (int i = 0; i<allp.length; i++) {
			if (calcDistance(allp[i]) == 0) {
				continue;
			}
			allfx += calcForceExertedByX(allp[i]);
		}

		return allfx;
	}

	public double calcNetForceExertedByY(Planet[] allp) {
		double allfy = 0;

		for (int i = 0; i<allp.length; i++) {
			if (calcDistance(allp[i]) == 0) {
				continue;
			}
			allfy += calcForceExertedByY(allp[i]);
		}

		return allfy;
	}	

	public void update(double dt, double fx, double fy) {
		double ax = fx / this.mass;
		double ay = fy / this.mass;
		
		this.xxVel = this.xxVel + ax * dt;
		this.yyVel = this.yyVel + ay * dt;
		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, 
			              "./images/" + this.imgFileName);
	}

}